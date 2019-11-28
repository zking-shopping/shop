package com.shopping.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.shopping.dao.MemberDao;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Goods;
import com.shopping.pojo.Member;
import com.shopping.util.EncryptionHelper;

public class TypeAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {

		String forward = null;
//		MemberDao md = new MemberDaoImpl();
//        Connection conn = DBHelper.getConnection();
//  		MemberDao dao = new MemberDaoImpl();
  		Goods goods = new Goods();
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
  		//根据传过来的页面和数量查询数据库
  		int page = Integer.parseInt( request.getParameter("page"));
  		int pagesize = Integer.parseInt(request.getParameter("pagesize"));
  		//根据名字和密码获得的member全部信息
  		goods.setGoodsName("夜光灯");
  		goods.setIntroduction("一种用来夜间照明的照明工具");
  		//因为图片还没有id ,暂时用别的属性顶替存储图片的地址
  		goods.setTime("/shop/img/type1/13--.jpg");
  		JSONObject jo = JSONObject.fromObject(goods);
  		try {
  			//如果页码不够
  			PrintWriter out = response.getWriter();
  			if(page<=20){
	  			forward = "success";
	  			out.print(jo.toString());
  			}else{
  				forward="error";
  			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		return forward;
	
	}
}
