package com.yunforge.base.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.yunforge.base.model.Year;

public interface YearManager {

	public List<Year> findActiveYearNums();

	public Year findByYearNum(Integer num);

	public Year saveYear(Year year);

	public void removeYear(Year year);

	public Page<Year> listYears(String searchField, String searchOper,
			String searchString, Pageable pageable);

}