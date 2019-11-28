package com.shopping.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.shopping.dao.MemberDao;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Member;
import com.shopping.util.DateHelper;
import com.shopping.util.EncryptionHelper;
import com.shopping.util.UUIDHelper;
import com.shopping.web.form.FormFather;
import com.shopping.web.form.RegisterForm;

public class RegisterAction extends ActionFather{

	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, FormFather ff) {
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
  		//将ff的类型转换为需要的
		RegisterForm rf = (RegisterForm) ff;
		//获得连接
        Connection conn = DBHelper.getConnection();
        //设置会员基础信息
		Member m = new Member();
		m.setUsername(rf.getUsername());
		//密码加密
		String pass = EncryptionHelper.encryption(rf.getPassword());
		m.setPassword(pass);
		m.setPhoneNumber(rf.getPhoneNumber());
		
		JSONArray jarr = null;
		PrintWriter out = null;
        try {
            //设置不自动提交
			conn.setAutoCommit(false);
    		//查询账号是否存在
    		MemberDao md = new MemberDaoImpl();
    		Member ms = (Member) md.select("selectByUsername", m, conn);
    		conn.commit();
			//获得输出流
			out = response.getWriter();
			if(ms.getUsername()==null){
				String uuid = UUIDHelper.getUUID();
				String name = "用户"+uuid.substring(0, 8);
				new String(name.getBytes("ISO8859-1"),"UTF-8");
				//设置账号初始值
				m.setName(name);
				m.setId(uuid);
				m.setCost("0");
				m.setTime("0");
				m.setDate(DateHelper.getSimpleDate());
				m.setDel("false");
				boolean b = md.insert(m, conn);
	    		conn.commit();
				if(b==true){
					jarr = JSONArray.fromObject(0);
				}else{
					jarr = JSONArray.fromObject(1);
				}
			}else{
				jarr = JSONArray.fromObject(2);
			}
		} catch (SQLException e) {
			try {
				//回滚
	            conn.rollback();
	        } catch (SQLException e1) {
	            e1.printStackTrace();
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
