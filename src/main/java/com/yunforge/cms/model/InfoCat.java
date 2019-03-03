package com.yunforge.cms.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yunforge.core.model.IEntity;

@Entity
@Table(name = "CMS_INFO_CAT")
public class InfoCat implements IEntity<String> {
	private static final long serialVersionUID = -2530155692153089462L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(nullable = false, updatable = false, length = 36)
	private String id;

	@Column(name = "CAT_NAME", length = 50)
	private String catName;

	private Integer weight;

	@Column(name = "CAT_DESC")
	private String catDesc;

	@JsonIgnore
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "PID", nullable = true)
	private InfoCat parent;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	private Set<InfoCat> children = new HashSet<InfoCat>(0);

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "infoCat")
	private Set<Info> infos = new HashSet<Info>(0);

	public InfoCat() {
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;

	}

	public String getCatName() {
		return this.catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getCatDesc() {
		return this.catDesc;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

	public InfoCat getParent() {
		return this.parent;
	}

	public void setParent(InfoCat parent) {
		this.parent = parent;
	}

	public String getParentName() {
		if (this.getParent() != null) {
			return this.getParent().getCatName();
		} else {
			return null;
		}
	}

	public Set<InfoCat> getChildren() {
		return this.children;
	}

	public void setChildren(Set<InfoCat> children) {
		this.children = children;
	}

	public Set<Info> getInfos() {
		return infos;
	}

	public void setInfos(Set<Info> infos) {
		this.infos = infos;
	}

	@Override
	public String toString() {
		return "InfoCat [id=" + this.id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
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
		InfoCat other = (InfoCat) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}

}