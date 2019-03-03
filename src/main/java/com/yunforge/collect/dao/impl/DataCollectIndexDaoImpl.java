package com.yunforge.collect.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.yunforge.collect.model.DataCollectIndex;
import com.yunforge.collect.model.DataReoprtedMain;
import com.yunforge.collect.model.TaskMain;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.util.PageUtil;
import com.yunforge.common.util.StringUtil;

public class DataCollectIndexDaoImpl {

	@PersistenceContext
	private EntityManager em;
	
	public Page<DataCollectIndex> getDataCollectIndex(Map<String, Object> searchParams,Pageable pageable) {

		String taskMainId = (String)searchParams.get("taskMainId");
		String personId = (String)searchParams.get("personId");
		String reriod = (String)searchParams.get("reriod");
		
		//查总数
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(ddc.id) ");
		sql.append(" from oper_datacollectindex ddc ");
		Query query = em.createNativeQuery(sql.toString());
		
		List list = query.getResultList();
		int count = StringUtil.getCount(list);
		
		GridBean gridBean = new PageUtil().getGridBean((pageable.getPageNumber()+1)+"",pageable.getPageSize(),count);
		
		//分页查询
		sql = new StringBuffer();
		sql.append(" select ddc.id,ddc.indexcode,ddc.indexname ");
		sql.append(" from oper_datacollectindex ddc ");
		sql.append(" order by ddc.indexcode ");
		sql.append(" limit " + gridBean.getLimitBegin() + "," + gridBean.getLimitEnd());
		query = em.createNativeQuery(sql.toString());

		List<DataCollectIndex> resultList = new ArrayList<DataCollectIndex>();
		list = query.getResultList();

		//封装数据
		for(int i = 0;i < list.size();i++) {
			
			DataCollectIndex dataCollectIndex = new DataCollectIndex();
			Object[] obs = (Object[])list.get(i);
			
//			select odo.id,odo.reriod,ddc.name,odo.reportedstatus
			int col = 0;
			dataCollectIndex.setId(StringUtil.getString(obs[col++]));
			dataCollectIndex.setIndexCode(StringUtil.getString(obs[col++]));
			dataCollectIndex.setIndexName(StringUtil.getString(obs[col++]));
			
			resultList.add(dataCollectIndex);
		}
		
		PageImpl p = new PageImpl(resultList,pageable,count);
		return p;
	}
	
}
