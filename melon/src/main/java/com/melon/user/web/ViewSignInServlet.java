package com.melon.user.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.melon.user.service.UserService;
import com.melon.user.service.UserServiceImpl;
import com.melon.user.vo.UserVO;

public class ViewSignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;

	public ViewSignInServlet() {

		userService = new UserServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/user/signIn.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");

		UserVO userVO = new UserVO();

		userVO.setUserId(userId);
		userVO.setUserPassword(userPassword);

		userVO = userService.getOneUser(userVO);
		
		HttpSession session = request.getSession();
		session.setAttribute("_USER_", userVO);
		
		if(userVO == null ){
			
			response.sendRedirect("/melon/user/signIn");
			
		}else response.sendRedirect("/melon/user/signIn");

	}

}
