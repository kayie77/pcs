package com.yunforge.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yunforge.base.model.Group;

public interface GroupDao extends JpaRepository<Group, String>,
		JpaSpecificationExecutor<Group> {
	public Group findByGroupName(String groupName);

}
