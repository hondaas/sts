package com.ktds.dojun.biz;

import java.util.List;

import com.ktds.dojun.dao.UnitDao;
import com.ktds.dojun.dao.UnitDaoImpl;
import com.ktds.dojun.vo.UnitVO;

public class UnitBizImpl implements UnitBiz {

	private UnitDao unitDao;

	public UnitBizImpl() {

		this.unitDao = new UnitDaoImpl();

	}

	@Override
	public boolean addUnit(UnitVO unitVO) {
		return unitDao.addUnit(unitVO) > 0 ;
	}

	@Override
	public List<UnitVO> printAllUnits() {
		return unitDao.getAllUnits();
	}

	@Override
	public boolean deleteUnit(int unitId) {
		return unitDao.deleleUnit(unitId) > 0;
	}

	@Override
	public boolean modifyUnit(UnitVO unitVO) {
		return unitDao.modifyUnit(unitVO) > 0;
	}

	@Override
	public UnitVO getOneUnit(int unitId) {
		return unitDao.getOneUnit(unitId);
	}

	@Override
	public byte[] getImage(int unitId) {
		return unitDao.getImage(unitId);
	}

}
