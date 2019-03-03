package com.yunforge.collect.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.yunforge.collect.model.DataCollectPerson;
import com.yunforge.collect.model.GropInfo;

public interface GropInfoDao extends JpaRepository<GropInfo, String>,JpaSpecificationExecutor<GropInfo>{
	
	@Query("select t from GropInfo t order by t.code")
	public List<GropInfo> findGropInfoByOrderByCode();

	@Query("select t from GropInfo t where t.state = ?1 order by t.code")
	public List<GropInfo> findGropInfoByOrderByCode(Integer state);
	
	@Query(value=" select ddc.* " +
		   " from oper_taskgive ot,oper_PersonGroup dp,oper_datacollectperson ddc " + 
		   " where ot.DATACOLLECTPERSON = dp.DATACOLLECTPERSONID " + 
		   " and ot.DATACOLLECTPERSON = ddc.ID " + 
		   " and ot.TASKMAIN = ?1 " + 
		   " and dp.GROPINFOID = ?2 ",nativeQuery=true)
	public List<DataCollectPerson> findDataCollectPerson(String taskMainId,String gropInfoId);
	
	@Query(value=" select ddc.* " +
			   " from oper_taskgive ot,oper_datacollectperson ddc " + 
			   " where ot.DATACOLLECTPERSON = ddc.ID " + 
			   " and not exists (select dp.id from oper_PersonGroup dp where ot.DATACOLLECTPERSON = dp.DATACOLLECTPERSONID) " + 
			   " and ot.TASKMAIN = ?1 ",nativeQuery=true)
		public List<DataCollectPerson> findDataCollectPersonNoGroup(String taskMainId);
}
