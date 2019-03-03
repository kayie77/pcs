package com.yunforge.collect.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.collect.model.DataCollectIndex;

public interface DataCollectIndexManager {
	
	public Page<DataCollectIndex> getDataCollectIndex(Map<String, Object> searchParams,Pageable pageable);

	public List<DataCollectIndex> findByTaskDetailId(String taskDetailId);
	
	public List<DataCollectIndex> findAllOrderByIndexCode();
	
	public DataCollectIndex findByIndexCode(String indexCode);
	
	public DataCollectIndex findById(String id);
	
	public DataCollectIndex save(DataCollectIndex dataCollectIndex);
	
	public void deleteById(String id);
}
