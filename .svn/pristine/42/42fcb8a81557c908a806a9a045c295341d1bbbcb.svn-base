package com.yunforge.base.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yunforge.base.model.Dict;

public interface DictDao extends JpaRepository<Dict, String>,
		JpaSpecificationExecutor<Dict> {

	public Dict findByDictCode(String dictCode);

	public Dict findByDictName(String dictName);
	
	public List<Dict> findByDictTypeIdIsOrderByWeightAsc(String id);
	
}
