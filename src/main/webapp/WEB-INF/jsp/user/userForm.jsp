<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="modal-dialog">
	<div class="modal-content">
      	<div class="modal-header no-padding">
        	<div class="table-header">
				  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						<span class="white">&times;</span>
				  </button>
				  <i class="ace-icon fa fa-user"></i>
				  <b>用户信息</b>
			 </div>
      	</div>
      	<div class="space-6"></div>
      	<form:form cssClass="form-horizontal" id="userForm" modelAttribute="user" action="${actionUrl}" method="POST">      		  	
			<div class="modal-body no-padding">
				<div class="form-group">
					<label class="col-md-3 control-label" for="username">用  户  名:<span class="required">*</span></label>
					<div class="col-md-9 controls">
					    <span>
						   <form:input  id="username" maxlength="20" path="username" placeholder="用户名..."  required="required"/>
					    </span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="persName">用户姓名:<span class="required">*</span></label>
					<div class="col-md-9 controls">
						 <span>
		    				<form:input  id="persName" maxlength="20" path="person.persName" placeholder="用户姓名..."  required="required"/>
					     </span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="email">电子邮件:</label>
					<div class="col-md-9 controls">
						 <span>
							<form:input  id="email" maxlength="100" path="person.email" placeholder="电子邮箱..."/>
						 </span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="mobile">手机号码:</label>
					<div class="col-md-9 controls">
					     <span>
							<form:input  id="mobile" maxlength="20" path="person.mobile" placeholder="手机号码..."/>
						</span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="qq">QQ帐号:</label>
					<div class="col-md-9 controls">
						<span>
							<form:input  id="qq" maxlength="50" path="qq" placeholder="QQ号码..."/>
						</span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="caSn">CA证书号:</label>
					<div class="col-md-9 controls">
						<span>
							<form:input  id="caSn" maxlength="20" path="caSn" placeholder="CA证书号..."/>
						</span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-md-3 control-label" for="enabled">是否启用</label>
					<div class="col-md-9 controls">
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

