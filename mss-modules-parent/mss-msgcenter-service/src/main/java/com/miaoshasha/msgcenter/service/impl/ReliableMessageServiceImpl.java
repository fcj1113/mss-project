package com.miaoshasha.msgcenter.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.miaoshasha.common.base.AbstractBaseService;
import com.miaoshasha.common.enums.ErrorCode;
import com.miaoshasha.common.enums.YesOrNo;
import com.miaoshasha.common.exception.SystemException;
import com.miaoshasha.common.mq.CommonMsgPublisher;
import com.miaoshasha.common.mq.RabbitConstants;
import com.miaoshasha.common.utils.GlobalConstants;
import com.miaoshasha.common.utils.page.PageResult;
import com.miaoshasha.common.utils.page.PageQueryParam;
import com.miaoshasha.msgcenter.enums.MessageStatusEnum;
import com.miaoshasha.msgcenter.mapper.ReliableMessageMapper;
import com.miaoshasha.msgcenter.service.ReliableMessageService;
import com.miaoshasha.common.entity.message.ReliableMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author fengchaojun <br/>
 * ----------------------------
 * Created with IDEA.
 * Date：2018-09-26
 * Time：19:49:21
 * ----------------------------
 */

@Slf4j
@Service
public class ReliableMessageServiceImpl extends AbstractBaseService<ReliableMessageMapper, ReliableMessage> implements ReliableMessageService {

    @Autowired
    private ReliableMessageMapper reliableMessageMapper;

    @Autowired
    private CommonMsgPublisher commonMsgPublisher;

    /**
     * 默认消息的超时时间100秒
     */
    public static final int MESSAGE_TIMEOUT = 100;

    @Transactional
    @Override
    public ReliableMessage preSaveMessage(ReliableMessage reliableMessage) {
        this.checkMessage(reliableMessage);
        reliableMessage.setStatus(MessageStatusEnum.WAIT_VERIFY.name());
        reliableMessage.setAlreadyDead(YesOrNo.N.name());
        reliableMessage.setMessageSendTimes(0);//重发次数为0

        this.save(reliableMessage);
        return reliableMessage;
    }

    @Transactional
    @Override
    public int confirmAndSendMessage(Long messageId) {
        ReliableMessage reliableMessage = this.findById(messageId);
        if (reliableMessage == null) {
            throw new SystemException(ErrorCode.DATA_NULL_ERROR);
        }

        reliableMessage.setStatus(MessageStatusEnum.SENDING.name());
        reliableMessage.setUpdateTime(new Date());
        int res = this.modify(reliableMessage);

        //发送消息
        commonMsgPublisher.send(reliableMessage, RabbitConstants.TRANSACTION_EXCHANGE_NAME, reliableMessage.getConsumerQueue(), reliableMessage.getRoutingKey());

        return res;
    }

    @Transactional
    @Override
    public Long saveAndSendMessage(ReliableMessage reliableMessage) {
        this.checkMessage(reliableMessage);
        reliableMessage.setStatus(MessageStatusEnum.WAIT_VERIFY.name());
        reliableMessage.setAlreadyDead(YesOrNo.N.name());
        reliableMessage.setMessageSendTimes(0);//重发次数为0

        this.save(reliableMessage);
        //发送消息
        commonMsgPublisher.send(reliableMessage, RabbitConstants.TRANSACTION_EXCHANGE_NAME, reliableMessage.getConsumerQueue(), reliableMessage.getRoutingKey());

        return reliableMessage.getMessageId();
    }

    @Override
    public void directSendMessage(ReliableMessage reliableMessage) {
        this.checkMessage(reliableMessage);
        commonMsgPublisher.send(reliableMessage, RabbitConstants.TRANSACTION_EXCHANGE_NAME, reliableMessage.getConsumerQueue(), reliableMessage.getRoutingKey());
    }

    @Transactional
    @Override
    public int reSendMessage(ReliableMessage reliableMessage) {
        //检查消息数据的完整性
        this.checkMessage(reliableMessage);

        //更新消息发送次数
        reliableMessage.setMessageSendTimes(reliableMessage.getMessageSendTimes() + 1);
        reliableMessage.setUpdateTime(new Date());
        int res = this.modify(reliableMessage);

        //发送消息
        commonMsgPublisher.send(reliableMessage, RabbitConstants.TRANSACTION_EXCHANGE_NAME, reliableMessage.getConsumerQueue(), reliableMessage.getRoutingKey());
        return res;
    }

    @Override
    public int reSendMessageByMessageId(Long messageId) {
        ReliableMessage reliableMessage = this.findById(messageId);
        if (reliableMessage == null) {
            throw new SystemException(ErrorCode.DATA_NULL_ERROR);
        }
        return this.reSendMessage(reliableMessage);
    }

    @Transactional
    @Override
    public void setMessageToAreadlyDead(Long messageId) {
        ReliableMessage reliableMessage = this.findById(messageId);
        if (reliableMessage == null) {
            throw new SystemException(ErrorCode.DATA_NULL_ERROR);
        }

        reliableMessage.setAlreadyDead(YesOrNo.Y.name());
        reliableMessage.setUpdateTime(new Date());
        this.modify(reliableMessage);
    }

    @Transactional
    @Override
    public int deleteMessageByMessageId(Long messageId) {
        return this.removeById(messageId);
    }

    @Transactional
    @Override
    public int deleteMessageByBizId(Long bizId) {
        return reliableMessageMapper.deleteMessageByBizId(bizId);
    }

    /**
     * 查询队列下的死亡消息，默认重新发送第一页
     *
     * @param queueName
     */
    @Transactional
    @Override
    public void reSendAllDeadMessageByQueueName(String queueName) {

        //只查询第一页,数量是500,
        int pageSize = 500;
        PageQueryParam pageQueryParam = new PageQueryParam();
        pageQueryParam.setPageNo(1L);
        pageQueryParam.setPageSize(GlobalConstants.DEFAULT_PAGE_SIZE);
        PageResult<ReliableMessage> reliableMessagePageList = this.listDeadMessageByQueue(queueName, pageQueryParam);

        if (CollectionUtil.isEmpty(reliableMessagePageList.getList())) {
            return;
        }

        for (ReliableMessage reliableMessage : reliableMessagePageList.getList()) {
            this.reSendMessage(reliableMessage);
        }
    }

    @Override
    public PageResult<ReliableMessage> listDeadMessageByQueue(String queueName, PageQueryParam pageQueryParam) {

        long totalCount = reliableMessageMapper.getCountDeadMessageByQueue(queueName);

        long startIndex = (pageQueryParam.getPageNo() - 1) * pageQueryParam.getPageSize();
        int pageSize = pageQueryParam.getPageSize();
        List<ReliableMessage> reliableMessageList = reliableMessageMapper.getDeadMessageByQueue(queueName, startIndex, pageSize);

        PageResult<ReliableMessage> pageResult = new PageResult<>(pageQueryParam.getPageSize(), totalCount);
        pageResult.setList(reliableMessageList);
        pageResult.setCurrentPage(pageQueryParam.getPageNo());
        return pageResult;
    }

    /**
     * 分页获取待发送超时的数据，默认2分钟超时
     */
    @Override
    public PageResult<ReliableMessage> listPageWaitConfimTimeOut(PageQueryParam pageParam) {
        //往前偏移超时时间
        String dateTime = DateUtil.formatDateTime(DateUtil.offsetSecond(new Date(), -MESSAGE_TIMEOUT));

        List<ReliableMessage> reliableMessageList = reliableMessageMapper.getListTimeOut(dateTime,
                MessageStatusEnum.WAIT_VERIFY.name(), YesOrNo.Y.name(), pageParam.getStartIndex(), pageParam.getPageSize());


        long totalCount = reliableMessageMapper.getCountTimeOut(dateTime, MessageStatusEnum.WAIT_VERIFY.name(), YesOrNo.Y.name());

        PageResult<ReliableMessage> pageResult = new PageResult<>(pageParam.getPageSize(), totalCount);
        pageResult.setList(reliableMessageList);
        pageResult.setCurrentPage(pageParam.getPageNo());

        return pageResult;
    }

    @Override
    public PageResult<ReliableMessage> listPageSendingTimeOut(PageQueryParam pageParam) {
        //往前偏移超时时间
        String dateTime = DateUtil.formatDateTime(DateUtil.offsetSecond(new Date(), -MESSAGE_TIMEOUT));
        List<ReliableMessage> reliableMessageList = reliableMessageMapper.getListTimeOut(dateTime,
                MessageStatusEnum.SENDING.name(), "", pageParam.getStartIndex(), pageParam.getPageSize());


        long totalCount = reliableMessageMapper.getCountTimeOut(dateTime, MessageStatusEnum.SENDING.name(), "");

        PageResult<ReliableMessage> pageResult = new PageResult<>(pageParam.getPageSize(), totalCount);
        pageResult.setList(reliableMessageList);
        pageResult.setCurrentPage(pageParam.getPageNo());

        return pageResult;
    }


    /**
     * 检查消息参数
     */
    private void checkMessage(ReliableMessage reliableMessage) {
        if (reliableMessage == null) {
            throw new SystemException(ErrorCode.PARAM_ERROR);
        }
        if (StringUtils.isEmpty(reliableMessage.getMessageId())) {
            throw new SystemException(ErrorCode.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(reliableMessage.getMessageBody())) {
            throw new SystemException(ErrorCode.PARAM_MISSING);
        }
        if (StringUtils.isEmpty(reliableMessage.getConsumerQueue())) {
            throw new SystemException(ErrorCode.PARAM_MISSING);
        }
    }
}
