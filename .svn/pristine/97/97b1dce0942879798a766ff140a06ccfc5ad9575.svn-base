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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.yunforge.base.model.Group;
import com.yunforge.base.model.User;
import com.yunforge.base.service.GroupManager;
import com.yunforge.base.service.UserManager;
import com.yunforge.common.Constants;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.util.WebUtils;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.web.controller.BaseController;

@Controller
@SessionAttributes("group")
public class GroupController extends BaseController {
	final static Log log = LogFactory.getLog(GroupController.class);

	@Autowired
	private GroupManager groupManager;

	@Autowired
	private UserManager userManager;

	@RequestMapping("/group")
	public String index(HttpServletRequest request, ModelMap model) {
		model.addAttribute("actionUrl", request.getContextPath()
				+ "/group/save");
		String pageSize = WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE,
				pageSize == null ? 20 : Integer.valueOf(pageSize));
		return "/group/group";
	}

	@RequestMapping(value = "/group/list")
	public @ResponseBody
	GridBean<Group> listGroups(Integer page, Integer rows, String sidx,
			String sord, boolean search, String searchField, String searchOper,
			String searchString) {

		int pageSize = rows == null ? 15 : rows.intValue(); // 每页行数
		int pageIndex = page == null ? 0 : page.intValue() - 1;// 当前页数
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
		Page<Group> groups = groupManager.listGroups(searchField, searchOper,
				searchString, pageable);

		List<Group> groupList = new ArrayList<Group>();
		groupList.addAll(groups.getContent());
		Integer records = (int) groups.getTotalElements();

		Integer totalPages = groups.getTotalPages();
		Integer currPage = Math.min(totalPages, page);

		GridBean<Group> grid = new GridBean<Group>();

		grid.setRecords(records);
		grid.setTotal(totalPages);
		grid.setPage(currPage);
		grid.setRows(groupList);
		return grid;
	}

	@MethodRemark(remark = "访问角色群组首页")
	@RequestMapping(value = "/group/roleGroup/{roleId}", method = RequestMethod.GET)
	public String roleGroup(@PathVariable String roleId,
			HttpServletRequest request, ModelMap model) {
		String pageSize = WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE,
				pageSize == null ? 15 : Integer.valueOf(pageSize));
		model.addAttribute("roleId", roleId);
		return "/group/roleGroup";
	}
	
	@RequestMapping(value = "/group/listRoleGroups/{roleId}", method = RequestMethod.GET)
	public @ResponseBody
	GridBean<Group> listRoleGroups(@PathVariable String roleId, Integer rows, Integer page,
			String sidx, String sord, boolean loadonce, String searchField,
			String searchString, String searchOper) {

		int pageSize = rows == null ? 15 : rows.intValue(); // 每页行数
		int pageIndex = page == null ? 0 : page.intValue() - 1;// 当前页数
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
		Page<Group> groups = groupManager.listRoleGroups(roleId, searchField,
				searchOper, searchString, pageable);

		List<Group> groupList = new ArrayList<Group>();
		groupList.addAll(groups.getContent());
		Integer records = (int) groups.getTotalElements();

		Integer totalPages = groups.getTotalPages();
		Integer currPage = Math.min(totalPages, page);

		GridBean<Group> grid = new GridBean<Group>();

		grid.setRecords(records);
		grid.setTotal(totalPages);
		grid.setPage(currPage);
		grid.setRows(groupList);
		return grid;
	}

	@MethodRemark(remark = "新建群组")
	@RequestMapping(value = "/group/new", method = RequestMethod.GET)
	public String create(String id, HttpServletRequest request, ModelMap model) {
		Group group = new Group();
		model.addAttribute("group", group);
		model.addAttribute("actionUrl", request.getContextPath()
				+ "/group/save");

		return "/group/groupForm";
	}

	@MethodRemark(remark = "编辑群组信息")
	@RequestMapping(value = "/group/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, HttpServletRequest request,
			ModelMap model) {
		Group group = groupManager.findGroupById(id);
		model.addAttribute("group", group);
		model.addAttribute("actionUrl", request.getContextPath()
				+ "/group/save");
		return "/group/groupForm";
	}

	@MethodRemark(remark = "查看群组信息")
	@RequestMapping(value = "/groupview/{id}", method = RequestMethod.GET)
	public String view(@PathVariable String id, HttpServletRequest request,
			ModelMap model) {
		Group group = groupManager.findGroupById(id);
		model.addAttribute("group", group);
		return "/group/groupForm";
	}

	@MethodRemark(remark = "删除群组信息")
	@RequestMapping(value = "/group/delete")
	public @ResponseBody
	JSONObject delete(String[] ids, HttpServletRequest request, ModelMap model)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			if (ids != null) {
				groupManager.removeGroups(ids);
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "群组信息已删除!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}

	@RequestMapping(value = "/group/save", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject save(@ModelAttribute("group") Group group, ModelMap model,
			SessionStatus status) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			if (StringUtils.isBlank(group.getId())) {
				group.setId(null);
			}
			groupManager.saveGroup(group);
			jsonObject.put("id", group.getId());
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "群组已保存!");
			status.setComplete();
		} catch (Exception e) {
			if (e.getMessage().indexOf("Error Code: 1062") != -1) {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "群组名重复,请检查!");
			} else {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "操作失败!");
			}
		}
		return jsonObject;
	}

	
	@RequestMapping(value = "/group/addUsers", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject addUsers(String id, String[] users) throws Exception {
		JSONObject jsonObject = new JSONObject();
		if (users != null && users.length > 0) {
			User u = null;
			try {
				Group group = groupManager.findGroupById(id);
				for (int i = 0; i < users.length; i++) {
					u = userManager.findUserById(users[i]);
					if (!group.getUsers().contains(u)) {
						group.getUsers().add(u);
					}
				}
				this.groupManager.saveGroup(group);
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "加入用户成功!");
			} catch (Exception e) {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "加入用户失败!");
				throw e;
			}
		} else {
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "请选择群组的用户!");
		}
		return jsonObject;
	}

	@RequestMapping(value = "/group/removeUsers", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject removeUsers(String id, String[] users) throws Exception {
		JSONObject jsonObject = new JSONObject();
		if (users != null && users.length > 0) {
			User u = null;
			try {
				Group group = groupManager.findGroupById(id);
				for (int i = 0; i < users.length; i++) {
					u = userManager.findUserById(users[i]);
					u.getRoles().remove(group);
					group.getUsers().remove(u);
				}
				this.groupManager.saveGroup(group);
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "移去用户成功!");
			} catch (Exception e) {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "移去用户失败!");
				throw e;
			}
		} else {
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "请选择删除群组的用户!");
		}
		return jsonObject;
	}

	@MethodRemark(remark = "群组锁定")
	@RequestMapping(value = "/group/lock/{id}", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject lockGroup(@PathVariable String id, HttpServletRequest request,
			ModelMap model) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			Group group = groupManager.findGroupById(id);
			group.setEnabled(Boolean.FALSE);
			groupManager.saveGroup(group);
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "群组已锁定!");

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}

	@MethodRemark(remark = "群组解锁")
	@RequestMapping(value = "/group/unLock/{id}", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject unLockGroup(@PathVariable String id, HttpServletRequest request,
			ModelMap model) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			Group group = groupManager.findGroupById(id);
			group.setEnabled(Boolean.TRUE);
			groupManager.saveGroup(group);
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "群组已解锁!");

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}
	
	@MethodRemark(remark = "选择群组")
	@RequestMapping(value = "/group/select", method = RequestMethod.GET)
	public String selectGroup(HttpServletRequest request, ModelMap model) {
		String pageSize = WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE,
				pageSize == null ? 15 : Integer.valueOf(pageSize));
		return "/group/selectGroup";

	}
}
