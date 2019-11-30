package com.shopping.web.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.shopping.dao.GoodsDao;
import com.shopping.dao.PicDao;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.dao.daoImpl.PicDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Goods;

public class MintypeAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		String forward = null;
  		Goods goods = new Goods();
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
  		//根据传过来的页面和数量查询数据库
        String sort = request.getParameter("sort");
  		//根据sort查询数据库,找到所有这一类型的图片
        Connection conn = DBHelper.getConnection();
        GoodsDao gd = new GoodsDaoImpl();
//        System.out.println(sort);
        List<Goods> sortList = gd.selectBySort(sort, conn);
        System.out.println(sortList.size());
        DBHelper.closeConnection(conn);
        
        JSONArray jo = JSONArray.fromObject(sortList);
  		
  		try {
  			//如果页码不够
  			PrintWriter out = response.getWriter();
  			if(sortList.size()!=0){
//  				System.out.println(list);
  				out.print(jo.toString());
  				out.flush();
  				out.close();
  			}else{
  				forward = "error";
  			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		return forward;
	}

}
