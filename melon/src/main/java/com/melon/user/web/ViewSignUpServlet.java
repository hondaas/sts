package com.melon.user.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.user.service.UserService;
import com.melon.user.service.UserServiceImpl;
import com.melon.user.vo.UserVO;

public class ViewSignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	public ViewSignUpServlet() {
		userService = new UserServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/user/signUp.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");

		if(userId == null || userId.length() == 0 ){
			response.sendRedirect("/melon/user/signUp?errorCode=0");
			return;
		}
		if(userName == null || userName.length() == 0 ){
			response.sendRedirect("/melon/user/signUp?errorCode=1");
			return;
		}
		if(userPassword == null || userPassword.length() == 0 ){
			response.sendRedirect("/melon/user/signUp?errorCode=2");
			return;
		}

		if(userService.isDuplicateUserId(userId)){
			response.sendRedirect("/melon/user/signUp?errorCode=3");
			return;
		}
	
		
		
		UserVO userVO = new UserVO();

		userVO.setUserId(userId);
		userVO.setUserName(userName);
		userVO.setUserPassword(userPassword);

		if (userService.addNewUser(userVO)) {

			response.sendRedirect("/melon/artist/list");
			
		} else {

			response.sendRedirect("/melon/user/signUp");

		}

	}

}
