package com.ktds.dojun.board.board.vo;

import com.ktds.dojun.common.web.pager.Pager;
import com.ktds.dojun.dao.support.annotation.Types;

public class BoardSearchVO {
	@Types
	private Pager pager;

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
	
	

}
