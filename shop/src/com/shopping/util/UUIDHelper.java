package com.shopping.util;

import java.util.UUID;

public class UUIDHelper {
	public static String getUUID(){
		String id = UUID.randomUUID().toString();
		return id;
	}
}
