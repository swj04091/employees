package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmployeesDao;


@WebServlet("/employees/getEmployeesCountGender")
public class GetEmployeesCountGender extends HttpServlet {
	
	private EmployeesDao employeesDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//로그인 확인
		HttpSession session= request.getSession();
		System.out.println(session.getAttribute("login"));
		if(session.getAttribute("login") == null) {	//처음접속이거나 로그인을 안했을 때
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}	
		
		employeesDao = new EmployeesDao();
		
		List<Map<String,Object>>list = employeesDao.selectEmployeesCountGroupByGender();
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/employees/employeesCountGender.jsp").forward(request, response);
	}

}
