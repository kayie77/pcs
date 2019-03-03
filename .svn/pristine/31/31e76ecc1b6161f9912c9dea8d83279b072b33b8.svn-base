package com.yunforge.base.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.base.model.SysLog;

public interface SysLogManager {

	public SysLog findById(String id);

	public Page<SysLog> findAll(Pageable pageable);

	public List<SysLog> findAll(String[] ids);

	public SysLog saveLog(SysLog sysLog);

	public void removeLog(SysLog sysLog);

	public void removeLogs(List<SysLog> sysLogs);

	public void removeLogs(String[] ids);

	public Page<SysLog> listLogs(String searchField, String searchOper,
			String searchString, Pageable pageable);
}