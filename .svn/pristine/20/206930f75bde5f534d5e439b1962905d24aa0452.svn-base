package com.yunforge.collect.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yunforge.collect.model.GropInfo;

public interface AgrProCategoryDao extends JpaRepository<GropInfo, String>{
	
	@Query("select t from GropInfo t order by t.code")
	public List<GropInfo> findGropInfoByOrderByCode();

}
