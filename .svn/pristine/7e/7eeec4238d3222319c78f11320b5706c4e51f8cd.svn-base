package com.yunforge.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.base.dao.PreferenceDao;
import com.yunforge.base.model.Preference;
import com.yunforge.base.service.PreferenceManager;

@Scope("prototype")
@Service
@Transactional(readOnly = true)
public class PreferenceManagerImpl implements PreferenceManager {

	@Autowired
	private PreferenceDao preferenceDao;

	@Override
	public Preference findPreferenceById(String id) {
		return preferenceDao.findOne(id);
	}

	@Override
	public Preference getPreferenceByUserId(String prefName, String userId) {
		return preferenceDao.findByPrefNameAndUserId(prefName, userId);
	}

	@Override
	public Preference getPreferenceByUsername(String prefName, String username) {
		return preferenceDao.findByPrefNameAndUserUsername(prefName, username);
	}

	@Override
	public List<Preference> getPreferencesByUsername(String username) {
		return preferenceDao.findByUserUsername(username);
	}

	@Override
	public Integer countByUserId(String id) {
		return preferenceDao.countByUserId(id);
	}
	
	@Override
	public Integer countByUsername(String username) {
		return preferenceDao.countByUserUsername(username);
	}

	public List<Preference> getPreferencesByUserId(String id) {
		return preferenceDao.findByUserId(id);
	}

	@Override
	@Transactional
	public Preference savePreference(Preference preference) {
		return preferenceDao.save(preference);
	}

}
