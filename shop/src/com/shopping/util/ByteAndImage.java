package com.shopping.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ByteAndImage {
	public static byte[] ImageToByte(String path){
		BufferedImage img;
		ByteArrayOutputStream bos = null;
		try {
			img = ImageIO.read(new File(path));
			if(img != null){
				bos = new ByteArrayOutputStream();
				ImageIO.write(img, "jpg", bos);
				return bos.toByteArray();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static ImageIcon ByteToImage(byte[] b){
		return new ImageIcon(b);
	}
}
