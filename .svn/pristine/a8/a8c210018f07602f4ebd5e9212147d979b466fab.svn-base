package com.yunforge.collect.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunforge.collect.dto.MessageObject;
import com.yunforge.collect.dto.TreeNode2;
import com.yunforge.collect.jackson.mixin.AgrProCategory2Mixin;
import com.yunforge.collect.model.AgrProCategory2;
import com.yunforge.collect.service.AgrProCategory2Manager;

@Controller
@RequestMapping("/collect")
public class AgrProCategoryController {
	
	@Autowired
	private AgrProCategory2Manager agrProCategory2Manager;

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	/**************** view controller*******************/
	@RequestMapping(value="agrProCtg/manager")
	public String managerCtg( ModelMap model) {
		int type = 1;
		model.addAttribute("type", type);
		return "collect/agrProCategory/categoryMgr";
	}
	
	@RequestMapping(value="/agrProCtg/view/new", params="pid", method=RequestMethod.GET)
	public String newAgrProCtgView(@RequestParam String pid, ModelMap model) {
		AgrProCategory2 apc = agrProCategory2Manager.getAgrProCategory2(pid);
		model.addAttribute("pid", pid);
		model.addAttribute("apcName", apc.getName());
		return "collect/agrProCategory/categoryForm";
	}
	
	@RequestMapping(value="/agrProCtg/view/edit", params="id", method=RequestMethod.GET)
	public String editAgrProCtgView(@RequestParam String id, ModelMap model) {
		AgrProCategory2 apc = agrProCategory2Manager.getAgrProCategory2(id);
		model.addAttribute("pid", apc.getParent().getId());
		model.addAttribute("apcName", apc.getParent().getName());
		model.addAttribute("apc", apc);
		return "collect/agrProCategory/categoryForm";
	}
	
	/**************** data controller*******************/
	
	//action get all agrProCtg
	@RequestMapping(value="/agrProCtg", params="pid", produces="application/json; charset=utf-8")
	public @ResponseBody String getChildrenCategories(@RequestParam("pid") String pid) throws JsonProcessingException {
	  	objectMapper.addMixInAnnotations(AgrProCategory2.class, AgrProCategory2Mixin.BasicInfo.class);
		List<AgrProCategory2> bdclist = agrProCategory2Manager.getChildrenAgrProCategory2(pid, null);
		bdclist = agrProCategory2Manager.setNodeType(bdclist);
		return objectMapper.writeValueAsString(bdclist);
	}
	
	//action new edit
	@RequestMapping(value="/agrProCtg", method=RequestMethod.POST, produces="application/json; charset=utf-8")
	@ResponseBody
	public String saveAgrProCtg(@RequestBody AgrProCategory2 agrProCategory2) throws JsonProcessingException {
		objectMapper.addMixInAnnotations(AgrProCategory2.class, AgrProCategory2Mixin.BasicInfo.class);
		MessageObject messageObject = new MessageObject();
		AgrProCategory2 data = null;
		JSONObject extra = new JSONObject();
		String id = agrProCategory2.getId();
		try {
			if (id != null) {
				//编辑
				if (id.equals(agrProCategory2.getParent().getId())) {
					messageObject.setMessage("不能选择自己作为自己的上级分类!");
					return objectMapper.writeValueAsString(messageObject);
				}
				AgrProCategory2 oldAgrProCategory2 = agrProCategory2Manager.getAgrProCategory2(id);
				if (oldAgrProCategory2.getState() != agrProCategory2.getState()) {
					agrProCategory2Manager.batchEnableOrDisable(id, agrProCategory2.getState());
				}
			}
			data = agrProCategory2Manager.newAgrProCategory2(agrProCategory2);		
			messageObject.setMessage("保存成功!");
			messageObject.setData(data);
			extra.put("pid", data.getParent().getId());
			//data.clearRelation();
			messageObject.setExtra(extra);
		} catch (Exception e) {
			e.printStackTrace();
			messageObject.setMessage("保存失败!" + e.getMessage());
		}
		return objectMapper.writeValueAsString(messageObject);
	}
	
	//得到所有子分类
	@RequestMapping(value="/agrProCtgsTreeNode/all", params="pid")
	public @ResponseBody List<TreeNode2> getChildrenCategoriesTreeNodeAll(@RequestParam("pid") String pid)
			throws JsonProcessingException {
		return agrProCategory2Manager.getChildrenAgrProCategory2TreeNode(pid, null, null);
	}
	
	//删除
	@RequestMapping(value="/agrProCtg/{id}", params="del", method=RequestMethod.POST)
	@ResponseBody
	public MessageObject delBasicDataCategory(@PathVariable String id) {
		MessageObject messageObject = new MessageObject();
		try {
			agrProCategory2Manager.delAgrProCategory2(id);
			messageObject.setMessage("删除成功!");
			messageObject.setStatus(true);
		} catch (Exception e) {
			// TODO: handle exception
			messageObject.setMessage(e.getMessage());
			messageObject.setStatus(false);
		}
		return messageObject;
	}
	
	//名称模糊查询
	@RequestMapping(value="/agrProCtg", params="name", produces="application/json; charset=utf-8")
	public @ResponseBody String searchCategories(@RequestParam("name") String name) throws JsonProcessingException {
		objectMapper.addMixInAnnotations(AgrProCategory2.class, AgrProCategory2Mixin.treeInfo.class);
		List<AgrProCategory2> apclist = agrProCategory2Manager.searchAgrProCategory2ByName(name);
		apclist = agrProCategory2Manager.setNodeType(apclist);
		return objectMapper.writeValueAsString(apclist);
	}

	//判断是否是最底层
	@RequestMapping(value="/agrProCtg/checkLevel")
	@ResponseBody
	public MessageObject delBasicDataCategory(HttpServletRequest request) {
		MessageObject messageObject = new MessageObject();
		try {
			boolean isBottomLevel = agrProCategory2Manager.isBottomLevel(request.getParameter("id"));
			messageObject.setData(isBottomLevel);
			messageObject.setMessage("操作成功！");
			messageObject.setStatus(true);
		} catch (Exception e) {
			// TODO: handle exception
			messageObject.setMessage(e.getMessage());
			messageObject.setStatus(false);
		}
		return messageObject;
	}
}
