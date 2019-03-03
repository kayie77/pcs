package com.yunforge.base.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javassist.bytecode.Descriptor.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.yunforge.base.model.Notice;
import com.yunforge.base.model.NoticeDivision;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.bean.Params;
import com.yunforge.common.util.PageUtil;
import com.yunforge.common.util.StringUtil;

public class NoticeDaoImpl {

	@PersistenceContext
	public EntityManager em;
	
	public List<Notice> getUnReadNoticeByCurrentDivCode(String divCode,int rowCount) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select nd.notice ");
		sql.append(" from NoticeDivision nd ");
		sql.append(" where nd.division.divCode = '" +divCode+ "' ");
		sql.append(" and nd.notice.status = '1' ");
		sql.append(" and nd.readFlag = '0' ");
		sql.append(" order by nd.notice.createdate ");
		
		Query query = em.createQuery(sql.toString());
		if(rowCount != -1) {
			query.setMaxResults(rowCount);
		}
		return query.getResultList();
	}
	
	public List<Notice> getNoticeByCurrentDivCode(String divCode,int rowCount) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select nd.notice ");
		sql.append(" from NoticeDivision nd ");
		sql.append(" where nd.division.divCode = '" +divCode+ "' ");
		sql.append(" and nd.notice.status = '1' ");
		sql.append(" order by nd.notice.createdate desc ");
		
		Query query = em.createQuery(sql.toString());
		query.setMaxResults(rowCount);
		return query.getResultList();
	}
	
	public void updateNotice(Notice notice) {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" update notice ");
		sql.append(" set title = ?1, ");
		sql.append(" 	ntcontent = ?2, ");
		sql.append(" 	modifydate = ?3, ");
		sql.append(" 	status = ?4 ");
		sql.append(" where id = ?5 ");
		
		Query query = em.createNativeQuery(sql.toString());
		query.setParameter(1, notice.getTitle());
		query.setParameter(2, notice.getNtcontent());
		query.setParameter(3, notice.getModifydate());
		query.setParameter(4, notice.getStatus());
		query.setParameter(5, notice.getId());
		query.executeUpdate();
	}
	
	public GridBean listNotices(Params params) {
		
		String title = (String)params.get("title");
		String status = (String)params.get("status");
		String pageIndex = (String)params.get("pageIndex");
		Integer pageSize = (Integer)params.get("pageSize");
		String sidx = (String)params.get("sidx");
		String sord = (String)params.get("sord");
//		String searchField = (String)params.get("searchField");
//		String searchString = (String)params.get("searchString");
		
		Map<String,String> orderMap = new HashMap<String,String>();
		orderMap.put("title", "n.title");
		orderMap.put("status", "n.status");

		Map<Integer,String> conditionMap = new HashMap<Integer,String>();
		

		StringBuffer conditionSql = new StringBuffer();
		StringBuffer orderSql = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select count(n.id) ");
		sql.append(" from notice n ");
		sql.append(" where 1=1 ");
		
		if(StringUtil.notEmpty(title)) {
			conditionSql.append(" and n.title like ?1 ");
			conditionMap.put(1, "%" + title + "%");
		}
		if(StringUtil.notEmpty(status)) {
			conditionSql.append(" and n.status = ?2 ");
			conditionMap.put(2, status);
		}
		sql.append(conditionSql.toString());
		
		if(StringUtil.notEmpty(sidx)) {
			orderSql.append(" order by " + orderMap.get(sidx) + " " + sord);
		}
		sql.append(orderSql.toString());
		
		Query query = em.createNativeQuery(sql.toString());
		Set set = conditionMap.entrySet();
		for(java.util.Iterator i = set.iterator();i.hasNext();) {
			Map.Entry entry = (Map.Entry)i.next();
			query.setParameter((Integer)entry.getKey(), entry.getValue());
		}
		
		List list = query.getResultList();
		int count = StringUtil.getCount(list);
		
		GridBean gridBean = new PageUtil().getGridBean(pageIndex,pageSize,count);
		
		
		
		sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select z.*,rownum r from ( ");
		sql.append(" select * from ( ");
		sql.append(" select n.id,n.title,n.ntcontent,n.createdate,n.modifydate,n.status ");
		sql.append(" from notice n ");
		sql.append(" where 1=1 ");
		sql.append(conditionSql.toString());
		sql.append(orderSql.toString());
		sql.append(" ) y ");
		sql.append(" ) z where rownum <= " + gridBean.getRowEnd());
		sql.append(" ) x where r > " + gridBean.getRowBegin());
		
		query = em.createNativeQuery(sql.toString());
		for(java.util.Iterator i = set.iterator();i.hasNext();) {
			Map.Entry entry = (Map.Entry)i.next();
			query.setParameter(entry.getKey().toString(), entry.getValue());
		}

		
//		Page<Notice> page = Page.class.newInstance();
//		page.getContent();
		List<Notice> resultList = new ArrayList<Notice>();
		list = query.getResultList();
		for(int i = 0;i < list.size();i++) {
			
			Notice notice = new Notice();
			Object[] obs = (Object[])list.get(i);
			
			int col = 0;
			notice.setId(StringUtil.getString(obs[col++]));
			notice.setTitle(StringUtil.getString(obs[col++]));
//			notice.setContent(StringUtil.getStrByClob(obs[col++]));
			notice.setNtcontent(StringUtil.getString(obs[col++]));
			notice.setCreatedate(StringUtil.getYMD(obs[col++]));
			notice.setModifydate(StringUtil.getYMD(obs[col++]));
			notice.setStatus(StringUtil.getInt(obs[col++],0));
			
			resultList.add(notice);
		}
		
		gridBean.setRows(resultList);
		return gridBean;
	}


	public GridBean queryViewList(Params params) {
		
		String title = (String)params.get("title");
		String status = (String)params.get("status");
		String readflag = (String)params.get("readflag");
		String pageIndex = (String)params.get("pageIndex");
		Integer pageSize = (Integer)params.get("pageSize");
		String sidx = (String)params.get("sidx");
		String sord = (String)params.get("sord");
		String divcode = (String)params.get("divcode");
//		String searchField = (String)params.get("searchField");
//		String searchString = (String)params.get("searchString");
		
		Map<String,String> orderMap = new HashMap<String,String>();
		orderMap.put("title", "n.title");
		orderMap.put("status", "n.status");
		orderMap.put("readflag", "nd.readflag");

		Map<Integer,String> conditionMap = new HashMap<Integer,String>();
		

		StringBuffer conditionSql = new StringBuffer();
		StringBuffer orderSql = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select count(n.id) ");
		sql.append(" from notice n,noticedivision nd,sys_division sd ");
		sql.append(" where 1=1 ");
		sql.append(" and n.id = nd.noticeid ");
		sql.append(" and nd.divisionid = sd.id ");
		
		conditionSql.append(" and sd.div_code = '" +divcode+ "'");
		if(StringUtil.notEmpty(title)) {
			conditionSql.append(" and n.title like ?1 ");
			conditionMap.put(1, "%" + title + "%");
		}
		if(StringUtil.notEmpty(status)) {
			conditionSql.append(" and n.status = ?2 ");
			conditionMap.put(2, status);
		}
		if(StringUtil.notEmpty(readflag)) {
			conditionSql.append(" and nd.readflag = ?3 ");
			conditionMap.put(3, readflag);
		}
		sql.append(conditionSql.toString());
		
		if(StringUtil.notEmpty(sidx)) {
			orderSql.append(" order by " + orderMap.get(sidx) + " " + sord);
		} else {
			orderSql.append(" order by n.createdate desc ");
		}
		sql.append(orderSql.toString());
		
		Query query = em.createNativeQuery(sql.toString());
		Set set = conditionMap.entrySet();
		for(java.util.Iterator i = set.iterator();i.hasNext();) {
			Map.Entry entry = (Map.Entry)i.next();
			query.setParameter((Integer)entry.getKey(), entry.getValue());
		}
		
		List list = query.getResultList();
		int count = StringUtil.getCount(list);
		
		GridBean gridBean = new PageUtil().getGridBean(pageIndex,pageSize,count);
		
		
		
		sql = new StringBuffer();
		sql.append(" select * from ( ");
		sql.append(" select z.*,rownum r from ( ");
		sql.append(" select * from ( ");
		sql.append(" select n.id,n.title,n.ntcontent,n.createdate,n.modifydate,n.status,nd.readflag,nd.readdate ");
		sql.append(" from notice n,noticedivision nd,sys_division sd ");
		sql.append(" where 1=1 ");
		sql.append(" and n.id = nd.noticeid ");
		sql.append(" and nd.divisionid = sd.id ");
		sql.append(conditionSql.toString());
		sql.append(orderSql.toString());
		sql.append(" ) y ");
		sql.append(" ) z where rownum <= " + gridBean.getRowEnd());
		sql.append(" ) x where r > " + gridBean.getRowBegin());
		
		query = em.createNativeQuery(sql.toString());
		for(java.util.Iterator i = set.iterator();i.hasNext();) {
			Map.Entry entry = (Map.Entry)i.next();
			query.setParameter(entry.getKey().toString(), entry.getValue());
		}

		List<Notice> resultList = new ArrayList<Notice>();
		list = query.getResultList();
		for(int i = 0;i < list.size();i++) {
			
			Notice notice = new Notice();
			Object[] obs = (Object[])list.get(i);
			
			int col = 0;
			notice.setId(StringUtil.getString(obs[col++]));
			notice.setTitle(StringUtil.getString(obs[col++]));
//			notice.setContent(StringUtil.getStrByClob(obs[col++]));
			notice.setNtcontent(StringUtil.getString(obs[col++]));
			notice.setCreatedate(StringUtil.getYMD(obs[col++]));
			notice.setModifydate(StringUtil.getYMD(obs[col++]));
			notice.setStatus(StringUtil.getInt(obs[col++],0));
			notice.setReadflag(StringUtil.getString(obs[col++]));
			notice.setReaddate(StringUtil.getYMD(obs[col++]));

			if((NoticeDivision.FLAG_READED + "").equals(notice.getReadflag())) {
				notice.setReadflag("已读");
			} else {
				notice.setReadflag("未读");
			}
			
			resultList.add(notice);
		}
		
		gridBean.setRows(resultList);
		return gridBean;
	}

}
