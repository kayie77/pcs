package com.yunforge.collect.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yunforge.collect.model.DataCollectCategory;

public interface DataCollectCategoryDao extends JpaRepository<DataCollectCategory, String>{
	
	@Query("select t from DataCollectCategory t")
	public List<DataCollectCategory> findDataCollectCategoryByOrderByCode();

}
