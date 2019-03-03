<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<script>
	function downloadfile(id) {
		var url = '${ctx}/office/wordtypeinfo/downloadWordTypeInfo?id=' + id;
		window.open(url);
	}
	$(function () {
		$("#subBtn").bind("click", function () {
		
		    //$('#noticeForm').submit();
			createRow();
	    });
	});
	
	function tonouse(id) {
		var url = "${ctx}/office/wordtypeinfo/toNoUseStandard?id="+id +"&date="+new Date().getTime();
		$.getJSON(url,function(data) {
			if(data.success) {
				$("#noUseButton")[0].style.display = "none";
				$("#useButton")[0].style.display = "";
				
				bootbox.alert(data.message);
				
				$("#grid-table").trigger('reloadGrid');
			}
		});
	}
	
	function touse(id) {
	
			$.get("${ctx}/office/wordtypeinfo/toUseStandard?id="+id+"&"+Math.random(1000) , '', function(data){ 
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
						},
						div_code : {
							required : true,
							minlength : 6,
							maxlength : 6
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
	/*
		var url = "${ctx}/office/wordtypeinfo/useWordTypeInfo?id=" + id + "&readflag=" + readflag;
		$.getJSON(url,function(data) {
			if(data.success) {
				$(button).remove();
				alert(data.message);
			}
		});
		*/
	}
	
</script>

<div class="modal-dialog" style="width:80%;">
	<div class="modal-content">
      	<div class="table-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<i class="ace-icon fa fa-file-text"></i>
        	<b>文字查看</b>
      	</div>
      	
      		<div class="modal-body">
	      		<div class="row">
	      		
	      			<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">文字编号:</label>
							<div class="col-sm-4 controls">
				    			${wordTypeInfo.num}
							</div>
						</div>
					</div>
					
					<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">文字标题:</label>
							<div class="col-sm-4 controls">
				    			${wordTypeInfo.title}
							</div>
						</div>
					</div>
					
					<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">文字类型:</label>
							<div class="col-sm-4 controls">
				    			${wordType.title}
							</div>
						</div>
					</div>
					
					<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="content">文字内容:</label>
							<div class="col-sm-8 controls">
				    			${wordTypeInfo.content}
							</div>
						</div>
					</div>
					
					
					<c:if test="${fn:length(wordTypeInfoFileList) > 0}">
						<div class="col-sm-12">
							<div class="form-group">
								<label class="col-sm-2 control-label" for="content">附件:</label>
								<div class="col-sm-8 controls">
									<table style="width:100%">
										<tr>
											<td>文件名</td>
											<td>文件大小</td>
										</tr>
					    				<c:forEach var="messageFileItem" items="${wordTypeInfoFileList}">
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
				
					
			</div>
			
			
			<div class="modal-footer">
				<c:if test="${isManager == 'true'}">
				
					<button id="useButton" onclick="touse('${wordTypeInfo.id}')" type="button" class="btn btn-default btn-info btn-sm btn-round" style="display:none"> 
	    				<i class="ace-icon fa fa-check"></i>   
	    				采用
	   				</button>
					<button id="noUseButton" onclick="tonouse('${wordTypeInfo.id}')" type="button" class="btn btn-default btn-info btn-sm btn-round" style="display:none"> 
	    				<i class="ace-icon fa fa-check"></i>   
	    				取消采用
	   				</button>
					
					<c:if test="${wordTypeInfo.readflag != '2'}">
						<script>$("#useButton")[0].style.display = "";</script>
					</c:if>
					<c:if test="${wordTypeInfo.readflag == '2'}">
						<script>$("#noUseButton")[0].style.display = "";</script>
					</c:if>
				</c:if>
      			<button type="button" class="btn btn-default btn-info btn-sm btn-round" data-dismiss="modal" aria-hidden="true"> 
    				<i class="ace-icon fa fa-times"></i>        			
    				关闭
   				</button>
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