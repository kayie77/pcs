package com.yunforge.collect.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.springframework.beans.BeanUtils;
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
import com.yunforge.collect.jackson.mixin.TaskDetailMixin;
import com.yunforge.collect.model.AgrProCategory2;
import com.yunforge.collect.model.DataCollectIndex;
import com.yunforge.collect.model.TaskDetail;
import com.yunforge.collect.service.DataCollectIndexManager;
import com.yunforge.collect.service.TaskDetailManager;
import com.yunforge.common.util.StringUtil;
import com.yunforge.srpingside.Servlets;

@Controller
@RequestMapping("/collect")
public class TaskDetailController {
	
	@Autowired 
	private TaskDetailManager taskDetailManager;

	@Autowired 
	private DataCollectIndexManager dataCollectIndexManager;
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	
	/**************** view controller*******************/	
	
	//管理界面
	@RequestMapping(value="/taskDetail/manager",params="tmId", method=RequestMethod.GET)
	public String taskDetailView(@RequestParam String tmId, ModelMap model) {
		model.addAttribute("tmId", tmId);
		return "collect/taskDetail/manager";
	}
	
	//新增表单
	@RequestMapping(value="/taskDetail/view/new", params="tmId", method=RequestMethod.GET)
	public String newTaskDetailView(@RequestParam String tmId, ModelMap model) {
		TaskDetail taskDetail = new TaskDetail();
		List<DataCollectIndex> dataCollectIndexList = dataCollectIndexManager.findAllOrderByIndexCode();

		model.addAttribute("dataCollectIndexList", dataCollectIndexList);
		model.addAttribute("tmId", tmId);
		model.addAttribute("taskDetail", taskDetail);

		return "collect/taskDetail/form";
	}
	
	//编辑表单
	@RequestMapping(value="/taskDetail/view/edit", params="id", method=RequestMethod.GET)
	public String editTaskDetailView(@RequestParam String id, ModelMap model) {
		TaskDetail taskDetail = taskDetailManager.getTaskDetail(id);
		AgrProCategory2 apc = taskDetail.getAgrProCategory2();
		
		//获取采集指标，并判断是不是已经保存过的，保存过得要自动选中
		List<DataCollectIndex> dataCollectIndexList = dataCollectIndexManager.findAllOrderByIndexCode();
		List<DataCollectIndex> selfDataCollectIndexList = dataCollectIndexManager.findByTaskDetailId(taskDetail.getId());
		for(int i = 0;i < dataCollectIndexList.size();i++) {
			for(int j = 0;j < selfDataCollectIndexList.size();j++) {
				if(dataCollectIndexList.get(i).getIndexCode().equals(selfDataCollectIndexList.get(j).getIndexCode())) {
					dataCollectIndexList.get(i).setSelected(true);
					break;
				}
			}
		}

		model.addAttribute("selfDataCollectIndexList", selfDataCollectIndexList);
		model.addAttribute("dataCollectIndexList", dataCollectIndexList);
		model.addAttribute("tmId", taskDetail.getTaskMain().getId());
		model.addAttribute("taskDetail", taskDetail);
		model.addAttribute("apcId", apc.getId());
		model.addAttribute("apcName", apc.getName());

		return "collect/taskDetail/form";
	}

	//查看视图
	@RequestMapping(value="/taskDetail/view/detail", params="id", method=RequestMethod.GET)
	public String viewTaskDetail(@RequestParam String id, ModelMap model) {
		TaskDetail taskDetail = taskDetailManager.getTaskDetail(id);
		model.addAttribute("tmName", taskDetail.getTaskMain().getName());
		model.addAttribute("taskDetail", taskDetail);
		return "collect/taskDetail/detail";
	}
	
	/**************** data controller*******************/
	
	//获取任务细表信息
	@RequestMapping(value="/taskDetail", method=RequestMethod.GET, produces="application/json; charset=utf-8")
	public @ResponseBody String getTaskDetailByTm(@RequestParam("tmId") String tmId,ServletRequest request, Pageable pageable){
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		objectMapper.addMixInAnnotations(TaskDetail.class, TaskDetailMixin.BasicInfo.class);
		
		searchParams.put("taskMainId", tmId);
		Page<TaskDetail> page =  taskDetailManager.getTaskDetail(searchParams, pageable);
		try {
			return objectMapper.writeValueAsString(page);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//新增
	@RequestMapping(value="/taskDetail", method=RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public String saveTaskDetail(@RequestBody TaskDetail taskDetail) throws JsonProcessingException {
		objectMapper.addMixInAnnotations(TaskDetail.class, TaskDetailMixin.BasicInfo.class);
		MessageObject messageObject = new MessageObject();
		List<TaskDetail> saveTaskDetail = new ArrayList<TaskDetail>();
		
		//修改
		if(StringUtil.notEmpty(taskDetail.getId())) {
			
			//先判断同一个任务里，是否品种重复
			boolean has = taskDetailManager.hasSameAgrprocategory(taskDetail.getTaskMain().getId(), taskDetail.getAgrProCategory2().getId(),taskDetail.getId());
			if(has) {
				messageObject.setMessage("同一个任务不能存在相同的农产品！");
				return objectMapper.writeValueAsString(messageObject);
			}
			
			//删除以前的数据，在保存
			taskDetailManager.deleteSameTaskDetail(new String[]{taskDetail.getId()});
		} else {
			
			//如果是新增，需要判断是否品种重复
			boolean has = taskDetailManager.hasSameAgrprocategory(taskDetail.getTaskMain().getId(), taskDetail.getAgrProCategory2().getId());
			if(has) {
				messageObject.setMessage("同一个任务不能存在相同的农产品！");
				return objectMapper.writeValueAsString(messageObject);
			}
		}
		try {	
			
			//根据选择的采集指标个数，依次保存
			String[] dataIds = taskDetail.getDataID().split(",");
			for(int i = 0;i < dataIds.length;i++) {
				TaskDetail saveObject = new TaskDetail();
				BeanUtils.copyProperties(taskDetail, saveObject);
				saveObject.setDataID(dataIds[i]);
				saveObject.setId(null);
				TaskDetail td = taskDetailManager.newTaskDetail(saveObject);
				saveTaskDetail.add(td);
			}
			messageObject.setData(saveTaskDetail);
			messageObject.setMessage("保存成功!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messageObject.setMessage("保存失败!"+e.getMessage());
		}
		return objectMapper.writeValueAsString(messageObject);
	}
	
	//删除
	@RequestMapping(value="/taskDetail", params="del", method=RequestMethod.POST)
	@ResponseBody
	public MessageObject delTaskDetail(@RequestParam("id") String[] id) {
		MessageObject messageObject = new MessageObject();
		try {
			taskDetailManager.deleteSameTaskDetail(id);

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
