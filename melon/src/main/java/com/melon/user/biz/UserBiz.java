package com.melon.user.biz;

import com.melon.user.vo.UserVO;

public interface UserBiz {

	public boolean addNewUser(UserVO userVO);

	public UserVO getOneUser(UserVO userVO);

	public boolean managePoint(String userId, int point);
	
	public boolean isDuplicateUserId(String userId);

}
