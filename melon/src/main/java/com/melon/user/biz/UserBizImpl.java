package com.melon.user.biz;

import com.melon.user.dao.UserDao;
import com.melon.user.dao.UserDaoImpl;
import com.melon.user.vo.UserVO;

public class UserBizImpl implements UserBiz {
	private UserDao userDao;

	public UserBizImpl() {
		userDao = new UserDaoImpl();
	}

	@Override
	public boolean addNewUser(UserVO userVO) {

		boolean isOk = userDao.insertNewUser(userVO) > 0;
		if (isOk) {
			managePoint(userVO.getUserId(), 300);

		}

		return isOk;

	}

	@Override
	public UserVO getOneUser(UserVO userVO) {
		UserVO loginUser = userDao.selectOneUser(userVO);
		
		if(loginUser != null){
		managePoint(userVO.getUserId(), 10);
		int point = loginUser.getUserPoint();
		loginUser.setUserPoint(point+10);
		}
		
		return loginUser;
	}

	@Override
	public boolean managePoint(String userId, int point) {
		return userDao.updatePoint(userId, point) > 0;
	}

	@Override
	public boolean isDuplicateUserId(String userId) {
		return userDao.selectCountByUserId(userId)>0;
	}

}
