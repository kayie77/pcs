package com.yunforge.base.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.base.model.Resource;

public interface ResourceManager {

	public Resource findById(String id);

	public List<Resource> getChildren(String pid);
	
	public boolean hasChildren(String pid);

	public Page<Resource> findAll(Pageable pageable);

	public List<Resource> findAll(String[] ids);

	public Resource saveResource(Resource resource);

	public void removeResource(Resource resource);

	public void removeResources(List<Resource> resources);

	public void removeResources(String[] ids);

	public Page<Resource> listResources(String pid, String filters,
			Pageable pageable);

	public Set<String> getAllPermissions();

	public List<Resource> getTopMenus();

	public List<Resource> getSubMenus(String pid);

	public boolean hasSubMenus(String pid);

}