package com.shopping.web.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shopping.dao.BaseDao;
import com.shopping.dao.GoodsDao;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Goods;

public class BettergoodsAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		String forward = null;
  		Goods goods = new Goods();
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
        Connection conn = DBHelper.getConnection();
        BaseDao bd = new GoodsDaoImpl();
       List<Object> list = new ArrayList<Object>();
    		  list =  bd.selectAll(new Goods(),conn);
    		  
        DBHelper.closeConnection(conn);
        JSONArray jo =JSONArray.fromObject(list);
  		try {
  			//如果页码不够
  			PrintWriter out = response.getWriter();
  			if(list.size()!=0){
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
