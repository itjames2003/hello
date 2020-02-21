package com.js.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	//日期转字符串
	public static String date2String(Date da,String patt){
		SimpleDateFormat sm=new SimpleDateFormat(patt);
		String s = sm.format(da);
		return s;
	}
	//字符串转日期
	public static Date string2Date(String str,String patt) throws ParseException {
		SimpleDateFormat sm=new SimpleDateFormat(patt);
		Date date = sm.parse(str);
		return date;
	}
}
