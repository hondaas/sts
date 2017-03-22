package com.melon.authorization.vo;

public class AuthorizationVO {

	private String authorizationId;
	private String parentAuthorizationId;
	private String authorizationName;

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

	public String getAuthorizationName() {
		return authorizationName;
	}

	public void setAuthorizationName(String authorizationName) {
		this.authorizationName = authorizationName;
	}

}
