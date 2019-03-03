package com.yunforge.analysis.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yunforge.base.service.AnalysisManager;
import com.yunforge.srpingside.Servlets;

@Controller
@RequestMapping("/analysis")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AgrAnalysisController {
	
	@Autowired AnalysisManager analysisManager;
	/**************** view controller *******************/

	/**
	 * 分析页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String Analysis() {
		return "analysis/jsontest";
	}

	/**
	 * 获取农产品类别 主要是根据数据库查询 格式化成json数据
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/ctgs")
	public @ResponseBody Collection<JSONObject> getCtype(ServletRequest request)
			throws JsonProcessingException {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "");
		List<Object[]> datas = analysisManager.getCategories(searchParams);
		Collection<JSONObject> catogories = CollectionUtils.collect(datas, new Transformer() {
			@Override
			public Object transform(Object object) {
				Object[] objectArray = (Object[])object;
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("name", objectArray[0]);
				jsonObject.put("code", objectArray[1]);
				return jsonObject;
			}
		});
		return catogories;
	}

	/**
	 * 根据农产品类别获取农产品 获取的数据格式化成json数据
	 * 
	 * @param ctype
	 *            农产品类别
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/ctgs/{ctg}/products")
	public @ResponseBody Collection<JSONObject> getCNameByCType(@PathVariable("ctg") String ctg,
			ServletRequest request)
			throws JsonProcessingException {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "");
		List<Object[]> datas = analysisManager.getProducts(ctg, searchParams);
		Collection<JSONObject> products = CollectionUtils.collect(datas, new Transformer() {
			@Override
			public Object transform(Object object) {
				Object[] productObject = (Object[])object;
				JSONObject product = new JSONObject();
				product.put("name", productObject[0]);
				product.put("code", productObject[1]);
				return product;
			}
		});
		return products;
	}
	

	/**
	 * 获取区域 格式化成json数据
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/regions")
	public @ResponseBody Collection<JSONObject> getRegion(ServletRequest request)
			throws JsonProcessingException {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "");
		List<Object[]> datas = analysisManager.getCities(searchParams);
		Collection<JSONObject> regions = CollectionUtils.collect(datas, new Transformer() {
			@Override
			public Object transform(Object object) {
				Object[] objectArray = (Object[])object;
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("name", objectArray[0]);
				jsonObject.put("code", objectArray[1]);
				return jsonObject;
			}
		});
		return regions;
	}

	/**
	 * 根据区域名称获取该区域的市场 格式化成json数据
	 * 
	 * @param region
	 *            某个区域的名称
	 * @return
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/regions/{region}/markets")
	public @ResponseBody Collection<JSONObject> getMarketByRegion(@PathVariable("region") String region
			, ServletRequest request)
			throws JsonProcessingException {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "");
		List<Object[]> datas = analysisManager.getMarkets(region, searchParams);
		Collection<JSONObject> markets = CollectionUtils.collect(datas, new Transformer() {
			@Override
			public Object transform(Object object) {
				Object[] marketObject = (Object[])object;
				JSONObject market = new JSONObject();
				market.put("name", marketObject[0]);
				market.put("code", marketObject[1]);
				return market;
			}
		});
		return markets;
	}
	
	

}
