/**   
 * @Title: RestAuthenticationFilter.java 
 * @Package com.yunforge.core.security 
 * @Description: TODO 
 * @author Oliver Wen  
 * @date 2015年10月14日 上午1:29:45 
 * @version V1.0   
 */
package com.yunforge.core.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: MobileAuthenticationFilter
 * @Description: 移动终端登录过滤器
 * @author Oliver Wen
 * @date 2015年10月14日 上午1:29:45
 * 
 */
public class MobileAuthenticationFilter extends AuthenticationFilter {
	public static final String TOKEN = "token";
	final static Logger log = LoggerFactory
			.getLogger(MobileAuthenticationFilter.class);

	/**
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             onAccessDenied
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		log.info("安卓用户进入校验！" + getLoginUrl());

		HttpServletRequest req = (HttpServletRequest) request;

		String token = req.getParameter(TOKEN);
		if (isAccess(token)) {
			return onAccessSuccess(req, (HttpServletResponse) response);
		}

		return onAccessFail(req, (HttpServletResponse) response);
	}

	/**
	 * 判断token的合法性
	 * 
	 * @param token
	 * @return
	 */
	public boolean isAccess(String token) {
		if (StringUtils.isNotBlank(token)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}

	}

	/**
	 * 认证成功进行的操作处理
	 * 
	 * @param request
	 * @param response
	 * @return true 继续后续处理，false 不需要后续处理
	 */
	public boolean onAccessSuccess(HttpServletRequest request,
			HttpServletResponse response) {
		return Boolean.FALSE;
	}

	/**
	 * 认证失败时处理结果
	 * 
	 * @param request
	 * @param response
	 * @return true 继续后续处理，false 不需要后续处理
	 */
	public boolean onAccessFail(HttpServletRequest request,
			HttpServletResponse response) {
		return Boolean.FALSE;
	}

}
