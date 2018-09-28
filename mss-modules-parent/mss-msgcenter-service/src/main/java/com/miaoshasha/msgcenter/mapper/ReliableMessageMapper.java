package com.miaoshasha.msgcenter.mapper;

import com.miaoshasha.common.base.BaseMapper;
import com.miaoshasha.common.entity.message.ReliableMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fengchaojun <br/>
 * ----------------------------
 * Created with IDEA.
 * Date：2018-09-26
 * Time：18:49:21
 * ----------------------------
 */
@Mapper
public interface ReliableMessageMapper extends BaseMapper<ReliableMessage> {

    /**
     * 根据业务id删除消息
     *
     * @param bizId
     * @return
     */
    int deleteMessageByBizId(long bizId);

    /**
     * 查询队列下所有死亡的消息
     *
     * @param consumerQueue
     * @return
     */
    List<ReliableMessage> getDeadMessageByQueue(@Param("consumerQueue") String consumerQueue,
                                                @Param("startIndex") long startIndex,
                                                @Param("pageSize") int pageSize);

    /**
     *
     * @param consumerQueue
     * @return
     */
    Long getCountDeadMessageByQueue(@Param("consumerQueue") String consumerQueue);
}
