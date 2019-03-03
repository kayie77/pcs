package com.yunforge.base.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.yunforge.base.model.Person;
import com.yunforge.base.model.User;
import com.yunforge.base.service.PersonManager;
import com.yunforge.common.Constants;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.util.WebUtils;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.web.controller.BaseController;

@Controller
@SessionAttributes("person")
public class PersonController extends BaseController {
	final static Log log = LogFactory.getLog(PersonController.class);

	@Autowired
	private PersonManager personManager;

	@MethodRemark(remark = "访问人员列表")
	@RequestMapping(value = "/person/list")
	public @ResponseBody
	GridBean<Person> listPersons(Integer page, Integer rows, String sidx,
			String sord, boolean search, String searchField, String searchOper,
			String searchString, String filters,String persNameParam) {
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
		Page<Person> persons = personManager.listPersons(filters, pageable,persNameParam);

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

	@MethodRemark(remark = "访问指定机构人员列表")
	@RequestMapping("/person/listOrgPersons/{orgId}")
	public @ResponseBody
	GridBean<Person> listOrgPersons(@PathVariable String orgId, Integer rows,
			Integer page, String sidx, String sord, boolean loadonce,
			String filters) {
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
		Page<Person> persons = personManager.listOrgPersons(orgId, filters,
				pageable);

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

	
	@MethodRemark(remark = "编辑人员信息")
	@RequestMapping(value = "/person/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, HttpServletRequest request,
			ModelMap model) {
		Person person = personManager.findById(id);
		model.addAttribute("person", person);
		model.addAttribute("actionUrl", request.getContextPath() + "/person/update");
		return "/person/personForm";
	}

	@MethodRemark(remark = "查看人员信息")//暂时不用
	@RequestMapping(value = "/person/view/{id}", method = RequestMethod.GET)
	public String doView(@PathVariable String id, ModelMap model) {
		Person person = personManager.findById(id);
		model.addAttribute("person", person);
		model.addAttribute("oper", "view");
		return "/person/viewForm";
	}
	
	@MethodRemark(remark = "更新人员")
	@RequestMapping(value = "/person/update", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject updateUser(@ModelAttribute("person") Person person,
			HttpServletRequest request, ModelMap model,SessionStatus status) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			personManager.savePerson(person);
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "人员信息已更新!");
            status.setComplete();
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}

	@MethodRemark(remark = "删除指定人员")
	@RequestMapping(value = "/person/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	JSONObject doDelete(@PathVariable String id,HttpServletRequest request, ModelMap model) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			if (StringUtils.isNotBlank(id)) {
				personManager.removePersons(new String[]{id});;
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "人员信息已删除!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}
	
	@MethodRemark(remark = "批量删除人员")//暂时不用
	@RequestMapping(value = "/person/delete", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject doDelete(@RequestParam("ids") String[] ids) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			if (ids != null) {
				personManager.removePersons(ids);
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "人员信息已删除!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}

	@MethodRemark(remark = "选择人员")
	@RequestMapping(value = "/person/select", method = RequestMethod.GET)
	public String selectPerson(HttpServletRequest request, ModelMap model) {
		String pageSize = WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE,
				pageSize == null ? 15 : Integer.valueOf(pageSize));
		return "/person/selectPerson";

	}

}
