package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DepartmentsDao;
import vo.Departments;

@WebServlet("/departments/getDepartmentsList")	//url을 /departments/getDepartmentsList로 대체한다.
public class GetDepartmentsListServlet extends HttpServlet {
	private DepartmentsDao departmentsDao;	//DepartmentsDao를 호출 한다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		departmentsDao = new DepartmentsDao();
		List<Departments> list = departmentsDao.selectDepartmentsList();
		//호출한 departmentsDao에 참조된 selectDepartmentsList()를 List로 저장한다.
		
		request.setAttribute("list", list);
		// request에 추가적으로 값을 넣어서 보낸다.
		request.getRequestDispatcher("/WEB-INF/views/departments/departmentsList.jsp").forward(request, response);
		//그리고 /WEB-INF/views/departments/departmentsList.jsp로 보내주고 request의 값도 보내준다.
		
	}

}
