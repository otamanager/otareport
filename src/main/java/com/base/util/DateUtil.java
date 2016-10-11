package com.base.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 通用的函数(日期相关函数)
 * 
 * @version 3.0
 */

public class DateUtil {
	// 每个月的天数
	static private int[] daysInMonth = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public static long nowTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.getTimeInMillis();
	}

	/**
	 * unix时间戳
	 * 
	 * @return
	 */
	public static int nowTimeUnix() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		Long now = cal.getTimeInMillis();
		now = now / 1000;
		return now.intValue();
	}

	/**
	 * 日期/时间格式化显示（年、月、日、时、分、秒、毫秒、星期）
	 * 
	 * @param strDate
	 *            需要格式化的日期
	 * @param strOldFormat
	 *            该日期的格式串
	 * @param strNewFormat
	 *            需要格式化的格式串
	 * @return 格式化后的字符串（String）
	 * @throws ParseException
	 */
	public static String msFormatDateTime(String strDate, String strOldFormat, String strNewFormat) throws ParseException {
		java.util.Date dtDate = new Date();

		if (strNewFormat.equals("") | strOldFormat.equals(""))
			strNewFormat = "yyyy-MM-dd HH:mm:ss";

		SimpleDateFormat myFormatter = new SimpleDateFormat(strNewFormat);
		myFormatter.setLenient(false);
		dtDate = new SimpleDateFormat(strOldFormat).parse(strDate);
		return myFormatter.format(dtDate.getTime());
	}

	/**
	 * 日期/时间格式化显示（年、月、日、时、分、秒、毫秒、星期）
	 * 
	 * @param dtmDate
	 *            需要格式化的日期（java.util.Date）
	 * @param strFormat
	 *            该日期的格式串
	 * @return 格式化后的字符串（String）
	 */
	public static String msFormatDateTime(java.util.Date dtmDate, String strFormat) {

		if (strFormat.equals(""))
			strFormat = "yyyy-MM-dd HH:mm:ss";

		SimpleDateFormat myFormat = new SimpleDateFormat(strFormat);

		return myFormat.format(dtmDate.getTime());
	}

	/**
	 * 日期/时间格式化显示（年、月、日）
	 * 
	 * @param dtmDate
	 *            需要格式化的日期（java.util.Date）
	 * @param strFormat
	 *            该日期的格式串
	 * @return 格式化后的字符串（String）
	 */
	public static String msFormatDate(java.util.Date dtmDate, String strFormat) {

		if (strFormat.equals(""))
			strFormat = "yyyy-MM-dd";

		SimpleDateFormat myFormat = new SimpleDateFormat(strFormat);

		return myFormat.format(dtmDate.getTime());
	}

	/**
	 * 取得给定日期数天前（后）的日期函数
	 * 
	 * @param strDate
	 *            需要进行加减的日期("yyyy-MM-dd")
	 * @param intStep
	 *            需要计算的间隔天数
	 * @return 返回计算后的日期（java.util.Date）
	 * @throws ParseException
	 */
	public static java.util.Date msInterDate(String strDate, int intStep) throws ParseException {

		String strFormat = "yyyy-MM-dd";
		java.util.Date dtDate = null;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat myFormatter = new SimpleDateFormat(strFormat);
		myFormatter.setLenient(false);
		dtDate = myFormatter.parse(strDate);

		cal.setTime(dtDate);
		cal.add(Calendar.DAY_OF_MONTH, intStep);

		return cal.getTime();
	}

	/**
	 * 取得给定日期数天前（后）的日期函数
	 * 
	 * @param strDate
	 *            需要进行加减的日期("yyyy-MM-dd")
	 * @param intStep
	 *            需要计算的间隔天数
	 * @return 返回计算后的日期（String）
	 * @throws ParseException
	 */
	public static String msInterDateString(String strDate, int intStep) throws ParseException {
		String strFormat = "yyyy-MM-dd";
		java.util.Date dtDate = null;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat myFormatter = new SimpleDateFormat(strFormat);
		myFormatter.setLenient(false);
		dtDate = myFormatter.parse(strDate);

		cal.setTime(dtDate);
		cal.add(Calendar.DAY_OF_MONTH, intStep);

		return msFormatDateTime(cal.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 日期比较
	 * 
	 * @param strDate1
	 *            需要进行计较的日期1(yyyy-MM-dd)
	 * @param strDate2
	 *            需要进行计较的日期2(yyyy-MM-dd)
	 * @return 比较的结果（int） -1：strDate1 < strDate2 0：strDate1 = strDate2
	 *         1：strDate1 > strDate2
	 * @throws ParseException
	 */
	public static int msCompareDate(String strDate1, String strDate2) throws ParseException {
		String strFormat = "yyyy-MM-dd";
		java.util.Date dtDate1 = null;
		java.util.Date dtDate2 = null;
		int intCom = 0;
		SimpleDateFormat myFormatter = new SimpleDateFormat(strFormat);
		myFormatter.setLenient(false);
		dtDate1 = myFormatter.parse(strDate1);
		dtDate2 = myFormatter.parse(strDate2);

		intCom = dtDate1.compareTo(dtDate2);
		if (intCom > 0)
			return 1;
		if (intCom < 0)
			return -1;
		return 0;
	}

	/**
	 * 获得日期的星期
	 * 
	 * @param strDate
	 *            需要计算星期的日期(yyyy-MM-dd)
	 * @return 计算后的星期（int）
	 * @throws ParseException
	 */
	public static int msGetWeeks(String strDate) throws ParseException {
		String strFormat = "yyyy-MM-dd";
		java.util.Date dtDate = null;
		int intDay = 0;
		SimpleDateFormat myFormatter = new SimpleDateFormat(strFormat);
		Calendar cal = Calendar.getInstance();

		dtDate = myFormatter.parse(strDate);
		cal.setTime(dtDate);
		intDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (intDay == 0)
			intDay = 7;
		return intDay;
	}

	/**
	 * 获得给定月份的星期数,以星期日为一周的第一天
	 * 
	 * @param strMonth
	 *            需要计算星期的日期(yyyy-MM-dd)
	 * @return 计算后的星期数（int）
	 * @throws ParseException
	 */
	public static int msGetWeeksofMonth(String strMonth) throws ParseException {
		String strFormat = "yyyy-MM-dd";
		int intDays = msGetDaysofMonth(strMonth);
		String strDays = strMonth.substring(0, 8) + String.valueOf(intDays);
		java.util.Date dtDate = null;
		int intWeeks = 0;
		SimpleDateFormat myFormatter = new SimpleDateFormat(strFormat);
		Calendar cal = Calendar.getInstance();

		dtDate = myFormatter.parse(strDays);

		cal.setTime(dtDate);
		intWeeks = cal.get(Calendar.WEEK_OF_MONTH);

		return intWeeks;
	}

	/**
	 * 获得给定月份的天数
	 * 
	 * @param strMonth
	 *            月份
	 * @return 计算后的天数（int）
	 */
	public static int msGetDaysofMonth(String strMonth) {
		int intReturn = 0;
		int intMonth = Integer.valueOf(strMonth.substring(5, 7));
		switch (intMonth) {
		case 1:
			intReturn = daysInMonth[0];
			break;
		case 2:
			intReturn = daysInMonth[1];
			break;
		case 3:
			intReturn = daysInMonth[2];
			break;
		case 4:
			intReturn = daysInMonth[3];
			break;
		case 5:
			intReturn = daysInMonth[4];
			break;
		case 6:
			intReturn = daysInMonth[5];
			break;
		case 7:
			intReturn = daysInMonth[6];
			break;
		case 8:
			intReturn = daysInMonth[7];
			break;
		case 9:
			intReturn = daysInMonth[8];
			break;
		case 10:
			intReturn = daysInMonth[9];
			break;
		case 11:
			intReturn = daysInMonth[10];
			break;
		case 12:
			intReturn = daysInMonth[11];
			break;
		}
		int intYear = Integer.parseInt(strMonth.substring(0, 4));
		if (intYear % 4 == 0 && intYear % 100 != 0 || intYear % 400 == 0) {
			if (intMonth == 2)
				intReturn = 29;

		}
		return intReturn;
	}

	/**
	 * 获取当前日期、时间
	 * 
	 * @return 系统当前的日期/时间（Date）
	 */
	public static java.util.Date msGetCurrentDate() {
		java.util.Date dtDate = new Date();
		return dtDate;
	}

	/**
	 * 返回格式化的当前日期/时间
	 * 
	 * @param strFormat
	 *            格式串
	 * @return 当前日期/时间格式化后的字符串（String）
	 */
	public static String getFormatCurrentDate(String strFormat) {
		return msFormatDateTime(msGetCurrentDate(), strFormat);
	}

	/**
	 * 格式化：
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public static String format(String time, String orgFormat, String format) {
		if (StringUtils.isEmpty(time)) {
			return getCurrentDateTime();
		}
		SimpleDateFormat myFormatter = new SimpleDateFormat(orgFormat);
		Date date = null;
		try {
			date = myFormatter.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formateDate(date, format);
	}

	/**
	 * 取上个工作日
	 * 
	 * @param strFormat
	 *            (yyyy-MM-dd) 日期
	 * @return 上个工作日(yyyy-MM-dd)
	 * @throws ParseException
	 */
	public static String msGetLastWorkDate(String strFormat) throws ParseException {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dtDate = null;
		Calendar cal = Calendar.getInstance();

		dtDate = myFormatter.parse(strFormat);
		cal.setTime(dtDate);
		do {
			cal.add(Calendar.DAY_OF_YEAR, -1);
		} while ((cal.get(Calendar.DAY_OF_WEEK) == 1) | (cal.get(Calendar.DAY_OF_WEEK) == 7));
		return myFormatter.format(cal.getTime());
	}

	/**
	 * 取得当前日期数天前（后）的日期函数
	 * 
	 * @param intStep
	 *            间隔天数
	 * @return 计算后的日期（java.util.Date）
	 */
	public static java.util.Date msCurInterDate(int intStep) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_MONTH, intStep);

		return cal.getTime();
	}

	/**
	 * 两日期的间隔天数
	 * 
	 * @param strDate1
	 *            需要进行计较的日期1(yyyy-MM-dd hh:mm:ss)
	 * @param strDate2
	 *            需要进行计较的日期2(yyyy-MM-dd hh:mm:ss)
	 * @return 间隔秒数（int）
	 * @throws ParseException
	 */
	public static long diffSeconds(String strDateBegin, String strDateEnd) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date dateBegin = sdf.parse(strDateBegin);
		Date dateEnd = sdf.parse(strDateEnd);
		long milliSencods = dateEnd.getTime() - dateBegin.getTime();
		long intDiff = milliSencods / 1000;
		return intDiff;
	}

	/**
	 * 两日期的间隔天数
	 * 
	 * @param strDate1
	 *            需要进行计较的日期1(yyyy-MM-dd)
	 * @param strDate2
	 *            需要进行计较的日期2(yyyy-MM-dd)
	 * @return 间隔秒数（int）
	 * @throws ParseException
	 */
	public static long diffSecond(String strDateBegin, String strDateEnd) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateBegin = sdf.parse(strDateBegin);
		Date dateEnd = sdf.parse(strDateEnd);

		long milliSencods = dateEnd.getTime() - dateBegin.getTime();
		long intDiff = milliSencods / 1000;
		return intDiff;
	}

	/**
	 * @Description：秒间隔
	 * @example:
	 * @author: dimmer
	 * @date: 2015年12月29日 下午4:58:49
	 * @param strDateBegin
	 * @param strDateEnd
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static long diffSecond(String strDateBegin, String strDateEnd, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date dateBegin = sdf.parse(strDateBegin);
		Date dateEnd = sdf.parse(strDateEnd);

		long milliSencods = dateEnd.getTime() - dateBegin.getTime();
		long intDiff = milliSencods / 1000;
		return intDiff;
	}

	/**
	 * 两日期的间隔天数
	 * 
	 * @param strDate1
	 *            需要进行计较的日期1(yyyy年MM月dd日)
	 * @param strDate2
	 *            需要进行计较的日期2(yyyy年MM月dd日)
	 * @return 间隔秒数（int）
	 * @throws ParseException
	 */
	public static long diffSecondZh(String strDateBegin, String strDateEnd) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		Date dateBegin = sdf.parse(strDateBegin);
		Date dateEnd = sdf.parse(strDateEnd);

		long milliSencods = dateEnd.getTime() - dateBegin.getTime();
		long intDiff = milliSencods / 1000;
		return intDiff;
	}

	/**
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDateBegin
	 * @param strDateEnd
	 * @return
	 * @throws ParseException
	 */
	public static long diffMinute(String strDateBegin, String strDateEnd) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateBegin = sdf.parse(strDateBegin);
		Date dateEnd = sdf.parse(strDateEnd);

		long milliSencods = dateEnd.getTime() - dateBegin.getTime();
		long intDiff = milliSencods / 60000;
		return intDiff;
	}

	/**
	 * compate date
	 * 
	 * @param dateBegin
	 * @param dateEnd
	 * @return
	 * @throws ParseException
	 */
	public static Long diffMinute(Date dateBegin, Date dateEnd) throws ParseException {

		long milliSencods = dateEnd.getTime() - dateBegin.getTime();
		long intDiff = milliSencods / 60000;
		return intDiff;
	}

	/**
	 * 两日期的间隔天数
	 * 
	 * @param strDate1
	 *            需要进行计较的日期1(yyyy-MM-dd)
	 * @param strDate2
	 *            需要进行计较的日期2(yyyy-MM-dd)
	 * @return 间隔天数（int）
	 * @throws ParseException
	 */
	public static int diffDay(String strDateBegin, String strDateEnd) throws ParseException {
		long milliSencods = diffSecond(strDateBegin, strDateEnd);
		long intDiff = milliSencods / (60 * 60 * 24);
		return (int) intDiff;
	}

	/**
	 * @Description：天间隔
	 * @example:
	 * @author: dimmer
	 * @date: 2015年12月29日 下午4:59:15
	 * @param strDateBegin
	 * @param strDateEnd
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static int diffDay(String strDateBegin, String strDateEnd, String format) throws ParseException {
		long milliSencods = diffSecond(strDateBegin, strDateEnd, format);
		long intDiff = milliSencods / (60 * 60 * 24);
		return (int) intDiff;
	}

	/**
	 * 获取两个日期相差的月数,按一个月30天算
	 * 
	 * @param date1
	 *            需要进行计较的日期1(yyyy-MM-dd)
	 * @param date2
	 *            需要进行计较的日期2(yyyy-MM-dd)
	 * @return 间隔月数（int）
	 * @throws ParseException
	 */
	public static int diffMonth(String strDateBegin, String strDateEnd) throws ParseException {
		long milliSencods = diffSecond(strDateBegin, strDateEnd);
		long intDiff = milliSencods / (60 * 60 * 24 * 30);
		return (int) intDiff;
	}

	/**
	 * 獲取連個日期相差的年數，一年365天算
	 * 
	 * @param strDate1
	 *            需要进行计较的日期1(yyyy-MM-dd)
	 * @param strDate2
	 *            需要进行计较的日期2(yyyy-MM-dd)
	 * @return 间隔年数（int）
	 * @throws ParseException
	 */
	public static int diffYear(String strDateBegin, String strDateEnd) throws ParseException {
		long milliSencods = diffSecond(strDateBegin, strDateEnd);
		long intDiff = milliSencods / (60 * 60 * 24 * 365);
		return (int) intDiff;
	}

	/**
	 * 获取两个日期中的较大者
	 * 
	 * @param strDate1
	 *            日期
	 * @param strDate2
	 *            日期
	 * @return 返回较大的日期
	 * @throws ParseException
	 */
	public static String msGetMaxTime(String strDate1, String strDate2) throws ParseException {
		int intTemp = msCompareDate(strDate1, strDate2);

		if (intTemp == -2)
			return "日期错误";
		if ((intTemp == 0) || (intTemp == 1)) {
			return strDate1;
		} else {
			return strDate2;
		}
	}

	/**
	 * 取得日期信息
	 * 
	 * @return 当前日期
	 */
	public static String msGetToday() {
		Date curDate = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy年MM月dd日");
		String strDate = formatDate.format(curDate);
		String[] weekdays = new String[] { "", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		strDate = strDate + "&nbsp;&nbsp;" + weekdays[cal.get(Calendar.DAY_OF_WEEK)];

		if (cal.get(Calendar.DAY_OF_WEEK) == 7) {
			strDate = "<font color='#b5ffb5'>" + strDate + "</font>";
		}
		if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
			strDate = "<font color='#ffff80'>" + strDate + "</font>";
		}

		return strDate;
	}

	/**
	 * 取上一天
	 * 
	 * @param strFormat
	 *            (yyyy-MM-dd) 日期
	 * @return 上个工作日(yyyy-MM-dd)
	 * @throws ParseException
	 */
	public static String msGetLastDate(String strFormat) throws ParseException {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dtDate = null;
		Calendar cal = Calendar.getInstance();

		dtDate = myFormatter.parse(strFormat);

		cal.setTime(dtDate);
		cal.add(Calendar.DAY_OF_YEAR, -1);

		return myFormatter.format(cal.getTime());
	}

	/**
	 * 取上个月
	 * 
	 * @param strFormat
	 *            (yyyy-MM-dd) 日期
	 * @return 上个月(yyyy-MM-dd)
	 * @throws ParseException
	 */
	public static String msGetLastMonth(String strFormat) throws ParseException {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dtDate = null;
		Calendar cal = Calendar.getInstance();

		dtDate = myFormatter.parse(strFormat);
		cal.setTime(dtDate);
		cal.add(Calendar.MONTH, -1);

		return myFormatter.format(cal.getTime());
	}

	/**
	 * 取得给定日期数月前后的日期函数
	 * 
	 * @param strDate
	 *            需要进行加减的日期("yyyy-MM-dd")
	 * @param intMon
	 *            需要计算的间隔天数
	 * @return 返回计算后的日期（String）
	 * @throws ParseException
	 */
	public static String msDateAfterMonth(String strDate, int intMon) throws ParseException {
		String strFormat = "yyyy-MM-dd";
		java.util.Date dtDate = null;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat myFormatter = new SimpleDateFormat(strFormat);
		myFormatter.setLenient(false);
		dtDate = myFormatter.parse(strDate);
		cal.setTime(dtDate);
		cal.add(Calendar.MONTH, intMon);

		return msFormatDateTime(cal.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 获得昨天
	 * 
	 * @return
	 */
	public static Date getYesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	/**
	 * 日期格式化
	 * 
	 * @param pattern
	 * @param date
	 * @return
	 */
	public static String getParseDate(String pattern, Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.applyPattern(pattern);
		return sdf.format(date);
	}

	/**
	 * 转换为日期
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date toDate(String dateStr, String format) {
		Date date = new Date();
		if (StringUtils.isEmpty(dateStr)) {
			return date;
		}
		SimpleDateFormat formater = new SimpleDateFormat(format);
		try {
			date = formater.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static String formTimeString(String[] arr) {
		StringBuffer buf = new StringBuffer();
		if (1 == arr[2].length()) {
			buf.append("0");
			buf.append(arr[2]);
		} else {
			buf.append(arr[2]);
		}
		buf.append(":");
		if (1 == arr[1].length()) {
			buf.append("0");
			buf.append(arr[1]);
		} else {
			buf.append(arr[1]);
		}
		buf.append(":");
		if (1 == arr[0].length()) {
			buf.append("0");
			buf.append(arr[0]);
		} else {
			buf.append(arr[0]);
		}
		return buf.toString();
	}

	/**
	 * 
	 * @param date
	 * @param formatStyle
	 * @return 格式化后的日期字符串
	 */
	public static String toString(Date date, String formatStyle) {
		SimpleDateFormat simple = new SimpleDateFormat(formatStyle);
		return simple.format(date);
	}

	public static String getTimeZone(Calendar cal, int day) {
		String date = "";
		try {
			if (cal.get(Calendar.MONTH) < 11) {
				if (cal.get(Calendar.DATE) + day > cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
					date = cal.get(Calendar.YEAR) + "年" + (formatNum(cal.get(Calendar.MONTH) + 2)) + "月01日";
				} else {
					date = cal.get(Calendar.YEAR) + "年" + (formatNum(cal.get(Calendar.MONTH) + 1)) + "月"
							+ (formatNum(cal.get(Calendar.DATE) + day)) + "日";
				}
			} else {
				if (cal.get(Calendar.DATE) >= cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
					date = (cal.get(Calendar.YEAR) + 1) + "年01月01日";
				} else {
					date = cal.get(Calendar.YEAR) + "年" + (formatNum(cal.get(Calendar.MONTH) + 1)) + "月"
							+ (formatNum(cal.get(Calendar.DATE) + day)) + "日";
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return date;
	}

	public static Date getTZ(Calendar cal, int day) {
		Date d = new Date();
		SimpleDateFormat sdfTemp = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		try {
			d = sdfTemp.parse(getTimeZone(cal, day) + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static String formateDate(Date d) {
		SimpleDateFormat sdfTemp = new SimpleDateFormat("yyyy年MM月dd日");
		return sdfTemp.format(d);
	}

	public static String formatNum(int num) {
		if (num < 10)
			return "0" + num;
		return Integer.valueOf(num).toString();
	}

	/**
	 * 去掉时间的时分秒 生成合同计划表时用
	 * 
	 * @param date
	 * @return
	 */
	public static Date getTimeofDate(Date date) {
		SimpleDateFormat my = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = my.format(date);
		String[] strs = str.split(" ");
		String start = strs[0] + " 00:00:00";
		Date startTime = null;
		try {
			startTime = my.parse(start);
		} catch (ParseException e) {
		}
		return startTime;
	}

	public static List<String> getPayDate(String strDate, int loanTerm) {
		List<String> list = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 1; i <= loanTerm; i++) {
			Calendar calendar = Calendar.getInstance();
			Date date2 = null;
			try {
				date2 = sdf.parse(strDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			calendar.setTimeInMillis(date2.getTime());
			calendar.add(Calendar.MONTH, i);
			Date date = calendar.getTime();
			list.add(sdf.format(date));

		}
		return list;
	}

	/**
	 * 获取系统当前日期和时间，格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @return 返回计算后的日期时间（String）
	 */
	public static String getCurrentDateTime() {
		return DateUtil.getFormatCurrentDate("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @Description：获得当前时间戳
	 * @author: dimmer
	 * @date: 2015年10月31日 下午3:57:47
	 * @return
	 */
	public static Long getCurrentTimeSecond() {
		return System.currentTimeMillis();
	}

	/**
	 * 获取当前带年月日汉字的时间
	 * 
	 * @Description:
	 * @param
	 * @return String yyyy年MM月dd日 HH:mm:ss
	 * @throws @author
	 *             Egg
	 * @date 2015-4-20 下午4:22:42
	 * @version V1.00
	 */
	public static String getCurrentDateTimeZh() {
		return DateUtil.getFormatCurrentDate("yyyy年MM月dd日 HH:mm:ss");
	}

	/**
	 * 获取系统当前时间，格式为HH:mm:ss
	 * 
	 * @return 返回计算后的时间（String）
	 */
	public static String getCurrentTime() {
		return DateUtil.getFormatCurrentDate("HH:mm:ss");
	}

	/**
	 * 获得系统时间，格式自定义
	 * 
	 * @param formatter
	 * @return
	 */
	public static String getCurrentTime(String formatter) {
		return DateUtil.getFormatCurrentDate(formatter);
	}

	/**
	 * 获取系统当前日期，格式为yyyy-MM-dd
	 * 
	 * @return 返回计算后的日期（String）
	 */
	public static String getCurrentDate() {
		return DateUtil.getFormatCurrentDate("yyyy-MM-dd");
	}

	/**
	 * @Description：获得当前时间or日期
	 * @example:
	 * @author: dimmer
	 * @date: 2015年12月29日 下午4:59:46
	 * @param format
	 * @return
	 */
	public static String getCurrentDate(String format) {
		return DateUtil.getFormatCurrentDate(format);
	}

	/**
	 * 获取系统当前日期，格式为yyyy年MM月dd日
	 * 
	 * @return 返回计算后的日期（String）
	 */
	public static String getCurrentDateStr() {
		return DateUtil.getFormatCurrentDate("yyyy年MM月dd日");
	}

	/**
	 * @Description：yyyyMMdd
	 * @example:
	 * @author: dimmer
	 * @date: 2015年11月13日 上午10:37:27
	 * @return
	 */
	public static String getCurrentDateSplit() {
		return DateUtil.getFormatCurrentDate("yyyyMMdd");
	}

	public static String getMonthDayStr(Date date) {
		SimpleDateFormat sf = new SimpleDateFormat("MM月dd日");
		return sf.format(date);
	}

	public static String getYearMonthDayStr(Date date) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
		return sf.format(date);
	}

	public static String formateDate(Date d, String format) {
		SimpleDateFormat sdfTemp = new SimpleDateFormat(format);
		return sdfTemp.format(d);
	}

	// -------------

	/**
	 * 获取系统当前时间，格式为：yyyy-MM-dd HH:mm:ss SSS
	 * 
	 * @return 返回计算后的日期时间（String）
	 */
	public static String getCurrentDateTimeSSS() {
		return DateUtil.getFormatCurrentDate("yyyy-MM-dd HH:mm:ss:SSS");
	}

	public static Date parseDateStr(String dtStr) throws ParseException {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dt = sf.parse(dtStr);
		return dt;
	}

	/*
	 * public static Date addDays(int days) { Date date = new Date(); Calendar
	 * calendar = new GregorianCalendar(); calendar.setTime(date);
	 * calendar.add(calendar.DAY_OF_MONTH, days); date = calendar.getTime();
	 * return date; }
	 * 
	 * public static Date addDays(Date date, int days) { Calendar calendar = new
	 * GregorianCalendar(); calendar.setTime(date);
	 * calendar.add(calendar.DAY_OF_MONTH, days); date = calendar.getTime();
	 * return date; }
	 * 
	 * 
	 * 
	 * public static Date addMonths(int month) { Calendar c =
	 * Calendar.getInstance(); c.add(Calendar.MONTH, month); Date date =
	 * c.getTime(); return date; }
	 * 
	 * 
	 * 
	 * public static String addMonthsFmt(Date date, int month) { Calendar c =
	 * Calendar.getInstance(); c.setTime(date); c.add(Calendar.MONTH, month);
	 * date = c.getTime(); return msFormatDateTime(date, ""); }
	 */

	// public static int getMonth(String dtStr) throws ParseException {
	// SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// Date dt = sf.parse(dtStr);
	// return dt.getMonth();
	// }

	/**
	 * 获取下几月
	 */
	// public static int getNextMonth(Date date, int diffMonth) {
	// Calendar calendar = Calendar.getInstance();
	// calendar.setTime(date);
	// calendar.add(Calendar.MONTH, diffMonth);
	// int month = calendar.get(Calendar.MONTH) + 1;
	// calendar = null;
	// return month;
	// }

	public static Date addMonths(Date date, int month) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, month);
		date = c.getTime();
		return date;
	}

	public static String addDaysFmt(Date date, int days) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DAY_OF_MONTH, days);
		date = calendar.getTime();
		return msFormatDateTime(date, "yyyy-MM-dd");
	}

	/**
	 * @Description：增加分钟
	 * @example:
	 * @author: dimmer
	 * @date: 2016年2月18日 上午11:18:44
	 * @param date
	 * @param minutes
	 * @return
	 */
	public static Date addMinute(Date date, int minutes) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.MINUTE, minutes);
		date = calendar.getTime();
		return date;
	}

	/**
	 * @Description：增加秒
	 * @example:
	 * @author: dimmer
	 * @date: 2016年2月18日 上午11:18:58
	 * @param date
	 * @param seconds
	 * @return
	 */
	public static Date addSecond(Date date, int seconds) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.SECOND, seconds);
		date = calendar.getTime();
		return date;
	}

	/**
	 * @Description：增加小时
	 * @example:
	 * @author: dimmer
	 * @date: 2016年2月18日 上午11:20:19
	 * @param date
	 * @param hours
	 * @return
	 */
	public static Date addHour(Date date, int hours) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.HOUR, hours);
		date = calendar.getTime();
		return date;
	}

	public static String addDateFmt(Date date, int days) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DAY_OF_MONTH, days);
		date = calendar.getTime();
		return msFormatDateTime(date, "yyyy年MM月dd日");
	}

	/**
	 * @Description：增加天数
	 * @example:
	 * @author: dimmer
	 * @date: 2015年12月29日 下午4:57:17
	 * @param dateStr
	 * @param days
	 * @return
	 */
	public static String addDay(String dateStr, int days, String format) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(stringToDate(dateStr, format));
		calendar.add(Calendar.DATE, days);
		Date date = calendar.getTime();
		return msFormatDateTime(date, "yyyyMMdd");
	}

	public static void main(String[] args) throws ParseException {
		// System.out.println(new Date("").getSeconds());
		System.out.println(DateUtil.getCurrentHour());
		System.out.println(10 > DateUtil.getCurrentHour());
	}

	/**
	 * @Description：字符串转换为日期
	 * @example:
	 * @author: dimmer
	 * @date: 2015年12月29日 下午4:56:15
	 * @param dateStr
	 * @param formatStr
	 * @return
	 */
	public static Date stringToDate(String dateStr, String formatStr) {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取下几月
	 */
	public static String getNextMonthDay(Date date, int diffMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, diffMonth);

		String monthDayStr = "";
		// int day = calendar.get(Calendar.DAY_OF_MONTH);
		// int month = calendar.get(Calendar.MONTH) + 1;
		// monthDayStr = String.format("%s.%s", month, day);

		SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
		monthDayStr = sf.format(calendar.getTime());
		calendar = null;
		return monthDayStr;
	}

	public static int getCurDayFromStr(String dateStr) {
		int day = 0;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dtDate;
		try {
			dtDate = sf.parse(dateStr);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dtDate);
			day = calendar.get(Calendar.DAY_OF_MONTH);
			calendar = null;
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		return day;
	}

	public static String getCurMonthAndDay(Date date) {
		String monthDayStr = "";
		// Calendar calendar = Calendar.getInstance();
		// calendar.setTime(date);
		// int day = calendar.get(Calendar.DAY_OF_MONTH);
		// int month = calendar.get(Calendar.MONTH) + 1;
		// monthDayStr = String.format("%s.%s", month, day);
		// calendar = null;

		SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
		monthDayStr = sf.format(date);
		return monthDayStr;
	}

	public static String getLastMonthAndDay(Date date, int diffDay) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -diffDay);

		String monthDayStr = "";
		// int day = calendar.get(Calendar.DAY_OF_MONTH);
		// int month = calendar.get(Calendar.MONTH) + 1;
		// monthDayStr = String.format("%s.%s", month, day);

		SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
		monthDayStr = sf.format(calendar.getTime());
		calendar = null;
		return monthDayStr;
	}

	public static String getNextMonthAndDay(Date date, int diffDay) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, diffDay);

		String monthDayStr = "";
		// int day = calendar.get(Calendar.DAY_OF_MONTH);
		// int month = calendar.get(Calendar.MONTH) + 1;
		// monthDayStr = String.format("%s.%s", month, day);

		SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
		monthDayStr = sf.format(calendar.getTime());
		calendar = null;
		return monthDayStr;
	}

	public static int getCurDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// date = calendar.getTime();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar = null;
		return day;
	}

	public static String getCurrentDateTimeForFile() {
		return DateUtil.getFormatCurrentDate("yyyyMMddHHmmss");
	}

	public static String getCurrentMinute() {
		String dat = getCurrentDate("MM");
		return dat;
	}

	/**
	 * 两时间戳的间隔天数
	 * 
	 * @param beginTime
	 *            起始时间戳
	 * @param endTime
	 *            截止时间戳
	 * @return 返回相隔天数（int）
	 * @throws ParseException
	 */
	public static int diffDayWithStampTime(long beginTime, long endTime) throws ParseException {
		long intDiffDays = (long) ((endTime - beginTime) / (1000 * 60 * 60 * 24) + 0.5);
		return (int) intDiffDays;
	}

	/**
	 * @Description：获得当前小时
	 * @example:
	 * @author: dimmer
	 * @date: 2016年2月11日 下午4:16:41
	 * @return
	 */
	public static Integer getCurrentHour() {
		String dat = getCurrentDate("HH");
		return Integer.parseInt(dat);
	}

	/**
	 * 根据传入的时间得到当天 00:00:00 000的毫秒值 @Description：
	 * 
	 * @author:
	 * @date: 2015-5-12 下午4:37:29
	 * @param time
	 * @return
	 */
	public static String longToDateStr(Long time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(time);
		return sdf.format(date);
	}

	/**
	 * 根据传入的时间得到当天 00:00:00 000的毫秒值 @Description：
	 * 
	 * @author:
	 * @date: 2015-5-12 下午4:37:29
	 * @param time
	 * @return
	 */
	public static long strToLong(String datestr, String format) {
		try {
			if (StringUtils.isNotBlank(datestr)) {
				if (StringUtils.isBlank(format)) {
					format = "yyyy-MM-dd HH:mm:ss";
				}
				SimpleDateFormat sf = new SimpleDateFormat(format);
				return sf.parse(datestr).getTime();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0l;
	}

	/**
	 * 获取本周 开始和结束时间 @Description：
	 * 
	 * @author: 何帅斌
	 * @date: 2016年5月23日 下午4:46:46
	 * @return
	 */
	public static Map<String, String> getWeekDay() {
		Map<String, String> map = new HashMap<String, String>();
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		map.put("start", df.format(cal.getTime()));
		// System.out.println("********得到本周一的日期*******"+df.format(cal.getTime()));
		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		map.put("end", df2.format(cal.getTime()));
		// System.out.println("********得到本周天的日期*******"+df2.format(cal.getTime()));
		return map;
	}

	/**
	 * 获取本月 开始和结束时间 @Description：
	 * 
	 * @author: 何帅斌
	 * @date: 2016年5月23日 下午4:50:23
	 * @return
	 */
	public static Map<String, String> getMonthDate() {
		Map<String, String> map = new HashMap<String, String>();
		// 获取Calendar
		Calendar calendar = Calendar.getInstance();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		// 设置时间,当前时间不用设置
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		map.put("start", format.format(calendar.getTime()));
		// System.out.println("*********得到本月的最小日期**********"+format.format(calendar.getTime()));
		// 设置日期为本月最大日期
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		map.put("end", format2.format(calendar.getTime()));
		// System.out.println("*********得到本月的最大日期**********"+format2.format(calendar.getTime()));
		return map;
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = stringToDate(sdate, "yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}
	// public static int getLastDay(Date date, int diffDay) throws
	// ParseException {
	// Calendar calendar = Calendar.getInstance();
	// calendar.setTime(date);
	// calendar.add(Calendar.DAY_OF_MONTH, -diffDay);
	// int day = calendar.get(Calendar.DAY_OF_MONTH);
	// calendar = null;
	// return day;
	// }
	//
	// public static int getNextDay(Date date, int diffDay) throws
	// ParseException {
	// Calendar calendar = Calendar.getInstance();
	// calendar.setTime(date);
	// calendar.add(Calendar.DAY_OF_MONTH, diffDay);
	// int day = calendar.get(Calendar.DAY_OF_MONTH);
	// calendar = null;
	// return day;
	// }

	/*
	 * public static void main(String[] args) { try { int a =
	 * diffDay("2000-01-01","2000-12-31"); int b =
	 * diffMonth("2014-11-30","2014-12-31"); int c =
	 * diffYear("2012-12-12","2014-12-12"); } catch ( ParseException e ) {
	 * e.printStackTrace(); } }
	 */
}