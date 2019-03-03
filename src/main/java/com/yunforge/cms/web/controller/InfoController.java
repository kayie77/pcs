package com.yunforge.cms.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.Parser;
import org.htmlparser.visitors.TextExtractingVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.yunforge.cms.model.Info;
import com.yunforge.cms.model.InfoType;
import com.yunforge.cms.service.InfoCatManager;
import com.yunforge.cms.service.InfoManager;
import com.yunforge.common.Constants;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.util.WebUtils;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.security.ShiroUser;
import com.yunforge.core.web.controller.BaseController;

@Controller
@SessionAttributes("info")
public class InfoController extends BaseController {
	final static Log log = LogFactory.getLog(InfoController.class);

	private final AtomicInteger atomicInteger = new AtomicInteger(0);
	@Autowired
	private InfoManager infoManager;

	@Autowired
	private InfoCatManager infoCatManager;

	@MethodRemark(remark = "访问我的信息首页")
	@RequestMapping(value = "/cms/info/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		String pageSize = WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE,
				pageSize == null ? 20 : Integer.valueOf(pageSize));
		return "/cms/info/index";
	}

	@MethodRemark(remark = "访问信息发布首页")
	@RequestMapping(value = "/cms/info/published", method = RequestMethod.GET)
	public String publishedIndex(HttpServletRequest request, ModelMap model) {
		String pageSize = WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE,
				pageSize == null ? 20 : Integer.valueOf(pageSize));
		return "/cms/info/published_index";
	}

	@MethodRemark(remark = "访问信息审核首页")
	@RequestMapping(value = "/cms/info/audit", method = RequestMethod.GET)
	public String auditIndex(HttpServletRequest request, ModelMap model) {
		String pageSize = WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE,
				pageSize == null ? 20 : Integer.valueOf(pageSize));
		return "/cms/info/audit_index";
	}

	@MethodRemark(remark = "访问信息管理首页")
	@RequestMapping(value = "/cms/info/admin", method = RequestMethod.GET)
	public String adminIndex(HttpServletRequest request, ModelMap model) {
		String pageSize = WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE,
				pageSize == null ? 20 : Integer.valueOf(pageSize));
		return "/cms/info/admin_index";
	}

	@MethodRemark(remark = "访问我的文件")
	@RequestMapping(value = "/cms/info/listPrivate")
	public @ResponseBody
	GridBean<Info> listMine(Integer page, Integer rows, String sidx,
			String sord, boolean search, String searchField, String searchOper,
			String searchString, String filters) {
		int pageSize = rows == null ? 20 : rows.intValue(); // 每页行数
		int pageIndex = (page == null || page == 0) ? 0 : page.intValue() - 1;// 当前页数
		Sort sort = null;
		if (sidx != null && !sidx.equals("")) {
			if (sord.equals("asc")) {
				sort = new Sort(new Sort.Order(Sort.Direction.ASC, sidx));
			} else {
				sort = new Sort(new Sort.Order(Sort.Direction.DESC, sidx));
			}
		} else {
			sort = new Sort(new Sort.Order(Sort.Direction.ASC, "createDate"));
		}

		Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
		Page<Info> infos = infoManager.findPrivate(filters, pageable);

		List<Info> infoList = new ArrayList<Info>();
		infoList.addAll(infos.getContent());
		Integer records = (int) infos.getTotalElements();

		Integer totalPages = infos.getTotalPages();
		Integer currPage = Math.min(totalPages, page);

		GridBean<Info> grid = new GridBean<Info>();

		grid.setRecords(records);
		grid.setTotal(totalPages);
		grid.setPage(currPage);
		grid.setRows(infoList);
		return grid;
	}

	@MethodRemark(remark = "访问发布文件")
	@RequestMapping(value = "/cms/info/listPublished")
	public @ResponseBody
	GridBean<Info> listPublished(Integer page, Integer rows, String sidx,
			String sord, boolean search, String searchField, String searchOper,
			String searchString, String filters) {
		int pageSize = rows == null ? 20 : rows.intValue(); // 每页行数
		int pageIndex = (page == null || page == 0) ? 0 : page.intValue() - 1;// 当前页数
		Sort sort = null;
		if (sidx != null && !sidx.equals("")) {
			if (sord.equals("asc")) {
				sort = new Sort(new Sort.Order(Sort.Direction.ASC, sidx));
			} else {
				sort = new Sort(new Sort.Order(Sort.Direction.DESC, sidx));
			}
		} else {
			sort = new Sort(new Sort.Order(Sort.Direction.DESC, "pubDate"));
		}

		Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
		Page<Info> infos = infoManager.findByStatus(
				new Object[] { Info.STATUS_ACCEPTED }, filters, pageable);

		List<Info> infoList = new ArrayList<Info>();
		infoList.addAll(infos.getContent());
		Integer records = (int) infos.getTotalElements();

		Integer totalPages = infos.getTotalPages();
		Integer currPage = Math.min(totalPages, page);

		GridBean<Info> grid = new GridBean<Info>();

		grid.setRecords(records);
		grid.setTotal(totalPages);
		grid.setPage(currPage);
		grid.setRows(infoList);
		return grid;
	}

	@MethodRemark(remark = "访问待审文件")
	@RequestMapping(value = "/cms/info/listAudit")
	public @ResponseBody
	GridBean<Info> listAudit(Integer page, Integer rows, String sidx,
			String sord, boolean search, String searchField, String searchOper,
			String searchString, String filters) {
		int pageSize = rows == null ? 20 : rows.intValue(); // 每页行数
		int pageIndex = (page == null || page == 0) ? 0 : page.intValue() - 1;// 当前页数
		Sort sort = null;
		if (sidx != null && !sidx.equals("")) {
			if (sord.equals("asc")) {
				sort = new Sort(new Sort.Order(Sort.Direction.ASC, sidx));
			} else {
				sort = new Sort(new Sort.Order(Sort.Direction.DESC, sidx));
			}
		} else {
			sort = new Sort(new Sort.Order(Sort.Direction.ASC, "createDate"));
		}

		Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
		Page<Info> infos = infoManager.findByStatus(
				new Object[] { Info.STATUS_AUDIT }, filters, pageable);

		List<Info> infoList = new ArrayList<Info>();
		infoList.addAll(infos.getContent());
		Integer records = (int) infos.getTotalElements();

		Integer totalPages = infos.getTotalPages();
		Integer currPage = Math.min(totalPages, page);

		GridBean<Info> grid = new GridBean<Info>();

		grid.setRecords(records);
		grid.setTotal(totalPages);
		grid.setPage(currPage);
		grid.setRows(infoList);
		return grid;
	}

	@MethodRemark(remark = "访问信息管理列表")
	@RequestMapping(value = "/cms/info/listAdmin")
	public @ResponseBody
	GridBean<Info> listAdmin(Integer page, Integer rows, String sidx,
			String sord, boolean search, String searchField, String searchOper,
			String searchString, String filters) {
		int pageSize = rows == null ? 20 : rows.intValue(); // 每页行数
		int pageIndex = (page == null || page == 0) ? 0 : page.intValue() - 1;// 当前页数
		Sort sort = null;
		if (sidx != null && !sidx.equals("")) {
			if (sord.equals("asc")) {
				sort = new Sort(new Sort.Order(Sort.Direction.ASC, sidx));
			} else {
				sort = new Sort(new Sort.Order(Sort.Direction.DESC, sidx));
			}
		} else {
			sort = new Sort(new Sort.Order(Sort.Direction.ASC, "createDate"));
		}

		Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
		Page<Info> infos = infoManager.findByStatus(new Object[] {
				Info.STATUS_AUDIT, Info.STATUS_ACCEPTED, Info.STATUS_REJECTED }, filters,
				pageable);

		List<Info> infoList = new ArrayList<Info>();
		infoList.addAll(infos.getContent());
		Integer records = (int) infos.getTotalElements();

		Integer totalPages = infos.getTotalPages();
		Integer currPage = Math.min(totalPages, page);

		GridBean<Info> grid = new GridBean<Info>();

		grid.setRecords(records);
		grid.setTotal(totalPages);
		grid.setPage(currPage);
		grid.setRows(infoList);
		return grid;
	}

	@MethodRemark(remark = "新建信息")
	@RequestMapping(value = "/cms/info/new", method = RequestMethod.GET)
	public String newInfo(HttpServletRequest request, ModelMap model) {
		ShiroUser user = super.getCurrentUser();
		Info info = new Info();
		info.setCreateDate(new Date());
		info.setStatus(0);
		info.setDeleted(Boolean.FALSE);
		info.setAuthor(user.getFullName());
		info.setAuthorId(user.getUid());
		info.setIp(request.getRemoteHost());
		final int weight = atomicInteger.getAndIncrement();
		info.setWeight(weight);
		info.setNotifyPub(Boolean.FALSE);
		InfoType infoType=new InfoType(Info.INFO_TYPE_INFO,null);
		info.setInfoType(infoType);
		info.setCommentCount(0);
		info.setCounter(0);
		info.setFavorite(0);
		info.setScore(new BigDecimal("0.00"));
		model.addAttribute("info", info);
		model.addAttribute("actionUrl", request.getContextPath()
				+ "/cms/info/save");
		return "/cms/info/infoEditForm";
	}

	@MethodRemark(remark = "编辑信息")
	@RequestMapping(value = "/cms/info/edit/{id}", method = RequestMethod.GET)
	public String editInfoForm(@PathVariable String id,
			HttpServletRequest request, ModelMap model) {
		Info info = infoManager.findInfoById(id);
		model.addAttribute("actionUrl", request.getContextPath()
				+ "/cms/info/update");
		model.addAttribute("info", info);
		return "/cms/info/infoEditForm";
	}

	@MethodRemark(remark = "查看信息")
	@RequestMapping(value = "/cms/info/view/{id}", method = RequestMethod.GET)
	public String viewInfoForm(@PathVariable String id,
			HttpServletRequest request, ModelMap model) {
		Info info = infoManager.findInfoById(id);
		model.addAttribute("actionUrl", "");
		model.addAttribute("info", info);
		return "/cms/info/infoViewForm";
	}

	@MethodRemark(remark = "审核信息")
	@RequestMapping(value = "/cms/info/audit/{id}", method = RequestMethod.GET)
	public String auditInfoForm(@PathVariable String id,
			HttpServletRequest request, ModelMap model) {
		Info info = infoManager.findInfoById(id);
		model.addAttribute("actionUrl", request.getContextPath()
				+ "/cms/info/update");
		model.addAttribute("info", info);
		return "/cms/info/infoAuditForm";
	}

	@MethodRemark(remark = "查看信息内容")
	@RequestMapping(value = "/cms/info/p/{id}", method = RequestMethod.GET)
	public String viewInfoContent(@PathVariable String id,
			HttpServletRequest request, ModelMap model) {
		Info info = infoManager.findInfoById(id);
		model.addAttribute("info", info);
		return "/cms/info/viewPage";
	}

	@MethodRemark(remark = "保存信息")
	@RequestMapping(value = "/cms/info/save", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject saveInfo(@ModelAttribute("info") Info info,
			HttpServletRequest request, ModelMap model, SessionStatus status)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			String message="信息息已保存!";
			info.setId(null);
			info.setApproverId(null);
			if (StringUtils.isBlank(info.getSummary())) {
				Parser parser = new Parser(info.getBody());
				TextExtractingVisitor visitor = new TextExtractingVisitor();
				parser.visitAllNodesWith(visitor);
				String textInPage = visitor.getExtractedText();
				info.setSummary(StringUtils.abbreviate(textInPage, 100));
			}
			if(info.getUrl()==null){
				info.setUrl("");
			}
			infoManager.saveInfo(info);
			jsonObject.put("success", Boolean.TRUE);
			if(info.getStatus()==1){
				message="信息息已提交审核!";
			}
			jsonObject.put("message", message);
			status.setComplete();
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}

	@MethodRemark(remark = "更新信息")
	@RequestMapping(value = "/cms/info/update", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject updateInfo(@ModelAttribute("info") Info info,
			HttpServletRequest request, ModelMap model, SessionStatus status)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			String message="信息息已更新!";
			ShiroUser user = super.getCurrentUser();
			if(info.getStatus()==1){
				message="信息息已提交审核!";
			}else if(info.getStatus()==2){
				info.setPubDate(new Date());
				info.setApproverId(user.getUid());
				message="信息已审核通过!";
			}else if(info.getStatus()==3){
				info.setApproverId(user.getUid());
				message="信息已驳回!";
			}
			if(info.getUrl()==null){
				info.setUrl("");
			}
			if (StringUtils.isBlank(info.getSummary())) {
				Parser parser = new Parser(info.getBody());
				TextExtractingVisitor visitor = new TextExtractingVisitor();
				parser.visitAllNodesWith(visitor);
				String textInPage = visitor.getExtractedText();
				info.setSummary(StringUtils.abbreviate(textInPage, 100));
			}
			infoManager.saveInfo(info);
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", message);
			status.setComplete();
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}

	@MethodRemark(remark = "删除信息")
	@RequestMapping(value = "/cms/info/delete/{id}", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject deleteInfo(@PathVariable String id,
			HttpServletRequest request, ModelMap model) {
		JSONObject jsonObject = new JSONObject();
		try {
			if (id != null) {
				infoManager.removeInfos(new String[] { id });
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "信息已删除!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}

	@MethodRemark(remark = "删除信息")
	@RequestMapping(value = "/cms/info/delete")
	public @ResponseBody
	JSONObject deleteInfos(String[] ids, HttpServletRequest request,
			ModelMap model) {
		JSONObject jsonObject = new JSONObject();
		try {
			if (ids != null) {
				infoManager.removeInfos(ids);
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "信息已删除!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}

	@MethodRemark(remark = "恢复信息审核状态")
	@RequestMapping(value = "/cms/info/restore/{id}", method = RequestMethod.GET)
	public @ResponseBody
	JSONObject restoreInfo(@PathVariable String id,
			HttpServletRequest request, ModelMap model) {
		JSONObject jsonObject = new JSONObject();
		try {
			if (id != null) {
				Info info = infoManager.findInfoById(id);
				info.setStatus(Info.STATUS_AUDIT);
				info.setPubDate(null);
				infoManager.saveInfo(info);
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "信息审核状态已恢复!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}
}
