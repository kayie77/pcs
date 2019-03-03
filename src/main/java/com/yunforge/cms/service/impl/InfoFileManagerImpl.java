package com.yunforge.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.cms.dao.InfoFileDao;
import com.yunforge.cms.model.InfoFile;
import com.yunforge.cms.service.InfoFileManager;

@Scope("prototype")
@Service
@Transactional(readOnly = true)
public class InfoFileManagerImpl implements InfoFileManager {

	@Autowired
	private InfoFileDao infoFileDao;

	public InfoFile findInfoFileById(String id) {
		InfoFile infoFile = this.infoFileDao.findOne(id);
		return infoFile;
	}

	public Page<InfoFile> findAll(Pageable pageable) {
		Page<InfoFile> infoFiles = this.infoFileDao.findAll(pageable);
		return infoFiles;
	}

	public List<InfoFile> findByInfoId(String id) {
		return infoFileDao.findByInfoId(id);
	}

	@Transactional
	public InfoFile saveInfoFile(InfoFile infoFile) {
		return infoFileDao.save(infoFile);
	}

	@Transactional
	public void removeInfoFile(InfoFile infoFile) {
		infoFileDao.delete(infoFile);
	}

	@Transactional
	public void removeInfoFiles(List<InfoFile> infoFiles) {
		infoFileDao.delete(infoFiles);
	}

	@Transactional
	public void removeInfoFiles(String[] ids) {
		for (String id : ids) {
			infoFileDao.delete(id);
		}

	}

}