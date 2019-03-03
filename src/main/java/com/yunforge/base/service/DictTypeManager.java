package com.yunforge.base.service;

import java.util.List;

import com.yunforge.base.model.DictType;

public interface DictTypeManager {

	public DictType findById(String id);

	public DictType findByTypeName(String typeName);

	public DictType findByTypeCode(String typeCode);
	
	public List<DictType> findAll();

	public DictType saveDictType(DictType dictType);

	public void removeDictType(DictType dictType);

}