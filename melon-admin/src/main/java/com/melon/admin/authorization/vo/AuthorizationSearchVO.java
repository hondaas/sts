package com.melon.admin.authorization.vo;

import com.melon.admin.common.web.pager.Pager;
import com.melon.admin.common.web.pager.PagerFactory;

public class AuthorizationSearchVO {

	private Pager pager;
	
	private String authorizationId;
	private String parentAuthorizationId;

	
	public Pager getPager() {
		if(pager == null) {
			pager = PagerFactory.getPager(Pager.ORACLE, 50, 10);
		}
		
		return pager;
	}
	
	public void setPager(Pager pager) {
		this.pager = pager;
	}


	public String getAuthorizationId() {
		return authorizationId;
	}

	public void setAuthorizationId(String authorizationId) {
		this.authorizationId = authorizationId;
	}

	public String getParentAuthorizationId() {
		return parentAuthorizationId;
	}

	public void setParentAuthorizationId(String parentAuthorizationId) {
		this.parentAuthorizationId = parentAuthorizationId;
	}
}
