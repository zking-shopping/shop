package com.shopping.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.web.form.FormFather;

public abstract class ActionFather {

	public abstract String doAction(HttpServletRequest request, HttpServletResponse response,FormFather ff);
}
