package com.ktds.dojun.web;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.dojun.biz.UnitBiz;
import com.ktds.dojun.biz.UnitBizImpl;
import com.ktds.dojun.vo.UnitVO;

public class ViewListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UnitBiz unitBiz;

       
    public ViewListServlet() {
    	unitBiz = new UnitBizImpl();
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		List<UnitVO> unitList = unitBiz.printAllUnits();

		request.setAttribute("unitList", unitList);
		

		RequestDispatcher dispatcher = request.getRequestDispatcher("/list.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
