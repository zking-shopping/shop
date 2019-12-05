package com.shopping.test;

import org.junit.Test;

import com.shopping.util.DateHelper;
import com.shopping.util.EncryptionHelper;
import com.shopping.util.OrderNumberHelper;
import com.shopping.util.TimeHelper;
import com.shopping.util.UUIDHelper;

public class UtilTest {
	
	@Test
	public void TestDateHelper(){
		System.out.println(DateHelper.getSimpleDate());
		System.out.println(DateHelper.getComplexDate());
	}
	
	@Test
	public void TestEncryption(){
		System.out.println(EncryptionHelper.encryption("123"));
	}
	
	@Test
	public void TestUUID(){
		System.out.println(UUIDHelper.getUUID());
	}
	
	@Test
	public void TestTimeHelper(){
		long start = TimeHelper.getTime();
		System.out.println(start);
		try {
			Thread.sleep(4566);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = TimeHelper.getTime();
		System.out.println(end);
		int time = TimeHelper.duration(start, end);
		System.out.println(time);
	}
	
	@Test
	public void testOrderNumberHelper(){
		System.out.println(OrderNumberHelper.getOrderNumber());
	}
}
