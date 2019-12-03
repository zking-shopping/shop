package com.shopping.web.action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.AddressDao;
import com.shopping.dao.daoImpl.AddressDaoImpl;
import com.shopping.db.DBHelper;
import com.shopping.pojo.Address;
import com.shopping.pojo.Member;
import com.shopping.web.form.SavaAddressForm;

public class SavaAddressAction extends ActionFather{
	
	public Object doAction(HttpServletRequest request,
			HttpServletResponse response, Object o) {
  		response.setCharacterEncoding("UTF-8");
  		response.setHeader("Content-Type", "application/json;charset=utf-8");
  		
		SavaAddressForm saf = (SavaAddressForm) o;
		//获得会员id
//		Member m = (Member)request.getSession().getAttribute("member");
//		String memberId = m.getId();
		String memberId = "125a2177-a2be-4ee4-8223-8ecfe0eefef4";
		Address add = new Address();
		add.setMemberId(memberId);
		add.setCousignee(saf.getCousignee());
		add.setPhoneNumber(saf.getPhoneNumber());
		add.setProvinces(saf.getProvinces());
		add.setCity(saf.getCity());
		add.setArea(saf.getArea());
		add.setDetailAddress(saf.getDetailAddress());
		add.setDefaultAddress(saf.getDefaultAddress());

		Connection conn = DBHelper.getConnection();
		AddressDao ad = new AddressDaoImpl();
		boolean b = ad.insert(add, conn);
		System.out.println(b);
		
		return null;
	}

}
