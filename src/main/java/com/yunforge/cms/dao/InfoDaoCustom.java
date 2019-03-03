package com.yunforge.cms.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.api.dto.InfoDto;

public interface InfoDaoCustom {

	public Page<InfoDto> queryByStatusAndTypeAndCatalog(Integer status,
			Integer type, Integer catalog, Pageable pageable);
}
