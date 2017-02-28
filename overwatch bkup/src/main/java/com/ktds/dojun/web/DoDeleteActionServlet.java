package com.ktds.dojun.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.dojun.biz.UnitBiz;
import com.ktds.dojun.biz.UnitBizImpl;

public class DoDeleteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UnitBiz unitBiz;
	
    public DoDeleteActionServlet() {
    	unitBiz = new UnitBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	String unitIdString = request.getParameter("boardId");
	
	
	int unitId = Integer.parseInt(unitIdString);
	
	
	if( unitBiz.deleteUnit(unitId)){
		
		response.sendRedirect("/overwatch/list");
		
	}else {
		
		response.sendRedirect("/overwatch/list");
		
	}
	
	
	
	}

}
