package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

@WebServlet({"/","/index"})	//url을 /과 /index로 대체한다.
public class IndexServlet extends HttpServlet {
	private EmployeesDao employeesDao;
	private DepartmentsDao departmentsDao;
	private DeptManagerDao deptManagerDao;
	private DeptEmpDao deptEmpDao;
	private SalariesDao salariesDao;
	private TitlesDao titlesDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/index URL 요청");
		
		this.employeesDao = new EmployeesDao();
		this.departmentsDao = new DepartmentsDao();
		this.deptManagerDao = new DeptManagerDao();
		this.deptEmpDao = new DeptEmpDao();
		this.salariesDao = new SalariesDao();
		this.titlesDao = new TitlesDao();
		
		
		int employeesRowCount = employeesDao.selectEmployeesCount();
		int departmentsRowCount = departmentsDao.selectDepartmentsCount();
		int deptManagerRowCount = deptManagerDao.selectDeptManagerCount();
		int deptEmpRowCount = deptEmpDao.selectDeptEmpDaoCount();
		int salariesRowCount = salariesDao.selectSalariesCount();
		int titlesRowCount = titlesDao.selectTitlesCount();
		int maxEmpNo = employeesDao.selectEmpNo("max");
		int minEmpNo = employeesDao.selectEmpNo("min");
		
		// /WEB-INF/views/index.jsp
		
		/*	
	 	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/index.jsp");
		rd.forward(request,response);
		*/
		
		//autoboxion, rappertype
		request.setAttribute("employeesRowCount", employeesRowCount);
		request.setAttribute("departmentsRowCount", departmentsRowCount);
		request.setAttribute("deptManagerRowCount", deptManagerRowCount);
		request.setAttribute("deptEmpRowCount", deptEmpRowCount);
		request.setAttribute("salariesRowCount", salariesRowCount);
		request.setAttribute("titlesRowCount", titlesRowCount);
		request.setAttribute("maxEmpNo", maxEmpNo);
		request.setAttribute("minEmpNo", minEmpNo);
		
		request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request,response);
	}

}