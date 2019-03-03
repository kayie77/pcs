<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="modal-dialog">
	<div class="modal-content">
      	<div class="modal-content">
      	<div class="modal-header no-padding">
        	<div class="table-header">
				  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						<span class="white">&times;</span>
				  </button>
				  <i class="ace-icon glyphicon glyphicon-cog"></i>
				  <b>系统参数</b>
			 </div>
      	</div>
      	<div class="space-6"></div>
      	<form:form cssClass="form-horizontal" id="parameterForm" modelAttribute="parameter" action="${actionUrl}" method="POST">
      		<div class="modal-body no-padding">    
				<div class="form-group">
					<label class="col-sm-3 control-label" for="paraName">参数名:<span class="required">*</span></label>
					<div class="col-sm-9 controls">
		    			<form:input  id="paraName" path="paraName" placeholder="参数名..." readonly="true" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="paraVal">参数值:<span class="required">*</span></label>
					<div class="col-sm-9 controls">
		    			<form:input  id="paraVal" path="paraVal" placeholder="参数值..." required="required" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="paraDesc">参数说明:</label>
					<div class="col-sm-9 controls">
		    			<form:input  id="paraDesc" path="paraDesc" placeholder="参数说明..." />
					</div>
				</div>
			</div>
			<input type="hidden" name="oper" value="${oper}">
			<div class="modal-footer no-margin-top">
        		<button type="button" class="btn btn-default btn-info btn-sm btn-round" data-dismiss="modal" aria-hidden="true"> 
        			<i class="ace-icon fa fa-times"></i>        			
        			关闭
       			</button>
      	 		<button type="submit" class="btn btn-default btn-info btn-sm btn-round">
          			<i class="ace-icon fa fa-check"></i>
        	 		 保存
          		</button>
       		</div>
       		<form:hidden path="id" />
       		<form:hidden path="paraCode" />
       	</form:form>
    </div>
</div>

<script>
function initSomeStyle() {
	$(".modal-content")[0].style.border = "1px solid #307ecc";
	$(".table-header").find(".close")[0].style.fontSize = "40px";
}
setTimeout("initSomeStyle()",300);
</script>
