package com.ryuuzakiumi.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ryuuzakiumi.dao.AdminDAO;
import com.ryuuzakiumi.dto.BoardDTO;

@WebServlet("/admin/ip")
public class Ip extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ip() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println(request.getParameter("ip"));
		
		AdminDAO dao = new AdminDAO();
		
		
		//List<Map<String, Object>> often = dao.mostOften();
		request.setAttribute("often", dao.mostOften());
		
		List<Map<String, Object>> list =null;
		
		if (request.getParameter("ip")!= null && !request.getParameter("ip").equals("")) {
			list = dao.ipList(request.getParameter("ip"));
		} else {
			list = dao.ipList();
		}
		request.setAttribute("list", list);
		

		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/ip.jsp"); //파일 경로
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		
	}

}
