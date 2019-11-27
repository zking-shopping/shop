package com.shopping.web.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.shopping.pojo.Member;

public class SingleLogListener implements HttpSessionAttributeListener{

	 Map<String, HttpSession> map = new HashMap<String, HttpSession>();
		@Override
		public void attributeAdded(HttpSessionBindingEvent arg0) {
			String infoName = arg0.getName();
			if(infoName.equals("member")){   //如果名字是info 
				
				  //拿到对应的pojo对象
				Member newInfo = (Member)arg0.getValue();
				//拿到新的对象的名字
				if(map.get(infoName)!=null){   //如果新对象的账号对应的session值不为空，说明已经有人登录过了
					 HttpSession session = map.get(infoName);
					 Member oldInfo = (Member)session.getAttribute(infoName);
					 session.removeAttribute("member");
					 System.out.println(oldInfo.getUsername()+"已经在线了");
				}
			}
			
			map.put(infoName, arg0.getSession());
	   }
		

		@Override
		public void attributeRemoved(HttpSessionBindingEvent arg0) {
			// TODO Auto-generated method stub
			//拿到新对象的名字
			String infoName = arg0.getName();
			if(infoName.equals("member")){   //如果名字是info 
				  //拿到对应的pojo对象
				Member newInfo = (Member)arg0.getValue();
				//拿到新的对象的名字
				if(map.get(infoName)!=null){   //如果新对象的账号对应的session值不为空，说明已经有人登录过了
					 map.remove(infoName);
				}
			}
		}

		@Override
		public void attributeReplaced(HttpSessionBindingEvent arg0) {
			  String infoName = arg0.getName();
				if(infoName.equals("member")){ 
					//移除旧的账号
					Member oldInfo = (Member)arg0.getValue();
					map.remove(oldInfo.getUsername());
				}
				
				if(map.get(infoName)!=null){   //如果新对象的账号对应的session值不为空，说明已经有人登录过了
					 HttpSession session = map.get(infoName);
					 Member oldInfo = (Member)session.getAttribute(infoName);
					 System.out.println("替换的用户"+oldInfo.getUsername()+"已经在线了");
				}
				map.put(infoName,arg0.getSession());
			}
}
