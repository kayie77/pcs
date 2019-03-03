package com.yunforge.base.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.yunforge.base.model.Year;

public interface YearDao extends JpaRepository<Year, String>,
		JpaSpecificationExecutor<Year> {

	public Year findById(Year year);

	public List<Year> findByEnabled(boolean enabled);

	public Year findByYearNum(Integer yearNum);

}
