package com.miaoshasha.common.utils.page;

import com.miaoshasha.common.utils.GlobalConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页工具类
 * 
 */
public class PageUtil {

	/**
	 * 初始化分页类
	 * 
	 * @param <T>
	 * @param <T>
	 */
	public static <T> PageResult<T> initPage(HttpServletRequest request, Long totalCount, int pageSize) {
		long pageNo = 1;// 页码
		String pageNoParam = request.getParameter("pageNo");
		if (pageNoParam != null && !"".equals(pageNoParam)) {
			pageNo = Long.parseLong(pageNoParam);
		} else {
			pageNo = 1;// 页码，默认是第一页
		}

		PageResult<T> pageResult = new PageResult<T>(pageSize, totalCount);
		pageResult.setCurrentPage(pageNo);

		return pageResult;
	}

	public static <T> PageResult<T> initPage(HttpServletRequest request, Long totalCount) {
		return initPage(request, totalCount, GlobalConstants.DEFAULT_PAGE_SIZE);
	}

}
