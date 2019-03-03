package com.yunforge.common.exception;

public class SystemException extends BaseException {
	private static final long serialVersionUID = -3342022583278439648L;

	public SystemException() {
		super();
	}

	public SystemException(String errorKey) {
		super(errorKey);
	}

	public SystemException(Exception nestedException, String errorKey) {
		super(nestedException, errorKey);
	}

	public SystemException(Exception nestedException) {
		super(nestedException);
	}

}
