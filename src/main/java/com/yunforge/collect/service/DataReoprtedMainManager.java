package com.yunforge.collect.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.base.model.Person;
import com.yunforge.collect.model.DataReoprtedMain;
import com.yunforge.collect.model.TaskMain;
import com.yunforge.srpingside.persitence.SearchFilter;

public interface DataReoprtedMainManager {

	DataReoprtedMain newDataReoprtedMain(DataReoprtedMain dataReoprtedMain,TaskMain taskMain);

	Page<DataReoprtedMain> getDataReoprtedMain(Map<String, Object> searchParams,Pageable pageable);

	DataReoprtedMain getReoprteMainByPersonPeriodTask(String personId,String period, String taskId);

	DataReoprtedMain saveDataReoprtedMain(DataReoprtedMain reoprtMian);
	
	List<DataReoprtedMain> getReoprteMainByPersonDate(Person person, String period);
	
	Page<DataReoprtedMain> getReoprteMain(Map<String, SearchFilter> filters,Pageable pageable);
	
	Page<DataReoprtedMain> getTaskByJPA(Map<String, String[]> paramsMap,Pageable pageable);
	
	List<DataReoprtedMain>  getReoprteMainByMultParam(String personId,String executeType);
	
	Page<DataReoprtedMain> getMyCurrentTask(Map<String, String[]> params,Pageable pageable);
	
	Page<DataReoprtedMain> setDataReoprtedMainEditable(List<DataReoprtedMain> content, Pageable pageable, int totalPages);

	public void save(DataReoprtedMain dataReoprtedMain);
	
	public DataReoprtedMain findDataReoprtedMainById(String id);
	
	public void taskReported(DataReoprtedMain dataReoprtedMain);

	Page<DataReoprtedMain> getMyUnReoprtedTask(Map<String, String[]> params,Pageable pageable);
	
	public int getDataReoprtedMainStatus(DataReoprtedMain dataReoprtedMain);
}
