package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EmployeesDao;
import vo.Employees;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
	private EmployeesDao employeesDao;	//메소드의 컨테이너 성격
	
	//Login Form을 위한 메소드
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}
	
	//Login Action을 위한 메소드
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		int empNo = Integer.parseInt(request.getParameter("empNo"));
		// 디버깅 -> 단위테스트
		
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(empNo);
		
		Employees employees = new Employees();
		employees.setFirstName(firstName);
		employees.setLastName(lastName);
		employees.setEmpNo(empNo);
		
		employeesDao = new EmployeesDao();
		int login = employeesDao.login(employees);
		
		System.out.println(login);
		
		HttpSession session = request.getSession();
		
		if(login == employees.getEmpNo()) {
		System.out.println("로그인 성공");
		session.setAttribute("login",login);
		response.sendRedirect(request.getContextPath()+"/");
		}else {
			System.out.println("로그인 실패");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		}
	}

}