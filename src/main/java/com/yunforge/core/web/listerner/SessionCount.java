package com.yunforge.core.web.listerner;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCount implements HttpSessionListener {
	private static int numberOfSessionsCount = 0;

	@Override
	public void sessionCreated(HttpSessionEvent evt) {
		HttpSession session = evt.getSession();
		numberOfSessionsCount++;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent evt) {
		numberOfSessionsCount--;
	}

	public static int getNumberOfSessionsCount() {
		return numberOfSessionsCount;
	}
}
