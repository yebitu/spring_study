package com.springbook.view.controller;

import java.util.HashMap;
import java.util.Map;

import com.springbook.view.user.LoginController;

public class HandlerMapping {
	private Map<String, Controller> mappings;
	
	
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		mappings.put("/login.do", new LoginController());
		mappings.put("/getBoardList.do", new GetBoardListController());
		mappings.put("/getBoard.do", new GetBoardController());
		mappings.put("/insertBoard.do", new InsertBoardController());
		mappings.put("/updateBoard.do", new UpdateBoardController());
		mappings.put("/deleteBoard.do", new DeleteBoardController());
		mappings.put("/logout.do", new LogoutController());


		
	}
	
	// 
	public Controller getController(String path) {
		return mappings.get(path);
		// mapping의 키값이 login.do, 밸류값이 로그인컨트롤러라 경로값인 path(/getBoardList.do)가 들어가면 매핑해둔 LoginController가 리턴됨 
	}

}
