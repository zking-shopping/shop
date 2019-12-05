package com.shopping.web.listener;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.shopping.dao.MemberDao;
import com.shopping.dao.MemberStatisticsDao;
import com.shopping.dao.daoImpl.MemberDaoImpl;
import com.shopping.dao.daoImpl.MemberStatisticsDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Member;
import com.shopping.pojo.MemberStatistics;
import com.shopping.util.DateHelper;
import com.shopping.util.TimeHelper;

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
				HttpSession session = map.get(newInfo.getUsername());
				session.setAttribute("msg", "您的帐号已经在其他机器上登录，您被迫下线。");
				session.removeAttribute("member");
			}
			map.put(infoName, arg0.getSession());
		}
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
			Member a = (Member) arg0.getValue();
			if(a.getId() != null){
				long startTime = (Long) arg0.getSession().getAttribute("startTime");
				if(startTime > 0){
					statictis(a, startTime);
				}
			}
			map.remove(a.getUsername()); 
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
			Member oldMember = (Member) arg0.getValue();
			map.remove(oldMember.getUsername());
			Member newMember = (Member) arg0.getSession().getAttribute("member");
			if(map.get(newMember.getUsername()) != null){
				HttpSession session = map.get(newMember.getUsername());
				session.setAttribute("msg", "您的帐号已经在其他机器上登录，您被迫下线。");
				Member a = (Member) session.getAttribute("member");
				if(a.getId() != null){
					long startTime = (Long) session.getAttribute("startTime");
					if(startTime > 0){
						statictis(a, startTime);
					}
				}
				session.removeAttribute("member");
			}
			map.put(newMember.getUsername(), arg0.getSession());
		}
		application.setAttribute("loginMap", map);
	}
	
	public void statictis(Member a, long startTime){
		Connection conn = DBHelper.getConnection();
		MemberDao dao = new MemberDaoImpl();
		long endTime = TimeHelper.getTime();
		int time = TimeHelper.duration(startTime, endTime);
		String nowDate = DateHelper.getSimpleDate();
		int oldTime = Integer.valueOf(a.getTime());
		int newTime = oldTime+time;
		try{
			conn.setAutoCommit(false);
			if(nowDate.equalsIgnoreCase(a.getDate())){
				a.setTime(String.valueOf(newTime));
				Boolean res = dao.update("updateMemberTime", a, conn);
			}else{
				String oldCost = a.getCost();
				MemberStatisticsDao statisticsDao = new MemberStatisticsDaoImpl();
				MemberStatistics ms = new MemberStatistics();
				ms.setMembeId(a.getId());
				ms.setTime(a.getTime());
				ms.setCost(a.getCost());
				statisticsDao.insert(ms, conn);
				a.setDate(DateHelper.getSimpleDate());
				a.setCost("0");
				a.setTime(String.valueOf(time));
				Boolean res = dao.update("updateStatistics", a, conn);
			}
			conn.commit();
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			DBHelper.closeConnection(conn);
		}
	}
}
