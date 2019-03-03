package com.yunforge.common.exception;

public class FatalException extends BaseException {
	private static final long serialVersionUID = -1410578487969099271L;

	public FatalException() {
		super();
	}

	public FatalException(Exception nestedException, String errorKey) {
		super(nestedException, errorKey);
	}

	public FatalException(String errorKey) {
		super(errorKey);
	}

	public FatalException(Exception nestedException) {
		super(nestedException);
	}
}
