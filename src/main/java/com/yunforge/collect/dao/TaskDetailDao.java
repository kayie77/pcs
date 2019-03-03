package com.yunforge.collect.dao;

import java.util.List;
import java.util.Map;


import javax.persistence.Transient;


import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.collect.model.AgrProCategory2;
import com.yunforge.collect.model.TaskDetail;

public interface TaskDetailDao extends JpaRepository<TaskDetail, String>,JpaSpecificationExecutor<TaskDetail>{
	@Query(" from TaskDetail td where td.taskMain.id=?1")
	List<TaskDetail> findAllByTaskMain(String id);
	
	@Modifying
	@Query(value=" delete from oper_taskdetail otd where otd.agrprocategory2 = ?1 and otd.taskmain = ?2 ",nativeQuery=true)
	public void deleteByAgrProCategory2IdAndTaskMainId(String agrProCategory2Id,String taskMainId);
	
	@Modifying
	@Query(value=" delete from oper_taskdetail otd2 where otd2.id in ( " +
	             " 	select otd_1.id " +
	             " 	from oper_taskdetail otd,oper_taskdetail otd_1 " +
	             " 	where otd_1.agrprocategory2 = otd.agrprocategory2 " +
	             " 	and otd_1.taskmain = otd.taskmain " +
	             " 	and otd.id = ?1 " +
	             " 	) ",nativeQuery=true)
	public void deleteBySameAgrProCategory2AndTaskMain(String taskDetailId);
	
	@Query(value=" SELECT otd_1  " +
			"  FROM TaskDetail otd, TaskDetail otd_1 " +
            "  WHERE otd_1.agrProCategory2.id = otd.agrProCategory2.id " +
            "  AND otd_1.taskMain.id = otd.taskMain.id " +
            "  AND otd.id in ?1 ")
	public List<TaskDetail> findSameTaskDetail(List<String> taskDetailId);
	
	@Query(value=" select otd.* from oper_taskdetail otd where otd.TASKMAIN = ?1 and otd.AGRPROCATEGORY2 = ?2 ",nativeQuery=true)
	public List<TaskDetail> findByTaskMainAndAgrprocategory(String taskMainId,String agrprocategoryId);
	
	@Query(value=" select otd from TaskDetail otd where otd.taskMain.id = ?1 and otd.agrProCategory2.id = ?2 and otd.id not in ?3 ")
	public List<TaskDetail> findByTaskMainAndAgrprocategory(String taskMainId,String agrprocategoryId,List<String> taskDetailId);

}
