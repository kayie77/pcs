package com.yunforge.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @ClassName: NoticeDetailDto
 * @Description: 通知实体类
 * @author Oliver Wen
 * @date 2015年10月5日 上午12:16:12
 * 
 */
@JsonRootName("noticeDetail")
public class NoticeDetailDto implements Serializable {
	private static final long serialVersionUID = 4109002976756478001L;

	private NoticeDto notice;

	private ResultDto result;

	public NoticeDetailDto() {

	}

	public NoticeDto getNotice() {
		return notice;
	}

	public void setNotice(NoticeDto notice) {
		this.notice = notice;
	}

	public ResultDto getResult() {
		return result;
	}

	public void setResult(ResultDto result) {
		this.result = result;
	}

}
