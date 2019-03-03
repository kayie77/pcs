package com.yunforge.collect.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.web.controller.BaseController;

/**
 * 分析页面控制器
 * 
 * @author helt
 * 2015-12
 */
@Controller
public class AnalysisController extends BaseController {
	final static Log log = LogFactory.getLog(AnalysisController.class);


	@MethodRemark(remark = "访问分析首页")
	@RequestMapping(value = "/collect/analysis/manager", method = RequestMethod.GET)
	public String manager(HttpServletRequest request, ModelMap model) {
		return "/collect/analysis/manager";
	}
}
