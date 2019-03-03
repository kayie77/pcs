package com.yunforge.collect.web.controller;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunforge.collect.dto.MessageObject;
import com.yunforge.collect.jackson.mixin.DataCollectPointMixin;
import com.yunforge.collect.jackson.mixin.TaskMainMixin;
import com.yunforge.collect.model.DataCollectIndex;
import com.yunforge.collect.model.DataCollectPoint;
import com.yunforge.collect.model.TaskMain;
import com.yunforge.collect.service.DataCollectIndexManager;
import com.yunforge.common.util.StringUtil;
import com.yunforge.srpingside.Servlets;

@Controller
public class DataCollectIndexController {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private DataCollectIndexManager dataCollectIndexManager;
	
	// 管理界面
	@RequestMapping(value = "/dataCollectIndex/manager")
	public String dataCollectPersonView(HttpServletRequest request) {
		
		return "collect/dataCollectIndex/manager";
	}
	
	//查询列表
	@RequestMapping(value="/dataCollectIndex/queryList",produces="application/json; charset=utf-8")
	public @ResponseBody String selfTaskMain(ServletRequest request, Pageable pageable) throws JsonProcessingException{
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		objectMapper.addMixInAnnotations(TaskMain.class, TaskMainMixin.BasicInfo.class);
		Page<DataCollectIndex> page = dataCollectIndexManager.getDataCollectIndex(searchParams, pageable);
		try {
			return objectMapper.writeValueAsString(page);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//编辑表单
	@RequestMapping(value="/dataCollectIndex/edit")
	public String newDataCollectIndexView(HttpServletRequest request) {

		String id = request.getParameter("id");
		if(StringUtil.notEmpty(id)) {
			
			DataCollectIndex dataCollectIndex = dataCollectIndexManager.findById(id);
			request.setAttribute("id", id);
			request.setAttribute("indexCode", dataCollectIndex.getIndexCode());
			request.setAttribute("indexName", dataCollectIndex.getIndexName());
		}
		return "collect/dataCollectIndex/edit";
	}

	//新增
	@RequestMapping(value="/dataCollectIndex/save", method=RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public String saveDataCollectIndex(@RequestBody DataCollectIndex dataCollectIndex) throws JsonProcessingException {
		MessageObject messageObject = new MessageObject();
		DataCollectIndex saveDataCollectIndex;
		try {	
			
			saveDataCollectIndex = dataCollectIndexManager.save(dataCollectIndex);
			messageObject.setData(saveDataCollectIndex);
			messageObject.setMessage("保存成功!");
		} catch (Exception e) {
			e.printStackTrace();
			messageObject.setMessage("保存失败!"+e.getMessage());
		}
		return objectMapper.writeValueAsString(messageObject);
	}

	//删除
	@RequestMapping(value="/dataCollectIndex/delete")
	@ResponseBody public String deleteDataCollectIndex(HttpServletRequest request) throws JsonProcessingException {
		MessageObject messageObject = new MessageObject();
		try {	
			String id = request.getParameter("id");
			dataCollectIndexManager.deleteById(id);
			
			messageObject.setMessage("删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			messageObject.setMessage("删除失败!"+e.getMessage());
		}
		return objectMapper.writeValueAsString(messageObject);
	}
}
