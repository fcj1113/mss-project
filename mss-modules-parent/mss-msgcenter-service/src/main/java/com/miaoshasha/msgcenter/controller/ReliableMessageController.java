package com.miaoshasha.msgcenter.controller;

import com.miaoshasha.common.api.message.ReliableMessageControllerApi;
import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.entity.message.ReliableMessage;
import com.miaoshasha.common.utils.page.PageResult;
import com.miaoshasha.common.utils.page.PageQueryParam;
import com.miaoshasha.msgcenter.service.ReliableMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-27
 * Time：09:58
 * -----------------------------
 */
@RestController
public class ReliableMessageController implements ReliableMessageControllerApi {


    @Autowired
    private ReliableMessageService reliableMessageService;

    /**
     * 预存储消息
     */
    @Override
    public DataResult<ReliableMessage> preSaveMessage(@RequestBody ReliableMessage reliableMessage) {
        return DataResult.success(reliableMessageService.preSaveMessage(reliableMessage));
    }

    /**
     * 确认并发送消息
     */
    @Override
    public DataResult<Boolean> confirmAndSendMessage(@RequestParam("messageId") Long messageId) {
        int res = reliableMessageService.confirmAndSendMessage(messageId);
        return DataResult.success(res > 0);
    }

    /**
     * 存储并发送消息
     */
    @Override
    public DataResult<Long> saveAndSendMessage(@RequestBody ReliableMessage reliableMessage) {
        Long messageId = reliableMessageService.saveAndSendMessage(reliableMessage);
        return DataResult.success(messageId);
    }

    /**
     * 直接发送消息
     */
    @Override
    public DataResult<Boolean> directSendMessage(@RequestBody ReliableMessage reliableMessage) {
        reliableMessageService.directSendMessage(reliableMessage);
        return DataResult.success(true);
    }

    /**
     * 重发消息
     */
    @Override
    public DataResult<Integer> reSendMessage(@RequestBody ReliableMessage reliableMessage) {
        return DataResult.success(reliableMessageService.reSendMessage(reliableMessage));
    }

    /**
     * 根据messageId重发某条消息
     */
    @Override
    public DataResult<Integer> reSendMessageByMessageId(@RequestParam("messageId") Long messageId) {
        return DataResult.success(reliableMessageService.reSendMessageByMessageId(messageId));
    }

    /**
     * 将消息标记为死亡消息
     */
    @Override
    public DataResult<Boolean> setMessageToAreadlyDead(@RequestParam("messageId") Long messageId) {
        reliableMessageService.setMessageToAreadlyDead(messageId);
        return DataResult.success(true);
    }

    /**
     * 根据消息ID获取消息
     */
    @Override
    public DataResult<ReliableMessage> getMessageByMessageId(@RequestParam("messageId") Long messageId) {
        return DataResult.success(reliableMessageService.findById(messageId));
    }

    /**
     * 根据消息ID删除消息
     */
    @Override
    public DataResult<Boolean> deleteMessageByMessageId(@RequestParam("messageId") Long messageId) {
        int res = reliableMessageService.deleteMessageByMessageId(messageId);
        return DataResult.success(res > 0);
    }

    /**
     * 根据业务id删除消息
     */
    @Override
    public DataResult<Boolean> deleteMessageByBizId(@RequestParam("bizId") Long bizId) {
        return DataResult.success(reliableMessageService.deleteMessageByBizId(bizId) > 0);
    }

    /**
     * 重发某个消息队列中的全部已死亡的消息.
     */
    @Override
    public DataResult<Boolean> reSendAllDeadMessageByQueueName(@RequestParam("queueName") String queueName) {
        reliableMessageService.reSendAllDeadMessageByQueueName(queueName);
        return DataResult.success(true);
    }

    /**
     * 分页获取待发送超时的数据
     */
    @Override
    public DataResult<PageResult<ReliableMessage>> listPageWaitConfimTimeOutMessages(@RequestBody PageQueryParam pageParam) {
        return DataResult.success(reliableMessageService.listPageWaitConfimTimeOut(pageParam));
    }

    /**
     * 分页获取发送中超时的数据
     */
    @Override
    public DataResult<PageResult<ReliableMessage>> listPageSendingTimeOutMessages(@RequestBody PageQueryParam pageParam) {
        return DataResult.success(reliableMessageService.listPageSendingTimeOut(pageParam));
    }
}
