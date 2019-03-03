<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="modal-dialog">
	<div class="modal-content">
      	<div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<h3 class="modal-title">
        	  <i class="ace-icon fa fa-user bigger-110 blue"></i> 
        	         任务审核信息：${task.taskName }
        	 </h3>
      	</div>
      	<form:form cssClass="form-horizontal" id="auditForm" modelAttribute="taskAudit" action="${actionUrl}" method="POST">
      		<div class="modal-body">
      		<div class="row">
				<div class="col-sm-12">
					<div class="form-group">
						<label class="col-sm-4 control-label" for="role">是否通过:<span class="required">*</span></label>
						<div class="col-sm-8 controls">
							<form:radiobutton path="status" value="5"/>通过
							<form:radiobutton path="status" value="6"/>不通过
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="role">审核意见:<span class="required">*</span></label>
						<div class="col-sm-8 controls">
							<form:textarea path="desc"/>
						</div>
					</div>
				</div>
			</div>
			</div>
			<div class="modal-footer">
        			<button type="button" class="btn btn-primary btn-sm" data-dismiss="modal" aria-hidden="true"> 
        				<i class="ace-icon fa fa-times"></i>        			
        				关闭
       				</button>
      	 			<button type="submit" class="btn btn-primary btn-sm">
          				<i class="ace-icon fa fa-check"></i>
        	 			 保存
          			</button>
       		</div>
       		<form:hidden path="id" />
       	</form:form>
    </div>
</div>
