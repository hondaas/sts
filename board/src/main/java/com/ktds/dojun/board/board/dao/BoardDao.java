package com.ktds.dojun.board.board.dao;

import java.util.List;

import com.ktds.dojun.board.board.vo.BoardSearchVO;
import com.ktds.dojun.board.board.vo.BoardVO;

public interface BoardDao {

	public int getAllArticlesCount(BoardSearchVO boardSearchVO);

	public int insertArticle(BoardVO boardVO);

	public List<BoardVO> selectAllArticle(BoardSearchVO searchVO);

	public BoardVO selectOneArticle(int articleNum);

	public int deleteOneArticle(int articleNum);

	public int updateOneArticle(BoardVO boardVO);

}
