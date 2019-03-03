package com.yunforge.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yunforge.base.model.Parameter;
import com.yunforge.common.Constants;

public class WebUtils {

	public static Object getSessionAttribute(HttpServletRequest request,
			String sessionKey) {
		Object objSessionAttribute = null;
		HttpSession session = request.getSession(false);
		if (session != null) {
			objSessionAttribute = session.getAttribute(sessionKey);
		}
		return objSessionAttribute;
	}

	public static void setSessionAttribute(HttpServletRequest request,
			String sessionKey, Object objSessionAttribute) {
		HttpSession session = request.getSession();
		if (session != null)
			session.setAttribute(sessionKey, objSessionAttribute);
	}

	public static void removeSessionAttribute(HttpServletRequest request,
			String sessionKey) {
		HttpSession session = request.getSession();
		if (session != null)
			session.removeAttribute(sessionKey);
	}

	@SuppressWarnings("rawtypes")
	public static Map getParamAsDto(HttpServletRequest request) {
		Map dto = new HashMap();
		Map map = request.getParameterMap();
		Iterator keyIterator = map.keySet().iterator();
		while (keyIterator.hasNext()) {
			String key = (String) keyIterator.next();
			String value = ((String[]) (map.get(key)))[0];
			dto.put(key, value);
		}
		return dto;
	}

	@SuppressWarnings("rawtypes")
	public static String getCodeDesc(String pField, String pCode,
			HttpServletRequest request) {
		List codeList = (List) request.getSession().getServletContext()
				.getAttribute("CODELIST");
		String codedesc = null;
		if (codeList != null) {
			for (int i = 0; i < codeList.size(); i++) {
				Map codeDto = (Map) codeList.get(i);
				if (pField.equalsIgnoreCase(codeDto.get("field").toString())
						&& pCode.equalsIgnoreCase(codeDto.get("code")
								.toString()))
					codedesc = codeDto.get("codedesc").toString();
			}
		}
		return codedesc;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getCodeListByField(String pField,
			HttpServletRequest request) {
		List codeList = (List) request.getSession().getServletContext()
				.getAttribute("CODELIST");
		List lst = new ArrayList();
		if (codeList != null) {
			for (int i = 0; i < codeList.size(); i++) {
				Map codeDto = (Map) codeList.get(i);
				if ((codeDto.get("field").toString()).equalsIgnoreCase(pField)) {
					lst.add(codeDto);
				}
			}
		}
		return lst;
	}

	@SuppressWarnings("rawtypes")
	public static String getParaValue(String paraName,
			HttpServletRequest request) {
		String paraValue = null;
		ServletContext context = request.getSession().getServletContext();
		if (YunforgeUtils.isEmpty(context)) {
			return null;
		}
		List paraList = (List) context.getAttribute(Constants.SYS_PARA);
		if (paraList != null) {
			for (int i = 0; i < paraList.size(); i++) {
				Parameter parameter = (Parameter) paraList.get(i);
				if (paraName.equals(parameter.getParaName())) {
					paraValue = parameter.getParaVal();
				}
			}
		}
		return paraValue;
	}
	
	@SuppressWarnings("rawtypes")
	public static List getParaList(HttpServletRequest request) {
		ServletContext context = request.getSession().getServletContext();
		if (YunforgeUtils.isEmpty(context)) {
			return new ArrayList();
		}
		return (List) context.getAttribute(Constants.SYS_PARA);
	}

	public static String getCookieValue(Cookie[] cookies, String cookieName,
			String defaultValue) {
		if (cookies == null) {
			return defaultValue;
		}
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookieName.equals(cookie.getName()))
				return (cookie.getValue());
		}
		return defaultValue;
	}

}
