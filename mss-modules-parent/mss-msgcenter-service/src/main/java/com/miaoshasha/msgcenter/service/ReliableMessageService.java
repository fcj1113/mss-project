package com.miaoshasha.msgcenter.service;

import com.miaoshasha.common.base.BaseService;
import com.miaoshasha.common.entity.message.ReliableMessage;
import com.miaoshasha.common.utils.page.PageResult;
import com.miaoshasha.common.utils.page.PageQueryParam;

/**                            
 *                             
 * @author fengchaojun <br/>   
 * ----------------------------
 * Created with IDEA.          
 * Date：2018-09-26            
 * Time：18:49:21                 
 * ----------------------------
 */                            
public interface ReliableMessageService extends BaseService<ReliableMessage> {

    /**
     * 预存储消息
     */
    ReliableMessage preSaveMessage( ReliableMessage reliableMessage);

    /**
     * 确认并发送消息
     */
    int confirmAndSendMessage( Long messageId);

    /**
     * 存储并发送消息
     */
    Long saveAndSendMessage( ReliableMessage reliableMessage);

    /**
     * 直接发送消息
     */
    void directSendMessage( ReliableMessage reliableMessage);

    /**
     * 重发消息
     */
    int reSendMessage( ReliableMessage reliableMessage);

    /**
     * 根据messageId重发某条消息
     */
    int reSendMessageByMessageId( Long messageId);

    /**
     * 将消息标记为死亡消息
     */
    void setMessageToAreadlyDead( Long messageId);

    /**
     * 根据消息ID删除消息
     */
    int deleteMessageByMessageId( Long messageId);

    /**
     * 根据业务id删除消息
     */
    int deleteMessageByBizId( Long bizId);

    /**
     * 重发某个消息队列中的全部已死亡的消息.
     */
    void reSendAllDeadMessageByQueueName(String queueName);

    /**
     * 获取死亡的全部消息
     * @param queueName
     * @param pageQuery
     * @return
     */
    PageResult<ReliableMessage> listDeadMessageByQueue(String queueName, PageQueryParam pageQuery);

    /**
     * 分页获取待发送超时的数据
     */
    PageResult<ReliableMessage> listPageWaitConfimTimeOut(PageQueryParam pageParam);

    /**
     * 分页获取发送中超时的数据
     */
    PageResult<ReliableMessage> listPageSendingTimeOut(PageQueryParam pageParam);
}
