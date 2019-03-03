package com.yunforge.cms.web.controller;

import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/analysis")
public class PcsAnalysisController {
	@RequestMapping("/{id}")
	public String getComponent(@PathVariable String id, Model model, ServletRequest request) {
		return "analysis/"+id;
	}
}
