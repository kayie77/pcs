package com.yunforge.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yunforge.base.model.Parameter;

public interface ParameterDao extends JpaRepository<Parameter, String>,
		JpaSpecificationExecutor<Parameter> {

	public Parameter findByParaCode(String paraCode);
	
	public Parameter findByParaName(String paraName);
}
