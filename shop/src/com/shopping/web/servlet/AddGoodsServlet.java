package com.shopping.web.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.shopping.dao.ColorDao;
import com.shopping.dao.GoodsDao;
import com.shopping.dao.PicDao;
import com.shopping.dao.daoImpl.ColorDaoImpl;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.dao.daoImpl.PicDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Color;
import com.shopping.pojo.Goods;
import com.shopping.pojo.Pic;
import com.shopping.util.DateHelper;
import com.shopping.util.UUIDHelper;

public class AddGoodsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddGoodsServlet() {
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
		request.setCharacterEncoding("gbk");
		
		SmartUpload su = new SmartUpload();
		su.initialize(this.getServletConfig(), request, response);
		try {
			su.upload();
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		GoodsDao goodsDao = new GoodsDaoImpl();
		ColorDao colorDao = new ColorDaoImpl();
		PicDao picDao = new PicDaoImpl();
		
		String goodsName = su.getRequest().getParameter("goodsName");
		String price = su.getRequest().getParameter("price");
		String introduction = su.getRequest().getParameter("introduction");
		String type = su.getRequest().getParameter("type");
		String color = su.getRequest().getParameter("color");
		String colorContent = su.getRequest().getParameter("colorContent");
		
		if(goodsName.length()==0 || price.length()==0 || introduction.length()==0 || type.length()==0 || color.length()==0 || colorContent.length()==0){
			response.sendRedirect("/shop/admin/index.jsp");
			return;
		}
		
		Connection conn = DBHelper.getConnection();
		Goods goods = new Goods();
		Color c = new Color();
		Pic p = new Pic();
		
		goods.setGoodsName(goodsName);
		goods.setPrice(price);
		goods.setIntroduction(introduction);
		goods.setSort(type);
		goods.setTime(DateHelper.getSimpleDate());
		goods.setDel("false");
		
		c.setGoodsColor(color);
		c.setStock(Integer.valueOf(colorContent));
		c.setHide("false");
		for (int i = 0; i < su.getFiles().getCount(); i++) {
			File pic = su.getFiles().getFile(i);
			String name = UUIDHelper.getUUID();
			String path = "/admin/img/"+ name + "." + pic.getFileExt();
			String path1 = "aaaaaaaaaaaaaaaaaaaaaa/shop/admin/img/"+ name + "." + pic.getFileExt();
			try {
				pic.saveAs(path, File.SAVEAS_AUTO);
				if(i == 0){
					p.setPicture1(path1);
				}
				if(i == 1){
					p.setPicture2(path1);
					pic.saveAs(path, File.SAVEAS_AUTO);
				}
				if(i == 2){
					p.setPicture3(path1);
					pic.saveAs(path, File.SAVEAS_AUTO);
				}
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
		}
		p.setDel("false");
		
		
		try{
			conn.setAutoCommit(false);
			Boolean res1 = picDao.insert(p, conn);
			int picId = picDao.selectMaxId(conn);
			System.out.println(picId+"==res1=="+res1);
			goods.setPicId(picId);
			Boolean res2 = goodsDao.insert(goods, conn);
			int goodsId = goodsDao.selectMaxId(conn);
			System.out.println(goodsId+"==res2=="+res2);
			c.setGoodsId(goodsId);
			Boolean res3 = colorDao.insert(c, conn);
			System.out.println("res3=="+res3);
			conn.commit();
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			DBHelper.closeConnection(conn);
		}
		request.getRequestDispatcher("/admin/allGoods.do").forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
