package com.yunforge.cms.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.cms.dao.InfoCatDao;
import com.yunforge.cms.model.InfoCat;
import com.yunforge.cms.service.InfoCatManager;

@Scope("prototype")
@Service
@Transactional(readOnly = true)
public class InfoCatManagerImpl implements InfoCatManager {

	@Autowired
	private InfoCatDao infoCatDao;

	@Override
	public InfoCat findById(String id) {
		return infoCatDao.findOne(id);
	}

	@Override
	public List<InfoCat> getChildren(String pid) {
		if (StringUtils.isNotBlank(pid)) {
			return infoCatDao.findByParentIdIsOrderByWeightAsc(pid);
		} else {
			return infoCatDao.findByParentIsNullOrderByWeightAsc();
		}

	}

	@Override
	public boolean hasChildren(String pid) {
		return infoCatDao.countByParentIdIs(pid).intValue() > 0;
	}

	@Override
	public Page<InfoCat> findAll(Pageable pageable) {
		return infoCatDao.findAll(pageable);
	}

	@Override
	public List<InfoCat> findAll(String[] ids) {
		List<String> idList = Arrays.asList(ids);
		return infoCatDao.findAll(idList);
	}

	@Override 
	@Transactional
	public InfoCat saveInfoCat(InfoCat infoCat) {
		return infoCatDao.save(infoCat);
	}

	@Override
	@Transactional
	public void removeInfoCat(InfoCat infoCat) {
		infoCatDao.delete(infoCat);
	}

	@Override
	@Transactional
	public void removeInfoCats(List<InfoCat> infoCats) {
		infoCatDao.delete(infoCats);
	}

	@Override
	@Transactional
	public void removeInfoCats(String[] ids) {
		List<InfoCat> artList = findAll(ids);
		infoCatDao.delete(artList);
	}

}
