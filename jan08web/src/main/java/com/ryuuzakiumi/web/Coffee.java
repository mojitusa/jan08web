package com.ryuuzakiumi.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ryuuzakiumi.dao.BoardDAO;
import com.ryuuzakiumi.dao.CoffeeDAO;
import com.ryuuzakiumi.dto.CoffeeDTO;

@WebServlet("/coffee")
public class Coffee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Coffee() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//DAO랑 연결
		CoffeeDAO dao = new CoffeeDAO();
		List<CoffeeDTO> list = dao.vieworder();
		
		request.setAttribute("order", list);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("coffee.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		String hotOrCold = request.getParameter("hotorcold");
		String beverage = request.getParameter("beverage");
		String teaChoice = request.getParameter("teachoice");
		
		CoffeeDTO dto = new CoffeeDTO();
		dto.setHotOrCold(hotOrCold);
		dto.setBeverage(beverage);
		dto.setTeaChoice(teaChoice);
		
		CoffeeDAO dao = new CoffeeDAO();
		int result = dao.selectBeverage(dto);
		
		// 페이지 이동하기
		if (result == 1) {
			response.sendRedirect("./coffee");
		} else {
			response.sendRedirect("./error.jsp");
		}
		
		
	}

}
