package com.yunforge.base.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.base.model.Parameter;
import com.yunforge.base.model.Role;

public interface ParameterManager {

	public Parameter findParameterById(String id);

	public Parameter findParameterByParaName(String paraName);

	public List<Parameter> findAll();

	public Page<Parameter> findAll(Pageable pageable);

	public List<Parameter> findAll(String[] ids);

	public Parameter saveParameter(Parameter parameter);

	public Page<Parameter> search(String searchField, String searchOper,
			String searchString, Pageable pageable);

	public String getMaxParaCode();
	
	public void removeParameter(Parameter parameter);

}