package com.yunforge.collect.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.sf.json.JSONObject;

public class TreeNode2 {

	private String id;
	private String text;
	private boolean children = true;
	private Object a_attr;
	@JsonIgnore
	private boolean isLeaf;
	
	public boolean isLeaf() {
		return isLeaf;
	}

	public void setLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	//private List<CtgTreeNode> children;
	
	public Object getA_attr() {
		return a_attr;
	}

	public void setA_attr(Object a_attr) {
		this.a_attr = a_attr;
	}

	public TreeNode2(String id, String text) {
		this.id = id;
		this.text = text;
	}
	
	public TreeNode2(String id, String text, boolean isLeaf) {
		this.id = id;
		this.text = text;
		this.children = !isLeaf;
		JSONObject attr = new JSONObject();
		attr.put("isLeaf", isLeaf);
		this.a_attr = attr;
	}
	
	public TreeNode2(String id, String text, boolean isLeaf, boolean open) {
		this.id = id;
		this.text = text;
		this.children = open;
		JSONObject attr = new JSONObject();
		attr.put("isLeaf", isLeaf);
		this.a_attr = attr;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public boolean isChildren() {
		return children;
	}

	public void setChildren(boolean children) {
		this.children = children;
	}

}
