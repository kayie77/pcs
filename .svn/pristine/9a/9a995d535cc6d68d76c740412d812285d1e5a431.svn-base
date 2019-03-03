package com.yunforge.collect.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.yunforge.base.model.Division;
import com.yunforge.collect.model.DataCollectPoint;

public interface DataCollectPointDao extends JpaRepository<DataCollectPoint, String>,JpaSpecificationExecutor<DataCollectPoint>{
	
	@Query("select t from DataCollectPoint t order by t.code")
	public List<DataCollectPoint> findDataCollectPointByOrderByCode();

	@Query("select t from DataCollectPoint t,DataCollectPerson p where t.id=p.dataCollectPoint.id and p.id=?1")
	public DataCollectPoint findOneByPersonDate(String personId);
	
	@Query("select t from DataCollectPoint t where t.dataCollectCategory.id=?1")
	public List<DataCollectPoint> findAllDataCollectPointByCtgId(String ctgId);
	
}
