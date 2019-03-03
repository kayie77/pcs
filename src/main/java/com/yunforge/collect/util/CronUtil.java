package com.yunforge.collect.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class CronUtil {

	/**
	 * 
	 * @param date 时间
	 * @param cronExpression cron表达式
	 * @return 符合这个时间段返回true，否则返回false
	 * @author heliteng
	 * 2015-12-16
	 */
	public static boolean checkTime(Date date, String cronExpression) {
		CronExpression exp = null;
		try {
			// 初始化cron表达式解析器
			exp = new CronExpression(cronExpression);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return exp.isSatisfiedBy(date);
	}
	
	@Test
	public void dayTest() throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=sdf.parse("2015-12-12 14:02:30");
		String cronExpression="* * 11-18 ? * *";//每天11点到18点
		System.out.println(CronUtil.checkTime(date, cronExpression));
	}
	
	@Test
	public void weekTest() throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=sdf.parse("2015-12-18 14:02:30");
		String cronExpression="* * * ? *  6,7,1";//每周五、六、日
		System.out.println(CronUtil.checkTime(date, cronExpression));
	}
	
	@Test
	public void monthTest() throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=sdf.parse("2015-12-10 14:02:30");
		String cronExpression="* * * 1-10 * ?";//每月上旬填报上个月的数据
		System.out.println(CronUtil.checkTime(date, cronExpression));
	}
	
	@Test
	public void quartzTest() throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=sdf.parse("2016-1-10 14:02:30");
		String cronExpression="* * * ? 1,4,7,10 *";//每季度的第一个月填报上个季度的数据
		System.out.println(CronUtil.checkTime(date, cronExpression));
	}
	@Test
	public void yearTest() throws ParseException{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=sdf.parse("2016-2-10 14:02:30");
		String cronExpression="* * * ? 1,2 *";//每年 1~2 月份填报上年的数据
		System.out.println(CronUtil.checkTime(date, cronExpression));
	}
	
}
