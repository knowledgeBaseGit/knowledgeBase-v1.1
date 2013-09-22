package com.loongsoft.knowledgebase.util;

import java.text.SimpleDateFormat;

/**
 * 处理时间日期公共类
 * 
 * @author suoyanming 2013-8-31
 *
 */
public class DateTimeUtil {

	/**
	 * 获取当前时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param entity
	 */
	public static String currentTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System
				.currentTimeMillis());
	}

	/**
	 * 获取当前日期
	 * 
	 * @param entity
	 */
	public static String currentDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(System
				.currentTimeMillis());
	}

	/**
	 * 设置日期的yyyy-MM-dd HH:mm:ss格式 参数：毫秒值
	 * 
	 * @param entity
	 */
	public static String toLocalTime(long ms) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ms);
	}

	/**
	 * 设置日期的yyyy-MM-dd格式 参数：毫秒值
	 * 
	 * @param entity
	 */
	public static String toLocalDate(long ms) {
		return new SimpleDateFormat("yyyy-MM-dd").format(ms);
	}

	public static void main(String args[]) {
		System.out.println(DateTimeUtil.currentDate());
		System.out.println(DateTimeUtil.currentTime());
		System.out
				.println(DateTimeUtil.toLocalDate(System.currentTimeMillis()));
		System.out
				.println(DateTimeUtil.toLocalTime(System.currentTimeMillis()));
	}

}
