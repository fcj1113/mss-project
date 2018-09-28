package com.miaoshasha.common.utils;

/**
 * 全局的常量定义
 */
public class GlobalConstants {

	public static final int DEFAULT_PAGE_SIZE = 20 ;//默认分页条数
	public static final int PWD_ERROR_MAX = 5 ;//密码错误次数最大值

	/**
	 * 存放Authorization的header字段
	 */
	public static final String AUTHORIZATION = "authorization";

	/**
	 * 当前登录用户id的字段名
	 */
	public static final String CURRENT_USER_ID = "CURRENT_USER_ID";

	/**
	 * 登录用户信息的缓存name
	 */
	public static final String LOGIN_USER_CACHE="LOGIN_USER_CACHE:";

}
