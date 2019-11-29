package com.shopping.web.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageCodeAction extends ActionFather{
	//验证码包含的所有字符
	private String[] str = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			"X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
	//随机生成器
	private Random r = new Random();
	//初始化验证码图片的宽高
	private static int WIDTH = 115;
	private static int HEIGHT = 30;
	
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		//用来存验证码
		StringBuffer sb = new StringBuffer();
		//用来存加了空格的验证码
		StringBuffer sbs = new StringBuffer();
		//生成7位验证码
		for (int i = 0; i < 5; i++) {
			int index = r.nextInt(str.length);
			sb.append(str[index]);
			sbs.append(str[index]+" ");
		}
		//获得验证码字符串
		String code = sb.toString();
		//将验证码存进session
		request.getSession().setAttribute("code", code);
		
		//内存中一个缓存的图片对象
		BufferedImage bi = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		//获得图片的画笔
		Graphics g = bi.getGraphics();
		//画个图形用来显示验证码
		g.drawRect(0, 0, WIDTH, HEIGHT);
		//设置画笔颜色,并填充背景
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//设置画笔颜色,并填充验证码
		g.setColor(Color.RED);
		g.setFont(new Font("宋体", Font.BOLD, 20));
		g.drawString(sbs.toString(), 10, 22);
		//画干扰点
		g.setColor(Color.black);
		for (int i = 0; i < 400; i++) {
			int x = r.nextInt(WIDTH);
			int y = r.nextInt(HEIGHT);
			g.drawLine(x, y, x, y);
		}
		//画干扰线,随机生成
		g.setColor(Color.MAGENTA);
		for (int i = 0; i < 3; i++) {
			int x1 = r.nextInt(10)+95;
			int y1 = r.nextInt(30);
			g.drawLine(10, i*8+10, x1, y1);
		}
		
		//获得servlet输出流
		ServletOutputStream sos = response.getOutputStream();
		//将内存中生产的图片写入输出流中
		ImageIO.write(bi, "jpg", sos);
		sos.flush();
		sos.close();
		return null;
	}

}
