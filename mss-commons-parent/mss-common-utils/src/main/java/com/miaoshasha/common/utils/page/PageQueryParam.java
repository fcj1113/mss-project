package com.miaoshasha.common.utils.page;

import lombok.Data;

/**
 * @author fengchaojun <br/>
 * -----------------------------
 * Created with IDEA.
 * Date：2018-06-27
 * Time：20:08
 * -----------------------------
 */
@Data
public class PageQueryParam {
    /**
     * 每页的条数
     */
    private Integer pageSize;

    /**
     * 页编码(第几页)
     */
    private Long pageNo;

    /**
     * 排序方式(asc 或者 desc)
     */
    private String order;

    /**
     * 排序的字段名称
     */
    private String sort;

//    long startIndex = (pageQueryParam.getPageNo() - 1) * pageQueryParam.getPageSize();
//    int pageSize = pageQueryParam.getPageSize();

    private long startIndex ;

    public void setStartIndex(){
        startIndex = (getPageNo()-1) * getPageSize();
    }
}
