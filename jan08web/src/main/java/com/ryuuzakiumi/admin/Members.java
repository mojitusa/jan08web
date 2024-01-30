package com.ryuuzakiumi.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ryuuzakiumi.dao.AdminDAO;
import com.ryuuzakiumi.dto.MemberDTO;
import com.ryuuzakiumi.util.Util;

@WebServlet("/admin/members")
public class Members extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Members() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		while (true) {
			System.out.println("리퀘스트 grade 값! : " + request.getParameter("grade"));

			List<MemberDTO> list = null;
			AdminDAO dao = new AdminDAO();

			if (request.getParameter("grade") == null) {
				list = dao.memberList();
			} else {

				if (request.getParameter("grade").isEmpty()) {
					response.sendRedirect("./members");
					break;

				} else {

					String str = request.getParameter("grade");
					list = dao.memberList(Util.str2Int(request.getParameter("grade")));
				}

			}

			request.setAttribute("list", list);
			// ("", ) 앞의 따옴표 안 의 이름으로 jsp에서 사용할 수 있음
			RequestDispatcher rd = request.getRequestDispatcher("/admin/members.jsp");
			// 지정된페이지로 제어를 전달
			rd.forward(request, response);
			// jsp로 제어를 보내고request를 호출해서 사용할 수 있음
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// db에 변경
		HttpSession session = request.getSession();
		int mgrade;

		System.out.println("아이디가 뭐야?" + session.getAttribute("mid"));

		int result = 0;

		MemberDTO dto = new MemberDTO();
		dto.setMno(Util.str2Int(request.getParameter("mno")));
		dto.setMgrade(Util.str2Int(request.getParameter("grade")));

		AdminDAO dao = new AdminDAO();
		mgrade = dao.findUserMgrade(session.getAttribute("mid"));
		System.out.println("로그인된 사용자의 등급이 뭐야?" + mgrade);

		if (session.getAttribute("mid") != null && mgrade == 9) {

			result = dao.membergradeUpdate(dto);

			// 페이지 이동

			if (request.getParameter("currentgrade") == null || request.getParameter("currentgrade").equals("")) {
				response.sendRedirect("./members");
			} else {
				response.sendRedirect("./members?grade=" + request.getParameter("currentgrade"));
			}
		} else {
			System.out.println("등급을 수정할 수 없습니다.");
			response.sendRedirect("./members");
		}

	}

}
