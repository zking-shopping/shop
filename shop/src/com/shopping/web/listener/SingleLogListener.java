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
		String infoName = arg0.getName();    //登录新账户的账号
		if (infoName.equals("member")) { // 如果名字是info
			// 拿到对应的pojo对象
			Member newInfo = (Member) arg0.getValue();
			// 拿到新的对象的名字
			if (map.get(infoName) != null) { // 如果新对象的账号对应的session值不为空，说明已经有人登录过了
				HttpSession session = map.get(infoName);
				session.removeAttribute("member");
				System.out.println(newInfo.getUsername() + "已经在线了");
			}
			map.put(infoName, arg0.getSession());
		}
		application.setAttribute("loginMap", map);
		System.out.println(map.size()+"==Map里面的用户数");

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		ServletContext application = arg0.getSession().getServletContext();
		if (map == null) {
			map = (Map<String, HttpSession>) application.getAttribute("loginMap");
		}
		// 拿到新对象的名字
		String infoName = arg0.getName();
		if (infoName.equals("member")) { // 如果名字是info
			System.out.println("map移除了一个session");
			// 拿到新的对象的名字
			map.remove(infoName);
		}
		application.setAttribute("loginMap", map);
		System.out.println(map.size()+"==size");
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		ServletContext application = arg0.getSession().getServletContext();
		if (map == null) {
			map = (Map<String, HttpSession>) application.getAttribute("loginMap");
		}
		String infoName = arg0.getName();
		if (infoName.equals("member")) {
			System.out.println("map替换了一个session");
			// 移除旧的账号
			Member oldInfo = (Member) arg0.getValue();
			Member newInfo = (Member) arg0.getSession().getAttribute("member");
			map.remove(oldInfo.getUsername());
			Member m = (Member) arg0.getSession().getAttribute("member");
			if (map.get(m.getUsername()) != null) {
				HttpSession session = map.get(m.getUsername());
				Member old = (Member) session.getAttribute("member");
				System.out.println("替换的用户" +old.getUsername()+ "已经在线了");
			}
			map.put(newInfo.getUsername(), arg0.getSession());
		}
		application.setAttribute("loginMap", map);
	}
	
}
