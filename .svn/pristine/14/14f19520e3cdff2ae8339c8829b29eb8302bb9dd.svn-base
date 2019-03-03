<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="modal-dialog">
	<div class="modal-content">
      	<div class="modal-header no-padding">
        	<div class="table-header">
				  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						<span class="white">&times;</span>
				  </button>
				  <i class="ace-icon fa fa-user"></i><b> 用户详细信息</b>
			 </div>
      	</div>
      	<div class="space-6"></div>
      	<form:form cssClass="form-horizontal" id="personForm" modelAttribute="person" action="${actionUrl}" method="POST">
      		<div class="modal-body no-padding">      	
				<div class="form-group">
					<label class="col-sm-3 control-label" for="persName">姓名:<span class="required">*</span></label>
					<div class="col-sm-9 controls">
					    <span>
							<form:input  id="persName" path="persName" placeholder="姓名..." required="required"/>
						</span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="gender">性别:</label>
					<div class="col-sm-9 controls">
		    			<label class="inline">
							<form:radiobutton cssClass="ace" path="gender" id="genderMale"  value="1" />
							<span class="lbl middle">男</span>
						</label>
						&nbsp; &nbsp; &nbsp;
						<label class="inline">
							<form:radiobutton cssClass="ace" path="gender" id="genderFemal" value="2"/>
							<span class="lbl middle">女</span>
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="idCertNum">身份证号</label>
					<div class="col-sm-9 controls">
						<span>
							<form:input id="idCertNum" path="idCertNum" placeholder="身份证号..." />
						</span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="empNum">员工编号</label>
					<div class="col-sm-9 controls">
					    <span>
							<form:input id="empNum" path="empNum" placeholder="员工编号..." />
						</span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="email">电子邮件:</label>
					<div class="col-sm-9 controls">
					    <span>
							<form:input  id="email" path="email" placeholder="电子邮箱..."/>
						</span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="mobile">手机号码:</label>
					<div class="col-sm-9 controls">
					    <span>
							<form:input  id="mobile" path="mobile" placeholder="手机号码..."/>
						</span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="telNum">电话号码:</label>
					<div class="col-sm-9 controls">
					    <span>
							<form:input  id="telNum" path="telNum" placeholder="电话号码..."/>
						</span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="faxNum">传真号码:</label>
					<div class="col-sm-9 controls">
					    <span>
							<form:input  id="faxNum" path="faxNum" placeholder="传真号码..."/>
						</span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="zip">邮政编码</label>
					<div class="col-sm-9 controls">
					    <span>
							<form:input id="zip" path="zip"/>
						</span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="address">通讯地址</label>
					<div class="col-sm-9 controls">
					    <span>
							<form:input id="address" path="address"/>
						</span>
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
