package com.yunforge.core.security;

import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunforge.base.model.User;
import com.yunforge.base.service.UserManager;

public class AgrimsFormAuthenticationFilter extends FormAuthenticationFilter {

	@Autowired
	private UserManager userManager;
	
	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		if (request.getAttribute(getFailureKeyAttribute()) != null) {
			return true;
		}
		return super.onAccessDenied(request, response, mappedValue);
	}

	@Override
	protected boolean onLoginSuccess(AuthenticationToken token,
			Subject subject, ServletRequest request, ServletResponse response)
			throws Exception {
		String username = (String) subject.getPrincipal();
		User user = userManager.findUserByUsername(username);

		user.setLastLoginIp(request.getRemoteHost());
		// 设定用户登陆时间
		user.setLastLoginTime(new Date());
		user=userManager.saveUser(user);
		return super.onLoginSuccess(token, subject, request, response);
	}

}
