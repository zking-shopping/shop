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
		request.setCharacterEncoding("UTF-8");
		
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
		
		Connection conn = DBHelper.getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		Goods goods = new Goods();
		Color c = new Color();
		Pic p = new Pic();
		String goodsId = UUIDHelper.getUUID();
		String picId = UUIDHelper.getUUID();
		goods.setGoodsName(goodsName);
		goods.setPrice(price);
		goods.setIntroduction(introduction);
		goods.setSort(type);
		goods.setPicId(Integer.valueOf(picId));
		goods.setTime(DateHelper.getSimpleDate());
		goods.setDel("false");
		c.setGoodsId(Integer.valueOf(goodsId));
		c.setGoodsColor(color);
		c.setStock(Integer.valueOf(colorContent));
		
		for (int i = 0; i < su.getFiles().getCount(); i++) {
			File pic = su.getFiles().getFile(i);
			String path = this.getServletContext().getRealPath("/")+"/admin/img/"+ UUIDHelper.getUUID() + pic.getFileExt();
			try {
				pic.saveAs(path);
				if(i == 0){
					p.setPicture1(path);
				}
				if(i == 1){
					p.setPicture2(path);
				}
				if(i == 2){
					p.setPicture3(path);
				}
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
		}
		p.setId(Integer.valueOf(picId));
		p.setDel("false");
//		try{
//			goodsDao.insert(goods, conn);
//			colorDao.insert(c, conn);
//			picDao.insert(p, conn);
//		}catch(Exception e){
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//		}finally{
//			DBHelper.closeConnection(conn);
//		}
		
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
