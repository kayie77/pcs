package com.yunforge.api.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunforge.api.dto.LoginUserDto;
import com.yunforge.api.dto.TaskDto;
import com.yunforge.api.user.LoginApiController;
import com.yunforge.base.model.Person;
import com.yunforge.base.model.User;
import com.yunforge.collect.dao.TaskMainDao;
import com.yunforge.collect.model.DataReoprtedMain;
import com.yunforge.collect.model.TaskMain;
import com.yunforge.collect.service.DataReoprtedMainManager;
import com.yunforge.common.Constants;
import com.yunforge.core.web.controller.BaseController;

@Controller
public class TaskController extends BaseController {
	final static Log log = LogFactory.getLog(LoginApiController.class);

	@Autowired
	private TaskMainDao taskMainDao;
	
	@Autowired
	private DataReoprtedMainManager dataReoprtedMainManager;

	@RequestMapping(value = "/api/task/list")
	public @ResponseBody JSONObject list(HttpServletRequest request,
			HttpServletResponse resp, ModelMap model,Pageable pageable) throws Exception {
		Subject currentUser=SecurityUtils.getSubject();
		User user=(User)currentUser.getSession().getAttribute(Constants.SESSION_USER_KEY);
		Person person=user.getPerson();
		String personId=person.getId();
		
		//根据用户获取有效的任务类别
		List<TaskMain> taskMainList= taskMainDao.findClassTaskMain(personId);
		System.out.println(pageable.getSort());
		
		int pageSize = request.getParameter("size") == null ? 20 : Integer.parseInt(request.getParameter("size")); // 每页行数
		int pageIndex =request.getParameter("page") == null ? 0 : Integer.parseInt(request.getParameter("page"));// 当前页数
		String sord = request.getParameter("sord");
		String sidx =request.getParameter("sidx");
		Sort sort = null;
		if (sidx != null && !sidx.equals("")) {
			if (sord.equals("asc")) {
				sort = new Sort(new Sort.Order(Sort.Direction.ASC, sidx));
			} else {
				sort = new Sort(new Sort.Order(Sort.Direction.DESC, sidx));
			}
		} else {
			sort = new Sort(new Sort.Order(Sort.Direction.ASC, "period"));
		}

		Pageable pageable_ = new PageRequest(pageIndex, pageSize, sort);
		
		//根据用户和分页条件获取任务列表
		Map<String, String[]> params=new HashMap<String, String[]>();
		params.putAll(request.getParameterMap());
		params.put("personId",new String[] {personId});
		Page<DataReoprtedMain> taskPage =dataReoprtedMainManager.getTaskByJPA(params, pageable_);
		
		List<TaskDto> taskList=new ArrayList<TaskDto>();
		for (DataReoprtedMain task : taskPage.getContent()) {
			TaskDto td=new TaskDto();
			td.setTaskId(task.getId());
			td.setTaskName(task.getTaskName());
			td.setExecuteType(task.getExecuteType());
			td.setPeriod(task.getPeriod());
			taskList.add(td);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("taskList", taskList);
		jsonObject.put("pageable", pageable);
		//jsonObject.put("taskList", taskList.getContent());
		return jsonObject;
	}

}
