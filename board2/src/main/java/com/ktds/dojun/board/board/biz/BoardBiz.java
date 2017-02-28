package com.ktds.dojun.board.board.biz;

import com.ktds.dojun.board.vo.BoardVO;

import java.util.List;

/**
 * Created by Admin on 2017-02-17.
 */
public interface BoardBiz {

	public boolean writeArticle(BoardVO boardVO);

	public List<BoardVO> getAllArticles();

	public BoardVO getOneArticle(int articleNum);

	public boolean deleteOneArticle(int articleNum);

	public boolean updateOneArticle(BoardVO boardVO);

}
