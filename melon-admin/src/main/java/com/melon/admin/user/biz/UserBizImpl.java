package com.melon.admin.user.biz;

import java.util.ArrayList;
import java.util.List;

import com.melon.admin.user.dao.UserDao;
import com.melon.admin.user.dao.UserDaoImpl;
import com.melon.admin.user.vo.UserSearchVO;
import com.melon.admin.user.vo.UserVO;

public class UserBizImpl implements UserBiz {

	private UserDao userDao;

	public UserBizImpl() {
		userDao = new UserDaoImpl();
	}

	@Override
	public boolean registNewUser(UserVO newUserVO) {
		return userDao.insertNewUser(newUserVO) > 0;
	}

	@Override
	public List<UserVO> getAllUsers(UserSearchVO userSearchVO) {

		int totalCount = userDao.selectAllUserCount(userSearchVO);
		// System.out.println("totalCount = " + totalCount);
		userSearchVO.getPager().setTotalArticleCount(totalCount);

		System.out.println("yes = " + userSearchVO.getPager().getEndArticleNumber());

		if (totalCount == 0) {
			return new ArrayList<UserVO>();
		}

		return userDao.selectAllUser(userSearchVO);
	}

	@Override
	public UserVO getOneUser(String userId) {
		return userDao.selectOneUser(userId);
	}

	@Override
	public UserVO getOneUser(UserVO userVO) {
		return userDao.selectOneUser(userVO);
	}

	@Override
	public boolean deleteOneUser(String userId) {
		return userDao.deleteOneUser(userId) > 0;
	}

	@Override
	public boolean updateUser(UserVO user) {
		return userDao.updateUserInfo(user) > 0;
	}

	@Override
	public boolean changeUser(String[] checkedUsers, String beforeAuthorization, String afterAuthorization) {
		
		if (checkedUsers != null && checkedUsers.length > 0) {
			
			int isSuccess = 0;
			
			for (int i = 0; i < checkedUsers.length; i++) {

				isSuccess = userDao.changeUser(checkedUsers[i], beforeAuthorization, afterAuthorization);

			}
			return isSuccess > 0;

		} else {

			String checkedUserId = null;

			return userDao.changeUser(checkedUserId, beforeAuthorization, afterAuthorization) > 0;

		}

	}

}
