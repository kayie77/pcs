<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script type="text/javascript">
<!--
$(document).ready(function() {
	  $.subscribe('onDelete', function(event, data) {
	       jConfirm("删除该分类，相关子分类和信息将一并被删除，确定删除吗?","确定信息框",function(yesno){
	            if(yesno){
	                var id='<s:property value="%{articleCat.id}"/>'; 
	                var data={"id":id};
	                $.ajax({
	  				   type : 'POST',
	  				   url : '<s:url  value="/info/category/delete.action"/>',
	  				   data :data,
	  				   success : function(data) {
	  					   jAlert(data.message,"消息框");
	  					   var tree = jQuery.jstree._reference("#artCatTree");
		                   tree.refresh();
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
	<s:form>
		<table>
			<tbody>
				<tr>
					<td style="width: 15%"><s:label key="label.articleCat.catName" />：</td>
					<td style="width: 35%"><s:property
							value="%{articleCat.catName}" /></td>
					<td style="width: 15%"><s:label key="label.articleCat.parent" />：</td>
					<td style="width: 35%"><s:property
							value="%{articleCat.parent.catName}" /></td>
				</tr>
				<tr>
					<td><s:label key="label.articleCat.description" />：</td>
					<td colspan="3"><s:property value="%{articleCat.description}" /></td>
				</tr>
			</tbody>
		</table>
	</s:form>
	<div style="text-align: center; margin-top: 6px;">
		<s:url var="addCat" action="input" namespace="/info/category">
			<s:param name="pid" value="%{articleCat.id}" />
		</s:url>
		<sj:a href="%{addCat}" targets="catDiv" button="true"
			buttonIcon="ui-icon-plus">
			<s:text name="button.add" />
		</sj:a>
		<s:if test="%{id!=null}">
			<s:url var="editCat" action="input" namespace="/info/category">
				<s:param name="id" value="%{articleCat.id}" />
			</s:url>
			<sj:a href="%{editCat}" targets="catDiv" button="true"
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