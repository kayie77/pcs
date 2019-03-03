<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<s:url var="listDetail" action="listByItem" namespace="/item/detail">
	<s:param name="itemId" value="%{itemId}" />
</s:url>
<s:url var="editDetailUrl" action="delete" namespace="/item/detail" />
<sjg:grid id="itemDetailTable" dataType="json" href="%{listDetail}"
	pager="true" rownumbers="true" navigator="true" navigatorSearch="true"
	navigatorSearchOptions="{caption:'查询',multipleSearch:true}"
	navigatorAdd="false" navigatorEdit="false" navigatorView="false"
	navigatorDelete="false"
	navigatorExtraButtons="{
    	seperator: { 
    		title : 'seperator'  
    	}, 
    	addDetailBtn : { 
    		caption:'新增',
	    	title : '新增记录', 
	    	icon: 'ui-icon-plus',
	    	topic:'addDetail'
    	},
    	editDetailBtn : { 
    		caption:'编辑',
	    	title : '编辑记录', 
	    	icon: 'ui-icon-pencil',
	    	topic:'editDetail'
    	},
    	seperator: { 
    		title : 'seperator'  
    	},
    	deleteDetailBtn : { 
    		caption:'删除',
	    	title : '删除记录', 
	    	icon: 'ui-icon-minus',
	    	topic:'deleteDetail'
    	}
    }"
	gridModel="detailList" rowList="5,10,15,20" rowNum="5"
	editurl="%{editDetailUrl}" viewrecords="true" height="160" width="860"
	footerrow="true" userDataOnFooter="true" shrinkToFit="false">
	<sjg:gridColumn name="id" index="id" title="" hidden="true"
		formatter="integer" sortable="false" search="false" width="20" />
	<sjg:gridColumn name="detailCode" index="detailCode" title="明细项目编码"
		sortable="true" search="false"
		searchoptions="{sopt:['eq','ne','bw','cn']}" width="200" />
	<sjg:gridColumn name="detailName" index="detailName" title="明细项目名称"
		sortable="false" search="true"
		searchoptions="{sopt:['eq','ne','bw','cn']}" width="200" />
	<sjg:gridColumn name="catalog" index="catalog"
		jsonmap="catalog.itemCode" title="采购品目(编码)" sortable="false"
		search="true" searchoptions="{sopt:['eq','ne','bw','cn']}"
		formatter="formatCatalog" width="250" />
	<sjg:gridColumn name="specification" index="specification" title="规格型号"
		sortable="false" search="false" width="150" />
	<sjg:gridColumn name="parameter" index="parameter" title="配置参数"
		sortable="false" search="false" width="150" />
	<sjg:gridColumn name="unit" index="unit" title="计量单位" sortable="false"
		search="false" width="120" />
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
		title="计划(交货)竣工时间" sortable="false" search="true" formatter="date"
		formatoptions="{newformat : 'Y-m-d', srcformat : 'Y-m-d H:i:s'}"
		width="100" />
	<sjg:gridColumn name="place" index="place" title="交货地点" jsonmap="place"
		sortable="false" search="true" width="80" />
</sjg:grid>
