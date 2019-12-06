package com.shopping.web.action;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.CartDao;
import com.shopping.dao.GoodsDao;
import com.shopping.dao.PicDao;
import com.shopping.dao.daoImpl.CartDaoImpl;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.dao.daoImpl.PicDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Cart;
import com.shopping.pojo.Goods;
import com.shopping.pojo.Member;
import com.shopping.pojo.Pic;

public class AddToShoppingCarAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		
		Connection conn = DBHelper.getConnection();
		Member m = (Member)request.getSession().getAttribute("member");
		int id = Integer.parseInt(request.getParameter("id"));
		String type_selected = request.getParameter("type_selected");
		try {
			type_selected = new String(type_selected.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("text/html;charset=utf-8");//设置页面的字符编码
		
		GoodsDao gd = new GoodsDaoImpl();
		Goods g = new Goods();
		g.setId(id);
		Goods getg = (Goods)gd.selectById(g, conn);

		if(m==null){
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie : cookies){
		        if(cookie.getName().equals("goodsToCar"+id+"1")){
		        	
		            String goodInfo = cookie.getValue();

		            	int count = Integer.parseInt(goodInfo.split("=-=")[1])+1;
		            	Cookie c = new Cookie("goodsToCar"+id+"1",id+"=-="+String.valueOf(count)+"=-=1");
		            	response.addCookie(c);
		            	return null;
//		            }		            
		        }
		     } 
			Cookie c = new Cookie("goodsToCar"+id+"1",id+"=-="+1+"=-=1");
			response.addCookie(c);
			return null;
		}
		
		
		CartDao cd = new CartDaoImpl();
		ArrayList<Cart> list = (ArrayList<Cart>)cd.selectByMemberId(m.getId(), conn);
		Boolean flag = false;
		int cartId = -1;
		for (Cart cart : list) {
			if(cart.getGoodsId()==id){
				flag = true;
				cartId = cart.getId();
				break;
			}			
		}
		Cart c = null;
		if(flag){
			//表示购物车有此产品，数量加一
			c = new Cart();
			c.setId(cartId);
			cd.update("addNumber", c, conn);			
		}else{
			//表示购物车没有此产品，加入购物车
			c = new Cart();
			
			Goods good = (Goods)gd.selectById(g, conn);
//			c.setColorId(colorId);
			c.setGoodsColor(type_selected);
			c.setGoodsId(good.getId());
			c.setGoodsName(good.getGoodsName());
			c.setMemberId(m.getId());
			c.setNumber(1);
			c.setPrice(good.getPrice());
			int picId = good.getPicId();
			PicDao pd = new PicDaoImpl();
			Pic p = new Pic();
			p.setId(picId);
			Pic pic = (Pic)pd.selectById(p, conn);
			c.setUrl(pic.getPicture1());
			cd.insert(c, conn);
		}
		
		return null;
	}

}
