package com.ktds.dojun.board.user.dao;

import com.ktds.dojun.board.user.vo.UsersVO;

public interface UserDao {

	public int addUser(UsersVO usersVO);
	
	public UsersVO loginUser(UsersVO usersVO);
	
	
	
}
