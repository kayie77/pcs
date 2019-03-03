package com.yunforge.core.captcha;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.servlet.OncePerRequestFilter;

@WebFilter(filterName="jCaptchaFilter",urlPatterns={"/jcaptcha.jpg"},asyncSupported = true)  
public class JCaptchaFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(ServletRequest request,
			ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;

		HttpServletResponse rep = (HttpServletResponse) response;

		rep.setDateHeader("Expires", 0L);
		rep.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		rep.addHeader("Cache-Control", "post-check=0, pre-check=0");
		rep.setHeader("Pragma", "no-cache");
		rep.setContentType("image/jpeg");
		String id = req.getRequestedSessionId();
		BufferedImage bi = JCaptcha.captchaService.getImageChallengeForID(id);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
	}

}
