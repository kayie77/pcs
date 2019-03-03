<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

		<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jstree/themes/default/style.css" />
		<script type="text/javascript" charset="utf-8" src='${ctx}/assets/kindeditor-4.1.7/kindeditor.js'></script>
		<script type="text/javascript" charset="utf-8" src='${ctx}/assets/kindeditor-4.1.7/lang/zh_CN.js'></script>
	
		<script type="text/javascript">
			$(function () {
				$("#subBtn").bind("click", function () {
				
					if($("#taskReturnReason").val() == '') {
			            bootbox.alert('请填入重报原因！');
			            return false;
					}
				
				    $('#noticeForm').ajaxSubmit({
						type:"POST",
						dataType:"json",
						success:function(data,status) {
							
			            	$("#grid-table").trigger('reloadGrid');
			            	bootbox.alert(data.message);
			            	var modal = $('#taskModal');
			            	modal.modal('hide');
				        }
			        });
			            
			    });
			});
		
		</script>	
<div class="modal-dialog" style="width:800px;">
	<div class="modal-content">
      	<div class="table-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<i class="ace-icon fa fa-user"></i><b>重报信息</b>
      	</div>
      	<form:form cssClass="form-horizontal" id="noticeForm" modelAttribute="notice" action="${ctx}/office/task/saveDownTaskReport" method="POST">
      		<div class="modal-body">
	      		<div class="row">
	      			<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">原因:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
								<input id="taskReturnReason" name="taskReturnReason" style="width:500px" placeholder="标题..." required="required" maxlength="200"/>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<input type="hidden" id="taskItemId" name="taskItemId" value="${id}">
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
       	</form:form>
    </div>
</div>
