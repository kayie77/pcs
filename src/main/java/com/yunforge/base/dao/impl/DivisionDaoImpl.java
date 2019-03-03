package com.yunforge.base.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.mysql.jdbc.StringUtils;
import com.yunforge.base.model.Division;
import com.yunforge.common.util.StringUtil;

public class DivisionDaoImpl {

	@PersistenceContext
	public EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	public List<Division> findByParentIdAndRolesOrderByNewOrderAsc(String id,String roles){
		List<Division> divisions=new ArrayList<Division>();
		StringBuilder sb=new StringBuilder();
		
		sb.append(" select t.id, "
				+ " t.div_code, "
				+ " t.div_name, "
				+ " t.mnemonic_code, "
				+ " t.ver_num, "
				+ " t.pid, "
				+ " t.div_desc, "
				+ " t.weight, "
				+ " t.wordnum, "
				+ " t.new_order "
				+ " from SYS_DIVISION t ,T_JSTREE_SHOW_DIVCODE jstree "
				+ " where t.div_code= jsTree.DIVCODE ");
		
		if(id==null || id.equals(""))
		{
			sb.append(" and t.pid is null ");
		}else{
			sb.append(" and t.pid ='").append(id).append("' ");
		}
		sb.append(" and jsTree.CS_ID in ").append(roles).append(" ");
		sb.append(" and jsTree.enabled = 'Y' ");
		sb.append(" order by t.new_order asc ");

		
		Query query=this.em.createNativeQuery(sb.toString(), Division.class);
		
		divisions=query.getResultList();
		return divisions;
	}
	
}
