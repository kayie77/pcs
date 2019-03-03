package com.yunforge.base.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunforge.base.model.SysLog;
import com.yunforge.base.service.SysLogManager;
import com.yunforge.common.Constants;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.util.WebUtils;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.web.controller.BaseController;

@Controller
public class SysLogController extends BaseController {
	final static Log log = LogFactory.getLog(SysLogController.class);

	@Autowired
	private SysLogManager sysLogManager;

	@RequestMapping(value = "/log", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		model.addAttribute("actionUrl", request.getContextPath()
				+ "/log/delete");
		String pageSize = WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE,
				pageSize == null ? 15 : Integer.valueOf(pageSize));
		return "/log/log";
	}

	@RequestMapping(value = "/log/list", method = RequestMethod.GET)
	public @ResponseBody
	GridBean<SysLog> listLogs(Integer page, Integer rows, String sidx,
			String sord, boolean search, String searchField, String searchOper,
			String searchString) throws Exception {

		int pageSize = rows == null ? 15 : rows.intValue(); // 每页行数
		int pageIndex = (page == null || page == 0) ? 0 : page.intValue() - 1;// 当前页数
		Sort sort = null;
		if (sidx != null && !sidx.equals("")) {
			if (sord.equals("asc")) {
				// sort=new Sort(Direction.ASC,sidx);
				sort = new Sort(new Sort.Order(Sort.Direction.ASC, sidx));
			} else {
				// sort=new Sort(Direction.DESC,sidx);
				sort = new Sort(new Sort.Order(Sort.Direction.DESC, sidx));
			}
		} else {
			// sort=new Sort(Direction.ASC,"id");
			sort = new Sort(new Sort.Order(Sort.Direction.DESC, "operDate"));
		}

		Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
		Page<SysLog> logs = sysLogManager.listLogs(searchField, searchOper,
				searchString, pageable);

		List<SysLog> logList = new ArrayList<SysLog>();
		logList.addAll(logs.getContent());
		Integer records = (int) logs.getTotalElements();

		Integer totalPages = logs.getTotalPages();
		Integer currPage = Math.min(totalPages, page);

		GridBean<SysLog> grid = new GridBean<SysLog>();

		grid.setRecords(records);
		grid.setTotal(totalPages);
		grid.setPage(currPage);
		grid.setRows(logList);
		return grid;
	}

	@MethodRemark(remark = "删除日志信息")
	@RequestMapping(value = "/log/delete")
	public @ResponseBody
	JSONObject delete(String[] ids, HttpServletRequest request, ModelMap model)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			if (ids != null) {
				sysLogManager.removeLogs(ids);
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "日志信息已删除!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}
	
}
