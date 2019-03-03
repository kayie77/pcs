<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<ul id="formerrors" class="errorMessage ui-widget-content ui-corner-all"
	style="display: none;"></ul>
<div id="result" class="result ui-widget-content ui-corner-all"
	style="display: none;"></div>
<div class="formContainer">
	<s:form id="itemForm" action="update" namespace="/item">
		<table>
			<tbody>
				<tr>
					<td style="width: 15%"><s:label key="label.item.itemCode" />：</td>
					<td style="width: 35%"><s:textfield id="itemCode"
							name="item.itemCode" cssClass="w60" readOnly="true" /></td>
					<td style="width: 15%"><s:label key="label.item.itemName" />：<span
						class="required">*</span></td>
					<td style="width: 35%"><s:textfield id="itemName"
							name="item.itemName" cssClass="w60" /><span id="itemNameError"></span></td>
				</tr>
				<tr>
					<td><s:label key="label.item.yearNum" />：<span
						class="required">*</span></td>
					<td><s:textfield id="yearNum" name="item.yearNum"
							cssClass="w60" /><span id="yearNumError"></span></td>
					<td><s:label key="label.item.org" />：<span class="required">*</span></td>
					<td><s:url var="selectOrg" action="selectOrg"
							namespace="/admin/org" escapeAmp="false">
							<s:param name="keyId">orgId</s:param>
							<s:param name="valueId">orgorgName</s:param>
							<s:param name="multiSelect">false</s:param>
						</s:url> <sj:dialog id="orgDialog" autoOpen="false" modal="true"
							resizable="false" title="选择机构" width="360" height="510"
							indicator="indicator"
							buttons="{
                				'取消':function() {$('#orgDialog').dialog('close');}
                            }" />
						<s:textfield id="orgorgName" name="item.org.orgName"
							cssClass="w60" /> <s:hidden id="orgId" name="item.org.id" /> <sj:a
							href="%{selectOrg}" openDialog="orgDialog" cssClass="fg-button"
							title="选择">
							<span class="ui-icon ui-icon-search"></span>&nbsp;</sj:a><span
						id="orgorgNameError"></span></td>
					</td>
				</tr>
				<tr>
					<td><s:label key="label.item.itemQuantity" />：<span
						class="required">*</span></td>
					<td><s:textfield id="itemQuantity" name="item.itemQuantity"
							cssClass="w60" /><span id="itemQuantityError"></span></td>
					<td><s:label key="label.item.fundTotal" />：<span
						class="required">*</span></td>
					<td><s:textfield id="fundTotal" name="item.fundTotal"
							cssClass="w60" />万元 <span id="fundTotalError"></span></td>
				</tr>
				<tr>
					<td><s:label key="label.item.fundSource" />：<span
						class="required">*</span></td>
					<td><s:textfield id="fundSource" key="item.fundSource"
							cssClass="w60" /></td>
					<td><s:label key="label.item.itemType" />：</td>
					<td><s:action id="itemTypeData" name="listItem"
							executeResult="false" namespace="/base/dictionary">
							<s:param name="dictId" value="19" />
						</s:action> <sj:autocompleter id="itemTypeid" name="item.itemType.id"
							cssClass="w50" list="#itemTypeData.itemList" listKey="id"
							listValue="itemName" forceValidOption="true" selectBox="true"
							selectBoxIcon="true" loadMinimumCount="2" /><span
						id="itemTypeidError"></span></td>
				</tr>
				<tr>
					<td><s:label key="label.item.purchaseMethod" />：<span
						class="required">*</span></td>
					<td><s:action id="purchaseMethodData" name="listItem"
							executeResult="false" namespace="/base/dictionary">
							<s:param name="dictId" value="21" />
						</s:action> <sj:autocompleter id="purchaseMethodid"
							name="item.purchaseMethod.id" cssStyle="width:300px;"
							list="#purchaseMethodData.itemList" listKey="id"
							listValue="itemName" forceValidOption="true" selectBox="true"
							selectBoxIcon="true" loadMinimumCount="2" /><span
						id="purchaseMethodidError"></span></td>
					<td><s:label key="label.item.purchaseMode" />：<span
						class="required">*</span></td>
					<td><s:action id="purchaseModeData" name="listItem"
							executeResult="false" namespace="/base/dictionary">
							<s:param name="dictId" value="20" />
						</s:action> <sj:autocompleter id="purchaseModeId" name="item.purchaseMode.id"
							cssStyle="width:300px;" list="#purchaseModeData.itemList"
							listKey="id" listValue="itemName" selectBox="true"
							selectBoxIcon="true" forceValidOption="true" loadMinimumCount="2" /><span
						id="purchaseModeidError"></span></td>
				</tr>
				<tr>
					<td><s:label key="label.item.superintendent" />：<span
						class="required">*</span></td>
					<td><s:textfield id="superintendent"
							name="item.superintendent" cssClass="w60" /><span
						id="superintendentError"></span></td>
					<td><s:label key="label.item.contact" />：</td>
					<td><s:textfield id="contact" name="item.contact"
							cssClass="w60" /></td>
				</tr>
				<tr>
					<td><s:label key="label.item.phoneNum" />：</td>
					<td><s:textfield id="phoneNum" name="item.phoneNum"
							cssClass="w60" /></td>
					<td><s:label key="label.item.faxNum" />：</td>
					<td><s:textfield id="faxNum" name="item.faxNum" cssClass="w60" /></td>
				</tr>
				<tr>
					<td><s:label key="label.item.address" />：</td>
					<td><s:textfield id="address" name="item.address"
							cssClass="w60" /></td>
					<td><s:label key="label.item.submitted" />：<span
						class="required">*</span></td>
					<td><sj:datepicker id="submitted" name="item.submitted"
							timepicker="true" timepickerShowSecond="true"
							timepickerFormat="hh:mm:ss" displayFormat="yy-mm-dd"
							cssClass="w60" /><span id="submittedError"></span></td>
				</tr>
				<tr>
					<td><s:label key="label.item.description" />：</td>
					<td colspan="3"><s:textarea id="description"
							name="item.description" cssClass="w96" rows="3" /></td>
				</tr>
			</tbody>
		</table>
		<s:hidden id="id" name="item.id" />
		<s:hidden id="created" name="item.created" />
		<s:hidden id="oper" name="oper" />
		<s:hidden id="username" name="item.username" />
		<sj:submit id="submitItemForm" formIds="itemForm"
			cssStyle="display:none" onCompleteTopics="updateItemComplete"
			validate="true" validateFunction="customeValidation"
			onBeforeTopics="removeErrors" onSuccessTopics="removeErrors"
			value="Submit Form" targets="result" />
	</s:form>
</div>