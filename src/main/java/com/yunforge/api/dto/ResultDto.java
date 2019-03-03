package com.yunforge.api.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;

/** 
* @ClassName: ResultDto 
* @Description: 数据操作结果实体类
* @author Oliver Wen
* @date 2015年10月1日 下午12:00:22 
*  
*/
@JsonRootName("result")
public class ResultDto implements Serializable {

	private static final long serialVersionUID = 3022737446335700731L;

    private int errorCode;

 
    private String errorMessage;

    public boolean OK() {
	return errorCode == 1;
    }

    public int getErrorCode() {
	return errorCode;
    }

    public void setErrorCode(int errorCode) {
	this.errorCode = errorCode;
    }

    public String getErrorMessage() {
	return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
    }
}
