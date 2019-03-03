package com.yunforge.cms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yunforge.cms.model.InfoFile;

public interface InfoFileDao extends JpaRepository<InfoFile, String>,
		JpaSpecificationExecutor<InfoFile> {
	public List<InfoFile> findByInfoId(String id);
}
