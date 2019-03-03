package com.yunforge.collect.service.impl;

import java.util.ArrayList;
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

import com.yunforge.collect.dao.AgrProCategory2Dao;
import com.yunforge.collect.dao.TaskDetailDao;
import com.yunforge.collect.dao.TaskMainDao;
import com.yunforge.collect.dao.impl.TaskDetailDaoImpl;
import com.yunforge.collect.model.AgrProCategory2;
import com.yunforge.collect.model.TaskDetail;
import com.yunforge.collect.model.TaskMain;
import com.yunforge.collect.service.TaskDetailManager;
import com.yunforge.common.util.StringUtil;
import com.yunforge.srpingside.persitence.DynamicSpecifications;
import com.yunforge.srpingside.persitence.SearchFilter;
import com.yunforge.srpingside.persitence.SearchFilter.Operator;

@Service("TaskDetailManager")
public class TaskDetailManagerImpl implements TaskDetailManager{
	
	final static Log log = LogFactory.getLog(TaskDetailManagerImpl.class);
	
	@Autowired
	private TaskDetailDao taskDetailDao;
	
	@Autowired
	private TaskMainDao taskMainDao;

	@Autowired
	private TaskDetailDaoImpl taskDetailDaoImpl;
	
	@Autowired
	private AgrProCategory2Dao agrProCategory2Dao;
	
	@PersistenceContext
	private EntityManager em;

	public boolean hasSameAgrprocategory(String taskMainId,String agrprocategoryId) {
		List<TaskDetail> taskDetailList = taskDetailDao.findByTaskMainAndAgrprocategory(taskMainId, agrprocategoryId);
		if(taskDetailList.size() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean hasSameAgrprocategory(String taskMainId,String agrprocategoryId,String taskDetailId) {
		List<String> taskDetailStrList = new ArrayList<String>();
		taskDetailStrList.add(taskDetailId);
		
		//查找品种相同的taskDetail记录，并放到List<String>中
		List<TaskDetail> sameTaskDetailList = taskDetailDao.findSameTaskDetail(taskDetailStrList);
		List<String> list = new ArrayList<String>();
		for(int i = 0;i < sameTaskDetailList.size();i++) {
			list.add(sameTaskDetailList.get(i).getId());
		}
		
		//查找品种不同的taskDetail记录，判断是否重复
		List<TaskDetail> taskDetailList = taskDetailDao.findByTaskMainAndAgrprocategory(taskMainId, agrprocategoryId,list);
		if(taskDetailList.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
	public void deleteSameTaskDetail(String[] ids) {
		
		//根据传过来的taskDetailId，查询相同的品种，然后删掉品种相同的taskDetail
		List<TaskDetail> list = findSameTaskDetail(ids);
		String[] idArray = new String[list.size()];
		for(int i = 0;i < list.size();i++) {
			idArray[i] = list.get(i).getId();
		}
		try {
			delTaskDetails(idArray);
		} catch(Exception e) {
			
		}
	}
	
	public List<TaskDetail> findSameTaskDetail(String[] ids) {
		return taskDetailDao.findSameTaskDetail(StringUtil.array2List(ids));
	}
	
	public void deleteBySameAgrProCategory2AndTaskMain(String[] taskDetailId) {
		taskDetailDaoImpl.deleteBySameAgrProCategory2AndTaskMain(em,taskDetailId);
	}
	
	@Override
	public TaskDetail getTaskDetail(String id) {
		// TODO Auto-generated method stub
		return taskDetailDao.findOne(id);
	}

	public Page<TaskDetail> getTaskDetail(Map<String, Object> searchParams,Pageable pageable) {
		return taskDetailDaoImpl.getTaskDetail(searchParams, pageable);
	}
	
	//获得信息
	@Override
	public Page<TaskDetail> getTaskDetail(String tmId, Map<String, Object> searchParams,Pageable pageable) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		filters.put("taskMain.id", new SearchFilter("taskMain.id", Operator.EQ, tmId));
		Specification<TaskDetail> spec = DynamicSpecifications.bySearchFilter(filters.values(), TaskDetail.class);
		return taskDetailDao.findAll(spec, pageable);
	}

	public void deleteTaskDetail(String agrProCategory2Id,String taskMainId) {
		taskDetailDao.deleteByAgrProCategory2IdAndTaskMainId(agrProCategory2Id, taskMainId);
	}
	
	//新增
	@Override
	public TaskDetail newTaskDetail(TaskDetail taskDetail) {
		TaskMain taskMain = taskDetail.getTaskMain();
		AgrProCategory2 agrProCategory2 = taskDetail.getAgrProCategory2();
		if (taskMain != null){
			taskMain = taskMainDao.findOne(taskMain.getId());
			taskDetail.setTaskMain(taskMain);
		}
		
		if (agrProCategory2 != null){
			agrProCategory2 = agrProCategory2Dao.findOne(agrProCategory2.getId());
			taskDetail.setAgrProCategory2(agrProCategory2);
		}
		return taskDetailDao.save(taskDetail);
	}
	

	//删除
	@Override
	public void delTaskDetails(String[] id) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < id.length; i++) {
			TaskDetail taskDetail = getTaskDetail(id[i]);
			em.refresh(taskDetail);
			em.remove(taskDetail);
		}
	}

}
