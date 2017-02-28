package com.ktds.dojun.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.dojun.biz.UnitBiz;
import com.ktds.dojun.biz.UnitBizImpl;
import com.ktds.dojun.vo.UnitVO;

public class ViewModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UnitBiz unitBiz;
	
	
    public ViewModifyServlet() {
    	unitBiz = new UnitBizImpl();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setCharacterEncoding("UTF-8");
	
	String unitIdString = request.getParameter("unitId");
	
	int unitId = Integer.parseInt(unitIdString);
	
	UnitVO unitVO = unitBiz.getOneUnit(unitId);

	request.setAttribute("unitVO", unitVO);
	
	RequestDispatcher dispatcher = request.getRequestDispatcher("/modify.jsp");
	dispatcher.forward(request, response);

	}

}
