package com.yunforge.core.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.yunforge.common.util.DateUtil;
import com.yunforge.core.security.ShiroUser;

public abstract class BaseController {
	final static Log log = LogFactory.getLog(BaseController.class);

	protected String getIPAddress(HttpServletRequest request) {
		return request.getRemoteAddr();
	}

	protected String getCookie(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		Cookie cookie = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}

		}
		return null;
	}

	protected String getCurrentUserName() {
		Subject currentUser = SecurityUtils.getSubject();
		return currentUser.getPrincipal().toString();
	}

	/**
	 * 获取一个Session属性对象
	 * 
	 * @param request
	 * @param sessionName
	 * @return
	 */
	protected Object getSessionAttribute(HttpServletRequest request,
			String sessionKey) {
		Object objSessionAttribute = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			objSessionAttribute = session.getAttribute(sessionKey);
		}
		return objSessionAttribute;
	}

	protected void setSessionAttribute(HttpServletRequest request,
			String sessionKey, Object objSessionAttribute) {
		HttpSession session = request.getSession();
		if (session != null)
			session.setAttribute(sessionKey, objSessionAttribute);
	}

	protected void removeSessionAttribute(HttpServletRequest request,
			String sessionKey) {
		HttpSession session = request.getSession();
		if (session != null)
			session.removeAttribute(sessionKey);
	}

	protected final boolean hasRole(String role) {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser != null) {
			return currentUser.hasRole(role);
		}
		return false;
	}

	protected boolean hasRoles(String[] roles) {
		Subject currentUser = SecurityUtils.getSubject();
		List<String> roleList = new ArrayList<String>();
		for (String r : roles) {
			roleList.add(r);
		}
		if (currentUser != null) {
			return currentUser.hasAllRoles(roleList);
		}
		return false;
	}

	protected boolean hasPermissions(String... permissions) {
		Subject currentUser = SecurityUtils.getSubject();
		return currentUser.isPermittedAll(permissions);
	}

	protected Integer getCurrentYearNum(HttpServletRequest request) {
		Object yearNum = request.getSession().getAttribute("yearNum");
		if (yearNum != null && yearNum.toString().trim().length() > 0) {
			return Integer.valueOf(yearNum.toString());
		} else {
			return DateUtil.getYear(new Date());
		}
	}

	protected Object getVariable(HttpServletRequest request, String name) {
		Object obj = request.getParameter(name);
		if (obj == null) {
			obj = request.getAttribute(name);
			if (obj == null) {
				obj = request.getSession().getAttribute(name);
				if (obj == null) {
					obj = getCookie(request, name);
					if (obj == null) {
						obj = request.getServletContext().getAttribute(name);
					}
				}
			}
		}
		return obj;
	}


	protected ShiroUser getCurrentUser() {
		ShiroUser currentUser = (ShiroUser) SecurityUtils.getSubject()
				.getPrincipal();
		return currentUser;
	}
}
