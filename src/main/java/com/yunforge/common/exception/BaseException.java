package com.yunforge.common.exception;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Oliver Wen
 */
public class BaseException extends Exception {
	private static final long serialVersionUID = -2268010989574881830L;
	private static Log log = LogFactory.getLog(BaseException.class);
	private String errorKey;
	private String userMessage;
	private String systemMessage;
	private Exception nestedException;
	private static Configuration msgs;

	public BaseException() {
		super();
	}

	public BaseException(String errorKey) {
		this.errorKey = errorKey;
		this.userMessage = msgs.getString(this.errorKey);
		log.error("An exception is created. Message: " + userMessage);
	}

	public BaseException(Exception nestedException, String errorKey) {
		this.nestedException = nestedException;

		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(bout);
		this.nestedException.printStackTrace(out);
		this.systemMessage = bout.toString();
		try {
			bout.close();
		} catch (IOException e) {
		}
		this.errorKey = errorKey;
		this.userMessage = msgs.getString(errorKey);
		log.error("An exception is created. Message: " + userMessage);
	}

	public BaseException(Exception nestedException) {
		this.nestedException = nestedException;

		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(bout);
		this.nestedException.printStackTrace(out);
		this.systemMessage = bout.toString();
		log.error("An exception is created. System Message: "
				+ this.systemMessage);
		try {
			bout.close();
		} catch (IOException e) {
		}
	}

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getSystemMessage() {
		return systemMessage;
	}

	public void setSystemMessage(String systemMessage) {
		this.systemMessage = systemMessage;
	}

}
