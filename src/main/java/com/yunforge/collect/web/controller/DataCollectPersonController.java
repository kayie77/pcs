package com.yunforge.collect.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunforge.base.model.Org;
import com.yunforge.base.model.Person;
import com.yunforge.base.model.User;
import com.yunforge.base.service.OrgManager;
import com.yunforge.base.service.PersonManager;
import com.yunforge.base.service.UserManager;
import com.yunforge.collect.dto.MessageObject;
import com.yunforge.collect.jackson.mixin.DataCollectPersonMixin;
import com.yunforge.collect.model.DataCollectPerson;
import com.yunforge.collect.model.DataCollectPoint;
import com.yunforge.collect.service.DataCollectPersonManager;
import com.yunforge.collect.service.DataCollectPointManager;
import com.yunforge.common.Constants;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.util.StringUtil;
import com.yunforge.common.util.WebUtils;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.srpingside.Servlets;

@Controller
@RequestMapping("/collect")
public class DataCollectPersonController {

	@Autowired
	private DataCollectPersonManager dcpManager;
	
	@Autowired
	private UserManager userManager;

	@Autowired
	private PersonManager personManager;
	
	@Autowired
	private OrgManager orgManager;
	
	@Autowired 
	private DataCollectPointManager pointManager;

	private static final ObjectMapper objectMapper = new ObjectMapper();

	/**************** view controller *******************/

	// 管理界面
	@RequestMapping(value = "/dataCollectPerson/manager", params = "dcpId", method = RequestMethod.GET)
	public String dataCollectPersonView(@RequestParam String dcpId,
			ModelMap model) {
		model.addAttribute("dcpId", dcpId);
		return "collect/dataCollectPerson/dataCollectPersonManager";
	}

	// 新增表单
	@RequestMapping(value = "/dataCollectPerson/view/new", params = "dcpId", method = RequestMethod.GET)
	public String newDataCollectPersonView(@RequestParam String dcpId,
			ModelMap model) {
		DataCollectPerson dataCollectPerson = new DataCollectPerson();
		model.addAttribute("dcpId", dcpId);
		model.addAttribute("dataCollectPerson", dataCollectPerson);
		return "collect/dataCollectPerson/dataCollectPersonForm";
	}

	// 编辑表单
	@RequestMapping(value = "/dataCollectPerson/view/edit", params = "id", method = RequestMethod.GET)
	public String editDataCollectPersonView(@RequestParam String id,
			ModelMap model) {
		DataCollectPerson dataCollectPerson = dcpManager
				.getDataCollectPerson(id);
		model.addAttribute("dcpId", dataCollectPerson.getDataCollectPoint()
				.getId());
		model.addAttribute("dataCollectPerson", dataCollectPerson);
		return "collect/dataCollectPerson/dataCollectPersonForm";
	}

	// 查看视图
	@RequestMapping(value = "/dataCollectPerson/view/detail", params = "id", method = RequestMethod.GET)
	public String viewDataCollectPersonView(@RequestParam String id,
			ModelMap model) {
		DataCollectPerson dataCollectPerson = dcpManager
				.getDataCollectPerson(id);
		model.addAttribute("dcpName", dataCollectPerson.getDataCollectPoint()
				.getName());
		model.addAttribute("dataCollectPerson", dataCollectPerson);
		return "collect/dataCollectPerson/dataCollectPersonDetail";
	}

	/**************** data controller *******************/

	// 获取采集人员信息
	@RequestMapping(value = "/dataCollectPerson", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody String getDataCollectPersonByCtg(@RequestParam("dcpId") String dcpId, ServletRequest request,Pageable pageable) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		objectMapper.addMixInAnnotations(DataCollectPerson.class,DataCollectPersonMixin.BasicInfo.class);
		Page<DataCollectPerson> page = dcpManager.getDataCollectPerson(dcpId,searchParams, pageable);
		try {
			return objectMapper.writeValueAsString(page);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 新增
	@RequestMapping(value = "/dataCollectPerson", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String saveDataCollectPerson(@RequestBody DataCollectPerson dataCollectPerson)throws JsonProcessingException {
		objectMapper.addMixInAnnotations(DataCollectPerson.class,
				DataCollectPersonMixin.BasicInfo.class);
		MessageObject messageObject = new MessageObject();
		DataCollectPerson saveDataCollectPerson;
		try {
			saveDataCollectPerson = dcpManager
					.newDataCollectPerson(dataCollectPerson);
			messageObject.setData(saveDataCollectPerson);
			messageObject.setMessage("保存成功!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			messageObject.setMessage("保存失败!" + e.getMessage());
		}
		return objectMapper.writeValueAsString(messageObject);
	}

	// 删除
	@RequestMapping(value = "/dataCollectPerson", params = "del", method = RequestMethod.POST)
	@ResponseBody
	public MessageObject delDataCollectPoint(@RequestParam("id") String[] id) {
		MessageObject messageObject = new MessageObject();
		try {
			dcpManager.delDataCollectPersons(id);
			personManager.setUnIsCollect(id);
			//personManager.
			messageObject.setMessage("删除成功!");
			messageObject.setStatus(true);
		} catch (Exception e) {
			// TODO: handle exception
			messageObject.setMessage("删除失败!" + e.getMessage());
			messageObject.setStatus(false);
		}
		return messageObject;
	}

	@MethodRemark(remark = "选择人员")
	@RequestMapping(value = "/person/select", method = RequestMethod.GET)
	public String selectPerson(HttpServletRequest request, ModelMap model) {
		String pageSize = WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE,pageSize == null ? 15 : Integer.valueOf(pageSize));
		model.addAttribute("dcpId",request.getParameter("dcpId"));
		return "collect/dataCollectPerson/selectPerson";

	}

	@MethodRemark(remark = "访问人员列表")
	@RequestMapping(value = "/person/list")
	public @ResponseBody GridBean<Person> listPersons(HttpServletRequest request,Integer page,
			Integer rows, String sidx, String sord, boolean search,
			String searchField, String searchOper, String searchString,
			String filters, String persNameParam) {
		int pageSize = rows == null ? 20 : rows.intValue(); // 每页行数
		int pageIndex = (page == null || page == 0) ? 0 : page.intValue() - 1;// 当前页数
		Sort sort = null;
		if (sidx != null && !sidx.equals("")) {
			if (sord.equals("asc")) {
				sort = new Sort(new Sort.Order(Sort.Direction.ASC, sidx));
			} else {
				sort = new Sort(new Sort.Order(Sort.Direction.DESC, sidx));
			}
		} else {
			sort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"));
		}

		Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
		//Page<Person> persons = personManager.listPersons(filters, pageable,persNameParam);
		Org o = orgManager.findByOrgName("价格采集");
		//Page<Person> persons = personManager.listOrgPersons(o.getId(),filters, pageable);
		Page<Person> persons = personManager.listPersonByOrgAndIsCollect(o.getId(),0, pageable,"%" + StringUtil.getString(request, persNameParam) + "%");

		List<Person> personList = new ArrayList<Person>();
		personList.addAll(persons.getContent());
		Integer records = (int) persons.getTotalElements();

		Integer totalPages = persons.getTotalPages();
		Integer currPage = Math.min(totalPages, page);

		GridBean<Person> grid = new GridBean<Person>();

		grid.setRecords(records);
		grid.setTotal(totalPages);
		grid.setPage(currPage);
		grid.setRows(personList);
		return grid;
	}

	// 增加采集人到采集点
	@RequestMapping(value = "/addPerson2Point", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String addPerson2Point(HttpServletRequest request)
			throws JsonProcessingException {
		objectMapper.addMixInAnnotations(DataCollectPerson.class,
				DataCollectPersonMixin.BasicInfo.class);
		String  dcpId=request.getParameter("dcpId");
		String personId=request.getParameter("personId");
		DataCollectPoint point=pointManager.getDataCollectPoint(dcpId);
		Person person=personManager.findById(personId);
		
		
		User user=person.getUser();
		
		MessageObject messageObject = new MessageObject();
		DataCollectPerson dataCollectPerson=new DataCollectPerson();
		dataCollectPerson.setId(personId);
		dataCollectPerson.setCode(user.getUsername());
		dataCollectPerson.setName(person.getPersName());
		dataCollectPerson.setAdress(person.getAddress());
		dataCollectPerson.setTelephone(person.getMobile());
		dataCollectPerson.setDataCollectPoint(point);
		
		DataCollectPerson saveDataCollectPerson;
		try {
			saveDataCollectPerson = dcpManager
					.newDataCollectPerson(dataCollectPerson);
			person.setIsCollent(1);
			personManager.savePerson(person);
			messageObject.setData(saveDataCollectPerson);
			messageObject.setMessage("保存成功!");
		} catch (Exception e) {
			e.printStackTrace();
			messageObject.setMessage("保存失败!" + e.getMessage());
		}
		return objectMapper.writeValueAsString(messageObject);
	}

}
