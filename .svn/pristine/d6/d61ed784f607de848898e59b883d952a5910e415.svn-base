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
				  <i class="ace-icon fa fa-sitemap"></i>
				  <b>机构信息</b>
			 </div>
      	</div>
      	<div class="space-6"></div>
     	<form:form cssClass="form-horizontal" id="orgForm" modelAttribute="org" action="${actionUrl}" method="POST">
			<div class="modal-body">
				<div class="row">
				   <div class="col-xs-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="orgName">机构名称:<span class="required">*</span></label>
							<div class="col-sm-4 controls no-padding-left">
							    <span>
    								<form:input  id="orgName" path="orgName" cssClass="col-xs-8" placeholder="机构名称..." required="required" />
							    </span>
							</div>
							<label class="col-sm-2 control-label" for="orgCode">机构代码:<span class="required">*</span></label>
							<div class="col-sm-4 controls no-padding-left">
    							<span>
    							    <form:input  id="orgCode" path="orgCode" cssClass="col-xs-8" placeholder="机构代码..." required="required" />
							    </span>
							</div>
						</div>
						<div class="space-6"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="divCode">行政区划:</label>
							<div class="col-sm-4 controls no-padding-left">
    							<span>
    								<form:input id="divCode" cssClass="col-xs-8" path="divCode" placeholder="行政区划..." />
							    </span>
							</div>
							<label class="col-sm-2 control-label" for="mnemonicCode">记助码:</label>
							<div class="col-sm-4 controls no-padding-left">
    							<form:input id="mnemonicCode" cssClass="col-xs-8" path="mnemonicCode" placeholder="记助码..." />
							</div>
						</div>
						<div class="space-6"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="telNum">电话号码:</label>
							<div class="col-sm-4 controls no-padding-left">
    							<span>
    								<form:input id="telNum" cssClass="col-xs-8" path="telNum" placeholder="电话号码..." />
							    </span>
							</div>
							<label class="col-sm-2 control-label" for="email">电子邮箱:</label>
							<div class="col-sm-4 controls no-padding-left">
    							<span>
    								<form:input id="email" cssClass="col-xs-8" path="email"  placeholder="电子邮箱..." />
							    </span>
							</div>
						</div>
						<div class="space-6"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="faxNum">传真号码:</label>
							<div class="col-sm-4 controls no-padding-left">
    							<span>
    								<form:input id="faxNum" cssClass="col-xs-8" path="faxNum" placeholder="传真号码..." />
							    </span>
							</div>
							<label class="col-sm-2 control-label" for="address">地址:</label>
							<div class="col-sm-4 controls no-padding-left">
    							<form:input id="address" cssClass="col-xs-8" path="address"  placeholder="地址..." />
							</div>
						</div>
						<div class="space-6"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="zip">邮政编码:</label>
							<div class="col-sm-4 controls no-padding-left">
    							<span>
    								<form:input id="zip" cssClass="col-xs-8" path="zip" placeholder="邮政编码..." />
							    </span>
							</div>
							<label class="col-sm-2 control-label" for="website">网址:</label>
							<div class="col-sm-4 controls no-padding-left">
    							<span>
    								<form:input id="website" cssClass="col-xs-8" path="website"  placeholder="网址..." />
							     </span>
							</div>
						</div>
						<div class="space-6"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="orgDesc">备注说明:</label>
							<div class="col-sm-10 controls no-padding-left">
    							<form:textarea id="orgDesc"	cssClass="col-xs-12 limited" path="orgDesc"  rows="4" placeholder="备注说明..." />
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

