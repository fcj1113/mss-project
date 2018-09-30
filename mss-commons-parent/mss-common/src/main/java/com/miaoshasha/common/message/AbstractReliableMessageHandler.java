package com.miaoshasha.common.message;

import com.miaoshasha.common.entity.message.ReliableMessage;
import com.miaoshasha.common.enums.MessageStatusEnum;
import com.miaoshasha.common.utils.page.PageQueryParam;
import com.miaoshasha.common.utils.page.PageResult;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-09-29
 * Time：18:19
 * -----------------------------
 */
@Slf4j
public abstract class AbstractReliableMessageHandler implements ReliableMessageHandler {

    /**
     * 构建前三页的message数据，并存储在map中
     *
     * @return
     */
    public Map<Long, ReliableMessage> buildMessagesMap(MessageStatusEnum messageStatusEnum) {
        //存放查询结果
        Map<Long, ReliableMessage> messageMap = new HashMap<>();

        int pageSize = 1000;            //每页条数
        int maxHandlePageCount = 5;     //一次最多处理页数

        long currentPage = 1;            //当前处理页
        PageQueryParam pageQueryParam = new PageQueryParam();
        pageQueryParam.setPageSize(pageSize);
        pageQueryParam.setPageNo(currentPage);
        PageResult<ReliableMessage> pageResult = getPageResult(pageQueryParam, messageStatusEnum);

        List<ReliableMessage> rows = pageResult.getList();
        for (ReliableMessage item : rows) {
            messageMap.put(item.getMessageId(), item);
        }

        long totalPage = pageResult.getTotalPage();
        if (totalPage > maxHandlePageCount) {
            totalPage = maxHandlePageCount;
        }

        //按页数解析，并存储到map中
        for (currentPage = 2; currentPage <= totalPage; currentPage++) {
            pageQueryParam = new PageQueryParam();
            pageQueryParam.setPageSize(pageSize);
            pageQueryParam.setPageNo(currentPage);
            pageResult = getPageResult(pageQueryParam, messageStatusEnum);
            if (pageResult != null && pageResult.getList() != null) {
                List<ReliableMessage> otherResults = pageResult.getList();
                for (ReliableMessage rowItem : otherResults) {
                    messageMap.put(rowItem.getMessageId(), rowItem);
                }
            } else {
                break;
            }
        }

        return messageMap;
    }

    /**
     * 查询操作,根据状态查询不同的消息列表
     *
     * @param pageQueryParam
     * @return
     * @Param messageStatusEnum
     */
    protected abstract PageResult<ReliableMessage> getPageResult(PageQueryParam pageQueryParam, MessageStatusEnum messageStatusEnum);


}
