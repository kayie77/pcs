<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="formContainer">
	<s:form id="divisionForm">
		<table>
			<tbody>
				<tr>
					<td style="width: 15%"><s:label
							key="label.division.divisionCode" />：</td>
					<td style="width: 35%"><s:property
							value="%{division.divisionCode}" /></td>
					<td style="width: 15%"><s:label
							key="label.division.divisionName" />：</td>
					<td style="width: 35%"><s:property
							value="%{division.divisionName}" /></td>
				</tr>
				<tr>
					<td><s:label key="label.division.parent" />：</td>
					<td><s:property value="%{division.parent.divisionName}" /></td>
					<td><s:label key="label.division.mnemonicCode" />：</td>
					<td><s:property value="%{division.mnemonicCode}" /></td>
				</tr>
				<tr>
					<td><s:label key="label.division.description" />：</td>
					<td colspan="3"><s:property value="%{division.description}" /></td>
				</tr>
			</tbody>
		</table>
	</s:form>
</div>