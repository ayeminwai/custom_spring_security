package com.wirecard.sg.sample.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getCurrentDateTimeByString() {
		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()).toString();
	}

	public static Date getCurrentDateTimeByDate(String date) throws ParseException {
		return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(date);
	}
	
	public static String getCurrentDateByString() {
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	
	public static String getCurrentTimeByString() {
		return new SimpleDateFormat("HHmmss").format(new Date());
	}
}
