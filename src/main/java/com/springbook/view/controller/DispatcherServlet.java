package com.springbook.view.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;



public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
	
	// 스프링 컨테이너 만들어지고 초기화
	public void init() throws ServletException{ 
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		viewResolver.setPrefix("./");
		viewResolver.setSuffix(".jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 안 깨지게 인코딩 처리 추가
		request.setCharacterEncoding("EUC-KR");
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 1. 클라이언트의 요청 path 정보를 추출한다
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println("path명:"+path);
//		ㄴ 결과: path명:/getBoardList.do

		
		// 2. HandlerMapping을 통해 path에 해당하는 Controller를 검색한다.
		Controller ctrl = handlerMapping.getController(path);
		//로그인 컨트롤러를 받았다면 로그인 컨트롤러의 핸들리퀘스트(로그인에 해당하는 스트링을 리턴)
		
		// 3. 검색된 controller를 실행한다.
		String viewName = ctrl.handleRequest(request, response);
		System.out.println("viewName값:"+viewName);
       //ㄴ 결과: getBoardList

		
		// 4. ViewResolver를 통해 viewName에 해당하는 화면을 검색한다.
		String view = null;
		//.do로 끝나지 않는 경우 > .jsp로 가고
		if(!viewName.contains(".do")) {
			view = viewResolver.getView(viewName);
		} 
		// .do로 끝나는 경우 > .do 화면을 호출
		else {
			view = viewName;
		}
		
		// 5. 검색된 화면으로 이동한다
		response.sendRedirect(view);
		
	}
}
