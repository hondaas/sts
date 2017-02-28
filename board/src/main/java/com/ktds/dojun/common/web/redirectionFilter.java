package com.ktds.dojun.common.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ktds.dojun.board.user.vo.UsersVO;

public class redirectionFilter implements Filter {

    public redirectionFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// HttpSession session = ((HttpServletRequest)request).getSession();
		
		HttpSession session = req.getSession();
		
		UsersVO user = (UsersVO) session.getAttribute("_USER_");
		
		
		if(user == null){
			
			//((HttpServletResponse)response).sendRedirect("/board/login");
			res.sendRedirect("/board/login");
			
			return;
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
