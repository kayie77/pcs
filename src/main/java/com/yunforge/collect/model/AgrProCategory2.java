package com.yunforge.collect.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunforge.collect.jackson.serializer.EnableOrDisableSerializer;

@Entity
@Table(name = "oper_AgrProCategory2")
public class AgrProCategory2 {
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(name="ID", nullable = false, updatable = false, length = 100)
	private String id;
	
	@Column(name="CODE",nullable = false, length = 100)
	private String code; //分类编码
	
	@Column(name="NAME", nullable = false, length = 100)
	private String name; //分类名称
	
	@Column(name="EXPLAIN_", length = 100)
	private String explain; //分类说明
	
	@Column(name="SORT", nullable = true)
	private Integer sort;//排序
	
	@JsonSerialize(using = EnableOrDisableSerializer.class)
	@Column(name="STATE")
	private Integer state;//状态
	
	@Transient
	private String nodeType;
	
	@ManyToOne(fetch=FetchType.LAZY, optional = false)
	@JoinColumn(name = "PID")
	private AgrProCategory2 parent;//父id
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="parent", fetch=FetchType.LAZY)  
    @OrderBy("sort asc")
    private List<AgrProCategory2> children;//孩子
	
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="agrProCategory2", fetch=FetchType.LAZY)  
    private List<TaskDetail> taskDetails;
		
	public static final class STATE{
		public static final int DISABLE  = 0;
		public static final int ENABLE  = 1;

	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public AgrProCategory2 getParent() {
		return parent;
	}

	public void setParent(AgrProCategory2 parent) {
		this.parent = parent;
	}

	public List<AgrProCategory2> getChildren() {
		return children;
	}

	public void setChildren(List<AgrProCategory2> children) {
		this.children = children;
	}

	public List<TaskDetail> getTaskDetails() {
		return taskDetails;
	}

	public void setTaskDetails(List<TaskDetail> taskDetails) {
		this.taskDetails = taskDetails;
	}

}
