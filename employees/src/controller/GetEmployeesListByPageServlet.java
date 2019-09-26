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


@WebServlet("/employees/getEmployeesListByPage")
public class GetEmployeesListByPageServlet extends HttpServlet {
	
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
		int currentPage = 1;	//현재 페이지
		int rowPerPage = 10;	//페이지당 보여줄 데이터의 갯수
		
		//currentPage 변수가 값을 가지고 있으면 그 값을 저장한다.
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		System.out.println(currentPage);
		
		//employeesDao에 참조된 selectLastPage(rowPerPage)메소드 호출(마지막 페이지 구하는 메소드)
		int lastPage =  employeesDao.selectLastPage(rowPerPage);
		System.out.println(lastPage);
		
		//list로 저장한 employees데이터를 호출함.
		List<Employees>list = employeesDao.selectEmployeesListByPage(currentPage, rowPerPage);
		System.out.println(list);
		
		//list, currentPage, lastPage를 request안에 저장한다.
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		
		request.getRequestDispatcher("/WEB-INF/views/employees/employeesListByPage.jsp").forward(request, response);
	}

}
