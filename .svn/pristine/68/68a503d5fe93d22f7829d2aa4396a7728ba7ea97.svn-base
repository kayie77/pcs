package com.yunforge.base.web.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
//import oracle.sql.BLOB;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yunforge.base.model.Division;
import com.yunforge.base.model.Group;
import com.yunforge.base.model.Message;
import com.yunforge.base.model.MessageFile;
import com.yunforge.base.model.MessageSend;
import com.yunforge.base.model.Notice;
import com.yunforge.base.model.Org;
import com.yunforge.base.model.Person;
import com.yunforge.base.model.Role;
import com.yunforge.base.model.User;
import com.yunforge.base.service.GroupManager;
import com.yunforge.base.service.OrgManager;
import com.yunforge.base.service.PersonManager;
import com.yunforge.base.service.RoleManager;
import com.yunforge.base.service.UserManager;
import com.yunforge.common.Constants;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.bean.Params;
import com.yunforge.common.bean.TreeNode;
import com.yunforge.common.util.StringUtil;
import com.yunforge.common.util.WebUtils;
import com.yunforge.common.util.YunforgeUtils;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.web.controller.BaseController;

@Controller
public class FileController extends BaseController {
	final static Log log = LogFactory.getLog(FileController.class);


	/**
	 * 保存短信
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/office/file/update", method = RequestMethod.POST)
	public void saveMessage(HttpServletRequest request,HttpServletResponse response) throws Exception {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		JSONObject jsonObject = new JSONObject();
		
		//获取表单数据
		try {
			Map<String, String[]> map1 = multipartRequest.getParameterMap();
			Set<Entry<String, String[]>> set1 = map1.entrySet();
			for(Iterator j = set1.iterator();j.hasNext();) {
				Entry<String, String[]> item = (Entry<String, String[]>)j.next();
				String key = item.getKey();
				String[] values = item.getValue();
				
				if("id".equals(key)) {

				}
			}

		} catch(Exception e) {
			
		}
		
		try {
			
			//保存文件操作
			String randomfilename = null;
			MultiValueMap<String, MultipartFile> map = (MultiValueMap<String, MultipartFile>) multipartRequest.getMultiFileMap();
			
			//遍历要导入的文件
			Set set = map.entrySet();
			for(Iterator i = set.iterator();i.hasNext();) {
				
				Map.Entry entry = (Map.Entry)i.next();
				List list = (List)entry.getValue();
				for(int k = 0;k < list.size();k++) {
					CommonsMultipartFile item = (CommonsMultipartFile)list.get(k);

					//生成随机文件名，保存文件
					randomfilename = UUID.randomUUID().toString();
					byte[] bytes = item.getBytes();

		        	new StringUtil().saveFile(request,Message.WORDTYPEFILE_FILE_PATH, randomfilename, bytes);
				}
			}
			
			
//{"error":0,"message":".....","url":"/img/1111.gif"} 
			jsonObject.put("url", request.getContextPath()+"/office/file/download?type="+Message.WORDTYPEFILE_FILE_PATH+"&filename=" + randomfilename);
			jsonObject.put("error", 0);
			jsonObject.put("message", "操作成功!");
			
		} catch(Exception e) {
			e.printStackTrace();
			
			jsonObject.put("error", 1);
			jsonObject.put("message", "操作失败!");
		}
		
		try {
			response.setCharacterEncoding("gbk");
			response.getWriter().print(jsonObject.toString());
		} catch(Exception e) {
			
		}
//		return jsonObject;
	}
	
	@RequestMapping(value = "/office/file/download")
	public String download(HttpServletRequest request,HttpServletResponse response) {

		String filename = request.getParameter("filename");
		String type = request.getParameter("type");
		String uploadPath = StringUtil.getUploadPath(request);
		
		StringUtil.downloadFile(response, uploadPath + type + "/" + filename, filename);

		return null;
	}
}