package com.yunforge.base.web.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunforge.base.service.UserManager;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.web.controller.BaseController;

@Controller
public class ProxyController extends BaseController {
	final static Log log = LogFactory.getLog(ProxyController.class);

	@Autowired
	private UserManager userManager;

	@MethodRemark(remark = "访问新浪数据")
	@ResponseBody
	@RequestMapping(value = "/ajaxProxy/proxy", method = RequestMethod.GET)
	public void proxyAjaxCall(
			@RequestParam(required = true, value = "url") String url,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		url = URLDecoder.decode(url, "utf-8");
		url = url.replace("^", "&");
		OutputStreamWriter writer = new OutputStreamWriter(
				response.getOutputStream(), response.getCharacterEncoding());
		HttpClient client = new HttpClient();
		try {
			HttpMethod method = null;
			if (request.getMethod().equals("GET")) {
				method = new GetMethod(url);
			} else if (request.getMethod().equals("POST")) {
				method = new PostMethod(url);
				Enumeration<String> paramNames = request.getParameterNames();
				while (paramNames.hasMoreElements()) {
					String paramName = paramNames.nextElement();
					((PostMethod) method).setParameter(paramName,
							request.getParameter(paramName));
				}

			} else {
				throw new NotImplementedException("本代理只支持GET and POST方法.");
			}
			method.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			client.executeMethod(method);
			Header[] headers = method.getResponseHeaders();
			for (Header header : headers) {
				if ("Content-Type".equalsIgnoreCase(header.getName())) {
					response.setContentType(header.getValue());
				}
			}
			String responseBody = method.getResponseBodyAsString();
			responseBody = responseBody.replaceAll("=\"/ethink", "=\"http://125.73.143.205:8080/YunforgeBI");
			// Write the body, flush and close
			writer.write(responseBody);
			writer.flush();
			writer.close();
		} catch (HttpException e) {
			writer.write(e.toString());
			throw e;

		} catch (IOException e) {
			e.printStackTrace();
			writer.write(e.toString());
			throw e;
		}
	}

}
