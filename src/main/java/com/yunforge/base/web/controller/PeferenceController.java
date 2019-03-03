package com.yunforge.base.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.digester.Digester;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunforge.base.model.Preference;
import com.yunforge.base.service.PreferenceManager;
import com.yunforge.base.service.UserManager;
import com.yunforge.core.web.controller.BaseController;

@Controller
public class PeferenceController extends BaseController {
	final static Log log = LogFactory.getLog(PeferenceController.class);

	@Autowired
	private UserManager userManager;

	@Autowired
	private PreferenceManager preferenceManager;

	@RequestMapping(value = "/preference/load", method = RequestMethod.GET)
	public @ResponseBody
	List<Preference> updatePersonInfo(HttpServletRequest request, ModelMap model)
			throws Exception {
		List<Preference> prefs = new ArrayList<Preference>();
		Digester digester = new Digester();
		digester.setValidating(false);
		digester.addObjectCreate("settings", "java.util.ArrayList");
		digester.addSetProperties("settings");
		digester.addObjectCreate("settings/pref",
				"com.yunforge.base.model.Preference");
		digester.addBeanPropertySetter("settings/pref/prefName");
		digester.addBeanPropertySetter("settings/pref/prefVal");
		digester.addBeanPropertySetter("settings/pref/prefDesc");
		digester.addSetNext("settings/pref", "add",
				"com.yunforge.base.model.Preference");
		try {
			// 定义好了上面的解析规则后，就可以开始进行解析工作了
			prefs = (List<Preference>) digester.parse(PeferenceController.class
					.getResourceAsStream("/user-settings.xml"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return prefs;
	}
}
