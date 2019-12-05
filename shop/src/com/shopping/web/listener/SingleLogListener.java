package com.shopping.web.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.shopping.pojo.Member;

public class SingleLogListener implements HttpSessionAttributeListener {

	Map<String, HttpSession> map = null;

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		ServletContext application = arg0.getSession().getServletContext();
		if(map==null){
			map = (Map<String, HttpSession>) application.getAttribute("loginMap");
		}

		String infoName = arg0.getName();    //session存储的键
		if (infoName.equals("member")) {     //如果名字是member
			// 拿到对应的pojo对象
			Member newInfo = (Member) arg0.getValue();   //新账户对应的member对象
			// 拿到新的对象的名字
			if (map.get(infoName) != null) { // 如果新对象的账号对应的session值不为空，说明已经有人登录过了
				HttpSession session = arg0.getSession();    //新账户对应的session
				session.removeAttribute("member");          //新账户的session移除member对象
				System.out.println(newInfo.getUsername() + "已经在线了");    //member对象的账户名
				session.setAttribute("msg", "您的帐号已经在其他机器上登录，您被迫下线。");
			}
			map.put(infoName, arg0.getSession());
		}
        
		map.put(infoName, arg0.getSession());

		application.setAttribute("loginMap", map);
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		ServletContext application = arg0.getSession().getServletContext();
		if (map == null) {
			map = (Map<String, HttpSession>) application.getAttribute("loginMap");
		}
		// 拿到新对象的名字
		String infoName = arg0.getName();    //session存储的键
		if (infoName.equals("member")) {    // 如果名字是member
			//新账户对应的对象
			Member newInfo = (Member) arg0.getValue();
			// 拿到新的对象的名字
				map.remove(newInfo.getUsername());        //
		}
		application.setAttribute("loginMap", map);
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
         
		ServletContext application = arg0.getSession().getServletContext();
		if (map == null) {
			map = (Map<String, HttpSession>) application.getAttribute("loginMap");
		}
		
		String infoName = arg0.getName();  //session对应的键是member
		if (infoName.equals("member")) {
			// 移除旧的账号
			Member oldInfo = (Member) arg0.getValue();
			map.remove(oldInfo.getUsername());
			//新的登录信息
			Member newInfo = (Member) arg0.getSession().getAttribute("member");
			
			if (map.get(newInfo.getUsername()) != null) { // 如果新对象的账号对应的session值不为空，说明已经有人登录过了
				HttpSession session = map.get(newInfo.getUsername());
                session.removeAttribute("member");
                session.setAttribute("msg", "您的帐号已经在其他机器上登录，您被迫下线");
			}
			Member m = (Member) arg0.getSession().getAttribute("member");
			if (map.get(m.getUsername()) != null) {
				HttpSession session = map.get(m.getUsername());
				Member old = (Member) session.getAttribute("member");
			}
			map.put(newInfo.getUsername(), arg0.getSession());
		}

		
		application.setAttribute("loginMap", map);
	}
	
}
