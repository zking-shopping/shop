package com.shopping.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToPayAction extends ActionFather{

	@Override
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");

  		//"immediatePayment.do?addressId="+result.addressId+"&total="+result.total+"&receiveInfo"+receiveInfo
	  	String addressId = request.getParameter("addressId");
	  	String total = request.getParameter("total");
	  	String receiveInfo = request.getParameter("receiveInfo");
	  	String id = request.getParameter("id");
	  	if(addressId!=null && !addressId.equals("null") && !addressId.equals("undefined")){
	  		if(total!=null && !total.equals("null") && !total.equals("undefined")){
	  			if(receiveInfo!=null && !receiveInfo.equals("null") && !receiveInfo.equals("undefined")){
	  				if(id!=null && !id.equals("null") && !id.equals("undefined")){
		  			  	request.setAttribute("addressId", addressId);
		  			  	request.setAttribute("total", total);
		  			  	request.setAttribute("receiveInfo", receiveInfo);
		  			  	request.setAttribute("id", id);
		  				return "success";
		  		  	}
					return "error";
	  		  	}
				return "error";
		  	}
			return "error";
	  	}else{
			return "error";
	  	}
	}

}
