package com.ktds.dojun.board.board.biz;

import java.util.List;

import com.ktds.dojun.board.board.dao.BoardDao;
import com.ktds.dojun.board.board.dao.BoardDaoImpl;
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
        return boardDao.insertArticle(boardVO) > 0 ;
    }

    @Override
    public List<BoardVO> getAllArticles() {
        return boardDao.selectAllArticle();
    }

    @Override
    public BoardVO getOneArticle(int articleNum) {
        return boardDao.selectOneArticle( articleNum );
    }


	@Override
	public boolean deleteOneArticle(int articleNum) {
		return boardDao.deleteOneArticle(articleNum) > 0 ;
	}


	@Override
	public boolean updateOneArticle(BoardVO boardVO) {
		return boardDao.updateOneArticle(boardVO)>0;
	}

}


