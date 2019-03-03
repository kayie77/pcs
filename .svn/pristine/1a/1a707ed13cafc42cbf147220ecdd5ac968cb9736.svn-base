package com.yunforge.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @ClassName: LoginUserDto
 * @Description:
 * @author Oliver Wen
 * @date 2015年10月6日 上午11:35:05
 * 
 */
@JsonRootName("loginUser")
public class LoginUserDto implements Serializable {

	private static final long serialVersionUID = 666631801980085441L;

	private ResultDto result;

	@JsonInclude(Include.NON_NULL)
	private UserDto user;

	@JsonInclude(Include.NON_NULL)
	private NoticeDto notice;

	public ResultDto getResult() {
		return result;
	}

	public void setResult(ResultDto resultDto) {
		this.result = resultDto;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public NoticeDto getNotice() {
		return notice;
	}

	public void setNotice(NoticeDto noticeDto) {
		this.notice = noticeDto;
	}

}