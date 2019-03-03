package com.yunforge.collect.service;

import java.util.List;

import com.yunforge.collect.dto.TreeNode;
import com.yunforge.collect.model.GropInfo;

public interface AgrProCategoryManager {
		
	//得到农产品分类子节点
	List<TreeNode> getChildrenAgrProCategoryTreeNode();
	List<GropInfo> getChildrenAgrProCategory();
	List<GropInfo> getAllAgrProCategory();
}
