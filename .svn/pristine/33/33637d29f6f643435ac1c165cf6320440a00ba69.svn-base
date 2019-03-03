package com.yunforge.base.service.impl;

import static com.yunforge.base.dao.SysLogSpecifications.searchLogs;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.base.dao.SysLogDao;
import com.yunforge.base.model.SysLog;
import com.yunforge.base.service.SysLogManager;

@Scope("prototype")
@Service
@Transactional(readOnly = true)
public class SysLogManagerImpl implements SysLogManager {

	@Autowired
	private SysLogDao sysLogDao;

	@Override
	public SysLog findById(String id) {
		SysLog sysLog = this.sysLogDao.findOne(id);
		return sysLog;
	}

	@Override
	public Page<SysLog> findAll(Pageable pageable) {
		Page<SysLog> sysLogs = this.sysLogDao.findAll(pageable);
		return sysLogs;
	}

	@Override
	public List<SysLog> findAll(String[] ids) {
		List<String> idList = Arrays.asList(ids);
		return sysLogDao.findAll(idList);
	}

	@Override
	@Transactional
	public SysLog saveLog(SysLog sysLog) {
		return sysLogDao.save(sysLog);
	}

	@Override
	@Transactional
	public void removeLog(SysLog sysLog) {
		sysLogDao.delete(sysLog);

	}

	@Override
	@Transactional
	public void removeLogs(List<SysLog> sysLogs) {
		sysLogDao.delete(sysLogs);

	}

	@Override
	@Transactional
	public void removeLogs(String[] ids) {
		for (String id : ids) {
			sysLogDao.delete(id);
		}
	}

	@Override
	public Page<SysLog> listLogs(String searchField, String searchOper,
			String searchString, Pageable pageable) {
		return sysLogDao.findAll(
				searchLogs(searchField, searchOper, searchString), pageable);
	}

}
