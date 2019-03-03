package com.yunforge.core.web.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.AbstractView;

public class StringView extends AbstractView {
	private static final String DEFAULT_ENCODING = "UTF-8";
	public static final String DEFAULT_JSON_CONTENT_TYPE = "application/html";
	private static final String REQUEST_CONTEXT_ATTRIBUTE = RequestContext.class
			.toString();
	protected static final Log log = LogFactory
			.getLog("com.gpcsoft.core.extra.json.StringView");
	private String encoding;

	public StringView() {
		setRequestContextAttribute(REQUEST_CONTEXT_ATTRIBUTE);
		setContentType("application/html");
		setEncoding("UTF-8");
	}

	@Override
	public void renderMergedOutputModel(Map model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding(this.encoding);
		Assert.isInstanceOf(String.class, model.get("string"),
				"请在ModelAndView里放key为: " + "string" + " 的 String 类型的数据!");
		String string = (String) model.get("string");
		response.getWriter().print(string);
	}

	public String getEncoding() {
		return this.encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
}