package com.yunforge.base.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.data.domain.Pageable;

import com.yunforge.support.sql.CustomPage;



public interface CommonDao {
	public void executeSql(Query query);
	public List<Object[]> executeQuery(Query query);
	public CustomPage executePageQuery(Query query, Query countQuery, Pageable pageable);
	
	public String withOrder(String sql, Pageable pageable);
	
	public void executeSql(String sql);
	public void executeSql(String sql, Object param);
	public void executeSql(String sql, Object[] params);
	
	
	public CustomPage executeQuery(String sql, Pageable pageable) ;
	
	public List<Object[]> executeNativeQuery(String sql);
	public CustomPage 	  executeNativeQuery(String sql, Pageable pageable);
	public List<Object[]> executeNativeQuery(String sql, Object param);
	public List<Object[]> executeNativeQuery(String sql, Object[] params);
	public CustomPage executeNativeQuery(String sql, Object[] params, Pageable pageable);

	
}
