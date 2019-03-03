<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<ul id="formerrors" class="errorMessage ui-widget-content ui-corner-all"
	style="display: none;"></ul>
<div class="formContainer">
	<s:form id="divisionForm" action="update" namespace="/base/division">
		<table style="width: 100%">
			<tbody>
				<tr>
					<td style="width: 15%"><s:label
							key="label.division.divisionCode" />：<span class="required">*</span></td>
					<td style="width: 35%"><s:textfield id="divisionCode"
							name="division.divisionCode" maxlength="12" cssClass="w50" /><span
						id="divisionCodeError"></span></td>
					<td style="width: 15%"><s:label
							key="label.division.divisionName" />：<span class="required">*</span></td>
					<td style="width: 35%"><s:textfield id="divisionName"
							name="division.divisionName" cssClass="w50" /><span
						id="divisionNameError"></span></td>
				</tr>
				<tr>
					<td><s:label key="label.division.parent" />：</td>
					<td><s:textfield id="parentName" name="parentName"
							value="%{division.parent.divisionName}" cssClass="w50"
							readonly="true" />
						<s:hidden id="pid" name="division.parent.id" /></td>
					<td><s:label key="label.division.mnemonicCode" />：</td>
					<td><s:textfield id="mnemonicCode"
							name="division.mnemonicCode" cssClass="w50" /></td>
				</tr>
				<tr>
					<td><s:label key="label.division.description" />：</td>
					<td colspan="3"><s:textarea id="description"
							name="division.description" cssClass="w96" /></td>
				</tr>
			</tbody>
		</table>
		<s:hidden id="id" name="division.id" />
		<s:hidden id="oper" name="oper" />
		<sj:submit id="submitDivisionForm" formIds="divisionForm"
			cssStyle="display:none" onCompleteTopics="updateDivisionComplete"
			validate="true" validateFunction="customeValidation"
			onBeforeTopics="removeErrors" onSuccessTopics="removeErrors"
			value="Submit Form" targets="result" />
	</s:form>
</div>