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
import com.yunforge.base.service.PersonManager;
import com.yunforge.collect.dto.MessageObject;
import com.yunforge.collect.jackson.mixin.PersonGroupMixin;
import com.yunforge.collect.model.DataCollectPerson;
import com.yunforge.collect.model.PersonGroup;
import com.yunforge.collect.service.DataCollectPersonManager;
import com.yunforge.collect.service.GropInfoManager;
import com.yunforge.collect.service.PersonGroupManager;
import com.yunforge.srpingside.Servlets;

@Controller
@RequestMapping("/collect")
public class PersonGroupController {

	@Autowired
	private PersonGroupManager personGroupManager;
	
	@Autowired
	private DataCollectPersonManager dataCollectPersonManager;
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	/**************** view controller*******************/	
	//管理界面
	@RequestMapping(value="/personGroup/manager",params="ctgId", method=RequestMethod.GET)
	public String PersonGroupView(@RequestParam String ctgId,ModelMap model) {
		model.addAttribute("ctgId", ctgId);
		return "collect/personGroup/manager";
	}
	
	//新增表单
	@RequestMapping(value="/personGroup/view/new", params="ctgId", method=RequestMethod.GET)
	public String newPersonGroupView(@RequestParam String ctgId, ModelMap model) {
		PersonGroup defaultPersonGroup = new PersonGroup();
		List<DataCollectPerson> dataCollectPersonList = dataCollectPersonManager.getAllDataCollectPerson();
		
		model.addAttribute("ctgId", ctgId);
		model.addAttribute("personGroup", defaultPersonGroup);
		model.addAttribute("dataCollectPersonList", dataCollectPersonList);
		return "collect/personGroup/form";
	}
	
	//编辑表单
	@RequestMapping(value="/personGroup/view/edit", params="id", method=RequestMethod.GET)
	public String editPersonGroupView(@RequestParam String id, ModelMap model) {
		PersonGroup personGroup = personGroupManager.getPersonGroup(id);
		List<DataCollectPerson> dataCollectPersonList = dataCollectPersonManager.getAllDataCollectPerson();
		
		model.addAttribute("ctgId", personGroup.getGropInfo().getId());
		model.addAttribute("personGroup", personGroup);
		model.addAttribute("dataCollectPersonList", dataCollectPersonList);
		return "collect/personGroup/form";
	}
	
	//查看视图
	@RequestMapping(value="/personGroup/view/detail", params="id", method=RequestMethod.GET)
	public String viewPersonGroupView(@RequestParam String id, ModelMap model) {
		PersonGroup personGroup = personGroupManager.getPersonGroup(id);
		
		model.addAttribute("ctgName", personGroup.getGropInfo().getName());
		model.addAttribute("personGroup", personGroup);
		return "collect/agrProReport/agrProReportDetail";
	}
	
	/**************** data controller*******************/
	
	//获取
	@RequestMapping(value="/personGroup", method=RequestMethod.GET, produces="application/json; charset=utf-8")
	public @ResponseBody String getAgrProReportByCtg(@RequestParam("ctgId") String ctgId,ServletRequest request, Pageable pageable){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		objectMapper.addMixInAnnotations(PersonGroup.class, PersonGroupMixin.BasicInfo.class);
		Page<PersonGroup> page =  personGroupManager.getPersonGroup(ctgId, searchParams, pageable);
		try {
			return objectMapper.writeValueAsString(page);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
		
	//新增
	@RequestMapping(value="/personGroup", method=RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public String savePersonGroup(@RequestBody PersonGroup personGroup) throws JsonProcessingException {
		objectMapper.addMixInAnnotations(PersonGroup.class, PersonGroupMixin.BasicInfo.class);
		MessageObject messageObject = new MessageObject();
		PersonGroup savePersonGroup;
		try {	
			savePersonGroup = personGroupManager.newPersonGroup(personGroup);
			messageObject.setData(savePersonGroup);
			messageObject.setMessage("保存成功!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messageObject.setMessage("保存失败!"+e.getMessage());
		}
		return objectMapper.writeValueAsString(messageObject);
	}
	
	//删除
	@RequestMapping(value="/personGroup", params="del", method=RequestMethod.POST)
	@ResponseBody
	public MessageObject delPersonGroup(@RequestParam("id") String[] id) {
		MessageObject messageObject = new MessageObject();
		try {
			personGroupManager.delPersonGroups(id);
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
