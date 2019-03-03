<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="modal-dialog">
	<div class="modal-content">
      	<div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<h3 class="modal-title">
        	  <i class="ace-icon fa fa-users"></i> 
        	        报表分配角色
        	 </h3>
      	</div>
      	<form:form cssClass="form-horizontal" id="dicForm" modelAttribute="dicReport" action="${actionUrl}" method="POST">
      		<div class="modal-body">
      		<div class="row">
      			<div class="col-sm-12">
					<div class="form-group">
						<label class="col-sm-4 control-label" for="reportName">任务名称:<span class="required">*</span></label>
						<div class="col-sm-8 controls">
			    			<form:input  id="reportName" path="reportName" readOnly="readOnly" required="required" style="width:60%" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-4 control-label" for="roles">选择报表:<span class="required">*</span></label>
						<div class="col-sm-8 controls">
							<c:forEach var="role" items="${roles }">
							<div style="margin-right:10px; float:left;"><input type="checkbox" name="roleIds" <c:if test='${role.isCheck}'>checked=checked</c:if> value="${role.id }">${role.roleNameCN }</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
			</div>
			<input type="hidden" id="dicReportId" name="dicReportId" value="${dicReport.id}">
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
       	</form:form>
    </div>
</div>
