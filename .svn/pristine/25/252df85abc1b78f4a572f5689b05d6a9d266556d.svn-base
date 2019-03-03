package com.yunforge.base.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yunforge.base.model.MessageSend;
import com.yunforge.base.model.Notice;
import com.yunforge.base.model.Resource;
import com.yunforge.base.model.Role;
import com.yunforge.base.model.User;
import com.yunforge.base.service.NoticeManager;
import com.yunforge.common.Constants;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.bean.Params;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.web.controller.BaseController;

@Controller
@SessionAttributes({ "accordion" })
public class MainController extends BaseController {
	final static Log log = LogFactory.getLog(MainController.class);

	@Autowired
	private NoticeManager noticeManager;


	@MethodRemark(remark = "访问系统首页")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			User user = (User) currentUser.getSession().getAttribute(
					Constants.SESSION_USER_KEY);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}

	@MethodRemark(remark = "访问应用门户首页")
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, ModelMap model) {
		return "/main";
	}

	
	@RequestMapping(value = "/main/home", method = RequestMethod.GET)
	public String home(HttpServletRequest request, ModelMap model) {
		return "/home";
	}
	
	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public String statistics(HttpServletRequest request, ModelMap model) {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			User user = (User) currentUser.getSession().getAttribute(
					Constants.SESSION_USER_KEY);
			String divCode = user.getDivCode();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "statistics";
	}
	

	@RequestMapping(value = "/nqview", method = RequestMethod.GET)
	public String nqview(HttpServletRequest request, ModelMap model) {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			User user = (User) currentUser.getSession().getAttribute(
					Constants.SESSION_USER_KEY);
			String divCode = user.getDivCode();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "nqview";
	}
	

	private Resource getFirstMenu(List<Resource> list) {
		if(list.size() != 0) {
			return list.get(0);
		}
		return new Resource();
	}
	
	private void addMenuItem(List<Resource> resultResourceList,String name,String url) {
		Resource r = new Resource();
		r.setResName(name);
		r.setResString(url);
		resultResourceList.add(r);
	}
	
	private List<Resource> getUserAllMenu(User user) {
		
		List<Resource> resourceList = new ArrayList<Resource>();
		List<Role> roles = user.getRoles();
		for(int i = 0;i < roles.size();i++) {
			Role role = roles.get(i);
			resourceList.addAll(role.getResources());
		}
		return resourceList;
	}
	
	private void removeUnPermMenu(List<Resource> allMenuList,List<Resource> userMenuList) {
		
		for(int i = allMenuList.size() - 1;i >= 0;i--) {
			Resource r1 = allMenuList.get(i);
			
			boolean isExists = false;
			for(int j = 0;j < userMenuList.size();j++) {
				Resource r2 = userMenuList.get(j);
				if(r2.getResString() != null) {
					if(r2.getResString().equals(r1.getResString())) {
//					if(r2.getResString().indexOf(r1.getResString()) != -1) {
						isExists = true;
					}
				}
			}
			
			if(!isExists) {
				allMenuList.remove(i);
			}
		}
	}
	
}