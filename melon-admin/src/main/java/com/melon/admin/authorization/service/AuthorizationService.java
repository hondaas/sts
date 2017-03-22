package com.melon.admin.authorization.service;

import java.util.List;

import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.authorization.vo.AuthorizationVO;

public interface AuthorizationService {
	public	boolean insertNewAuthorization(AuthorizationVO AuthorizationVO);
	public int selectAllAuthorizationCount(AuthorizationSearchVO authorizationSearchVO);
	public List<AuthorizationVO> selectAllAuthorizations(AuthorizationSearchVO authorizationSearchVO);
	public boolean updateauthorizationInfo(AuthorizationVO authorizationSearchVO);
	public boolean deleteAuthorizationInfo(String authorizationId);
	public boolean changeAuthorization(String beforeAuthorizationId, String afterAuthorizationId);
}
