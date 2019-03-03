package com.yunforge.cms.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.cms.model.InfoFile;


public interface InfoFileManager {

	public abstract InfoFile findInfoFileById(String id);

	public abstract Page<InfoFile> findAll(Pageable pageable);
	
	public abstract InfoFile saveInfoFile(InfoFile InfoFile);

	public abstract void removeInfoFile(InfoFile InfoFile);

	public abstract void removeInfoFiles(List<InfoFile> InfoFiles);

	public abstract void removeInfoFiles(String[] ids);

}