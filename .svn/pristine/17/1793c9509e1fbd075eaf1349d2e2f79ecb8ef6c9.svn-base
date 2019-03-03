package com.yunforge.collect.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yunforge.collect.jackson.serializer.EnableOrDisableSerializer;

@Entity
@Table(name = "oper_GropInfo")
public class GropInfo {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(name="ID", nullable = false, updatable = false, length = 100)
	private String id;

	@Column(name="CODE", length = 100)
	private String code; //编码
	
	@Column(name="NAME", nullable = false, length = 100)
	private String name; //名称
	
	@Column(name="EXPLAIN_", length = 500)
	private String explain; //说明
	
	@JsonSerialize(using = EnableOrDisableSerializer.class)
	@Column(name="STATE")
	private Integer state;//状态

	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="gropInfo", fetch=FetchType.LAZY)  
    private List<PersonGroup> personGroups;

	@Transient
	private List<DataCollectPerson> personList;
	
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

	public List<PersonGroup> getPersonGroups() {
		return personGroups;
	}

	public void setPersonGroups(List<PersonGroup> personGroups) {
		this.personGroups = personGroups;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public List<DataCollectPerson> getPersonList() {
		return personList;
	}

	public void setPersonList(List<DataCollectPerson> personList) {
		this.personList = personList;
	}

}
