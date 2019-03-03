package com.yunforge.base.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.base.model.Group;
import com.yunforge.base.model.User;

public interface UserManager {

	public User findUserById(String id);

	public User findUserByUsername(String username);

	public boolean checkPassword(String username, String oldPassword);

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean updatePassword(String username, String newPassword);

	public Page<User> findAll(Pageable pageable);

	public List<User> findAll(String[] ids);

	public String getFullName(String username);

	public boolean checkUser(String username);

	public User saveUser(User user);

	public void removeUser(User user);

	public void removeUsers(List<User> users);

	public void removeUsers(String[] ids);

	public Page<User> search(String filters, Pageable pageable, String searchField, String searchOper, String searchString,String userNameParam);

	public Page<User> listRoleUsers(String roleId, String filters,
			Pageable pageable);

	public Page<User> listGroupUsers(String groupId, String filters,
			Pageable pageable);

	public Set<String> getUserAllRoles(String username);
	
	public Set<String> findUserPermissionsByUsername(String username);
}