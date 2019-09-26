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

import model.DeptManagerDao;


@WebServlet("/departments/getDeptManagerListByPage")
public class GetDeptManagerListByPageServlet extends HttpServlet {
	
private DeptManagerDao deptManagerDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//로그인 확인
		HttpSession session= request.getSession();
		System.out.println(session.getAttribute("login"));
		if(session.getAttribute("login") == null) {	//처음접속이거나 로그인을 안했을 때
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		deptManagerDao = new DeptManagerDao();
		
		int currentPage = 1;
		int rowPerPage = 5;
		int lastPage = 0;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		List<Map<String,Object>> list = deptManagerDao.selectDeptManagerByList(currentPage, rowPerPage);
		
		lastPage = deptManagerDao.deptManagerLastPage(rowPerPage);
		
		System.out.println(currentPage);
		System.out.println(rowPerPage);
		System.out.println(lastPage);
		
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("lastPage", lastPage);
		
		request.getRequestDispatcher("/WEB-INF/views/departments/deptManagerListByPage.jsp").forward(request, response);
	}

}
