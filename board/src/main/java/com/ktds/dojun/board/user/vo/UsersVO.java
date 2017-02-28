package com.ktds.dojun.board.user.vo;

import com.ktds.dojun.dao.support.annotation.Types;

public class UsersVO {

	
	@Types(alias="USR_ID")
	private String userId;
	@Types(alias="USR_NM")
	private String userName;
	@Types(alias="USR_PWD")
	private String userPassword;
	@Types(alias="JOIN_DTE")
	private String joinDate;

	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

}
