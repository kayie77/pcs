package com.yunforge.collect.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.collect.model.PersonGroup;

public interface PersonGroupManager {
	
	PersonGroup  getPersonGroup(String id);
	List<PersonGroup> getAllPersonGroup();
	
	PersonGroup  savePersonGroup(PersonGroup  personGroup );
	PersonGroup  newPersonGroup(PersonGroup  personGroup);
    void delPersonGroups(String[] id) throws Exception;
	
	Page<PersonGroup> getPersonGroup(String ctgId, Map<String, Object> searchParams,Pageable pageable);
}
