package com.yunforge.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.base.dao.DictTypeDao;
import com.yunforge.base.model.DictType;
import com.yunforge.base.service.DictTypeManager;

@Scope("prototype")
@Service
@Transactional(readOnly = true)
public class DictTypeManagerImpl implements DictTypeManager {

	@Autowired
	private DictTypeDao dictTypeDao;

	@Override
	public DictType findById(String id) {
		return dictTypeDao.findOne(id);
	}

	@Override
	public DictType findByTypeName(String typeName) {
		return dictTypeDao.findByTypeName(typeName);
	}

	@Override
	public DictType findByTypeCode(String typeCode) {
		return dictTypeDao.findByTypeCode(typeCode);
	}

	@Override
	public DictType saveDictType(DictType dictType) {
		return dictTypeDao.saveAndFlush(dictType);
	}

	@Override
	public void removeDictType(DictType dictType) {
		dictTypeDao.delete(dictType);

	}

	@Override
	public List<DictType> findAll() {
		Sort sort = new Sort(Direction.ASC, "weight");
		return dictTypeDao.findAll(sort);
	}

}
