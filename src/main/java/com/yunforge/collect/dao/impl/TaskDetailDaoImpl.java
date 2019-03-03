package com.yunforge.collect.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.yunforge.collect.model.AgrProCategory2;
import com.yunforge.collect.model.TaskDetail;
import com.yunforge.collect.model.TaskMain;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.util.PageUtil;
import com.yunforge.common.util.StringUtil;

public class TaskDetailDaoImpl {

	@PersistenceContext
	private EntityManager em;

	public void deleteBySameAgrProCategory2AndTaskMain(EntityManager em1,String[] taskDetailId) {
		
//		EntityTransaction entityTransaction = em.getTransaction();
//		entityTransaction.begin();
		StringBuffer sql = new StringBuffer();
		sql.append(" delete from oper_taskdetail where id in ( ");
		sql.append(" 	select abc.id ");
		sql.append(" 	from ( ");
		sql.append(" 		select otd_1.id ");
		sql.append(" 		from oper_taskdetail otd,oper_taskdetail otd_1 ");
		sql.append(" 		where otd_1.agrprocategory2 = otd.agrprocategory2 ");
		sql.append(" 		and otd_1.taskmain = otd.taskmain ");
		sql.append(" 		and otd.id in ("+StringUtil.getStr(taskDetailId)+")");
		sql.append(" 	) abc ");
		sql.append(" ) ");
		Query query = em.createNativeQuery(sql.toString());
		query.executeUpdate();
//		entityTransaction.commit();
	}
	
	public Page<TaskDetail> getTaskDetail(Map<String, Object> searchParams,Pageable pageable) {

		String taskMainId = (String)searchParams.get("taskMainId");
		
		//查总数
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(otd.id) ");
		sql.append(" FROM Oper_TaskDetail otd,oper_AgrProCategory2 oapc ");
		sql.append(" WHERE otd.AGRPROCATEGORY2 = oapc.id ");
		sql.append(" 	and otd.TASKMAIN = '"+taskMainId+"' ");
		sql.append(" group by otd.AGRPROCATEGORY2 ");
		Query query = em.createNativeQuery(sql.toString());
		
		List list = query.getResultList();
		int count = list.size();
		GridBean gridBean = new PageUtil().getGridBean((pageable.getPageNumber()+1)+"",pageable.getPageSize(),count);
		
		//分页查询
		sql = new StringBuffer();
		sql.append(" SELECT otd.id,otd.AGRPROCATEGORY2,otd.TASKMAIN,oapc.name,min(otd.remark),min(otd.timeframe),min(otd.nullable) ");
		sql.append(" FROM Oper_TaskDetail otd,oper_AgrProCategory2 oapc ");
		sql.append(" WHERE otd.AGRPROCATEGORY2 = oapc.id ");
		sql.append(" 	and otd.TASKMAIN = '"+taskMainId+"' ");
		sql.append(" group by otd.AGRPROCATEGORY2 ");
//		sql.append(" order by odo.reriod desc ");
		sql.append(" limit " + gridBean.getLimitBegin() + "," + gridBean.getLimitEnd());
		query = em.createNativeQuery(sql.toString());

		List<TaskDetail> resultList = new ArrayList<TaskDetail>();
		list = query.getResultList();

		//封装数据
		for(int i = 0;i < list.size();i++) {
			
			TaskDetail dataReoprtedMain = new TaskDetail();
			Object[] obs = (Object[])list.get(i);

			int col = 0;
			String taskDetailId = StringUtil.getString(obs[col++]);
			String agrprocategory2Id = StringUtil.getString(obs[col++]);
			String taskMainId1 = StringUtil.getString(obs[col++]);
			String agrprocategory2Name = StringUtil.getString(obs[col++]);
			String remark = StringUtil.getString(obs[col++]);
			String timeframe = StringUtil.getString(obs[col++]);
			String nullable = StringUtil.getString(obs[col++]);
			
			TaskMain taskMain = new TaskMain();
			taskMain.setId(taskMainId1);
			
			AgrProCategory2 agrProCategory2 = new AgrProCategory2();
			agrProCategory2.setId(agrprocategory2Id);
			agrProCategory2.setName(agrprocategory2Name);
			
			dataReoprtedMain.setId(taskDetailId);
			dataReoprtedMain.setAgrProCategory2(agrProCategory2);
//			dataReoprtedMain.setDataID(dataID);
			dataReoprtedMain.setDataName(getIndexName(taskMainId,agrprocategory2Id));
			dataReoprtedMain.setRemark(remark);
			dataReoprtedMain.setTaskMain(taskMain);
			dataReoprtedMain.setTimeframe(timeframe);
			dataReoprtedMain.setNullable(Integer.parseInt(nullable));
			
			resultList.add(dataReoprtedMain);
		}
		
		PageImpl p = new PageImpl(resultList,pageable,count);
		return p;
	}
	
	public String getIndexName(String taskMainId,String agrprocategory2Id) {

		StringBuffer result = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append(" select odc.indexname ");
		sql.append(" from Oper_TaskDetail otd,oper_datacollectindex odc ");
		sql.append(" where otd.DATAID = odc.indexcode ");
		sql.append(" and otd.TASKMAIN = '"+taskMainId+"' ");
		sql.append(" and otd.AGRPROCATEGORY2 = '"+agrprocategory2Id+"' ");
		
		Query query = em.createNativeQuery(sql.toString());
		List list = query.getResultList();
		for(int i = 0;i < list.size();i++) {
			String indexName = list.get(i).toString();
			result.append(indexName).append(",");
		}
		
		if(result.length() != 0) {
			return result.substring(0, result.length() - 1);
		}
		return result.toString();
	}
}
