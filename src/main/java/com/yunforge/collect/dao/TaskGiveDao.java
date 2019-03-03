package com.yunforge.collect.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yunforge.collect.model.TaskGive;

public interface TaskGiveDao extends JpaRepository<TaskGive, String>,JpaSpecificationExecutor<TaskGive>{

	public void deleteByTaskMainId(String taskMainId);
	
	public List<TaskGive> findByTaskMainId(String taskMainId);
}
