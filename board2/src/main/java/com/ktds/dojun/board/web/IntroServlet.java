package com.ktds.dojun.board.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ktds.dojun.vo.IntroVO;


public class IntroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public IntroServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<IntroVO> introList = new ArrayList<IntroVO>();
		
		
		IntroVO introduce = new IntroVO();
		introduce.setName("김도준");
		introduce.setAge(2);
		introduce.setHome("서울시");
		introList.add(introduce);
		
		introduce = new IntroVO();
		introduce.setName("김도준");
		introduce.setAge(2);
		introduce.setHome("서울시");
		introList.add(introduce);
		
		introduce = new IntroVO();
		introduce.setName("김도준");
		introduce.setAge(2);
		introduce.setHome("서울시");
		introList.add(introduce);
		
		request.setAttribute("introduce", introduce);

		request.setAttribute("introList", introList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/me.jsp");
		dispatcher.forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
