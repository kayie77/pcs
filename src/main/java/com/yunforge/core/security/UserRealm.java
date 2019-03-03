package com.yunforge.core.security;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunforge.base.model.User;
import com.yunforge.base.service.ResourceManager;
import com.yunforge.base.service.RoleManager;
import com.yunforge.base.service.UserManager;
import com.yunforge.common.Constants;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserManager userManager;

	@Autowired
	private RoleManager roleManager;

	@Autowired
	private ResourceManager resourceManager;

	/** 认证回调函数,登录时调用. */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		// 通过表单接收的用户名
		String username = token.getUsername();
		if (username != null && !"".equals(username)) {
			User user = userManager.findUserByUsername(token.getUsername());
			if (user != null) {
				if (!user.isEnabled()) {
					throw new DisabledAccountException();// 帐号未启用
				}

				if (user.isAccountLocked()) {
					throw new LockedAccountException();// 帐号已锁定
				}

				if (user.isCredentialsExpired()) {
					throw new ExpiredCredentialsException();// 密码已过期
				}

				return new SimpleAuthenticationInfo(user.getUsername(),
						user.getPassword(), getName());

			} else {
				throw new UnknownAccountException();// 没找到帐号
			}
		}

		return null;
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String loginName = (String) principals.fromRealm(getName()).iterator()
				.next();
		User user = userManager.findUserByUsername(loginName);
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			if (!user.isAdmin()) {
				Set<String> roles = new HashSet<String>(0);
				roles.addAll(userManager.getUserAllRoles(loginName));
				info.setRoles(roles);
				Set<String> permissions = new HashSet<String>(0);
				permissions.addAll(userManager
						.findUserPermissionsByUsername(loginName));
				info.setStringPermissions(permissions);
			} else {
				info.addRole(Constants.ROLE_ADMIN_KEY);
				Set<String> permissions = new HashSet<String>(0);
				permissions.addAll(resourceManager.getAllPermissions());
				info.setStringPermissions(permissions);
			}
			return info;
		} else {
			return null;
		}

	}

	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	/**
	 * 
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		clearAllCachedAuthenticationInfo();
		clearAllCachedAuthorizationInfo();
	}

	/**
	 * 
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

}
