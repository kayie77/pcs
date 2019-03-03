package com.yunforge.base.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yunforge.base.model.Resource;
import com.yunforge.base.model.Role;

public interface ResourceDao extends JpaRepository<Resource, String>,
		JpaSpecificationExecutor<Resource> {

	public List<Resource> findByParentIsNullOrderByWeightAsc();

	public List<Resource> findByParentIdIsOrderByWeightAsc(String id);

	public List<Resource> findByParentIsNullAndResTypeIsAndEnabledIsOrderByWeightAsc(
			String resType, boolean enabled);

	public List<Resource> findByParentIdIsAndResTypeIsAndEnabledIsOrderByWeightAsc(
			String id, String resType, boolean enabled);

	public Long countByParentIdIs(String id);

	public Long countByParentIdIsAndResTypeIs(String id, String resType);

	public List<Resource> findByResTypeIs(String resType);

	public List<Resource> findByRolesId(String id);

	public List<Resource> findByRolesIdIn(List<String> ids);

	public List<Resource> findByPermissionIsNotNull();

}
