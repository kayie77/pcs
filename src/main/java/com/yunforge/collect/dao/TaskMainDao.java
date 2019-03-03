package com.yunforge.collect.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.yunforge.collect.model.DataReoprtedMain;
import com.yunforge.collect.model.TaskMain;

public interface TaskMainDao extends JpaRepository<TaskMain, String>,JpaSpecificationExecutor<TaskMain>{
	@Query("select o from TaskMain o where o.id in(select distinct t.taskMain.id from DataReoprtedMain t where  t.person.id=?1) ")
	public List<TaskMain> findClassTaskMain(String personId);
}
