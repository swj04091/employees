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

import model.DepartmentsDao;


@WebServlet("/departments/getDepartmentsCountByDeptNo")
public class GetDepartmentsCountByDeptNoServlet extends HttpServlet {
	
	private DepartmentsDao departmentsDao;	// DepartmentsDao 클래스에 참조되여있는 메소드를 사용하기 위해 DepartmentsDao타입으로 변수 하나를 생성하였다.
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//로그인 확인
		HttpSession session= request.getSession();
		System.out.println(session.getAttribute("login"));
		if(session.getAttribute("login") == null) {	//처음접속이거나 로그인을 안했을 때
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}		
		
		departmentsDao = new DepartmentsDao();	//departmentsDao에 DepartmentsDao() 메소드를 호출 했다.
		
		List<Map<String,Object>> list = departmentsDao.selectDepartmentsCountByDeptNo();
		//리턴을 받았던 데이터타입과 같게 list 변수를 만들었고 초기화된 값으로 departmentsDao에 참조 되어있는  selectDepartmentsCountByDeptNo()메소드를 호출했다.
		
		System.out.println(list);
		
		request.setAttribute("list", list);
		//호출된 메소드가 실행 되고 리턴을 받은 값을 request에 키 값형태로 저장한다.
		
		request.getRequestDispatcher("/WEB-INF/views/departments/departmentsCountByDeptNo.jsp").forward(request, response);
		//그리고 WEB-INF/departments/departmentsCountByDeptNo.jsp 로 페이지를 넘긴다.
	}

}
