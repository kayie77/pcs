package com.yunforge.cms.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.api.dto.InfoDto;
import com.yunforge.cms.model.Info;
import com.yunforge.common.bean.JqGridSearchBean;

public interface InfoManager {

	public abstract Info findInfoById(String id);

	public abstract Page<Info> find(JqGridSearchBean searchBean,
			Pageable pageable);

	public abstract Page<InfoDto> findByStatusAndTypeAndCatalog(Integer status,Integer type, Integer catalog, Pageable pageable);

	public abstract Page<Info> findPrivate(String filters, Pageable pageable);

	public abstract Page<Info> findByStatus(Object[] status, String filters,
			Pageable pageable);

	public abstract Page<Info> findByCat(String infoId, String filters,
			Pageable pageable);

	public abstract List<Info> find(String[] ids);

	public abstract Info saveInfo(Info info);

	public abstract void removeInfo(Info info);

	public abstract void removeInfos(List<Info> infos);

	public abstract void removeInfos(String[] ids);

}