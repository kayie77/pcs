package com.yunforge.base.service.impl;

import static com.yunforge.base.dao.ParameterSpecifications.searchConfigs;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.base.dao.ParameterDao;
import com.yunforge.base.model.Parameter;
import com.yunforge.base.service.ParameterManager;

@Scope("prototype")
@Service
@Transactional(readOnly = true)
public class ParameterManagerImpl implements ParameterManager {

	@Autowired
	private ParameterDao parameterDao;

	@Override
	public Parameter findParameterById(String id) {
		return parameterDao.findOne(id);
	}

	@Override
	public Parameter findParameterByParaName(String paraName) {
		return parameterDao.findByParaName(paraName);
	}

	@Override
	public List<Parameter> findAll() {
		List<Parameter> parameters = this.parameterDao.findAll();
		return parameters;
	}
	
	@Override
	public String getMaxParaCode() {
		String res = "";
		Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "id"));
		List<Parameter> parameters = this.parameterDao.findAll(sort);
		if(parameters ==null || parameters.size()<=0){
			return res;
		}
		Parameter parameter = parameters.get(0);
		int paraCode = Integer.parseInt(parameter.getParaCode())+1;
		res += paraCode;
		return res;
	}

	@Override
	public Page<Parameter> findAll(Pageable pageable) {
		Page<Parameter> parameters = this.parameterDao.findAll(pageable);
		return parameters;
	}

	@Override
	public List<Parameter> findAll(String[] ids) {
		List<String> idList = Arrays.asList(ids);
		return parameterDao.findAll(idList);
	}

	@Override
	@Transactional
	public Parameter saveParameter(Parameter parameter) {
		return parameterDao.save(parameter);
	}

	@Override
	public Page<Parameter> search(String searchField, String searchOper,
			String searchString, Pageable pageable) {
		return parameterDao.findAll(
				searchConfigs(searchField, searchOper, searchString), pageable);
	}

	@Override
	public void removeParameter(Parameter parameter) {
		parameterDao.delete(parameter);
	}

}
