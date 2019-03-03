package com.yunforge.base.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserRolePK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "role_id")
	private String roleId;
	
	@Column(name = "user_id")
	private String userId;
	

	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRolePK other = (UserRolePK) obj;
		
		if(userId != null 
				&& roleId != null
				&& other.getUserId() != null 
				&& other.getRoleId() != null
				&& userId.equals(other.getUserId())
				&& roleId.equals(other.getRoleId())) {
			return true;
		}
		
		return false;
	}
	
}