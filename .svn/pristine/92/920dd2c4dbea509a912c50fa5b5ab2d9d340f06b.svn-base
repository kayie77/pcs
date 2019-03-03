package com.yunforge.base.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.yunforge.base.model.Message;
import com.yunforge.base.model.MessageDTO;
import com.yunforge.base.model.MessageFile;
import com.yunforge.base.model.MessageSend;
import com.yunforge.base.model.Notice;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.bean.Params;
import com.yunforge.common.util.PageUtil;
import com.yunforge.common.util.StringUtil;

public class MessageDaoImpl {

	@PersistenceContext
	public EntityManager em;
	
	public GridBean queryRecvMessage(Params params) {
		
		String title = (String)params.get("title");
		String status = (String)params.get("status");
		String div_name = (String)params.get("div_name");
		String divcode = (String)params.get("divcode");
		String pageIndex = (String)params.get("pageIndex");
		Integer pageSize = (Integer)params.get("pageSize");
		String sidx = (String)params.get("sidx");
		String sord = (String)params.get("sord");

		Map<Integer,String> conditionMap = new HashMap<Integer,String>();
		Map<String,String> orderMap = new HashMap<String,String>();
		orderMap.put("createdate", "m.createdate");
		orderMap.put("title", "m.title");
		orderMap.put("div_name", "sd.div_name");

		StringBuffer conditionSql = new StringBuffer();
		StringBuffer orderSql = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(m.id) ");
		sql.append(" from message m inner join messagesend ms on m.id = ms.messageid ");
		sql.append(" inner join sys_division sd on sd.id = m.createdivid ");
		sql.append(" inner join sys_division sd_recv on sd_recv.id = ms.divisionid ");
		sql.append(" where 1=1 ");

		conditionSql.append(" and sd_recv.div_code = '" +divcode+ "' ");
		conditionSql.append(" and m.parentid is null ");
		if(StringUtil.notEmpty(title)) {
			conditionSql.append(" and m.title like ?1 ");
			conditionMap.put(1, "%" + title + "%");
		}
		if(StringUtil.notEmpty(div_name)) {
			conditionSql.append(" and sd.div_name like ?2 ");
			conditionMap.put(2, "%" + div_name + "%");
		}
		if(StringUtil.notEmpty(status)) {
			
			/*    
			<option value='1'>未阅读</option>  
		    <option value='2'>已阅读</option>  
		    <option value='3'>已回复</option>  
		    <option value='4'>重要</option>  
		    <option value='5'>要求回复</option>
		    */
			
			if("1".equals(status)) {
				conditionSql.append(" and ms.readflag = ?3 ");
				conditionMap.put(3, "0");
			}
			if("2".equals(status)) {
				conditionSql.append(" and ms.readflag = ?3 ");
				conditionMap.put(3, "1");
			}
			if("3".equals(status)) {
				conditionSql.append(" and ms.replayflag = ?3 ");
				conditionMap.put(3, "1");
			}
			if("4".equals(status)) {
				conditionSql.append(" and m.important = ?3 ");
				conditionMap.put(3, "1");
			}
			if("5".equals(status)) {
				conditionSql.append(" and m.needreplay = ?3 ");
				conditionMap.put(3, "1");
			}
		}
		sql.append(conditionSql.toString());
		
		if(StringUtil.notEmpty(sidx)) {
			orderSql.append(" order by " + orderMap.get(sidx) + " " + sord);
		} else {
			orderSql.append(" order by m.createdate desc ");
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
		sql.append(" select ms.id,ms.readflag,m.important,m.needreplay,ms.replayflag,sd.div_name,m.title,m.createdate ");
		sql.append(" from message m inner join messagesend ms on m.id = ms.messageid ");
		sql.append(" inner join sys_division sd on sd.id = m.createdivid ");
		sql.append(" inner join sys_division sd_recv on sd_recv.id = ms.divisionid ");
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



		List<MessageDTO> resultList = new ArrayList<MessageDTO>();
		list = query.getResultList();
		for(int i = 0;i < list.size();i++) {
			
			MessageDTO message = new MessageDTO();
			Object[] obs = (Object[])list.get(i);
			
			int col = 0;
			message.setId(StringUtil.getString(obs[col++]));
			message.setReadflag(StringUtil.getString(obs[col++]));
			message.setImportant(StringUtil.getString(obs[col++]));
			message.setNeedreplay(StringUtil.getString(obs[col++]));
			message.setReplayflag(StringUtil.getString(obs[col++]));
			message.setDiv_name(StringUtil.getString(obs[col++]));
			message.setTitle(StringUtil.getString(obs[col++]));
			message.setCreatedate(StringUtil.subStr(obs[col++],0,19));
			
			resultList.add(message);
		}
		
		gridBean.setRows(resultList);
		return gridBean;
	}
	
	public GridBean querySendedMessage(Params params) {

		String title = (String)params.get("title");
		String status = (String)params.get("status");
		String div_name = (String)params.get("div_name");
		String divcode = (String)params.get("divcode");
		String pageIndex = (String)params.get("pageIndex");
		Integer pageSize = (Integer)params.get("pageSize");
		String sidx = (String)params.get("sidx");
		String sord = (String)params.get("sord");

		Map<Integer,String> conditionMap = new HashMap<Integer,String>();
		Map<String,String> orderMap = new HashMap<String,String>();
		orderMap.put("createdate", "m.createdate");
		orderMap.put("title", "m.title");
		orderMap.put("div_name", "sd.div_name");

		StringBuffer conditionSql = new StringBuffer();
		StringBuffer orderSql = new StringBuffer();
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(m.id) ");
		sql.append(" from message m inner join messagesend ms on m.id = ms.messageid ");
		sql.append(" inner join sys_division sd on sd.id = ms.divisionid ");
		sql.append(" inner join sys_division sd_create on sd_create.id = m.createdivid ");
		sql.append(" where 1=1 ");

		conditionSql.append(" and sd_create.div_code = '" +divcode+ "' ");
		conditionSql.append(" and m.parentid is null ");
		if(StringUtil.notEmpty(title)) {
			conditionSql.append(" and m.title like ?1 ");
			conditionMap.put(1, "%" + title + "%");
		}
		if(StringUtil.notEmpty(div_name)) {
			conditionSql.append(" and sd.div_name like ?2 ");
			conditionMap.put(2, "%" + div_name + "%");
		}
		if(StringUtil.notEmpty(status)) {
			
			/*    
			<option value='1'>未阅读</option>  
		    <option value='2'>已阅读</option>  
		    <option value='3'>已回复</option>  
		    <option value='4'>重要</option>  
		    <option value='5'>要求回复</option>
		    */
			
			if("1".equals(status)) {
				conditionSql.append(" and ms.readflag = ?3 ");
				conditionMap.put(3, "0");
			}
			if("2".equals(status)) {
				conditionSql.append(" and ms.readflag = ?3 ");
				conditionMap.put(3, "1");
			}
			if("3".equals(status)) {
				conditionSql.append(" and ms.replayflag = ?3 ");
				conditionMap.put(3, "1");
			}
			if("4".equals(status)) {
				conditionSql.append(" and m.important = ?3 ");
				conditionMap.put(3, "1");
			}
			if("5".equals(status)) {
				conditionSql.append(" and m.needreplay = ?3 ");
				conditionMap.put(3, "1");
			}
		}
		sql.append(conditionSql.toString());
		
		if(StringUtil.notEmpty(sidx)) {
			orderSql.append(" order by " + orderMap.get(sidx) + " " + sord);
		} else {
			orderSql.append(" order by m.createdate desc ");
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
		sql.append(" select ms.id,ms.readflag,m.important,m.needreplay,ms.replayflag,sd.div_name,m.title,m.createdate ");
		sql.append(" from message m inner join messagesend ms on m.id = ms.messageid ");
		sql.append(" inner join sys_division sd on sd.id = ms.divisionid ");
		sql.append(" inner join sys_division sd_create on sd_create.id = m.createdivid ");
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

		
		List<MessageDTO> resultList = new ArrayList<MessageDTO>();
		list = query.getResultList();
		for(int i = 0;i < list.size();i++) {
			
			MessageDTO message = new MessageDTO();
			Object[] obs = (Object[])list.get(i);
			
			int col = 0;
			message.setId(StringUtil.getString(obs[col++]));
			message.setReadflag(StringUtil.getString(obs[col++]));
			message.setImportant(StringUtil.getString(obs[col++]));
			message.setNeedreplay(StringUtil.getString(obs[col++]));
			message.setReplayflag(StringUtil.getString(obs[col++]));
			message.setDiv_name(StringUtil.getString(obs[col++]));
			message.setTitle(StringUtil.getString(obs[col++]));
			message.setCreatedate(StringUtil.subStr(obs[col++],0,19));
			
			resultList.add(message);
		}
		
		gridBean.setRows(resultList);
		return gridBean;
	}

	
}
