package com.yunforge.base.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.base.model.Org;

public interface OrgManager {

	public Org findById(String id);

	public Org findByOrgCode(String orgCode);

	public Org findByOrgName(String orgName);

	public boolean hasChildren(String pid);

	public List<Org> getChildren(String pid);

	public List<Org> findByUsername(String username);

	public Page<Org> findAll(Pageable pageable);

	public List<Org> findAll(String[] ids);

	public Org saveOrg(Org org);

	public List<Org> saveOrgs(List<Org> orgs);

	public void removeOrg(Org org);

	public void removeOrgs(List<Org> orgs);

	public void removeOrgs(String[] ids);

	public Page<Org> listOrgs(String pid, String filters, Pageable pageable);

	public Page<Org> listRoleOrgs(String roleId, String filters, Pageable pageable);

	public String getOrgCode(String pid);

}