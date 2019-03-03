<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<script type="text/javascript">
$(document).ready(function() {
    $.subscribe('listItemDetail', function(event,data) {
		 var rowId = jQuery("#"+data.id).jqGrid('getGridParam','selrow');
		 if(rowId){
			 jQuery("#itemDetailTable").jqGrid('setGridParam',{url:"<s:url value="/item/detail/listByItem.action"/>?itemId="+rowId,page:1});
			 jQuery("#itemDetailTable").trigger('reloadGrid');
		 }
	 });
    
    $.subscribe('addSelectDetail', function(event,data) {
    	 var selectedIds = jQuery("#"+data.id).jqGrid('getGridParam','selarrrow');
    	 var detailIds = [];
    	 if (selectedIds==null || selectedIds.length==0){
    		 jAlert("请选择要添加的项目明细项!");
    		 return false;
    	 }
    	 for(i=0;i<selectedIds.length;i++){
    		 detailIds.push(selectedIds[i]);
    	 }
        var purchaseId= $("#id").val();
        var params={"details":detailIds,"id":purchaseId};
    	$.ajax({
    		  		type : 'POST',
    		  		url : '<s:url value="/purchase/addDetails.action"/>',
    		  		data :jQuery.param(params,true),
    		  		success : function(data) {
    		  			   jAlert(data.message,"消息框");
    		  			  $.publish("updatePurchaseAmount");
    		  		      $.publish("reloadPurchaseDetail");
    		  		},
    		  		dataType : 'JSON'
    	 });		
    	$('#itemDetailDialog').dialog('close'); 
	 });
});

</script>
<s:url var="listApproved" action="listApproved" namespace="/item" />
<div>
	<sjg:grid dataType="json" caption="采购项目" href="%{listApproved}"
		pager="true" rownumbers="true" navigator="true" navigatorSearch="true"
		navigatorSearchOptions="{caption:'查询',multipleSearch:true}"
		navigatorAdd="false" navigatorEdit="false" navigatorView="false"
		navigatorDelete="false" gridModel="itemList"
		onSelectRowTopics="listItemDetail" rowList="10,15,20" rowNum="10"
		viewrecords="true" height="150" width="760" shrinkToFit="false">
		<sjg:gridColumn name="id" index="id" hidden="true" title="#ID"
			sortable="false" width="50" />
		<sjg:gridColumn name="itemCode" index="itemCode" title="明细项目编号"
			sortable="true" search="true"
			searchoptions="{sopt:['eq','ne','bw','cn']}" width="150" />
		<sjg:gridColumn name="itemName" index="itemName" title="项目项目名称"
			sortable="true" search="true"
			searchoptions="{sopt:['eq','ne','bw','cn']}" width="180" />
		<sjg:gridColumn name="org" index="org" jsonmap="org.orgName"
			title="采购单位" sortable="true" search="true"
			searchoptions="{sopt:['eq','ne','bw','cn']}" formatter="formatOrg"
			width="180" />
		<sjg:gridColumn name="fundTotal" index="fundTotal" title="采购预算(万元)"
			sortable="false" search="false"
			searchoptions="{sopt:['eq','ne','ge','le']}"
			formatter="numberFormatter" width="150" />
		<sjg:gridColumn name="purchaseMethod" index="purchaseMethod"
			jsonmap="purchaseMethod.itemName" title="采购方式" sortable="false"
			search="true" searchoptions="{sopt:['eq','ne']}" width="100" />
		<sjg:gridColumn name="purchaseMode" index="purchaseMode"
			jsonmap="purchaseMode.itemName" title="组织方式" sortable="false"
			search="true" searchoptions="{sopt:['eq','ne']}" width="100" />
		<sjg:gridColumn name="subContract" index="subContract" title="分包情况"
			sortable="true" search="false" width="180" />
		<sjg:gridColumn name="superintendent" index="superintendent"
			title="项目负责人" sortable="false" search="true"
			searchoptions="{sopt:['eq','ne','bw','cn']}" width="100" />
		<sjg:gridColumn name="contact" index="contact" title="联系人"
			sortable="false" search="true"
			searchoptions="{sopt:['eq','ne','bw','cn']}" width="100" />
		<sjg:gridColumn name="phoneNum" index="phoneNum" title="电话号码"
			sortable="false" search="false" width="100" />
		<sjg:gridColumn name="faxNum" index="faxNum" title="传真"
			sortable="false" search="false" width="100" />
		<sjg:gridColumn name="address" index="address" title="地址"
			sortable="false" search="false" width="180" />
		<sjg:gridColumn name="submitted" index="submitted" title="上报日期"
			sortable="true" search="false" searchoptions="{sopt:['eq','ne']}"
			formatter="date"
			formatoptions="{newformat : 'Y-m-d', srcformat : 'Y-m-d H:i:s'}"
			width="100" />
	</sjg:grid>
</div>
<div style="margin: 6px 0;">
	<s:url var="listDetail" action="listByItem" namespace="/item/detail" />
	<sjg:grid id="itemDetailTable" dataType="json" caption="采购项目明细"
		href="%{listDetail}" pager="true" rownumbers="true" multiselect="true"
		multiboxonly="true" navigator="true" navigatorSearch="true"
		navigatorSearchOptions="{caption:'查询',multipleSearch:true}"
		navigatorAdd="false" navigatorEdit="false" navigatorView="false"
		navigatorDelete="false"
		navigatorExtraButtons="{
    					seperator: { 
    						title : 'seperator'  
    					}, 
    					addItemBtn : { 
    		  			    caption:'增加',
	    					title : '增加记录到采购表', 
	    					icon: 'ui-icon-plus',
	    					topic: 'addSelectDetail'
    					}
    				}"
		gridModel="detailList" rowList="5,10,15,20" rowNum="5"
		viewrecords="true" height="150" width="760" footerrow="true"
		userDataOnFooter="true" shrinkToFit="false">
		<sjg:gridColumn name="id" index="id" title="" hidden="true"
			formatter="integer" sortable="false" search="false" width="20" />
		<sjg:gridColumn name="detailCode" index="detailCode" title="项目明细编码"
			sortable="false" search="true"
			searchoptions="{sopt:['eq','ne','bw','cn']}" width="200" />
		<sjg:gridColumn name="detailName" index="detailName" title="项目明细名称"
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
		<sjg:gridColumn name="unit" index="unit" title="计量单位" sortable="false"
			search="false" width="120" />
		<sjg:gridColumn name="price" index="price" title="单价(万元)"
			sortable="false" search="true"
			searchoptions="{sopt:['eq','ne','ge','le']}" formatter="currency"
			formatoptions="{thousandsSeparator:','}" width="70" />
		<sjg:gridColumn name="quantity" index="quantity" title="数量"
			sortable="false" search="true"
			searchoptions="{sopt:['eq','ne','ge','le']}" formatter="integer"
			width="80" />
		<sjg:gridColumn name="amount" index="amount" title="金额(万元)"
			sortable="false" search="true"
			searchoptions="{sopt:['eq','ne','ge','le']}" formatter="currency"
			formatoptions="{thousandsSeparator:','}" width="150" />
		<sjg:gridColumn name="deliveryDate" index="deliveryDate"
			title="计划(交货)竣工时间" sortable="false" search="true" formatter="date"
			formatoptions="{newformat : 'Y-m-d', srcformat : 'Y-m-d H:i:s'}"
			width="100" />
		<sjg:gridColumn name="place" index="place" title="交货地点"
			jsonmap="place" sortable="false" search="true" width="80" />
	</sjg:grid>
</div>