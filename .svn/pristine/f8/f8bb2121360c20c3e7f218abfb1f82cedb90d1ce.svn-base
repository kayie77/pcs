package com.yunforge.core.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yunforge.base.model.User;
import com.yunforge.base.service.UserManager;
import com.yunforge.common.bean.ThreadVariable;

/**
 * 上下文信息拦截器
 * 
 * 包括登录信息、权限信息
 * 
 * @author Oliver Wen
 * 
 */
public class ContextInterceptor extends HandlerInterceptorAdapter {
	private static final Logger log = Logger
			.getLogger(ContextInterceptor.class);

	@Autowired
	private UserManager userManager;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		log.debug("拦截器：preHandle(){}");
		User user = null;
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			Session session = currentUser.getSession(true);
			if (session.getAttribute("user") == null) {
				user = userManager.findUserByUsername(currentUser
						.getPrincipal().toString());
				currentUser.getSession(true).setAttribute("user", user);
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		ThreadVariable.removeUser();
	}

}