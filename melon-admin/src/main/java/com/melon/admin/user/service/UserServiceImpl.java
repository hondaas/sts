package com.melon.admin.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.melon.admin.authorization.biz.AuthorizationBiz;
import com.melon.admin.authorization.biz.AuthorizationBizImpl;
import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.user.biz.UserBiz;
import com.melon.admin.user.biz.UserBizImpl;
import com.melon.admin.user.vo.UserSearchVO;
import com.melon.admin.user.vo.UserVO;

public class UserServiceImpl implements UserService{
	
	private UserBiz userBiz;
	private AuthorizationBiz authorizationBiz;
	
	public UserServiceImpl() {
		userBiz = new UserBizImpl();
		authorizationBiz = new AuthorizationBizImpl();
	}
	
	
	
	@Override
	public boolean registNewUser(UserVO newUserVO) {
		return	userBiz.registNewUser(newUserVO);
	}

	@Override
	public List<UserVO> getAllUsers(UserSearchVO userSearchVO) {
		return userBiz.getAllUsers(userSearchVO);
	}

	@Override
	public UserVO getOneUser(String userId) {
		return userBiz.getOneUser(userId);
	}

	@Override
	public UserVO getOneUser(UserVO userVO) {
		return userBiz.getOneUser(userVO);
	}

	@Override
	public boolean updateUser(UserVO user) {
		UserVO tempUserVO = getOneUser(user.getUserId());
		
		if(user.getAuthorizationId() != null && user.getAuthorizationId().length() > 0) {
				tempUserVO.setAuthorizationId(user.getAuthorizationId());
		}
		
		if(user.getUserPoint() > 0) {
			tempUserVO.setUserPoint(user.getUserPoint());
		}
		
		if(user.getUserPassword() != null && user.getUserPassword().length() > 0) {
			tempUserVO.setUserPassword(user.getUserPassword());
		}
		
		return userBiz.updateUser(tempUserVO);
	}

	@Override
	public boolean deleteOneUser(String userId) {
		return userBiz.deleteOneUser(userId);
	}



	@Override
	public Map<String, Object> getOneUserWithAuthorizations(String userId) {
		
		AuthorizationSearchVO authorizationSearchVO = new AuthorizationSearchVO();
		authorizationSearchVO.getPager().setPageNumber(0);
		
		Map<String, Object> user = new HashMap<String, Object>();
		user.put("user", userBiz.getOneUser(userId));
		//System.out.println("test = " + userBiz.getOneUser(userId));
		user.put("authorizations", authorizationBiz.selectAllAuthorizations(authorizationSearchVO));
		//System.out.println("test = " + userBiz.getOneUser(userId));
		return user;
	}



	@Override
	public boolean changeUser(String[] checkedUsers, String beforeAuthorization, String afterAuthorization) {
		System.out.println("ser");
		return userBiz.changeUser(checkedUsers, beforeAuthorization, afterAuthorization);
	}

}
