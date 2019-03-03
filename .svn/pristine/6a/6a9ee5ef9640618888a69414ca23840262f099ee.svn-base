package com.yunforge.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {
	public static final String defaultPattern = "yyyy-MM-dd";
	public static final String hour12HMSPattern = "yyyy-MM-dd hh:mm:ss";
	public static final String hour12HMPattern = "yyyy-MM-dd hh:mm";
	public static final String hour12HPattern = "yyyy-MM-dd hh";
	public static final String hour24HMSPattern = "yyyy-MM-dd HH:mm:ss";
	public static final String hour24HMPattern = "yyyy-MM-dd HH:mm";
	public static final String hour24HPattern = "yyyy-MM-dd HH";
	public static String timePattern = "HH:mm";

	private static Log log = LogFactory.getLog(DateUtil.class);
	private static String defaultDatePattern = null;

	public static int getYear(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(1);
	}

	public static int getMonth(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return (cal.get(2) + 1);
	}

	public static int getDay(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(7);
	}
	
	public static int getWeek(java.util.Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	public static java.util.Date newDate(int year, int month, int date) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date);
		return cal.getTime();
	}

	public static java.util.Date newDate(int year, int month, int date,
			int hour, int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date, hour, minute, second);
		return cal.getTime();
	}

	public static java.util.Date parse(String dateStr) throws ParseException {
		return DateFormat.getInstance().parse(dateStr);
	}

	public static java.sql.Date newSqlDate(int year, int month, int date) {
		return new java.sql.Date(newDate(year, month, date).getTime());
	}

	public static Timestamp newSqlTimestamp(int year, int month, int date,
			int hour, int minute, int second) {
		return new Timestamp(newDate(year, month, date, hour, minute, second)
				.getTime());
	}

	public static synchronized String getDatePattern() {
		defaultDatePattern = "yyyy-MM-dd";
		return defaultDatePattern;
	}

	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	public static final Date convertStringToDate(String aMask, String strDate)
			throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}
		return (date);
	}

	public static String getTimeNow(Date theTime) {
		return getDateTime(timePattern, theTime);
	}

	public static Calendar getToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	public static final String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	public static Date convertStringToDate(String strDate)
			throws ParseException {
		Date aDate = null;

		try {
			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (ParseException pe) {
			log.error("Could not convert '" + strDate
					+ "' to a date, throwing exception");
			pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());

		}

		return aDate;
	}

	public static String getDateAndTime(String fomat) {
		SimpleDateFormat dformat = new SimpleDateFormat(fomat);
		return dformat.format(new Date());
	}

	public static String getDateAndTime(Date date, String fomat) {
		SimpleDateFormat dformat = new SimpleDateFormat(fomat);
		return dformat.format(date);
	}

	public static String getDate() {
		return getDateAndTime("yyyy-MM-dd");
	}

	public static String formatDate(Date pidate, String strFormat) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(strFormat);
		String str = simpledateformat.format(pidate);
		return str;
	}

	public static Date strToDate(String str, String strFormat) {
		Date reDate = null;
		SimpleDateFormat simpledateformat = new SimpleDateFormat(strFormat);
		try {
			reDate = simpledateformat.parse(str);
		} catch (Exception exception) {
		}
		return reDate;
	}
	
	public static Date getFormatDate(Date date, String fomat) {
		SimpleDateFormat dformat = new SimpleDateFormat(fomat);
		return strToDate(dformat.format(date),fomat);
	}
	public static Date addDay(Date date, long days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(5, (int) days);
		Date date1 = calendar.getTime();
		return date1;
	}

	public static Date addMinute(Date pidate, Long minutes) {
		Long adate = pidate.getTime() + minutes * 60L * 1000L;
		return new Date(adate);
	}

	public static long dateDiff(Date pidate1, Date pidate2) {
		return pidate1.getTime() - pidate2.getTime();
	}

	public static long dateDiff(String style, Date fromdate, Date todate) {
		byte byte1 = 0;
		int i = 1;
		Date date2;
		Date date3;
		if (fromdate.getTime() > todate.getTime()) {
			i = -1;
			date2 = todate;
			date3 = fromdate;
		} else {
			date2 = fromdate;
			date3 = todate;
		}
		byte byte0;
		if (style.equals("yyyy")) {
			byte0 = 1;
		} else if (style.equals("m")) {
			byte0 = 2;
		} else if (style.equals("d")) {
			byte0 = 5;
		} else if (style.equals("y")) {
			byte0 = 5;
		} else if (style.equals("w")) {
			byte0 = 4;
		} else if (style.equals("ww")) {
			byte0 = 3;
		} else if (style.equals("h")) {
			byte0 = 5;
			byte1 = 11;
		} else if (style.equals("n")) {
			byte0 = 5;
			byte1 = 12;
		} else if (style.equals("s")) {
			byte0 = 5;
			byte1 = 13;
		} else {
			return -1L;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date2);
		long l = 0L;
		calendar.add(byte0, 1);
		for (Date date4 = calendar.getTime(); date4.getTime() <= date3
				.getTime();) {
			calendar.add(byte0, 1);
			date4 = calendar.getTime();
			l++;
		}
		if ((byte1 == 11) || (byte1 == 12) || (byte1 == 13)) {
			calendar.setTime(date2);
			calendar.add(byte0, (int) l);
			@SuppressWarnings("unused")
			Date date5 = calendar.getTime();
			switch (byte1) {
			case 11: // '\013'
				l *= 24L;
				break;
			case 12: // '\f'
				l = l * 24L * 60L;
				break;
			case 13: // '\r'
				l = l * 24L * 60L * 60L;
				break;
			}
			calendar.add(byte1, 1);
			for (Date date6 = calendar.getTime(); date6.getTime() <= date3
					.getTime();) {
				calendar.add(byte1, 1);
				date6 = calendar.getTime();
				l++;
			}
		}
		return l * i;
	}

	public static int getWeekDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(7);
	}

	public static Date getNowWeekBegin() {
		Map<String, String> map = new HashMap<String, String>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
		map.put("mon", df.format(cal.getTime()));
		return cal.getTime();
	}

	public static Date getNowWeekEnd() {
		Map<String, String> map = new HashMap<String, String>();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

		// 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		// 增加一个星期，才是我们中国人理解的本周日的日期
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		map.put("sun", df.format(cal.getTime()));
		return cal.getTime();

	}

	public static Date getMonthStartDate() {
		Map<String, String> map = new HashMap<String, String>();
		// 获取Calendar
		Calendar calendar = Calendar.getInstance();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		// 设置时间,当前时间不用设置
		// calendar.setTime(new Date());
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		map.put("monthF", format.format(calendar.getTime()));
		return calendar.getTime();
	}

	public static Date getMonthEndDate() {
		Map<String, String> map = new HashMap<String, String>();
		// 获取Calendar
		Calendar calendar = Calendar.getInstance();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		// 设置时间,当前时间不用设置
		// calendar.setTime(new Date());
		// 设置日期为本月最大日期
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		// 打印
		map.put("monthL", format.format(calendar.getTime()));
		return calendar.getTime();
	}
}
