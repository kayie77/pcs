package com.yunforge.base.service;

import java.util.List;
import java.util.Map;

public interface AnalysisManager {
	List<Object[]> getCities(Map<String, Object> paramsMap);
	List<Object[]> getCategories(Map<String, Object> paramsMap);
	List<Object[]> getProducts(String ctgCode, Map<String, Object> paramsMap);
	List<Object[]> getMarkets(String regionCode, Map<String, Object> paramsMap);
}
