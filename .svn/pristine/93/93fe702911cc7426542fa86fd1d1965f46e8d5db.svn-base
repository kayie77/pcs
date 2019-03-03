package com.yunforge.base.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunforge.base.model.Resource;
import com.yunforge.base.model.Role;
import com.yunforge.base.service.ResourceManager;
import com.yunforge.common.bean.TreeNode;
import com.yunforge.core.web.controller.BaseController;

@Controller
public class MenuTreeController extends BaseController {
	@Autowired
	private ResourceManager resourceManager;

	@RequestMapping("/menu")
	public @ResponseBody
	List<TreeNode> execute(String id) throws Exception {
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		if (StringUtils.isNotBlank(id) && !id.equals("0")) {
			nodes.addAll(getTree(id));
		}
		return nodes;
	}

	private List<TreeNode> getTree(String menuId) throws Exception {
		String id = null;
		TreeNode node = null;
		List<Resource> menuList = new ArrayList<Resource>();
		List<TreeNode> list = new ArrayList<TreeNode>();

		for (Resource menu : this.resourceManager.getChildren(menuId)) {
			if (menu.isEnabled()) {
				for (Role r : menu.getRoles()) {
					if (super.hasRole(r.getRoleName())) {
						menuList.add(menu);
						break;
					}
				}
			}
		}
		Map<String, Object> dataMap = null;
		for (Resource menu : menuList) {
			node = new TreeNode();
			id = menu.getId();
			node.setId(String.valueOf(id));
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
			dataMap.put("menuUrl", menu.getResString());
			dataMap.put("iconCls", menu.getIconCls());
			node.setA_attr(dataMap);
			list.add(node);
			dataMap = null;
		}
		return list;
	}

}
