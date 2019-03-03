package com.yunforge.core.security;

import org.springframework.dao.DataAccessException;

public abstract interface PasswordEncoder {
	public abstract String encodePassword(String rawPass, Object salt)
			throws DataAccessException;

	public abstract boolean isPasswordValid(String encPass, String rawPass,
			Object salt) throws DataAccessException;
}