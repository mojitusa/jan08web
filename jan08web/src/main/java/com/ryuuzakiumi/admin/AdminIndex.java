package com.ryuuzakiumi.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/index")
//url의 경로 - 실제 파일 위치와 상관 없음 호출하 url을 지정
public class AdminIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndex() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//화면에 정보 보여주는 녀석
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/admin.jsp");
															//파일 있는 경로
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
	}

}
