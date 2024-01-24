package com.ryuuzakiumi.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ryuuzakiumi.dao.CommentDAO;
import com.ryuuzakiumi.dto.CommentDTO;
import com.ryuuzakiumi.util.Util;

@WebServlet("/comment")
public class Comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Comment() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		//오는 값 받기
		String commentcontent = request.getParameter("commentcontent");
		
		//오는 값에서 특수기호 <, > 를 변경하기
		commentcontent = Util.removeTag(commentcontent);
		
		//줄바꿈 처리해 주기.
		commentcontent = Util.addBR(commentcontent);
		
		
		
		String bno = request.getParameter("bno"); //board_no
		System.out.println(commentcontent + " : " + bno);
		
		//저장해 주세요.
		CommentDTO dto = new CommentDTO();
		
		dto.setBoard_no(Util.str2Int(bno));
		dto.setComment(commentcontent);
		dto.setMid((String) session.getAttribute("mid"));
		dto.setCip(Util.getIP(request));
		
		CommentDAO dao = new CommentDAO();
		int result  = dao.commentWrite(dto);
		System.out.println("처리 결과 : " + result);
		
		
		
		//이동해 주세요.
		
		if(result == 1) {
			response.sendRedirect("./detail?no=" + bno);
		} else {
			response.sendRedirect("./error.jsp");
		}
		
	}

}
