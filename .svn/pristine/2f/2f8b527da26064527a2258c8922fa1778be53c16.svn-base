package com.yunforge.base.service;

import java.util.List;

import com.yunforge.base.model.Division;

public interface DivisionManager {
	
	public List<Division> findAllDivOrderByNewOrder();

	public List<Division> queryCreateDivId(String createDivType);
	
	public Division findById(String id);
	
	public List<Division> findAll();

	public Division findByDivisionName(String divisionName);

	public List<Division> findChildByDivisionCodeIncludeSelf(String divisionCode);
	
	public List<Division> findByDivIdList(List<String> divIdList);
	
	public List<Division> findChildByDivisionCode(String divisionCode);

	public Division findByDivisionCode(String divisionCode);
	
	public boolean hasChildren(String pid);
	public List<Division> getChildrenPidRoles(String pid,List<String> roles);
	
	public List<Division> getChildren(String pid);

	public Division saveDivision(Division division);

	public void removeDivision(Division division);
	
	public List<Division> findSubDivision(String id);
	
	public List<Division> findXianQuDivision();
	
	public List<Division> findCityDivision();
}