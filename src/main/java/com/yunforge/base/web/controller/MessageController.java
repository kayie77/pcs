package com.yunforge.base.web.controller;

import java.sql.Blob;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.yunforge.base.model.Division;
import com.yunforge.base.model.Message;
import com.yunforge.base.model.MessageDTO;
import com.yunforge.base.model.MessageFile;
import com.yunforge.base.model.MessageSend;
import com.yunforge.base.model.Org;
import com.yunforge.base.model.Person;
import com.yunforge.base.model.User;
import com.yunforge.base.service.DivisionManager;
import com.yunforge.base.service.MessageManager;
import com.yunforge.base.service.OrgManager;
import com.yunforge.base.service.PersonManager;
import com.yunforge.base.service.UserManager;
import com.yunforge.common.Constants;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.bean.Params;
import com.yunforge.common.util.StringUtil;
import com.yunforge.common.util.WebUtils;
import com.yunforge.common.util.YunforgeUtils;
import com.yunforge.core.web.controller.BaseController;

/**
 * 短信功能
 * @author Administrator
 *
 */
@Controller
public class MessageController extends BaseController {
	final static Log log = LogFactory.getLog(MessageController.class);

	@Autowired
	private MessageManager messageManager;

	@Autowired
	private DivisionManager divisionManager;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private PersonManager personManager;
	
	@Autowired
	private OrgManager orgManager;

	/**
	 * 短信状态列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/office/message/messageStatusSearch")
	public String messageStatusSearch(HttpServletRequest request, ModelMap model) {
		
		return "/office/message/messageStatusSearch";
	}

	/**
	 * 接收到的短信列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/office/message/recvlist")
	public String recvlist(HttpServletRequest request, ModelMap model) {
		
		model.addAttribute("actionUrl", request.getContextPath() + "/office/notice/update");
		String pageSize= WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE, pageSize==null?20:Integer.valueOf(pageSize));
		model.addAttribute("canReply", "true");
		
		return "/office/message/recvlist";
	}
	
	/**
	 * 查询接收到的短信
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/office/message/queryRecvList")
	public @ResponseBody GridBean<MessageDTO> queryRecvList(HttpServletRequest request) {
		
		//封装查询条件
		String title = null;
		String div_name = null;
		String status = null;
		Integer page = StringUtil.getInt(request.getParameter("page"), 0);
		Integer rows = StringUtil.getInt(request.getParameter("rows"), 20);
		String sidx = request.getParameter("sidx");
		String sord = request.getParameter("sord");
		String searchField = request.getParameter("searchField");
		String searchString = request.getParameter("searchString");
		
		int pageSize = rows == null ? 20 : rows.intValue();
		int pageIndex = (page == null || page == 0) ? 0 : page.intValue() - 1;

		searchString = YunforgeUtils.getString(searchString, "iso-8859-1", "utf-8");
		
		if("readflag".equals(searchField) && YunforgeUtils.isEmpty(searchString)) {
			searchString = "1";
		}
		if("title".equals(searchField)) {
			title = searchString;
		}
		if("div_name".equals(searchField)) {
			div_name = searchString;
		}
		if("readflag".equals(searchField)) {
			status = searchString;
		}

		//获取当前用户信息
		Subject currentUser = SecurityUtils.getSubject();
		User user = userManager.findUserByUsername(currentUser.getPrincipal().toString());
		Person person = personManager.findById(user.getPerson().getId());
		Org org = orgManager.findById(person.getOrg().getId());
		String divcode = org.getDivCode();

		//封装查询条件进行查询
		Params params = new Params();
		params.put("pageIndex", pageIndex+1 + "");
		params.put("pageSize", pageSize);
		params.put("sidx", sidx);
		params.put("sord", sord);
		params.put("divcode", divcode);
		params.put("title", title);
		params.put("div_name", div_name);
		params.put("status", status);
		
		GridBean grid = messageManager.queryRecvMessage(params);
		return grid;
	}
	
	/**
	 * 查询已发送短信
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/office/message/querySendedMessage")
	public @ResponseBody GridBean<MessageDTO> querySendedMessage(HttpServletRequest request) {

		//封装查询条件
		String title = null;
		String div_name = null;
		String status = null;
		Integer page = StringUtil.getInt(request.getParameter("page"), 0);
		Integer rows = StringUtil.getInt(request.getParameter("rows"), 20);
		String sidx = request.getParameter("sidx");
		String sord = request.getParameter("sord");
		String searchField = request.getParameter("searchField");
		String searchString = request.getParameter("searchString");
		
		int pageSize = rows == null ? 20 : rows.intValue();
		int pageIndex = (page == null || page == 0) ? 0 : page.intValue() - 1;

		searchString = YunforgeUtils.getString(searchString, "iso-8859-1", "utf-8");
		
		if("readflag".equals(searchField) && YunforgeUtils.isEmpty(searchString)) {
			searchString = "1";
		}
		if("title".equals(searchField)) {
			title = searchString;
		}
		if("div_name".equals(searchField)) {
			div_name = searchString;
		}
		if("readflag".equals(searchField)) {
			status = searchString;
		}

		//获取当前登陆用户信息
		Subject currentUser = SecurityUtils.getSubject();
		User user = userManager.findUserByUsername(currentUser.getPrincipal().toString());
		Person person = personManager.findById(user.getPerson().getId());
		Org org = orgManager.findById(person.getOrg().getId());
		String divcode = org.getDivCode();

		//封装查询条件进行查询
		Params params = new Params();
		params.put("pageIndex", pageIndex+1 + "");
		params.put("pageSize", pageSize);
		params.put("sidx", sidx);
		params.put("sord", sord);
		params.put("divcode", divcode);
		params.put("title", title);
		params.put("div_name", div_name);
		params.put("status", status);
		
		GridBean grid = messageManager.querySendedMessage(params);
		return grid;
	}

	/**
	 * 保存短信
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/office/message/save", method = RequestMethod.POST)
	public void saveMessage(HttpServletRequest request,HttpServletResponse response) throws Exception { //HttpServletRequest request  @ResponseBody JSONObject

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Message message = new Message();
		String messageSendId = null;
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
					message.setId(StringUtil.getStr(values, 0));
				}
				if("title".equals(key)) {
					message.setTitle(StringUtil.getStr(values, 0));
				}
				if("needreplay".equals(key)) {
					message.setNeedreplay(StringUtil.getInt(StringUtil.getStr(values, 0), 0));
				}
				if("contentStr".equals(key)) {
					message.setContentStr(StringUtil.getStr(values, 0));
				}
				if("important".equals(key)) {
					message.setImportant(StringUtil.getInt(StringUtil.getStr(values, 0), 0));
				}
				if("divIdStr".equals(key)) {
					message.setDivIdStr(StringUtil.getStr(values, 0));
				}
				if("parentId".equals(key)) {
					String parentId = StringUtil.getStr(values, 0);
					if(StringUtil.notEmpty(parentId)) {
						Message parent = messageManager.getMessage(parentId);
						message.setParent(parent);
					}
				}
				if("messageSendId".equals(key)) {
					messageSendId = StringUtil.getStr(values, 0);
				}
			}

		} catch(Exception e) {
			
		}
		
		//获取当前登陆用户
		Subject currentUser = SecurityUtils.getSubject();
		User user = userManager.findUserByUsername(currentUser.getPrincipal().toString());
		Person person = personManager.findById(user.getPerson().getId());
		Org org = orgManager.findById(person.getOrg().getId());
		String divcode = org.getDivCode();
		
		try {
			
			//id为空，表示新增
			if(StringUtil.isEmpty(message.getId())) {
				
				Division division = divisionManager.findByDivisionCode(divcode);
				
//				Blob blob = Blob.getEmptyBLOB();
//				blob.setBytes(message.getContentStr().getBytes());
				
				message.setId(null);
				message.setContent(null);
				message.setCreatedate(new Date());
				message.setCreateDiv(division);
				message.setModifydate(new Date());
				message.setModifyDiv(division);
				if(message.getImportant() == null) {
					message.setImportant(0);
				}
				if(message.getNeedreplay() == null) {
					message.setNeedreplay(0);
				}
				
				//保存短信
				messageManager.saveMessage(message);
			
				//保存短信接收者
				if(StringUtil.notEmpty(messageSendId)) {
					MessageSend messageSend = messageManager.getMessageSend(messageSendId);
					if(messageSend.getReadflag() == null || messageSend.getReplayflag() == 0) {
						messageSend.setReplayflag(1);
						messageManager.saveMessageSend(messageSend);
					}
				}
			} else {
				
				//id不为空，表示修改
				Message message1 = messageManager.getMessage(message.getId());

				message.setCreatedate(message1.getCreatedate());
				message.setModifydate(new Date());
				if(message.getImportant() == null) {
					message.setImportant(0);
				}
				if(message.getNeedreplay() == null) {
					message.setNeedreplay(0);
				}

				messageManager.saveMessage(message);
			}
			
			//删除发送区域，重新新增
			messageManager.deleteMessageSendByMessage(message.getId());
			String[] divIds = YunforgeUtils.split(message.getDivIdStr(), ",");
			for(int i = 0;i < divIds.length;i++) {
				
				Division division = divisionManager.findById(divIds[i]);
				
				MessageSend messageSend = new MessageSend();
				messageSend.setDivision(division);
				messageSend.setMessage(message);
				messageSend.setReadflag(0);
				messageSend.setReplayflag(0);
				
				messageManager.saveMessageSend(messageSend);
			}
			
			
			
			//保存文件操作
			MultiValueMap<String, MultipartFile> map = (MultiValueMap<String, MultipartFile>) multipartRequest.getMultiFileMap();
			
			//遍历要导入的文件
			Set set = map.entrySet();
			for(Iterator i = set.iterator();i.hasNext();) {
				
				Map.Entry entry = (Map.Entry)i.next();
				List list = (List)entry.getValue();
				for(int k = 0;k < list.size();k++) {
					CommonsMultipartFile item = (CommonsMultipartFile)list.get(k);

					//生成随机文件名，保存文件
					String randomfilename = UUID.randomUUID().toString();
					byte[] bytes = item.getBytes();
					
					MessageFile messageFile = new MessageFile();
					messageFile.setFilelength(item.getSize());
					messageFile.setFilename(item.getOriginalFilename());
					messageFile.setFilepath(randomfilename);
					messageFile.setMessage(message);
					messageManager.saveMessageFile(messageFile);
					
		        	new StringUtil().saveFile(request,Message.MESSAGE_FILE_PATH, randomfilename, bytes);
				}
			}
			
			

			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "操作成功!");
			
		} catch(Exception e) {
			e.printStackTrace();
			
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		
		try {
			response.setCharacterEncoding("gbk");
			response.getWriter().print(jsonObject.toString());
		} catch(Exception e) {
			
		}
//		return jsonObject;
	}
	
	//短信列表
	@RequestMapping("/office/message/list")
	public String index(HttpServletRequest request, ModelMap model) {
		
		model.addAttribute("actionUrl", request.getContextPath() + "/office/notice/update");
		String pageSize= WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE, pageSize==null?20:Integer.valueOf(pageSize));
		
		return "/office/message/list";
	}
	

	/**
	 * 删除短信
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/office/message/delete", method = RequestMethod.POST)
	public @ResponseBody JSONObject delete(HttpServletRequest request) throws Exception {
		JSONObject jsonObject = new JSONObject();
		
		try {
			//删除id用逗号隔开
			String id = request.getParameter("id");
			String[] ids = id.split(",");
			for(int i = 0;i < ids.length;i++) {
				messageManager.deleteMessageSendAndOther(ids[i]);
			}
			
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "操作成功!");
		} catch(Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		
		return jsonObject;
	}
	
	/**
	 * 删除短信和文件
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/office/message/deleteMessage", method = RequestMethod.POST)
	public @ResponseBody JSONObject deleteMessage(HttpServletRequest request) throws Exception {
		JSONObject jsonObject = new JSONObject();
		
		try {
			
			//删除短信和文件
			String id = request.getParameter("id");
			messageManager.deleteMessageAndFile(id);
			
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "操作成功!");
		} catch(Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		
		return jsonObject;
	}
	
	/**
	 * 编辑短信
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/office/message/edit", method = RequestMethod.GET)
	public String editNotice(HttpServletRequest request, ModelMap model) {

		String messageSendId = request.getParameter("messageSendId");
		String parentId = request.getParameter("parentId");
		
		if(parentId != null) {
			Message message = messageManager.getMessage(parentId);
			model.addAttribute("parentMessage", message);
		}

		model.addAttribute("parentId", parentId);
		model.addAttribute("messageSendId", messageSendId);
		model.addAttribute("actionUrl", request.getContextPath() + "/office/message/save");
		return "/office/message/edit";
	}
	
	/**
	 * 下载短信附件
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/office/message/downloadMessage")
	public String downloadMessage(HttpServletRequest request,HttpServletResponse response) {

		String id = request.getParameter("id");
		String uploadPath = StringUtil.getUploadPath(request);
		
		MessageFile messageFile = messageManager.getMessageFileById(id);
		
		StringUtil.downloadFile(response, uploadPath + "message/" + messageFile.getFilepath(),messageFile.getFilename());

		return null;
	}
	
	/**
	 * 查看短信信息
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/office/message/viewMessage", method = RequestMethod.GET)
	public String viewMessage(HttpServletRequest request, ModelMap model) throws Exception {

		String canReply = request.getParameter("canReply");
		String canDelete = request.getParameter("canDelete");
		
		//获取当前登陆用户信息
		String id = request.getParameter("id");
		Subject currentUser = SecurityUtils.getSubject();
		User user = userManager.findUserByUsername(currentUser.getPrincipal().toString());
		Person person = personManager.findById(user.getPerson().getId());
		Org org = orgManager.findById(person.getOrg().getId());
		String divcode = org.getDivCode();
		
		//获取短信息信息
		MessageSend messageSend = messageManager.getMessageSend(id);
		Message message = messageSend.getMessage();
		
		//登录单位=接收单位，则可以回复和删除
		if(messageSend.getDivision().getDivCode().equals(divcode)) {

			//可回复
			model.addAttribute("canReply", "true");
			model.addAttribute("canDelete", "true");
			
			//标示为已阅读
			if(messageSend.getReadflag() == 0) {
				messageSend.setReadflag(1);
				messageManager.saveMessageSend(messageSend);
			}
		}
		

		//查看回复
		Message replyMessage = messageManager.getReplyMessage(message.getId(), messageSend.getId());
		if(replyMessage != null) {
			Blob content = replyMessage.getContent();
			if(content != null) {
				replyMessage.setContentStr(new String());
			}
			
			List<MessageFile> replyMessageFileList = messageManager.getMessageFile(replyMessage.getId());
			model.addAttribute("replyMessage", replyMessage);
			model.addAttribute("replyMessageFileList", replyMessageFileList);
		}
		
		Blob content = message.getContent();
		if(content != null) {
			message.setContentStr(new String());
		}
		List<MessageFile> messageFileList = messageManager.getMessageFile(message.getId());
		
		//是否可以操作
		if(StringUtil.notEmpty(canDelete)) {
			model.addAttribute("canDelete", canDelete);
		}
		if(StringUtil.notEmpty(canReply)) {
			model.addAttribute("canReply", canReply);
		}

		model.addAttribute("messageFileList", messageFileList);
		model.addAttribute("messageSend", messageSend);
		model.addAttribute("message", message);
		
		return "/office/message/viewMessage";
	}
	
	
	
}