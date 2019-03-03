package com.yunforge.cms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yunforge.cms.model.InfoCat;

public interface InfoCatDao extends JpaRepository<InfoCat, String>,
		JpaSpecificationExecutor<InfoCat> {

	public List<InfoCat> findByParentIsNullOrderByWeightAsc();

	public List<InfoCat> findByParentIdIsOrderByWeightAsc(String id);

	public Integer countByParentIdIs(String id);

}
