package com.ktds.dojun.board.dao;

import com.ktds.dojun.board.vo.BoardVO;

import java.util.List;

public interface BoardDao {

public int insertArticle(BoardVO boardVO) ;

public List<BoardVO> selectAllArticle();

public BoardVO selectOneArticle(int articleNum);

public int deleteOneArticle(int articleNum);

public int updateOneArticle(BoardVO boardVO);

}

