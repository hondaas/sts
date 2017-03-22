package com.melon.admin.authorization.service;

import java.util.List;

import com.melon.admin.authorization.biz.AuthorizationBiz;
import com.melon.admin.authorization.biz.AuthorizationBizImpl;
import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.authorization.vo.AuthorizationVO;

public class AuthorizationServiceImpl implements AuthorizationService{

	private AuthorizationBiz authorizationBiz;
	
	public AuthorizationServiceImpl() {
		authorizationBiz = new AuthorizationBizImpl();
	}
	
	@Override
	public boolean insertNewAuthorization(AuthorizationVO AuthorizationVO) {
		return authorizationBiz.insertNewAuthorization(AuthorizationVO);
	}

	@Override
	public int selectAllAuthorizationCount(AuthorizationSearchVO authorizationSearchVO) {
		return authorizationBiz.selectAllAuthorizationCount(authorizationSearchVO);
	}

	@Override
	public List<AuthorizationVO> selectAllAuthorizations(AuthorizationSearchVO authorizationSearchVO) {
		return authorizationBiz.selectAllAuthorizations(authorizationSearchVO);
	}

	@Override
	public boolean updateauthorizationInfo(AuthorizationVO authorizationVO) {
		return authorizationBiz.updateauthorizationInfo(authorizationVO);
	}

	@Override
	public boolean deleteAuthorizationInfo(String authorizationSearchVO) {
		return authorizationBiz.deleteAuthorizationInfo(authorizationSearchVO);
	}

	@Override
	public boolean changeAuthorization(String beforeAuthorizationId, String afterAuthorizationId) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
