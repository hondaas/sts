package com.ktds.dojun.common.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class characterEncodingFilter implements Filter {

    public characterEncodingFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");
	// 모든 URL에 UTF-8이 적용된다.
	
	

		// pass the request along the filter chain
		chain.doFilter(request, response); //servlet을 요청하기 / 응답하기   (중계하는)
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
