package com.yunforge.base.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunforge.base.model.Division;
import com.yunforge.base.model.Notice;
import com.yunforge.base.model.NoticeDivision;
import com.yunforge.base.model.Option;
import com.yunforge.base.model.Org;
import com.yunforge.base.model.Person;
import com.yunforge.base.model.User;
import com.yunforge.base.service.DivisionManager;
import com.yunforge.base.service.NoticeManager;
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
 * 通知功能
 * @author Administrator
 *
 */
@Controller
public class NoticeController extends BaseController {
	final static Log log = LogFactory.getLog(NoticeController.class);

	@Autowired
	private NoticeManager noticeManager;

	@Autowired
	private DivisionManager divisionManager;
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private PersonManager personManager;
	
	@Autowired
	private OrgManager orgManager;

	
	/**
	 * 访问通知页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/office/notice")
	public String index(HttpServletRequest request, ModelMap model) {
		
		model.addAttribute("actionUrl", request.getContextPath() + "/office/notice/update");
		String pageSize= WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE, pageSize==null?20:Integer.valueOf(pageSize));
		
		return "/office/notice/notice";
	}
	
	/**
	 * 访问状态列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/office/notice/statusSearch")
	public String statusSearch(HttpServletRequest request, ModelMap model) {
		
		return "/office/notice/statusSearch";
	}
	
	/**
	 * 访问标示列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/office/notice/readflagSearch")
	public String readflagSearch(HttpServletRequest request, ModelMap model) {
		
		return "/office/notice/readflagSearch";
	}
	
	/**
	 * 查看通知
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/office/notice/viewNotice", method = RequestMethod.GET)
	public String viewNotice(HttpServletRequest request, ModelMap model) {

		String id = request.getParameter("id");
		Subject currentUser = SecurityUtils.getSubject();
		User user = userManager.findUserByUsername(currentUser.getPrincipal().toString());
		Person person = personManager.findById(user.getPerson().getId());
		Org org = orgManager.findById(person.getOrg().getId());
		String divcode = org.getDivCode();
		
		Notice notice = noticeManager.getNotice(id);
		Division division = divisionManager.findByDivisionCode(notice.getDivcode());
//		if(notice.getContent() != null) {
////			notice.setContentStr(new String(notice.getContent().getBytes()));
//			notice.setContentStr(new String(notice.getContent().toString()));
//		}
		model.addAttribute("divName", division.getDivName());
		model.addAttribute("notice", notice);
		
		//设置为已读
		noticeManager.setViewFlag(divcode, id);
		
		return "/office/notice/viewNotice";
	}
	
	
	/**
	 * 编辑通知
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/office/notice/edit", method = RequestMethod.GET)
	public String editNotice(HttpServletRequest request, ModelMap model) {

		String id = request.getParameter("id");
		Notice notice = new Notice();
		
		//id不为空，标示修改
		if(!YunforgeUtils.isEmpty(id)) {
			notice = noticeManager.getNotice(id);
//			if(notice.getContent() != null) {
////				notice.setContentStr(new String(notice.getContent().getBytes()));
//				notice.setContentStr(new String(notice.getContent().toString()));
//			}
			
			//获取通知区域
			List<NoticeDivision> noticeDivisionList = noticeManager.getNoticeDivisionByNoticeId(id);
			
			//拼接通知区域id
			String divIds = "";
			StringBuffer divIdsSB = new StringBuffer();
			for(int i = 0;i < noticeDivisionList.size();i++) {
				divIdsSB.append(noticeDivisionList.get(i).getId()).append(",");
			}
			if(divIdsSB.length() != 0) {
				divIds = divIdsSB.toString();
				divIds = divIds.substring(0,divIds.length() - 1);
			}
			model.addAttribute("divIdStr", divIds);
		}
		
		//通知状态列表
		String[][] values = new String[][]{{"已发布","1"},{"未发布","2"},{"已删除","0"}};
		List<Option> statusList = new ArrayList<Option>();
		for(int i = 0;i < values.length;i++) {
			Option option = new Option();
			option.setKey(values[i][0]);
			option.setValue(values[i][1]);
			statusList.add(option);
		}
		
		model.addAttribute("statusList", statusList);
		model.addAttribute("notice", notice);
		
		model.addAttribute("actionUrl", request.getContextPath() + "/office/notice/save");
		return "/office/notice/noticeForm";
	}
	
	/**
	 * 保存通知
	 * @param notice
	 * @param oper
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/office/notice/save", method = RequestMethod.POST)
	public @ResponseBody JSONObject saveNotice(@ModelAttribute("Notice") Notice notice, String oper) throws Exception {
		JSONObject jsonObject = new JSONObject();

		try {
			
			//获取当前登陆用户信息
			Subject currentUser = SecurityUtils.getSubject();
			User user = userManager.findUserByUsername(currentUser.getPrincipal().toString());
			Person person = personManager.findById(user.getPerson().getId());
			Org org = orgManager.findById(person.getOrg().getId());
			String divcode = org.getDivCode();
			
			//id为空，表示新增
			if(YunforgeUtils.isEmpty(notice.getId())) {
				
				notice.setId(null);
				notice.setDivcode(divcode);
				notice.setCreatedate(new Date());
				notice.setModifydate(new Date());
				noticeManager.saveNotice(notice);
			
			} else {

				//id不为空，表示修改
				Notice notice1 = noticeManager.getNotice(notice.getId());
				notice1.setTitle(notice.getTitle());
				notice1.setStatus(notice.getStatus());
				notice1.setNtcontent(notice.getNtcontent());
				notice1.setModifydate(new Date());
				noticeManager.saveNotice(notice1);
			}
			
			//删除通知区域，重新新增
			noticeManager.deleteNoticeDivisionByNotice(notice.getId());
			String[] divIds = YunforgeUtils.split(notice.getDivIdStr(), ",");
			for(int i = 0;i < divIds.length;i++) {
				
				Division division = divisionManager.findById(divIds[i]);
				
				NoticeDivision nd = new NoticeDivision();
				nd.setDivision(division);
				nd.setNotice(notice);
				nd.setReadDate(null);
				nd.setReadFlag(NoticeDivision.FLAG_UNREAD);
				
				noticeManager.saveNoticeDivision(nd);
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
	 * 删除通知
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/office/notice/delete", method = RequestMethod.POST)
	public @ResponseBody JSONObject deleteNotice(HttpServletRequest request) throws Exception {
		JSONObject jsonObject = new JSONObject();
		
		try {
			
			//删除的id用逗号隔开
			String id = request.getParameter("id");
			String[] ids = id.split(",");
			for(int i = 0;i < ids.length;i++) {
				noticeManager.deleteNotice(ids[i]);
			}
			
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "操作成功!");
		} catch(Exception e) {
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		
		return jsonObject;
	}
	
	/**
	 * 查看通知列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/office/notice/noticeViewList")
	public String viewlist(HttpServletRequest request, ModelMap model) {
		
		model.addAttribute("actionUrl", request.getContextPath() + "/office/notice/update");
		String pageSize= WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE, pageSize==null?20:Integer.valueOf(pageSize));
		
		return "/office/notice/noticeViewList";
	}
	
	/**
	 * 查询接受的通知列表
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @param search
	 * @param searchField
	 * @param searchOper
	 * @param searchString
	 * @param filters
	 * @return
	 */
	@RequestMapping(value = "/office/notice/queryViewList")
	public @ResponseBody GridBean<Notice> queryViewList(Integer page, Integer rows, String sidx,
			String sord, boolean search, String searchField, String searchOper,
			String searchString, String filters) {

		//获取传入参数
		String title = null;
		String readflag = null;
		int pageSize = rows == null ? 20 : rows.intValue();
		int pageIndex = (page == null || page == 0) ? 0 : page.intValue() - 1;

		//中文转码
		searchString = YunforgeUtils.getString(searchString, "iso-8859-1", "utf-8");
		
		//获取查询字符串
		if("readflag".equals(searchField) && YunforgeUtils.isEmpty(searchString)) {
			searchString = "1";
		}
		if("title".equals(searchField)) {
			title = searchString;
		}
		if("readflag".equals(searchField)) {
			readflag = searchString;
		}

		//获取当前登陆用户信息
		Subject currentUser = SecurityUtils.getSubject();
		User user = userManager.findUserByUsername(currentUser.getPrincipal().toString());
		Person person = personManager.findById(user.getPerson().getId());
		Org org = orgManager.findById(person.getOrg().getId());
		String divcode = org.getDivCode();

		//封装查询条件，进行查询
		Params params = new Params();
		params.put("pageIndex", pageIndex+1 + "");
		params.put("pageSize", pageSize);
		params.put("sidx", sidx);
		params.put("sord", sord);
		params.put("title", title);
		params.put("readflag", readflag);
		params.put("status", Notice.STATUS_PUBLISH.toString());
		params.put("divcode", divcode);
		
		GridBean grid1 = noticeManager.queryViewList(params);
		return grid1;
	}
	
	/**
	 * 查看发布的通知信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/office/notice/list")
	public @ResponseBody GridBean<Notice> listNotices(HttpServletRequest request) {
		
//		String str = vAgrimsNewestRecordsManager.getNewestRecords("ZQTJB", "450122", "RA0104006");
//		System.out.println(str);
		
		//封装查询条件
		Integer page = StringUtil.getInt(request.getParameter("page"), 0);
		Integer rows = StringUtil.getInt(request.getParameter("rows"), 20);
		String sidx = request.getParameter("sidx");
		String sord = request.getParameter("sord");
		String searchOper = request.getParameter("searchOper");
		String searchField = request.getParameter("searchField");
		String searchString = request.getParameter("searchString");
		
		int pageSize = rows == null ? 20 : rows.intValue();
		int pageIndex = (page == null || page == 0) ? 0 : page.intValue() - 1;
		
		if("status".equals(searchField) && StringUtil.isEmpty(searchString)) {
			searchString = "1";
		}

		//查询当前登陆用户
		Subject currentUser = SecurityUtils.getSubject();
		User user = userManager.findUserByUsername(currentUser.getPrincipal().toString());
		Person person = personManager.findById(user.getPerson().getId());
		Org org = orgManager.findById(person.getOrg().getId());
		String divcode = org.getDivCode();

		//查询
		Sort sort = StringUtil.createSort(sidx, sord, "createdate");
		Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
		GridBean grid = noticeManager.listNotices(searchField, searchOper, searchString, pageable, page, divcode);
		
		return grid;
	}
	
	
}