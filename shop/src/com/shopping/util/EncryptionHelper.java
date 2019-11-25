package com.shopping.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionHelper {
	public static String encryption(String password){
		StringBuffer sb = null;
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(password.getBytes());
			byte[] b = md.digest();
			sb = new StringBuffer();
			for (byte c : b) {
				sb.append(Integer.toHexString(c));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
