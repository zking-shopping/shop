package com.shopping.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopping.dao.CartDao;
import com.shopping.dao.GoodsDao;
import com.shopping.dao.MemberDao;
import com.shopping.dao.PicDao;
import com.shopping.dao.daoImpl.CartDaoImpl;
import com.shopping.dao.daoImpl.GoodsDaoImpl;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.dao.daoImpl.PicDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Cart;
import com.shopping.pojo.Goods;
import com.shopping.pojo.Member;
import com.shopping.pojo.Pic;
import com.shopping.util.EncryptionHelper;
import com.shopping.util.TimeHelper;
import com.shopping.web.form.FormFather;

public class LoginAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		String forward = null;
		MemberDao md = new MemberDaoImpl();
        Connection conn = DBHelper.getConnection();
  		MemberDao dao = new MemberDaoImpl();
  		GoodsDao gd = new GoodsDaoImpl();
  		CartDao cd = new CartDaoImpl();
  		Goods g = null;
  		Member m = new Member();
  		HttpSession session = request.getSession();
  		
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
  		m.setUsername(request.getParameter("username"));
  		//加密密码
  		String pass = EncryptionHelper.encryption(request.getParameter("password"));
  		m.setPassword(pass);
  		//根据名字和密码获得的member全部信息
  		Member member = (Member) dao.select("selectForLogin", m, conn);
  		String memberId = member.getId();
  		try {
			PrintWriter out = response.getWriter();
			if(member.getPassword()!=null){
				System.out.println("session中存储了member,然后登陆了");
	  			request.getSession().setAttribute("member", member);
	  			Cookie[] cookies = request.getCookies();
	  			//登录成功查看cookie里面有没有商品，有就加入购物车
	  			 for(Cookie cookie : cookies){
				        if(cookie.getName().startsWith("goodsToCar")){
				        	g = new Goods();
				        	//
				            String goodInfo = cookie.getValue();
				            String goodid = goodInfo.split("=-=")[0];
				            String goodcount = goodInfo.split("=-=")[1];
				            String goodtype = goodInfo.split("=-=")[2];
				            g.setId(Integer.parseInt(goodid));		          
				            Goods goodOne = (Goods)gd.selectById(g, conn);
				            Cart c = new Cart();
//				            c.setColorId();
				            c.setId(Integer.parseInt(cookie.getName().substring(10)));
				            c.setGoodsColor(goodtype);
				            c.setGoodsId(goodOne.getId());
				            c.setGoodsName(goodOne.getGoodsName());
				            
				            c.setMemberId(memberId);
				            c.setNumber(Integer.parseInt(goodcount));
				            c.setPrice(goodOne.getPrice());
				            int picId = goodOne.getPicId();
							PicDao pd = new PicDaoImpl();
							Pic p = new Pic();
							p.setId(picId);
							Pic pic = (Pic)pd.selectById(p, conn);
							c.setUrl(pic.getPicture1());
				            //把cart  insert到用户对应的购物车
							cd.insert(c, conn);
				            g = null;
				            cookie.setMaxAge(0);
				            response.addCookie(cookie);
				        }

				     }
	  			
	  			long startTime = TimeHelper.getTime();
	  			session.setAttribute("startTime", startTime);
	  			 
	  			forward = "success";
	  		}else{
	  			forward = "error";
	  		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		
  		return forward;
	}

}
