package com.ryuuzakiumi.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.ryuuzakiumi.dao.BoardDAO;
import com.ryuuzakiumi.dto.BoardDTO;
import com.ryuuzakiumi.util.Util;

@WebServlet("/update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Update() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if (session.getAttribute("mid") != null) {
			
			//세션이 있을 때 = 정상 작업하기
			int no = Util.str2Int(request.getParameter("no"));
			
			// DAO에 질의하기
			BoardDAO dao = new BoardDAO();
			BoardDTO dto = dao.detail(no);
			
			if (session.getAttribute("mid").equals(dto.getMid())) {
				
				// jsp에 보내기
				request.setAttribute("update", dto);
				
				RequestDispatcher rd = request.getRequestDispatcher("update.jsp");
				rd.forward(request, response);
				
			} else {
				
				response.sendRedirect("./error.jsp");
				
			}
			
		} else {
			response.sendRedirect("./login?login=nologin");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		if(request.getParameter("title") != null
				&& request.getParameter("content") != null
				&& Util.intCheck(request.getParameter("no"))
				&& session.getAttribute("mid") != null) {
			
			// 진짜 수정
			BoardDTO dto = new BoardDTO();
			dto.setTitle(request.getParameter("title"));
			dto.setContent(request.getParameter("content"));
			dto.setNo(Util.str2Int(request.getParameter("no")));
			dto.setMid((String) session.getAttribute("mid"));
			
			BoardDAO dao = new BoardDAO();
			int result = dao.update(dto);
			System.out.println("수정 결과 : " + result);
			
			
			response.sendRedirect("./detail?no=" + request.getParameter("no"));
		} else {
			//error
			response.sendRedirect("./error.jsp");
		}
			
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String no = request.getParameter("no");
		
		System.out.println(title);
		System.out.println(content);
		System.out.println(no);
		
	}

}
