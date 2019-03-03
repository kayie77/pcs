package com.yunforge.core.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunforge.common.Constants;
import com.yunforge.core.security.UserRealm;

@Controller
public class LoginController extends BaseController {
	final static Log log = LogFactory.getLog(LoginController.class);

	@RequestMapping(value = "/login")
	public String showLoginForm(String username, String password,
			String captcha, boolean rememberMe,HttpServletRequest req,
			HttpServletResponse resp, ModelMap model) {
		// 设置页面不缓存
		resp.setHeader("Pragma", "No-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);

		String randNum = generateRandomNum();
		if (randNum == null || randNum.trim().equals("")) {
			log.info("证书认证数据不完整！");
			// resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} else {
			// 设置认证原文到页面，给页面程序提供参数，用于产生认证请求数据包
			model.addAttribute("original", randNum);
		}
        log.info("login方法："+req.getMethod());
		String exceptionClassName = (String) req
				.getAttribute("shiroLoginFailure");
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			model.addAttribute("error", "用户名不存在!");
			model.addAttribute("type", "username");
		} else if (IncorrectCredentialsException.class.getName().equals(
				exceptionClassName)) {
			model.addAttribute("error", "用户名对应的密码错误!");
			model.addAttribute("type", "password");
		} else if (LockedAccountException.class.getName().equals(
				exceptionClassName)) {
			model.addAttribute("error", "登录用户被锁定，登录失败!");
			model.addAttribute("type", "username");
		} else if (ExpiredCredentialsException.class.getName().equals(
				exceptionClassName)) {
			model.addAttribute("error", "密码已过期!");
			model.addAttribute("type", "password");
		} else if (ExcessiveAttemptsException.class.getName().equals(
				exceptionClassName)) {
			model.addAttribute("error", "超过登录次数!");
			model.addAttribute("type", "general");
		} else if ("jCaptcha.error".equals(exceptionClassName)) {
			model.addAttribute("error", "验证码错误!");
			model.addAttribute("type", "captcha");
		} else if (exceptionClassName != null) {
			model.addAttribute("error", "其他错误：" + exceptionClassName);
			model.addAttribute("type", "general");
		}
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Subject currentUser = SecurityUtils.getSubject();
		//RealmSecurityManager securityManager = (RealmSecurityManager) SecurityUtils.getSecurityManager();
		//UserRealm userRealm = (UserRealm) securityManager.getRealms().iterator().next();
		//PrincipalCollection principals = new SimplePrincipalCollection(u.getUsername(), "userRealm");
		//userRealm.clearCachedAuthorizationInfo(principals);
		try {
			if (currentUser != null) {
				currentUser.logout();
			}
			response.setHeader("Cache-Control", "no-cache, must-revalidate");
			HttpSession session = request.getSession();
			if (session != null) {
				session.removeAttribute(Constants.SESSION_USER_KEY);
				session.invalidate();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "index";
	}

	/**
	 * 产生认证原文
	 */
	private String generateRandomNum() {
		/**************************
		 * 第二步 服务端产生认证原文 *
		 **************************/
		String num = "1234567890abcdefghijklmnopqrstopqrstuvwxyz";
		int size = 6;
		char[] charArray = num.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(charArray[((int) (Math.random() * 10000) % charArray.length)]);
		}
		return sb.toString();
	}
}
