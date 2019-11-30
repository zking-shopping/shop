package com.shopping.util;

import java.util.Random;

public class OrderNumberHelper {
	public static String getOrderNumber(){
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 14; i++) {
			int res = r.nextInt(10);
			sb.append(res);
		}
		return sb.toString();
	}
}
