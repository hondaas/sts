package com.ktds.dojun.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.dojun.biz.UnitBiz;
import com.ktds.dojun.biz.UnitBizImpl;
import com.ktds.dojun.vo.UnitVO;

public class DoModifyActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UnitBiz unitBiz;
	
    public DoModifyActionServlet() {
    	unitBiz = new UnitBizImpl();
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("UTF-8");
		
		String unitName = request.getParameter("unitName");
		String unitDamageString =request.getParameter("unitDamage");
		String unitType =request.getParameter("unitType");
		String unitIdString =request.getParameter("unitId");

			int unitId = Integer.parseInt(unitIdString);
			int unitDamage = Integer.parseInt(unitDamageString);
	
		
		
		UnitVO unitVO = new UnitVO();
		unitVO.setUnitId(unitId);
		unitVO.setUnitName(unitName);
		unitVO.setUnitDamage(unitDamage);
		unitVO.setUnitType(unitType);
		
		
		if (unitBiz.modifyUnit(unitVO)){
			
			response.sendRedirect("/overwatch/list");
			
		}
		
		else{
			response.sendRedirect("/overwatch/modify");
		}
		
	}
	}


