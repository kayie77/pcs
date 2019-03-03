package com.yunforge.collect.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.yunforge.collect.dto.TreeNode;
import com.yunforge.collect.dao.AgrProCategoryDao;
import com.yunforge.collect.dao.GropInfoDao;
import com.yunforge.collect.dao.PersonGroupDao;
import com.yunforge.collect.model.GropInfo;
import com.yunforge.collect.model.PersonGroup;
import com.yunforge.collect.service.PersonGroupManager;
import com.yunforge.srpingside.persitence.DynamicSpecifications;
import com.yunforge.srpingside.persitence.SearchFilter;
import com.yunforge.srpingside.persitence.SearchFilter.Operator;

@Service("PersonGroupManager")
public class PersonGroupManagerImpl implements PersonGroupManager{
	
	final static Log log = LogFactory.getLog(PersonGroupManagerImpl.class);
	
	@Autowired
	private PersonGroupDao personGroupDao;
	
	@Autowired
	private GropInfoDao gropInfoDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public PersonGroup getPersonGroup(String id) {
		// TODO Auto-generated method stub
		return personGroupDao.findOne(id);
	}
	
	//获取所有的农产品
	@Override
	public List<PersonGroup> getAllPersonGroup() {
		return personGroupDao.findAll();
	}
	
	@Override
	public PersonGroup savePersonGroup(PersonGroup  personGroup) {
		// TODO Auto-generated method stub
		return personGroupDao.save(personGroup);
	}
		
	//获得分页下的农场品上报信息
	@Override
	public Page<PersonGroup> getPersonGroup(String ctgId, Map<String, Object> searchParams,Pageable pageable) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("gropInfo.id", new SearchFilter("gropInfo.id", Operator.EQ, ctgId));
		Specification<PersonGroup> spec = DynamicSpecifications.bySearchFilter(filters.values(), PersonGroup.class);
		return personGroupDao.findAll(spec, pageable);
	}
	
	//新增
	@Override
	public PersonGroup newPersonGroup(PersonGroup personGroup) {

		GropInfo gropInfo = personGroup.getGropInfo();
		if (gropInfo != null){
			gropInfo = gropInfoDao.findOne(gropInfo.getId());
			personGroup.setGropInfo(gropInfo);
		}
		return personGroupDao.save(personGroup);
	}
	
	//删除
	@Override
	public void delPersonGroups(String[] id) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < id.length; i++) {
			PersonGroup personGroup = getPersonGroup(id[i]);
			em.refresh(personGroup);
			em.remove(personGroup);		
		}
	}

}
