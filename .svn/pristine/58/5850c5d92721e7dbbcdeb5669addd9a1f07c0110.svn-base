<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<ul id="formerrors" class="errorMessage ui-widget-content ui-corner-all"
	style="display: none;"></ul>
<div id="itemResult" class="result ui-widget-content ui-corner-all"
	style="display: none;"></div>
<div class="formContainer">
	<s:form id="detailEditForm" action="update" namespace="/item/detail">
		<table>
			<tbody>
				<tr>
					<td style="width: 12%"><s:label
							key="label.itemDetail.detailCode" />：<font class="required">*</font></td>
					<td style="width: 38%"><s:textfield id="detailCode"
							name="detail.detailCode" cssClass="w70" /><span
						id="detailCodeError"></span></td>
					<td style="width: 12%"><s:label
							key="label.itemDetail.detailName" />：<font class="required">*</font></td>
					<td style="width: 38%"><s:textfield id="detailName"
							name="detail.detailName" cssClass="w70" /></span><span
						id="detailNameError"></span></td>
				</tr>
				<tr>
					<td><s:label key="label.itemDetail.catalog" />：<font
						class="required">*</font></td>
					<td><s:url var="selectCatalog" action="selectItem"
							namespace="/base/dictionary" escapeAmp="false">
							<s:param name="keyId">catalogid</s:param>
							<s:param name="valueId">catalogName</s:param>
							<s:param name="keyType">id</s:param>
							<s:param name="id">22</s:param>
						</s:url> <sj:dialog id="dictItemDialog" autoOpen="false" modal="true"
							resizable="false" title="选择品目" width="400" height="510"
							indicator="indicator"
							buttons="{
                				'取消':function() {$('#dictItemDialog').dialog('close');}
                            }" />
						<s:textfield id="catalogName" name="detail.catalog.itemName"
							cssClass="w70" readonly="true" /> <s:hidden id="catalogid"
							name="detail.catalog.id" /> <sj:a href="%{selectCatalog}"
							openDialog="dictItemDialog" cssClass="fg-button" title="选择">
							<span class="ui-icon ui-icon-search"></span>&nbsp;</sj:a><span
						id="catalogidError"></span></td>
					<td><s:label key="label.itemDetail.specification" />：</td>
					<td><s:textfield id="specification"
							name="detail.specification" cssClass="w70" /></td>
				</tr>
				<tr>
					<td><s:label key="label.itemDetail.parameter" />：</td>
					<td><s:textfield id="parameter" name="detail.parameter"
							cssClass="w70" /></td>
					<td><s:label key="label.itemDetail.unit" />：</td>
					<td><s:action id="unitData" name="listItem"
							executeResult="false" namespace="/base/dictionary">
							<s:param name="dictId" value="25" />
						</s:action> <sj:autocompleter id="unit" name="detail.unit" cssClass="w50"
							list="#unitData.itemList" listKey="itemName" listValue="itemName"
							forceValidOption="true" selectBox="true" selectBoxIcon="true"
							loadMinimumCount="2" /></td>
				</tr>
				<tr>
					<td><s:label key="label.itemDetail.quantity" />：<font
						class="required">*</font></td>
					<td><s:textfield id="detailQuantity" name="detail.quantity"
							cssClass="w70" /><span id="quantityError"></span></td>
					<td><s:label key="label.itemDetail.price" />：<font
						class="required">*</font></td>
					<td><s:textfield id="detailPrice" key="detail.price"
							value="%{getText('global.format.number',{detail.price})}"
							cssClass="w70" />元<span id="priceError"></span></td>
				</tr>
				<tr>
					<td><s:label key="label.itemDetail.amount" />：</td>
					<td><s:textfield id="detailAmount" key="detail.amount"
							value="%{getText('global.format.number6',{detail.amount})}"
							cssClass="w70" readonly="true" />万元</td>
					<td><s:label key="label.itemDetail.deliveryDate" />：</td>
					<td><sj:datepicker id="deliveryDate"
							name="detail.deliveryDate" displayFormat="yy-mm-dd"
							cssClass="w70" /></td>
				</tr>
				<tr>
					<td><s:label key="label.itemDetail.place" />：</td>
					<td><s:textfield id="place" name="detail.place" cssClass="w70" /></td>
					<td><s:label key="label.itemDetail.description" />：</td>
					<td><s:textfield id="description" name="detail.description"
							cssClass="w70" /></td>
				</tr>
			</tbody>
		</table>
		<s:hidden id="id" name="detail.id" />
		<s:hidden id="itemId" name="detail.item.id" />
		<s:hidden id="oper" name="oper" />
		<s:hidden id="lastAmount" name="detail.lastAmount" />
		<sj:submit id="submitItemDetailForm" formIds="detailEditForm"
			cssStyle="display:none" onCompleteTopics="updateDetailComplete"
			validate="true" validateFunction="customeValidation"
			onBeforeTopics="removeErrors" onSuccessTopics="removeErrors"
			value="Submit Form" targets="result" />
	</s:form>
</div>