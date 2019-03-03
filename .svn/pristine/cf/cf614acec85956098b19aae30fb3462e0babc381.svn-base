package com.yunforge.collect.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.yunforge.collect.model.DataReoprtedMain;

public interface DataReoprtedMainDao extends JpaRepository<DataReoprtedMain, String>,JpaSpecificationExecutor<DataReoprtedMain>{

	@Query("select t from DataReoprtedMain t where t.taskMain.id = ?1")
	public DataReoprtedMain findTaskMainById(String id);

	@Query("select t from DataReoprtedMain t where  t.person.id=?1  and t.period=?2 and  t.taskMain.id = ?3")
	public DataReoprtedMain findReoprtedMainByPersonPeriodTask(String personId,Integer period, String taskId);

	@Query("select t from DataReoprtedMain t where  t.person.id=?1  and t.period=?2")
	public List<DataReoprtedMain> findReoprtedMainByPersonPeriod(String personId, int period);
	@Query("select t from DataReoprtedMain t where  t.person.id=?1  and t.executeType=?2")
	public  List<DataReoprtedMain> findByMultParam(String personId, int executeType);
	
	@Query("select t from DataReoprtedMain t where t.taskMain.id =?1 and t.period>=?2")
	public List<DataReoprtedMain> findThanPeriod(String id,Integer period);

}

