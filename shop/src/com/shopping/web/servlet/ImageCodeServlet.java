package com.shopping.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.util.FormulaHelper;

public class ImageCodeServlet extends HttpServlet {
	//验证码包含的所有字符
	private String[] str = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
			"X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
	//随机生成器
	private Random r = new Random();
	//初始化验证码图片的宽高
	private static int WIDTH = 206;
	private static int HEIGHT = 46;
	
	public ImageCodeServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getSession().getAttribute("imageCode")!=null){
			request.getSession().removeAttribute("imageCode");
		}
		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
  		
		//生成一个算式和结果
  		String[] result = FormulaHelper.getAddSubMulFormula(3,0,9);
  		System.out.println("result[1]："+result[1]);
  		//将结果存进session
		request.getSession().setAttribute("imageCode", result[1]);
		
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
		g.setFont(new Font("宋体", Font.BOLD, 30));
		g.drawString(result[0], 20, 34);
		//画干扰点
		g.setColor(Color.black);
		for (int i = 0; i < 1200; i++) {
			int x = r.nextInt(WIDTH);
			int y = r.nextInt(HEIGHT);
			g.drawLine(x, y, x, y);
		}
		//画干扰线,随机生成
		g.setColor(Color.MAGENTA);
		for (int i = 0; i < 3; i++) {
			int x1 = r.nextInt(166)+20;
			int y1 = r.nextInt(28)+8;
			g.drawLine(20, i*15+10, x1, y1);
		}
		
		//获得servlet输出流
		ServletOutputStream sos = response.getOutputStream();
		//将内存中生产的图片写入输出流中
		ImageIO.write(bi, "jpg", sos);
		sos.flush();
		sos.close();
	}
	
	public void init() throws ServletException {
		
	}

}
