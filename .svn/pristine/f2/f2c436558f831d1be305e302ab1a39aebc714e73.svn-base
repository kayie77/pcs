package com.yunforge.base.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.yunforge.base.model.Org;
import com.yunforge.base.model.Person;
import com.yunforge.base.service.OrgManager;
import com.yunforge.base.service.PersonManager;
import com.yunforge.common.Constants;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.bean.TreeNode;
import com.yunforge.common.util.WebUtils;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.web.controller.BaseController;

@Controller
@SessionAttributes("org")
public class OrgController extends BaseController {
	final static Logger log = LoggerFactory.getLogger(OrgController.class);

	@Autowired
	private OrgManager orgManager;

	@Autowired
	private PersonManager personManager;

	@RequestMapping(value = "/org", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		String pageSize = WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE,
				pageSize == null ? 15 : Integer.valueOf(pageSize));
		return "/org/org";
	}

	@RequestMapping("/org/list")
	public @ResponseBody
	GridBean<Org> listOrgs(String pid, Integer page, Integer rows, String sidx,
			String sord, boolean search, String searchField, String searchOper,
			String searchString, String filters) {

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
		Page<Org> orgs = orgManager.listOrgs(pid, filters, pageable);

		List<Org> orgList = new ArrayList<Org>();
		orgList.addAll(orgs.getContent());
		Integer records = (int) orgs.getTotalElements();

		Integer totalPages = orgs.getTotalPages();
		Integer currPage = Math.min(totalPages, pageIndex);

		GridBean<Org> grid = new GridBean<Org>();

		grid.setRecords(records);
		grid.setTotal(totalPages);
		grid.setPage(currPage);
		grid.setRows(orgList);
		return grid;
	}
	
	@MethodRemark(remark = "访问角色机构首页")
	@RequestMapping(value = "/org/roleOrg/{roleId}", method = RequestMethod.GET)
	public String roleOrg(@PathVariable String roleId,
			HttpServletRequest request, ModelMap model) {
		String pageSize = WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE,
				pageSize == null ? 15 : Integer.valueOf(pageSize));
		model.addAttribute("roleId", roleId);
		return "/org/roleOrg";
	}
	
	@MethodRemark(remark = "访问指定角色机构列表")
	@RequestMapping(value = "/org/listRoleOrgs/{roleId}", method = RequestMethod.GET)
	public @ResponseBody
	GridBean<Org> listRoleOrgs(@PathVariable String roleId, Integer rows, Integer page,
			String sidx, String sord, boolean loadonce, String filters) {

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
		Page<Org> orgs = orgManager.listRoleOrgs(roleId, filters, pageable);

		List<Org> orgList = new ArrayList<Org>();
		orgList.addAll(orgs.getContent());
		Integer records = (int) orgs.getTotalElements();

		Integer totalPages = orgs.getTotalPages();
		Integer currPage = Math.min(totalPages, page);

		GridBean<Org> grid = new GridBean<Org>();

		grid.setRecords(records);
		grid.setTotal(totalPages);
		grid.setPage(currPage);
		grid.setRows(orgList);
		return grid;
	}

	@RequestMapping("/org/treeList")
	public @ResponseBody
	GridBean<Org> listOrgTree(String nodeid, Integer level, boolean search,
			String searchField, String searchOper, String searchString) {
		List<Org> orgs = new ArrayList<Org>();
		List<Org> orgList = new ArrayList<Org>();
		if (level == null) {
			level = 0;
		}

		if (StringUtils.isNotBlank(nodeid)) {
			level = level + 1;
		}
		log.info("nodeid:" + nodeid);
		orgs.addAll(orgManager.getChildren(nodeid));
		for (Org org : orgs) {
			Org parent = org.getParent();
			if (parent == null) {
				org.setPid(null);
			} else {
				org.setPid(parent.getId());
			}
			if (orgManager.hasChildren(org.getId())) {
				org.setIsLeaf(Boolean.FALSE);
			}
			org.setLevel(level);
			org.setExpanded(Boolean.FALSE);
			orgList.add(org);
		}

		GridBean<Org> grid = new GridBean<Org>();

		grid.setRecords(1);
		grid.setTotal(1);
		grid.setPage(1);
		grid.setRows(orgList);
		return grid;
	}

	@MethodRemark(remark = "新建机构")
	@RequestMapping(value = "/org/new", method = RequestMethod.GET)
	public String newOrg(String pid, HttpServletRequest request, ModelMap model) {
		Org org = new Org();
		if (StringUtils.isNotBlank(pid)) {
			Org parent = orgManager.findById(pid);
			org.setParent(parent);
			parent.getChildren().add(org);
		}
		model.addAttribute("org", org);
		model.addAttribute("actionUrl", request.getContextPath() + "/org/save");
		return "/org/orgForm";
	}
	
	@MethodRemark(remark = "编辑机构信息")
	@RequestMapping(value = "/org/edit/{id}", method = RequestMethod.GET)
	public String editOrg(@PathVariable String id, HttpServletRequest request,
			ModelMap model) {
		Org org = orgManager.findById(id);
		model.addAttribute("actionUrl", request.getContextPath() + "/org/save");
		model.addAttribute("org", org);
		return "/org/orgForm";
	}

	@MethodRemark(remark = "查看机构信息")
	@RequestMapping(value = "/org/view/{id}", method = RequestMethod.GET)
	public String viewOrg(@PathVariable String id, HttpServletRequest request,
			ModelMap model) {
		Org org = orgManager.findById(id);
		model.addAttribute("org", org);
		return "/org/orgForm";
	}

	@MethodRemark(remark = "保存机构")
	@RequestMapping(value = "/org/save", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject save(@ModelAttribute("org") Org org, HttpServletRequest request,
			ModelMap model, SessionStatus status) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			if (StringUtils.isBlank(org.getId())) {
				org.setId(null);
			}
			orgManager.saveOrg(org);
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "机构已保存!");
			status.setComplete();
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}

	@MethodRemark(remark = "删除指定机构")
	@RequestMapping(value = "/org/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	JSONObject delete(@PathVariable String id, HttpServletRequest request,
			ModelMap model) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			if (StringUtils.isNotBlank(id)) {
				Org org = orgManager.findById(id);
				org.setParent(null);
				for (Person person : org.getPersons()) {
					person.setOrg(null);
				}
				orgManager.removeOrg(org);
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "机构已删除!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}

	@RequestMapping("/org/addPersons")
	public @ResponseBody
	JSONObject addPersons(String id, String[] persons) throws Exception {
		JSONObject jsonObject = new JSONObject();
		if (persons != null && persons.length > 0) {
			Person person = null;
			try {
				Org o = orgManager.findById(id);
				for (int i = 0; i < persons.length; i++) {
					person = personManager.findById(persons[i]);
					if (!o.getPersons().contains(person)) {
						o.getPersons().add(person);
						person.setOrg(o);
					}
				}
				this.orgManager.saveOrg(o);
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "加入人员成功!");
			} catch (Exception e) {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "加入人员失败!");
				throw e;
			}
		} else {
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "请选择要加入组织机构的人员!");
		}
		return jsonObject;
	}

	@RequestMapping("/org/removePersons")
	public @ResponseBody
	JSONObject removePersons(String id, String[] persons) throws Exception {
		JSONObject jsonObject = new JSONObject();
		if (persons != null && persons.length > 0) {
			Person person = null;
			try {
				Org o = orgManager.findById(id);
				for (int i = 0; i < persons.length; i++) {
					person = personManager.findById(persons[i]);
					o.getPersons().remove(person);
					person.setOrg(null);
				}
				this.personManager.savePerson(person);
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "移去人员成功!");
			} catch (Exception e) {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "移去人员失败!");
				throw e;
			}
		} else {
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "请选择删除组织的人员!");
		}
		return jsonObject;
	}

	@RequestMapping(value = "/org/treeData", method = RequestMethod.GET)
	public @ResponseBody
	List<TreeNode> tree(String id, boolean includeUser) throws Exception {
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		if (StringUtils.isNotBlank(id) && !id.equals("#")) {
			nodes.addAll(getTree(id, includeUser));
		} else {
			nodes.addAll(getTree(null, includeUser));
		}
		return nodes;
	}

	private List<TreeNode> getTree(String pid, boolean includeUser)
			throws Exception {
		String orgId = null;
		String code = null;
		TreeNode node = null;
		List<Org> orgList = new ArrayList<Org>();
		List<Person> empList = new ArrayList<Person>();
		List<TreeNode> list = new ArrayList<TreeNode>();

		orgList.addAll(this.orgManager.getChildren(pid));
		Map<String, Object> dataMap = null;
		for (Org org : orgList) {
			node = new TreeNode();
			orgId = org.getId();
			code = org.getOrgCode();
			node.setId("node_" + String.valueOf(orgId));
			node.setText(org.getOrgName());
			node.getState().setOpened(false);
			node.getState().setDisabled(false);
			if (org.getChildren().size() > 0) {
				node.setHasChildren(true);
			} else {
				node.setHasChildren(false);
			}
			dataMap = new HashMap<String, Object>();
			dataMap.put("code", code);
			dataMap.put("title", org.getOrgName());
			dataMap.put("type", "org");
			node.setA_attr(dataMap);
			list.add(node);
			dataMap = null;
		}
		if (includeUser) {
			if (StringUtils.isNotBlank(pid) && !pid.equals("0")) {
				String userId = null;
				// userList.addAll(userManager.findByOrg(pid));
				for (Person emp : empList) {
					node = new TreeNode();
					userId = emp.getId();
					node.setId(String.valueOf(userId));
					node.setIcon("ui-icon-person");
					node.setText(emp.getPersName());
					node.getState().setOpened(false);
					node.getState().setDisabled(false);
					node.setType("file");
					dataMap = new HashMap<String, Object>();
					dataMap.put("id", String.valueOf(userId));
					dataMap.put("title", emp.getPersName());
					dataMap.put("type", "user");
					node.setA_attr(dataMap);
					list.add(node);
					dataMap = null;
				}
			}
		}
		return list;
	}

	@RequestMapping(value = "/org/tree", method = RequestMethod.GET)
	public @ResponseBody
	List<TreeNode> tree(String id, HttpServletRequest request, ModelMap model)
			throws Exception {
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		if (StringUtils.isNotBlank(id) && !id.equals("#")) {
			nodes.addAll(getTree(id));
		} else {
			nodes.addAll(getTree(null));
		}
		Collections.sort(nodes,new Comparator<TreeNode>() {
			public int compare(TreeNode o1, TreeNode o2) {
				try {
					return o1.getCode().compareTo(o2.getCode());
				} catch(Exception e) {
					
				}
				return 0;
			}
		});
		return nodes;
	}

	private List<TreeNode> getTree(String pid) throws Exception {
		String resId = null;
		TreeNode node = null;
		List<Org> dataList = new ArrayList<Org>();
		List<TreeNode> list = new ArrayList<TreeNode>();

		dataList.addAll(this.orgManager.getChildren(pid));
		Map<String, Object> dataMap = null;
		for (Org item : dataList) {
			node = new TreeNode();
			resId = item.getId();
			node.setId(String.valueOf(resId));
			node.setText(item.getOrgName());
			node.getState().setOpened(false);
			node.getState().setDisabled(false);
			if (this.orgManager.hasChildren(item.getId())) {
				node.setHasChildren(true);
			} else {
				node.setHasChildren(false);
			}
			dataMap = new HashMap<String, Object>();
			dataMap.put("title", item.getOrgName());
			node.setA_attr(dataMap);
			node.setCode(item.getDivCode());
			list.add(node);
			dataMap = null;
		}
		return list;
	}

	@RequestMapping(value = "/org/select", method = RequestMethod.GET)
	public String selectOrg(HttpServletRequest request, ModelMap model) {
		String pageSize = WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE,
				pageSize == null ? 15 : Integer.valueOf(pageSize));
		return "/org/selectOrg";

	}

}
