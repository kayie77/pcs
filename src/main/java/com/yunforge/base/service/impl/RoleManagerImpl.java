package com.yunforge.base.service.impl;

import static com.yunforge.base.dao.RoleSpecifications.searchResourceRoles;
import static com.yunforge.base.dao.RoleSpecifications.searchRoles;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.base.dao.RoleDao;
import com.yunforge.base.dao.UserRoleDao;
import com.yunforge.base.model.Role;
import com.yunforge.base.model.UserRole;
import com.yunforge.base.service.RoleManager;

@Scope("prototype")
@Service
@Transactional(readOnly = true)
public class RoleManagerImpl implements RoleManager {

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private RoleDao roleDao;
	
	public boolean existsUserRole(String userId,String roleId) {
		
		List<UserRole> list = userRoleDao.findByUserIdAndRoleId(userId, roleId);
		if(list.size() == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public void saveUserRole(UserRole userRole) {
		userRoleDao.saveAndFlush(userRole);
	}

	public void deleteUserRoleByRoleId(String id) {
		userRoleDao.deleteByUserRolePKRoleId(id);
	}

	@Override
	public Role findRoleById(String id) {
		Role role = this.roleDao.findOne(id);
		return role;
	}

	@Override
	public Role findRoleByRoleName(String roleName) {
		Role role = this.roleDao.findByRoleName(roleName);
		return role;
	}

	@Override
	public Page<Role> findAll(Pageable pageable) {
		Page<Role> roles = this.roleDao.findAll(pageable);
		return roles;
	}

	@Override
	public List<Role> findAll(String[] ids) {
		List<String> idList = Arrays.asList(ids);
		return roleDao.findAll(idList);
	}
	
	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}
	
	@Override
	@Transactional
	public Role saveRole(Role role) {
		return roleDao.saveAndFlush(role);
	}

	@Override
	@Transactional
	public void removeRole(Role role) {
		roleDao.delete(role);

	}

	@Override
	@Transactional
	public void removeRoles(List<Role> roles) {
		roleDao.delete(roles);

	}

	@Override
	@Transactional
	public void removeRoles(String[] ids) {
		for (String id : ids) {
			roleDao.delete(id);
		}

	}

	@Override
	public Page<Role> listRoles(String searchField, String searchOper,
			String searchString, Pageable pageable) {
		return roleDao.findAll(
				searchRoles(searchField, searchOper, searchString), pageable);
	}

	@Override
	public Page<Role> listResRoles(String resId, String searchField,
			String searchOper, String searchString, Pageable pageable) {
		return roleDao.findAll(
				searchResourceRoles(resId, searchField, searchOper,
						searchString), pageable);
	}

}
