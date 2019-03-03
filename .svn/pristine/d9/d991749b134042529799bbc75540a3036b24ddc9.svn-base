package com.yunforge.base.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yunforge.core.model.IEntity;

@Entity
@Table(name = "SYS_USER_ROLE")
public class UserRole implements IEntity<String> {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserRolePK userRolePK;
	
	public UserRole() {
	}

	public UserRolePK getUserRolePK() {
		return userRolePK;
	}

	public void setUserRolePK(UserRolePK userRolePK) {
		this.userRolePK = userRolePK;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

}