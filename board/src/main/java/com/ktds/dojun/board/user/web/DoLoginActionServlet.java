package com.ktds.dojun.board.user.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.PortableServer.ServantLocatorPackage.CookieHolder;

import com.ktds.dojun.board.user.biz.UserBiz;
import com.ktds.dojun.board.user.biz.UserBizImpl;
import com.ktds.dojun.board.user.vo.UsersVO;

public class DoLoginActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserBiz userBiz;

	public DoLoginActionServlet() {
		userBiz = new UserBizImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		
		UsersVO user = new UsersVO(); 
		
		user.setUserId(userId);
		user.setUserPassword(userPassword);


		UsersVO usersVO = userBiz.loginUser(user);
		
		if(usersVO==null){
		//	RequestDispatcher dispatcher = request.getRequestDispatcher("/board/login");
	//		dispatcher.forward(request, response); ???
			
			response.sendRedirect("/board/login");
			
		}else{
			
			HttpSession session = request.getSession(true); //세션 컨테이너를 가져온다.
		
			session.setAttribute("_USER_", usersVO); // _USER_ 는 세션 키임을 알려주기 위한 규칙
			//session에 로그인 사용자의 userVO 정보를 담는다. 
			
			
			System.out.println(usersVO.getUserId());
			System.out.println(usersVO.getUserName());
			
			response.sendRedirect("/board/list");
			
		}
	
		
	}

}
