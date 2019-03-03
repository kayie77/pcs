package com.yunforge.collect.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "oper_datacollectindex")
public class DataCollectIndex {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "ID", nullable = false, updatable = false, length = 100)
	private String id;

	@Column(name = "indexcode", nullable = false, length = 100)
	private String indexCode; // 采集员编码

	@Column(name = "indexname", nullable = true, length = 100)
	private String indexName; // 采集员姓名

	@Transient
	private boolean selected;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIndexCode() {
		return indexCode;
	}

	public void setIndexCode(String indexCode) {
		this.indexCode = indexCode;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	
}
