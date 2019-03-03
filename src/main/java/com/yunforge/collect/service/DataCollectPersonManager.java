package com.yunforge.collect.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.collect.model.DataCollectPerson;
import com.yunforge.collect.model.TaskGive;

public interface DataCollectPersonManager {
	
	DataCollectPerson  getDataCollectPerson(String id);
	List<DataCollectPerson> getAllDataCollectPerson();
	DataCollectPerson  newDataCollectPerson(DataCollectPerson  dataCollectPerson);
	void delDataCollectPersons(String[] id) throws Exception;
	
	Page<DataCollectPerson> getDataCollectPerson(String ctgId, Map<String, Object> searchParams,Pageable pageable);
	public List<DataCollectPerson> getNotGroupPersonList();
	List<TaskGive> getPersonByTaskMain(String taskMainId);
}
