package com.yunforge.core.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yunforge.base.service.UserManager;
import com.yunforge.core.web.listerner.OnLineUser;

@WebServlet(asyncSupported = false, name = "initiateServlet", description = "登录初始化", urlPatterns = { "/iniateServlet" })
public abstract class InitiateServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 2311174807499611586L;

	public InitiateServlet() {
	}

	@Override
	public void init() throws ServletException {
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		execute(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		execute(request, response);
	}

	@SuppressWarnings("unchecked")
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			ApplicationContext ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(this.getServletContext());
			UserManager manager = (UserManager) ctx.getBean("userManager");
			Map<String, String> userMap = (HashMap<String, String>) this
					.getServletContext().getAttribute("userMap");
			if (userMap == null
					|| (userMap != null && !userMap.containsKey(request
							.getRemoteUser()))) {
				String fullName = manager.getFullName(request.getRemoteUser());
				OnLineUser onlineUser = new OnLineUser(request.getRemoteUser(),
						fullName);
				request.getSession().setAttribute("onlineUser", onlineUser);
			}

			request.getRequestDispatcher("/banner.jsp").forward(request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
	}
}
