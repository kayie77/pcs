package com.yunforge.collect.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
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
import com.yunforge.base.model.User;
import com.yunforge.collect.dto.MessageObject;
import com.yunforge.collect.jackson.mixin.TaskMainMixin;
import com.yunforge.collect.model.DataCollectCategory;
import com.yunforge.collect.model.DataCollectPerson;
import com.yunforge.collect.model.DataCollectPoint;
import com.yunforge.collect.model.DataReoprtedMain;
import com.yunforge.collect.model.GropInfo;
import com.yunforge.collect.model.TaskGive;
import com.yunforge.collect.model.TaskMain;
import com.yunforge.collect.service.DataCollectCategoryManager;
import com.yunforge.collect.service.DataCollectPersonManager;
import com.yunforge.collect.service.DataCollectPointManager;
import com.yunforge.collect.service.GropInfoManager;
import com.yunforge.collect.service.PersonGroupManager;
import com.yunforge.collect.service.TaskGiveManager;
import com.yunforge.collect.service.TaskMainManager;
import com.yunforge.common.Constants;
import com.yunforge.common.util.StringUtil;
import com.yunforge.srpingside.Servlets;

@Controller
@RequestMapping("/collect")
public class TaskMainController {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired 
	private TaskMainManager taskMainManager;
	
	@Autowired 
	private DataCollectPersonManager dcpManager;
	
	@Autowired 
	private DataCollectPointManager dataCollectPointManager;
	
	@Autowired 
	private DataCollectCategoryManager dccManager;

	@Autowired 
	private GropInfoManager gropInfoManager;
	
	@Autowired
	private PersonGroupManager personGroupManager;

	@Autowired
	private TaskGiveManager taskGiveManager;
	
	/**************** view controller*******************/	
	
	//管理界面
	@RequestMapping(value="/taskMain/manager", method=RequestMethod.GET)
	public String TaskMainView() {
		return "collect/taskMain/manager";
	}
	
	//新增表单
	@RequestMapping(value="/taskMain/view/new", method=RequestMethod.GET)
	public String newDataCollectPointView(ModelMap model) {
		TaskMain taskMain = new TaskMain();
		List<DataCollectPerson> personList = dcpManager.getAllDataCollectPerson();
		List<DataCollectPoint> pointList = dataCollectPointManager.getAllDataCollectPoint();
		List<DataCollectCategory> categoryList = dccManager.getDataCollectCategory();
		
		model.addAttribute("personList", personList);
		model.addAttribute("pointList", pointList);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("taskMain", taskMain);
		
		return "collect/taskMain/form";
	}
	
	//编辑表单
	@RequestMapping(value="/taskMain/view/edit", params="id", method=RequestMethod.GET)
	public String editTaskMainView(@RequestParam String id, ModelMap model) {
		TaskMain taskMain = taskMainManager.getTaskMain(id);
		List<DataCollectPerson> personList = dcpManager.getAllDataCollectPerson();
		List<DataCollectPoint> pointList = dataCollectPointManager.getAllDataCollectPoint();
		List<DataCollectCategory> categoryList = dccManager.getDataCollectCategory();
		
		model.addAttribute("personList", personList);
		model.addAttribute("pointList", pointList);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("taskMain", taskMain);
		
		return "collect/taskMain/form";
	}
	
	//查看视图
	@RequestMapping(value="/taskMain/view/detail", params="id", method=RequestMethod.GET)
	public String viewTaskMainView(@RequestParam String id, ModelMap model) {
		TaskMain taskMain = taskMainManager.getTaskMain(id);
		model.addAttribute("taskMain", taskMain);
		return "collect/taskMain/detail";
	}
	

	/**************** data controller *******************/
	
	//管理员获取任务
	@RequestMapping(value="/taskMain", method=RequestMethod.GET, produces="application/json; charset=utf-8")
	public @ResponseBody String getTaskMain(ServletRequest request, Pageable pageable) throws JsonProcessingException{
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		objectMapper.addMixInAnnotations(TaskMain.class, TaskMainMixin.BasicInfo.class);
		Page<TaskMain> page =  taskMainManager.getTaskMain(searchParams, pageable);
		try {
			return objectMapper.writeValueAsString(page);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//新增
	@RequestMapping(value="/taskMain", method=RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public String saveTaskMain(@RequestBody TaskMain taskMain) throws JsonProcessingException {
		objectMapper.addMixInAnnotations(TaskMain.class, TaskMainMixin.BasicInfo.class);
		MessageObject messageObject = new MessageObject();
		TaskMain saveTaskMain;
		try {	
			saveTaskMain = taskMainManager.saveTaskMain(taskMain);
			messageObject.setData(saveTaskMain);
			messageObject.setMessage("保存成功!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messageObject.setMessage("保存失败!"+e.getMessage());
		}
		return objectMapper.writeValueAsString(messageObject);
	}
	
	//删除
	@RequestMapping(value="/taskMain", params="del", method=RequestMethod.POST)
	@ResponseBody
	public MessageObject delTaskMain(@RequestParam("id") String[] id) {
		MessageObject messageObject = new MessageObject();
		try {
			taskMainManager.delTaskMains(id);
			messageObject.setMessage("删除成功!");
			messageObject.setStatus(true);
		} catch (Exception e) {
			// TODO: handle exception
			messageObject.setMessage("删除失败!" + e.getMessage());
			messageObject.setStatus(false);
		}
		return messageObject;
	}

	//查看人员分配
	@RequestMapping(value="/taskMain/view/person")
	public String viewPerson(HttpServletRequest request) {

		String taskMainId = request.getParameter("id");
		
		List<GropInfo> groupInfoList = gropInfoManager.getAllGropInfo(GropInfo.STATE.ENABLE);
//		List<TaskGive> taskGiveList = taskGiveManager.findByTaskMain(taskMainId);
		List<DataCollectPerson> notGroupPersonList = dcpManager.getNotGroupPersonList();
		List<GropInfo> groupInfoAndPersonList = gropInfoManager.findDataCollectPerson(groupInfoList, taskMainId);

		request.setAttribute("taskMainId", taskMainId);
//		request.setAttribute("taskGiveList", taskGiveList);
		request.setAttribute("groupInfoList", groupInfoList);
		request.setAttribute("notGroupPersonList", notGroupPersonList);
		request.setAttribute("groupInfoAndPersonList", groupInfoAndPersonList);
		return "collect/person/form";
	}
	
	//保存人员分配
	@RequestMapping(value="/taskMain/saveTaskMainPerson")
	public @ResponseBody MessageObject saveTaskMainPerson(HttpServletRequest request) {

		MessageObject messageObject = new MessageObject();
		
		String personIds = request.getParameter("personIds");
		String taskMainId = request.getParameter("taskMainId");

		taskGiveManager.deleteByTaskMainId(taskMainId);
		TaskMain taskMain = taskMainManager.getTaskMain(taskMainId);
		
		if(StringUtil.notEmpty(personIds))
		{
			String[] personIdArray = personIds.split(",");
			for(int i = 0;i < personIdArray.length;i++)
			{
				String personId = personIdArray[i];
				if(StringUtil.notEmpty(personId))
				{
					DataCollectPerson dataCollectPerson = dcpManager.getDataCollectPerson(personId);
					
					TaskGive taskGive = new TaskGive();
					taskGive.setDataCollectPerson(dataCollectPerson);
					taskGive.setTaskMain(taskMain);
					taskGiveManager.save(taskGive);
				}
			}
		}
		
		messageObject.setMessage("操作成功！");
		messageObject.setStatus(true);
		
		return messageObject;
	}
	

	
	//首页任务列表
	@RequestMapping(value="/selfTaskMain", method=RequestMethod.GET, produces="application/json; charset=utf-8")
	public @ResponseBody String selfTaskMain(ServletRequest request, Pageable pageable) throws JsonProcessingException{
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		objectMapper.addMixInAnnotations(TaskMain.class, TaskMainMixin.BasicInfo.class);
//		Page<TaskMain> page =  taskMainManager.getTaskMain(searchParams, pageable);
		
		Subject currentUser = SecurityUtils.getSubject();
		User user = (User) currentUser.getSession().getAttribute(Constants.SESSION_USER_KEY);
		String personId = user.getPerson().getId();

		searchParams.put("state", TaskMain.STATE.INUSE);
		searchParams.put("personId", personId);
		Page<TaskMain> page = taskMainManager.getSelfTaskMain(searchParams, pageable);
		try {
			return objectMapper.writeValueAsString(page);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	//管理界面
		@RequestMapping(value="/taskMain/instanceManager", method=RequestMethod.GET)
		public String instanceManager(ModelMap model,HttpServletRequest request) {
			String taskMainId = request.getParameter("taskMainId");
			String beginDate = request.getParameter("beginDate");
			String endDate = request.getParameter("endDate");
			model.addAttribute("taskMainId", taskMainId);
			model.addAttribute("beginDate", beginDate);
			model.addAttribute("endDate", endDate);
			return "collect/taskMain/instanceManager";
		}

	// 实例化任务
	@RequestMapping(value = "/taskMain/instance")
	public @ResponseBody JSONObject instance(ModelMap model,
			HttpServletRequest request) throws Exception {
		//获取任务模板
		String taskMainId = request.getParameter("taskMainId");
		TaskMain taskMain = taskMainManager.getTaskMain(taskMainId);
		//处理时间
		String sDate = request.getParameter("beginDate");
		String eDate = request.getParameter("endDate");
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String beginDate = df.format(new Date());
		String endDate = "";
		if (!StringUtil.isEmpty(sDate)) {
			beginDate = df.format(DateFormat.getDateInstance().parse(sDate));
		}
		if (!StringUtil.isEmpty(eDate)) {
			endDate = df.format(DateFormat.getDateInstance().parse(eDate));
		}else{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.MONTH, 2);//默认2个月
			endDate = df.format(calendar.getTime());
		}
		//根据任务模板+开始生成时间+结束生成时间 -> 实例化任务
		HashMap<String,Object> instanceResult=taskMainManager.instanceTask(taskMain,beginDate,endDate);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", "实例化完毕!"); 
		jsonObject.put("instanceResult", instanceResult); 
		return jsonObject;
	}

	//历史查询界面
	@RequestMapping(value="/taskMain/historyManager")
	public String historyManager() {
		return "collect/taskMain/historyManager";
	}

	//历史查询
	@RequestMapping(value="/queryHistory", method=RequestMethod.GET, produces="application/json; charset=utf-8")
	public @ResponseBody String queryHistory(ServletRequest request, Pageable pageable) throws JsonProcessingException{
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		objectMapper.addMixInAnnotations(TaskMain.class, TaskMainMixin.BasicInfo.class);
		Page<TaskMain> page =  taskMainManager.getTaskMain(searchParams, pageable);
		try {
			return objectMapper.writeValueAsString(page);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//管理员历史项目查询
	@RequestMapping(value="/taskMain/historyDetailManager")
	public String historyDetailManager(HttpServletRequest request) {
		String taskMainId = request.getParameter("taskMainId");
		String isCheck = request.getParameter("isCheck");
		List<String> reriodList = taskMainManager.findDatareoprtedmainByTaskMainId(taskMainId,null);

		request.setAttribute("isCheck", isCheck);
		request.setAttribute("taskMainId", taskMainId);
		request.setAttribute("reriodList", reriodList);
		return "collect/taskMain/historyDetailManager";
	}
	
	//管理员历史查询
	@RequestMapping(value="/queryHistoryDetail", method=RequestMethod.GET, produces="application/json; charset=utf-8")
	public @ResponseBody String queryHistoryDetail(ServletRequest request, Pageable pageable) throws JsonProcessingException{
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		objectMapper.addMixInAnnotations(TaskMain.class, TaskMainMixin.BasicInfo.class);
		searchParams.put("reriod", request.getParameter("reriod"));
		searchParams.put("taskMainId", request.getParameter("taskMainId"));
		searchParams.put("state", request.getParameter("state"));
		Page<DataReoprtedMain> page =  taskMainManager.queryHistoryDetail(searchParams, pageable);
		try {
			return objectMapper.writeValueAsString(page);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//个人历史项目查询界面
	@RequestMapping(value="/taskMain/selfHistoryManager")
	public String selfHistoryManager() {
		return "collect/taskMain/selfHistoryManager";
	}

	//个人历史项目查询
	@RequestMapping(value="/querySelfHistory", method=RequestMethod.GET, produces="application/json; charset=utf-8")
	public @ResponseBody String selfHistoryManager(HttpServletRequest request, Pageable pageable) throws JsonProcessingException{
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		objectMapper.addMixInAnnotations(TaskMain.class, TaskMainMixin.BasicInfo.class);
		
		Subject currentUser = SecurityUtils.getSubject();
		User user = (User) currentUser.getSession().getAttribute(Constants.SESSION_USER_KEY);
		String personId = user.getPerson().getId();
		String name = request.getParameter("search_LIKE_name");
		String code = request.getParameter("search_LIKE_code");

		searchParams.put("state", null);
		searchParams.put("name", StringUtil.getString(request, name));
		searchParams.put("code", StringUtil.getString(request, code));
		searchParams.put("personId", personId);
		Page<TaskMain> page =  taskMainManager.getSelfTaskMain(searchParams, pageable);
		try {
			return objectMapper.writeValueAsString(page);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	//个人历史项目明细查询
	@RequestMapping(value="/taskMain/selfHistoryDetailManager")
	public String selfHistoryDetailManager(HttpServletRequest request) {
		
		Subject currentUser = SecurityUtils.getSubject();
		User user = (User) currentUser.getSession().getAttribute(Constants.SESSION_USER_KEY);
		String personId = user.getPerson().getId();

		String taskMainId = request.getParameter("taskMainId");
		List<String> reriodList = taskMainManager.findDatareoprtedmainByTaskMainId(taskMainId,personId);

		request.setAttribute("taskMainId", taskMainId);
		request.setAttribute("reriodList", reriodList);
		return "collect/taskMain/selfHistoryDetailManager";
	}
	
	//个人历史项目明细查询
	@RequestMapping(value="/querySelfHistoryDetail", method=RequestMethod.GET, produces="application/json; charset=utf-8")
	public @ResponseBody String querySelfHistoryDetail(ServletRequest request, Pageable pageable) throws JsonProcessingException{
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		objectMapper.addMixInAnnotations(TaskMain.class, TaskMainMixin.BasicInfo.class);
		
		Subject currentUser = SecurityUtils.getSubject();
		User user = (User) currentUser.getSession().getAttribute(Constants.SESSION_USER_KEY);
		String personId = user.getPerson().getId();

		searchParams.put("personId", personId);
		searchParams.put("reriod", request.getParameter("reriod"));
		searchParams.put("taskMainId", request.getParameter("taskMainId"));
		Page<DataReoprtedMain> page =  taskMainManager.queryHistoryDetail(searchParams, pageable);
		try {
			return objectMapper.writeValueAsString(page);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
