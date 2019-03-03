<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<script>
	function downloadfile(id) {
		var url = '${ctx}/office/message/downloadMessage?id=' + id;
		window.open(url);
	}
	$(function () {
		$("#subBtn").bind("click", function () {
		
		    //$('#noticeForm').submit();
			createRow();
	    });
	});
	
	function deleteReply(id) {
		bootbox.confirm("确定删除吗？",function(result) {
			if(result) {	

				var url = '${ctx}/office/message/deleteMessage?id=' + id;
				$.post(url,function(data) {
					
					bootbox.alert(data.message);
					if(data.success) {
						$("#replyMessageDiv").remove();
						$("#subBtn")[0].style.display = "";
					}
				});
			}
		});
		
	}
	
					    
	//新建记录
	function createRow(){
		$.get("${ctx}/office/message/edit?"+Math.random(1000)+"&parentId=${message.id}&messageSendId=${messageSend.id}" , '', function(data){ 
			var modal = $('#taskModal');
			modal.html(data);
			modal.modal({show:true,backdrop:false}).on("hidden", function(){
		    	$(this).remove();
			});
	        var form = modal.find('form:eq(0)');
	        form.validate({	
				rules : {
					title : {
						required : true,
						minlength : 1,
						maxlength : 100
					},
					content : {
						required : true,
						minlength : 1,
						maxlength : 2000
					}
				},
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
				            	$("#grid-table").trigger('reloadGrid');
				            	modal.modal('hide');
				            	bootbox.dialog({
		 							message: '操作成功！',
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
										message: '操作失败！',
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
	

</script>

<div class="modal-dialog" style="width:800px;">
	<div class="modal-content">
      	<div class="table-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<i class="ace-icon fa fa-envelope-o"></i>
        	<b>短信信息</b>
      	</div>
      	
      		<div class="modal-body">
	      		<div class="row">
	      		
	      			<div class="col-sm-12">
						<div class="form-group">
						
							<label class="col-sm-2 control-label" for="title">发件人:</label>
							<div class="col-sm-4 controls">
				    			${message.createDiv.divName}
							</div>
							
							<label class="col-sm-2 control-label" for="title">发送时间:</label>
							<div class="col-sm-4 controls">
				    			${message.createdateStr}
							</div>
							
						</div>
					</div>
					
					<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">收件人:</label>
							<div class="col-sm-4 controls">
				    			${messageSend.division.divName}
							</div>
						</div>
					</div>
					
					<div class="col-sm-12">
						<div class="form-group">
						
							<label class="col-sm-2 control-label" for="title">级  别:</label>
							<div class="col-sm-4 controls">
								<c:if test="${message.important == 1}">
									重要
								</c:if>
				    			<c:if test="${message.important != 1}">
				    				普通
				    			</c:if>
							</div>
							
							<label class="col-sm-2 control-label" for="title">要求回复:</label>
							<div class="col-sm-4 controls">
								<c:if test="${message.needreplay == 1}">
									是
								</c:if>
				    			<c:if test="${message.needreplay != 1}">
				    				否
				    			</c:if>
							</div>
							
						</div>
					</div>
					
	      			<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">标题:</label>
							<div class="col-sm-8 controls">
				    			${message.title}
							</div>
						</div>
					</div>
					
					<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="content">内容:</label>
							<div class="col-sm-8 controls">
				    			${message.contentStr}
							</div>
						</div>
					</div>
					
					
					<c:if test="${fn:length(messageFileList) > 0}">
						<div class="col-sm-12">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="content">附件:</label>
								<div class="col-sm-8 controls">
									<table style="width:100%">
										<tr>
											<td>文件名</td>
											<td>文件大小</td>
										</tr>
					    				<c:forEach var="messageFileItem" items="${messageFileList}">
										<tr>
											<td><a href="###" onclick="downloadfile('${messageFileItem.id}')">${messageFileItem.filename}</a></td>
											<td>${messageFileItem.filelength/1024}&nbsp;KB</td>
										</tr>
					    				</c:forEach>
									</table>
								</div>
							</div>
						</div>
					</c:if>
				</div>
				
				
					
					
				<c:if test="${replyMessage != null}">
				<hr/>
      			<div class="row" id="replyMessageDiv">
					<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">回复:</label>
						</div>
					</div>
					
					<div class="col-sm-12">
						<div class="form-group">
						
							<label class="col-sm-2 control-label" for="title">发件人:</label>
							<div class="col-sm-4 controls">
				    			${replyMessage.createDiv.divName}
							</div>
							
							<label class="col-sm-2 control-label" for="title">发送时间:</label>
							<div class="col-sm-4 controls">
				    			${replyMessage.createdateStr}
							</div>
							
						</div>
					</div>
					
					<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">收件人:</label>
							<div class="col-sm-4 controls">
								<%/*
				    			${replyMessage.division.divName}
								*/%>
								${replyMessage.parent.createDiv.divName}
							</div>
						</div>
					</div>
					
					<div class="col-sm-12">
						<div class="form-group">
						
							<label class="col-sm-2 control-label" for="title">级  别:</label>
							<div class="col-sm-4 controls">
								<c:if test="${replyMessage.important == 1}">
									重要
								</c:if>
				    			<c:if test="${replyMessage.important != 1}">
				    				普通
				    			</c:if>
							</div>
							
							<label class="col-sm-2 control-label" for="title">要求回复:</label>
							<div class="col-sm-4 controls">
								<c:if test="${replyMessage.needreplay == 1}">
									是
								</c:if>
				    			<c:if test="${replyMessage.needreplay != 1}">
				    				否
				    			</c:if>
							</div>
							
						</div>
					</div>
					
	      			<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">标题:</label>
							<div class="col-sm-8 controls">
				    			${replyMessage.title}
							</div>
						</div>
					</div>
					
					<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="content">内容:</label>
							<div class="col-sm-8 controls">
				    			${replyMessage.contentStr}
							</div>
						</div>
					</div>
					
					
					<c:if test="${fn:length(replyMessageFileList) > 0}">
						<div class="col-sm-12">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="content">附件:</label>
								<div class="col-sm-8 controls">
									<table style="width:100%">
										<tr>
											<td>文件名</td>
											<td>文件大小</td>
										</tr>
					    				<c:forEach var="messageFileItem" items="${replyMessageFileList}">
										<tr>
											<td><a href="###" onclick="downloadfile('${messageFileItem.id}')">${messageFileItem.filename}</a></td>
											<td>${messageFileItem.filelength/1024}&nbsp;KB</td>
										</tr>
					    				</c:forEach>
									</table>
								</div>
							</div>
						</div>
					</c:if>
					
					<c:if test="${canDelete == 'true'}">
						<div class="col-sm-12">
							<div class="form-group" style="text-align:right">
								<button onclick="deleteReply('${replyMessage.id}')" type="button" class="btn btn-default btn-info btn-sm btn-round" > 
			        				<i class="ace-icon fa fa-times"></i>        			
			        				删除回复
			       				</button>
							</div>
						</div>
					</c:if>
				</div>
				</c:if>
					
			</div>
			
			<div class="modal-footer">
        			<button type="button" class="btn btn-default btn-info btn-sm btn-round" data-dismiss="modal" aria-hidden="true"> 
        				<i class="ace-icon fa fa-times"></i>        			
        				关闭
       				</button>
       				<button type="button" id="subBtn" class="btn btn-default btn-info btn-sm btn-round" style="display:none">
          				<i class="ace-icon fa fa-check"></i>
        	 			 回复
          			</button>
	          		
       				<c:if test="${canReply == 'true' && replyMessage == null}">
       					<script>
       						$("#subBtn")[0].style.display = "";
       					</script>
       				</c:if>
       		</div>

    </div>
</div>
<script>
function initSomeStyle() {
	$(".modal-content")[0].style.border = "1px solid #307ecc";
	$(".table-header").find(".close")[0].style.fontSize = "40px";
}
setTimeout("initSomeStyle()",300);
</script>