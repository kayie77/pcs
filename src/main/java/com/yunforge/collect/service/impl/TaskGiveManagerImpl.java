package com.yunforge.collect.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.collect.dao.TaskGiveDao;
import com.yunforge.collect.model.TaskGive;
import com.yunforge.collect.service.TaskGiveManager;

@Service("TaskGiveManager")
@Transactional(readOnly=true, rollbackFor=Exception.class)
public class TaskGiveManagerImpl implements TaskGiveManager{
	
	@Autowired
	private TaskGiveDao taskGiveDao;
	
	public void deleteByTaskMainId(String taskMainId)
	{
		taskGiveDao.deleteByTaskMainId(taskMainId);
	}
	
	public void save(TaskGive taskGive)
	{
		taskGiveDao.save(taskGive);
	}
	
	public List<TaskGive> findByTaskMain(String taskMainId)
	{
		return taskGiveDao.findByTaskMainId(taskMainId);
	}
}
