package com.springbook.view.board;

import java.io.File;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springbook.biz.board.BoardListVO;
import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Controller
@SessionAttributes("board") // 세션에  board라고 저장된 객체가 있으면 사용하겠다.
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/dataTransform.do")
	@ResponseBody // 자바 객체를 Http 응답 프로토콜의 몸체로 변환하기 위해 사용()
				  // 메소드의 실행결과는 JSON으로 변환되어 HTTP 응답 보디에 설정된다. 
	public BoardListVO dataTransform(BoardVO vo){
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		List<BoardVO> boardList = boardService.getBoardList(vo);
		BoardListVO boardListVO = new BoardListVO();
		boardListVO.setBoardList(boardList);
		return boardListVO;
	}
	
	
	
	// 글 등록
	@RequestMapping(value = "/insertBoard.do")
	public String insertBoard(BoardVO vo) throws IOException{
		// 파일 업로드 처리
		MultipartFile uploadFile = vo.getUploadFile();
		if(!uploadFile.isEmpty()) {
			String fileName = uploadFile.getOriginalFilename();
			uploadFile.transferTo(new File("D:/" + fileName));
		}
		
		boardService.insertBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	// 글 수정
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board")BoardVO vo) {
		System.out.println("번호: " + vo.getSeq());
		System.out.println("제목: " + vo.getTitle());
		System.out.println("작성자: " + vo.getWriter());
		System.out.println("내용: " + vo.getContent());
		System.out.println("등록일: " + vo.getRegDate());
		System.out.println("조회수: " + vo.getCnt());
		boardService.updateBoard(vo);
		return "getBoardList.do";
	}
	
	// 글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {

		boardService.deleteBoard(vo);
		return "getBoardList.do";

	}

	// 글 상세 조회
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, Model model) {

		model.addAttribute("board", boardService.getBoard(vo)); // Model 정보 저장
		return "getBoard.jsp"; // View 정보 저장
		
	}

	// 검색 조건 목록 설정
	@ModelAttribute("conditionMap")
	public Map<String, String> searchConditionMap(){
		Map<String, String> conditionMap = new HashMap<String, String>();
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}
	
	
	// 글 목록 검색
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model) {
		System.out.println("글 목록 검색 처리");
		
		// Null Check
		if(vo.getSearchCondition() == null) vo.setSearchCondition("TITLE");
		if(vo.getSearchKeyword() == null) vo.setSearchKeyword("");
		
		// Model 정보 저장 
		model.addAttribute("boardList", boardService.getBoardList(vo)); // Model 정보 저장
		return "getBoardList.jsp"; // View 정보 저장
		
	}

}
