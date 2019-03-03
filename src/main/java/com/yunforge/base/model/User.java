package com.yunforge.base.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yunforge.core.model.IEntity;

@Entity
@Table(name = "SYS_USER")
public class User implements IEntity<String> {
	private static final long serialVersionUID = 1L;
	public static final String AVATAR_FILE_PATH = "avatar";

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(nullable = false, updatable = false, length = 64)
	private String id;

	@Column(name = "USERNAME", length = 20)
	private String username;

	@Column(name = "PASSWORD", length = 100)
	private String password;

	@Column(name = "SALT", length = 50)
	private String salt;

	@Column(name = "avatar")
	private String avatar;

	@Transient
	private String fullName;

	@Column(name = "QQ_ACCOUNT", length = 50)
	private String qq;

	@Column(name = "CA_SN", length = 20)
	private String caSn;

	private boolean admin;

	@Column(name = "REGISTER_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date registerTime;

	@Column(name = "REGISTER_IP", length = 20)
	private String registerIp;

	@Column(name = "LAST_LOGIN_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginTime;

	@Column(name = "LAST_LOGIN_IP", length = 20)
	private String lastLoginIp;

	@Column(name = "LOGIN_COUNT")
	private Integer loginCount;

	@Column(name = "ACCOUNT_LOCKED")
	private boolean accountLocked = Boolean.FALSE;

	@Column(name = "CREDENTIALS_EXPIRED")
	private boolean credentialsExpired = Boolean.FALSE;

	@Column(name = "ENABLED")
	private boolean enabled = Boolean.TRUE;

	@Transient
	private String divCode;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
	private List<Preference> preferences = new ArrayList<Preference>();

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH,
			CascadeType.MERGE }, mappedBy = "users")
	private List<Group> groups = new ArrayList<Group>();

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH,
			CascadeType.MERGE }, mappedBy = "users")
	private List<Role> roles = new ArrayList<Role>();

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
	private Person person;

	public User() {
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getFullName() {
		String name = null;
		if (this.person != null) {
			name = person.getPersName();
		}
		return name;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getCaSn() {
		return caSn;
	}

	public void setCaSn(String caSn) {
		this.caSn = caSn;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public boolean isAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Preference> getPreferences() {
		return preferences;
	}

	public void setPreferences(List<Preference> preferences) {
		this.preferences = preferences;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	public void removeGroup(Group group) {
		if (this.groups.contains(group)) {
			this.groups.remove(group);
		}
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void removeRole(Role role) {
		if (this.roles.contains(role)) {
			this.roles.remove(role);
		}
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getDivCode() {
		String code = null;
		if (this.person != null) {
			Org org = this.person.getOrg();
			if (org != null) {
				code = org.getDivCode();
			}
		}
		return code;
	}

	public void setDivCode(String divCode) {
		this.divCode = divCode;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (id != other.id)
			return false;
		return true;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}