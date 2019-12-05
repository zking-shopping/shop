package com.shopping.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.AddressDao;
import com.shopping.dao.OrderDao;
import com.shopping.dao.daoImpl.AddressDaoImpl;
import com.shopping.dao.daoImpl.OrderDaoImpl;
import com.shopping.db.DBHe;
import com.shopping.db.DBHelper;
import com.shopping.dto.OrderDto;
import com.shopping.pojo.Address;
import com.shopping.pojo.Order;

public class AllOrderServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AllOrderServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String state = request.getParameter("state");
		Connection conn = DBHelper.getConnection();
		OrderDao dao = new OrderDaoImpl();
		AddressDao addressDao = new AddressDaoImpl();
		List<Order> list = dao.selectAllByState(state, conn);
		List<OrderDto> orderDtoList = new ArrayList<OrderDto>();
		for (int i = 0; i < list.size(); i++) {
			Order order = list.get(i);
			Address address = new Address();
			address.setId(order.getAddressId());
			Address addr = (Address) addressDao.selectById(address, conn);
			OrderDto orderDto = new OrderDto();
			orderDto.setId(order.getId());
			orderDto.setMemberId(order.getMemberId());
			orderDto.setOrderNumber(order.getOrderNumber());
			orderDto.setTotal(order.getTotal());
			orderDto.setTime(order.getTime());
			orderDto.setState(order.getState());
			orderDto.setCousignee(addr.getCousignee());
			orderDto.setPhoneNumber(addr.getPhoneNumber());
			orderDto.setProvinces(addr.getProvinces());
			orderDto.setCity(addr.getCity());
			orderDto.setArea(addr.getArea());
			
			orderDtoList.add(orderDto);
		}
		DBHelper.closeConnection(conn);
		request.setAttribute("AllOrder", orderDtoList);
		request.setAttribute("state", state);
		request.getRequestDispatcher("/admin/allOrder.jsp").forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
