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
import com.yunforge.base.dao.DivisionDao;
import com.yunforge.base.model.Division;
import com.yunforge.base.model.Person;
import com.yunforge.collect.dao.DataCollectCategoryDao;
import com.yunforge.collect.dao.DataCollectPointDao;
import com.yunforge.collect.model.DataCollectCategory;
import com.yunforge.collect.model.DataCollectPoint;
import com.yunforge.collect.model.DataReoprtedDetail;
import com.yunforge.collect.service.DataCollectPointManager;
import com.yunforge.srpingside.persitence.DynamicSpecifications;
import com.yunforge.srpingside.persitence.SearchFilter;
import com.yunforge.srpingside.persitence.SearchFilter.Operator;

@Service("DataCollectPointManager")
public class DataCollectPointManagerImpl implements DataCollectPointManager{
	
	final static Log log = LogFactory.getLog(DataCollectPointManagerImpl.class);
	
	@Autowired
	private DataCollectPointDao dataCollectPointDao;
	
	@Autowired
	private DataCollectCategoryDao dataCollectCategoryDao;

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public DataCollectPoint getDataCollectPoint(String id) {
		// TODO Auto-generated method stub
		return dataCollectPointDao.findOne(id);
	}
	
	@Override
	public List<DataCollectPoint> getAllDataCollectPoint() {
		// TODO Auto-generated method stub
		return dataCollectPointDao.findAll();
	}
	@Override
	public List<DataCollectPoint> getAllDataCollectPointByCtgId(String ctgId) {
		// TODO Auto-generated method stub
		return dataCollectPointDao.findAllDataCollectPointByCtgId(ctgId);
	}
	
	@Override
	public DataCollectPoint saveDataCollectPoint(DataCollectPoint  dataCollectPoint) {
		// TODO Auto-generated method stub
		return dataCollectPointDao.save(dataCollectPoint);
	}
		
	//获得分页下的农场品上报信息
	@Override
	public Page<DataCollectPoint> getDataCollectPoint(String ctgId, Map<String, Object> searchParams,Pageable pageable) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("dataCollectCategory.id", new SearchFilter("dataCollectCategory.id", Operator.EQ, ctgId));
		Specification<DataCollectPoint> spec = DynamicSpecifications.bySearchFilter(filters.values(), DataCollectPoint.class);
		return dataCollectPointDao.findAll(spec, pageable);
	}
	
	//新增
	@Override
	public DataCollectPoint newDataCollectPoint(DataCollectPoint dataCollectPoint) {

		DataCollectCategory dcc = dataCollectPoint.getDataCollectCategory();
		if (dcc != null){
			dcc = dataCollectCategoryDao.findOne(dcc.getId());
			dataCollectPoint.setDataCollectCategory(dcc);
		}
		return dataCollectPointDao.save(dataCollectPoint);
	}
	
	//保存地理位置信息
	@Override
	public void saveMapData(String id,double lng,double lat,String address) throws Exception {
		// TODO Auto-generated method stub
		    DataCollectPoint dataCollectPoint = getDataCollectPoint(id);
		    dataCollectPoint.setLongitude(lng);
		    dataCollectPoint.setLatitude(lat);
		    dataCollectPoint.setAdress(address);
		    dataCollectPointDao.save(dataCollectPoint);	
	}
	
	//删除
	@Override
	public void delDataCollectPoints(String[] id) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < id.length; i++) {
			DataCollectPoint dataCollectPoint = getDataCollectPoint(id[i]);
			em.refresh(dataCollectPoint);
			em.remove(dataCollectPoint);
		}
	}
	
	@Override
	public Page<DataCollectPoint> getDataCollectPointByUser(String userId, Map<String, Object> searchParams,Pageable pageable) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("dataCollectPersons.id", new SearchFilter("dataCollectPersons.id", Operator.EQ, userId));
		Specification<DataCollectPoint> spec = DynamicSpecifications.bySearchFilter(filters.values(), DataCollectPoint.class);
		return dataCollectPointDao.findAll(spec, pageable);
	}

	@Override
	public DataCollectPoint getPointByPersonDate(Person person, String period) {
		return dataCollectPointDao.findOneByPersonDate(person.getId());
	}

}
