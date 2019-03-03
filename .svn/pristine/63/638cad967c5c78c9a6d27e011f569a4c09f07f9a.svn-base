package com.yunforge.base.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yunforge.base.model.Preference;

public interface PreferenceDao extends JpaRepository<Preference, String>,
		JpaSpecificationExecutor<Preference> {

	public Preference findByPrefNameAndUserUsername(String prefName,
			String username);

	public Preference findByPrefNameAndUserId(String prefName, String id);

	public List<Preference> findByUserUsername(String username);

	public List<Preference> findByUserId(String id);
	
	public Integer countByUserId(String id);
	
	public Integer countByUserUsername(String username);

}
