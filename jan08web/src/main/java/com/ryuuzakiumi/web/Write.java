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

@WebServlet("/write")
public class Write extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Write() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		HttpSession session = request.getSession();

		if (session.getAttribute("mname") == null) {
			response.sendRedirect("./login"); // url까지 변경해서 화면 보여 줍니다.
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("write.jsp"); // url 고정 화면만 변환
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 한글처리
		request.setCharacterEncoding("UTF-8");

		// 세션에 들어 있는 mid 가져오기
		HttpSession session = request.getSession();

		// if문으로 로그인 되어 있는(=세션이 있는) 사람만 로직 수행하도록 변경
		if (session.getAttribute("mid") == null || session.getAttribute("mname") == null) {
			// 로그인하지 않았다면 login으로 가게 하겠습니다.
			response.sendRedirect("./login?login=nologin");

		} else {

			String title = request.getParameter("title");
			String content = request.getParameter("content");

			// 태그 특수기호 변경하기
			title = Util.removeTag(title);

//			System.out.println(title);
//			System.out.println(content);

			// DAO에 write 메소드 만들기
			BoardDTO dto = new BoardDTO();
			dto.setTitle(title);
			dto.setContent(content);
			dto.setMid((String) session.getAttribute("mid"));
			// 세션은 다 오브젝트로 처리한다. 그래서 캐스트 필요
			dto.setIp(Util.getIP(request));

			BoardDAO dao = new BoardDAO();
			int result = dao.write(dto);
//			System.out.println("글쓰기 결과는 : " + result);

			// 페이지 이동하기
			if (result == 1) {
				response.sendRedirect("./board");
			} else {
				response.sendRedirect("./error.jsp");
			}

		}

	}

}
