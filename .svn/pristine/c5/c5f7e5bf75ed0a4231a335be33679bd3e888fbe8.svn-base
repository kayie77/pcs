package com.yunforge.collect.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.collect.model.DataReoprtedMain;
import com.yunforge.collect.model.TaskMain;

public interface TaskMainManager {
	
	TaskMain  saveTaskMain(TaskMain  taskMain );
	TaskMain getTaskMain(String id);
	public void delTaskMains(String[] id) throws Exception;
	Page<TaskMain> getSelfTaskMain(Map<String, Object> searchParams,Pageable pageable);
	Page<TaskMain> getTaskMain(Map<String, Object> searchParams,Pageable pageable);
	Page<DataReoprtedMain> queryHistoryDetail(Map<String, Object> searchParams,Pageable pageable);
	
	Page<TaskMain> getTaskMaiByCollectCategory(String id,
			Map<String, Object> searchParams, Pageable pageable);
	Page<TaskMain> getTaskMainByJPA(Map<String, String[]> paramsMap,
			Pageable pageable);
	HashMap<String, Object> instanceTask(TaskMain taskMain, String beginDate,
			String endDate);
	public int getSelfTaskMainCount(String personId);
	public List<String> findDatareoprtedmainByTaskMainId(String taskMainId,String personId);
}
