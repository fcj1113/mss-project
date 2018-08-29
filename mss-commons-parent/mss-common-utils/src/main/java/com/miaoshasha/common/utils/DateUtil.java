package com.miaoshasha.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String getParkDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String str = sdf.format(date);
		return str;
	}

	public static String formatDateByType(Date date, int type) {
		if (date != null) {
			String fmStr = "";
			switch (type) {
			case 1:
				fmStr = "yyyy年MM月dd日 HH时mm分ss秒";
				break;
			case 2:
				fmStr = "yyyy/MM/dd HH:mm";
				break;
			case 3:
				fmStr = "yyyy-MM-dd HH:mm:ss";
				break;
			case 4:
				fmStr = "yyyy年MM月dd日";
				break;
			case 5:
				fmStr = "yyyy-MM-dd";
				break;
			case 6:
				fmStr = "yyyyMM";
				break;

			}
			SimpleDateFormat sdf = new SimpleDateFormat(fmStr);

			return sdf.format(date);
		} else {
			return "";
		}
	}

	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 字符串转换成日期
	 * 
	 * @param str
	 * @return date
	 */
	public static Date StrToDate(String str,String formatStr) {
		
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * @param c1
	 *            需要比较的时间 不能为空(null),需要正确的日期格式
	 * @param c2
	 *            被比较的时间 为空(null)则为当前时间
	 * @param stype
	 *            返回值类型 0为多少天，1为多少个月，2为多少年
	 * @return
	 */
	public static int compareDate(Calendar c1, Calendar c2, int stype) {
		int n = 0;
		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			// list.add(df.format(c1.getTime())); // 这里可以把间隔的日期存到数组中 打印出来
			n++;
			if (stype == 1) {
				c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
			} else {
				c1.add(Calendar.DATE, 1); // 比较天数，日期+1
			}
		}
		n = n - 1;
		if (stype == 2) {
			n = (int) n / 365;
		}
		// System.out.println(date1+" -- "+date2+" 相差多少"+u[stype]+":"+n);
		return n;
	}

	/**
	 * 返回某月的最后一天
	 * @param date
	 * @return
	 */
	public static int getLastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return lastDay;
	}

	
	public static String DateToLocalStr(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public static String getTime(Date create_date) {
		long startT = new Date().getTime(); // 现在时间
		long endT = create_date.getTime(); // 创建时间
		long ss = (startT - endT) / (1000); // 共计秒数

		int MM = (int) ss / 60; // 共计分钟数
		int hh = (int) ss / 3600; // 共计小时数
		int dd = (int) hh / 24; // 共计天数

		if (MM == 0) {
			return "刚刚";
		} else if (MM < 60 && MM != 0) {
			return String.valueOf(MM) + "分钟前";
		} else if (MM >= 60 && hh < 24) {
			return String.valueOf(hh) + "小时前";
		} else if (hh >= 24 && dd <= 31) {
			return String.valueOf(dd) + "天前";
		} else if (dd > 31 && dd < 62) {
			return "一个月以前";
		} else {
			return "很久以前";
		}
	}

}
