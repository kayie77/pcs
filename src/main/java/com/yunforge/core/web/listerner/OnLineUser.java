package com.yunforge.core.web.listerner;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class OnLineUser implements HttpSessionBindingListener {
	private String username;
	private String fullName;

	public OnLineUser(String username, String fullName) {
		this.username = username;
		this.fullName = fullName;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void valueBound(HttpSessionBindingEvent evt) {
		Map<String, String> userMap = null;
		HttpSession session = evt.getSession();
		ServletContext context = session.getServletContext();
		userMap = (HashMap<String, String>) context.getAttribute("userMap");
		if (userMap == null) {
			userMap = new HashMap<String, String>();
		}
		userMap.put(username, fullName);
		context.setAttribute("userMap", userMap);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void valueUnbound(HttpSessionBindingEvent evt) {
		HttpSession session = evt.getSession();
		ServletContext context = session.getServletContext();
		Map<String, String> userMap = (HashMap<String, String>) context
				.getAttribute("userMap");
		userMap.remove(this.username);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}