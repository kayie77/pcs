<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script src="${ctx}/assets/js/agrims.js"></script>
<link rel="stylesheet" href="${ctx}/assets/jquery/ui/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jqGrid/css/ui.jqgrid.css" />
<link rel="stylesheet" href="${ctx}/assets/bootstrap/plugins/date-time/css/datepicker.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jstree/themes/proton/style.css" />
<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<div class="row">
			<div class="col-xs-12 widget-container-col">
				<div class="widget-box widget-color-blue">
					<div class="widget-header widget-header-small">
					    <h5 class="widget-title bolder">
							<i class="ace-icon fa fa-tasks"></i>
							待办任务
						</h5>
						<div class="widget-toolbar no-border">
							<c:if test="${fn:length(taskDivList) != 0}">
						  		<c:choose>
									<c:when test="${isCity == true || isArea == true}">
								    	<a  class="white" style="font-size:12px" href="${ctx}/index#page/office/task/taskAudit">更多..</a>
									</c:when>
									<c:otherwise>				    
								    	<a  class="white" style="font-size:12px" href="${ctx}/index#page/office/task/wait">更多..</a>
									</c:otherwise>
								</c:choose>
						   	</c:if>
						</div>
					</div>
					<div class="widget-body" style="min-height: 200px;">
					   <div class="widget-main no-padding">
					   		<table class="table table-striped table-bordered table-hover" id="taskTable">
					   			<thead class="thin-border-bottom">
					      			<tr>
					      				<!-- 
					         			<th width="15%">任务编号</th>
					         			<th width="30%">任务名称</th>
					         			<th width="10%">任务期数</th>
					         			<th width="12%">发布时间</th>
					         			<th width="23%">上报时间</th>
					         			<th style="display:none">上报频率</th>
					         			<th width="10%">状态</th>
					      				 -->
					         			<th width="60%">任务名称</th>
					         			<th width="10%">任务期数</th>
					         			<th width="20%">上报时间</th>
					         			<th width="10%">状态</th>
					      			</tr>
					   			</thead>
					   			<tbody>
						    	<c:forEach var="taskDivItem" items="${taskDivList}" varStatus="i">
						  	    <c:choose>
								   	<c:when test="${isCity == true || isArea == true}">
									  	<c:set var="urls" value="${ctx}/index#page/office/task/taskReportAudit?id=${taskDivItem.taskDivId}"/>
								   	</c:when>
								   	<c:otherwise>
									  	<c:set var="urls" value="${ctx}/index#page/office/task/viewInput/${taskDivItem.id}"/>
								   	</c:otherwise>
							   	</c:choose>
					      	   		<tr onclick="golocation('${urls}')" style="cursor:pointer;">
					      	   			<%/*
						          		<td>${taskDivItem.taskNum}</td>
						          		<td>${taskDivItem.taskName}</td>
						          		<td>${taskDivItem.taskNo}</td>
						          		<td>${taskDivItem.beginTime}</td>
						          		<td>${taskDivItem.endTime}</td>
						          		<td style="display:none">${taskDivItem.taskHz}</td>
						          		<td class="statusTd"><span class="label label-warning arrowed-in arrowed-in-right">未完成</span><!-- ${taskDivItem.status} --></td>
					      	   			*/%>
						          		<td>${taskDivItem.taskName}</td>
						          		<td>${taskDivItem.taskNo}</td>
						          		<td>${taskDivItem.endTime}</td>
						          		<td class="statusTd"><span class="label label-warning arrowed-in arrowed-in-right">未完成</span><!-- ${taskDivItem.status} --></td>
					      	   		</tr>
					      		</c:forEach>
					        	<c:if test="${fn:length(taskDivList) == 0}">
						   			<tr>
						   				<td colspan="4" style="text-align:center;font-size:10px;">暂无数据</td>
						   			</tr>
						   		</c:if>
					   			</tbody>
							</table>
					  </div>
				</div>
			 </div>
		   </div>
		</div>
		<div class="space-10"></div>
		<div class="row">
           <div class="col-xs-12 col-sm-6 widget-container-col">
				<div class="widget-box widget-color-blue">
					<div class="widget-header widget-header-small">
						<h5 class="widget-title bolder">
							<i class="ace-icon fa fa-exclamation-circle"></i>
							通知
						</h5>
						<div class="widget-toolbar no-border">
							<c:if test="${fn:length(noticeList) != 0}">
					   		   <a  class="white" style="font-size:12px" href="${ctx}/index#page/office/notice/noticeViewList">更多..</a>
					   	    </c:if>
					   	</div>
					 </div>
					 <div class="widget-body" style="min-height: 200px;">
					      <div class="widget-main no-padding">
					        <table class="table table-striped table-bordered table-hover">
                               <tbody>
                                  	<c:forEach var="noticeItem" items="${noticeList}">
                                    	<tr>
                                      		<td><a href="javascript:showRow('${noticeItem.id}','notice')">${noticeItem.title}</a></td>
                                    	</tr>
					   				</c:forEach>
                                   </tbody>
                            </table>            
					      </div>
					</div>	
				 </div>
			</div>
			<div class="col-xs-12 col-sm-6 widget-container-col">
				<div class="widget-box widget-color-blue">
					<div class="widget-header widget-header-small">
						<h5 class="widget-title  bolder">
							<i class="ace-icon fa  fa-envelope-o"></i>
							短信
						</h5>
						<div class="widget-toolbar no-border">
							<c:if test="${fn:length(messageList) != 0}">
					   		       <a  class="white" style="font-size:12px" href="${ctx}/index#page/office/message/recvlist">更多..</a>
					   	    </c:if>
					   	</div>
					 </div>
					 <div class="widget-body" style="min-height: 200px;">
					     <div class="widget-main no-padding">
					       <table class="table table-striped table-bordered table-hover">
                               <tbody>
                                   <c:forEach var="messageItem" items="${messageList}">
                                      <tr>
                                        <td><a href="javascript:showRow('${messageItem.id}','message')">${messageItem.message.title}</a></td>
                                      </tr>
					   				</c:forEach>
                               </tbody>
                           </table>            
					     </div>
					</div>	
				 </div>
			</div>
	   </div><!-- PAGE CONTENT ENDS -->	
	</div><!-- /.col -->
</div><!-- /.row -->

<div id="taskModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
	  <div class="modal-content">
    	 <div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<h3 class="modal-title">通知信息</h3>
    	 </div>
    	 <div class="modal-body"></div>
    	 <div class="modal-footer">
         	<button type="button" class="btn btn-default btn-info btn-sm btn-round" data-dismiss="modal" aria-hidden="true">
        		<i class="icon-times"></i>         			
        		关闭
       		</button>
      		<button type="submit" class="btn btn-default btn-info btn-sm btn-round">
          		<i class="icon-save"></i> 
        	  	保存
        	</button>
        </div>
  	 </div>
  </div>
</div>
		

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
  <script src="${ctx}/assets/js/excanvas.min.js"></script>
<![endif]-->
<script type="text/javascript">
var scripts = [null,"${ctx}/assets/jquery/ui/jquery-ui.min.js","${ctx}/assets/jquery/ui/jquery.ui.touch-punch.min.js","${ctx}/assets/js/agrims.js", null]
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	jQuery(function($) {
		var desktopUrl = "${ctx_bi}${desktop_path}?divCode=${divCode}";//${divCode}
		$('#desktopIframe').attr("src",desktopUrl);
	});
});




//编辑选定记录
function showRow(id,type){
	
	var url11 = "";
	if(type == 'notice') {
		url11 = "${ctx}/office/notice/viewNotice?id="+id+"&"+Math.random(1000);
	} else {
		url11 = "${ctx}/office/message/viewMessage?id="+id+"&"+Math.random(1000)+"&canDelete=false&canReply=false";
	}
	$.get(url11, '', function(data){ 
		var modal = $('#taskModal');
		modal.html(data);
		modal.modal({show:true,backdrop:false}).on("hidden", function(){
	    	$(this).remove();
		});
        var form = modal.find('form:eq(0)');
        form.validate({	
			//errorElement: "span",
			errorPlacement: function(error, element) {
				console.log(error);
			    error.insertAfter(element.parent());
			},
			highlight: function(element) {
			    //$(element).closest('.form-group').removeClass('success').addClass('error');
			},
			submitHandler: function (form) {
				$(form).ajaxSubmit({
					//target: "#result",
					type:"POST",
					dataType:"json",
					success:function(data,status) {
			            console.log(data)
			            if(data.success===true) {
			            	modal.modal('hide');
			            	bootbox.dialog({
	 							message: data.message,
	 							buttons: 			
	 							{
	 								"success" :
	 								 {
	 									"label" : "<i class='ace-icon fa fa-check'></i>确定",
	 									"className" : "btn-sm btn-success",
										"callback": function() {	
										}
	 								}
	 							}
	 						});
			            } else {
			            	modal.modal('hide');
			            	 bootbox.dialog({
									message: data.message,
									buttons: 			
									{
										"danger" :
										 {
											"label" : "<i class='ace-icon fa fa-exclamation-triangle'></i>确定",
											"className" : "btn-sm btn-success",
											"callback": function() {	
											}
										}
									}
							});
			            }
			        }  
				});
			},
		});
    });    
}

function golocation(url) {
	location.href = url;
}
</script>