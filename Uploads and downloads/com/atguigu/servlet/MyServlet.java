package com.atguigu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MyServlet  请求到达了.....");
		
		//1. 往Request对象中绑定数据
		request.setAttribute("username", "Admin");
		
		//2. 往Request中替换数据
		request.setAttribute("username", "Root");
		
		//3. 从Request中移除数据
		request.removeAttribute("username");
		
		
		
		//响应.
		response.sendRedirect("main.jsp");
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
