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
	public static <T> Page<T> initPage(HttpServletRequest request, Long totalCount, int pageSize) {
		long pageNo = 1;// 页码
		String pageNoParam = request.getParameter("pageNo");
		if (pageNoParam != null && !"".equals(pageNoParam)) {
			pageNo = Long.parseLong(pageNoParam);
		} else {
			pageNo = 1;// 页码，默认是第一页
		}

		Page<T> page = new Page<T>(pageSize, totalCount);
		page.setCurrentPage(pageNo);

		return page;
	}

	public static <T> Page<T> initPage(HttpServletRequest request, Long totalCount) {
		return initPage(request, totalCount, GlobalConstants.PAGE_SIZE);
	}

}
