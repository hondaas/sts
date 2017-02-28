package com.ktds.dojun.web;

import java.io.IOException;
import java.sql.Blob;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.dojun.biz.UnitBiz;
import com.ktds.dojun.biz.UnitBizImpl;
import com.ktds.dojun.vo.UnitVO;

public class DoCreateActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UnitBiz unitBiz;

	public DoCreateActionServlet() {

		unitBiz = new UnitBizImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		
		String unitName = request.getParameter("unitName");
		String unitDamageString = request.getParameter("unitDamage");
		String unitType = request.getParameter("unitType");
		

		int unitDamage = 0;
		try {
			unitDamage = Integer.parseInt(unitDamageString);
		} catch (NumberFormatException e) {
			throw new RuntimeException("잘못 입력 하셨습니다.");
		}

		UnitVO unitVO = new UnitVO();
		unitVO.setUnitName(unitName);
		unitVO.setUnitDamage(unitDamage);
		unitVO.setUnitType(unitType);


		if (unitBiz.addUnit(unitVO)) {

			response.sendRedirect("/overwatch/list");

		} else {

			response.sendRedirect("/overwatch/create");

		}

	}

}
