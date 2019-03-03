package com.yunforge.base.service.impl;

import static com.yunforge.base.dao.OrgSpecifications.findOrgs;
import static com.yunforge.base.dao.OrgSpecifications.findRoleOrgs;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.base.dao.OrgDao;
import com.yunforge.base.model.Org;
import com.yunforge.base.service.OrgManager;

@Scope("prototype")
@Service
@Transactional(readOnly = true)
public class OrgManagerImpl implements OrgManager {

	@Autowired
	private OrgDao orgDao;

	@Override
	public Org findById(String id) {
		return orgDao.findOne(id);
	}

	@Override
	public Org findByOrgCode(String orgCode) {
		return orgDao.findByOrgCode(orgCode);
	}

	@Override
	public Org findByOrgName(String orgName) {
		return orgDao.findByOrgName(orgName);
	}

	@Override
	public boolean hasChildren(String pid) {
		if (StringUtils.isNotBlank(pid)) {
			return orgDao.countByParentIdIs(pid)>0;
		}
		return orgDao.countByParentIdIsNull()>0;
	}
	
	@Override
	public List<Org> getChildren(String pid) {
		if (StringUtils.isNotBlank(pid)) {
			return orgDao.findByParentIdIsOrderByWeightAsc(pid);
		}
		return orgDao.findByParentIdIsNullOrderByWeightAsc();
	}

	@Override
	public List<Org> findByUsername(String username) {
		return null;
	}

	@Override
	public Page<Org> findAll(Pageable pageable) {
		return orgDao.findAll(pageable);
	}

	@Override
	public List<Org> findAll(String[] ids) {
		List<String> idList = Arrays.asList(ids);
		return orgDao.findAll(idList);
	}

	@Override
	@Transactional
	public Org saveOrg(Org org) {
		return orgDao.saveAndFlush(org);
	}
	
	@Override
	@Transactional
	public List<Org> saveOrgs(List<Org> orgs) {
		return orgDao.save(orgs);
	}

	@Override
	@Transactional
	public void removeOrg(Org org) {
		orgDao.delete(org);

	}

	@Override
	@Transactional
	public void removeOrgs(List<Org> orgs) {
		orgDao.delete(orgs);
	}

	@Override
	@Transactional
	public void removeOrgs(String[] ids) {
		List<Org> orgList = findAll(ids);
		orgDao.deleteInBatch(orgList);

	}

	@Override
	public Page<Org> listOrgs(String pid, String filters, Pageable pageable) {
		return orgDao.findAll(findOrgs(pid, filters), pageable);
	}
	
	@Override
	public Page<Org> listRoleOrgs(String roleId, String filters, Pageable pageable) {
		return orgDao.findAll(findRoleOrgs(roleId, filters), pageable);
	}
	
	@Override
	public String getOrgCode(String pid) {
		String res = "10000";
		List<Org> orgs = null;
		if (StringUtils.isNotBlank(pid)) {
			orgs =  orgDao.findByParentIdIsOrderByOrgCodeDesc(pid);
			int orgCode = 0;
			if(orgs.size()>0){
				Org org = orgs.get(0);
				orgCode = Integer.parseInt(org.getOrgCode())+1;
			}else{
				Org parent = orgDao.findOne(pid);
				orgCode = Integer.parseInt(parent.getOrgCode())+1;
			}
			res = ""+orgCode;
		}else{
			orgs =  orgDao.findByParentIdIsNullOrderByOrgCodeDesc();
			if(orgs.size()>0){
				Org org = orgs.get(0);
				String parentCode = org.getOrgCode();
				String starCodeStr = parentCode.substring(0,1);
				int starCode = Integer.parseInt(starCodeStr)+1;
				res = starCode+"0000";
			}
		}
		
		return res;
	}

}
