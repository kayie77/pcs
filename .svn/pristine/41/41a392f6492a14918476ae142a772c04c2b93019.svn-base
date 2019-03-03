<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script type="text/javascript">
<!--
$(document).ready(function() {
	  $.subscribe('updateArtCatComplete', function(event,data) {
		  var data=jQuery.parseJSON(event.originalEvent.request.responseText);
		    jAlert(data.message);
		    var tree = jQuery.jstree._reference("#artCatTree");
		    var currentNode = tree._get_node("#"+data.id);
		    var parentNode = tree._get_parent(currentNode);
		    tree.refresh(parentNode);
		    tree.reopen();
		   $('#id').val(data.id);
	   });
});
//-->
</script>
<ul id="formerrors" class="errorMessage ui-widget-content ui-corner-all"
	style="display: none;"></ul>
<div class="formContainer">
	<s:form id="articleCatForm" action="update" namespace="/info/category">
		<table>
			<tbody>
				<tr>
					<td style="width: 15%"><s:label key="label.articleCat.catName" />：<span
						class="required">*</span></td>
					<td style="width: 35%"><s:textfield id="catName"
							name="articleCat.catName" cssClass="w50" /><span
						id="catNameError"></span></td>
					<td style="width: 15%"><s:label key="label.articleCat.parent" />：<span
						class="required">*</span></td>
					<td style="width: 35%"><s:textfield id="parentName"
							name="articleCat.parent.catName" cssClass="w50" readonly="true" />
						<s:hidden id="pid" name="articleCat.parent.id" /></td>
				</tr>
				<tr>
					<td><s:label key="label.articleCat.description" />：</td>
					<td colspan="3"><s:textarea id="description"
							name="articleCat.description" cols="100" rows="4" /></td>
				</tr>
			</tbody>
		</table>
		<s:hidden id="id" name="articleCat.id" />
		<s:hidden id="oper" name="oper" />
		<s:hidden id="weight" name="articleCat.weight" />
	</s:form>
	<div style="text-align: center">
		<sj:a formIds="articleCatForm" targets="result"
			onCompleteTopics="updateArtCatComplete" validate="true"
			validateFunction="customeValidation" onBeforeTopics="removeErrors"
			onSuccessTopics="removeErrors" button="true"
			buttonIcon="ui-icon-disk">
			<s:text name="button.save" />
		</sj:a>
	</div>
</div>