package com.ktds.dojun.board.user.biz;

import com.ktds.dojun.board.user.vo.UsersVO;

public interface UserBiz {

	public boolean signUpUser(UsersVO usersVO);
	
	public UsersVO loginUser(UsersVO usersVO);
	
}
