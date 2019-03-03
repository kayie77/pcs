package com.yunforge.base.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunforge.base.model.Year;
import com.yunforge.base.service.YearManager;
import com.yunforge.common.Constants;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.util.WebUtils;
import com.yunforge.core.web.controller.BaseController;

@Controller
public class YearController extends BaseController {
	final static Log log = LogFactory.getLog(YearController.class);

	@Autowired
	private YearManager yearManager;

	@RequestMapping("/year")
	public String index(HttpServletRequest request, ModelMap model) {
		model.addAttribute("actionUrl", request.getContextPath()
				+ "/year/save");
		String pageSize= WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE,
				pageSize==null?20:Integer.valueOf(pageSize));
		return "/year/year";
	}

	@RequestMapping("/year/list")
	public @ResponseBody
	GridBean<Year> listYears(Integer page, Integer rows, String sidx,
			String sord, boolean search, String searchField, String searchOper,
			String searchString) {

		int pageSize = rows == null ? 20 : rows.intValue(); // 每页行数
		int pageIndex = page == null ? 0 : page.intValue() - 1;// 当前页数
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
			sort = new Sort(new Sort.Order(Sort.Direction.ASC, "id"));
		}

		Pageable pageable = new PageRequest(pageIndex, pageSize, sort);
		Page<Year> years = yearManager.listYears(searchField, searchOper,
				searchString, pageable);

		List<Year> yearList = new ArrayList<Year>();
		yearList.addAll(years.getContent());
		Integer records = (int) years.getTotalElements();

		Integer totalPages = years.getTotalPages();
		Integer currPage = Math.min(totalPages, page);

		GridBean<Year> grid = new GridBean<Year>();

		grid.setRecords(records);
		grid.setTotal(totalPages);
		grid.setPage(currPage);
		grid.setRows(yearList);
		return grid;
	}

	@RequestMapping("/year/yearNumData")
	public @ResponseBody
	JSONArray listYearNums() {
		JSONArray jsonArray = new JSONArray();
		List<Year> yearList = this.yearManager.findActiveYearNums();
		jsonArray.addAll(yearList);
		return jsonArray;
	}

	
	@RequestMapping("/year/save")
	public @ResponseBody
	JSONObject doUpdate(String id, Year year, String oper) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			if (oper.equalsIgnoreCase("add")) {
				year.setId(null);
				yearManager.saveYear(year);
				jsonObject.put("id", year.getId());
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "年度信息已保存!");
			} else if (oper.equalsIgnoreCase("edit")) {
				Year y = yearManager.saveYear(year);
				jsonObject.put("id", y.getId());
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "年度信息已更新!");
			} else if (oper.equalsIgnoreCase("del")) {
				yearManager.removeYear(year);
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "年度信息已删除!");
			} else {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "无效操作!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().indexOf("Error Code: 1062") != -1) {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "年度重复,请检查!");
			} else {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "年度重复!");
			}
		}
		return jsonObject;
	}

}
