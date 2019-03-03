package com.yunforge.collect.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.yunforge.collect.model.DataCollectIndex;
import com.yunforge.collect.model.DataCollectPoint;

public interface DataCollectIndexDao extends JpaRepository<DataCollectIndex, String>,JpaSpecificationExecutor<DataCollectIndex>{
	
	@Query(" from DataCollectIndex dci order by dci.indexCode ")
	public List<DataCollectIndex> findAllOrderByIndexCode();

	@Query(" from DataCollectIndex dci where dci.indexCode = ?1 ")
	public List<DataCollectIndex> findByIndexCode(String indexCode);
	
	@Query(value=" SELECT odc.* " +
			 " FROM oper_taskdetail otd, oper_taskdetail otd_1, oper_datacollectindex odc " +
			 " WHERE otd_1.agrprocategory2 = otd.agrprocategory2 " +
			 " and odc.indexcode = otd_1.dataid " +
			 " AND otd_1.taskmain = otd.taskmain " +
			 " AND otd.id = ?1 ",nativeQuery=true)
	public List<DataCollectIndex> findByTaskDetailId(String id);
}
