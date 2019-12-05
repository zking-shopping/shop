package com.shopping.web.servlet;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopping.dao.GoodsStatisticsDao;
import com.shopping.dao.TypeDao;
import com.shopping.dao.daoImpl.GoodsStatisticsImpl;
import com.shopping.dao.daoImpl.TypeDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Type;
import com.shopping.util.DateHelper;

public class DataGraphServlet extends HttpServlet {
	
//	static int sort;
	/**
	 * Constructor of the object.
	 */
	public DataGraphServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = DBHelper.getConnection();
		String data = request.getParameter("type");
		int sort = 0;
		if(data == null){
			sort = 0;
		}else{
			sort = Integer.valueOf(data);
		}
		GoodsStatisticsDao goodsStatisticsDao = new GoodsStatisticsImpl();
		TypeDao typeDao = new TypeDaoImpl();
		List<Object> list = typeDao.selectAll(new Type(), conn);
		Type t = (Type) list.get((sort % list.size()));
		String type = String.valueOf(t.getId());
		
		final int WIDTH = 800;
		final int HEIGHT = 300;
		BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = bi.getGraphics();
		Graphics2D g2= (Graphics2D)g;
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, WIDTH, HEIGHT);
		//
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("行楷", Font.BOLD, 30));
		g2.setStroke(new BasicStroke(2.0F,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2.drawString(t.getName(), 400, 30);
		//画坐标轴
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke());
		//X轴
		g2.drawLine(10, HEIGHT-10, WIDTH-10, HEIGHT-10);
		//Y轴
		g2.drawLine(10, 10, 10, HEIGHT-10);
		//使用虚线画行距线与纵线
		g2.setColor(Color.GRAY);
		g2.setStroke(new BasicStroke(1.0F,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL, 0, new float[]{16, 4}, 0));
		g2.setFont(new Font("宋体", Font.BOLD, 18));
		int mostClick = 60000;
		for (int i = 50; i < 300; i=i+60) {
			g2.drawLine(10, i, WIDTH-10, i);
			g2.drawString(String.valueOf(mostClick), 0, i);
			mostClick -= 15000;
		}
		int mostTime = Integer.valueOf(DateHelper.getSimpleMonth());
		for (int i = 670; i > 0; i-=120) {
			g2.drawLine(i, 10, i, HEIGHT-10);
			g2.drawString(String.valueOf(mostTime), i, HEIGHT-10);
			mostTime -= 1;
			if(mostTime == 0){
				mostTime = 12;
			}
		}
		//使用默认样式
		g2.setStroke(new BasicStroke());
		
		String date = DateHelper.getSimpleDate();
		double endC = goodsStatisticsDao.selectClickSumBySort(type, date, conn)/300;
		double endB = goodsStatisticsDao.selectBuySumBySort(type, date, conn)/300;
		int time = Integer.valueOf(date);
		int startX = 790;
		for (int i = 0; i < 5; i++) {
			time = time-1;
			startX = startX - 120;
			int click = goodsStatisticsDao.selectClickSumBySort(type, String.valueOf(time), conn);
			if(click > 0){
				double startC = click/300;
				g2.setStroke(new BasicStroke(2.0F,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
				g2.setColor(Color.BLUE);
				g2.drawLine(startX-120, 300-(int) startC, startX, 300-(int) endC);
				g2.setStroke(new BasicStroke());
				endC = startC;
			}else{
				endC = 0;
			}
			int buy = goodsStatisticsDao.selectBuySumBySort(type, String.valueOf(time), conn);
			if(buy > 0){
				double startB = buy/300;
				g2.setStroke(new BasicStroke(2.0F,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
				g2.setColor(Color.YELLOW);
				g2.drawLine(startX-120, 300-(int) startB, startX, 300-(int) endB);
				g2.setStroke(new BasicStroke());
				endB = startB;
			}else{
				endB = 0;
			}
		}
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("宋体", Font.ITALIC, 24));
		g2.drawString("蓝色-点击量", 650, 40);
		g2.drawString("黄色-购买量", 650, 70);
		DBHelper.closeConnection(conn);
		ServletOutputStream sos = response.getOutputStream();
		ImageIO.write(bi, "jpg", sos);
		sos.flush();
		sos.close();
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
//		sort = 0;
	}

}
