package com.miaoshasha.common.utils.page;

import com.miaoshasha.common.utils.GlobalConstants;

import java.util.List;

/**
 * 分页类
 * @param <T>
 */
public class Page<T> implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	//前一页
	private Boolean hasPrePage;
	//后一页
	private Boolean hasNextPage;
	//每页显示多少条:默认20条
	private int pageSize = GlobalConstants.DEFAULT_PAGE_SIZE;
	//总页数
	private Long totalPage;
	//当前第多少页:默认第1页
	private Long currentPage = 1L;
	//开始下标
	private Long beginIndex;
	//结束下标
	private Long endIndex;
	//总共多少条
	private Long totalCount;	
	
	//保存数据使用
	private List<T> list ;
	
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	/**
	 * 常用,用于计算分页
	 * */
	public Page(Long totalRecords){		
		this.totalCount = totalRecords;
		setTotalPage(getTotalPage(totalRecords));     
	}
	
	/**
	 * 设置每页显示多少条时使用
	 * */
	public Page(int pageSize,Long totalRecords){	
		this.pageSize = pageSize;
		this.totalCount = totalRecords;
		setTotalPage(getTotalPage(totalRecords));     
	}
	
	/**
	 * 计算总页数
	 * */
	private Long getTotalPage(Long totalRecords) {
	     Long totalPage = 0L;	 
	     pageSize = pageSize < GlobalConstants.DEFAULT_PAGE_SIZE ? GlobalConstants.DEFAULT_PAGE_SIZE : pageSize;
	     if (totalRecords % pageSize == 0)
	       totalPage = totalRecords / pageSize;
	     else {
	       totalPage = totalRecords / pageSize + 1;
	     }
	     return totalPage;
	}	
	

	public Long getBeginIndex() {
		this.beginIndex = (currentPage - 1) * pageSize;
		return this.beginIndex;
	}

	public void setBeginIndex(Long beginIndex) {
		this.beginIndex = beginIndex;
	}

	public Long getCurrentPage() {
		this.currentPage = currentPage == 0 ? 1 : currentPage;
		return this.currentPage;
	}

	public void setCurrentPage(Long currentPage) {
		if(0 == currentPage){
			currentPage = 1L;
		}
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		this.pageSize = pageSize < GlobalConstants.DEFAULT_PAGE_SIZE ? GlobalConstants.DEFAULT_PAGE_SIZE : pageSize;
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {		
		this.pageSize = pageSize;
	}

	public Boolean getHasNextPage() {
		this.hasNextPage = (currentPage != totalPage) && (totalPage != 0);
		return this.hasNextPage;
	}

	public void setHasNextPage(Boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public Boolean getHasPrePage() {
		this.hasPrePage = currentPage != 1;
		return this.hasPrePage;
	}

	public void setHasPrePage(Boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}

	public Long getTotalPage() {
		return this.totalPage;
	}

	public void setTotalPage(Long totalPage) {
		if(this.currentPage > totalPage){
			this.currentPage = totalPage;
		}
		this.totalPage = totalPage;
	}

	public Long getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(Long totalCount) {
		setTotalPage(getTotalPage(totalCount));  
		this.totalCount = totalCount;
	}

	public Long getEndIndex() {
		this.endIndex = (currentPage) * pageSize;
		return endIndex;
	}

	public void setEndIndex(Long endIndex) {
		this.endIndex = endIndex;
	}	
}