package com.jeeplus.common.utils.skywin.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.alibaba.druid.util.StringUtils;



public class DateUtils {
	// 全日期时间格式
	private static final SimpleDateFormat formatFullDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	// 長日期时间格式
	private static final SimpleDateFormat formatDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// 短日期时间格式
	private static final SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");

	// 中間顯示上午或下午的長日期格式
	// private static final SimpleDateFormat formatSDT = new
	// SimpleDateFormat("yyyy-MM-dd a HH:mm:ss");

	/**
	 * <p>
	 * <strong>getCurrentlyDateTime</strong> - 获取当前日期时间的日期格式
	 * </p>
	 * 
	 * @version 1.0 @since 1.0 @author ljg
	 * 
	 * @param @return 日期时间的日期格式 @throws
	 */
	public static Date getCurrentlyDateTime() {
		return new Date();
	}

	/**
	 * <p>
	 * <strong>getCurrentlyFullDateTimeAsString</strong> -
	 * 获取当前日期时间的字符串的格式（包括日期、时间、毫秒）
	 * </p>
	 * 
	 * @version 1.0 @since 1.0 @author ljg
	 * 
	 * @param @return 当前日期时间的字符串的格式（包括日期、时间、毫秒） @throws
	 */
	public static String getCurrentlyFullDateTimeAsString() {
		String strDate = null;

		try {
			strDate = formatFullDateTime.format(getCurrentlyDateTime());
		} catch (Exception exception) {
		}
		return strDate;
	}

	/**
	 *  传入俩个时间点获取中间的拿一个时间
	 * @param dBegin
	 * @param dEnd
	 * @return
	 */
	public static String findDates(Date dBegin, Date dEnd) {

		long start = (dBegin.getTime() + dEnd.getTime()) / 2;
		Date date = new Date(start);

		return formatDateTime.format(date);
	}

	/**
	 * <p>
	 * <strong>getCurrentlyDateTimeAsString</strong> - 获取当前日期时间的字符串的格式（包括日期、时间）
	 * </p>
	 * 
	 * @version 1.0 @since 1.0 @author ljg
	 * 
	 * @param @return 当前日期时间的字符串的格式（包括日期、时间） @throws
	 */
	public static String getCurrentlyDateTimeAsString() {
		String strDate = null;

		try {
			strDate = formatDateTime.format(getCurrentlyDateTime());
		} catch (Exception exception) {
		}
		return strDate;
	}

	/**
	 * <p>
	 * <strong>getCurrentlyDateAsString</strong> - 获取当前日期时间的字符串的格式（包括日期）
	 * </p>
	 * 
	 * @version 1.0 @since 1.0 @author ljg
	 * 
	 * @param @return 当前日期时间的字符串的格式（包括日期） @throws
	 */
	public static String getCurrentlyDateAsString() {
		String strDate = null;

		try {
			strDate = formatDate.format(getCurrentlyDateTime());
		} catch (Exception exception) {
		}
		return strDate;
	}

	/**
	 * 两个时间之间的日期
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<Date> getBetweenDates(Date start, Date end) {
		List<Date> result = new ArrayList<Date>();
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(start);

		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(end);
		tempEnd.add(Calendar.HOUR_OF_DAY, 1);
		while (tempStart.before(tempEnd)) {
			result.add(tempStart.getTime());
			tempStart.add(Calendar.HOUR_OF_DAY, 1);
		}
		return result;
	}

	/**
	 * <p>
	 * <strong>fontNumberCN</strong> - 格式化中文数字
	 * </p>
	 * 
	 * @version 1.0 @since 1.0 @author ljg
	 * 
	 * @param n 指定的数字 @return 数字的中文表示 @throws
	 */
	public static String fontNumberCN(int n) {
		String result = "";

		switch (n) {
		case 0:
			result = "零";
			break;
		case 1:
			result = "一";
			break;
		case 2:
			result = "二";
			break;
		case 3:
			result = "三";
			break;
		case 4:
			result = "四";
			break;
		case 5:
			result = "五";
			break;
		case 6:
			result = "六";
			break;
		case 7:
			result = "七";
			break;
		case 8:
			result = "八";
			break;
		case 9:
			result = "九";
			break;
		case 10:
			result = "十";
			break;
		case 11:
			result = "十一";
			break;
		case 12:
			result = "十二";
			break;
		case 13:
			result = "十三";
			break;
		case 14:
			result = "十四";
			break;
		case 15:
			result = "十五";
			break;
		case 16:
			result = "十六";
			break;
		case 17:
			result = "十七";
			break;
		case 18:
			result = "十八";
			break;
		case 19:
			result = "十九";
			break;
		case 20:
			result = "二十";
			break;
		case 21:
			result = "二十一";
			break;
		case 22:
			result = "二十二";
			break;
		case 23:
			result = "二十三";
			break;
		case 24:
			result = "二十四";
			break;
		case 25:
			result = "二十五";
			break;
		case 26:
			result = "二十六";
			break;
		case 27:
			result = "二十七";
			break;
		case 28:
			result = "二十八";
			break;
		case 29:
			result = "二十九";
			break;
		case 30:
			result = "三十";
			break;
		case 31:
			result = "三十一";
			break;
		}
		return result;
	}

	/**
	 * <p>
	 * <strong>getNowCNDate</strong> - 格式化中文数字
	 * </p>
	 * 
	 * @version 1.0 @since 1.0 @author ljg
	 * 
	 * @param appointedDateTime 指定的日期时间 @return 日期时间的中文表示 @throws
	 */
	public static String getNowCNDate(String appointedDateTime) {
		String year = getCurrentlyYear(appointedDateTime);
		int month = Integer.parseInt(getCurrentlyMonth(appointedDateTime));
		int day = Integer.parseInt(getCurrentlyDay(appointedDateTime));
		String result = "";

		for (int i = 0; i < year.length(); i++) {
			result += fontNumberCN(Integer.parseInt(year.substring(i, i + 1)) * 1);
		}
		result += "年";
		result += fontNumberCN(month) + "月";
		result += fontNumberCN(day) + "日";
		return result;
	}

	/**
	 * 判断时间是否在两个时间之间
	 * 
	 * @param string
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean checkDate(Date date, Date startDate, Date endDate) {
		boolean ft = false;
		if (!date.before(startDate) && !endDate.before(date)) {
			ft = true;
		}
		return ft;
	}

	private static Date _strToDate(String sDate) {
		Date dateObj = null;

		try {
			sDate = String.valueOf((sDate == null) ? "" : sDate).trim();
			if (sDate.equals("") == false) {
				dateObj = formatDate.parse(sDate);
			}
		} catch (Exception exception) {
		}
		return dateObj;
	}

	private static Date _strToDateTime(String sDateTime) {
		Date dateObj = null;

		try {
			sDateTime = String.valueOf((sDateTime == null) ? "" : sDateTime).trim();
			if (sDateTime.equals("") == false) {
				dateObj = formatDateTime.parse(sDateTime);
			}
		} catch (Exception exception) {
		}
		return dateObj;
	}

	/**
	 * @methodName stringToDate ★★★★★
	 * @param req , res
	 * @return 日期对象
	 * @throws Exception
	 * 
	 * @author ljg 2010-09-07
	 * @description 将字符串转成日期型数据
	 */
	public static Date stringToDate(String sDate) {
		Date dateObj = null;

		if (sDate.indexOf(":") > -1) {
			dateObj = _strToDateTime(sDate);
		} else {
			dateObj = _strToDate(sDate);
		}
		return dateObj;
	}

	/**
	 * @methodName getPNNDayDateAsString ★未完整★
	 * @param req , res
	 * @return 以某個單位計算兩個時間差
	 * @throws Exception
	 * 
	 * @author ljg 2010-10-26
	 * @description 仿ASP的DateDiff函數，以某個單位計算兩個時間差
	 * @test System.out.println(DateUtils.dateDiff("h" ,
	 *       DateUtils.stringToDate("2010-10-26 00:00:00") , DateUtils.getNow()));
	 */
	public static double DateDiff(String type, Date d1, Date d2) {
		Double result = 0.0;
		int oneDay = 1000 * 60 * 60 * 24; // 一天
		long diff = d2.getTime() - d1.getTime();

		if (type.equals("d") == true) {
			result = diff * 1.0 / oneDay;
		} else if (type.equals("h") == true) {
			result = diff * 24.0 / oneDay;
		} else if (type.equals("m") == true) {
			result = diff * 24.0 * 60.0 / oneDay;
		} else if (type.equals("s") == true) {
			result = diff * 24.0 * 60.0 * 60.0 / oneDay;
		}
		return result;
	}

	/**
	 * @methodName getPNNDayDateAsString ★未完整★
	 * @param req , res
	 * @return 以某個單位計算兩個時間差
	 * @throws Exception
	 * 
	 * @author ljg 2010-10-26
	 * @description 仿ASP的DateDiff函數，以某個單位計算兩個時間差，可計算兩個日期的天數，兩個日期的小時數
	 * @test System.out.println(DateUtils.dateDiff("h" ,
	 *       DateUtils.stringToDate("2010-10-26 00:00:00") , DateUtils.getNow()));
	 */
	public static double DateDiff(String type, String sDate1, String sDate2) {
		Date dDate1 = stringToDate(sDate1);
		Date dDate2 = stringToDate(sDate2);
		Double result = 0.0;
		int oneDay = 1000 * 60 * 60 * 24; // 一天
		long diff = dDate2.getTime() - dDate1.getTime();

		if (type.equals("d") == true) {
			result = diff * 1.0 / oneDay;
		} else if (type.equals("h") == true) {
			result = diff * 24.0 / oneDay;
		} else if (type.equals("m") == true) {
			result = diff * 24.0 * 60.0 / oneDay;
		} else if (type.equals("s") == true) {
			result = diff * 24.0 * 60.0 * 60.0 / oneDay;
		}
		return result;
	}

	public static String dateTimeToString(Date date) {
		String dateString = null;

		try {
			if (date != null) {
				dateString = formatDateTime.format(date);
			}
		} catch (Exception exception) {
		}
		return dateString;
	}

	public static String dateToString(Date date) {
		String dateString = null;

		try {
			if (date != null) {
				dateString = formatDate.format(date);
			}
		} catch (Exception exception) {
			return null;
		}
		return dateString;
	}

	public static String getAppointedDateDay(Date appointedDate) {
		String strDate = formatDate.format(appointedDate);
		String result = "";

		result = strDate.split("\\-")[2];
		return result;
	}

	public static String getAppointedDateDay(String appointedDate) {
		String result = "";

		result = appointedDate.split("\\-")[2];
		return result;
	}

	public static String getAppointedDateMonth(Date appointedDate) {
		String strDate = formatDate.format(appointedDate);
		String result = "";

		result = strDate.split("\\-")[1];
		return result;
	}

	public static String getAppointedDateMonth(String appointedDate) {
		String result = "";

		result = appointedDate.split("\\-")[1];
		return result;
	}

	public static String getAppointedDateYear(Date appointedDate) {
		String strDate = formatDate.format(appointedDate);
		String result = "";

		result = strDate.split("\\-")[0];
		return result;
	}

	public static String getAppointedDateYear(String appointedDate) {
		String result = "";

		result = appointedDate.split("\\-")[0];
		return result;
	}

	/**
	 * <p>
	 * <strong>getCurrentlySecond</strong> - 获取一个指定日期时间的秒
	 * </p>
	 * 
	 * @version 1.0 @since 1.0 @author ljg
	 * 
	 * @param appointedDateTime 指定的日期时间 @return 一个指定日期时间的秒 @throws
	 */
	public static String getCurrentlySecond(String appointedDateTime) {
		String aDateTime = StringUtils.isEmpty(appointedDateTime) == true ? getPNNDayDateTimeAsString(0)
				: appointedDateTime.trim();
		String[] dtArray = aDateTime.split(" ");
		String[] tArray = dtArray[1].split("\\:");

		return tArray[2];
	}

	/**
	 * <p>
	 * <strong>getCurrentlyMinute</strong> - 获取一个指定日期时间的分钟
	 * </p>
	 * 
	 * @version 1.0 @since 1.0 @author ljg
	 * 
	 * @param appointedDateTime 指定的日期时间 @return 一个指定日期时间的分钟 @throws
	 */
	public static String getCurrentlyMinute(String appointedDateTime) {
		String aDateTime = StringUtils.isEmpty(appointedDateTime) == true ? getPNNDayDateTimeAsString(0)
				: appointedDateTime.trim();
		String[] dtArray = aDateTime.split(" ");
		String[] tArray = dtArray[1].split("\\:");

		return tArray[1];
	}

	/**
	 * <p>
	 * <strong>getCurrentlyHour</strong> - 获取一个指定日期时间的小时
	 * </p>
	 * 
	 * @version 1.0 @since 1.0 @author ljg
	 * 
	 * @param appointedDateTime 指定的日期时间 @return 一个指定日期时间的小时 @throws
	 */
	public static String getCurrentlyHour(String appointedDateTime) {
		String aDateTime = StringUtils.isEmpty(appointedDateTime) == true ? getPNNDayDateTimeAsString(0)
				: appointedDateTime.trim();
		String[] dtArray = aDateTime.split(" ");
		String[] tArray = dtArray[1].split("\\:");

		return tArray[0];
	}

	/**
	 * <p>
	 * <strong>getCurrentlyDay</strong> - 获取一个指定日期时间的号数
	 * </p>
	 * 
	 * @version 1.0 @since 1.0 @author ljg
	 * 
	 * @param appointedDateTime 指定的日期时间 @return 一个指定日期时间的号数 @throws
	 */
	public static String getCurrentlyDay(String appointedDateTime) {
		String aDateTime = StringUtils.isEmpty(appointedDateTime) == true ? getPNNDayDateTimeAsString(0)
				: appointedDateTime.trim();
		String[] dtArray = aDateTime.split(" ");
		String[] dArray = dtArray[0].split("\\-");

		return dArray[2];
	}

	/**
	 * <p>
	 * <strong>getCurrentlyMonth</strong> - 获取一个指定日期时间的月份
	 * </p>
	 * 
	 * @version 1.0 @since 1.0 @author ljg
	 * 
	 * @param appointedDateTime 指定的日期时间 @return 一个指定日期时间的月份 @throws
	 */
	public static String getCurrentlyMonth(String appointedDateTime) {
		String aDateTime = StringUtils.isEmpty(appointedDateTime) == true ? getPNNDayDateTimeAsString(0)
				: appointedDateTime.trim();
		String[] dtArray = aDateTime.split(" ");
		String[] dArray = dtArray[0].split("\\-");

		return dArray[1];
	}

	/**
	 * <p>
	 * <strong>getCurrentlyYear</strong> - 获取一个指定日期时间的年份
	 * </p>
	 * 
	 * @version 1.0 @since 1.0 @author ljg
	 * 
	 * @param appointedDateTime 指定的日期时间 @return 一个指定日期时间的年份 @throws
	 */
	public static String getCurrentlyYear(String appointedDateTime) {
		String aDateTime = StringUtils.isEmpty(appointedDateTime) == true ? getPNNDayDateTimeAsString(0)
				: appointedDateTime.trim();
		String[] dtArray = aDateTime.split(" ");
		String[] dArray = dtArray[0].split("\\-");

		return dArray[0];
	}

	public static Timestamp getNowAxTimestamp() {
		return new Timestamp(getCurrentlyDateTime().getTime());
	}

	public static String getPNNSecondDateTimeAsStringByAD(String appointedDate, int n) {
		String result = null;
		Calendar cal = Calendar.getInstance();
		String[] dtArray = appointedDate.split(" ", -1);
		String[] dArray = dtArray[0].split("\\-", -1);
		String[] tArray = dtArray[1].split(":", -1);

		if (dtArray.length == 2 && dArray.length == 3 && tArray.length == 3) {
			int iYear = Integer.parseInt(dArray[0]);
			int iMonth = Integer.parseInt(dArray[1]);
			int iDate = Integer.parseInt(dArray[2]);
			int iHour = Integer.parseInt(tArray[0]);
			int iMinute = Integer.parseInt(tArray[1]);
			int iSecond = Integer.parseInt(tArray[2]);

			cal.set(iYear, iMonth - 1, iDate, iHour, iMinute, iSecond);
			cal.add(Calendar.SECOND, n);
			result = formatDateTime.format(cal.getTime());
			return result;
		} else {
			return null;
		}
	}

	public static String getPNNMinuteDateTimeAsStringByAD(String appointedDate, int n) {
		String result = null;
		Calendar cal = Calendar.getInstance();
		String[] dtArray = appointedDate.split(" ", -1);
		String[] dArray = dtArray[0].split("\\-", -1);
		String[] tArray = dtArray[1].split(":", -1);

		if (dtArray.length == 2 && dArray.length == 3 && tArray.length == 3) {
			int iYear = Integer.parseInt(dArray[0]);
			int iMonth = Integer.parseInt(dArray[1]);
			int iDate = Integer.parseInt(dArray[2]);
			int iHour = Integer.parseInt(tArray[0]);
			int iMinute = Integer.parseInt(tArray[1]);
			int iSecond = Integer.parseInt(tArray[2]);

			cal.set(iYear, iMonth - 1, iDate, iHour, iMinute, iSecond);
			cal.add(Calendar.MINUTE, n);
			result = formatDateTime.format(cal.getTime());
			return result;
		} else {
			return null;
		}
	}

	public static String getPNNHourDateTimeAsStringByAD(String appointedDate, int n) {
		String result = null;
		Calendar cal = Calendar.getInstance();
		String[] dtArray = appointedDate.split(" ", -1);
		String[] dArray = dtArray[0].split("\\-", -1);
		String[] tArray = dtArray[1].split(":", -1);

		if (dtArray.length == 2 && dArray.length == 3 && tArray.length == 3) {
			int iYear = Integer.parseInt(dArray[0]);
			int iMonth = Integer.parseInt(dArray[1]);
			int iDate = Integer.parseInt(dArray[2]);
			int iHour = Integer.parseInt(tArray[0]);
			int iMinute = Integer.parseInt(tArray[1]);
			int iSecond = Integer.parseInt(tArray[2]);

			cal.set(iYear, iMonth - 1, iDate, iHour, iMinute, iSecond);
			cal.add(Calendar.HOUR, n);
			result = formatDateTime.format(cal.getTime());
			return result;
		} else {
			return null;
		}
	}

	/**
	 * @methodName getPNNDayDateAsString ★★★★★ PNN : previous next n
	 * @param req , res
	 * @return 指定的日期格式: formatDate
	 * @throws Exception
	 * 
	 * @author ljg 2010-09-07
	 * @description 獲取当前日期的前n天或后n天的日期
	 */
	public static String getPNNDayDateAsString(int n) {
		String result = null;
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, n);
		result = formatDate.format(cal.getTime());
		return result;
	}

	/**
	 * @methodName getPNNDayDateAsStringByAD ★★★★★ PNN : previous next n; AD :
	 *             AppointedDate
	 * @param req , res
	 * @return 指定的日期格式: formatDate
	 * @throws Exception
	 * 
	 * @author ljg 2010-09-07
	 * @description 獲取指定日期的前n天或后n天的日期
	 */
	public static String getPNNDayDateAsStringByAD(String appointedDate, int n) {
		String result = null;
		Calendar cal = Calendar.getInstance();
		String[] adArray = appointedDate.split("\\-");

		if (adArray.length == 3) {
			int iYear = Integer.parseInt(adArray[0]);
			int iMonth = Integer.parseInt(adArray[1]);
			int iDate = Integer.parseInt(adArray[2]);

			cal.set(iYear, iMonth - 1, iDate);
		}
		cal.add(Calendar.DATE, n);
		result = formatDate.format(cal.getTime());
		return result;
	}

	/**
	 * @methodName getPNNHourDateTimeAsString ★★★★★ PNN : previous next n
	 * @param req , res
	 * @return 指定的日期格式
	 * @throws Exception
	 * 
	 * @author ljg 2010-09-07
	 * @description 獲取当前日期时间的前n个小时或后n个小时的日期时间
	 */
	public static String getPNNHourDateTimeAsString(int n) {
		String result = null;
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.HOUR, n);
		result = formatDateTime.format(cal.getTime());
		return result;
	}

	/**
	 * @methodName getPNNDayDateTimeAsString ★★★★★ PNN : previous next n
	 * @param req , res
	 * @return 指定的日期格式
	 * @throws Exception
	 * 
	 * @author ljg 2010-09-07
	 * @description 獲取当前日期时间的前n天或后n天的日期时间
	 */
	public static String getPNNDayDateTimeAsString(int n) {
		String result = null;
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, n);
		result = formatDateTime.format(cal.getTime());
		return result;
	}

	/**
	 * @methodName getPNNDayDateTimeAsStringByAD ★★★★★ PNN : previous next n; AD :
	 *             AppointedDate
	 * @param req , res
	 * @return 指定的日期格式: formatDateTime
	 * @throws Exception
	 * 
	 * @author ljg 2010-09-07
	 * @description 獲取指定日期的前n天或后n天的日期
	 */
	public static String getPNNDayDateTimeAsStringByAD(String appointedDate, int n) {
		String result = null;
		Calendar cal = Calendar.getInstance();
		String[] adArray = appointedDate.split("\\-");

		if (adArray.length == 3) {
			int iYear = Integer.parseInt(adArray[0]);
			int iMonth = Integer.parseInt(adArray[1]);
			int iDate = Integer.parseInt(adArray[2]);

			cal.set(iYear, iMonth - 1, iDate);
		}
		cal.add(Calendar.DATE, n);
		result = formatDateTime.format(cal.getTime());
		return result;
	}

	/**
	 * @methodName getPNNMonthDateAsString ★★★★★ PNN : previous next n
	 * @param req , res
	 * @return 指定的日期格式
	 * @throws Exception
	 * 
	 * @author ljg 2010-09-07
	 * @description 獲取当前日期的前n个月或后n个月的日期
	 */
	public static String getPNNMonthDateAsString(int n) {
		String result = null;
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.MONTH, n);
		result = formatDate.format(cal.getTime());
		return result;
	}

	/**
	 * @methodName getPNNMonthDateAsStringByAD ★★★★★ PNN : previous next n; AD :
	 *             AppointedDate
	 * @param req , res
	 * @return 指定的日期格式: formatDate
	 * @throws Exception
	 * 
	 * @author ljg 2010-09-07
	 * @description 獲取指定日期的前n月或后n月的日期
	 */
	public static String getPNNMonthDateAsStringByAD(String appointedDate, int n) {
		String result = null;
		Calendar cal = Calendar.getInstance();
		String[] adArray = appointedDate.split("\\-");

		if (adArray.length == 3) {
			int iYear = Integer.parseInt(adArray[0]);
			int iMonth = Integer.parseInt(adArray[1]);
			int iDate = Integer.parseInt(adArray[2]);

			cal.set(iYear, iMonth - 1, iDate);
		}
		cal.add(Calendar.MONTH, n);
		result = formatDate.format(cal.getTime());
		return result;
	}

	/**
	 * @methodName getPNNMonthDateTimeAsString ★★★★★ PNN : previous next n
	 * @param req , res
	 * @return 指定的日期时间格式
	 * @throws Exception
	 * 
	 * @author ljg 2010-09-07
	 * @description 獲取当前日期时间的前n个月或后n个月的日期时间
	 */
	public static String getPNNMonthDateTimeAsString(int n) {
		String result = null;
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.MONTH, n);
		result = formatDateTime.format(cal.getTime());
		return result;
	}

	/**
	 * @methodName getPNNMonthDateTimeAsStringByAD ★★★★★ PNN : previous next n; AD :
	 *             AppointedDate
	 * @param req , res
	 * @return 指定的日期格式: formatDate
	 * @throws Exception
	 * 
	 * @author ljg 2010-09-07
	 * @description 獲取指定日期的前n月或后n月的日期
	 */
	public static String getPNNMonthDateTimeAsStringByAD(String appointedDate, int n) {
		String result = null;
		Calendar cal = Calendar.getInstance();
		String[] adArray = appointedDate.split("\\-");

		if (adArray.length == 3) {
			int iYear = Integer.parseInt(adArray[0]);
			int iMonth = Integer.parseInt(adArray[1]);
			int iDate = Integer.parseInt(adArray[2]);

			cal.set(iYear, iMonth - 1, iDate);
		}
		cal.add(Calendar.MONTH, n);
		result = formatDateTime.format(cal.getTime());
		return result;
	}

	/**
	 * @methodName getPNNYearDateAsString ★★★★★ PNN : previous next n
	 * @param req , res
	 * @return 指定的日期格式
	 * @throws Exception
	 * 
	 * @author ljg 2010-09-07
	 * @description 獲取当前日期的前n年或后n年的日期
	 */
	public static String getPNNYearDateAsString(int n) {
		String result = null;
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.YEAR, n);
		result = formatDate.format(cal.getTime());
		return result;
	}

	/**
	 * @methodName getPNNYearDateAsStringByAD ★★★★★ PNN : previous next n; AD :
	 *             AppointedDate
	 * @param req , res
	 * @return 指定的日期格式: formatDate
	 * @throws Exception
	 * 
	 * @author ljg 2010-09-07
	 * @description 獲取指定日期的前n年或后n年的日期
	 */
	public static String getPNNYearDateAsStringByAD(String appointedDate, int n) {
		String result = null;
		Calendar cal = Calendar.getInstance();
		String[] adArray = appointedDate.split("\\-");

		if (adArray.length == 3) {
			int iYear = Integer.parseInt(adArray[0]);
			int iMonth = Integer.parseInt(adArray[1]);
			int iDate = Integer.parseInt(adArray[2]);

			cal.set(iYear, iMonth - 1, iDate);
		}
		cal.add(Calendar.YEAR, n);
		result = formatDate.format(cal.getTime());
		return result;
	}

	/**
	 * @methodName getPNNYearDateTimeAsString ★★★★★ PNN : previous next n
	 * @param req , res
	 * @return 指定的日期时间格式
	 * @throws Exception
	 * 
	 * @author ljg 2010-09-07
	 * @description 獲取当前日期时间的前n年或后n年的日期时间
	 */
	public static String getPNNYearDateTimeAsString(int n) {
		String result = null;
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.YEAR, n);
		result = formatDateTime.format(cal.getTime());
		return result;
	}

	/**
	 * @methodName getPNNYearDateTimeAsStringByAD ★★★★★ PNN : previous next n; AD :
	 *             AppointedDate
	 * @param req , res
	 * @return 指定的日期格式: formatDate
	 * @throws Exception
	 * 
	 * @author ljg 2010-09-07
	 * @description 獲取指定日期的前n年或后n年的日期
	 */
	public static String getPNNYearDateTimeAsStringByAD(String appointedDate, int n) {
		String result = null;
		Calendar cal = Calendar.getInstance();
		String[] adArray = appointedDate.split("\\-");

		if (adArray.length == 3) {
			int iYear = Integer.parseInt(adArray[0]);
			int iMonth = Integer.parseInt(adArray[1]);
			int iDate = Integer.parseInt(adArray[2]);

			cal.set(iYear, iMonth - 1, iDate);
		}
		cal.add(Calendar.YEAR, n);
		result = formatDateTime.format(cal.getTime());
		return result;
	}

	/**
	 * @methodName getWeekNoByAD ★★★★★
	 * @param req , res
	 * @return 当前日期是星期几
	 * @throws Exception
	 * 
	 * @author ljg 2010-09-07
	 * @description 獲取当前日期星期序号
	 */
	public static int getWeekNo() {
		int result = 0;
		Calendar cal = Calendar.getInstance();

		result = cal.get(Calendar.DAY_OF_WEEK);
		return result;
	}

	/**
	 * @methodName getWeekNoByAD ★★★★★
	 * @param req , res
	 * @return 一个日期是星期几
	 * @throws Exception
	 * 
	 * @author ljg 2010-09-07
	 * @description 獲取指定日期星期序号
	 */
	public static int getWeekNoByAD(Date paramsDate) {
		String appointedDate = formatDate.format(paramsDate);

		return getWeekNoByAD(appointedDate);
	}

	/**
	 * @methodName getWeekNoByAD ★★★★★
	 * @param req , res
	 * @return 一个日期是星期几
	 * @throws Exception
	 * 
	 * @author ljg 2010-09-07
	 * @description 獲取指定日期星期序号
	 */
	public static int getWeekNoByAD(String appointedDate) {
		int result = 0;
		Calendar cal = Calendar.getInstance();
		String[] adArray = appointedDate.split("\\-");

		if (adArray.length == 3) {
			int iYear = Integer.parseInt(adArray[0]);
			int iMonth = Integer.parseInt(adArray[1]);
			int iDate = Integer.parseInt(adArray[2]);

			cal.set(iYear, iMonth - 1, iDate);
		}
		result = cal.get(Calendar.DAY_OF_WEEK);
		return result;
	}

	/**
	 * @methodName stringToTimestamp ★★★★★
	 * @param req , res
	 * @return
	 * @throws Exception
	 * 
	 * @author ljg
	 * @description
	 */
	public static Timestamp stringToTimestamp(String sTime) {
		Date date = DateUtils.stringToDate(sTime);

		if (date != null) {
			return new Timestamp(date.getTime());
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(formatDateTime.format(Calendar.getInstance(TimeZone.getTimeZone("GMT-8")).getTime()));

		System.out.println(getCurrentlyFullDateTimeAsString());
		System.out.println(getCurrentlyDateTimeAsString());
		System.out.println(getCurrentlyDateAsString());

		System.out.println(getNowCNDate(null));
		// String endTime = getPNNHourDateTimeAsString(0);
		// String beforeTime = getPNNHourDateTimeAsStringByAD(endTime, -19);
		//
		// System.out.println(endTime);
		// System.out.println(beforeTime);
	}

}
