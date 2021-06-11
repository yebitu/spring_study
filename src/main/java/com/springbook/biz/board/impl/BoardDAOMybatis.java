package com.springbook.biz.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

//DAO(Data Access Object)
@Repository
public class BoardDAOMybatis {
	@Autowired
	private SqlSessionTemplate mybatis;

	// CRUD  기능
	// insert 기능
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 insertBoard 기능 처리");
		mybatis.insert("BoardDAO.insertBoard", vo);
	}
	
	//update
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 updateBoard 기능 처리");
		mybatis.update("BoardDAO.updateBoard", vo);
	}
	
	//delete
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 deleteBoard 기능 처리");
		mybatis.delete("BoardDAO.deleteBoard", vo);
	}
	
	// 개별보기
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Mybatis로  getBoardList() 기능 처리");
		return (BoardVO) mybatis.selectOne("BoardDAO.getBoard", vo);
	}

	// 목록보기
	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> Mybatis로  getBoardList() 기능 처리");
		return mybatis.selectList("BoardDAO.getBoardList", vo);
	}

}
