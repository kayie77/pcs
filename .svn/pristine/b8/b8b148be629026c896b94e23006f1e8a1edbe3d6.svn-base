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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yunforge.core.model.IEntity;

@Entity
@Table(name = "SYS_RES")
public class Resource implements IEntity<String> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(nullable = false, updatable = false, length = 64)
	private String id;

	@Column(name = "RES_NAME", length = 50)
	private String resName;

	@Column(name = "RES_CODE", length = 50)
	private String resCode;

	@Column(name = "RES_STRING", length = 100)
	private String resString;

	@Column(name = "RES_TYPE", length = 50)
	private String resType;

	@Column(name = "ICON_CLS", length = 50)
	private String iconCls;

	@JsonIgnore
	@ManyToOne(optional = true,fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
	private Resource parent;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "parent")
	private List<Resource> children = new ArrayList<Resource>();

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "SYS_RES_ROLE", joinColumns = { @JoinColumn(name = "RES_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private List<Role> roles = new ArrayList<Role>();

	@Column(name = "ENABLED")
	private boolean enabled;

	private Long weight;

	@Column(name = "PERMISSION", length = 100)
	private String permission;

	@Column(name = "RES_DESC")
	private String resDesc;

	public Resource() {
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getResString() {
		return resString;
	}

	public void setResString(String resString) {
		this.resString = resString;
	}

	public String getResType() {
		return resType;
	}

	public void setResType(String resType) {
		this.resType = resType;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Resource getParent() {
		return parent;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}

	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void addRoles(List<Role> roles) {
		for (Role role : roles) {
			if (!this.getRoles().contains(role)) {
				this.getRoles().add(role);
				role.getResources().add(this);
			}
		}
	}

	public void removeRoles(List<Role> roles) {
		for (Role role : roles) {
			if (!this.getRoles().contains(role)) {
				this.getRoles().remove(role);
				role.getResources().remove(this);
			}
		}
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public String getResDesc() {
		return resDesc;
	}

	public void setResDesc(String resDesc) {
		this.resDesc = resDesc;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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
		Resource other = (Resource) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (id != other.id) {
			return false;
		}
		return true;
	}

}