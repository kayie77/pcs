<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script type="text/javascript">
<!--
$(document).ready(function() {
	  $.subscribe('onDelete', function(event, data) {
	       jConfirm("删除该资源，相关下级机构将一并被删除，确定删除吗?","确定信息框",function(yesno){
	            if(yesno){
	                var id='<s:property value="%{org.id}"/>'; 
	                var data={"id":id};
	                $.ajax({
	  				   type : 'POST',
	  				   url : '<s:url  value="/admin/org/delete.action"/>',
	  				   data :data,
	  				   success : function(data) {
	  					   jAlert(data.message,"消息框");
	  					   var tree = jQuery.jstree._reference("#orgTree");
		                   tree.refresh();
		                   $.publish("reloadOrg");
	  				  },
	  				  dataType : 'JSON'
	  		       });
	            }
	       });
	 }); 
});
//-->
</script>
<div class="formContainer">
	<s:form id="orgForm">
		<table width="100%">
			<tbody>
				<tr>
					<td style="width: 15%"><s:label key="label.org.orgCode" />：</td>
					<td style="width: 35%"><s:property value="%{org.orgCode}" /></td>
					<td style="width: 15%"><s:label key="label.org.orgName" />：</td>
					<td style="width: 35%"><s:property value="%{org.orgName}" /></td>
				</tr>
				<tr>
					<td><s:label key="label.org.divisionCode" />：</td>
					<td><s:property value="%{org.divisionCode}" /></td>
					<td><s:label key="label.org.mnemonicCode" />：</td>
					<td><s:property value="%{org.mnemonicCode}" /></td>
				</tr>
				<tr>
					<td><s:label key="label.org.orgType" />：</td>
					<td><s:property value="%{org.orgType.itemName}" /></td>
					<td><s:label key="label.org.parent" />：</td>
					<td><s:property value="%{org.parent.orgName}" /></td>
				</tr>
				<tr>
					<td><s:label key="label.org.director" />：</td>
					<td><s:property value="%{org.directo}r" /></td>
					<td><s:label key="label.org.contact" />：</td>
					<td><s:property value="%{org.contact}" /></td>
				</tr>
				<tr>
					<td><s:label key="label.org.phoneNum" />：</td>
					<td><s:property value="%{org.phoneNum}" /></td>
					<td><s:label key="label.org.faxNum" />：</td>
					<td><s:property value="%{org.faxNum}" /></td>
				</tr>
				<tr>
					<td><s:label key="label.org.mobile" />：</td>
					<td><s:property value="%{org.mobile}" /></td>
					<td><s:label key="label.org.email" />：</td>
					<td><s:property value="%{org.email}" /></td>
				</tr>
				<tr>
					<td><s:label key="label.org.zip" />：</td>
					<td><s:property value="%{org.zip}" /></td>
					<td><s:label key="label.org.website" />：</td>
					<td><s:property value="%{org.website}" /></td>
				</tr>
				<tr>
					<td><s:label key="label.org.address" />：</td>
					<td colspan="3"><s:property value="%{org.address}" /></td>
				</tr>
				<tr>
					<td><s:label key="label.org.description" />：</td>
					<td colspan="3"><s:property value="%{org.description}" /></td>
				</tr>
			</tbody>
			</tbody>
		</table>
	</s:form>
	<div style="text-align: center; margin-top: 6px;">
		<s:url var="addOrg" action="input" namespace="/admin/org">
			<s:param name="pid" value="%{org.id}" />
		</s:url>
		<sj:a href="%{addOrg}" targets="orgDiv" button="true"
			buttonIcon="ui-icon-plus">
			<s:text name="button.add" />
		</sj:a>
		<s:if test="%{id!=null}">
			<s:url var="editOrg" action="input" namespace="/admin/org">
				<s:param name="id" value="%{org.id}" />
			</s:url>
			<sj:a href="%{editOrg}" targets="orgDiv" button="true"
				buttonIcon="ui-icon-pencil">
				<s:text name="button.edit" />
			</sj:a>
			<sj:a onClickTopics="onDelete" button="true"
				buttonIcon="ui-icon-minus">
				<s:text name="button.delete" />
			</sj:a>
		</s:if>
	</div>
</div>