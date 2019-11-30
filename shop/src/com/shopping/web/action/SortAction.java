package com.shopping.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shopping.dao.GoodsDao;
import com.shopping.dao.MemberDao;
import com.shopping.dao.PicDao;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.dao.daoImpl.PicDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Goods;
import com.shopping.pojo.Member;
import com.shopping.pojo.Pic;
import com.shopping.util.EncryptionHelper;

public class SortAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
       
		String forward = null;
  		Goods goods = new Goods();
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
  		//根据传过来的页面和数量查询数据库
  		int page = Integer.parseInt( request.getParameter("page"));
  		int pagesize = Integer.parseInt(request.getParameter("pagesize"));
        String sort = request.getParameter("sort");
  		//根据sort查询数据库,找到所有这一类型的图片
//        List<Goods> list = new ArrayList<Goods>();
        Connection conn = DBHelper.getConnection();
        GoodsDao gd = new GoodsDaoImpl();
//        System.out.println(sort);
        PicDao pd = new PicDaoImpl();
        List<Goods> sortList = gd.selectBySort("2", conn);
        System.out.println(sort);
        DBHelper.closeConnection(conn);
        //根据查询的所有商品找到所有的相对应的picid
//        List<Integer> listPic = new ArrayList<Integer>();
//       for(int i = 0;i<list.size();i++){
//    	   listPic.add(list.get(i).getPicId());
//       }
       //根据每一个picid找到对应的详情图片的picture1
//       List<String> pictures = new ArrayList<String>();
//       for(int i = 0;i<listPic.size();i++){
//    	   Pic pic =  (Pic) pd.selectById(list.get(i).getPicId(), conn);
//    	   String picture = pic.getPicture1();
//    	    pictures.add(picture);
//       }
       
       
        //查询到每一张图片的所有信息,传到前端
//  		goods.setIntroduction("一种用来夜间照明的照明工具");
//  		goods.setSort(sort);
  		
  		//因为图片还没有id ,暂时用别的属性顶替存储图片的地址
//  		goods.setTime("/shop/img/type1/13--.jpg");
 
  		JSONArray jo = JSONArray.fromObject(sortList);
  		
  		try {
  			//如果页码不够
  			PrintWriter out = response.getWriter();
  			if(page<=20){
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
