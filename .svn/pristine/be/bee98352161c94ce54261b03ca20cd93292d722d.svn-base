package com.yunforge.collect.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.yunforge.collect.dao.impl.DataCollectIndexDaoImpl;
import com.yunforge.collect.dto.MessageObject;
import com.yunforge.collect.jackson.mixin.DataReoprtedDetailMixin;
import com.yunforge.collect.model.DataCollectIndex;
import com.yunforge.collect.model.DataReoprtedDetail;
import com.yunforge.collect.model.DataReoprtedMain;
import com.yunforge.collect.model.TaskDetail;
import com.yunforge.collect.service.DataCollectIndexManager;
import com.yunforge.collect.service.DataReoprtedDetailManager;
import com.yunforge.collect.service.DataReoprtedMainManager;
import com.yunforge.collect.service.TaskMainManager;
import com.yunforge.collect.service.impl.DataReoprtedMainManagerImpl;
import com.yunforge.collect.util.CronUtil;
import com.yunforge.srpingside.Servlets;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/collect")
public class DataReoprtedDetailController {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired 
	private TaskMainManager taskMainManager;
	
	@Autowired 
	private DataReoprtedDetailManager dataReoprtedDetailManager;
	
	@Autowired
	private DataReoprtedMainManager dataReoprtedMainManager;
	
	@Autowired
	private DataCollectIndexManager dataCollectIndexManager;
	
	
	/**************** view controller*******************/	
	
	//管理界面(获取任务填报明细)
	@RequestMapping(value="/dataReoprtedDetail/manager",params="dmId", method=RequestMethod.GET)
	public String DataReoprtedDetailView(@RequestParam String dmId,ModelMap model) {//dmId填报主表id
		List<DataReoprtedDetail> drlist =  dataReoprtedDetailManager.getDataReoprtedDetailByDmId(dmId);
		List<DataCollectIndex> dclist =  dataCollectIndexManager.findAllOrderByIndexCode();
		DataReoprtedMain dataReoprtedMain = dataReoprtedMainManager.findDataReoprtedMainById(dmId);
		//指标code与name之间的转换
		dataReoprtedDetailManager.convertedIndexNameByCode(drlist,dclist);
		model.addAttribute("detailList", drlist);
		model.addAttribute("dmId", dmId);
		model.addAttribute("reportedStatus", dataReoprtedMain.getReportedStatus());
		return "collect/dataReoprtedDetail/manager";
	}
	
	//领取任务获得填报明细
	@RequestMapping(value="/dataReoprtedDetail/receive", method=RequestMethod.GET, produces="application/json; charset=utf-8")
	public @ResponseBody String receiveTaskDetailByDm(@RequestParam String dmId , ServletRequest request, Pageable pageable) throws JsonProcessingException{
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		objectMapper.addMixInAnnotations(DataReoprtedDetail.class, DataReoprtedDetailMixin.BasicInfo.class);
		Page<DataReoprtedDetail> page =  dataReoprtedDetailManager.receiveDataReoprtedDetailByDmId(dmId,searchParams,pageable);
		try {
		   return objectMapper.writeValueAsString(page);
		} catch (JsonProcessingException e) {
		   e.printStackTrace();
		}
		return null;
	}

	//获取任务填报明细
/*	@RequestMapping(value="/dataReoprtedDetail", method=RequestMethod.GET, produces="application/json; charset=utf-8")
	public @ResponseBody String getTaskDetailByDm(@RequestParam String dmId , ServletRequest request, Pageable pageable) throws JsonProcessingException{
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		objectMapper.addMixInAnnotations(DataReoprtedDetail.class, DataReoprtedDetailMixin.BasicInfo.class);
		Page<DataReoprtedDetail> page =  dataReoprtedDetailManager.getDataReoprtedDetailByDmId(dmId,searchParams,pageable);
		List<DataReoprtedDetail> detailList=page.getContent();
		List<DataReoprtedDetail> list=new ArrayList<DataReoprtedDetail>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		try {
			date = sdf.parse(sdf.format(date));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		for (DataReoprtedDetail d : detailList) {
			DataReoprtedDetail temp=new DataReoprtedDetail();
			TaskDetail td=d.getTaskDetail();
			BeanUtils.copyProperties(d, temp);
			temp.setEditable(1);
			list.add(temp);
		}
		Page<DataReoprtedDetail> page2=new PageImpl<DataReoprtedDetail>(list, pageable, page.getTotalPages());
		try {
		   return objectMapper.writeValueAsString(page2);
		} catch (JsonProcessingException e) {
		   e.printStackTrace();
		}
		return null;
	}*/
	
	
	//编辑并且填报数据值
/*	@RequestMapping(value="/dataReoprtedDetail", params={"id","data"}, method=RequestMethod.POST)
	@ResponseBody
	public MessageObject saveTaskDetail(@RequestParam("id") String[] id,@RequestParam("data") String[] data) {
		MessageObject messageObject = new MessageObject();
		try {
			dataReoprtedDetailManager.saveReoprtedDetailData(id, data);
			messageObject.setMessage("修改成功!");
			messageObject.setStatus(true);
		} catch (Exception e) {
			// TODO: handle exception
			messageObject.setMessage("修改失败!" + e.getMessage());
			messageObject.setStatus(false);
		}
		return messageObject;
	}*/
	
	@RequestMapping(value="/dataReoprtedDetail", method=RequestMethod.POST)
	@ResponseBody
	public MessageObject saveTaskDetail(HttpServletRequest request) {

/*		String a = data.replace('[', '{');
		String b =a.replace(']', '}');
		String a = data.substring(1,data.length()-1);

	    Gson gson = new Gson();
	    String str = gson.toJson(data);
	    String azz = str.substring(1,str.length()-1);*/
		String data = request.getParameter("data");
	    JSONArray jsonArr = JSONArray.fromObject(data);
	    
        String ids[] = new String[jsonArr.size()];
        String datas[] = new String[jsonArr.size()];

        for (int i = 0; i < jsonArr.size(); i++) {
        	ids[i] = jsonArr.getJSONObject(i).getString("id");
        	datas[i] = jsonArr.getJSONObject(i).getString("data");
        }

		MessageObject messageObject = new MessageObject();
		try {
			dataReoprtedDetailManager.saveReoprtedDetailData(ids, datas);
			messageObject.setMessage("修改成功!");
			messageObject.setStatus(true);
		} catch (Exception e) {
			// TODO: handle exception
			messageObject.setMessage("修改失败!" + e.getMessage());
			messageObject.setStatus(false);
		}
		return messageObject;
	}
	
}
