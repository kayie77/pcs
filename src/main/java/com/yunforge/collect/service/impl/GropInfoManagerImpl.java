package com.yunforge.collect.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.yunforge.collect.dao.GropInfoDao;
import com.yunforge.collect.model.DataCollectPerson;
import com.yunforge.collect.model.GropInfo;
import com.yunforge.collect.service.GropInfoManager;
import com.yunforge.srpingside.persitence.DynamicSpecifications;
import com.yunforge.srpingside.persitence.SearchFilter;

@Service("GropInfoManager")
public class GropInfoManagerImpl implements GropInfoManager{
	
	final static Log log = LogFactory.getLog(GropInfoManagerImpl.class);
	
	@Autowired
	private GropInfoDao gropInfoDao;
	
	@PersistenceContext
	private EntityManager em;

	public List<GropInfo> getAllGropInfo(Integer state) {
		return gropInfoDao.findGropInfoByOrderByCode(state);
	}
	
	public List<GropInfo> findDataCollectPerson(List<GropInfo> groupInfoList,String taskMainId) {
		
		//遍历所有分组
		List<GropInfo> groupInfoListCopy = new ArrayList<GropInfo>();
		for(int i = 0;i < groupInfoList.size();i++)
		{
			GropInfo source = groupInfoList.get(i);
			GropInfo target = new GropInfo();
			BeanUtils.copyProperties(source, target);
			try {
			} catch(Exception e) {
				
			}
			
			//查询该分组下的人
			List<DataCollectPerson> personList = gropInfoDao.findDataCollectPerson(taskMainId, source.getId());
			target.setPersonList(personList);
			groupInfoListCopy.add(target);
		}
		
		//查询没有分组的人
		List<DataCollectPerson> personNoGroupList = gropInfoDao.findDataCollectPersonNoGroup(taskMainId);
		GropInfo gropInfo = new GropInfo();
		gropInfo.setName("未分组");
		
		gropInfo.setPersonList(personNoGroupList);
		groupInfoListCopy.add(gropInfo);
		
		return groupInfoListCopy;
	}
	
	@Override
	public GropInfo getGropInfo(String id) {
		// TODO Auto-generated method stub
		return gropInfoDao.findOne(id);
	}
	
	//获取所有的分组
	@Override
	public List<GropInfo> getAllGropInfo() {
		return gropInfoDao.findAll();
	}
	
	//新增
	@Override
	public GropInfo saveGropInfo(GropInfo  gropInfo) {
		// TODO Auto-generated method stub
		return gropInfoDao.save(gropInfo);
	}
		
	//获得分页下的分组
	@Override
	public Page<GropInfo> getGropInfo(Map<String, Object> searchParams,Pageable pageable) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		Specification<GropInfo> spec = DynamicSpecifications.bySearchFilter(filters.values(), GropInfo.class);
		return gropInfoDao.findAll(spec, pageable);
	}
	
	//删除
	@Override
	public void delGropInfos(String[] id) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < id.length; i++) {
			GropInfo gropInfo = getGropInfo(id[i]);
			em.refresh(gropInfo);
			em.remove(gropInfo);		
		}
	}

}
