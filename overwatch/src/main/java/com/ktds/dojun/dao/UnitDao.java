package com.ktds.dojun.dao;

import java.sql.SQLException;
import java.util.List;

import com.ktds.dojun.vo.UnitVO;

public interface UnitDao {
	
	public int addUnit(UnitVO uniVO);
	
	public List<UnitVO> getAllUnits();
	
	public UnitVO getOneUnit(int boardId);
	
	public int deleleUnit(int unitId);

	public int modifyUnit(UnitVO unitVO);
	
	public byte[] getImage(int uniId);

	
}
