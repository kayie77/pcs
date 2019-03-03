package com.yunforge.base.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
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
import com.yunforge.base.model.Org;
import com.yunforge.base.model.Role;
import com.yunforge.base.model.User;
import com.yunforge.base.service.GroupManager;
import com.yunforge.base.service.OrgManager;
import com.yunforge.base.service.RoleManager;
import com.yunforge.base.service.UserManager;
import com.yunforge.common.Constants;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.util.WebUtils;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.web.controller.BaseController;

@Controller
@SessionAttributes("role")
public class RoleController extends BaseController {

	@Autowired
	private RoleManager roleManager;

	@Autowired
	private UserManager userManager;

	@Autowired
	private GroupManager groupManager;

	@Autowired
	private OrgManager orgManager;

	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		model.addAttribute("actionUrl", request.getContextPath() + "/role/save");
		String pageSize = WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE,
				pageSize == null ? 15 : Integer.valueOf(pageSize));
		return "/role/role";
	}

	@RequestMapping(value = "/role/list")
	public @ResponseBody
	GridBean<Role> listRoles(Integer page, Integer rows, String sidx,
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
		Page<Role> roles = roleManager.listRoles(searchField, searchOper,
				searchString, pageable);

		List<Role> roleList = new ArrayList<Role>();
		roleList.addAll(roles.getContent());
		Integer records = (int) roles.getTotalElements();

		Integer totalPages = roles.getTotalPages();
		Integer currPage = Math.min(totalPages, page);

		GridBean<Role> grid = new GridBean<Role>();

		grid.setRecords(records);
		grid.setTotal(totalPages);
		grid.setPage(currPage);
		grid.setRows(roleList);
		return grid;
	}

	@RequestMapping(value = "/role/listResRoles/{resId}", method = RequestMethod.GET)
	public @ResponseBody
	GridBean<Role> listResRoles(@PathVariable String resId, Integer rows,
			Integer page, String sidx, String sord, boolean loadonce,
			String searchField, String searchString, String searchOper) {

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
		Page<Role> roles = roleManager.listResRoles(resId, searchField,
				searchOper, searchString, pageable);

		List<Role> roleList = new ArrayList<Role>();
		roleList.addAll(roles.getContent());
		Integer records = (int) roles.getTotalElements();

		Integer totalPages = roles.getTotalPages();
		Integer currPage = Math.min(totalPages, page);

		GridBean<Role> grid = new GridBean<Role>();

		grid.setRecords(records);
		grid.setTotal(totalPages);
		grid.setPage(currPage);
		grid.setRows(roleList);
		return grid;
	}

	@MethodRemark(remark = "新建角色")
	@RequestMapping(value = "/role/new", method = RequestMethod.GET)
	public String newRole(HttpServletRequest request, ModelMap model) {
		Role role = new Role();
		model.addAttribute("role", role);
		model.addAttribute("actionUrl", request.getContextPath() + "/role/save");

		return "/role/roleForm";
	}

	@MethodRemark(remark = "编辑角色信息")
	@RequestMapping(value = "/role/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, HttpServletRequest request,
			ModelMap model) {
		Role role = roleManager.findRoleById(id);
		model.addAttribute("role", role);
		model.addAttribute("actionUrl", request.getContextPath() + "/role/save");
		return "/role/roleForm";
	}

	@MethodRemark(remark = "编辑角色信息")
	@RequestMapping(value = "/role/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable String id, HttpServletRequest request,
			ModelMap model) {
		Role role = roleManager.findRoleById(id);
		model.addAttribute("role", role);
		model.addAttribute("actionUrl", request.getContextPath() + "/role/save");
		return "/role/roleForm";
	}

	@MethodRemark(remark = "删除角色信息")
	@RequestMapping(value = "/role/delete")
	public @ResponseBody
	JSONObject delte(String[] ids, HttpServletRequest request, ModelMap model)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			if (ids != null) {
				roleManager.removeRoles(ids);
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "角色信息已删除!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}

	@RequestMapping(value = "/role/save", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject save(@ModelAttribute("Role") Role role, SessionStatus status)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			if (StringUtils.isBlank(role.getId())) {
				role.setId(null);
			}
			roleManager.saveRole(role);
			jsonObject.put("id", role.getId());
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "角色已保存!");
			status.setComplete();
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().indexOf("Error Code: 1062") != -1) {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "角色名复,请检查!");
			} else {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "操作失败!");
			}
		}
		return jsonObject;
	}

	@RequestMapping(value = "/role/addUsers", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject addUsers(String id, String[] users) throws Exception {
		JSONObject jsonObject = new JSONObject();
		if (users != null && users.length > 0) {
			User u = null;
			try {
				Role role = roleManager.findRoleById(id);
				for (int i = 0; i < users.length; i++) {
					u = userManager.findUserById(users[i]);
					if (!role.getUsers().contains(u)) {
						role.getUsers().add(u);
					}
				}
				this.roleManager.saveRole(role);
				
/*
				for(int i = 0;i < users.length;i++) {
					
					if(!roleManager.existsUserRole(users[i], id)) {
						
						UserRole userRole = new UserRole();
						UserRolePK userRolePK = new UserRolePK();
						
						userRolePK.setRoleId(id);
						userRolePK.setUserId(users[i]);
						
						userRole.setUserRolePK(userRolePK);
						roleManager.saveUserRole(userRole);
					}
				}
*/
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "加入用户成功!");
			} catch (Exception e) {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "加入用户失败!");
				throw e;
			}
		} else {
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "请选择授予角色的用户!");
		}
		return jsonObject;
	}

	@RequestMapping(value = "/role/removeUsers", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject removeUsers(String id, String[] users) throws Exception {
		JSONObject jsonObject = new JSONObject();
		if (users != null && users.length > 0) {
			User u = null;
			try {
				Role role = roleManager.findRoleById(id);
				for (int i = 0; i < users.length; i++) {
					u = userManager.findUserById(users[i]);
					u.getRoles().remove(role);
					role.getUsers().remove(u);
				}
				this.roleManager.saveRole(role);
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "移去用户成功!");
			} catch (Exception e) {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "移去用户失败!");
				throw e;
			}
		} else {
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "请选择删除角色的用户!");
		}
		return jsonObject;
	}

	@RequestMapping(value = "/role/addGroups", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject addGroups(String id, String[] groups) throws Exception {
		JSONObject jsonObject = new JSONObject();
		if (groups != null && groups.length > 0) {
			Group g = null;
			try {
				Role role = roleManager.findRoleById(id);
				for (int i = 0; i < groups.length; i++) {
					g = groupManager.findGroupById(groups[i]);
					if (!role.getGroups().contains(g)) {
						role.getGroups().add(g);
						g.getRoles().add(role);
					}
				}
				this.roleManager.saveRole(role);
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "加入群组成功!");
			} catch (Exception e) {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "加入群组失败!");
				throw e;
			}
		} else {
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "请选择授予角色的群组!");
		}
		return jsonObject;
	}

	@RequestMapping(value = "/role/removeGroups", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject removeGroups(String id, String[] groups) throws Exception {
		JSONObject jsonObject = new JSONObject();
		if (groups != null && groups.length > 0) {
			Group g = null;
			try {
				Role role = roleManager.findRoleById(id);
				for (int i = 0; i < groups.length; i++) {
					g = groupManager.findGroupById(groups[i]);
					if (role.getGroups().contains(g)) {
						role.getGroups().remove(g);
						g.getRoles().remove(role);
					}
				}
				this.roleManager.saveRole(role);
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "移去群组成功!");
			} catch (Exception e) {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "移去群组失败!");
				throw e;
			}
		} else {
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "请选择收回角色的群组!");
		}
		return jsonObject;
	}

	@RequestMapping(value = "/role/addOrgs", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject addOrgs(String id, String[] orgs) throws Exception {
		JSONObject jsonObject = new JSONObject();
		if (orgs != null && orgs.length > 0) {
			Org o = null;
			try {
				Role role = roleManager.findRoleById(id);
				for (int i = 0; i < orgs.length; i++) {
					o = orgManager.findById(orgs[i]);
					if (!role.getOrgs().contains(o)) {
						role.getOrgs().add(o);
						o.getRoles().add(role);
					}
				}
				this.roleManager.saveRole(role);
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "加入机构成功!");
			} catch (Exception e) {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "加入机构失败!");
				throw e;
			}
		} else {
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "请选择授予角色的机构!");
		}
		return jsonObject;
	}

	@RequestMapping(value = "/role/removeOrgs", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject removeOrgs(String id, String[] orgs) throws Exception {
		JSONObject jsonObject = new JSONObject();
		if (orgs != null && orgs.length > 0) {
			Org o = null;
			try {
				Role role = roleManager.findRoleById(id);
				for (int i = 0; i < orgs.length; i++) {
					o = orgManager.findById(orgs[i]);
					if (role.getOrgs().contains(o)) {
						role.getOrgs().remove(o);
						o.getRoles().remove(role);
					}
				}
				this.roleManager.saveRole(role);
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "移去机构成功!");
			} catch (Exception e) {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "移去机构失败!");
				throw e;
			}
		} else {
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "请选择收回角色的机构!");
		}
		return jsonObject;
	}

	@MethodRemark(remark = "角色锁定")
	@RequestMapping(value = "/role/lock/{id}", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject lockRole(@PathVariable String id, HttpServletRequest request,
			ModelMap model) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			Role role = roleManager.findRoleById(id);
			role.setEnabled(Boolean.FALSE);
			roleManager.saveRole(role);
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "角色已锁定!");

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}

	@MethodRemark(remark = "角色解锁")
	@RequestMapping(value = "/role/unLock/{id}", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject unLockRole(@PathVariable String id, HttpServletRequest request,
			ModelMap model) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			Role role = roleManager.findRoleById(id);
			role.setEnabled(Boolean.TRUE);
			roleManager.saveRole(role);
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "角色已解锁!");

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}

	@MethodRemark(remark = "选择角色")
	@RequestMapping(value = "/role/select", method = RequestMethod.GET)
	public String selectGroup(HttpServletRequest request, ModelMap model) {
		String pageSize = WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE,
				pageSize == null ? 15 : Integer.valueOf(pageSize));
		return "/role/selectRole";

	}
}