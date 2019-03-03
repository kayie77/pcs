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

import com.yunforge.base.model.Dict;
import com.yunforge.base.model.DictType;
import com.yunforge.base.model.DictVal;
import com.yunforge.base.model.Parameter;
import com.yunforge.base.service.DictManager;
import com.yunforge.base.service.DictTypeManager;
import com.yunforge.common.bean.GridBean;
import com.yunforge.common.bean.TreeNode;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.web.controller.BaseController;

@Controller
public class DictController extends BaseController {
	final static Log log = LogFactory.getLog(DictController.class);

	@Autowired
	private DictTypeManager dictTypeManager;

	@Autowired
	private DictManager dictManager;
	

	@RequestMapping("/dict")
	public String index(HttpServletRequest request, ModelMap model) {
		return "/dict/dict";
	}

	@RequestMapping(value = "/dict/listDictVals")
	public @ResponseBody
	GridBean<DictVal> listDictVals(String dictId,Integer page, Integer rows, String sidx,
			String sord, boolean search, String searchField, String searchOper,
			String searchString) {

		int pageSize = rows == null ? 20 : rows.intValue(); // 每页行数
		int pageIndex = (page == null||page==0) ? 0 : page.intValue() - 1;// 当前页数
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
		Page<DictVal> dictVals = dictManager.listDictVals(dictId,searchField, searchOper,
				searchString, pageable);

		List<DictVal> valList = new ArrayList<DictVal>();
		valList.addAll(dictVals.getContent());
		Integer records = (int) dictVals.getTotalElements();

		Integer totalPages = dictVals.getTotalPages();
		Integer currPage = Math.min(totalPages, page);

		GridBean<DictVal> grid = new GridBean<DictVal>();

		grid.setRecords(records);
		grid.setTotal(totalPages);
		grid.setPage(currPage);
		grid.setRows(valList);
		return grid;
	}
	
	
	@RequestMapping(value="/dict/select/{dictId}", method = RequestMethod.GET)
	public String selectItem(@PathVariable String dictId, ModelMap model) {
		List<DictVal> valList=dictManager.findDictValsByDictId(dictId);
		model.addAttribute("valList", valList);
		return "/dict/dictVal";
	}

	@RequestMapping("/dict/tree")
	public @ResponseBody
	List<TreeNode> dictTree(String id,HttpServletRequest request, ModelMap model) throws Exception {
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		List<DictType> typeList = dictTypeManager.findAll();
		model.addAttribute("dictTypes", typeList);
		log.info("id值:"+id);
		if (StringUtils.isNotBlank(id) && !id.equals("#")) {
			nodes.addAll(getTree(id));
		} else {
			nodes.addAll(getTree(null));
		}
		return nodes;
	}

	private List<TreeNode> getTree(String typeId) throws Exception {
		String id = null;
		String code = null;
		TreeNode node = null;
		List<DictType> typeList = new ArrayList<DictType>();
		List<Dict> dictList = new ArrayList<Dict>();
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
        if(typeId==null){
        	typeList.addAll(dictTypeManager.findAll());
        	Map<String, Object> dataMap = null;
    		for (DictType type : typeList) {
    			node = new TreeNode();
    			id = type.getId();
    			code = type.getTypeCode();
    			node.setId(String.valueOf(id));
    			node.setText(type.getTypeName());
    			node.getState().setOpened(false);
    			node.getState().setDisabled(false);
    			node.setHasChildren(true);
    			
    			dataMap = new HashMap<String, Object>();
    			dataMap.put("code", code);
    			dataMap.put("type", "0");
    			node.setA_attr(dataMap);
    			nodeList.add(node);
    			dataMap = null;
    		}
        }else{
        	dictList.addAll(dictManager.findDictsByDictType(typeId));
        	Map<String, Object> dataMap = null;
    		for (Dict dict : dictList) {
    			node = new TreeNode();
    			id = dict.getId();
    			code = dict.getDictCode();
    			node.setId(String.valueOf(id));
    			node.setText(dict.getDictName());
    			node.getState().setOpened(false);
    			node.getState().setDisabled(false);
    			node.setHasChildren(false);
    			
    			dataMap = new HashMap<String, Object>();
    			dataMap.put("code", code);
    			dataMap.put("type", "1");
    			node.setA_attr(dataMap);
    			nodeList.add(node);
    			dataMap = null;
    		}
        }
		return nodeList;
	}
	
	@MethodRemark(remark = "查看代码集值信息")
	@RequestMapping(value = "/dictVal/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable String id, HttpServletRequest request,
			ModelMap model) {
		DictVal dictVal = dictManager.findDictValById(id);
		model.addAttribute("dictVal", dictVal);
		model.addAttribute("actionUrl", request.getContextPath() + "/dictVal/save");
		return "/dict/dictValViewForm";
	}
	
	@MethodRemark(remark = "新建代码集值")
	@RequestMapping(value = "/dictVal/new/{dictId}", method = RequestMethod.GET)
	public String create(@PathVariable String dictId,HttpServletRequest request, ModelMap model) {
		DictVal dictVal = new DictVal();
		Dict dict = dictManager.findDictById(dictId);
		dictVal.setDict(dict);
		model.addAttribute("dictVal", dictVal);
		model.addAttribute("oper", "add");
		model.addAttribute("actionUrl", request.getContextPath() + "/dictVal/save");

		return "/dict/dictValForm";
	}

	@MethodRemark(remark = "编辑代码集值信息")
	@RequestMapping(value = "/dictVal/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, HttpServletRequest request,
			ModelMap model) {
		DictVal dictVal = dictManager.findDictValById(id);
		model.addAttribute("dictVal", dictVal);
		model.addAttribute("oper", "edit");
		model.addAttribute("actionUrl", request.getContextPath() + "/dictVal/save");
		return "/dict/dictValForm";
	}
	
	@MethodRemark(remark = "删除代码集值信息")
	@RequestMapping(value = "/dictVal/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	JSONObject  delte(@PathVariable String id, HttpServletRequest request,ModelMap model) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			DictVal dictVal = dictManager.findDictValById(id);
			dictManager.removeDictVal(dictVal);
			jsonObject.put("success", Boolean.TRUE);
			jsonObject.put("message", "代码集值已删除!");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("success", Boolean.FALSE);
			jsonObject.put("message", "操作失败!");
		}
		return jsonObject;
	}
	

	@RequestMapping(value = "/dictVal/save", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject doUpdate(@ModelAttribute("DictVal") DictVal dictVal, String oper) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			if (oper.equalsIgnoreCase("add")) {
				dictVal.setId(null);
				dictManager.saveDictVal(dictVal);
				jsonObject.put("id", dictVal.getId());
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "代码集值已保存!");
			} else if (oper.equalsIgnoreCase("edit")) {
				DictVal d = dictManager.saveDictVal(dictVal);
				jsonObject.put("id", d.getId());
				jsonObject.put("success", Boolean.TRUE);
				jsonObject.put("message", "代码集值已更新!");

			} 
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().indexOf("Error Code: 1062") != -1) {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "代码集值重复,请检查!");
			} else {
				jsonObject.put("success", Boolean.FALSE);
				jsonObject.put("message", "操作失败!");
			}
		}
		return jsonObject;
	}
	
}