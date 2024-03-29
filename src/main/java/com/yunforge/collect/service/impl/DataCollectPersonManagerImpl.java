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
import org.springframework.stereotype.Service;

import com.yunforge.collect.dao.DataCollectPersonDao;
import com.yunforge.collect.dao.DataCollectPointDao;
import com.yunforge.collect.model.DataCollectPerson;
import com.yunforge.collect.model.DataCollectPoint;
import com.yunforge.collect.model.TaskGive;
import com.yunforge.collect.service.DataCollectPersonManager;
import com.yunforge.srpingside.persitence.DynamicSpecifications;
import com.yunforge.srpingside.persitence.SearchFilter;
import com.yunforge.srpingside.persitence.SearchFilter.Operator;

@Service("DataCollectPersonManager")
public class DataCollectPersonManagerImpl implements DataCollectPersonManager{
	
	final static Log log = LogFactory.getLog(DataCollectPersonManagerImpl.class);
	
	@Autowired
	private DataCollectPersonDao dataCollectPersonDao;
	
	@Autowired
	private DataCollectPointDao dataCollectPointDao;
	
	@PersistenceContext
	private EntityManager em;
	
	public List<DataCollectPerson> getNotGroupPersonList() {
		return dataCollectPersonDao.getNotGroupPersonList();
	}
	
	@Override
	public DataCollectPerson getDataCollectPerson(String id) {
		// TODO Auto-generated method stub
		return dataCollectPersonDao.findOne(id);
	}
	
	@Override
	public List<DataCollectPerson> getAllDataCollectPerson(){
		// TODO Auto-generated method stub
		return dataCollectPersonDao.findAll();
	}
		
	//获得分页下采集人员信息
	@Override
	public Page<DataCollectPerson> getDataCollectPerson(String dcpId, Map<String, Object> searchParams,Pageable pageable) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("dataCollectPoint.id", new SearchFilter("dataCollectPoint.id", Operator.EQ, dcpId));
		Specification<DataCollectPerson> spec = DynamicSpecifications.bySearchFilter(filters.values(), DataCollectPerson.class);
		return dataCollectPersonDao.findAll(spec, pageable);
	}

	//新增
	@Override
	public DataCollectPerson newDataCollectPerson(DataCollectPerson dataCollectPerson) {
		DataCollectPoint dcp = dataCollectPerson.getDataCollectPoint();
		if (dcp != null){
			dcp = dataCollectPointDao.findOne(dcp.getId());
			dataCollectPerson.setDataCollectPoint(dcp);
		}
		return dataCollectPersonDao.save(dataCollectPerson);
	}

	//删除
	@Override
	public void delDataCollectPersons(String[] id) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < id.length; i++) {
			DataCollectPerson dataCollectPerson = getDataCollectPerson(id[i]);
			em.refresh(dataCollectPerson);
			em.remove(dataCollectPerson);
		}
	}
	
	@Override
	public List<TaskGive> getPersonByTaskMain(String taskMainId) {
		List<TaskGive> tg=dataCollectPersonDao.findByTaskMain(taskMainId);
		return tg;
	}



}
