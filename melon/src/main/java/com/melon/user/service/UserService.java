package com.melon.user.service;

import com.melon.user.vo.UserVO;

public interface UserService {

	public boolean addNewUser(UserVO userVO);

	public UserVO getOneUser(UserVO userVO);

	public boolean managePoint(String userId, int point);
	
	public boolean isDuplicateUserId(String userId);

	
}
