package com.yunforge.collect.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.collect.model.TaskDetail;


public interface TaskDetailManager {
	
	TaskDetail  newTaskDetail(TaskDetail  taskDetail );
	TaskDetail getTaskDetail(String id);
	public void delTaskDetails(String[] id) throws Exception;
	Page<TaskDetail> getTaskDetail(String tmId, Map<String, Object> searchParams,Pageable pageable);
	Page<TaskDetail> getTaskDetail(Map<String, Object> searchParams,Pageable pageable);
	public void deleteTaskDetail(String agrProCategory2Id,String taskMainId);
	public void deleteBySameAgrProCategory2AndTaskMain(String[] taskDetailId);
	public List<TaskDetail> findSameTaskDetail(String[] ids);
	public void deleteSameTaskDetail(String[] ids);
	public boolean hasSameAgrprocategory(String taskMainId,String agrprocategoryId);
	public boolean hasSameAgrprocategory(String taskMainId,String agrprocategoryId,String taskDetailId);
}
