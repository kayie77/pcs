<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="modal-dialog">
	<div class="modal-content">
      	<div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<h3 class="modal-title">
        	  <i class="ace-icon fa fa-user bigger-110 blue"></i> 
        	         审核报表分配角色
        	 </h3>
      	</div>
      	<form:form cssClass="form-horizontal" id="auditForm" modelAttribute="auditReport" action="${actionUrl}" method="POST">
      		<div class="modal-body">
      		<div class="row">
				<div class="col-sm-12">
					<div class="form-group">
						<label class="col-sm-4 control-label" for="auditReportName">报表名称:<span class="required">*</span></label>
						<div class="col-sm-8 controls">
			    			<form:input  id="auditReportName" path="auditReportName" readOnly="readOnly" required="required" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="role">选择角色:<span class="required">*</span></label>
						<div class="col-sm-8 controls">
			    			<form:select path="role.id" id="auditReport.role.id" items="${roles}" itemLabel="roleNameCN" itemValue="id"></form:select>  
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
       		<form:hidden path="auditReportUrl" />
       	</form:form>
    </div>
</div>
