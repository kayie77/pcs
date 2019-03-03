<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="modal-dialog">
	<div class="modal-content">
      	<div class="modal-header no-padding">
        	<div class="table-header">
				  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						<span class="white">&times;</span>
				  </button>
				  <i class="ace-icon fa fa-crosshairs"></i>
				  <b>角色信息</b>
			 </div>
      	</div>
      	<div class="space-6"></div>
      	<form:form cssClass="form-horizontal" id="roleForm" modelAttribute="role" action="${actionUrl}" method="POST">
      		<div class="modal-body no-padding">         	
				<div class="form-group">
					<label class="col-sm-3 control-label" for="roleName">角色名:<span class="required">*</span></label>
					<div class="col-sm-9 controls">
						<form:input  id="roleName" path="roleName" placeholder="角色名..." required="required"/>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="roleNameCN">角色中文名:<span class="required">*</span></label>
					<div class="col-sm-9 controls">
		    			<form:input  id="roleNameCN" path="roleNameCN" placeholder="角色中文名..." required="required" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="roleDesc">角色说明:</label>
					<div class="col-sm-9 controls">
		    			<form:input  id="roleDesc" path="roleDesc" placeholder="角色说明..." />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="enabled">是否启用</label>
					<div class="col-sm-9 controls">
						<form:checkbox id="enabled" path="enabled"/>
					</div>
				</div>
			</div>
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

