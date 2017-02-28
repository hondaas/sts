package com.ktds.dojun.biz;

import java.util.List;

import com.ktds.dojun.vo.UnitVO;

public interface UnitBiz {

	
	public boolean addUnit(UnitVO unitVO);
	
	public List<UnitVO> printAllUnits();
	
	public UnitVO getOneUnit(int unitId);
	
	public boolean deleteUnit(int unitId);
	
	public boolean modifyUnit(UnitVO unitVO);
	
	public byte[] getImage(int unitId);
	
	
	
	
}
