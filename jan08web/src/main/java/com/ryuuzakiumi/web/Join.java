package com.ryuuzakiumi.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ryuuzakiumi.dao.MemberDAO;
import com.ryuuzakiumi.dto.MemberDTO;

@WebServlet("/join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Join() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("join.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// id name pw1
		
		//db에 보내기
		MemberDTO dto = new MemberDTO();
		dto.setMid(request.getParameter("id"));
		dto.setMname(request.getParameter("name"));
		dto.setMpw(request.getParameter("pw1"));
		
		MemberDAO dao = new MemberDAO();
		
		int result = dao.join(dto);
	
		//정상적이면 로그인 페이지로
		//아니면 에러로
		
		if(result == 1) {
			response.sendRedirect("./login");
		} else {
			response.sendRedirect("./error.jsp");
		}
	}

}
