package com.shopping.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shopping.pojo.Member;

public class QuitLoginAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
		String forward = null;
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		System.out.println(member.getUsername());
		session.removeAttribute("member");
		forward = "success";
		return forward;
	}
}
