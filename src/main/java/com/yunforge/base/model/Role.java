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
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yunforge.core.model.IEntity;

@Entity
@Table(name = "SYS_ROLE")
public class Role implements IEntity<String> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(nullable = false, updatable = false, length = 64)
	private String id;

	@Column(name = "ROLE_NAME", length = 50)
	private String roleName;

	@Column(name = "ROLE_NAME_CN", length = 50)
	private String roleNameCN;

	@Column(name = "ROLE_DESC")
	private String roleDesc;

	private boolean enabled = Boolean.TRUE;

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "SYS_ORG_ROLE", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "ORG_ID") })
	private List<Org> orgs = new ArrayList<Org>();

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "SYS_GROUP_ROLE", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "GROUP_ID") })
	private List<Group> groups = new ArrayList<Group>();

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "SYS_USER_ROLE", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ID") })
	private List<User> users = new ArrayList<User>();

	@JsonIgnore
	@ManyToMany(cascade = CascadeType.MERGE, mappedBy = "roles", fetch = FetchType.LAZY)
	private List<Resource> resources = new ArrayList<Resource>();

	@Transient
	private String hasRole;
	
	@Transient
	private boolean isCheck = Boolean.FALSE;
	
	public boolean getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

	public Role() {
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleNameCN() {
		return roleNameCN;
	}

	public void setRoleNameCN(String roleNameCN) {
		this.roleNameCN = roleNameCN;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public List<Org> getOrgs() {
		return orgs;
	}

	public void setOrgs(List<Org> orgs) {
		this.orgs = orgs;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Resource> getResources() {
		return resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public void addUsers(List<User> users) {
		for (User user : users) {
			if (!this.getUsers().contains(user)) {
				this.getUsers().add(user);
				user.getRoles().add(this);
			}
		}
	}

	public void removeUsers(List<User> users) {
		for (User user : users) {
			if (!this.getUsers().contains(user)) {
				this.getUsers().remove(user);
				user.getRoles().remove(this);
			}
		}
	}

	public void addGroups(List<Group> groups) {
		for (Group group : groups) {
			if (!this.getGroups().contains(group)) {
				this.getGroups().add(group);
				group.getRoles().add(this);
			}
		}
	}

	public void removeGroups(List<Group> groups) {
		for (Group group : groups) {
			if (!this.getGroups().contains(group)) {
				this.getGroups().remove(group);
				group.getRoles().remove(this);
			}
		}
	}

	public void addOrgs(List<Org> orgs) {
		for (Org org : orgs) {
			if (!this.getOrgs().contains(org)) {
				this.getOrgs().add(org);
				org.getRoles().add(this);
			}
		}
	}

	public void removeOrgs(List<Org> orgs) {
		for (Org org : orgs) {
			if (!this.getOrgs().contains(org)) {
				this.getOrgs().remove(org);
				org.getRoles().remove(this);
			}
		}
	}

	public boolean isEnabled() {
		return enabled;
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
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (id != other.id) {
			return false;
		}
		return true;
	}

	public String getHasRole() {
		return hasRole;
	}

	public void setHasRole(String hasRole) {
		this.hasRole = hasRole;
	}

}