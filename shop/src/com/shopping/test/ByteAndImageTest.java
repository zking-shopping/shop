package com.shopping.test;

import static org.junit.Assert.*;

import javax.swing.ImageIcon;

import org.junit.Test;

import com.mchange.util.AssertException;
import com.shopping.util.ByteAndImage;

public class ByteAndImageTest {
	@Test
	public void ImageToByteTsest(){
		byte[] b = ByteAndImage.ImageToByte("C:/Users/gyh/Desktop/xh.png");
		System.out.println(b);
	}
}
