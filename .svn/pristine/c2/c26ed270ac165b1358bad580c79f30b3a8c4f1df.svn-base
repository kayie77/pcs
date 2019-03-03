<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<script type="text/javascript">
$(document).ready(function() {
	jQuery.struts2_jquery.require("js/base/jquery.ui.datepicker.min.js");
	jQuery.struts2_jquery.require("i18n/jquery.ui.datepicker-zh-CN.min.js");
	 
	$.subscribe('viewItem', function(event,data) {
		 var rowId = jQuery("#"+data.id).jqGrid('getGridParam','selrow');
		 if(rowId){
			 $('#itemViewDialog').empty().load("<s:url value="/item/viewForm.action"/>",{id:rowId}).dialog('open');
			 return false;
		 }else{
			 jAlert("请选择要查看的纪录!");
		 }			 
    });
	  
	  	  	  
	  $.subscribe('listActivity', function(event,data) {
		    var rowId = jQuery("#"+data.id).jqGrid('getGridParam','selrow');
		    if(rowId){
		    	jQuery("#activityTable").jqGrid('setGridParam',{url:"<s:url value="/item/activity/list.action"/>?itemId="+rowId,page:1});
				 jQuery("#activityTable").trigger('reloadGrid');
		    }else{
				 jAlert("请选择采购项目!");
			 }		 
	  });
	  
	  $.subscribe('checkState', function(event,data) {
		    var rowId = jQuery("#"+data.id).jqGrid('getGridParam','selrow');
		    if(rowId){
		    	var rowData= jQuery("#"+data.id).jqGrid('getRowData',rowId);
		    	if(rowData["activity.editable"]=="false"){
					 $('#editActivityBtn').hide();
					 return false;
					 return false;
				}else{
					var index= jQuery("#"+data.id).jqGrid('getInd',rowId)-1;
			    	var ids = jQuery("#"+data.id).jqGrid('getDataIDs');
			    	if(index>0){
			    		var prevId=ids[index-1];
			    		var prevRowData= jQuery("#"+data.id).jqGrid('getRowData',prevId);
			    		if(prevRowData["activity.status"]==1){
			    			$('#editActivityBtn').show();
			    		}else{
			    			$('#editActivityBtn').hide();
			    		}
			    	}
				}
		    }		 
	  });
	  
	  $.subscribe('editActivity', function(event,data) {
			  var rowId = jQuery("#"+data.id).jqGrid('getGridParam','selrow');
			  var itemId="<s:property value="%{itemId}"/>";
			  if(rowId){
				  var rowData= jQuery("#"+data.id).jqGrid('getRowData',rowId);
				  if(rowData["activity.editable"]=="false"){
					  jAlert("该记录不能编辑");
					  return false;
				  }else{
					   jQuery("#"+data.id).jqGrid('editGridRow', rowId,{width:400,reloadAfterSubmit:true,closeAfterEdit:true,editData:{itemId:itemId},
						   afterSubmit:
							     function(response, postdata, formid){
							         var data=jQuery.parseJSON(response.responseText);
							         if(data.id>0){
							            return [true,data.message];
							         }else{
							        	 return [false,data.message]; 
							         }
							    } 
				      });
				  }
			   }else {
				 jAlert("请选择要编辑的记录!");	
			  }		 
		 });
});
</script>
<div class="app-panel">
	<div class="title">当前位置：采购计划 &gt;&gt; 项目进度</div>
	<div class="toolbar">
		<sj:a onClickTopics="closeApp" button="true"
			buttonIcon="ui-icon-close">
			<s:text name="button.close" />
		</sj:a>
	</div>
</div>
<div id="itemWrapper">
	<s:url var="listActivityItem" action="listAdmin" namespace="/item" />
	<sjg:grid id="itemActivityTable" caption="采购项目" dataType="json"
		href="%{listActivityItem}" pager="true" rownumbers="true"
		multiselect="false" navigator="true" navigatorSearch="true"
		navigatorSearchOptions="{caption:'查询',multipleSearch:true,closeAfterSearch: true, drag: true, closeOnEscape: true}"
		navigatorAdd="false" navigatorEdit="false" navigatorView="false"
		navigatorDelete="false"
		navigatorExtraButtons="{
    					seperator: { 
    						title : 'seperator'  
    					}, 
    					viewBtn : { 
    		    			caption:'查看',
	    					title : '查看', 
	    					icon: 'ui-icon-document',
	    					topic: 'viewItem'
    					}
    				}"
		onCompleteTopics="resizeGridWidth" onSelectRowTopics="listActivity"
		gridModel="itemList" rowList="10,15,20" rowNum="15" viewrecords="true"
		height="320" width="1140" shrinkToFit="false">
		<sjg:gridColumn name="id" index="id" hidden="true" title="#ID"
			sortable="false" width="50" />
		<sjg:gridColumn name="submitted" index="submitted" title="上报日期"
			sortable="true" search="true"
			searchoptions="{sopt:['eq','ne','ge','le'],dataInit: function(element) {$(element).datepicker({dateFormat: 'yy-mm-dd'})}}"
			formatter="date"
			formatoptions="{newformat : 'Y-m-d', srcformat : 'Y-m-d H:i:s'}"
			width="100" />
		<sjg:gridColumn name="orgName" index="orgName" jsonmap="org.orgName"
			title="采购单位(部门)" sortable="false" search="true"
			searchoptions="{sopt:['eq','ne','bw','cn']}" width="200" />
		<sjg:gridColumn name="orgCode" index="orgCode" jsonmap="org.orgCode"
			title="单位编码" sortable="false" search="true"
			searchoptions="{sopt:['eq','ne','bw','cn']}" width="100" />
		<sjg:gridColumn name="itemName" index="itemName" title="项目名称"
			sortable="true" search="true"
			searchoptions="{sopt:['eq','ne','bw','cn']}" width="300" />
		<sjg:gridColumn name="itemCode" index="itemCode" title="项目编号"
			sortable="true" search="true" sorttype="text"
			searchoptions="{sopt:['eq','ne','bw','cn']}" width="150" />
		<sjg:gridColumn name="itemQuantity" index="itemQuantity" title="数量"
			sortable="false" search="false"
			searchoptions="{sopt:['eq','ne','ge','le']}"
			formatter="numberFormatter" width="150" />
		<sjg:gridColumn name="fundTotal" index="fundTotal" title="采购预算(万元)"
			sortable="false" search="false"
			searchoptions="{sopt:['eq','ne','ge','le']}" formatter="fundLink"
			width="150" />
		<sjg:gridColumn name="fundSource" index="fundSource" title="经费开支渠道"
			sortable="true" search="false" width="300" />
		<sjg:gridColumn name="itemType" index="itemType"
			jsonmap="itemType.itemName" title="项目分类" searchtype="select"
			searchoptions="{sopt:['eq','ne'],dataUrl : '%{itemTypeSelect}'}"
			sortable="true" search="true" width="150" />
		<sjg:gridColumn name="purchaseMethod" index="purchaseMethod"
			jsonmap="purchaseMethod.itemName" title="采购方式" searchtype="select"
			searchoptions="{sopt:['eq','ne'],dataUrl : '%{purchaseMethodSelect}'}"
			sortable="true" search="true" width="120" />
		<sjg:gridColumn name="purchaseMode" index="purchaseMode"
			jsonmap="purchaseMode.itemName" title="组织方式" sortable="false"
			search="true" searchtype="select"
			searchoptions="{sopt:['eq','ne'],dataUrl : '%{purchaseModeSelect}'}"
			width="120" />
		<sjg:gridColumn name="orgType" index="orgType"
			jsonmap="org.orgType.itemName" title="单位分类" sortable="true"
			search="false" width="80" />
		<sjg:gridColumn name="superintendent" index="superintendent"
			title="项目负责人" sortable="false" search="true"
			searchoptions="{sopt:['eq','ne','bw','cn']}" width="80" />
		<sjg:gridColumn name="archived" index="archived" title="归档"
			sortable="true" editoptions="{value:'true:false'}" search="false"
			formatter="checkbox" width="100" />
		<sjg:gridColumn name="username" index="username" title="录入人"
			sortable="false" search="false" width="80" />
		<sjg:gridColumn name="created" index="created" title="创建日期"
			sortable="true" sorttype="date" search="false" formatter="date"
			formatoptions="{newformat : 'Y-m-d', srcformat : 'Y-m-d H:i:s'}"
			width="80" />
	</sjg:grid>
</div>
<div id="activityWrapper" style="margin-top: 2px;">
	<s:url var="listActivity" action="list" namespace="/item/activity" />
	<s:url var="editActivityUrl" action="update" namespace="/item/activity" />
	<sjg:grid id="activityTable" caption="采购进度" dataType="json"
		href="%{listActivity}" pager="true" rownumbers="true" navigator="true"
		navigatorSearch="false"
		navigatorSearchOptions="{caption:'查询',multipleSearch:true}"
		navigatorAdd="false" navigatorEdit="false" navigatorView="false"
		navigatorDelete="false"
		navigatorExtraButtons="{
    					seperator: { 
    						title : 'seperator'  
    					}, 
    					editBtn : {
    					    id:'editActivityBtn', 
    		  			    caption:'编辑',
	    					title : '编辑记录', 
	    					icon: 'ui-icon-pencil',
	    					topic:  'editActivity'
    					}
    				}"
		onCompleteTopics="resizeGridWidth" onSelectRowTopics="checkState"
		gridModel="activityList" rowList="10,15,20" rowNum="10"
		editurl="%{editActivityUrl}" viewrecords="true" height="220"
		width="1140" shrinkToFit="false">
		<sjg:gridColumn name="id" index="id" title="ID" hidden="true"
			sortable="false" editable="true" width="20" />
		<sjg:gridColumn name="activity.id" index="id" jsonmap="id" title=""
			hidden="true" sortable="false" search="false" editable="true"
			width="20" />
		<sjg:gridColumn name="activity.activityName" index="activityName"
			jsonmap="activityName" title="项目阶段" sortable="false" search="true"
			searchoptions="{sopt:['eq','ne','bw','cn']}" editable="false"
			width="180" />
		<sjg:gridColumn name="activity.activityNum" index="activityNum"
			jsonmap="activityNum" title="顺序号" hidden="true" sortable="false"
			search="false" editable="false" width="20" />
		<sjg:gridColumn name="activity.signature" index="signature"
			jsonmap="signature" title="经办人" sortable="false" search="true"
			editable="true" width="80" />
		<sjg:gridColumn name="activity.completedDate" index="completedDate"
			jsonmap="completedDate" title="经办时间" sortable="false" search="true"
			searchoptions="{sopt:['eq','ne']}" editable="true"
			editoptions="{size: 20, maxlengh: 10,dataInit: function(element) {$(element).datepicker({dateFormat: 'yy-mm-dd'})}}"
			formatter="date"
			formatoptions="{newformat : 'Y-m-d', srcformat : 'Y-m-d H:i:s'}"
			width="100" />
		<sjg:gridColumn name="activity.comments" index="comments"
			jsonmap="comments" title="经办意见" sortable="false" search="false"
			editable="true" width="200" />
		<sjg:gridColumn name="activity.username" index="username"
			jsonmap="username" title="录入人" sortable="false" search="false"
			editable="false" width="100" />
		<sjg:gridColumn name="activity.created" index="created"
			jsonmap="created" title="录入日期" sortable="false" search="false"
			editable="false" formatter="date"
			formatoptions="{newformat : 'Y-m-d', srcformat : 'Y-m-d H:i:s'}"
			width="100" />
		<sjg:gridColumn name="activity.description" index="description"
			jsonmap="description" title="备注" sortable="false" search="false"
			editable="true" width="200" />
		<sjg:gridColumn name="activity.status" index="status" jsonmap="status"
			title="办理状态" sortable="false" search="true" editable="true"
			edittype="select" editoptions="{value:'0:待处理;1:已完成'}"
			formatter="select" width="100" />
		<sjg:gridColumn name="activity.editable" index="editable"
			jsonmap="editable" title="是否可编辑" width="100" search="false"
			sortable="false" editable="true" edittype="checkbox"
			editoptions="{value:'true:false'}" formatter="checkbox" />
	</sjg:grid>
</div>
<sj:dialog id="itemViewDialog" title="采购项目信息" modal="true"
	autoOpen="false" width="960" height="560" indicator="indicator"
	buttons="{
         '关闭':function() {$(this).dialog('close');}
  }" />
<sj:dialog id="fundDialog" autoOpen="false" modal="false"
	resizable="false" title="资金来源" width="885" height="340"
	indicator="indicator"
	buttons="{
         '关闭':function() {$(this).dialog('close');}
  }" />