package com.melon.admin.authorization.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.melon.admin.authorization.service.AuthorizationService;
import com.melon.admin.authorization.service.AuthorizationServiceImpl;
import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.authorization.vo.AuthorizationVO;
import com.melon.admin.common.web.pager.ClassicPageExplorer;
import com.melon.admin.common.web.pager.PageExplorer;
import com.melon.admin.user.service.UserService;
import com.melon.admin.user.service.UserServiceImpl;
import com.melon.admin.user.vo.UserSearchVO;
import com.melon.admin.user.vo.UserVO;

public class ViewAuthorizationListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService userService; 
    private AuthorizationService authorizationService;
 
    public ViewAuthorizationListServlet() {
    	userService = new UserServiceImpl();
    	authorizationService = new AuthorizationServiceImpl();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageNo = request.getParameter("pageNo");
	
		
		UserSearchVO userSearchVO = new UserSearchVO();
		AuthorizationSearchVO authorizationSearchVO = new AuthorizationSearchVO();
		
		userSearchVO.getPager().setPageNumber(pageNo);
		authorizationSearchVO.getPager().setPageNumber(pageNo);
		
		List<UserVO> userList = userService.getAllUsers(userSearchVO);
		List<AuthorizationVO> authorizationList = authorizationService.selectAllAuthorizations(authorizationSearchVO);
		
		
		
		
		
		PageExplorer pager = new ClassicPageExplorer(userSearchVO.getPager());
		
		request.setAttribute("userList", userList);
		request.setAttribute("authorizationList", authorizationList);
		request.setAttribute("userCount", userSearchVO.getPager().getTotalArticleCount());
		request.setAttribute("pager", pager.getPagingList("pageNo", "[@]", "이전", "이후", "listForm"));
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/authorization/list.jsp");
		dispatcher.forward(request, response);
	}

}
