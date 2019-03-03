package com.yunforge.api.cms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunforge.api.dto.ApiDTO;
import com.yunforge.collect.dto.MessageObject;
import com.yunforge.collect.jackson.mixin.DataReoprtedDetailMixin;
import com.yunforge.collect.jackson.mixin.DataReoprtedMainMixin;
import com.yunforge.collect.model.DataCollectIndex;
import com.yunforge.collect.model.DataReoprtedDetail;
import com.yunforge.collect.model.DataReoprtedMain;
import com.yunforge.collect.service.DataCollectIndexManager;
import com.yunforge.collect.service.DataReoprtedDetailManager;
import com.yunforge.collect.service.DataReoprtedMainManager;
import com.yunforge.core.annotation.MethodRemark;
import com.yunforge.core.web.controller.BaseController;

import net.sf.json.JSONArray;

@Controller
public class ReportedApiController extends BaseController  {
	
	final static Log log = LogFactory.getLog(ReportedApiController.class);
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired 
	private DataReoprtedDetailManager dataReoprtedDetailManager;
	
	@Autowired
	private DataCollectIndexManager dataCollectIndexManager;
	
	@Autowired
	private DataReoprtedMainManager dataReoprtedMainManager;
	
	//获取任务填报明细
	@MethodRemark(remark = "获取任务填报明细")
	@RequestMapping(value = "/apinew/cms/dataReoprtedDetail/manager",params="dmId", produces="application/json; charset=utf-8")
	public  @ResponseBody String DataReoprtedDetailView(@RequestParam String dmId, ModelMap model) {
		List<DataReoprtedDetail> drlist =  dataReoprtedDetailManager.getDataReoprtedDetailByDmId(dmId);
		List<DataCollectIndex> dclist =  dataCollectIndexManager.findAllOrderByIndexCode();
		objectMapper.addMixInAnnotations(DataReoprtedDetail.class, DataReoprtedDetailMixin.BasicInfo.class);
		//指标code与name之间的转换
		dataReoprtedDetailManager.convertedIndexNameByCode(drlist,dclist);
		//json转换
		ApiDTO apiDTO = new ApiDTO();
		apiDTO.setCode("1");
		apiDTO.setData(drlist);
		apiDTO.setMsg("操作成功！");
		
		try {
			return objectMapper.writeValueAsString(apiDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//保存任务填报明细
	@MethodRemark(remark = "保存任务填报明细")
	@RequestMapping(value = "/apinew/cms/dataReoprtedDetail", produces="application/json; charset=utf-8")
	public  @ResponseBody String DataReoprtedDetailView(HttpServletRequest request) {
		String data = request.getParameter("data");
	    JSONArray jsonArr = JSONArray.fromObject(data);
	    
        String ids[] = new String[jsonArr.size()];
        String datas[] = new String[jsonArr.size()];

        for (int i = 0; i < jsonArr.size(); i++) {
        	ids[i] = jsonArr.getJSONObject(i).getString("id");
        	datas[i] = jsonArr.getJSONObject(i).getString("data");
        }
		
		ApiDTO apiDTO = new ApiDTO();
		try {
			dataReoprtedDetailManager.saveReoprtedDetailData(ids, datas);
			apiDTO.setCode("1");
			apiDTO.setMsg("修改成功！");
		} catch (Exception e) {
			apiDTO.setCode("0");
			apiDTO.setMsg("修改失败！");
		}
		try {
			return objectMapper.writeValueAsString(apiDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//上报任务
	@MethodRemark(remark = "上报任务")
	@RequestMapping(value = "/apinew/cms/dataReoprtedMain/reoprted",params="dmId", produces="application/json; charset=utf-8")
	public  @ResponseBody String dataReoprtedMainReoprted(@RequestParam String dmId, ModelMap model) {
		ApiDTO apiDTO = new ApiDTO();
		try {
			DataReoprtedMain dataReoprtedMain=dataReoprtedMainManager.findDataReoprtedMainById(dmId);
			dataReoprtedMainManager.taskReported(dataReoprtedMain);
			apiDTO.setCode("1");
			apiDTO.setMsg("上报成功！");
		}catch (Exception e) {
			apiDTO.setCode("0");
			apiDTO.setMsg("上报失败！");
		}
		try {
			return objectMapper.writeValueAsString(apiDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//返回任务的状态信息
	@MethodRemark(remark = "返回任务的状态信息")
	@RequestMapping(value = "/apinew/cms/dataReoprtedMain/status",params="dmId", produces="application/json; charset=utf-8")
	public  @ResponseBody String getDataReoprtedMainStatus(@RequestParam String dmId, ModelMap model) {
		ApiDTO apiDTO = new ApiDTO();
		try {
			DataReoprtedMain dataReoprtedMain=dataReoprtedMainManager.findDataReoprtedMainById(dmId);
			int status = dataReoprtedMainManager.getDataReoprtedMainStatus(dataReoprtedMain);
			apiDTO.setData(status);
			apiDTO.setCode("1");
			apiDTO.setMsg("返回成功！");
		}catch (Exception e) {
			apiDTO.setCode("0");
			apiDTO.setMsg("返回失败！");
		}
		try {
			return objectMapper.writeValueAsString(apiDTO);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
