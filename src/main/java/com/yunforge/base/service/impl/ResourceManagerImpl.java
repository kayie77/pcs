package com.yunforge.base.service.impl;

import static com.yunforge.base.dao.ResourceSpecifications.searchResources;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.base.dao.ResourceDao;
import com.yunforge.base.model.Resource;
import com.yunforge.base.service.ResourceManager;

@Scope("prototype")
@Service
@Transactional(readOnly = true)
public class ResourceManagerImpl implements ResourceManager {

	@Autowired
	private ResourceDao resourceDao;

	@Override
	public Resource findById(String id) {
		return resourceDao.findOne(id);
	}

	@Override
	public List<Resource> getChildren(String pid) {
		if (StringUtils.isNotBlank(pid)) {
			return resourceDao.findByParentIdIsOrderByWeightAsc(pid);
		} else {
			return resourceDao.findByParentIsNullOrderByWeightAsc();
		}

	}

	@Override
	public boolean hasChildren(String pid) {
		return resourceDao.countByParentIdIs(pid).intValue() > 0;
	}

	@Override
	public Page<Resource> findAll(Pageable pageable) {
		return resourceDao.findAll(pageable);
	}

	@Override
	public List<Resource> findAll(String[] ids) {
		List<String> idList = Arrays.asList(ids);
		return resourceDao.findAll(idList);
	}

	@Override
	@Transactional
	public Resource saveResource(Resource resource) {
		if (resource.getId() == null) {
			resource.setWeight(resourceDao.count() + 1);
		}
		return resourceDao.save(resource);
	}

	@Override
	@Transactional
	public void removeResource(Resource resource) {
		resourceDao.delete(resource);
	}

	@Override
	@Transactional
	public void removeResources(List<Resource> resources) {
		resourceDao.delete(resources);
	}

	@Override
	@Transactional
	public void removeResources(String[] ids) {
		List<Resource> resList = findAll(ids);
		resourceDao.delete(resList);
	}

	@Override
	public Page<Resource> listResources(String pid, String filters,
			Pageable pageable) {
		return resourceDao.findAll(searchResources(pid, filters), pageable);
	}

	@Override
	public Set<String> getAllPermissions() {
		List<Resource> resList = resourceDao.findByPermissionIsNotNull();
		Set<String> perms = new HashSet<String>(0);
		if (resList != null) {
			for (Resource r : resList) {
				String permission = r.getPermission();
				if (StringUtils.isNotBlank(permission)) {
					perms.add(r.getPermission());
				}

			}
		}
		return perms;
	}

	@Override
	public List<Resource> getTopMenus() {
		return resourceDao
				.findByParentIsNullAndResTypeIsAndEnabledIsOrderByWeightAsc("menu",Boolean.TRUE);
	}

	@Override
	public List<Resource> getSubMenus(String pid) {
		return resourceDao.findByParentIdIsAndResTypeIsAndEnabledIsOrderByWeightAsc(pid, "menu", Boolean.TRUE);
	}

	@Override
	public boolean hasSubMenus(String pid) {
		return resourceDao.countByParentIdIsAndResTypeIs(pid, "menu")
				.intValue() > 0;
	}

}
