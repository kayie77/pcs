<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<style>
<!--
 .modal-dialog{width:720px;}
-->
</style>
<div class="modal-dialog">
	<div class="modal-content">
      	<div class="modal-header no-padding">
        	<div class="table-header">
				  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						<span class="white">&times;</span>
				  </button>
				  <i class="ace-icon fa fa-anchor"></i><b> 资源信息</b>
			 </div>
      	</div>
      	<div class="space-6"></div>
     	<form:form cssClass="form-horizontal" id="resourceForm" modelAttribute="resource" action="${actionUrl}" method="POST">
			<div class="modal-body">
				<div class="row">
				   <div class="col-xs-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="resName">资源名称:<span class="required">*</span></label>
							<div class="col-sm-4 controls no-padding-left">
							    <span>
    								<form:input  id="resName" path="resName" cssClass="col-xs-8" placeholder="资源名称..." required="required" />
							    </span>
							</div>
							<label class="col-sm-2 control-label" for="resCode">资源代码:<span class="required">*</span></label>
							<div class="col-sm-4 controls no-padding-left">
							    <span>
    								<form:input  id="resCode" path="resCode" cssClass="col-xs-8" placeholder="资源代码..." required="required" />
							    </span>
							</div>
						</div>
						<div class="space-6"></div>
						<div class="form-group">
						    <label class="col-sm-2 control-label" for="resType">资源类型:<span class="required">*</span></label>
							<div class="col-sm-4 controls no-padding-left">
    							<label class="inline">
									<form:radiobutton cssClass="ace" path="resType" id="resTypeMenu"  value="menu" />
									<span class="lbl middle">菜单</span>
								</label>
								&nbsp; &nbsp; &nbsp;
								<label class="inline">
									<form:radiobutton cssClass="ace" path="resType" id="resTypeAction" value="action"/>
									<span class="lbl middle">操作</span>
								</label>
							</div>
							<label class="col-sm-2 control-label" for="resString">资源地址:</label>
							<div class="col-sm-4 controls no-padding-left">
    							<span>
    								<form:input id="resString" cssClass="col-xs-8" path="resString" placeholder="资源地址..." />
							    </span>
							</div>
						</div>
						<div class="space-6"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="iconCls">资源图标式样:</label>
							<div class="col-sm-4 controls no-padding-left">
    							<form:input id="iconCls" cssClass="col-xs-8" path="iconCls" placeholder="资源图标式样..." />
							</div>
							<label class="col-sm-2 control-label" for="permission">权限代码:</label>
							<div class="col-sm-4 controls no-padding-left">
    							<span>
    								<form:input id="permission" cssClass="col-xs-8" path="permission" placeholder="权限代码..." />
							    </span>
							</div>
						</div>
						<div class="space-6"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="weight">排序号:</label>
							<div class="col-sm-4 controls no-padding-left">
    							<span>
    								<form:input id="weight" cssClass="col-xs-8" path="weight" placeholder="排序号..." />
							    </span>
							</div>
							<label class="col-sm-2 control-label" for="enabled">启用:</label>
							<div class="col-sm-4 controls no-padding-left">
    							<form:checkbox  id="enabled" path="enabled"/>
							</div>
						</div>
						<div class="space-6"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="resDesc">备注说明:</label>
							<div class="col-sm-10 controls no-padding-left">
    							<form:textarea id="resDesc"	cssClass="col-xs-12 limited" path="resDesc"  rows="4" placeholder="备注说明..." />
							</div>
						</div>
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