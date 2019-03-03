<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<script type="text/javascript">
$(document).ready(function() {
	 $.subscribe('addSelectItem', function(event,data) {
		 var selectedIds = jQuery("#"+data.id).jqGrid('getGridParam','selarrrow');
		 var itemIds = [];
		 if (selectedIds==null || selectedIds.length==0){
			 jAlert("请选择要添加的采购项目!");
			 return false;
		 }
		 var rowData;
		 for(i=0;i<selectedIds.length;i++){
			 rowData = jQuery("#"+data.id).jqGrid('getRowData',selectedIds[i]); 
			 itemIds.push(selectedIds[i]);
		} 
	    var planId= $("#id").val();
	    var params={"items":itemIds,"id":planId};
		$.ajax({
			  		type : 'POST',
			  		url : '<s:url value="/plan/addItems.action"/>',
			  		data :jQuery.param(params,true),
			  		success : function(data) {
			  			   jAlert(data.message,"消息框");
			  			   jQuery("#"+data.id).trigger('reloadGrid');
			  			  $.publish('reloadPlanItem');
			  		},
			  		dataType : 'JSON'
		 });		
		$('#itemDialog').dialog('close'); 
	 });
});
 function addSelectItem() {
	 var selectedIds = jQuery("#itemTable").jqGrid('getGridParam','selarrrow');
	 var itemIds = [];
	 if (selectedIds==null || selectedIds.length==0){
		 jAlert("请选择要添加的采购项目!");
		 return false;
	 }
	 var rowData;
	 for(i=0;i<selectedIds.length;i++){
		 rowData = jQuery("#itemTable").jqGrid('getRowData',selectedIds[i]); 
		 itemIds.push(selectedIds[i]);
	} 
    var planId= $("#id").val();
    var params={"items":itemIds,"id":planId};
	$.ajax({
		  		type : 'POST',
		  		url : '<s:url value="/plan/addItems.action"/>',
		  		data :jQuery.param(params,true),
		  		success : function(data) {
		  			   jAlert(data.message,"消息框");
		  			   jQuery("#itemTable").trigger('reloadGrid');
		  			    jQuery("#planItemTable").trigger('reloadGrid');
		  		},
		  		dataType : 'JSON'
	 });		
	$('#itemDialog').dialog('close'); 
}
</script>
<s:url var="listToAdd" action="listToAdd" namespace="/item" />
<sjg:grid dataType="json" caption="采购项目" href="%{listToAdd}"
	pager="true" rownumbers="true" multiselect="true" multiboxonly="true"
	navigator="true" navigatorSearch="true"
	navigatorSearchOptions="{caption:'查询',multipleSearch:true}"
	navigatorAdd="false" navigatorEdit="false" navigatorView="false"
	navigatorDelete="false"
	navigatorExtraButtons="{
    					seperator: { 
    						title : 'seperator'  
    					}, 
    					addItemBtn : { 
    		  			    caption:'加入',
	    					title : '加入项目到计划', 
	    					icon: 'ui-icon-plus',
	    					topic: 'addSelectItem'
    					}
    				}"
	gridModel="itemList" rowList="10,15,20" rowNum="10" viewrecords="true"
	height="200" width="760" shrinkToFit="false">
	<sjg:gridColumn name="id" index="id" hidden="true" title="#ID"
		sortable="false" width="50" />
	<sjg:gridColumn name="itemCode" index="itemCode" title="项目编号"
		sortable="true" search="true"
		searchoptions="{sopt:['eq','ne','bw','cn']}" width="150" />
	<sjg:gridColumn name="itemName" index="itemName" title="项目名称"
		sortable="true" search="true"
		searchoptions="{sopt:['eq','ne','bw','cn']}" width="180" />
	<sjg:gridColumn name="org" index="org" jsonmap="org.orgName"
		title="采购单位" sortable="true" search="true"
		searchoptions="{sopt:['eq','ne','bw','cn']}" width="180" />
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
	<sjg:gridColumn name="status" index="status" title="状态" sortable="true"
		search="true" searchoptions="{sopt:['eq','ne']}" edittype="select"
		editoptions="{value:'0:草稿;1:待审 ;2:审批通过;3:审批未通过'}" formatter="select"
		width="80" />
	<sjg:gridColumn name="submitted" index="submitted" title="上报日期"
		sortable="true" search="false" searchoptions="{sopt:['eq','ne']}"
		formatter="date"
		formatoptions="{newformat : 'Y-m-d', srcformat : 'Y-m-d H:i:s'}"
		width="100" />
	<sjg:gridColumn name="username" index="username" title="录入人"
		sortable="true" search="false" width="100" />
	<sjg:gridColumn name="created" index="created" title="创建日期"
		sortable="true" search="false" formatter="date"
		formatoptions="{newformat : 'Y-m-d', srcformat : 'Y-m-d H:i:s'}"
		width="100" />
</sjg:grid>