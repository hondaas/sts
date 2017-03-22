package com.melon.admin.user.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.melon.admin.user.service.UserService;
import com.melon.admin.user.service.UserServiceImpl;
import com.melon.admin.user.vo.UserVO;

public class DoUserModifyActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	
    public DoUserModifyActionServlet() {
        userService = new UserServiceImpl();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(404);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pointParam = request.getParameter("userPoint");
		String password = request.getParameter("userPassword");
		String authId = request.getParameter("authorizationId");
		String userId = request.getParameter("userId");
		
		/*
		System.out.println("point = " + pointParam);
		System.out.println("password = " + password);
		System.out.println("authId = " + authId);
		System.out.println("userId = " + userId);
		*/
		int userPoint = 0;
		
		try {
			userPoint = Integer.parseInt(pointParam);
		} catch(NumberFormatException e) {
			userPoint = 0;
		}
		
		UserVO userVO = new UserVO();
		userVO.setUserPoint(userPoint);
		userVO.setUserPassword(password);
		userVO.setAuthorizationId(authId);
		userVO.setUserId(userId);
		
		userService.updateUser(userVO);
		
		boolean isSuccess = userService.updateUser(userVO);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", isSuccess ? " success" : "fail");
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		
		PrintWriter writer = response.getWriter();
		writer.write(json);
		writer.flush();
		writer.close();
		
		
	}

}
