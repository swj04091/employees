package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		//로그인 확인
		HttpSession session= request.getSession();
		System.out.println(session.getAttribute("login"));
		if(session.getAttribute("login") == null) {	//처음접속이거나 로그인을 안했을 때
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
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