package com.yunforge.common.log;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;

import ch.qos.logback.access.spi.IAccessEvent;
import ch.qos.logback.core.db.DBAppenderBase;

public class LogAppender extends DBAppenderBase<IAccessEvent> {
	private String jndiLocation;

	public String getJndiLocation() {
		return jndiLocation;
	}

	public void setJndiLocation(String jndiLocation) {
		this.jndiLocation = jndiLocation;
	}

	@Override
	public void start() {
		super.start();
	}

	@Override
	protected Method getGeneratedKeysMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getInsertSQL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void secondarySubAppend(IAccessEvent arg0, Connection arg1,
			long arg2) throws Throwable {
		// TODO Auto-generated method stub

	}

	@Override
	protected void subAppend(IAccessEvent arg0, Connection arg1,
			PreparedStatement arg2) throws Throwable {
		// TODO Auto-generated method stub

	}

}
