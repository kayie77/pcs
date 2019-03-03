package com.yunforge.base.service.impl;

import static com.yunforge.base.dao.GroupSpecifications.searchGroups;
import static com.yunforge.base.dao.GroupSpecifications.searchRoleGroups;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.base.dao.GroupDao;
import com.yunforge.base.model.Group;
import com.yunforge.base.service.GroupManager;

@Scope("prototype")
@Service
@Transactional(readOnly = true)
public class GroupManagerImpl implements GroupManager {

	@Autowired
	private GroupDao groupDao;

	@Override
	public Group findGroupById(String id) {
		Group group = this.groupDao.findOne(id);
		return group;
	}

	@Override
	public Group findGroupByGroupName(String groupName) {
		Group group = this.groupDao.findByGroupName(groupName);
		return group;
	}

	@Override
	public Page<Group> findAll(Pageable pageable) {
		Page<Group> groups = this.groupDao.findAll(pageable);
		return groups;
	}

	@Override
	public List<Group> findAll(String[] ids) {
		List<String> idList = Arrays.asList(ids);
		return groupDao.findAll(idList);
	}

	@Override
	@Transactional
	public Group saveGroup(Group group) {
		return groupDao.save(group);
	}

	@Override
	@Transactional
	public void removeGroup(Group group) {
		groupDao.delete(group);

	}

	@Override
	@Transactional
	public void removeGroups(List<Group> groups) {
		groupDao.delete(groups);

	}

	@Override
	@Transactional
	public void removeGroups(String[] ids) {
		for (String id : ids) {
			groupDao.delete(id);
		}

	}
	
	public List<Group> findAll() {
		return groupDao.findAll();
	}

	@Override
	public Page<Group> listGroups(String searchField, String searchOper,
			String searchString, Pageable pageable) {
		return groupDao.findAll(
				searchGroups(searchField, searchOper, searchString), pageable);
	}

	@Override
	public Page<Group> listRoleGroups(String roleId, String searchField,
			String searchOper, String searchString, Pageable pageable) {
		return groupDao
				.findAll(
						searchRoleGroups(roleId, searchField, searchOper,
								searchString), pageable);
	}

}
