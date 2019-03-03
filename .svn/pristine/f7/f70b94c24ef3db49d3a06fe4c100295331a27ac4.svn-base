package com.yunforge.cms.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.yunforge.cms.model.InfoCat;
import com.yunforge.cms.service.InfoCatManager;
import com.yunforge.common.bean.TreeNode;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.web.controller.BaseController;

@Controller
@SessionAttributes("infoCat")
public class InfoCatController extends BaseController {
	final static Logger log = LoggerFactory.getLogger(InfoCatController.class);

	@Autowired
	private InfoCatManager infoCatManager;

	@RequestMapping(value = "/cms/infoCat", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		return "/cms/category/infoCat";
	}

	@RequestMapping(value = "/cms/infoCat/create")
	public @ResponseBody JSONObject createRes(String pid,
			HttpServletRequest request, ModelMap model) {
		JSONObject jsonObject = new JSONObject();
		InfoCat infoCat = new InfoCat();
		if (StringUtils.isNotBlank(pid) && !pid.equals("#")) {
			InfoCat parent = infoCatManager.findById(pid);
			infoCat.setParent(parent);
		} else {
			infoCat.setParent(null);
		}
		infoCat = infoCatManager.saveInfoCat(infoCat);
		jsonObject.put("id", infoCat.getId());
		jsonObject.put("text", infoCat.getCatName());
		jsonObject.put("success", Boolean.TRUE);
		jsonObject.put("message", "创建成功!");

		return jsonObject;
	}

	@RequestMapping(value = "/cms/infoCat/{id}", method = RequestMethod.GET)
	public String editInfoCat(@PathVariable String id,
			HttpServletRequest request, ModelMap model) {
		InfoCat infoCat = infoCatManager.findById(id);

		model.addAttribute("actionUrl", request.getContextPath()
				+ "/cms/infoCat/save");
		model.addAttribute("infoCat", infoCat);
		return "/cms/category/infoCatForm";
	}

	@MethodRemark(remark = "保存分类")
	@RequestMapping(value = "/cms/infoCat/save", method = RequestMethod.POST, headers = "Content-Type=application/x-www-form-urlencoded")
	public @ResponseBody JSONObject saveInfoCat(
			@ModelAttribute("infoCat") InfoCat infoCat,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, SessionStatus status) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			if(StringUtils.isNotBlank(infoCat.getId())){
				infoCat.setId(null);
			}
			infoCatManager.saveInfoCat(infoCat);
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "分类已保存!");
			status.setComplete();
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}

	@MethodRemark(remark = "删除指定分类")
	@RequestMapping(value = "/cms/infoCat/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody JSONObject deleteInfoCat(@PathVariable String id,
			HttpServletRequest request, ModelMap model) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			if (StringUtils.isNotBlank(id)) {
				infoCatManager.removeInfoCats(new String[] { id });
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "分类已删除!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}
	
	@RequestMapping(value = "/cms/infoCat/select", method = RequestMethod.GET)
	public String selectCat(HttpServletRequest request, ModelMap model) throws Exception {
		return "/cms/category/selectCat";
	}

	@RequestMapping(value = "/cms/infoCat/tree", method = RequestMethod.GET)
	public @ResponseBody List<TreeNode> tree(String id,
			HttpServletRequest request, ModelMap model) throws Exception {
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
		List<InfoCat> dataList = new ArrayList<InfoCat>();
		List<TreeNode> list = new ArrayList<TreeNode>();

		dataList.addAll(this.infoCatManager.getChildren(pid));
		Map<String, Object> dataMap = null;
		for (InfoCat infoCat : dataList) {
			node = new TreeNode();
			resId = infoCat.getId();
			node.setId(String.valueOf(resId));
			node.setText(infoCat.getCatName());
			node.getState().setOpened(false);
			node.getState().setDisabled(false);
			if (this.infoCatManager.hasChildren(infoCat.getId())) {
				node.setHasChildren(true);
			} else {
				node.setHasChildren(false);
			}
			dataMap = new HashMap<String, Object>();
			dataMap.put("title", infoCat.getCatName());
			node.setA_attr(dataMap);
			list.add(node);
			dataMap = null;
		}
		return list;
	}
}
