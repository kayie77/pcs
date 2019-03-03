package com.yunforge.base.service.impl;

import static com.yunforge.base.dao.UserSpecifications.searchGroupUsers;
import static com.yunforge.base.dao.UserSpecifications.searchRoleUsers;
import static com.yunforge.base.dao.UserSpecifications.searchUsers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.base.dao.GroupDao;
import com.yunforge.base.dao.PersonDao;
import com.yunforge.base.dao.ResourceDao;
import com.yunforge.base.dao.RoleDao;
import com.yunforge.base.dao.UserDao;
import com.yunforge.base.model.Group;
import com.yunforge.base.model.Org;
import com.yunforge.base.model.Person;
import com.yunforge.base.model.Resource;
import com.yunforge.base.model.Role;
import com.yunforge.base.model.User;
import com.yunforge.base.service.UserManager;
import com.yunforge.core.annotation.MethodRemark;

@Scope("prototype")
@Service
@Transactional(readOnly = true)
public class UserManagerImpl implements UserManager {
	final static Log log = LogFactory.getLog(UserManagerImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private PersonDao personDao;

	@Autowired
	private GroupDao groupDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private ResourceDao resourceDao;

	@Override
	@MethodRemark(remark = "通过用户Id查找用户实体")
	public User findUserById(String id) {
		User user = this.userDao.findOne(id);
		user.setFullName(user.getPerson().getPersName());
		return user;
	}

	@Override
	@MethodRemark(remark = "通过用户登录名查找用户实体")
	public User findUserByUsername(String username) {
		User user = this.userDao.findByUsername(username);
		user.setFullName(user.getPerson().getPersName());
		return user;
	}

	@Override
	@MethodRemark(remark = "检查用户密码是否相同")
	public boolean checkPassword(String username, String oldPassword) {
		User user = this.userDao.findByUsername(username);
		return user.getPassword().equals(oldPassword);
	}

	@Override
	@Transactional
	@MethodRemark(remark = "修改用户密码")
	public boolean updatePassword(String username, String newPassword) {
		User user = this.userDao.findByUsername(username);
		if (user != null) {
			user.setPassword(newPassword);
			userDao.save(user);
			return true;
		} else {
			return false;
		}

	}

	@Override
	@MethodRemark(remark = "通过用户ID数组查找用户实体列表")
	public List<User> findAll(String[] ids) {
		List<String> idList = Arrays.asList(ids);
		return userDao.findAll(idList);
	}

	@Override
	@MethodRemark(remark = "分页列出所有用户")
	public Page<User> findAll(Pageable pageable) {
		Page<User> users = this.userDao.findAll(pageable);
		return users;
	}

	@Override
	@MethodRemark(remark = "通过用户登录名获取用户全名")
	public String getFullName(String username) {
		String fullName = null;
		Person person = this.personDao.findByUserUsernameIs(username);
		if (person != null) {
			fullName = person.getPersName();
		}
		return fullName;
	}

	@Override
	@MethodRemark(remark = "通过用户名检查用户是否存在")
	public boolean checkUser(String username) {
		boolean ret = false;
		User user = this.userDao.findByUsername(username);
		if (user != null) {
			ret = true;
		}
		return ret;
	}

	@Override
	@MethodRemark(remark = "保存用户")
	@Transactional
	public User saveUser(User user) {
		return userDao.saveAndFlush(user);
	}

	@Override
	@MethodRemark(remark = "删除指定用户")
	@Transactional
	public void removeUser(User user) {
		userDao.delete(user);
	}

	@Override
	@Transactional
	@MethodRemark(remark = "删除列表中的用户")
	public void removeUsers(List<User> users) {
		userDao.delete(users);
	}

	@Override
	@Transactional
	@MethodRemark(remark = "删除ID数组指定的用户")
	public void removeUsers(String[] ids) {
		for (String id : ids) {
			userDao.delete(id);
		}
	}

	@Override
	@MethodRemark(remark = "搜索用户")
	public Page<User> search(String filters, Pageable pageable, String searchField, String searchOper, String searchString,String userNameParam) {
		return userDao.findAll(searchUsers(filters,searchField,searchOper,searchString,userNameParam), pageable);
	}

	@Override
	@MethodRemark(remark = "列出角色用户")
	public Page<User> listRoleUsers(String roleId, String filters,
			Pageable pageable) {
		return userDao.findAll(searchRoleUsers(roleId, filters), pageable);
	}

	@Override
	@MethodRemark(remark = "列出群组用户")
	public Page<User> listGroupUsers(String groupId, String filters,
			Pageable pageable) {
		return userDao.findAll(searchGroupUsers(groupId, filters), pageable);
	}

	@Override
	@MethodRemark(remark = "获取用户所有角色")
	public Set<String> getUserAllRoles(String username) {
		User user = findUserByUsername(username);
		List<Role> roleList = new ArrayList<Role>();
		roleList.addAll(user.getRoles());
		Set<String> roles = new HashSet<String>(0);
		List<Group> groups = user.getGroups();
		Org org = user.getPerson().getOrg();
		roleList.addAll(org.getRoles());
		for (Group group : groups) {
			roleList.addAll(group.getRoles());
		}
		for (Role role : roleList) {
			roles.add(role.getRoleName());
		}
		return roles;
	}

	@Override
	@MethodRemark(remark = "获取用户所有权限")
	public Set<String> findUserPermissionsByUsername(String username) {
		User user = findUserByUsername(username);
		List<Role> roleList = new ArrayList<Role>();
		roleList.addAll(user.getRoles());
		List<Group> groups = user.getGroups();
		Org org = user.getPerson().getOrg();
		roleList.addAll(org.getRoles());
		for (Group group : groups) {
			roleList.addAll(group.getRoles());
		}
		List<Resource> resList=new ArrayList<Resource>();
		for(Role role:roleList){
			resList.addAll(role.getResources());
		}
		Set<String> perms = new HashSet<String>(0);
		if (resList != null) {
			for (Resource r : resList) {
				perms.add(r.getPermission());
			}
		}
		return perms;
	}

}
