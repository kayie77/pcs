package com.yunforge.base.service.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunforge.base.dao.CommonDao;
import com.yunforge.base.service.AnalysisManager;
import com.yunforge.support.sql.NativeSearchFilter;
import com.yunforge.support.sql.NativeSql;

@Service
public class AnalysisManagerImpl implements AnalysisManager {
	@Autowired
	private CommonDao commonDao;

	@Override
	public List<Object[]> getCities(Map<String, Object> paramsMap) {
		Map<String, NativeSearchFilter> filters = NativeSearchFilter.parse(paramsMap);
		Collection<NativeSearchFilter> filtersColletion =  filters.values();
		String querySql = "select region_name, region_code from a_dic_region"
				+ " where " + StringUtils.defaultIfBlank(NativeSql.filter(filtersColletion), "true");
		return queryByFilters(querySql, filtersColletion);
	}

	@Override
	public List<Object[]> getCategories(Map<String, Object> paramsMap) {
		Map<String, NativeSearchFilter> filters = NativeSearchFilter.parse(paramsMap);
		Collection<NativeSearchFilter> filtersColletion =  filters.values();
		String querySql = "select category_name, category_code from a_dic_procategory"
				+ " where " + StringUtils.defaultIfBlank(NativeSql.filter(filtersColletion), "true");
		return queryByFilters(querySql, filtersColletion);
	}

	@Override
	public List<Object[]> getProducts(String ctgCode,
			Map<String, Object> paramsMap) {
		Map<String, NativeSearchFilter> filters = NativeSearchFilter.parse(paramsMap);
		filters.put("category", new NativeSearchFilter("category_code", NativeSearchFilter.Operator.EQ, ctgCode));
		Collection<NativeSearchFilter> filtersColletion =  filters.values();
		String querySql = "select agrpro_name, agrpro_code from a_dic_agrpro" 
				+ " where " + NativeSql.filter(filtersColletion);
		return queryByFilters(querySql, filtersColletion);
	}

	@Override
	public List<Object[]> getMarkets(String regionCode, 
			Map<String, Object> paramsMap) {
		Map<String, NativeSearchFilter> filters = NativeSearchFilter.parse(paramsMap);
		filters.put("region", new NativeSearchFilter("region_code", NativeSearchFilter.Operator.EQ, regionCode));
		Collection<NativeSearchFilter> filtersColletion =  filters.values();
		String querySql = "select market, market_code from a_dic_market" 
				+ " where " + NativeSql.filter(filtersColletion);
		return queryByFilters(querySql, filtersColletion);
	}
	
	private List<Object[]> queryByFilters(String query, Collection<NativeSearchFilter> filtersColletion) {
		Iterator<NativeSearchFilter> iterator = filtersColletion.iterator();
		int index = 0;
		Object[] params = new Object[filtersColletion.size()];
		while (iterator.hasNext()) {
			NativeSearchFilter filter = iterator.next();
			params[index++] = filter.value;
		}
		return commonDao.executeNativeQuery(query, params);
	}
}
