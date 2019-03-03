package com.yunforge.base.dao.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.base.dao.CommonDao;
import com.yunforge.support.sql.CustomPage;
import com.yunforge.support.sql.PageableParser;

@Repository("commonDao")
@Transactional
public class CommonDaoImpl implements CommonDao{
	@PersistenceContext
	public EntityManager em;
	
	@Override
	public List<Object[]> executeQuery(Query query) {
		return query.getResultList();
	}
	
	/*****************************native query**********************************/
	@Override
	public List<Object[]> executeNativeQuery(String sql) {
		// TODO Auto-generated method stub
		Query query = em.createNativeQuery(sql);
		return query.getResultList();
	}
	
	@Override
	public CustomPage executeNativeQuery(String sql, Pageable pageable) {
		CustomPage page = null;
		return page;
	}
	
	@Override
	public List<Object[]> executeNativeQuery(String sql, Object param) {
		Query query = em.createNativeQuery(sql);
		query.setParameter(1, param);
		return query.getResultList();
	}
	
	@Override
	public List<Object[]> executeNativeQuery(String sql, Object[] params) {
		// TODO Auto-generated method stub
		Query query = createNativeQuery(sql, params);
		return query.getResultList();
	}

	@Override
	public CustomPage executeNativeQuery(String sql, Object[] params, Pageable pageable) {
		CustomPage page = null;
		return page;
	}

	@Override
	public void executeSql(Query query)  {
		query.executeUpdate();
	}

	@Override
	public void executeSql(String sql) {
		Query query = em.createNativeQuery(sql);
		query.executeUpdate();
	}
	
	@Override
	public void executeSql(String sql, Object param) {
		Query query = em.createNativeQuery(sql);
		query.setParameter(1, param);
		query.executeUpdate();
	}


	@Override
	public void executeSql(String sql, Object[] params) {
		Query query = createNativeQuery(sql, params);
		query.executeUpdate();
	}

	/*****************************jpql query**********************************/
	@Override
	public CustomPage executeQuery(String sql, Pageable pageable) {
		CustomPage page = null;
		Query query = em.createQuery(withOrder(sql, pageable));
		page = executePageQuery(query, pageable);
		return page;
	}

	@Override
	public String withOrder(String sql, Pageable pageable){
		PageableParser pp = new PageableParser(pageable);
		String orderSql;
		if (sql.toLowerCase().indexOf("order by") == -1) {
			orderSql = pp.getOrderSql(true);
		}else{
			orderSql = pp.getOrderSql(false);
		}
		return sql + orderSql;
	}
	
	@Override
	public CustomPage executePageQuery(Query query, Query countQuery, Pageable pageable){
		CustomPage page = new CustomPage();
		PageableParser pp = new PageableParser(pageable);
		page.setTotalPages(pp.getTotalPage( ((BigDecimal) countQuery.getSingleResult()).intValue() ));
		page.setNumber(pp.getPageNumber());
		List content = query.setFirstResult(pp.getPageStart()).setMaxResults(pp.getPageSize()).getResultList();
		page.setContent(content);
		return page;
	}
	
	private CustomPage executePageQuery(Query query, Pageable pageable){
		CustomPage page = new CustomPage();
		PageableParser pp = new PageableParser(pageable);
		page.setTotalPages(pp.getTotalPage(query.getResultList().size()));
		page.setNumber(pp.getPageNumber());
		List content = query.setFirstResult(pp.getPageStart()).setMaxResults(pp.getPageSize()).getResultList();
		page.setContent(content);
		return page;
	}
	
	private Query createNativeQuery(String sql, Object[] params){
		Query query = em.createNativeQuery(sql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i+1, params[i]);
		}
		return query;
	}
	
	private Query createQuery(String sql, Object[] params){
		Query query = em.createNativeQuery(sql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i+1, params[i]);
		}
		return query;
	}
}
