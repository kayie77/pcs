package com.yunforge.base.service;

import java.util.List;

import com.yunforge.base.model.Preference;

public interface PreferenceManager {

	public Preference findPreferenceById(String id);

	public Preference getPreferenceByUserId(String prefName, String userId);

	public Preference getPreferenceByUsername(String prefName, String username);
	
	public List<Preference> getPreferencesByUserId(String id);
	
	public Integer countByUserId(String id);
	
	public Integer countByUsername(String username);
	
	public List<Preference> getPreferencesByUsername(String username);
	
	public Preference savePreference(Preference preference);

}