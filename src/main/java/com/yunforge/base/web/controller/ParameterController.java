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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunforge.base.model.Parameter;
import com.yunforge.base.service.ParameterManager;
import com.yunforge.common.Constants;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.util.WebUtils;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.web.controller.BaseController;

@Controller
public class ParameterController extends BaseController {
	final static Log log = LogFactory.getLog(ParameterController.class);

	@Autowired
	private ParameterManager parameterManager;

	@RequestMapping("/parameter")
	public String index(HttpServletRequest request, ModelMap model) {
		model.addAttribute("actionUrl", request.getContextPath()
				+ "/parameter/update");
		String pageSize = WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE,
				pageSize == null ? 15 : Integer.valueOf(pageSize));
		return "/parameter/parameter";
	}

	@RequestMapping(value = "/parameter/list")
	public @ResponseBody
	GridBean<Parameter> listParameter(Integer page, Integer rows, String sidx,
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
		Page<Parameter> parameters = parameterManager.search(searchField,
				searchOper, searchString, pageable);

		List<Parameter> parameterList = new ArrayList<Parameter>();
		parameterList.addAll(parameters.getContent());
		Integer records = (int) parameters.getTotalElements();

		Integer totalPages = parameters.getTotalPages();
		Integer currPage = Math.min(totalPages, page);

		GridBean<Parameter> grid = new GridBean<Parameter>();

		grid.setRecords(records);
		grid.setTotal(totalPages);
		grid.setPage(currPage);
		grid.setRows(parameterList);
		return grid;
	}

	@MethodRemark(remark = "编辑参数信息")
	@RequestMapping(value = "/parameter/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, HttpServletRequest request,
			ModelMap model) {
		Parameter parameter = parameterManager.findParameterById(id);
		model.addAttribute("parameter", parameter);
		model.addAttribute("actionUrl", request.getContextPath()
				+ "/parameter/save");
		return "/parameter/parameterForm";
	}

	@RequestMapping(value = "/parameter/save", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject doUpdate(@ModelAttribute("Parameter") Parameter parameter,
			String oper) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			Parameter P = parameterManager.saveParameter(parameter);
			jsonObject.put("id", P.getId());
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "系统参数已更新!");

		} catch (Exception e) {
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");

		}
		return jsonObject;
	}
}
