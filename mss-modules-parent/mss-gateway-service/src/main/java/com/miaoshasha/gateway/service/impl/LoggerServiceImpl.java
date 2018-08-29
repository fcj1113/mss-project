package com.miaoshasha.gateway.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.http.HttpUtil;
import com.miaoshasha.common.entity.system.SysLog;
import com.miaoshasha.common.mq.CommonMsgPublisher;
import com.miaoshasha.common.mq.RabbitConstants;
import com.miaoshasha.common.utils.SystemClock;
import com.miaoshasha.gateway.service.LoggerService;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by fengchaojun on 2018/7/27.
 */
@Slf4j
@Service
public class LoggerServiceImpl implements LoggerService {

    @Value("${zuul.logger.sendmq.enabled}")
    private boolean sendMq = false ;

    @Autowired
    private CommonMsgPublisher commonMsgPublisher;

    @Override
    public void send(RequestContext requestContext) {
        HttpServletRequest request = requestContext.getRequest();

        String requestUri = request.getRequestURI();
        String method = request.getMethod();
        String ip = HttpUtil.getClientIP(request);
        String userAgent = request.getHeader("base-agent");
        String params = HttpUtil.toParams(request.getParameterMap());
        Long startTime = (Long) requestContext.get("startTime");

        log.info("--------LoggerFilter::::::::  URI:{},METHOD:{},IP:{},AGENT:{},PARAMS:{}", requestUri, method, ip, userAgent, params);

        SysLog sysLog = new SysLog();
        sysLog.setIpAddr(ip);
        sysLog.setCreateTime(new Date());
        sysLog.setMethod(method);
        sysLog.setParams(params);
        sysLog.setDuration(SystemClock.now() - startTime);

        //正常发送服务异常解析
        if (requestContext.getResponseStatusCode() == HttpStatus.SC_INTERNAL_SERVER_ERROR
                && requestContext.getResponseDataStream() != null) {
            InputStream inputStream = requestContext.getResponseDataStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            InputStream stream1 = null;
            InputStream stream2;
            byte[] buffer = IoUtil.readBytes(inputStream);
            try {
                baos.write(buffer);
                baos.flush();
                stream1 = new ByteArrayInputStream(baos.toByteArray());
                stream2 = new ByteArrayInputStream(baos.toByteArray());
                String resp = IoUtil.read(stream1, "UTF-8");
                sysLog.setError(resp);
                requestContext.setResponseDataStream(stream2);
            } catch (IOException e) {
                log.error("响应流解析异常：", e);
                throw new RuntimeException(e);
            } finally {
                IoUtil.close(stream1);
                IoUtil.close(baos);
                IoUtil.close(inputStream);
            }
        }

        //网关内部异常
        Throwable throwable = requestContext.getThrowable();
        if (throwable != null) {
            log.error("网关异常", throwable);
            sysLog.setError(throwable.getMessage());
        }

        //发送消息
        if(sendMq) {
            commonMsgPublisher.send(sysLog, RabbitConstants.LOG_EXCHANGE_NAME, RabbitConstants.GATEWAY_LOG_QUEUE_NAME, RabbitConstants.GATEWAY_LOG_ROUTING_KEY);
        }
    }
}
