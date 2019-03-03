package com.yunforge.base.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.yunforge.base.model.Role;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.bean.Params;
import com.yunforge.common.util.PageUtil;
import com.yunforge.common.util.StringUtil;

public class IndexDaoImpl {

	@PersistenceContext
	public EntityManager em;
	
	public GridBean queryIndexRole(Params params) {

		String reportId = (String)params.get("reportId");
		String indexId = (String)params.get("indexId");
		String pageIndex = (String)params.get("pageIndex");
		Integer pageSize = (Integer)params.get("pageSize");
		String sidx = (String)params.get("sidx");
		String sord = (String)params.get("sord");

		Map<Integer,String> conditionMap = new HashMap<Integer,String>();
		Map<String,String> orderMap = new HashMap<String,String>();
		orderMap.put("roleName", "sr.role_name_cn");
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(sr.id) ");
		sql.append(" from sys_role sr ");
		if(StringUtil.notEmpty(sidx)) {
			sql.append(" order by " + orderMap.get(sidx) + " " + sord);
		} else {
			sql.append(" order by sr.role_name_cn ");
		}
		
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
		sql.append(" select sr.id,sr.role_name_cn, ");
		sql.append(" ( ");
		sql.append(" select max(ti.id) ");
		sql.append(" from index_role ir,t_index ti ");
		sql.append(" where ir.index_id = ti.id ");
		sql.append(" and ir.role_id = sr.id ");
		if(StringUtil.notEmpty(reportId)) {
			sql.append(" and ti.reportid = '"+reportId+"' ");
		}
		if(StringUtil.notEmpty(indexId)) {
			sql.append(" and ir.index_id = '"+indexId+"' ");
		}
		sql.append(" ) ");
		sql.append(" from sys_role sr ");
		if(StringUtil.notEmpty(sidx)) {
			sql.append(" order by " + orderMap.get(sidx) + " " + sord);
		} else {
			sql.append(" order by sr.role_name_cn ");
		}
		sql.append(" ) y ");
		sql.append(" ) z where rownum <= " + gridBean.getRowEnd());
		sql.append(" ) x where r > " + gridBean.getRowBegin());
		
		query = em.createNativeQuery(sql.toString());
		for(java.util.Iterator i = set.iterator();i.hasNext();) {
			Map.Entry entry = (Map.Entry)i.next();
			query.setParameter(entry.getKey().toString(), entry.getValue());
		}


		List<Role> resultList = new ArrayList<Role>();
		list = query.getResultList();
		for(int i = 0;i < list.size();i++) {
			
			Role role = new Role();
			Object[] obs = (Object[])list.get(i);
			
			int col = 0;
			role.setId(StringUtil.getString(obs[col++]));
			role.setRoleName(StringUtil.getString(obs[col++]));
			role.setHasRole(StringUtil.getString(obs[col++]));
			resultList.add(role);
		}
		
		gridBean.setRows(resultList);
		return gridBean;
	}

}
