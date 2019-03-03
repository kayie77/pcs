<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="modal-dialog">
	<div class="modal-content">
      	<div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<h3 class="modal-title">
        	  <i class="ace-icon fa fa-user bigger-110 blue"></i> 
        	         代码集值信息
        	 </h3>
      	</div>
      	<form:form cssClass="form-horizontal" id="dictvalForm" modelAttribute="dictVal" action="${actionUrl}" method="POST">
      		<div class="modal-body">
      			<div class="form-group">
					<label class="col-sm-3 control-label" for="dict">代码集:<span class="required">*</span></label>
					<div class="col-sm-9 controls">
		    			<form:input  id="dict" path="dict.dictName" readOnly="readOnly" required="required" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="valCode">值代码:<span class="required">*</span></label>
					<div class="col-sm-9 controls">
		    			<form:input  id="valCode" path="valCode" placeholder="值代码..." required="required" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="valName">值名称:<span class="required">*</span></label>
					<div class="col-sm-9 controls">
		    			<form:input  id="valName" path="valName" placeholder="值名称..." required="required" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="verNum">版本号:<span class="required">*</span></label>
					<div class="col-sm-9 controls">
		    			<form:input  id="verNum" path="verNum" placeholder="版本号..." required="required" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="valDesc">值说明:</label>
					<div class="col-sm-9 controls">
		    			<form:input  id="valDesc" path="valDesc" placeholder="值说明..." />
					</div>
				</div>
			</div>
			<input type="hidden" name="oper" value="${oper}">
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
       		<form:hidden path="dict.id" />
       	</form:form>
    </div>
</div>
