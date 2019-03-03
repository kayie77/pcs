package com.yunforge.base.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yunforge.base.model.Org;

public interface OrgDao extends JpaRepository<Org, String>,
		JpaSpecificationExecutor<Org> {

	public Org findByOrgCode(String orgCode);

	public Org findByOrgName(String orgName);

	public List<Org> findByParentIdIsOrderByWeightAsc(String id);

	public List<Org> findByParentIdIsNullOrderByWeightAsc();

	public Long countByParentIdIsNull();

	public Long countByParentIdIs(String id);
	
	public List<Org> findByParentIdIsOrderByOrgCodeDesc(String id);
	
	public List<Org> findByParentIdIsNullOrderByOrgCodeDesc();
}
