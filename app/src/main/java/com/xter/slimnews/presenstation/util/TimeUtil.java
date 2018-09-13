package com.xter.slimnews.presenstation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimeUtil {
	private static String NORMAL_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";

	private static final ThreadLocal<SimpleDateFormat> NORMAL_1 = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(NORMAL_FORMAT_1, Locale.CHINA);
		}
	};

	public static String getNormalTime(long time) {
		return NORMAL_1.get().format(time);
	}

	public static long getNormalMills(String date) {
		try {
			return NORMAL_1.get().parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static String getDelayFormat(String preDate) {
		try {
			long delay = System.currentTimeMillis() - NORMAL_1.get().parse(preDate).getTime();
			return getDelayNormal(getNormalTime(delay));
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String getDelayNormal(String delay) {
		int year = Integer.parseInt(delay.substring(0, 4));
		if (year > 1970) {
			return (year - 1970) + "年前";
		}
		int month = Integer.parseInt(delay.substring(5, 7));
		if (month > 1) {
			return (month - 1) + "月前";
		}
		int day = Integer.parseInt(delay.substring(8, 10));
		if (day > 1) {
			return (day - 1) + "天前";
		}
		int hour = Integer.parseInt(delay.substring(11, 13));
		if (hour > 8) {
			return (hour - 8) + "小时前";
		}
		int min = Integer.parseInt(delay.substring(14, 16));
		if (min > 0) {
			return min + "分钟前";
		}
		return "刚刚";
	}

}
