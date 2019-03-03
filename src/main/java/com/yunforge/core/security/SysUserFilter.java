package com.yunforge.core.security;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.yunforge.base.model.Resource;
import com.yunforge.base.model.User;
import com.yunforge.base.service.ResourceManager;
import com.yunforge.base.service.UserManager;
import com.yunforge.common.Constants;
import com.yunforge.common.bean.NavigationBean;

public class SysUserFilter extends PathMatchingFilter {

	@Autowired
	private UserManager userManager;

	@Autowired
	private ResourceManager resourceManager;

	@Override
	protected boolean onPreHandle(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		List<NavigationBean> accordion = new LinkedList<NavigationBean>();
		try {

			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			if (session.getAttribute(Constants.SESSION_USER_KEY) == null) {
				String username = (String) currentUser.getPrincipal();
				User user = userManager.findUserByUsername(username);
				session.setAttribute(Constants.SESSION_USER_KEY, user);
			}
			if (session.getAttribute("accordion") == null) {
				List<Resource> menuList = resourceManager.getTopMenus();
				for (Resource menu : menuList) {
					if (currentUser.isPermittedAll(menu.getPermission())) {
						accordion.add(new NavigationBean(String.valueOf(menu
								.getId()), menu.getResName(),
								menu.getIconCls(), menu.getParent() != null,
								getAccodionCentent(request.getServletContext()
										.getContextPath(), menu.getId(), menu
										.getResCode())));
					}
				}
				session.setAttribute("accordion", accordion);
				for (int i = 0; i < accordion.size(); i++) {
					System.out.println("***************" 
							+ accordion.get(i).getContent() +"************");
					;
				}
				session.setAttribute("menuList", menuList);

			}

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private String getAccodionCentent(String contextPath, String pid, String res) {
		StringBuilder content = new StringBuilder();
		List<Resource> list = resourceManager.getSubMenus(pid);
		Subject currentUser = SecurityUtils.getSubject();
		if ("10000".equals(res)) {
			if ("409".equals(pid)){
				content.append("<ul id=\"" + pid
						+ "\" class=\"nav nav-list\">\n");
			}else{
			content.append("<ul style=\"display: none;\" id=\"" + pid
					+ "\" class=\"nav nav-list\">\n");
			}
		} else{
			content.append("<ul class=\"submenu\">\n");
		}
		if (list.size() > 0) {
			for (Resource menu : list) {
				if (currentUser.isPermittedAll(menu.getPermission())) {
					content.append("<li class=\"\">\n").append("<a href=\"");
					if (resourceManager.hasSubMenus(menu.getId())) {
						content.append("#")
								.append("\"")
								.append(" class=\"dropdown-toggle\">\n")
								.append("<i class=\"menu-icon fa ")
								.append(menu.getIconCls())
								.append("\"></i>\n")
								.append(menu.getResName())
								.append("<b class=\"arrow fa fa-angle-down\"></b>\n")
								.append("</a>\n")
								.append("<b class=\"arrow\"></b>\n")
								.append(getAccodionCentent(contextPath,
										menu.getId(), menu.getResCode()));

					} else {
						content.append(
								contextPath + "/index#page"
										+ menu.getResString())
								.append("\" data-url=\"page"
										+ menu.getResString()).append("\">\n")
								.append("<i class=\"menu-icon fa ")
								.append(menu.getIconCls()).append("\"></i>\n")
								.append(menu.getResName()).append("</a>\n")
								.append("<b class=\"arrow\"></b>\n");
					}
					content.append("</li>\n");
				}

			}
		}
		content.append("</ul>");
		return content.toString();
	}
}
