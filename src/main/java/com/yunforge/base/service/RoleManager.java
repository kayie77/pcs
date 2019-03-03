package com.yunforge.base.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.base.model.Role;
import com.yunforge.base.model.UserRole;

public interface RoleManager {

	public boolean existsUserRole(String userId,String roleId);
	
	public Role findRoleById(String id);

	public void deleteUserRoleByRoleId(String id);
	
	public void saveUserRole(UserRole userRole);
	
	public Role findRoleByRoleName(String roleName);

	public Page<Role> findAll(Pageable pageable);

	public List<Role> findAll(String[] ids);
	
	public List<Role> findAll();
	
	public Role saveRole(Role role);

	public void removeRole(Role role);

	public void removeRoles(List<Role> roles);

	public void removeRoles(String[] ids);

	public Page<Role> listRoles(String searchField, String searchOper,
			String searchString, Pageable pageable);

	public Page<Role> listResRoles(String roleId, String searchField,
			String searchOper, String searchString, Pageable pageable);

}