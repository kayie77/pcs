package com.yunforge.cms.service.impl;

import static com.yunforge.cms.dao.InfoSpecifications.findInfos;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.api.dto.InfoDto;
import com.yunforge.cms.dao.InfoDao;
import com.yunforge.cms.model.Info;
import com.yunforge.cms.service.InfoManager;
import com.yunforge.common.bean.JqGridSearchBean;

@Scope("prototype")
@Service
@Transactional(readOnly = true)
public class InfoManagerImpl implements InfoManager {

	@Autowired
	private InfoDao infoDao;

	@Override
	public Info findInfoById(String id) {
		Info info = this.infoDao.findOne(id);
		return info;
	}

	@Override
	public Page<Info> find(JqGridSearchBean searchBean, Pageable pageable) {
		Page<Info> infos = this.infoDao.findAll(
				findInfos(null, 0, null, null, Boolean.FALSE, Boolean.FALSE,
						searchBean), pageable);
		return infos;
	}

	@Override 
	public Page<InfoDto> findByStatusAndTypeAndCatalog(Integer status,Integer type, Integer catalog, Pageable pageable) {
		Page<InfoDto> infos = this.infoDao.queryByStatusAndTypeAndCatalog(status,type,catalog,pageable);				
		return infos;
	}
	
	@Override
	public Page<Info> findPrivate(String filters, Pageable pageable) {
		Page<Info> infos = this.infoDao.findAll(
				findInfos(null, 0, null, null, Boolean.TRUE, Boolean.FALSE,
						filters), pageable);
		return infos;
	}
	
	@Override
	public Page<Info> findByStatus(Object[] status, String filters,
			Pageable pageable) {
		Page<Info> infos = this.infoDao.findAll(
				findInfos(null, 0, null, status, Boolean.FALSE, Boolean.FALSE,
						filters), pageable);
		return infos;
	}

	@Override
	public Page<Info> findByCat(String catId, String filters, Pageable pageable) {
		Page<Info> infos = this.infoDao.findAll(
				findInfos(null, 0, catId, null, Boolean.FALSE, Boolean.FALSE,
						filters), pageable);
		return infos;
	}

	@Override
	public List<Info> find(String[] ids) {
		List<String> idList = Arrays.asList(ids);
		return infoDao.findAll(idList);
	}

	@Override
	@Transactional
	public Info saveInfo(Info info) {
		return infoDao.save(info);
	}

	@Override
	@Transactional
	public void removeInfo(Info info) {
		infoDao.delete(info);
	}

	@Override
	@Transactional
	public void removeInfos(List<Info> infos) {
		infoDao.delete(infos);
	}

	@Override
	@Transactional
	public void removeInfos(String[] ids) {
		for (String id : ids) {
			infoDao.delete(id);
		}

	}

}