package com.yunforge.core.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.yunforge.base.model.User;
import com.yunforge.base.service.UserManager;
import com.yunforge.core.context.FrameBeanFactory;

public final class AuthenticationHelper {

	public static User getCurrentUser() {
		User user = null;
		UserManager userManager = (UserManager) FrameBeanFactory
				.getBean("userManager");
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser != null) {
			String username = currentUser.getPrincipal().toString();
			user = userManager.findUserByUsername(username);
		}
		return user;
	}
}
