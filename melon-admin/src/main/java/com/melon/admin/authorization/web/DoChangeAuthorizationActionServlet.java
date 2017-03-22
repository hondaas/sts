package com.melon.admin.authorization.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.admin.user.service.UserService;
import com.melon.admin.user.service.UserServiceImpl;

public class DoChangeAuthorizationActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	public DoChangeAuthorizationActionServlet() {
		userService = new UserServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] checkedUsers = request.getParameterValues("myCheck");

		String beforeAuthorization = request.getParameter("beforeAuthorization");
		String afterAuthorization = request.getParameter("afterAuthorization");

		System.out.println("before = " + beforeAuthorization);
		System.out.println("after = " + afterAuthorization);

		if (userService.changeUser(checkedUsers, beforeAuthorization, afterAuthorization)) {
			response.sendRedirect("/melon-admin/authorization/list");
			return;
		}

	}

}
