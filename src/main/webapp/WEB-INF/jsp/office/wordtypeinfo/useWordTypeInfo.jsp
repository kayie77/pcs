<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

		<script type="text/javascript">
			$(function () {
				$("#subBtn").bind("click", function () {


						$($('#messageForm')[0]).ajaxSubmit({
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

				    	//$('#messageForm').submit();
			    });
			});
		</script>	
<div class="modal-dialog" style="width:800px;">
	<div class="modal-content">
      	<div class="table-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<i class="ace-icon fa fa-star"></i>
        	<b>评分信息</b>
      	</div>
      	<form class="form-horizontal" id="messageForm" modelAttribute="useStandard" action="${ctx}/office/wordtypeinfo/saveUseStandard" method="POST" >
      		<input type="hidden" id="id" name="id" value="${useStandard.id}" />
      		<div class="modal-body">
	      		<div class="row">
	      			<div class="col-sm-12">
	      			
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">评分依据:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
				    			<input style="width:500px" id="title" value="${useStandard.title}" name="title" placeholder="评分依据..." required="required" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">评分分值:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
				    			<input style="width:500px" id="score" type="number" min="0" max="100" value="${useStandard.score}" name="score" placeholder="评分分值..." required="required" />
							</div>
						</div>
						
					</div>
				</div>
			</div>

			<div class="modal-footer">
        			<button type="button" class="btn btn-default btn-info btn-sm btn-round" data-dismiss="modal" aria-hidden="true"> 
        				<i class="ace-icon fa fa-times"></i>        			
        				关闭
       				</button>
      	 			<button type="button" id="subBtn" class="btn btn-default btn-info btn-sm btn-round">
          				<i class="ace-icon fa fa-check"></i>
        	 			 保存
          			</button>
       		</div>
       	</form>
    </div>
</div>
