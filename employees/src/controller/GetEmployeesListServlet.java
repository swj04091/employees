package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmployeesDao;
import vo.Employees;

//url은 getEmployeesList로 받는다.
@WebServlet("/employees/getEmployeesList")
public class GetEmployeesListServlet extends HttpServlet {
	
	private EmployeesDao employeesDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//로그인 확인
		HttpSession session= request.getSession();
		System.out.println(session.getAttribute("login"));
		if(session.getAttribute("login") == null) {	//처음접속이거나 로그인을 안했을 때
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}	
		
		int limit = 10;
		
		//limit의 값이 null아면 10으로 넣어주고, 그렇지 많다면 limit만큼 넣어준다.
		if(request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
			System.out.println("Page Limit: "+limit);
		}
		
		//employyesDao를 호출받는다.
		employeesDao = new EmployeesDao();
		
		List<Employees> list = employeesDao.selectEmployeesList(limit);
		
		System.out.println("servlet내 list: "+list);
		
		//request로 값을 넘길 때 list도 같이 넘겨준다.
		request.setAttribute("list", list);
		request.getRequestDispatcher("/WEB-INF/views/employees/employeesList.jsp").forward(request, response);
		
	}

}
