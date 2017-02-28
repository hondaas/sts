package com.ktds.dojun.board.user.biz;

import com.ktds.dojun.board.user.dao.UserDao;
import com.ktds.dojun.board.user.dao.UserDaoImpl;
import com.ktds.dojun.board.user.vo.UsersVO;

public class UserBizImpl implements UserBiz {

	private UserDao userDao;
	
	public UserBizImpl() {
		
		userDao= new UserDaoImpl();
	}

	@Override
	public boolean signUpUser(UsersVO usersVO) {

		return userDao.addUser(usersVO)>0;
	}

	@Override
	public UsersVO loginUser(UsersVO usersVO) {
		return userDao.loginUser(usersVO);
	}
}
