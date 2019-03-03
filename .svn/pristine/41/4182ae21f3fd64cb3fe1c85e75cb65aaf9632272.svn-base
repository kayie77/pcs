package com.yunforge.api.cms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunforge.api.dto.ApiDTO;
import com.yunforge.base.model.User;
import com.yunforge.collect.jackson.mixin.DataReoprtedMainMixin;
import com.yunforge.collect.jackson.mixin.TaskMainMixin;
import com.yunforge.collect.model.DataReoprtedMain;
import com.yunforge.collect.model.TaskMain;
import com.yunforge.collect.service.DataReoprtedMainManager;
import com.yunforge.collect.service.TaskMainManager;
import com.yunforge.common.Constants;
import com.yunforge.common.util.StringUtil;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.web.controller.BaseController;
import com.yunforge.srpingside.Servlets;

@Controller
public class TaskApiController extends BaseController {
	final static Log log = LogFactory.getLog(TaskApiController.class);

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private DataReoprtedMainManager dataReoprtedMainManager;

	@Autowired 
	private TaskMainManager taskMainManager;
	
	//采集人员：点击“我的任务”
	@MethodRemark(remark = "访问 我的任务")
	@RequestMapping(value = "/apinew/cms/dataReoprtedMain/myTask/list", produces="application/json; charset=utf-8")
	public  @ResponseBody String myTaskList(HttpServletRequest request, ModelMap model,Pageable pageable) {
		Map<String, String[]> params=new HashMap<String, String[]>();
		objectMapper.addMixInAnnotations(DataReoprtedMain.class,DataReoprtedMainMixin.BasicInfo.class);
		params.putAll(request.getParameterMap());
		Page<DataReoprtedMain> dataReoprtedMainPage =dataReoprtedMainManager.getMyCurrentTask(params, pageable);
		//json转换
		ApiDTO apiDTO = new ApiDTO();
		apiDTO.setCode("1");
		apiDTO.setData(dataReoprtedMainPage);
		apiDTO.setMsg("操作成功！");
		
		try {
			return objectMapper.writeValueAsString(apiDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//个人历史项目查询
	@RequestMapping(value="/apinew/cms/querySelfHistory", produces="application/json; charset=utf-8")
	public @ResponseBody String selfHistoryManager(HttpServletRequest request, Pageable pageable) throws JsonProcessingException{
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		objectMapper.addMixInAnnotations(TaskMain.class, TaskMainMixin.BasicInfo.class);

		String personId = request.getParameter("personId");
		String name = request.getParameter("search_LIKE_name");
		String code = request.getParameter("search_LIKE_code");

		if(StringUtil.isEmpty(personId)) {
			Subject currentUser = SecurityUtils.getSubject();
			User user = (User) currentUser.getSession().getAttribute(Constants.SESSION_USER_KEY);
			personId = user.getPerson().getId();
		}
		
		searchParams.put("state", null);
		searchParams.put("name", StringUtil.getString(request, name));
		searchParams.put("code", StringUtil.getString(request, code));
		searchParams.put("personId", personId);
		Page<TaskMain> page =  taskMainManager.getSelfTaskMain(searchParams, pageable);
		
		//json转换
		ApiDTO apiDTO = new ApiDTO();
		apiDTO.setCode("1");
		apiDTO.setData(page);
		apiDTO.setMsg("操作成功！");
		
		try {
			return objectMapper.writeValueAsString(apiDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//个人历史项目明细查询
	@RequestMapping(value="/apinew/cms/querySelfHistoryDetail", produces="application/json; charset=utf-8")
	public @ResponseBody String querySelfHistoryDetail(HttpServletRequest request, Pageable pageable) throws JsonProcessingException{

		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		objectMapper.addMixInAnnotations(TaskMain.class, TaskMainMixin.BasicInfo.class);
		
		searchParams.put("personId", request.getParameter("personId"));
		searchParams.put("reriod", request.getParameter("reriod"));
		searchParams.put("taskMainId", request.getParameter("taskMainId"));
		Page<DataReoprtedMain> page =  taskMainManager.queryHistoryDetail(searchParams, pageable);
		
		//json转换
		ApiDTO apiDTO = new ApiDTO();
		apiDTO.setCode("1");
		apiDTO.setData(page);
		apiDTO.setMsg("操作成功！");
		
		try {
			return objectMapper.writeValueAsString(apiDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
