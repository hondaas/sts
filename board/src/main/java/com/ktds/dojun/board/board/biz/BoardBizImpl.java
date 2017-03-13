package com.ktds.dojun.board.board.biz;

import java.util.ArrayList;
import java.util.List;

import com.ktds.dojun.board.board.dao.BoardDao;
import com.ktds.dojun.board.board.dao.BoardDaoImpl;
import com.ktds.dojun.board.board.vo.BoardSearchVO;
import com.ktds.dojun.board.board.vo.BoardVO;

/**
 * Created by Admin on 2017-02-17.
 */
public class BoardBizImpl implements BoardBiz {

	private BoardDao boardDao;

	public BoardBizImpl() {

		this.boardDao = new BoardDaoImpl();

	}

	@Override
	public boolean writeArticle(BoardVO boardVO) {
		return boardDao.insertArticle(boardVO) > 0;
	}

	@Override
	public List<BoardVO> getAllArticles(BoardSearchVO searchVO) {

		// TODO 게시글의 개수를 조회해 Pager에 할당한다.
		// 게시글의 개수가 0이라면, 비어있는 리스트를 리턴하고
		// 그렇지 않다면 getAllArticles를 리턴한다.

		int articleCount = boardDao.getAllArticlesCount(searchVO);
		searchVO.getPager().setTotalArticleCount(articleCount);

		if (articleCount == 0) {
			return new ArrayList<BoardVO>();
		} else {
			return boardDao.selectAllArticle(searchVO);

		}
	}

	@Override
	public BoardVO getOneArticle(int articleNum) {
		return boardDao.selectOneArticle(articleNum);
	}

	@Override
	public boolean deleteOneArticle(int articleNum) {
		return boardDao.deleteOneArticle(articleNum) > 0;
	}

	@Override
	public boolean updateOneArticle(BoardVO boardVO) {
		return boardDao.updateOneArticle(boardVO) > 0;
	}

}
