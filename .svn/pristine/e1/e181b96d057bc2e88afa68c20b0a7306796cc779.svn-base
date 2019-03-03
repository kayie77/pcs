package com.yunforge.api.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @ClassName: UserInformation
 * @Description: 个人信息专用实体类
 * @author Oliver Wen
 * @date 2015年10月6日 上午11:36:11
 * 
 */
@JsonRootName("userInfo")
public class UserInfoDto implements Serializable {

	private static final long serialVersionUID = -8520263284605378955L;

	private UserDto user;

	private int pageSize;

	private List<ActiveDto> activeList;

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<ActiveDto> getActiveList() {
		return activeList;
	}

	public void setActiveList(List<ActiveDto> activeList) {
		this.activeList = activeList;
	}
}