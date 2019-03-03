package com.yunforge.core.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 
 * @author Matt Raible
 * 
 * @web.filter name="compressionFilter"
 */
public class GZIPFilter extends OncePerRequestFilter {
	private final transient Log log = LogFactory.getLog(GZIPFilter.class);

	@Override
	public void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (isGZIPSupported(request)) {
			if (log.isDebugEnabled()) {
				log.debug("GZIP supported, compressing response");
			}
			GZIPResponseWrapper wrappedResponse = new GZIPResponseWrapper(
					response);
			chain.doFilter(request, wrappedResponse);
			wrappedResponse.finishResponse();
			return;
		}
		chain.doFilter(request, response);
	}

	/**
	 * Convenience method to test for GZIP cababilities
	 * 
	 * @param req
	 *            The current user request
	 * @return boolean indicating GZIP support
	 */
	private boolean isGZIPSupported(HttpServletRequest req) {
		String browserEncodings = req.getHeader("accept-encoding");
		boolean supported = ((browserEncodings != null) && (browserEncodings
				.indexOf("gzip") != -1));
		String userAgent = req.getHeader("user-agent");
		if ((userAgent != null) && userAgent.startsWith("httpunit")) {
			log.debug("httpunit detected, disabling filter...");
			return false;
		} else {
			return supported;
		}
	}
}
