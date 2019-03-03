package com.yunforge.core.security;

public class ShaPasswordEncoder extends MessageDigestPasswordEncoder {
	public ShaPasswordEncoder() {
		this(1);
	}

	public ShaPasswordEncoder(int strength) {
		super("SHA-" + strength);
	}

	public static void main(String[] args) throws Exception {
		ShaPasswordEncoder endcode = new ShaPasswordEncoder();
		System.out.print(endcode.encodePassword("111111", null));
	}
}