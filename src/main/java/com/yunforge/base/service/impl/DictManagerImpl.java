package com.yunforge.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.yunforge.base.dao.DictValSpecifications.findDictVals;
import com.yunforge.base.dao.DictDao;
import com.yunforge.base.dao.DictValDao;
import com.yunforge.base.model.Dict;
import com.yunforge.base.model.DictVal;
import com.yunforge.base.service.DictManager;

@Scope("prototype")
@Service
@Transactional(readOnly = true)
public class DictManagerImpl implements DictManager {

	@Autowired
	private DictDao dictDao;

	@Autowired
	private DictValDao dictValDao;

	@Override
	public Dict findDictById(String id) {
		return dictDao.findOne(id);
	}

	@Override
	public Dict findDictByDictCode(String dictCode) {
		return dictDao.findByDictCode(dictCode);
	}

	@Override
	public Dict findDictByDictName(String dictName) {
		return dictDao.findByDictCode(dictName);
	}

	@Override
	public List<Dict> findDictsByDictType(String typeId) {
		return dictDao.findByDictTypeIdIsOrderByWeightAsc(typeId);
	}

	@Override
	public Dict saveDict(Dict dict) {
		return dictDao.saveAndFlush(dict);
	}

	@Override
	public void removeDict(Dict dict) {
		dictDao.delete(dict);

	}

	@Override
	public DictVal findDictValById(String valId) {
		return dictValDao.findOne(valId);
	}

	@Override
	public DictVal findDictValByDictAndValCode(String dictId, String valCode) {
		return dictValDao.findByDictIdIsAndValCodeIs(dictId, valCode);
	}

	@Override
	public DictVal findDictValByDictAndValName(String dictId, String valName) {
		return dictValDao.findByDictIdIsAndValNameIs(dictId, valName);
	}

	@Override
	public List<DictVal> findDictValsByDictId(String dictId) {
		return dictValDao.findByDictIdIsOrderByWeightAsc(dictId);
	}

	@Override
	public Page<DictVal> listDictVals(String dictId, String searchField,
			String searchOper, String searchString, Pageable pageable) {
		return dictValDao.findAll(
				findDictVals(dictId, searchField, searchOper, searchString),
				pageable);
	}

	@Override
	public DictVal saveDictVal(DictVal dictVal) {
		return dictValDao.saveAndFlush(dictVal);
	}

	@Override
	public void removeDictVal(DictVal dictVal) {
		dictValDao.delete(dictVal);
	}

}
