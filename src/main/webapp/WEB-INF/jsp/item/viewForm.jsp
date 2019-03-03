<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div id="itemContent">
	<div class="formContainer">
		<s:form id="itemViewForm">
			<table>
				<tbody>
					<tr>
						<td style="width: 15%"><s:label key="label.item.itemCode" />：</td>
						<td style="width: 35%"><s:property value="%{item.itemCode}" /></td>
						<td style="width: 15%"><s:label key="label.item.itemName" />：</td>
						<td style="width: 35%"><s:property value="%{item.itemName}" /></td>
					</tr>
					<tr>
						<td><s:label key="label.item.yearNum" />：</td>
						<td><s:property value="%{item.yearNum}" />年</td>
						<td><s:label key="label.item.org" />：</td>
						<td><s:property value="%{item.org.orgName}" /></td>
					</tr>
					<tr>
						<td><s:label key="label.item.itemQuantity" />：</td>
						<td><s:property value="%{item.itemQuantity}" /></td>
						<td><s:label key="label.item.fundTotal" />：</td>
						<td><s:property
								value="%{getText('global.format.number6',{item.fundTotal})}" />万元
						</td>
					</tr>
					<tr>
						<td><s:label key="label.item.fundSource" />：</td>
						<td><s:property value="%{item.fundSource}" /></td>
						<td><s:label key="label.item.itemType" />：</td>
						<td><s:property value="%{item.itemType.itemName}" /></td>
					</tr>
					<tr>
						<td><s:label key="label.item.purchaseMethod" />：</td>
						<td><s:property value="%{item.purchaseMethod.itemName}" /></td>
						<td><s:label key="label.item.purchaseMode" />：</td>
						<td><s:property value="%{item.purchaseMode.itemName}" /></td>
					</tr>
					<tr>
						<td><s:label key="label.item.superintendent" />：</td>
						<td><s:property value="%{item.superintendent}" /></td>
						<td><s:label key="label.item.contact" />：</td>
						<td><s:property value="%{item.contact}" /></td>
					</tr>
					<tr>
						<td><s:label key="label.item.phoneNum" />：</td>
						<td><s:property value="%{item.phoneNum}" /></td>
						<td><s:label key="label.item.faxNum" />：</td>
						<td><s:property value="%{item.faxNum}" /></td>
					</tr>
					<tr>
						<td><s:label key="label.item.address" />：</td>
						<td><s:property value="%{item.address}" /></td>
						<td><s:label key="label.item.submitted" />：</td>
						<td><s:if test="item.submitted!=null ">
								<s:property
									value="%{getText('global.datetime',{item.submitted})}" />
							</s:if></td>
					</tr>
					<tr>
						<td><s:label key="label.item.description" />：</td>
						<td colspan="3"><s:property value="%{item.description}" /></td>
					</tr>
				</tbody>
			</table>
		</s:form>
	</div>
</div>
<div style="margin: 6px 0;">
	<s:url var="listDetail" action="listByItem" namespace="/item/detail">
		<s:param name="itemId" value="%{id}" />
	</s:url>
	<sjg:grid id="detailTable" dataType="json" caption="采购项目明细"
		href="%{listDetail}" pager="true" rownumbers="true" navigator="true"
		navigatorSearch="true"
		navigatorSearchOptions="{caption:'查询',multipleSearch:true}"
		navigatorAdd="false" navigatorEdit="false" navigatorView="false"
		navigatorDelete="false" gridModel="detailList" rowList="5,10,15,20"
		rowNum="5" viewrecords="true" height="150" width="917"
		footerrow="true" userDataOnFooter="true" shrinkToFit="false">
		<sjg:gridColumn name="id" index="id" title="" hidden="true"
			formatter="integer" sortable="false" search="false" width="20" />
		<sjg:gridColumn name="detailCode" index="detailCode" title="项目编码"
			sortable="true" search="false"
			searchoptions="{sopt:['eq','ne','bw','cn']}" width="200" />
		<sjg:gridColumn name="detailName" index="detailName" title="项目名称"
			sortable="false" search="true"
			searchoptions="{sopt:['eq','ne','bw','cn']}" width="200" />
		<sjg:gridColumn name="catalog" index="catalog"
			jsonmap="catalog.itemCode" title="采购品目(编码)" sortable="false"
			search="true" searchoptions="{sopt:['eq','ne','bw','cn']}"
			formatter="formatCatalog" width="250" />
		<sjg:gridColumn name="specification" index="specification"
			title="规格型号" sortable="false" search="false" width="150" />
		<sjg:gridColumn name="parameter" index="parameter" title="配置参数"
			sortable="false" search="false" width="150" />
		<sjg:gridColumn name="unit" index="unit" jsonmap="unit.itemName"
			title="计量单位" sortable="false" search="false" width="120" />
		<sjg:gridColumn name="price" index="price" title="单价(元)"
			sortable="false" search="true"
			searchoptions="{sopt:['eq','ne','ge','le']}" formatter="currency"
			formatoptions="{decimalPlaces: 2,thousandsSeparator:','}" width="70" />
		<sjg:gridColumn name="quantity" index="quantity" title="数量"
			sortable="false" search="true"
			searchoptions="{sopt:['eq','ne','ge','le']}" width="80" />
		<sjg:gridColumn name="amount" index="amount" title="金额(万元)"
			sortable="false" search="true"
			searchoptions="{sopt:['eq','ne','ge','le']}"
			formatter="numberFormatter" width="150" />
		<sjg:gridColumn name="deliveryDate" index="deliveryDate"
			title="计划(交货)竣工时间" sortable="false" search="false" formatter="date"
			formatoptions="{newformat : 'Y-m-d', srcformat : 'Y-m-d H:i:s'}"
			width="100" />
		<sjg:gridColumn name="place" index="place" title="交货地点"
			jsonmap="place" sortable="false" search="true" width="80" />
	</sjg:grid>
</div>
<div style="margin: 6px 0;">
	<s:url var="listFund" action="list" namespace="/item/fund">
		<s:param name="itemId" value="%{id}" />
	</s:url>
	<sjg:grid id="itemFundTable" dataType="json" caption="采购经费"
		href="%{listFund}" pager="true" rownumbers="true" navigator="true"
		navigatorSearch="false" navigatorAdd="false" navigatorEdit="false"
		navigatorView="false" navigatorDelete="false" gridModel="fundList"
		rowList="5,10,15,20" rowNum="5" viewrecords="true" height="130"
		width="917" footerrow="true" userDataOnFooter="true"
		shrinkToFit="false">
		<sjg:gridColumn name="id" index="id" hidden="true" title="ID"
			sortable="false" search="false" editable="false" width="20" />
		<sjg:gridColumn name="fund.id" index="id" jsonmap="id" title=""
			hidden="true" sortable="false" search="false" editable="false"
			width="20" />
		<sjg:gridColumn name="fund.budgetFund.id" index="budgetFund.id"
			jsonmap="budgetFund.id" hidden="true" title="预算资金" sortable="false"
			search="false" editable="false" width="100" />
		<sjg:gridColumn name="fund.fundType.id" index="fund.fundType.id"
			jsonmap="fundType.id" hidden="true" title="资金性质" sortable="false"
			search="false" editable="false" width="100" />
		<sjg:gridColumn name="fundTypeName" index="fundType.itemName"
			jsonmap="fundType.itemName" title="资金性质" sortable="true"
			search="false" editable="false" width="250" />
		<sjg:gridColumn name="fund.amount" index="amount" jsonmap="amount"
			title="使用金额(万元)" sortable="false" search="false" editable="false"
			formatter="currency"
			formatoptions="{decimalPlaces: 6,thousandsSeparator:','}" width="120" />
		<sjg:gridColumn name="fund.lastAmount" index="lastAmount"
			jsonmap="amount" hidden="true" title="" sortable="false"
			search="false" editable="false" width="120" />
		<sjg:gridColumn name="fund.description" index="description"
			jsonmap="description" title="备注" sortable="false" search="false"
			editable="false" width="250" />
		<sjg:gridColumn name="budget" index="budgetFund.id"
			jsonmap="budgetFund.id" title="预算资金" sortable="false" search="false"
			editable="false" formatter="formatBudgetItem" width="100" />
	</sjg:grid>
</div>