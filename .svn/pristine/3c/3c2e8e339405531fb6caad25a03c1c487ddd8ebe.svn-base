package com.yunforge.core.security;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {
	final static Logger log = LoggerFactory
			.getLogger(CustomCredentialsMatcher.class);
	private String hashAlgorithmName = "planText";
	private Cache<String, AtomicInteger> passwordRetryCache;

	public CustomCredentialsMatcher(CacheManager cacheManager) {
		this.passwordRetryCache = cacheManager
				.getCache("shiro-passwordRetryCache");
	}

	public void setPasswordRetryCache(
			Cache<String, AtomicInteger> passwordRetryCache) {
		this.passwordRetryCache = passwordRetryCache;
	}

	public String getHashAlgorithmName() {
		return hashAlgorithmName;
	}

	public void setHashAlgorithmName(String hashAlgorithmName) {
		this.hashAlgorithmName = hashAlgorithmName;
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken authcToken,
			AuthenticationInfo info) {
		Object tokenCredentials;

		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String username = (String) token.getPrincipal();

		// retry count + 1
		AtomicInteger retryCount = passwordRetryCache.get(username);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		if (retryCount.incrementAndGet() > 5) {
			// if retry count > 5 throw
			throw new ExcessiveAttemptsException();
		}

		log.info("算法：" + hashAlgorithmName);
		if (hashAlgorithmName.equalsIgnoreCase("MD5")) {
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			tokenCredentials = encoder.encodePassword(
					String.valueOf(token.getPassword()), null);
			log.info("输入密码：" + String.valueOf(token.getPassword()) + ":"
					+ tokenCredentials.toString());
		} else if (hashAlgorithmName.equalsIgnoreCase("SHA-1")) {
			ShaPasswordEncoder encoder = new ShaPasswordEncoder();
			tokenCredentials = encoder.encodePassword(
					String.valueOf(token.getPassword()), null);
		} else {
			PlaintextPasswordEncoder encoder = new PlaintextPasswordEncoder();
			tokenCredentials = encoder.encodePassword(
					String.valueOf(token.getPassword()), null);
		}
		Object accountCredentials = getCredentials(info);
		log.info("存储密码：" + accountCredentials.toString());
		// 将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false
		boolean matches = equals(tokenCredentials, accountCredentials);
		if (matches) {
			passwordRetryCache.remove(username);
		}

		return matches;
	}
}
