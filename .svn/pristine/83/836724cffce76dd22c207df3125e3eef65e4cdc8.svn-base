<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<style>
.agrims_ul{list-style:none;margin:0;padding:0;}
.agrims_ul li{list-style:none;margin:0;padding:0;}
.agrims_divbox{border: 1px #CCC solid;width:264px;height:300px;overflow:scroll;}
.agrims_buttonbox{height:300px;text-align: center;padding-top:30px;}
.agrims_buttonbox button label{width:60px;}
</style>
<script src="${ctx}/assets/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">


var scripts = [null,"${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js", null]
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
});


var reportIds = $("#reportIds").val();
var reportIdArr = new Array();
if(reportIds!=""){
	reportIdArr = reportIds.split(",");
}
function saveButton() {

	var myDate = new Date();
	var start = myDate.getTime();
	var taskName = $("#taskName").val();
	var taskNo = $("#taskNo").val();
	var taskNum = $("#taskNum").val();
	var endTime = $('#endTime').val();
	var end = new Date(Date.parse(endTime.replace(/-/g, "/"))); 
	
	if(endTime == '') {
		bootbox.alert("请选择上报日期！");
		return false;
	}
	
	if(taskName.length < 5) {
		bootbox.alert("任务名称至少5个字！");
		return false;
	}
	
	if(taskName.length > 50) {
		bootbox.alert("任务名称不能超过50个字！");
		return false;
	}
	
	if(end<start){
		//$('#endTime').val("");
		//bootbox.alert("“上报时间”不能早于“当前时间 ” ！");
		//return false;
	}

	var url = "${ctx}/office/task/hasSameTaskNo?taskNo=" + taskNo + "&taskNum=" +taskNum+ "&date=" + new Date().getTime();
	$.getJSON(url,function(data) {
		if(data.success) {
			$("#goToSaveButton")[0].style.display = "none";
			$("#taskForm").submit();
		} else {
			$("#goToSaveButton")[0].style.display = "";
			bootbox.alert(data.message);
		}
	});
	
}
function changeEndTime() {
	
	var endTime = $("#endTime")[0];
	var taskNo = $("#taskNo")[0];
	var taskNum = $("#taskNum")[0];
	
	var str = endTime.value.substring(0,10);
	taskNo.value = str.replace(/-/g,'');
	taskNum.value = taskNo.value;
}
$(function () {
	
	var myDate = new Date();
	var start = myDate.getTime();
	var endTime = $("#endTime")[0];
	var m = (myDate.getMonth()+1);
	var d = myDate.getDate();
	
	if(endTime.value == '') {
		endTime.value = myDate.getFullYear() + "-" + (m>10?m:"0"+m) + "-" + (d>10?d:"0"+d) + " 16:30:00";
	}
	/*
	$('#endTime').datepicker({
		format: 'yyyy-mm-dd',
		weekStart: 0,
		autoclose: true,
		todayBtn: 'linked',
		language: 'zh-CN'
	}).on('changeDate',function(ev){
		var end = ev.date.valueOf();
		if(end<start){
			$('#endTime').val("");
		   	alert("“上报时间”不能早于“当前时间 ” ！");
		}
	});
	*/
	
	$("#toBtn").bind("click", function () {
		var reportHtml = $("#taskReportUl").html();
        $(":checkbox[name = dicReportIds]:checked").each(function(){
        	var ckId = $(this).attr("id");
        	var reportName = $("#"+ckId+"Name").text();
        	var reportId = $(this).val();
        	if($("#"+reportId).length == 0){
        		reportIdArr.push(reportId);
        		reportHtml += "<li id='"+reportId+"'><input type='checkbox' id='taskReport"+reportId+"' name='reportIds' value='"+reportId+"'><span id='taskReport"+reportId+"Name'>"+reportName+"</span></li>";
        	}
        });
        $("#taskReportUl").html(reportHtml);
        var reportIdStr = reportIdArr.join(",");
        $("#reportIds").val(reportIdStr);
    });
	
	$("#toAllBtn").bind("click", function () {
		$("#taskReportUl").empty();
		var reportHtml = "";
        $(":checkbox[name = dicReportIds]").each(function(){
        	var ckId = $(this).attr("id");
        	var reportName = $("#"+ckId+"Name").text();
        	var reportId = $(this).val();
        	reportIdArr.push(reportId);
       		reportHtml += "<li id='"+reportId+"'><input type='checkbox' name='taskReportIds' value='"+reportId+"'>"+reportName+"</li>";
        });
        $("#taskReportUl").html(reportHtml);
        var reportIdStr = reportIdArr.join(",");
        $("#reportIds").val(reportIdStr);
    });
	
	$("#backBtn").bind("click", function () {
        $(":checkbox[name = reportIds]:checked").each(function(){
        	var reportId = $(this).val();
        	$("#"+reportId).remove();
        	for(var i=0;i<reportIdArr.length;++i){
        		if(reportId == reportIdArr[i]){
        			reportIdArr.splice(i,1);
        		}
        	}
        });
        var reportIdStr = reportIdArr.join(",");
        $("#reportIds").val(reportIdStr);
    });
	
	$("#backAllBtn").bind("click", function () {
		$("#taskReportUl").empty();
		$("#reportIds").val("");
    });
	
	
});

function insertAfter(newElement, targetElement){
	var parent = targetElement.parentNode;
	if (parent.lastChild == targetElement) {
	// 如果最后的节点是目标元素，则直接添加。因为默认是最后
	parent.appendChild(newElement);
	}
	else {
	parent.insertBefore(newElement, targetElement.nextSibling);
	//如果不是，则插入在目标元素的下一个兄弟节点 的前面。也就是目标元素的后面
	}
	}
	
//选中单位节点图片
function clickItem(img,id) {

	var div = $(img).parent();
	var nextDiv = $(div).next();

	//当前为打开，则执行关闭操作
	if(img.src.indexOf("branch_open") != -1) {

		$("li[id=" +id+ "]").each(function(i,ob) {
			$(ob).remove();
		});
		img.src = img.src.replace("branch_open","branch_close");

	//当前为关闭，则执行打开操作
	} else if(img.src.indexOf("branch_close") != -1) {

		var url = "${ctx}/report/getChildLevel?reportLevelId="+id+"&date=" + new Date().getTime();
		$.getJSON(url,function(data) {
			
			var str = "";
			var childReportList = data.childReportList;
			var childLevelList = data.childLevelList;
			var currentLevel = img.parentNode.getElementsByTagName("img").length - 1;
			
			for(var j = 0;j < childLevelList.length;j++) {
				
				//缩进图片
				var imageStr = "";
				for(var k = 0;k < currentLevel + 1;k++) {
					imageStr += "<img src='${ctx}/assets/images/tree/empty.png'>";
				}

				str += "<li id='"+id+"'>";
				str += "<span>"+imageStr+"<img src='${ctx}/assets/images/tree/branch_close.gif' style='cursor:pointer' onclick='clickItem(this,\""+childLevelList[j].id+"\")'/>" +childLevelList[j].name+ "</span>";
				str += "</li>";
			}

			for(var j = 0;j < childReportList.length;j++) {

				//缩进图片
				var imageStr = "";
				for(var k = 0;k < currentLevel + 1;k++) {
					imageStr += "<img src='${ctx}/assets/images/tree/empty.png'>";
				}
				
				str += "<li id='"+id+"'>";
				str += imageStr;
				str += "<input type='checkbox' id='dicReport"+childReportList[j].id+"' name='dicReportIds' value='"+childReportList[j].id+"'>";
				str += "<span id='dicReport"+childReportList[j].id+"Name' style='cursor:pointer;' onclick='openReportWindow(\""+childReportList[j].id+"\")'>" +childReportList[j].reportName+ "</span>";
				str += "</li>";
			}
			
			$(str).insertAfter($(img.parentNode.parentNode));
		});

		img.src = img.src.replace("branch_close","branch_open");
		
	//最低级单位，则不作操作
	} else if(img.src.indexOf("leaf") != -1) {
		
		
	} 
	
}
</script>
<div class="modal-dialog" style="width:850px;">
	<div class="modal-content">
      	<div class="modal-header no-padding">
        	 <div class="table-header">
				  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						<span class="white">&times;</span>
				  </button>
				  <i class="ace-icon glyphicon glyphicon-list"></i>
				  <b>任务信息</b>
			 </div>
      	</div>
      	<form:form cssClass="form-horizontal" id="taskForm" modelAttribute="task" action="${actionUrl}" method="POST">
      		<div class="modal-body">
      		<div class="row">
      			<div class="col-sm-12">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="taskNum">任务编号:<span class="required">*</span></label>
						<div class="col-sm-4 controls">
			    			<form:input  id="taskNum" cssStyle="width:250px" path="taskNum" value="${taskNumInit}" placeholder="任务编号..." required="required" />
						</div>
						<label class="col-sm-2 control-label" for="taskName" style="width:100px;">任务名称:<span class="required">*</span></label>
						<div class="col-sm-4 controls">
			    			<form:input  id="taskName" cssStyle="width:250px" path="taskName" placeholder="任务名称..." required="required" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="taskNo">任务期数:<span class="required">*</span></label>
						<div class="col-sm-4 controls">
			    			<form:input  id="taskNo" cssStyle="width:250px" path="taskNo" value="${taskNumInit}" placeholder="任务期数..." required="required" />
						</div>
						<%/*
						<label class="col-sm-2 control-label" for="taskHz">上报频率:<span class="required">*</span></label>
						<div class="col-sm-4 controls">
			    			<form:input  id="taskHz" path="taskHz" placeholder="上报频率..." required="required" />
						</div>
						*/%>
						<label class="col-sm-2 control-label" for="endTime" style="width:100px;">上报时间:<span class="required">*</span></label>
						<div class="col-sm-4 controls">
			    			<form:input  id="endTime" path="endTime" class="Wdate"  cssStyle="width:250px;height:35px;" placeholder="上报时间..." onChange="changeEndTime()" onFocus="WdatePicker({isShowClear:false,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readOnly="readOnly" required="required" />
						</div>
					</div>
					<!-- 
					<div class="form-group"></div>
					 -->
				</div>
				<div class="col-sm-12">
					<div class="form-group">
						<label class="col-sm-2 control-label" for="reports">选择报表:<span class="required">*</span></label>
						<div class="col-sm-4 agrims_divbox" style="width:300px">
							<ul class="agrims_ul" style="width:300px;">
								
								
								<c:forEach var="reportLevelItem" items="${reportLevelList }">
									<li>
										<span><img src="${ctx}/assets/images/tree/branch_close.gif" style="cursor:pointer" onclick="clickItem(this,'${reportLevelItem.id}')"/>${reportLevelItem.name}</span>
									</li>
									<%/*
									<c:forEach var="dicReport" items="${dicReports }">
										<c:if test="${dicReport.reportLevelId == reportLevelItem.id}">
											<li id="${reportLevelItem.id}" style="display:none">
												<img src="${ctx}/assets/images/tree/empty.png" />
												<input type="checkbox" id="dicReport${dicReport.id }" name="dicReportIds" value="${dicReport.id }">
												<span id="dicReport${dicReport.id }Name" style="cursor:pointer;" onclick="openReportWindow('${dicReport.id}')">${dicReport.reportName }</span>
											</li>
										</c:if>
									</c:forEach>
									*/%>
								</c:forEach>
								
							</ul>
						</div>
						<div class="col-sm-1 agrims_buttonbox">
							<div class="form-group" style="margin-top:60px;">
								<button type="button" id="toBtn" class="btn btn-info btn-blue btn-sm btn-round">&rArr;</button>
							</div>
							<div class="form-group" style="display:none">
								<button type="button" id="toAllBtn" class="btn btn-info btn-blue btn-sm btn-round">&gt;&gt;</button>
							</div>
							<div class="form-group" style="display:none">
								<label class="btn btn-info btn-blue btn-sm btn-round">&hArr;</label>
							</div>
							<div class="form-group">
								<button type="button" id="backBtn" class="btn btn-info btn-blue btn-sm btn-round">&lArr;</button>
							</div>
							<div class="form-group" style="display:none">
								<button type="button" id="backAllBtn" class="btn btn-info btn-blue btn-sm btn-round">&lt;&lt;</button>
							</div>
						</div>
						<div class="col-sm-4 agrims_divbox" style="width:300px">
							<ul class="agrims_ul" id="taskReportUl" style="width:300px;">
								<c:forEach var="taskReport" items="${taskReports }" varStatus="status">
									<li id="${taskReport.dicReport.id }">
										<input type="checkbox" name="taskReportIds" value="${taskReport.dicReport.id }">${taskReport.reportName }
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
			</div>
			<input type="hidden" id="reportIds" name="reportIds" value="${reportIds }">
			<input type="hidden" name="oper" value="${oper}">
			<input type="hidden" id="beginTime" name="beginTime" value="${task.beginTime}">
			<div class="modal-footer">
        			<button type="button" class="btn btn-info btn-blue btn-sm btn-round" data-dismiss="modal" aria-hidden="true"> 
        				<i class="ace-icon fa fa-times"></i>        			
        				关闭
       				</button>
      	 			<button id="goToSaveButton" type="button" onclick="saveButton()" class="btn btn-info btn-blue btn-sm btn-round">
          				<i class="ace-icon fa fa-check"></i>
        	 			 保存
          			</button>
       		</div>
       		<form:hidden path="id" />
       	</form:form>
    </div>
</div>

<script>

function openReportWindow(id){
	//var divCode = $("#divCode").val();
	var divCode = "450000";
	$.ajax({
  		type : 'GET',
  	    dataType : 'JSON',
  		url : '${ctx}/office/task/viewQueryReport3/'+id+"/"+divCode,
  		success : function(data) {
  			var url = "${ctx_bi}"+data.reportUrl;
  			window.open('${ctx}'+data.reportUrl);
  			//window.open('${ctx_bi}'+data.reportUrl);
  			//$("#url").val(url);
			//$("#showreportform").submit();
        }
	});
}

</script>

<script>
function initSomeStyle() {
	$(".modal-content")[0].style.border = "1px solid #307ecc";
	$(".table-header").find(".close")[0].style.fontSize = "40px";
}
setTimeout("initSomeStyle()",300);
</script>