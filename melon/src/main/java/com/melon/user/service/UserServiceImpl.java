package com.melon.user.service;

import com.melon.user.biz.UserBiz;
import com.melon.user.biz.UserBizImpl;
import com.melon.user.vo.UserVO;

public class UserServiceImpl implements UserService {

	private UserBiz userBiz;

	public UserServiceImpl() {

		userBiz = new UserBizImpl();
	}

	@Override
	public boolean addNewUser(UserVO userVO) {
		return userBiz.addNewUser(userVO);
	}

	@Override
	public UserVO getOneUser(UserVO userVO) {
		return userBiz.getOneUser(userVO);
	}

	@Override
	public boolean managePoint(String userId, int point) {
		return userBiz.managePoint(userId, point);
	}

	@Override
	public boolean isDuplicateUserId(String userId) {
		return userBiz.isDuplicateUserId(userId);
	}

}
