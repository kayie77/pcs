<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<s:url var="listFund" action="list" namespace="/item/fund">
	<s:param name="itemId" value="%{itemId}" />
</s:url>
<s:url var="editFundUrl" action="update" namespace="/item/fund" />
<s:url var="fundTypeSelect" action="select" namespace="/base/dictionary">
	<s:param name="dictId" value="16" />
</s:url>
<sjg:grid id="itemFundTable" dataType="json" href="%{listFund}"
	pager="true" rownumbers="true" navigator="true" navigatorSearch="false"
	navigatorAdd="false" navigatorEdit="false" navigatorView="false"
	navigatorDelete="false"
	navigatorExtraButtons="{
    	seperator: { 
    		title : 'seperator'  
    	}, 
    	addFundBtn : { 
    		caption:'新增',
	    	title : '新增资金', 
	    	icon: 'ui-icon-plus',
	    	topic: 'addFund'
    	},
    	selectFundBtn : { 
    		caption:'预算资金',
	    	title : '选择预算资金', 
	    	icon: 'ui-icon-arrowthick-1-s',
	    	topic: 'selectFund'
    	},
    		editFundBtn : { 
    		caption:'编辑',
	    	title : '编辑所选行', 
	    	icon: 'ui-icon-pencil',
	    	topic: 'editFund'
    	},
    	seperator: { 
    		title : 'seperator'  
    	},
    	deleteFundBtn : { 
    		caption:'删除',
	    	title : '删除所选行', 
	    	icon: 'ui-icon-minus',
	    	topic: 'deleteFund'
    	}
    }"
	gridModel="fundList" rowList="5,10,15,20" rowNum="5"
	editurl="%{editFundUrl}" viewrecords="true" height="160" width="860"
	footerrow="true" userDataOnFooter="true" shrinkToFit="false">
	<sjg:gridColumn name="id" index="id" hidden="true" title="ID"
		sortable="false" search="false" editable="true" width="20" />
	<sjg:gridColumn name="fund.id" index="id" jsonmap="id" title=""
		hidden="true" sortable="false" search="false" editable="true"
		width="20" />
	<sjg:gridColumn name="fund.budgetFund.id" index="budgetFund.id"
		jsonmap="budgetFund.id" hidden="true" title="预算资金" sortable="false"
		search="false" editable="true" width="100" />
	<sjg:gridColumn name="fund.fundType.id" index="fundType.id"
		jsonmap="fundType.id" hidden="true" title="资金性质" sortable="false"
		search="false" editable="true" edittype="select"
		editoptions="{dataUrl : '%{fundTypeSelect}'}"
		editrules="{edithidden: true}" width="100" />
	<sjg:gridColumn name="fundTypeName" index="fundType.itemName"
		jsonmap="fundType.itemName" title="资金性质" sortable="true"
		search="false" editable="false" width="250" />
	<sjg:gridColumn name="balance" index="budgetFund.balance"
		jsonmap="budgetFund.balance" title="可用余额(万元)" sortable="false"
		search="false" editable="false" formatter="numberFormatter"
		width="100" />
	<sjg:gridColumn name="fund.amount" index="amount" jsonmap="amount"
		title="使用金额(万元)" sortable="false" search="false" editable="true"
		editoptions="{defaultValue:'0.00'}"
		editrules="{custom:true, custom_func:amountCheck}"
		formatter="numberFormatter" width="120" />
	<sjg:gridColumn name="fund.lastAmount" index="lastAmount"
		jsonmap="amount" hidden="true" title="" sortable="false"
		search="false" editable="true" editoptions="{defaultValue:'0.00'}"
		formatter="numberFormatter" width="120" />
	<sjg:gridColumn name="fund.description" index="description"
		jsonmap="description" title="备注" sortable="false" search="false"
		editable="true" width="250" />
	<sjg:gridColumn name="budget" index="budgetFund.id"
		jsonmap="budgetFund.id" title="部门预算资金" sortable="false" search="false"
		editable="false" formatter="formatBudgetItem" width="100" />
</sjg:grid>