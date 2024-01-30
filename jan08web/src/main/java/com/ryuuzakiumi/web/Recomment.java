package com.ryuuzakiumi.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ryuuzakiumi.dao.CommentDAO;
import com.ryuuzakiumi.dto.CommentDTO;
import com.ryuuzakiumi.util.Util;

@WebServlet("/recomment")
public class Recomment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Recomment() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		int result = 0;
		if (session.getAttribute("mid") != null && Util.intCheck2(request.getParameter("cno"))
				&& request.getParameter("comment") != null) {

			CommentDTO dto = new CommentDTO();
			dto.setMid((String) session.getAttribute("mid"));
			dto.setcno(Util.str2Int(request.getParameter("cno")));
			dto.setComment(Util.addBR(request.getParameter("comment")));

			CommentDAO dao = new CommentDAO();
			result = dao.commentUpdate(dto);
		}
		PrintWriter pw = response.getWriter();
		pw.print(result);

	}

}
