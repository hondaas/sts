package com.melon.admin.authorization.biz;

import java.util.ArrayList;
import java.util.List;

import com.melon.admin.authorization.dao.AuthorizationDao;
import com.melon.admin.authorization.dao.AuthorizationDaoImpl;
import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.authorization.vo.AuthorizationVO;
import com.melon.admin.common.web.pager.Pager;

public class AuthorizationBizImpl implements AuthorizationBiz{

	private AuthorizationDao authorizationDao;
	
	public AuthorizationBizImpl() {
		authorizationDao = new AuthorizationDaoImpl();
	}
	
	@Override
	public boolean insertNewAuthorization(AuthorizationVO authorizationVO) {
		String id = authorizationDao.generateNewAuthorizationId();
		
		System.out.println("id = " + id);
		
		authorizationVO.setAuthorizationId(id);
		return authorizationDao.insertNewAuthorization(authorizationVO) > 0;
	}

	@Override
	public int selectAllAuthorizationCount(AuthorizationSearchVO authorizationSearchVO) {
		return 0;
	}

	@Override
	public List<AuthorizationVO> selectAllAuthorizations(AuthorizationSearchVO authorizationSearchVO) {
		int authorizationCount = authorizationDao.selectAllAuthorizationCount(authorizationSearchVO);
		
		authorizationSearchVO.getPager().setTotalArticleCount(authorizationCount);
		
		System.out.println("no.. = " + authorizationSearchVO.getPager().getEndArticleNumber());
		
		if(authorizationCount == 0) {
			return new ArrayList<AuthorizationVO>();
		}
		
		return authorizationDao.selectAllAuthorizations(authorizationSearchVO);
	}

	@Override
	public boolean updateauthorizationInfo(AuthorizationVO authorizationVO) {
		return authorizationDao.updateauthorizationInfo(authorizationVO) > 0;
	}

	@Override
	public boolean deleteAuthorizationInfo(String authorizationId) {
		return authorizationDao.deleteAuthorizationInfo(authorizationId) > 0;
	}

}
