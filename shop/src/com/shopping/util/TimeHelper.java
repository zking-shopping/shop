package com.shopping.util;

import java.util.Calendar;

public class TimeHelper {
	public static long getTime(){
		Calendar c = Calendar.getInstance();
		long l = c.getTimeInMillis();
		return l;
	}
	
	public static String duration(long start, long end){
		long time = end - start;
		time = time / 1000;
		String t = String.valueOf(time);
		return t;
	}
}
