package com.yunforge.collect.dto;

public class TreeNode {
	private String id;
	private String text;
/*	private boolean children = true;
	private Object a_attr;
	@JsonIgnore
	private boolean isLeaf;*/

	public TreeNode(String id, String text) {
		this.id = id;
		this.text = text;
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

}
