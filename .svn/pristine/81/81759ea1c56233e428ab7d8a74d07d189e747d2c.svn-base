package com.yunforge.collect.service;

import java.util.List;

import com.yunforge.collect.dto.TreeNode;
import com.yunforge.collect.dto.TreeNode2;
import com.yunforge.collect.model.GropInfo;
import com.yunforge.collect.model.AgrProCategory2;

public interface AgrProCategory2Manager {
	
	public List<AgrProCategory2> getChildrenAgrProCategory2(String pid, Integer state);
	
	public List<AgrProCategory2> setNodeType(List<AgrProCategory2> ctgList);
	
	public boolean isLeaf(String id);
	
	public AgrProCategory2 getAgrProCategory2(String id);
	
	public AgrProCategory2 saveAgrProCategory2(AgrProCategory2 object);
	
	public int batchEnableOrDisable(String rootId, Integer state);
	
	public AgrProCategory2 newAgrProCategory2(AgrProCategory2 agrProCategory2);
	
	public List<TreeNode2> getChildrenAgrProCategory2TreeNode(String pid, Integer state, String expanded);
	
	public void delAgrProCategory2(String id) throws Exception;
	
	public List<AgrProCategory2> searchAgrProCategory2ByName(String name);
	
	public boolean isBottomLevel(String id);
}
