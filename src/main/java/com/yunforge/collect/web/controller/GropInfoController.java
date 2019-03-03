package com.yunforge.collect.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunforge.collect.dto.MessageObject;
import com.yunforge.collect.dto.TreeNode;
import com.yunforge.collect.jackson.mixin.AgrProCategoryMixin;
import com.yunforge.collect.jackson.mixin.PersonGroupMixin;
import com.yunforge.collect.jackson.mixin.GropInfoMixin;
import com.yunforge.collect.model.GropInfo;
import com.yunforge.collect.service.GropInfoManager;
import com.yunforge.srpingside.Servlets;

@Controller
@RequestMapping("/collect")
public class GropInfoController {

	@Autowired
	private GropInfoManager gropInfoManager;

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	/**************** view controller*******************/	
	//管理界面
	@RequestMapping(value="/gropInfo/manager", method=RequestMethod.GET)
	public String GropInfoView() {
		return "collect/gropInfo/manager";
	}
	
	//新增表单
	@RequestMapping(value="/gropInfo/view/new", method=RequestMethod.GET)
	public String newAgrProReportView(ModelMap model) {
		GropInfo gropInfo = new GropInfo();
		model.addAttribute("gropInfo", gropInfo);
		return "collect/gropInfo/form";
	}
	
	//编辑表单
	@RequestMapping(value="/gropInfo/view/edit", params="id", method=RequestMethod.GET)
	public String editGropInfoView(@RequestParam String id, ModelMap model) {
		GropInfo gropInfo = gropInfoManager.getGropInfo(id);
		model.addAttribute("gropInfo", gropInfo);
		return "collect/gropInfo/form";
	}
	
	//查看视图
	@RequestMapping(value="/gropInfo/view/detail", params="id", method=RequestMethod.GET)
	public String viewGropInfoView(@RequestParam String id, ModelMap model) {
		GropInfo gropInfo = gropInfoManager.getGropInfo(id);
		model.addAttribute("gropInfo", gropInfo);
		return "collect/gropInfo/detail";
	}
	
	/**************** data controller*******************/
	
	//获取分组信息
	@RequestMapping(value="/gropInfo", method=RequestMethod.GET, produces="application/json; charset=utf-8")
	public @ResponseBody String getGropInfo(ServletRequest request, Pageable pageable){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		objectMapper.addMixInAnnotations(GropInfo.class, GropInfoMixin.BasicInfo.class);
		Page<GropInfo> page = gropInfoManager.getGropInfo(searchParams, pageable);
		try {
			return objectMapper.writeValueAsString(page);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
		
	//新增
	@RequestMapping(value="/gropInfo", method=RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public String saveGropInfo(@RequestBody GropInfo gropInfo) throws JsonProcessingException {
		objectMapper.addMixInAnnotations(GropInfo.class, GropInfoMixin.BasicInfo.class);
		MessageObject messageObject = new MessageObject();
		GropInfo saveGropInfo;
		try {	
			saveGropInfo = gropInfoManager.saveGropInfo(gropInfo);
			messageObject.setData(saveGropInfo);
			messageObject.setMessage("保存成功!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messageObject.setMessage("保存失败!"+e.getMessage());
		}
		return objectMapper.writeValueAsString(messageObject);
	}
	
	//删除
	@RequestMapping(value="/gropInfo", params="del", method=RequestMethod.POST)
	@ResponseBody
	public MessageObject delGropInfo(@RequestParam("id") String[] id) {
		MessageObject messageObject = new MessageObject();
		try {
			gropInfoManager.delGropInfos(id);
			messageObject.setMessage("删除成功!");
			messageObject.setStatus(true);
		} catch (Exception e) {
			// TODO: handle exception
			messageObject.setMessage("删除失败!" + e.getMessage());
			messageObject.setStatus(false);
		}
		return messageObject;
	}
		
}
