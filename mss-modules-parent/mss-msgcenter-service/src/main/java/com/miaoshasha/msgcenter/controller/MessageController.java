package com.miaoshasha.msgcenter.controller;

import cn.hutool.db.PageResult;
import com.miaoshasha.common.domain.DataResult;
import com.miaoshasha.common.entity.message.ReliableMessage;
import com.miaoshasha.common.utils.page.PageQueryParam;
import org.springframework.web.bind.annotation.*;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-27
 * Time：09:58
 * -----------------------------
 */
@RestController
@RequestMapping("message")
public class MessageController {



    /**
     * 预存储消息
     */
    @RequestMapping(value = "/preSaveMessage", method = RequestMethod.POST)
    public DataResult<ReliableMessage> preSaveMessage(@RequestBody ReliableMessage reliableMessage){

        return null ;
    }

    /**
     * 确认并发送消息
     */
    @RequestMapping("/confirmAndSendMessage")
    public DataResult<String> confirmAndSendMessage(@RequestParam("messageId") String messageId){
        return null;
    }

    /**
     * 存储并发送消息
     */
    @RequestMapping("/saveAndSendMessage")
    public DataResult<String> saveAndSendMessage(@RequestBody ReliableMessage reliableMessage){
        return null;
    }

    /**
     * 直接发送消息
     */
    @RequestMapping("/directSendMessage")
    public DataResult<String> directSendMessage(@RequestBody ReliableMessage reliableMessage){
        return null;
    }

    /**
     * 重发消息
     */
    @RequestMapping("/reSendMessage")
    public DataResult<String> reSendMessage(@RequestBody ReliableMessage reliableMessage){
        return null;
    }

    /**
     * 根据messageId重发某条消息
     */
    @RequestMapping("/reSendMessageByMessageId")
    public DataResult<String> reSendMessageByMessageId(@RequestParam("messageId") String messageId){
        return null;
    }

    /**
     * 将消息标记为死亡消息
     */
    @RequestMapping("/setMessageToAreadlyDead")
    public DataResult<String> setMessageToAreadlyDead(@RequestParam("messageId") String messageId){
        return null;
    }

    /**
     * 根据消息ID获取消息
     */
    @RequestMapping("/getMessageByMessageId")
    ReliableMessage getMessageByMessageId(@RequestParam("messageId") String messageId){
        return null;
    }

    /**
     * 根据消息ID删除消息
     */
    @RequestMapping("/deleteMessageByMessageId")
    public DataResult<String> deleteMessageByMessageId(@RequestParam("messageId") String messageId){
        return null;
    }

    /**
     * 根据业务id删除消息
     */
    @RequestMapping("/deleteMessageByBizId")
    public DataResult<String> deleteMessageByBizId(@RequestParam("bizId") Long bizId){
        return null;
    }

    /**
     * 重发某个消息队列中的全部已死亡的消息.
     */
    @RequestMapping("/reSendAllDeadMessageByQueueName")
    public DataResult<String> reSendAllDeadMessageByQueueName(@RequestParam("queueName") String queueName){
        return null;
    }

    /**
     * 分页获取待发送超时的数据
     */
    @RequestMapping("/listPagetWaitConfimTimeOutMessages")
    public PageResult<ReliableMessage> listPagetWaitConfimTimeOutMessages(@RequestBody PageQueryParam pageParam){
        return null;
    }

    /**
     * 分页获取发送中超时的数据
     */
    @RequestMapping("/listPageSendingTimeOutMessages")
    public PageResult<ReliableMessage> listPageSendingTimeOutMessages(@RequestBody PageQueryParam pageParam){
        return null;
    }
}
