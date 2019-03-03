package com.yunforge.base.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.yunforge.base.model.Resource;
import com.yunforge.base.model.Role;
import com.yunforge.base.service.ResourceManager;
import com.yunforge.base.service.RoleManager;
import com.yunforge.common.Constants;
import com.yunforge.common.bean.TreeNode;
import com.yunforge.common.util.WebUtils;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.web.controller.BaseController;

@Controller
@SessionAttributes("resource")
public class ResourceController extends BaseController {
	final static Logger log = LoggerFactory.getLogger(ResourceController.class);

	@Autowired
	private ResourceManager resourceManager;

	@Autowired
	private RoleManager roleManager;

	@RequestMapping(value = "/resource", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		String pageSize = WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE,
				pageSize == null ? 15 : Integer.valueOf(pageSize));
		return "/resource/resource";
	}

	@MethodRemark(remark = "新建资源")
	@RequestMapping(value = "/resource/new", method = RequestMethod.GET)
	public String newOrg(String pid, HttpServletRequest request, ModelMap model) {
		Resource resource = new Resource();
		if (StringUtils.isNotBlank(pid)) {
			Resource parent = resourceManager.findById(pid);
			resource.setParent(parent);
			parent.getChildren().add(resource);
		}
		model.addAttribute("resource", resource);
		model.addAttribute("actionUrl", request.getContextPath()
				+ "/resource/save");
		return "/resource/resourceForm";
	}

	@MethodRemark(remark = "编辑资源")
	@RequestMapping(value = "/resource/edit/{id}", method = RequestMethod.GET)
	public String editResource(@PathVariable String id,
			HttpServletRequest request, ModelMap model) {
		Resource resource = resourceManager.findById(id);

		model.addAttribute("actionUrl", request.getContextPath()
				+ "/resource/save");
		model.addAttribute("resource", resource);
		return "/resource/resourceForm";
	}

	@MethodRemark(remark = "查看资源信息")
	@RequestMapping(value = "/resource/view/{id}", method = RequestMethod.GET)
	public String viewResource(@PathVariable String id,
			HttpServletRequest request, ModelMap model) {
		Resource resource = resourceManager.findById(id);
		model.addAttribute("resource", resource);
		return "/resource/resourceForm";
	}

	@MethodRemark(remark = "保存资源")
	@RequestMapping(value = "/resource/save", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject saveResource(@ModelAttribute("resource") Resource resource,
			HttpServletRequest request, ModelMap model, SessionStatus status)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			if (StringUtils.isBlank(resource.getId())) {
				resource.setId(null);
			}
			resourceManager.saveResource(resource);
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "资源已保存!");
			status.setComplete();
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}

	@MethodRemark(remark = "删除指定资源")
	@RequestMapping(value = "/resource/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	JSONObject deleteResource(@PathVariable String id,
			HttpServletRequest request, ModelMap model) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			if (StringUtils.isNotBlank(id)) {
				resourceManager.removeResources(new String[] { id });
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "资源已删除!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}

	@RequestMapping("/resource/addRoles")
	public @ResponseBody
	JSONObject addRoles(String resId, String[] roles) throws Exception {
		JSONObject jsonObject = new JSONObject();
		if (roles != null && roles.length > 0) {
			Role r = null;
			try {
				Resource resource = resourceManager.findById(resId);
				Resource parent = resource.getParent();
				for (int i = 0; i < roles.length; i++) {
					r = roleManager.findRoleById(roles[i]);
					if (!resource.getRoles().contains(r)) {
						resource.getRoles().add(r);
						r.getResources().add(resource);
					}
					if (parent != null) {
						if (!parent.getRoles().contains(r)) {
							parent.getRoles().add(r);
							r.getResources().add(parent);
						}
					}
				}
				this.resourceManager.saveResource(resource);
				this.resourceManager.saveResource(parent);

				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "分配角色成功!");
			} catch (Exception e) {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "分配角色失败!");
				throw e;
			}
		} else {
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "请选择资源访问的角色!");
		}
		return jsonObject;
	}

	@RequestMapping("/resource/removeRoles")
	public @ResponseBody
	JSONObject removeRoles(String resId, String[] roles) throws Exception {
		JSONObject jsonObject = new JSONObject();
		if (roles != null && roles.length > 0) {
			Role r = null;
			try {
				Resource resource = resourceManager.findById(resId);

				for (int i = 0; i < roles.length; i++) {
					r = roleManager.findRoleById(roles[i]);
					r.getResources().remove(resource);
					resource.getRoles().remove(r);
				}
				this.resourceManager.saveResource(resource);
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "移去角色成功!");
			} catch (Exception e) {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "移去角色失败!");
				throw e;
			}
		} else {
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "请选择删除资源的角色!");
		}
		return jsonObject;
	}

	@RequestMapping(value = "/resource/tree", method = RequestMethod.GET)
	public @ResponseBody
	List<TreeNode> tree(String id, HttpServletRequest request, ModelMap model)
			throws Exception {
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		if (StringUtils.isNotBlank(id) && !id.equals("#")) {
			nodes.addAll(getTree(id));
		} else {
			nodes.addAll(getTree(null));
		}
		return nodes;
	}

	private List<TreeNode> getTree(String pid) throws Exception {
		String resId = null;
		TreeNode node = null;
		List<Resource> dataList = new ArrayList<Resource>();
		List<TreeNode> list = new ArrayList<TreeNode>();

		dataList.addAll(this.resourceManager.getChildren(pid));
		Map<String, Object> dataMap = null;
		for (Resource resource : dataList) {
			node = new TreeNode();
			resId = resource.getId();
			node.setId(String.valueOf(resId));
			node.setText(resource.getResName());
			node.getState().setOpened(false);
			node.getState().setDisabled(false);
			if (this.resourceManager.hasChildren(resource.getId())) {
				node.setHasChildren(true);
			} else {
				node.setHasChildren(false);
			}
			dataMap = new HashMap<String, Object>();
			dataMap.put("title", resource.getResName());
			node.setA_attr(dataMap);
			list.add(node);
			dataMap = null;
		}
		return list;
	}
}
