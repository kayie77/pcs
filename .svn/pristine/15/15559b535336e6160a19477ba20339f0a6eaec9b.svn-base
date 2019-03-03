package com.yunforge.base.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunforge.base.model.Division;
import com.yunforge.base.model.Role;
import com.yunforge.base.model.User;
import com.yunforge.base.service.DivisionManager;
import com.yunforge.common.Constants;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.bean.TreeNode;
import com.yunforge.common.util.StringUtil;
import com.yunforge.common.util.WebUtils;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.web.controller.BaseController;

@Controller
public class DivisionController extends BaseController {
	final static Log log = LogFactory.getLog(DivisionController.class);

	@Autowired
	private DivisionManager divisionManager;

	@RequestMapping(value = "/division", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model) {
		model.addAttribute("actionUrl", request.getContextPath() + "/division/save");
		String pageSize = WebUtils.getParaValue("DEFAULT_PAGE_SIZE", request);
		model.addAttribute(Constants.PAGE_SIZE,
				pageSize == null ? 20 : Integer.valueOf(pageSize));
		return "/division/division";
	}

	@RequestMapping("/division/treeList")
	public @ResponseBody
	GridBean<Division> listDivTree(String nodeid, Integer level, boolean search,
			String searchField, String searchOper, String searchString) {
		List<Division> divs = new ArrayList<Division>();
		List<Division> divList = new ArrayList<Division>();
		if (level == null) {
			level = 0;
		}

		if (StringUtils.isNotBlank(nodeid)) {
			level = level + 1;
		}
		log.info("nodeid:" + nodeid);
		
		divs.addAll(divisionManager.getChildren(nodeid));
		for (Division div : divs) {
			Division parent = div.getParent();
			if (parent == null) {
				div.setPid(null);
			} else {
				div.setPid(parent.getId());
			}
			if (divisionManager.hasChildren(div.getId())) {
				div.setIsLeaf(Boolean.FALSE);
			}
			div.setLevel(level);
			div.setExpanded(Boolean.FALSE);
			divList.add(div);
		}

		GridBean<Division> grid = new GridBean<Division>();

		grid.setRecords(1);
		grid.setTotal(1);
		grid.setPage(1);
		grid.setRows(divList);
		return grid;
	}
	
	@MethodRemark(remark="根据某个角色用户过滤要显示的地区")
	@RequestMapping("/division/treeList1")
	public @ResponseBody
	GridBean<Division> listDivTree1(String nodeid, Integer level, boolean search,
			String searchField, String searchOper, String searchString) {
		List<Division> divs = new ArrayList<Division>();
		List<Division> divList = new ArrayList<Division>();
		if (level == null) {
			level = 0;
		}

		if (StringUtils.isNotBlank(nodeid)) {
			level = level + 1;
		}
		log.info("nodeid:" + nodeid);
		
		Subject currentUser=SecurityUtils.getSubject();
		User user=(User)currentUser.getSession().getAttribute(Constants.SESSION_USER_KEY);
		List<Role> userRoles=user.getRoles();
		List<String> userStrRoles=new ArrayList<String>();
		for(Role role:userRoles)
		{
			userStrRoles.add(role.getId());
		}
		
		divs.addAll(divisionManager.getChildrenPidRoles(nodeid,userStrRoles));
		for (Division div : divs) {
			Division parent = div.getParent();
			if (parent == null) {
				div.setPid(null);
			} else {
				div.setPid(parent.getId());
			}
			if (divisionManager.hasChildren(div.getId())) {
				div.setIsLeaf(Boolean.FALSE);
			}
			div.setLevel(level);
			div.setExpanded(Boolean.FALSE);
			divList.add(div);
		}

		GridBean<Division> grid = new GridBean<Division>();

		grid.setRecords(1);
		grid.setTotal(1);
		grid.setPage(1);
		grid.setRows(divList);
		return grid;
	}
	
	@RequestMapping("/division/input")
	public String doInput(String id, String pid, HttpServletRequest request,
			ModelMap model) {
		if (StringUtils.isNotBlank(id) && !id.equals("0")) {
			Division division = divisionManager.findById(id);
			model.addAttribute("division", division);
			model.addAttribute("oper", "edit");
		} else {
			Division division = new Division();
			if (pid != null) {
				Division parent = divisionManager.findById(pid);
				division.setParent(parent);
			}
			model.addAttribute("division", division);
			model.addAttribute("oper", "add");
		}
		return "/division/inputForm";
	}

	@RequestMapping("/division/view")
	public String doView(String id, ModelMap model) {
		Division division = divisionManager.findById(id);
		model.addAttribute("division", division);
		return "/division/viewForm";
	}

	@RequestMapping("/division/getNameByCode")
	public @ResponseBody JSONObject getNameByCode(HttpServletRequest request) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			
			String divCode = request.getParameter("divCode");
			Division division = divisionManager.findByDivisionCode(divCode);
			jsonObject.put("divName", division.getDivName());
			
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "操作失败!");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
			jsonObject.put("divName", "");
		}
		return jsonObject;
	}
	
	@RequestMapping("/division/update")
	public @ResponseBody
	JSONObject doUpdate(Division division, String oper,
			HttpServletRequest request, ModelMap model) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			if (oper.equalsIgnoreCase("add")) {
				division.setId(null);
				if (division.getParent().getId() == null) {
					division.setParent(null);
				}
				divisionManager.saveDivision(division);
				jsonObject.put("id", division.getId());
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "区划已保存!");
			} else if (oper.equalsIgnoreCase("edit")) {
				if (division.getParent().getId() == null) {
					division.setParent(null);
				}
				Division o = divisionManager.saveDivision(division);
				jsonObject.put("id", o.getId());
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "区划已更新!");
			} else {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "无效操作!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}

	@RequestMapping("/division/delete")
	public @ResponseBody
	JSONObject doDelete(String id) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			if (id != null) {
				Division division = divisionManager.findById(id);
				divisionManager.removeDivision(division);
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "区划已删除!");
			} else {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "无效操作!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");

		}
		return jsonObject;
	}

	@RequestMapping("/division/treeData")
	public @ResponseBody
	List<TreeNode> execute(String id) throws Exception {
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		if (StringUtils.isNotBlank(id) && !id.equals("#")) {
			nodes.addAll(getTree(id,false));
		} else {
			nodes.addAll(getTree(null,false));
		}
		return nodes;
	}
	
	@MethodRemark(remark="根据不同的处室来获取下级应有的地区用户")
	@RequestMapping("/division/treeData1")
	public @ResponseBody
	List<TreeNode> execute1(String id) throws Exception {
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		if (StringUtils.isNotBlank(id) && !id.equals("#")) {
			nodes.addAll(getTree1(id,false));
		} else {
			nodes.addAll(getTree1(null,false));
		}
		return nodes;
	}
	
	@RequestMapping("/division/treeData4ReportView")
	public @ResponseBody List<TreeNode> treeData4ReportView(HttpServletRequest request) throws Exception {
		String divId = request.getParameter("id");
		if(StringUtil.notEmpty(divId)) {
			divId = divId.replaceAll("#", "");
		}
		
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		if (StringUtil.notEmpty(divId)) {
			nodes.addAll(getTree(divId,true));
		} else {
			
			Subject currentUser = SecurityUtils.getSubject();
			User user = (User) currentUser.getSession().getAttribute(Constants.SESSION_USER_KEY);
			Division division = divisionManager.findByDivisionCode(user.getDivCode());
			
			nodes.addAll(getTree(division.getId(),true));
//			if(division.getParent() == null) {
//				nodes.addAll(getTree(null,false));
//			} else {
//				nodes.addAll(getTree(division.getId(),false));
//			}
		}
		return nodes;
	}

	@RequestMapping("/division/showTree")
	public String showTree(HttpServletRequest request) {

		return "/division/showTree";
	}
	
	
	@RequestMapping("/division/select")
	public String execute(String id, String valueId, ModelMap model) {
		model.addAttribute("id", id);
		model.addAttribute("valueId", valueId);
		return "/base/division/select";
	}

	private List<TreeNode> getTree(String pid,boolean includeSelf) throws Exception {
		String divId = null;
		String code = null;
		TreeNode node = null;
		List<Division> dataList = new ArrayList<Division>();
		List<TreeNode> list = new ArrayList<TreeNode>();
		
		Subject currentUser=SecurityUtils.getSubject();
		User user=(User)currentUser.getSession().getAttribute(Constants.SESSION_USER_KEY);
		List<Role> userRoles=user.getRoles();
		List<String> userStrRoles=new ArrayList<String>();
		for(Role role:userRoles)
		{
			userStrRoles.add(role.getId());
		}
		
		
		if(includeSelf) {
//			dataList.add(this.divisionManager.findById(pid));
		}
		dataList.addAll(this.divisionManager.getChildren(pid));//.getChildrenPidRoles(pid,userStrRoles));
		Map<String, Object> dataMap = null;
		for (Division division : dataList) {
			node = new TreeNode();
			divId = division.getId();
			code = division.getDivCode();
			node.setId(String.valueOf(divId));
			node.setText(division.getDivName() + " " + code);
			node.getState().setOpened(false);
			node.getState().setDisabled(false);
			if (division.getChildren().size() > 0) {
				node.setHasChildren(true);
			} else {
				node.setHasChildren(false);
			}
			dataMap = new HashMap<String, Object>();
			dataMap.put("code", code);
			dataMap.put("title", division.getDivName());
			node.setA_attr(dataMap);
			list.add(node);
			dataMap = null;
		}
		return list;
	}
	
	private List<TreeNode> getTree1(String pid,boolean includeSelf) throws Exception {
		String divId = null;
		String code = null;
		TreeNode node = null;
		List<Division> dataList = new ArrayList<Division>();
		List<TreeNode> list = new ArrayList<TreeNode>();
		
		Subject currentUser=SecurityUtils.getSubject();
		User user=(User)currentUser.getSession().getAttribute(Constants.SESSION_USER_KEY);
		List<Role> userRoles=user.getRoles();
		List<String> userStrRoles=new ArrayList<String>();
		for(Role role:userRoles)
		{
			userStrRoles.add(role.getId());
		}
		
		
		if(includeSelf) {
//			dataList.add(this.divisionManager.findById(pid));
		}
		dataList.addAll(this.divisionManager.getChildrenPidRoles(pid,userStrRoles));
		Map<String, Object> dataMap = null;
		for (Division division : dataList) {
			node = new TreeNode();
			divId = division.getId();
			code = division.getDivCode();
			node.setId(String.valueOf(divId));
			node.setText(division.getDivName() + " " + code);
			node.getState().setOpened(false);
			node.getState().setDisabled(false);
			if (division.getChildren().size() > 0) {
				node.setHasChildren(true);
			} else {
				node.setHasChildren(false);
			}
			dataMap = new HashMap<String, Object>();
			dataMap.put("code", code);
			dataMap.put("title", division.getDivName());
			node.setA_attr(dataMap);
			list.add(node);
			dataMap = null;
		}
		return list;
	}

}
