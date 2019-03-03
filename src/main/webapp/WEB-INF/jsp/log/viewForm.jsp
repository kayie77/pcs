<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="modal-dialog" style="width:700px;">
	<div class="modal-content">
	 	 <div class="modal-header">
	     	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	     	<h3 class="modal-title">日志信息</h3>
	 	 </div>
 	 	<div class="modal-body">
			<table width="100%">
				<tbody>
					<tr>
						<td style="width: 15%">登录名：</td>
						<td style="width: 35%">${log.username}</td>
						<td style="width: 15%">用户姓名：</td>
						<td style="width: 35%">${log.fullName}</td>
					</tr>
					<tr>
						<td>ip地址：</td>
						<td>${log.ipAddress}</td>
						<td>操作时间：</td>
						<td>${log.operDate}</td>
					</tr>
					<tr>
						<td>操作内容：</td>
						<td colspan="3">${log.operDesc}</td>
					</tr>
				</tbody>
			</table>
  	 	</div>
		<div class="modal-footer">
		   	<button type="button" class="btn btn-primary btn-sm" data-dismiss="modal" aria-hidden="true">
		  		<i class="icon-times"></i>         			
		  		关闭
		 	</button>
		</div>
	</div>
</div>
