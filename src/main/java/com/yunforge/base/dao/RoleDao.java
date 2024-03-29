package com.yunforge.base.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.yunforge.base.model.Role;

public interface RoleDao extends JpaRepository<Role, String>,
		JpaSpecificationExecutor<Role> {
	public Role findByRoleName(String roleName);

	@Query(value=" select ir.role_id from index_role ir where ir.index_id = ?1 ",nativeQuery = true)
	public List<String> findRoleIdByIndexId(String indexId);
	
	@Query(value=" select ir.role_id from indexreportnum_role ir where ir.indexreportnum_id = ?1 ",nativeQuery = true)
	public List<String> findRoleIdByIndexReportNumId(String indexId);
}
