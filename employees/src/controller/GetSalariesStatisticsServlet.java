package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SalariesDao;


@WebServlet("/salaries/getSalariesStatistics")
public class GetSalariesStatisticsServlet extends HttpServlet {
	private SalariesDao salariesDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//로그인 확인
		HttpSession session= request.getSession();
		System.out.println(session.getAttribute("login"));
		if(session.getAttribute("login") == null) {	//처음접속이거나 로그인을 안했을 때
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}	
		
		salariesDao = new SalariesDao();
		Map<String, Long> map = salariesDao.selectSalariesStatistics();
		
		System.out.println(map);
		
		request.setAttribute("map", map);
		request.getRequestDispatcher("/WEB-INF/views/salaries/salariesStatistics.jsp").forward(request, response);
	}

}
