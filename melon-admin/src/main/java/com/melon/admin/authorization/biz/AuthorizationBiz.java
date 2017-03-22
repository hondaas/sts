package com.melon.admin.authorization.biz;

import java.util.List;

import com.melon.admin.authorization.vo.AuthorizationSearchVO;
import com.melon.admin.authorization.vo.AuthorizationVO;

public interface AuthorizationBiz {
	public	boolean insertNewAuthorization(AuthorizationVO AuthorizationVO);
	public int selectAllAuthorizationCount(AuthorizationSearchVO authorizationSearchVO);
	public List<AuthorizationVO> selectAllAuthorizations(AuthorizationSearchVO authorizationSearchVO);
	public boolean updateauthorizationInfo(AuthorizationVO authorizationVO);
	public boolean deleteAuthorizationInfo(String authorizationId);
}
