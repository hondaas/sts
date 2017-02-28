package com.ktds.dojun.vo;

import java.sql.Blob;

public class UnitVO {

	private int unitId;
	private String unitName;
	private int unitDamage;
	private String unitType;
//	private byte[] unitImg;
	
	
	
	/*public byte[] getUnitImg() {
		return unitImg;
	}
	public void setUnitImg(byte[] unitImg) {
		this.unitImg = unitImg;
	}*/
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public int getUnitDamage() {
		return unitDamage;
	}
	public void setUnitDamage(int unitDamage) {
		this.unitDamage = unitDamage;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	
	
	
}
