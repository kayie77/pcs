import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yunforge.base.model.User;
import com.yunforge.collect.jackson.mixin.DataReoprtedMainMixin;
import com.yunforge.collect.model.DataCollectPerson;
import com.yunforge.collect.model.DataCollectPoint;
import com.yunforge.collect.model.DataReoprtedMain;
import com.yunforge.collect.model.TaskMain;
import com.yunforge.common.Constants;
import com.yunforge.common.util.DateUtil;
import com.yunforge.srpingside.Servlets;

	//采集人员：点击“我的任务”
	@MethodRemark(remark = "访问 我的任务")
	@RequestMapping(value = "/dataReoprtedMain/myTask", method = RequestMethod.GET)
	public String index(HttpServletRequest request, ModelMap model,Pageable pageable) {
		Subject currentUser=SecurityUtils.getSubject();
		User user=(User)currentUser.getSession().getAttribute(Constants.SESSION_USER_KEY);
		Person person=user.getPerson();
		String personId=person.getId();
		Map<String, String[]> params=new HashMap<String, String[]>();
		params.putAll(request.getParameterMap());
		params.put("person",new String[] {person.getId()});
		params.put("taskMain",new String[] {"b4190e71-2957-4d1f-9f1e-d97f72b8eea7"});
		// page=taskMainManager.getTaskMainByJPA(params, pageable);
		
		int total = taskMainManager.getSelfTaskMainCount(personId);
		
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		objectMapper.addMixInAnnotations(TaskMain.class, TaskMainMixin.BasicInfo.class);
		//Page<DataReoprtedMain> taskPage =  dataReoprtedMainManager.getDataReoprtedMain(searchParams, pageable);

		List<TaskMain> taskClass= taskMainDao.findClassTaskMain(personId);
		System.out.println(taskClass.size());
		List<DataReoprtedMain> taskList= dataReoprtedMainManager.getReoprteMainByMultParam(personId,"1");
		//Page<DataReoprtedMain> taskPage =  dataReoprtedMainManager.getTaskByJPA(params, pageable);
		//List<DataReoprtedMain> taskList=taskPage.getContent();
		model.addAttribute("taskClass",taskClass);
		model.addAttribute("taskList",taskList);
		model.addAttribute("total",total);
		
		return "/collect/dataReoprtedMain/myTask";
	}
	
	
	// 填报人员更具personID获取采集点任务
	@RequestMapping(value="/myTaskMain", method=RequestMethod.GET, produces="application/json; charset=utf-8")
	public @ResponseBody String getMyTaskMain(ServletRequest request, Pageable pageable) throws JsonProcessingException{
		
		Subject currentUser=SecurityUtils.getSubject();
		User user=(User)currentUser.getSession().getAttribute(Constants.SESSION_USER_KEY);
		String personId=user.getPerson().getId();
		
		//根据采集点+当前日期获取 ->任务DataReoprtedMain
		Date date=DateUtil.getFormatDate(new Date(), "yyyy-MM-dd");
		String executeType=request.getParameter("executeType");
		String  period="";
		if(executeType.equals("1")){//天
			period=DateUtil.formatDate(date, "yyyyMMdd");
		}else if(executeType.equals("2")){//周
			Calendar cal = Calendar.getInstance();
			Format f = new DecimalFormat("000");
			cal.setTime(date);
			period=String.valueOf(DateUtil.getYear(date))+f.format(cal.get(Calendar.WEEK_OF_YEAR));
		}else if(executeType.equals("3")){//月
			period=DateUtil.formatDate(date, "yyyyMM");
		}else if(executeType.equals("4")){//季度
			
		}
		List<DataReoprtedMain> reoprtedMains=dataReoprtedMainManager.getReoprteMainByPersonDate(user.getPerson(),period);
		Page<TaskMain> page=null;
		if(reoprtedMains.size()<1){//未领取任务
			//1、根据用户获取定义任务
			DataCollectPoint point=dataCollectPointManager.getPointByPersonDate(user.getPerson(),period);
			if(point==null){
				System.out.println("该采集点无任务");
				return null;
			}
			Map<String, String[]> params=new HashMap<String, String[]>();
			params.putAll(request.getParameterMap());
			params.put("dataCollectCategory",new String[] {point.getDataCollectCategory().getId()});
			 page=taskMainManager.getTaskMainByJPA(params, pageable);
			List<TaskMain> tsakMainList=  page.getContent();
			DataCollectPerson person = dcpManager.getDataCollectPerson(personId);
			 for (int i = 0; i < tsakMainList.size(); i++) {
				 DataReoprtedMain reoprtMian=new DataReoprtedMain();
				 	TaskMain tm=tsakMainList.get(i);
				 	reoprtMian.setPeriod(Integer.parseInt(period));
					reoprtMian.setTaskMain(tm);
					reoprtMian.setPerson(person);
					reoprtMian.setTaskName(tm.getName());
					reoprtMian.setRemark(tm.getRemark());
					reoprtMian.setDescription(tm.getDescription());
					reoprtMian.setExecuteType(tm.getExecuteType());
					reoprtMian.setReceiveStatus(1);//任务领取状态
					reoprtMian.setReportedStatus(0);//未上报状态
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
					reoprtMian.setReceiveTime(df.format(new Date()));// new Date()为获取当前系统时间
					dataReoprtedMainManager.saveDataReoprtedMain(reoprtMian);
					//生成该任务明细
					Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
					dataReoprtedDetailManager.receiveDataReoprtedDetailByDmId(reoprtMian.getId(),searchParams,pageable);
					
					reoprtedMains.add(reoprtMian);
			}
			 
		}
		//DataCollectCategory category=myPoint.getDataCollectCategory();//采集类型
		objectMapper.addMixInAnnotations(DataReoprtedMain.class, DataReoprtedMainMixin.BasicInfo.class);
		Page<DataReoprtedMain> reslut=new PageImpl<DataReoprtedMain>(reoprtedMains);
		try {
			return objectMapper.writeValueAsString(reslut);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	