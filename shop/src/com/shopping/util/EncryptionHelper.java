package com.shopping.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.shopping.pojo.Address;

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
	
	public static void encryptMobileNumber(List<Address> list){
		if(list.size()!=0){
			Iterator<Address> iterator = list.iterator();
			//手机号码加密
			while (iterator.hasNext()) {
				Address add = iterator.next();
				String number = add.getPhoneNumber();
				number = number.substring(0, 3)+"****"+number.substring(7);
				add.setPhoneNumber(number);
			}
		}
		
	}
}
