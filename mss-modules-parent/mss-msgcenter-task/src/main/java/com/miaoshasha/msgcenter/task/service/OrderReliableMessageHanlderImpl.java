package com.miaoshasha.msgcenter.task.service;

import cn.hutool.core.date.DateUtil;
import com.miaoshasha.api.message.ReliableMessageRemoteClient;
import com.miaoshasha.api.order.OrderRemoteClient;
import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.entity.message.ReliableMessage;
import com.miaoshasha.common.entity.order.OrderInfo;
import com.miaoshasha.common.enums.MessageStatusEnum;
import com.miaoshasha.common.message.AbstractReliableMessageHandler;
import com.miaoshasha.common.utils.AssertUtil;
import com.miaoshasha.common.utils.page.PageQueryParam;
import com.miaoshasha.common.utils.page.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * 订单消息处理
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-08-30
 * Time：20:48
 * -----------------------------
 */
@Slf4j
@Service
public class OrderReliableMessageHanlderImpl extends AbstractReliableMessageHandler {

    @Autowired
    private ReliableMessageRemoteClient reliableMessageRemoteClient ;

    @Autowired
    private OrderRemoteClient orderRemoteClient;

    /**
     * 最大重新发送次数
     */
    public static final int RESENDMAX = 5;
    @Override
    protected PageResult<ReliableMessage> getPageResult(PageQueryParam pageQueryParam, MessageStatusEnum messageStatusEnum) {
        DataResult<PageResult<ReliableMessage>>  dataResult = null;
        switch (messageStatusEnum){
            case SENDING:
                dataResult = reliableMessageRemoteClient.listPageSendingTimeOutMessages(pageQueryParam);
                break;
            case WAIT_VERIFY:
                dataResult = reliableMessageRemoteClient.listPageWaitConfimTimeOutMessages(pageQueryParam);
                break;
        }

        AssertUtil.assertResult(dataResult);
        return dataResult.getResult();
    }


    /**
     * 处理[WAIT_VERIFY]状态的消息
     */
    @Override
    public void handleWaitingConfirmTimeOutMessages() {
        Map<Long, ReliableMessage> map = this.buildMessagesMap(MessageStatusEnum.WAIT_VERIFY);
        //循环操作并根据bizid查询订单信息，订单成功，则确认并发送消息，订单失败，直接删除没用订单；
        for (Map.Entry<Long,ReliableMessage> entry : map.entrySet()){
            ReliableMessage reliableMessage = entry.getValue();
            Long orderId = reliableMessage.getBizUniqueId() ;
            if(orderId == null){
                //无订单，删掉消息
                reliableMessageRemoteClient.deleteMessageByBizId(orderId);
            }else{
                //
                DataResult<OrderInfo> orderInfoDataResult = orderRemoteClient.findById(orderId);
                AssertUtil.assertResult(orderInfoDataResult);
                OrderInfo orderInfo = orderInfoDataResult.getResult();
                if(orderInfo != null){
                    //发送消息
                    reliableMessageRemoteClient.confirmAndSendMessage(reliableMessage.getMessageId());
                }else{
                    //无订单，删掉消息
                    reliableMessageRemoteClient.deleteMessageByBizId(orderId);
                }

            }
        }

    }


    /**
     * 处理[SENDING]状态的消息
     */
    @Override
    public void handleSendingTimeOutMessages() {
        Map<Long, ReliableMessage> map = this.buildMessagesMap(MessageStatusEnum.SENDING);

        for (Map.Entry<Long,ReliableMessage> entry : map.entrySet()){
            ReliableMessage reliableMessage = entry.getValue();
            int reSendTimes = reliableMessage.getMessageSendTimes();

            //超过最大发送次数，标记为死亡，并不再发送
            if(reSendTimes >= RESENDMAX){
                reliableMessageRemoteClient.setMessageToAreadlyDead(reliableMessage.getMessageId());
                continue;
            }

            // 判断是否达到发送消息的时间间隔条件，按次数间隔分钟
            int times = reSendTimes == 0 ? 1 : reSendTimes;
            long currentTimeInMillis = Calendar.getInstance().getTimeInMillis();
            long needTime = currentTimeInMillis - times * 60 * 1000;
            long hasTime = reliableMessage.getUpdateTime().getTime();
            // 判断是否达到了可以再次发送的时间条件
            if (hasTime > needTime) {
                log.debug("currentTime[" + DateUtil.formatDateTime(new Date()) + "],[SENDING]消息上次发送时间["
                        + DateUtil.formatDateTime(reliableMessage.getUpdateTime()) + "],必须过了[" + times + "]分钟才可以再发送。");
                continue;
            }

            // 重新发送消息
            reliableMessageRemoteClient.reSendMessage(reliableMessage);

            log.debug("结束处理[SENDING]消息ID为[" + reliableMessage.getMessageId() + "]的消息");


        }
    }
}
