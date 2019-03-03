package com.yunforge.base.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yunforge.base.model.DictVal;

public interface DictValDao extends JpaRepository<DictVal, String>,
		JpaSpecificationExecutor<DictVal> {

	public DictVal findByDictIdIsAndValCodeIs(String id, String valCode);
	
	public DictVal findByDictIdIsAndValNameIs(String id, String valName);

	public List<DictVal> findByDictIdIsOrderByWeightAsc(String id);

}
