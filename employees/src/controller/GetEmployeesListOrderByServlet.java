package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.EmployeesDao;
import vo.Employees;

@WebServlet("/employees/getEmployeesListOrderBy")
public class GetEmployeesListOrderByServlet extends HttpServlet {
	
	private EmployeesDao employeesDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//order의 값을 받아온다.
		String order = request.getParameter("order");
		
		System.out.println(order);
		
		employeesDao = new EmployeesDao();
		
		//ArrayList를 생성한다. 이 Arraylist의 들어갈 내용은 이름의 값을 출력할때 오름차순이나 내림차순으로 정렬하여 넣는다.
		List <Employees> list = new ArrayList<Employees>();
		list = employeesDao.selectEmployeesListOrderBy(order);
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/WEB-INF/views/employees/employeesListOrderBy.jsp").forward(request, response);
	}

}
