package com.yunforge.base.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunforge.base.model.Resource;
import com.yunforge.base.service.ResourceManager;
import com.yunforge.common.bean.TreeNode;
import com.yunforge.core.web.controller.BaseController;

@Controller
public class MenuController extends BaseController {
	final static Logger log = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	private ResourceManager resourceManager;

	@RequestMapping(value = "/menu/tree", method = RequestMethod.GET)
	public @ResponseBody
	List<TreeNode> tree(String id, HttpServletRequest request, ModelMap model)
			throws Exception {
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		if (StringUtils.isNotBlank(id) && !id.equals("0")) {
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

		dataList.addAll(this.resourceManager.getSubMenus(pid));
		Map<String, Object> dataMap = null;
		for (Resource menu : dataList) {
			if (super.hasPermissions(menu.getPermission())) {
				node = new TreeNode();
				resId = menu.getId();
				node.setId(String.valueOf(resId));
				node.setText(menu.getResName());
				node.getState().setOpened(false);
				node.getState().setDisabled(false);
				if (menu.getChildren().size() > 0) {
					node.setHasChildren(true);
				} else {
					node.setHasChildren(false);
				}
				dataMap = new HashMap<String, Object>();
				dataMap.put("title", menu.getResName());
				node.setA_attr(dataMap);
				list.add(node);
				dataMap = null;
			}
		}
		return list;
	}
}
