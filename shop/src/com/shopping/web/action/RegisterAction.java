package com.shopping.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.shopping.dao.MemberDao;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Member;
import com.shopping.util.DateHelper;
import com.shopping.util.UUIDHelper;
import com.shopping.web.form.FormFather;
import com.shopping.web.form.RegisterForm;

public class RegisterAction extends ActionFather{

	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, FormFather ff) {
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
		RegisterForm rf = (RegisterForm) ff;
		System.out.println("Username："+rf.getUsername());
		System.out.println("Password："+rf.getPassword());
		System.out.println("PhoneNumber："+rf.getPhoneNumber());
		
		MemberDao md = new MemberDaoImpl();
        Connection conn = DBHelper.getConnection();
		Member m = new Member();
		m.setUsername(rf.getUsername());
		m.setPassword(rf.getPassword());
		m.setPhoneNumber(rf.getPhoneNumber());
		Member ms = (Member) md.select("selectByUsername", m, conn);
		JSONArray jarr = null;
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if(ms.getUsername()==null){
				String uuid = UUIDHelper.getUUID();
				String name = "用户"+uuid.substring(0, 8);
				new String(name.getBytes("ISO8859-1"),"UTF-8");
				//设置账号初始值
				m.setName(name);
				System.out.println("用户"+uuid.substring(0, 8));
				m.setId(uuid);
				m.setCost("0");
				m.setTime("0");
				m.setDate(DateHelper.getSimpleDate());
				m.setDel("false");
				boolean b = md.insert(m, conn);
				if(b==true){
					jarr = JSONArray.fromObject(0);
				}else{
					jarr = JSONArray.fromObject(1);
				}
			}else{
				jarr = JSONArray.fromObject(2);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			DBHelper.closeConnection(conn);
		}
		out.println(jarr.toString());
		out.close();
		
		return null;
	}

}
