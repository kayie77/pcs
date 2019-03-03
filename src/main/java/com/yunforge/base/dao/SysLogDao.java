package com.yunforge.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yunforge.base.model.SysLog;

public interface SysLogDao extends JpaRepository<SysLog, String>,
		JpaSpecificationExecutor<SysLog> {

}
