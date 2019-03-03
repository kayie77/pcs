package com.yunforge.core.web.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.view.AbstractView;

public class WordView extends AbstractView {
	private static final String DEFAULT_ENCODING = "GBK";
	public static final String DEFAULT_JSON_CONTENT_TYPE = "application/msword";
	private static final String REQUEST_CONTEXT_ATTRIBUTE = RequestContext.class
			.toString();
	private String encoding;

	public WordView() {
		setRequestContextAttribute(REQUEST_CONTEXT_ATTRIBUTE);
		setContentType("application/msword");
		setEncoding("GBK");
	}

	@Override
	public void renderMergedOutputModel(Map model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String title = request.getParameter("filename");
		String content = request.getParameter("wordContent");
		Assert.isTrue((title != null) && (!title.equals("")), "Word文件名不能为空!");
		Assert.isTrue((content != null) && (!content.equals("")),
				"Word文件名内容不能为空!");

		response.setContentType(getContentType());
		response.setCharacterEncoding(getEncoding());
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(title.getBytes(getEncoding()), "ISO8859-1")
				+ ".doc");
		response.getWriter().write("<html>" + content + "</html>");
	}

	public String getEncoding() {
		return this.encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
}