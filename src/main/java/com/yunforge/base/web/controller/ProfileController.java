package com.yunforge.base.web.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.digester.Digester;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yunforge.base.model.Message;
import com.yunforge.base.model.MessageFile;
import com.yunforge.base.model.Org;
import com.yunforge.base.model.Person;
import com.yunforge.base.model.Preference;
import com.yunforge.base.model.User;
import com.yunforge.base.service.OrgManager;
import com.yunforge.base.service.PersonManager;
import com.yunforge.base.service.PreferenceManager;
import com.yunforge.base.service.UserManager;
import com.yunforge.common.util.StringUtil;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.web.controller.BaseController;

@Controller
@SessionAttributes("personInfo")
public class ProfileController extends BaseController {
	final static Log log = LogFactory.getLog(ProfileController.class);

	@Autowired
	private UserManager userManager;

	@Autowired
	private PersonManager personManager;

	@Autowired
	private PreferenceManager preferenceManager;

	@Autowired
	private PasswordService passwordService;

	@Autowired
	private OrgManager orgManager;

	@RequestMapping(value = "/profile/saveImage", method = RequestMethod.POST)
	public @ResponseBody JSONObject saveImage(HttpServletRequest request,HttpServletResponse response) throws Exception { //HttpServletRequest request  @ResponseBody JSONObject

		JSONObject jsonObject = new JSONObject();
		
		//获取当前登陆用户信息
		Subject currentUser = SecurityUtils.getSubject();
		User user = userManager.findUserByUsername(currentUser.getPrincipal().toString());
				
		try {
			//保存文件操作
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultiValueMap<String, MultipartFile> map = (MultiValueMap<String, MultipartFile>) multipartRequest.getMultiFileMap();
			
			//遍历要导入的文件
			Set set = map.entrySet();
			for(Iterator i = set.iterator();i.hasNext();) {
				
				Map.Entry entry = (Map.Entry)i.next();
				List list = (List)entry.getValue();
				for(int k = 0;k < list.size();k++) {
					CommonsMultipartFile item = (CommonsMultipartFile)list.get(k);

					String randomfilename = UUID.randomUUID().toString();
					byte[] bytes = item.getBytes();
		        	new StringUtil().saveFile(request,User.AVATAR_FILE_PATH, randomfilename, bytes);
		        	
					user.setAvatar(randomfilename);
					userManager.saveUser(user);
					
					jsonObject.put("file", randomfilename);
				}
			}
			
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "操作成功!");

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}
	@MethodRemark(remark = "访问自己帐户信息")
	@RequestMapping(value = "/profile/profile", method = RequestMethod.GET)
	public String showProfile(HttpServletRequest request, ModelMap model) {
		User user = userManager.findUserByUsername(super.getCurrentUserName());
		model.put("profile", user);
		model.put("personInfo", user.getPerson());
		return "/profile/profile";
	}

	@SuppressWarnings("unchecked")
	@MethodRemark(remark = "访问自己设置信息")
	@RequestMapping(value = "/profile/preference", method = RequestMethod.GET)
	public String showPreference(HttpServletRequest request, ModelMap model) {
		User user = userManager.findUserByUsername(super.getCurrentUserName());
		if (preferenceManager.countByUsername(super.getCurrentUserName()) == 0) {
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
				List<Preference> prefList = (List<Preference>) digester
						.parse(PeferenceController.class
								.getResourceAsStream("/user-settings.xml"));
				for (Preference pref : prefList) {
					pref.setUser(user);
					user.getPreferences().add(pref);
				}
				user = userManager.saveUser(user);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		model.put("user", user);
		model.put("preferences", user.getPreferences());
		return "/profile/preference";
	}

	@MethodRemark(remark = "更新自己帐户信息")
	@RequestMapping(value = "/profile/updateProfile")
	public @ResponseBody
	JSONObject updateProfile(String pk, String name, String value,
			HttpServletRequest request, ModelMap model) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			User user = userManager.findUserById(pk);
			if (StringUtils.isNotBlank(name) && name.equals("caSn")) {
				user.setCaSn(value);
			}
			if (StringUtils.isNotBlank(name) && name.equals("qq")) {
				user.setQq(value);
			}
			userManager.saveUser(user);
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "用户设置已更新!");

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}

	@MethodRemark(remark = "更新自己个人信息")
	@RequestMapping(value = "/profile/updatePersonInfo", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject updatePersonInfo(@ModelAttribute("personInfo") Person person,
			HttpServletRequest request, ModelMap model, SessionStatus status) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			personManager.savePerson(person);
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "用户信息已更新!");
			status.setComplete();
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}

	@MethodRemark(remark = "修改用户密码")
	@RequestMapping(value = "/profile/updatePassword", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject doUpdatePassword(String password, String confirmPassword)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			userManager.updatePassword(this.getCurrentUserName(),
					passwordService.encryptPassword(password));
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "密码已更新!");

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "密码更新失败!");
		}
		return jsonObject;
	}

	@MethodRemark(remark = "更新自己设置信息")
	@RequestMapping(value = "/profile/updatePreference")
	public @ResponseBody
	JSONObject updatePreference(String pk,
			String name, String value, HttpServletRequest request,
			ModelMap model) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			Preference preference = preferenceManager.findPreferenceById(pk);
			preference.setPrefVal(value);
			preferenceManager.savePreference(preference);
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "用户设置已更新!");

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}
}
