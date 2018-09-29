package com.miaoshasha.common.api.message;

import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.entity.message.ReliableMessage;
import com.miaoshasha.common.utils.page.PageResult;
import com.miaoshasha.common.utils.page.PageQueryParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 可靠消息对外开放的api
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-29
 * Time：15:52
 * -----------------------------
 */
@RequestMapping("message")
public interface ReliableMessageControllerApi {

    /**
     * 预存储消息
     */
    @RequestMapping(value = "/preSaveMessage", method = RequestMethod.POST)
    DataResult<ReliableMessage> preSaveMessage(@RequestBody ReliableMessage reliableMessage);

    /**
     * 确认并发送消息
     */
    @RequestMapping("/confirmAndSendMessage")
    DataResult<Boolean> confirmAndSendMessage(@RequestParam("messageId") Long messageId) ;
    /**
     * 存储并发送消息
     */
    @RequestMapping("/saveAndSendMessage")
    DataResult<Long> saveAndSendMessage(@RequestBody ReliableMessage reliableMessage) ;

    /**
     * 直接发送消息
     */
    @RequestMapping("/directSendMessage")
    DataResult<Boolean> directSendMessage(@RequestBody ReliableMessage reliableMessage) ;

    /**
     * 重发消息
     */
    @RequestMapping("/reSendMessage")
    DataResult<Integer> reSendMessage(@RequestBody ReliableMessage reliableMessage) ;

    /**
     * 根据messageId重发某条消息
     */
    @RequestMapping("/reSendMessageByMessageId")
    DataResult<Integer> reSendMessageByMessageId(@RequestParam("messageId") Long messageId) ;

    /**
     * 将消息标记为死亡消息
     */
    @RequestMapping("/setMessageToAreadlyDead")
    DataResult<Boolean> setMessageToAreadlyDead(@RequestParam("messageId") Long messageId);

    /**
     * 根据消息ID获取消息
     */
    @RequestMapping("/getMessageByMessageId")
    DataResult<ReliableMessage> getMessageByMessageId(@RequestParam("messageId") Long messageId);
    /**
     * 根据消息ID删除消息
     */
    @RequestMapping("/deleteMessageByMessageId")
    DataResult<Boolean> deleteMessageByMessageId(@RequestParam("messageId") Long messageId);

    /**
     * 根据业务id删除消息
     */
    @RequestMapping("/deleteMessageByBizId")
    DataResult<Boolean> deleteMessageByBizId(@RequestParam("bizId") Long bizId);
    /**
     * 重发某个消息队列中的全部已死亡的消息.
     */
    @RequestMapping("/reSendAllDeadMessageByQueueName")
    DataResult<Boolean> reSendAllDeadMessageByQueueName(@RequestParam("queueName") String queueName);

    /**
     * 分页获取待发送超时的数据
     */
    @RequestMapping("/listPageWaitConfimTimeOutMessages")
    DataResult<PageResult<ReliableMessage>> listPageWaitConfimTimeOutMessages(@RequestBody PageQueryParam pageParam) ;

    /**
     * 分页获取发送中超时的数据
     */
    @RequestMapping("/listPageSendingTimeOutMessages")
    DataResult<PageResult<ReliableMessage>> listPageSendingTimeOutMessages(@RequestBody PageQueryParam pageParam) ;
}
