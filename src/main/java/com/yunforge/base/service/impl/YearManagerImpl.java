package com.yunforge.base.service.impl;

import static com.yunforge.base.dao.YearSpecifications.searchYears;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yunforge.base.dao.YearDao;
import com.yunforge.base.model.Year;
import com.yunforge.base.service.YearManager;

@Scope("prototype")
@Service
@Transactional(readOnly = true)
public class YearManagerImpl implements YearManager {

	@Autowired
	private YearDao yearDao;

	@Override
	public List<Year> findActiveYearNums() {
		return yearDao.findByEnabled(Boolean.TRUE);
	}

	@Override
	public Year findByYearNum(Integer yearNum) {
		return yearDao.findByYearNum(yearNum);
	}

	@Override
	@Transactional
	public Year saveYear(Year year) {
		return yearDao.save(year);
	}

	@Override
	@Transactional
	public void removeYear(Year year) {
		yearDao.delete(year);

	}

	@Override
	public Page<Year> listYears(String searchField, String searchOper,
			String searchString, Pageable pageable) {
		return yearDao.findAll(
				searchYears(searchField, searchOper, searchString), pageable);
	}

}
