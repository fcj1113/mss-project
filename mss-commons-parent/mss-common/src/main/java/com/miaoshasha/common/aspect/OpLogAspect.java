package com.miaoshasha.common.aspect;

import com.miaoshasha.common.bean.OpLog;
import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.event.OplogEventPublisher;
import com.miaoshasha.common.mq.CommonMsgPublisher;
import com.miaoshasha.common.mq.RabbitConstants;
import com.miaoshasha.common.utils.AnnotationResolver;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by fengchaojun on 2018/3/20.
 */
@Aspect
@Component
public class OpLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(OpLogAspect.class);

    /**
     * 存储查询的旧数据
     */
    private static ThreadLocal<Object> hisDataLocal = new ThreadLocal<>();

    @Autowired
    private CommonMsgPublisher commonMsgPublisher;

    @Autowired
    private OplogEventPublisher oplogEventPublisher;

    //切入点
    @Pointcut(value = "@annotation(com.miaoshasha.common.annotation.OpLog)")
    private void pointcut() {
    }

    /**
     * 目标方法执行之前执行
     *
     * @param
     */
    @Before(value = "pointcut() && @annotation(opLog)")
    public void beforeMethod(JoinPoint joinPoint, com.miaoshasha.common.annotation.OpLog opLog) {
        //根据设置的注解调用方法获取历史记录
        Object historyResult = null;
        String methodName = opLog.methodName();
        if (!StringUtils.isEmpty(methodName) && !StringUtils.isEmpty(opLog.methodParam())) {
            Class clazz = joinPoint.getTarget().getClass();
            if (methodName.lastIndexOf(".") > -1) {
                String className = methodName.substring(0, methodName.lastIndexOf("."));
                methodName = methodName.substring(methodName.lastIndexOf(".") + 1, methodName.length());
                try {
                    clazz = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            //执行查询方法
            try {
                Method method = clazz.getMethod(methodName, new Class[]{Object.class});
                historyResult = method.invoke(clazz.newInstance(), new String[]{opLog.methodParam()});
                hisDataLocal.set(historyResult);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 方法执行成功后执行此方法
     *
     * @param joinPoint
     * @param opLog
     * @param result
     * @return
     */
    @AfterReturning(value = "pointcut() && @annotation(opLog)", returning = "result")
    public Object afterReturning(JoinPoint joinPoint, com.miaoshasha.common.annotation.OpLog opLog, Object result) {

        Object historyResult = hisDataLocal.get();

        String userIdObj = AnnotationResolver.parse(opLog.userId(), joinPoint);

        Long userId = Long.parseLong(userIdObj);
        logger.debug("userId=" + userId);

        logger.debug("执行结果：========" + result);
        if (result instanceof DataResult) {
            DataResult dataResult = (DataResult) result;
            logger.debug("执行结果：" + dataResult.toJson());
            if (dataResult.isSuccess()) {//返回结果成功
                //发送记录日志的MQ消息，暂定用rabbitMq，后续根据业务需要扩展为kafka
                OpLog log = new OpLog();
                log.setUserId(userId);
                log.setNotes(opLog.notes());
                log.setFuncCode(opLog.funcCode());
                log.setFuncName(opLog.funcName());
                log.setOpTime(System.currentTimeMillis());
                log.setOpType(opLog.opType().getValue());
                if (historyResult != null && historyResult instanceof DataResult) {
                    log.setHistoryResult(((DataResult) historyResult).toJson());
                }
                if (dataResult != null && dataResult instanceof DataResult) {
                    log.setLastResult(dataResult.toJson());
                }

                //消息
                commonMsgPublisher.send(log,RabbitConstants.LOG_EXCHANGE_NAME,RabbitConstants.OP_LOG_QUEUE_NAME,RabbitConstants.OP_LOG_ROUTING_KEY);
                //事件监听
                //oplogEventPublisher.publishEvent(new OplogEvent(log));
            }
        }

        hisDataLocal.remove();

        return result;
    }
}
