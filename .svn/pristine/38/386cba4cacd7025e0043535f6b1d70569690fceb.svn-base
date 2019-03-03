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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheCoordinationType;
import org.eclipse.persistence.annotations.CacheType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yunforge.core.model.IEntity;

@Entity
@Table(name = "SYS_ORG")
@Cache(type = CacheType.WEAK, size = 64000, expiry = 36000000, alwaysRefresh = true, disableHits = true, coordinationType = CacheCoordinationType.INVALIDATE_CHANGED_OBJECTS)
public class Org implements IEntity<String> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(nullable = false, updatable = false, length = 64)
	private String id;

	@Column(name = "ORG_CODE", length = 20)
	private String orgCode;

	@Column(name = "ORG_NAME", length = 100)
	private String orgName;

	@Column(name = "DIV_CODE", length = 11)
	private String divCode;

	@Column(name = "MNEMONIC_CODE", length = 20)
	private String mnemonicCode;

	@Column(name = "TEL_NUM", length = 20)
	private String telNum;

	@Column(name = "FAX_NUM", length = 20)
	private String faxNum;

	@Column(name = "EMAIL", length = 20)
	private String email;

	@Column(name = "ZIP", length = 6)
	private String zip;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "ORG_DESC")
	private String orgDesc;

	private Integer weight;

	@Column(name = "WEBSITE", length = 100)
	private String website;

	@ManyToOne(optional = true,fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
	private Org parent;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "parent")
	private List<Org> children = new ArrayList<Org>();

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "org")
	private List<Person> persons = new ArrayList<Person>();
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.MERGE, mappedBy = "orgs", fetch = FetchType.LAZY)
	private List<Role> roles = new ArrayList<Role>();

	private boolean enabled = Boolean.TRUE;

	@Transient
	private Integer level = 0;

	@Transient
	private boolean isLeaf = Boolean.TRUE;

	@Transient
	private boolean expanded = Boolean.FALSE;

	@Transient
	private String pid;

	public Org() {
	}

	public Org(String orgCode, String orgName, String divCode,
			String mnemonicCode, String telNum, String faxNum, String email,
			String zip, String address, String orgDesc, Integer weight,
			String website, boolean enabled) {
		super();
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.divCode = divCode;
		this.mnemonicCode = mnemonicCode;
		this.telNum = telNum;
		this.faxNum = faxNum;
		this.email = email;
		this.zip = zip;
		this.address = address;
		this.orgDesc = orgDesc;
		this.weight = weight;
		this.website = website;
		this.enabled = enabled;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
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

	public String getTelNum() {
		return telNum;
	}

	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}

	public String getFaxNum() {
		return faxNum;
	}

	public void setFaxNum(String faxNum) {
		this.faxNum = faxNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Org getParent() {
		return parent;
	}

	public void setParent(Org parent) {
		this.parent = parent;
	}

	public List<Org> getChildren() {
		return children;
	}

	public void setChildren(List<Org> children) {
		this.children = children;
	}

	public void addChild(Org child) {
		if (!this.getChildren().contains(child)) {
			this.getChildren().add(child);
			child.setParent(this);
		}
	}

	public void removeChild(Org child) {
		if (this.getChildren().contains(child)) {
			this.getChildren().remove(child);
			child.setParent(null);
		}
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public void addPerson(Person person) {
		if (!this.getPersons().contains(person)) {
			this.getPersons().add(person);
			person.setOrg(this);
		}
	}

	public void removePerson(Person person) {
		if (this.getPersons().contains(person)) {
			this.getPersons().remove(person);
		}
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		if (!this.getRoles().contains(role)) {
			this.getRoles().add(role);
			role.getOrgs().add(this);
		}
	}

	public void removeRole(Role role) {
		if (this.getRoles().contains(role)) {
			this.getRoles().remove(role);
			role.getOrgs().remove(this);
		}
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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
		Org other = (Org) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (id != other.id) {
			return false;
		}
		return true;
	}

}