package com.yunforge.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yunforge.base.model.User;

public interface UserDao extends JpaRepository<User, String>,
		JpaSpecificationExecutor<User> {

	public User findByUsername(String username);
	
}