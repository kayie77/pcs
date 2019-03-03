package com.yunforge.base.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.base.model.Group;

public interface GroupManager {

	public Group findGroupById(String id);

	public Group findGroupByGroupName(String groupName);

	public Page<Group> findAll(Pageable pageable);

	public List<Group> findAll(String[] ids);
	
	public List<Group> findAll();

	public Group saveGroup(Group group);

	public void removeGroup(Group group);

	public void removeGroups(List<Group> groups);

	public void removeGroups(String[] ids);

	public Page<Group> listGroups(String searchField, String searchOper,
			String searchString, Pageable pageable);

	public Page<Group> listRoleGroups(String roleId, String searchField,
			String searchOper, String searchString, Pageable pageable);
}