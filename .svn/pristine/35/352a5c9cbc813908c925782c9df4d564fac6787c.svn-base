package com.yunforge.base.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.base.model.UserRole;

public interface UserRoleDao extends JpaRepository<UserRole, String>, JpaSpecificationExecutor<UserRole> {
	
	@Modifying
	@Transactional
	@Query("delete from UserRole ur where ur.userRolePK.roleId = ?1")
	public void deleteByUserRolePKRoleId(String id);
	
	@Transactional
	@Query(" from UserRole ur where ur.userRolePK.userId = ?1 and ur.userRolePK.roleId = ?2 ")
	public List<UserRole> findByUserIdAndRoleId(String userId,String roleId);
	
//	public List<UserRole> findByUserRolePKUserIdAndUserRolePKRoleId(String userId,String roleId);
//	public List<UserRole> findByUserIdAndRoleId(String userId,String roleId);
}
