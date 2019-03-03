<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="formContainer">
	<s:form id="detailViewForm">
		<table>
			<tbody>
				<tr>
					<td style="width: 12%"><s:label key="label.itemDetail.org" />：</td>
					<td style="width: 38%"><s:property
							value="itemDetail.org.orgName" /></td>
					<td style="width: 12%"><s:label
							key="label.itemDetail.detailCode" />：</td>
					<td style="width: 38%"><s:property
							value="itemDetail.detailCode" /></td>
				</tr>
				<tr>
					<td><s:label key="label.itemDetail.detailName" />：</td>
					<td><s:property value="itemDetail.detailName" /></td>
					<td><s:label key="label.itemDetail.catalog" />：</td>
					<td><s:property value="itemDetail.catalog.itemName" /></td>
				</tr>
				<tr>
					<td><s:label key="label.itemDetail.specification" />：</td>
					<td><s:property value="itemDetail.specification" /></td>
					<td><s:label key="label.itemDetail.parameter" />：</td>
					<td><s:property value="itemDetail.parameter" /></td>
				</tr>
				<tr>
					<td><s:label key="label.itemDetail.unit" />：</td>
					<td><s:property value="itemDetail.unit" /></td>
					<td><s:label key="label.itemDetail.price" />：</td>
					<td><s:property
							value="%{getText('global.format.number',{itemDetail.price})}" />元</td>
				</tr>
				<tr>
					<td><s:label key="label.itemDetail.quantity" />：</td>
					<td><s:property value="itemDetail.quantity" /></td>
					<td><s:label key="label.itemDetail.amount" />：</td>
					<td><s:property
							value="%{getText('global.format.number6',{itemDetail.amount})}" />万元</td>
				</tr>
				<tr>
					<td><s:label key="label.itemDetail.deliveryDate" />：</td>
					<td><s:property
							value="%{getText('global.format.datet',{itemDetail.deliveryDate})}" /></td>
					<td><s:label key="label.itemDetail.place" />：</td>
					<td><s:property value="itemDetail.place" /></td>
				</tr>
				<tr>
					<td><s:label key="label.itemDetail.description" />：</td>
					<td colspan="3"><s:property value="itemDetail.description" /></td>

				</tr>
			</tbody>
		</table>
	</s:form>
</div>
<div style="margin: 0 auto; text-align: center">
	<sj:a onClickTopics="closeDetailDialog" button="true"
		buttonIcon="ui-icon-close">
		<s:text name="button.close" />
	</sj:a>
</div>