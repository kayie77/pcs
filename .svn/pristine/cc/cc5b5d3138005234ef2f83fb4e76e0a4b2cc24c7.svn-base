<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<script src="${ctx}/assets/jquery/ui/jquery-ui.custom.min.js"></script>
<script src="${ctx}/assets/jquery/plugins/jquery-form/jquery-form.min.js"></script>
<script src="${ctx}/assets/jquery/plugins/validation/jquery.validate.min.js"></script>
<script src="${ctx}/assets/jquery/plugins/validation/localization/messages_zh.min.js"></script>
<script src="${ctx}/assets/bootstrap/plugins/date-time/bootstrap-datepicker.min.js"></script>
<script src="${ctx}/assets/bootstrap/plugins/date-time/locales/bootstrap-datepicker.zh-CN.js"></script>
<script src="${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js"></script>
<script src="${ctx}/assets/jquery/plugins/jqGrid/js/jquery.jqGrid.min.js"></script>
<script src="${ctx}/assets/jquery/plugins/jqGrid/js/i18n/grid.locale-cn.js"></script>
<script src="${ctx}/assets/jquery/plugins/lixi_common/ajax_render.js"></script>
<script src="${ctx}/assets/jquery/plugins/lixi_common/jquery.serialize-object.min.js"></script>
<script src="${ctx}/assets/jquery/plugins/toast/jquery.toaster.js"></script>
<script src="${ctx}/assets/jquery/plugins/lixi_common/utils.js"></script>
<script src="${ctx}/assets/jquery/plugins/jstree/jstree.min.js"></script>
<script src="${ctx}/assets/jquery/plugins/lixi_common/js-table.js"></script>
<script src="${ctx}/assets/js/agrims.js"></script>

<link rel="stylesheet" href="${ctx}/assets/jquery/ui/css/jquery-ui.custom.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/gritter/css/jquery.gritter.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/select2/css/select2.min.css" />
<link rel="stylesheet" href="${ctx}/assets/bootstrap/plugins/date-time/css/datepicker.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/chosen/css/chosen.min.css" />
<link rel="stylesheet" href="${ctx}/assets/bootstrap/plugins/editable/css/bootstrap-editable.min.css" />

<script>
	function personFormSave() {
		$("#personForm").ajaxSubmit({
			type: 'post',
			url: '${ctx}/profile/updatePersonInfo', 
			success: function(data) {
			    alert(data.message);
			}
		});
	}
	function savePassword() {
		$("#passwordForm").ajaxSubmit({
			type: 'post',
			url: '${ctx}/profile/updatePassword', 
			success: function(data) {
			    alert(data.message);
			}
		});
	}
	$(document).ready(function() {
		
	});
</script>

<div class="row">
  <div id="user-profile" class="user-profile">
	<div class="col-xs-12">
		<div class="tabbable">
	   		<ul class="nav nav-tabs padding-18">
		   		<li class="active">
					<a data-toggle="tab" href="#accountTab">
						<i class="green ace-icon fa fa-pencil bigger-120"></i>
						帐户信息
			    	</a>
		   		</li>
		   		<li>
					<a data-toggle="tab" href="#personelTab">
						<i class="green  ace-icon fa fa-user bigger-120"></i>
						个人信息
			    	</a>
		   		</li>
		   		<li>
					<a data-toggle="tab" href="#passwordTab">
						<i class="green ace-icon fa fa-key bigger-120"></i>
						修改密码
					</a>
		   		</li>
	   		</ul>
	   		<div class="tab-content no-border padding-24 ">
	         	<div id="accountTab" class="tab-pane in active">	   
                	<div class="row">
                	    <div class="col-xs-12 col-sm-3 center">
									<span class="profile-picture">
										<c:choose>
											<c:when test="${profile.avatar == null}">
												<img id="avatar" class="editable img-responsive"  alt="${profile.fullName}"  src="${ctx}/assets/avatars/avatar156x156.jpg" />
											</c:when>
											<c:otherwise>
												<img id="avatar" class="editable img-responsive"  alt="${profile.fullName}"  src="${ctx}/office/file/download?filename=${profile.avatar}&type=avatar" />
											</c:otherwise>
										</c:choose>
									</span>

									<div class="space space-4"></div>
									<a href="#" class="btn btn-sm btn-block btn-primary" style="display:none">
										<i class="ace-icon fa fa-envelope-o bigger-110"></i>
										<span class="bigger-110">发送信息</span>
									</a>
						</div><!-- /.col -->
						
						<div class="col-xs-12 col-sm-9">
							<h4 class="blue">
								<span class="middle">${profile.fullName}</span>
								<span class="label label-purple arrowed-in-right">
									<i class="ace-icon fa fa-circle smaller-80 align-middle"></i>
									在线
								</span>
							</h4>
							<div class="profile-user-info">
								<div class="profile-info-row">
									<div class="profile-info-name"> <spring:message code="label.user.userName" />：  </div>
									<div class="profile-info-value">
										<span id="username">${profile.username}</span>
									</div>
								</div>
					 			<div class="profile-info-row">
									<div class="profile-info-name"> <spring:message code="label.user.fullName" />： </div>
									<div class="profile-info-value">
										<span id="fullName">${profile.fullName}</span>
									</div>
					 			</div>
					 			<div class="profile-info-row">
									<div class="profile-info-name"> <spring:message code="label.user.lastLoginTime" />： </div>
									<div class="profile-info-value">
										<span id="lastLoginTime"><fmt:formatDate value="${profile.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
									</div>
					 			</div>
					 			<div class="profile-info-row">
									<div class="profile-info-name"> <spring:message code="label.user.lastLoginIp" />： </div>
									<div class="profile-info-value">
										<span id="lastLoginIp">${profile.lastLoginIp}</span>
									</div>
					 			</div>
					 			<div class="profile-info-row">
									<div class="profile-info-name"> <spring:message code="label.user.qq" />： </div>
									<div class="profile-info-value">
							 			<span class="editable" id="qq">${profile.qq}</span>
									</div>
					 			</div>
					 			<div class="profile-info-row">
									<div class="profile-info-name"> <spring:message code="label.user.caSn" />： </div>
									<div class="profile-info-value">
							 			<span class="editable" id="caSn">${profile.caSn}</span>
									</div>
					 			</div>
							</div>
						</div>
				 	</div><!-- /.row -->				 
	         	</div>
	         	<div id="personelTab" class="tab-pane">
	           		<form:form cssClass="form-horizontal" id="personForm" modelAttribute="personInfo" action="${ctx}/profile/updatePersonInfo" method="POST">
	           			<div class="space-10"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="persName">用户姓名:</label>
							<div class="col-sm-9">
								<form:input cssClass="input-large" id="persName" path="persName"  placeholder="用户姓名..." />
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="gender">性别:</label>
							<div class="col-sm-9">
								 <label class="inline">
									<form:radiobutton cssClass="ace" path="gender" id="gender"  value="1" />
									<span class="lbl middle">男</span>
								</label>
								&nbsp; &nbsp; &nbsp;
								<label class="inline">
									<form:radiobutton cssClass="ace" path="gender" id="gender" value="2"/>
									<span class="lbl middle">女</span>
								</label>
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="birthday">出生日期:</label>
							<div class="col-sm-9">
							    <div class="input-medium">
									<div class="input-group">
										<form:input cssClass="input-large" path="birthday" id="birthday" data-date-format="yyyy-mm-dd" placeholder="yyyy-mm-dd" />
										<span class="input-group-addon">
										    <i class="ace-icon fa fa-calendar"></i>
										</span>
									</div>
								</div>
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="birthPlace">籍贯:</label>
							<div class="col-sm-9">
								<form:input cssClass="input-large"  path="birthPlace" id="birthPlace" placeholder="籍贯..." />
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="idCertNum">身份证号:</label>
							<div class="col-sm-9">
								<span class="input-icon input-icon-right">
									<form:input cssClass="input-large input-mask-idCertNum"  id="idCertNum" path="idCertNum" placeholder="身份证号..." />
								</span>
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="empNum">员工编号:</label>
							<div class="col-sm-9">
								<span class="input-icon input-icon-right">
									<form:input cssClass="input-large" id="empNum" path="empNum"  placeholder="员工编号..." />
								</span>
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="email">电子邮件:</label>
							<div class="col-sm-9">
								<span class="input-icon input-icon-right">
									<form:input cssClass="input-large"  path="email" id="email" placeholder="电子邮件..." />
									<i class="ace-icon fa fa-envelope"></i>
								</span>
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="mobile">手机号码:</label>
							<div class="col-sm-9">
								<span class="input-icon input-icon-right">
									<form:input cssClass="input-large input-mask-mobile" id="mobile" path="mobile" placeholder="手机号码..." />
									<i class="ace-icon fa fa-mobile bigger-180"></i>
								</span>
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="telNum">电话号码:</label>
							<div class="col-sm-9">
								<span class="input-icon input-icon-right">
									<form:input cssClass="input-large input-mask-phone" id="telNum" path="telNum"  placeholder="电话号码..." />
									<i class="ace-icon fa fa-phone fa-flip-horizontal"></i>
								</span>
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="faxNum">传真号码:</label>
							<div class="col-sm-9">
								<span class="input-icon input-icon-right">
									<form:input cssClass="input-large input-mask-phone" id="faxNum" path="faxNum"  placeholder="传真号码..." />
									<i class="ace-icon fa fa-fax fa-flip-horizontal"></i>
								</span>
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="zip">邮政编码:</label>
							<div class="col-sm-9">
								<span class="input-icon input-icon-right">
									<form:input cssClass="input-large input-mask-zip" id="zip" path="zip"  placeholder="邮政编码..." />
								</span>
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="address">地址:</label>
							<div class="col-sm-9">
								<form:textarea cssClass="input-xlarge" path="address" id="address" />
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="persDesc">描述:</label>
							<div class="col-sm-9">
								<form:textarea cssClass="input-xlarge" path="persDesc" id="persDesc" />
							</div>
						</div>
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-sm btn-info" type="button" onclick="personFormSave()">
									<i class="ace-icon fa fa-check"></i>
									保存
								</button>
								&nbsp; &nbsp;
								<button class="btn btn-sm" type="reset">
									<i class="ace-icon fa fa-undo"></i>
									重置
								</button>
							</div>
					    </div>
					</form:form>  
	        	</div>
	        	<div id="passwordTab" class="tab-pane">
	           		<form class="form-horizontal" data-async id="passwordForm" role="form" action="${ctx}/profile/updatePassword" method="POST">
	           			<div class="space-10"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label no-padding-right" for="password">输入密码：</label>
							<div class="col-sm-10">
							   <span class="input-icon input-icon-right">
							   		<input type="password" id="password" name="password"  placeholder="新密码" required autofocus>
							    	<i class="ace-icon fa fa-lock"></i>
							   </span>
							</div>
						</div>
						<div class="space-4"></div>
						<div class="form-group">
							<label class="col-sm-2 control-label no-padding-right" for="confirmPassword">确认密码：</label>
							<div class="col-sm-10">
							   <span class="input-icon input-icon-right">
									<input type="password" id="confirmPassword" name="confirmPassword"  placeholder="确认密码" required> 
							        <i class="ace-icon fa fa-lock"></i>
							   </span>
							</div>
						</div>
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-sm btn-info" type="button" onclick="savePassword()">
									<i class="ace-icon fa fa-check"></i>
									保存
								</button>
								&nbsp; &nbsp;
								<button class="btn btn-sm" type="reset">
									<i class="ace-icon fa fa-undo"></i>
									重置
								</button>
							</div>
					    </div>
					</form>
	        	</div>
        	</div>
        </div>
     </div>
  </div>
</div>

