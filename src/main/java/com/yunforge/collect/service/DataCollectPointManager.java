package com.yunforge.collect.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.base.model.Division;
import com.yunforge.base.model.Person;
import com.yunforge.collect.model.DataCollectPoint;

public interface DataCollectPointManager {
	
	DataCollectPoint  getDataCollectPoint(String id);
	List<DataCollectPoint> getAllDataCollectPoint();
	DataCollectPoint  saveDataCollectPoint(DataCollectPoint  dataCollectPoint );
	DataCollectPoint  newDataCollectPoint(DataCollectPoint  dataCollectPoint);
    void delDataCollectPoints(String[] id) throws Exception;
    
	Page<DataCollectPoint> getDataCollectPoint(String ctgId, Map<String, Object> searchParams,Pageable pageable);
	Page<DataCollectPoint> getDataCollectPointByUser(String userId,Map<String, Object> searchParams, Pageable pageable);
	DataCollectPoint  getPointByPersonDate(Person person, String period);
	List<DataCollectPoint> getAllDataCollectPointByCtgId(String ctgId);
	
	void saveMapData(String id,double lng,double lat,String address) throws Exception;
}
