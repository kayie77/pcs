package com.yunforge.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yunforge.base.model.DictType;

public interface DictTypeDao extends JpaRepository<DictType, String>,
		JpaSpecificationExecutor<DictType> {

	public DictType findByTypeCode(String typeCode);

	public DictType findByTypeName(String typeName);
	
}
