package com.melon.admin.user.biz;

import java.util.List;

import com.melon.admin.user.vo.UserSearchVO;
import com.melon.admin.user.vo.UserVO;

public interface UserBiz {
	public boolean registNewUser(UserVO newUserVO);
	public List<UserVO> getAllUsers(UserSearchVO userSearchVO);
	public UserVO getOneUser(String userId);
	public UserVO getOneUser(UserVO userVO);
	public boolean updateUser(UserVO user);
	public boolean deleteOneUser(String userId);
	public boolean changeUser(String[] checkedUsers,String beforeAuthorization, String afterAuthorization);
}
