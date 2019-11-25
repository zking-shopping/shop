package com.shopping.util;

import java.util.Calendar;
import java.util.Date;

public class DateHelper {
	public static String getSimpleDate(){
		Date date = new Date();
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		return String.valueOf(year + "" + month);
	}
	public static String getComplexDate(){
		Date date = new Date();
		int year = date.getYear() + 1900;
		int month = date.getMonth() + 1;
		return String.valueOf(year + "-" + month);
	}
}
