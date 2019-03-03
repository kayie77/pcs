package com.yunforge.base.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.yunforge.core.model.IEntity;

@Entity
@Table(name = "SYS_PREF")
public class Preference implements IEntity<String> {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(nullable = false, updatable = false, length = 64)
	private String id;

	@Column(name = "PREF_NAME", length = 50)
	private String prefName;

	@Column(name = "PREF_VAL", length = 50)
	private String prefVal;

	@Column(name = "PREF_DESC")
	private String prefDesc;

	@ManyToOne(optional = false)
	@JoinColumn(name = "USER_ID")
	private User user;

	public Preference() {
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getPrefName() {
		return prefName;
	}

	public void setPrefName(String prefName) {
		this.prefName = prefName;
	}

	public String getPrefVal() {
		return prefVal;
	}

	public void setPrefVal(String prefValue) {
		this.prefVal = prefValue;
	}

	public String getPrefDesc() {
		return prefDesc;
	}

	public void setPrefDesc(String prefDesc) {
		this.prefDesc = prefDesc;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		Preference other = (Preference) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (id != other.id) {
			return false;
		}
		return true;
	}

}