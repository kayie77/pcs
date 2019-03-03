package com.yunforge.core.captcha;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;

public class JCaptcha {
	public static final ManageableImageCaptchaServiceImpl captchaService = new ManageableImageCaptchaServiceImpl(
			new FastHashMapCaptchaStore(), new EaipCaptchaEngine(), 180,
			100000, 75000);
	final static Logger log = LoggerFactory
			.getLogger(ManageableImageCaptchaServiceImpl.class);

	public static boolean validateResponse(HttpServletRequest request,
			String userCaptchaResponse) {
		if (request.getSession(false) == null)
			return false;
		boolean validated = false;
		try {
			String id = request.getSession().getId();
			validated = captchaService.validateResponseForID(id,
					userCaptchaResponse).booleanValue();
			if (!validated) {
				log.info("验证码错误!");
			}
		} catch (CaptchaServiceException e) {
			log.error("验证码错误!");
			e.printStackTrace();
		}
		return validated;
	}

	public static boolean hasCaptcha(HttpServletRequest request,
			String userCaptchaResponse) {
		if (request.getSession(false) == null)
			return false;
		boolean validated = false;
		try {
			String id = request.getSession().getId();
			validated = captchaService.hasCapcha(id, userCaptchaResponse);
		} catch (CaptchaServiceException e) {
			e.printStackTrace();
		}
		return validated;
	}
}