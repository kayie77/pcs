package com.yunforge.base.model;

import java.util.ArrayList;
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
import javax.persistence.Table;
import javax.persistence.Transient;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheCoordinationType;
import org.eclipse.persistence.annotations.CacheType;
import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yunforge.core.model.IEntity;

@Entity
@Table(name = "SYS_DIVISION")
@Cache(type = CacheType.WEAK, size = 64000, expiry = 36000000, alwaysRefresh = true, disableHits = true, coordinationType = CacheCoordinationType.INVALIDATE_CHANGED_OBJECTS)
public class Division implements IEntity<String> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(nullable = false, updatable = false, length = 64)
	private String id;

	@Column(name = "DIV_NAME", length = 50)
	private String divName;

	@Column(name = "DIV_CODE", length = 6)
	private String divCode;

	@Column(name = "MNEMONIC_CODE", length = 20)
	private String mnemonicCode;

	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinFetch(value = JoinFetchType.OUTER)
	@JoinColumn(name = "PID")
	private Division parent;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "parent")
	private List<Division> children = new ArrayList<Division>();

	@Column(name = "DIV_DESC")
	private String divDesc;

	@Column(name = "VER_NUM")
	private Integer verNum;

	@Column(name = "wordnum")
	private String wordNum;
	
	private Integer weight;
	
	@Column(name = "new_order")
	private Integer newOrder;

	@Transient
	private Integer level = 0;

	@Transient
	private boolean isLeaf = Boolean.TRUE;

	@Transient
	private boolean expanded = Boolean.FALSE;

	@Transient
	private String pid;

	public Division() {
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getDivName() {
		return divName;
	}

	public void setDivName(String divName) {
		this.divName = divName;
	}

	public String getDivCode() {
		return divCode;
	}

	public void setDivCode(String divCode) {
		this.divCode = divCode;
	}

	public String getMnemonicCode() {
		return mnemonicCode;
	}

	public void setMnemonicCode(String mnemonicCode) {
		this.mnemonicCode = mnemonicCode;
	}

	public Division getParent() {
		return parent;
	}

	public void setParent(Division parent) {
		this.parent = parent;
	}

	public List<Division> getChildren() {
		return children;
	}

	public void setChildren(List<Division> children) {
		this.children = children;
	}

	public String getDivDesc() {
		return divDesc;
	}

	public void setDivDesc(String divDesc) {
		this.divDesc = divDesc;
	}

	public Integer getVerNum() {
		return verNum;
	}

	public void setVerNum(Integer verNum) {
		this.verNum = verNum;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void addChild(Division child) {
		if (!this.getChildren().contains(child)) {
			this.getChildren().add(child);
			child.setParent(this);
		}
	}

	public void removeChild(Division child) {
		if (this.getChildren().contains(child)) {
			this.getChildren().remove(child);
			child.setParent(null);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Division other = (Division) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (id != other.id) {
			return false;
		}
		return true;
	}

	public String getWordNum() {
		return wordNum;
	}

	public void setWordNum(String wordNum) {
		this.wordNum = wordNum;
	}

	public Integer getNewOrder() {
		return newOrder;
	}

	public void setNewOrder(Integer newOrder) {
		this.newOrder = newOrder;
	}

}