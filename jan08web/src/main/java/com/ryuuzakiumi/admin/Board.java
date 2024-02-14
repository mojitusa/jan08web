package com.ryuuzakiumi.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ryuuzakiumi.dao.AdminDAO;
import com.ryuuzakiumi.dto.BoardDTO;
import com.ryuuzakiumi.util.Util;

@WebServlet("/admin/board")
public class Board extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Board() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("search : " + request.getParameter("search"));

		// 데이터
		AdminDAO dao = new AdminDAO();
		List<BoardDTO> list = dao.boardList();
		
		if(request.getParameter("search") != null && !request.getParameter("search").equals("")) {
			list = dao.boardList(request.getParameter("search"));
		} else {
			list = dao.boardList();
		}
		
		request.setAttribute("list", list);

		RequestDispatcher rd = request.getRequestDispatcher("/admin/board.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("no :" + request.getParameter("no"));
		System.out.println("del : " + request.getParameter("del"));

		AdminDAO dao = new AdminDAO();
		BoardDTO dto = new BoardDTO();

		dto.setNo(Util.str2Int(request.getParameter("no")));
		dto.setDel(Util.str2Int(request.getParameter("del")) == 1 ? 0 : 1);

		int result = dao.boardDel(dto);

		PrintWriter pw = response.getWriter();
		pw.print(result);
	}

}
