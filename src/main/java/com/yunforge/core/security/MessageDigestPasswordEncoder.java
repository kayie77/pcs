package com.yunforge.core.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class MessageDigestPasswordEncoder extends BaseDigestPasswordEncoder {
	private final String algorithm;

	public MessageDigestPasswordEncoder(String algorithm) {
		this(algorithm, false);
	}

	public MessageDigestPasswordEncoder(String algorithm,
			boolean encodeHashAsBase64) throws IllegalArgumentException {
		this.algorithm = algorithm;
		setEncodeHashAsBase64(encodeHashAsBase64);

		getMessageDigest();
	}

	@Override
	public String encodePassword(String rawPass, Object salt) {
		String saltedPass = mergePasswordAndSalt(rawPass, salt, false);

		MessageDigest messageDigest = getMessageDigest();
		byte[] digest;
		try {
			digest = messageDigest.digest(saltedPass.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException("UTF-8 not supported!");
		}

		if (getEncodeHashAsBase64()) {
			return new String(Base64.encodeBase64(digest));
		}
		return new String(Hex.encodeHex(digest));
	}

	protected final MessageDigest getMessageDigest()
			throws IllegalArgumentException {
		try {
			return MessageDigest.getInstance(this.algorithm);
		} catch (NoSuchAlgorithmException e) {
		}
		throw new IllegalArgumentException("No such algorithm ["
				+ this.algorithm + "]");
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		String pass1 = "" + encPass;
		String pass2 = encodePassword(rawPass, salt);

		return pass1.equals(pass2);
	}

	public String getAlgorithm() {
		return this.algorithm;
	}
}
