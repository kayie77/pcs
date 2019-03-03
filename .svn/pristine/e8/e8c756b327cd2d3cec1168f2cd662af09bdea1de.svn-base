package com.yunforge.cms.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.cms.model.InfoCat;

public interface InfoCatManager {

	public InfoCat findById(String id);

	public List<InfoCat> getChildren(String pid);
	
	public boolean hasChildren(String pid);

	public Page<InfoCat> findAll(Pageable pageable);

	public List<InfoCat> findAll(String[] ids);

	public InfoCat saveInfoCat(InfoCat infoCat);

	public void removeInfoCat(InfoCat infoCat);

	public void removeInfoCats(List<InfoCat> infoCats);

	public void removeInfoCats(String[] ids);

}