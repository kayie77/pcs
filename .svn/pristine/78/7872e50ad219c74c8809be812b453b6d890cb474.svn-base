package com.yunforge.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.base.dao.DivisionDao;
import com.yunforge.base.dao.impl.DivisionDaoImpl;
import com.yunforge.base.model.Division;
import com.yunforge.base.service.DivisionManager;
import com.yunforge.common.util.StringUtil;

@Scope("prototype")
@Service
@Transactional(readOnly = true)
public class DivisionManagerImpl implements DivisionManager {

	@Autowired
	private DivisionDao divisionDao;
	
	@Autowired
	private DivisionDaoImpl divisionDaoImpl;
	
	public List<Division> findAllDivOrderByNewOrder() {
		List<Division> list1 = new ArrayList();
		List<Object[]> list = divisionDao.findAllDivOrderByNewOrder();
		for(int i = 0;i < list.size();i++) {
			Object[] obs = list.get(i);
			Division d = new Division();
			d.setDivCode(obs[0].toString());
			d.setDivName(obs[1].toString());
			list1.add(d);
		}
		return list1;
	}
	
	public List<Division> findByDivIdList(List<String> divIdList) {
		return divisionDao.findByIdInOrderByDivCodeDesc(divIdList);
	}
	
	public List<Division> findChildByDivisionCodeIncludeSelf(String divisionCode) {
		return divisionDao.findByDivCodeLikeAndDivCodeNotOrderByNewOrder(StringUtil.replaceEnd0(divisionCode) + "%");
	}
	
	public List<Division> findChildByDivisionCode(String divisionCode) {
		return divisionDao.findByDivCodeLikeAndDivCodeNotOrderByNewOrder(StringUtil.replaceEnd0(divisionCode) + "%",divisionCode);
	}
	
	@Override
	public Division findById(String id) {
		return divisionDao.findOne(id);
	}
	
	@Override
	public List<Division> findAll(){
		return divisionDao.findAll();
	}
	
	public List<Division> queryCreateDivId(String createDivType) {
		
		List<Division> resultList = new ArrayList<Division>();
		List<Division> resultList1 = new ArrayList<Division>();
		
		if("1".equals(createDivType)) {
			Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "divCode"));
			resultList = divisionDao.findAll(sort);
		}

		if("2".equals(createDivType)) {
			Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "divCode"));
			resultList = divisionDao.findByDivCodeLikeAndParentNotNull("____00", sort);
		}
		
		if("3".equals(createDivType)) {
			Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "divCode"));
			resultList = divisionDao.findByDivCodeNotLikeAndParentNotNull("____00", sort);
		}
		
		for(Division d:resultList) {
			
			Division d1 = new Division();
			d1.setId(d.getId());
			d1.setDivName(d.getDivName());
			d1.setDivCode(d.getDivCode());
			resultList1.add(d1);
		}
		return resultList1;
//		return divisionDaoImpl.queryCreateDivId(createDivType);
	}

	@Override
	public Division findByDivisionName(String divisionName) {
		return divisionDao.findByDivName(divisionName);
	}

	@Override
	public Division findByDivisionCode(String divisionCode) {
		return divisionDao.findByDivCode(divisionCode);
	}

	@Override
	public boolean hasChildren(String pid) {
		if (StringUtils.isNotBlank(pid)) {
			return divisionDao.countByParentIdIs(pid)>0;
		}
		return divisionDao.countByParentIdIsNull()>0;
	}
	
	@Override
	public List<Division> getChildren(String pid)
	{
		if(StringUtils.isNotBlank(pid))
		{
			return this.divisionDao.findByParentIdIsOrderByWeightAsc(pid);
		}else{
			return this.divisionDao.findByParentIdIsNullOrderByWeightAsc();
		}
	}
	
	@Override
	public List<Division> getChildrenPidRoles(String pid,List<String> roles) {
		StringBuilder strBuilder=new StringBuilder();
		strBuilder.append(" ('###'");
		for(String role:roles){
			strBuilder.append(",").append("'").append(role).append("'");
		}
		strBuilder.append(") ");
		
		if (StringUtils.isNotBlank(pid)) {
			return this.divisionDaoImpl.findByParentIdAndRolesOrderByNewOrderAsc(pid, strBuilder.toString());
		}
		return this.divisionDaoImpl.findByParentIdAndRolesOrderByNewOrderAsc(null, strBuilder.toString());
	}

	@Override
	@Transactional
	public Division saveDivision(Division division) {
		return this.divisionDao.save(division);
	}

	@Override
	@Transactional
	public void removeDivision(Division division) {
		this.divisionDao.delete(division);

	}

	@Override
	public List<Division> findSubDivision(String id)
	{
		return this.divisionDao.findSubDivision(id);
	}
	
	@Override
	public List<Division> findXianQuDivision()
	{
		return this.divisionDao.findXianQuDivision();
	}
	@Override
	public List<Division> findCityDivision(){
		return this.divisionDao.findCityDivision();
	}
}
