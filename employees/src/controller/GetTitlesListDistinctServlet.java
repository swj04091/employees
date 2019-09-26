package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TitlesDao;

@WebServlet("/titles/getTitlesListDistinct")
public class GetTitlesListDistinctServlet extends HttpServlet {
	
	private TitlesDao titlesDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//로그인 확인
		HttpSession session= request.getSession();
		System.out.println(session.getAttribute("login"));
		if(session.getAttribute("login") == null) {	//처음접속이거나 로그인을 안했을 때
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		titlesDao = new TitlesDao();
		
		List<String> list = titlesDao.selectTitlesListDistinct();	//selectTitlesListDistinct메소드 호출
		
		System.out.println("servlet의 list: "+list);
	
		request.setAttribute("list", list);	//list라는 키 값으로(앞) list(뒤)를 저장한다
		request.getRequestDispatcher("/WEB-INF/views/titles/titlesListDistinct.jsp").forward(request, response);
	}

}
