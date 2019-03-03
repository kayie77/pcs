<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<script type="text/javascript">
$(document).ready(function() {
	  jQuery.struts2_jquery.require("js/base/jquery.ui.datepicker.min.js");
	  jQuery.struts2_jquery.require("i18n/jquery.ui.datepicker-zh-CN.min.js");
	
	  $.subscribe('importItems', function(event,data) {
		  $('#uploadDialog').empty().load("<s:url value="/item/upload.action"/>").dialog('open');
		    return false;  
	   });
	  
	  $.subscribe('importItemsHistory', function(event,data) {
		  $('#uploadHistoryDialog').empty().load("<s:url value="/item/uploadHistory.action"/>").dialog('open');
		    return false;  
	   });
	
	  $.subscribe('updateItemComplete', function(event,data) {
		  var data=jQuery.parseJSON(event.originalEvent.request.responseText);
		    jAlert(data.message);
		    if(data.success==true){
		    	$('#itemEditDialog').dialog('close');
		    	jQuery("#itemImportTable").trigger('reloadGrid');
		    }  
	 });
	
	 $.subscribe('addItem', function(event,data) {
		 $('#itemEditDialog').empty().load("<s:url value="/item/inputForm.action"/>").dialog('open'); 
		 return false;
     });
	
	 $.subscribe('editItem', function(event,data) {
		 var rowId = jQuery("#"+data.id).jqGrid('getGridParam','selrow');
		 if(rowId){
			 $('#itemEditDialog').empty().load("<s:url value="/item/inputForm.action"/>",{id:rowId}).dialog('open');
			 return false;
		 }else{
			 jAlert("请选择要编辑的纪录!");
		 }		 
      });
	 
	 $.subscribe('viewItem', function(event,data) {
		 var rowId = jQuery("#"+data.id).jqGrid('getGridParam','selrow');
		 if(rowId){
			 $('#itemViewDialog').empty().load("<s:url value="/item/viewForm.action"/>",{id:rowId}).dialog('open');
			 return false;
		 }else{
			 jAlert("请选择要查看的纪录!");
		 }			 
     });
	 
	 
	 $.subscribe('updateItem', function(event, data) {
		 $("#submitItemForm").click();   
     }); 
	 
	 $.subscribe('deleteItem', function(event,data) {
		  var selectedIds = jQuery("#"+data.id).jqGrid('getGridParam','selarrrow');
		 var ids = [];
		 if (selectedIds==null || selectedIds.length==0){
				 jAlert("请选择要删除的采购项目!");
				 return false;
		  }
		   jConfirm("删除该项目，相关资料一并被删除，确定删除吗?","确定信息框",function(yesno){
	            if(yesno){
	            	  for(var i=0;i<selectedIds.length;i++){
	     				 ids.push(selectedIds[i]);
	     		      } 
	     		     var params={"ids":ids};
	     		     $.ajax({
	     			  		type : 'POST',
	     			  		url : '<s:url value="/item/delete.action"/>',
	     			  		data :jQuery.param(params,true),
	     			  		success : function(response) {
	     			  		  jAlert(response.message,"消息框");
	     			  		 jQuery("#"+data.id).trigger('reloadGrid');	
	     			  },
	     			  dataType : 'JSON'
	     		   });
	            }
	       });
		
     });	  	 
	 
     //明细
	  $.subscribe('editItemDetail', function(event,data) {
	      var rowId = jQuery("#"+data.id).jqGrid('getGridParam','selrow');
	      if(rowId){
	    	 $('#itemDetailDialog').empty().load("<s:url value="/item/detail/detailInput.action"/>",{itemId:rowId}).dialog('open'); 
			 return false;
	      }else{
			 jAlert("请选择要编辑明细的纪录!");
		  }		 
       });
	 
	   
	  $.subscribe('addDetail', function(event,data) {
		   var rowId = jQuery("#itemImportTable").jqGrid('getGridParam','selrow');
		   $('#detailDialog').empty().load("<s:url value="/item/detail/inputForm.action"/>",{itemId:rowId}).dialog('open'); 	
	   });
     
	   $.subscribe('editDetail', function(event,data) {
			 var rowId = jQuery("#"+data.id).jqGrid('getGridParam','selrow');
			 if(rowId){
				 $('#detailDialog').empty().load("<s:url value="/item/detail/inputForm.action"/>",{id:rowId}).dialog('open');
				 return false;
			 }else{
				 jAlert("请选择要编辑的纪录!");
			 }		 
		});
	   
	    $.subscribe('deleteDetail', function(event,data) {
		   var rowId = jQuery("#"+data.id).jqGrid('getGridParam','selrow');
			 if(rowId){
				 jQuery("#"+data.id).jqGrid('delGridRow',rowId,{reloadAfterSubmit:true});
				 return false;
			 }else{
				 jAlert("请选择要删除的记录!");	
			 }
		});
	    
	    $.subscribe('updateDetailComplete', function(event,data) {
				var data=jQuery.parseJSON(event.originalEvent.request.responseText);
			    jAlert(data.message);
			    if(data.success==true){
			    	$('#detailDialog').dialog('close');
			    	jQuery("#itemDetailTable").trigger('reloadGrid');
			    }
				//$.publish("updateItemTotal");
		});

		$('#detailPrice,#detailQuantity').live('change', function(){
				var quantity = $('#detailQuantity').val();
			    var price = parseFloat($('#detailPrice').val())/10000;
				var v =computeMultify(quantity,price);
				$('#detailAmount').val(v);
		});

		
		 $.subscribe('updateDetailItem', function(event, data) {
			 $("#submitItemDetailForm").click();   
	     }); 
		
		
     
	 //资金
	  $.subscribe('editItemFund', function(event,data) {
	     var rowId = jQuery("#"+data.id).jqGrid('getGridParam','selrow');
	     if(rowId){
	    	 $('#fundDialog').empty().load("<s:url value="/item/fund/fundInput.action"/>",{itemId:rowId}).dialog('open'); 
			 return false;
	     }else{
			 jAlert("请选择要编辑资金的纪录!");
		 }		 
      });
	 
	  $.subscribe('addFund', function(event,data) {
		  var rowId = jQuery("#itemImportTable").jqGrid('getGridParam','selrow');
   		 jQuery("#"+data.id).jqGrid('editGridRow', 'new',{width:400,reloadAfterSubmit:true,closeAfterAdd:true,editData:{itemId:rowId},
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
     });
   
     $.subscribe('selectFund', function(event,data) {
   		 $('#selectBudgetDialog').empty().load('<s:url value="/budget/selectBudgetFund.action"/>').dialog('open');
     });
   
     $.subscribe('editFund', function(event,data) {
  	    var rowId = jQuery("#"+data.id).jqGrid('getGridParam','selrow');
  	    var itemId = jQuery("#itemImportTable").jqGrid('getGridParam','selrow');
  	    if(rowId){
  		   jQuery("#"+data.id).jqGrid('editGridRow', rowId,{width:400,reloadAfterSubmit:true,closeAfterEdit:true,editData:{itemId:itemId},
  			   afterSubmit:
  				     function(response, postdata, formid){
  				         var data=jQuery.parseJSON(response.responseText);
  				         if(data.id>0){
  				            return [true,data.message];
  				         }else{
  				        	 return [false,data.message]; 
  				         }
  				    } });
  	   }else {
  		 jAlert("请选择要编辑的记录!");	
  	  }	
    });
   
     $.subscribe('deleteFund', function(event,data) {
   	   var rowId = jQuery("#"+data.id).jqGrid('getGridParam','selrow');
   	   if(rowId){
   		 jQuery("#"+data.id).jqGrid('delGridRow',rowId,{reloadAfterSubmit:true});
   	   }else {
   		 jAlert("请选择要删除的记录!");	
   	   }
    });
});
</script>
<div class="app-panel">
	<div class="title">当前位置：采购计划 &gt;&gt; 项目导入列表</div>
	<div class="toolbar">
		<sj:a onClickTopics="importItems" button="true"
			buttonIcon="ui-icon-arrowthick-1-n">
			<s:text name="button.import" />
		</sj:a>
		<sj:a onClickTopics="importItemsHistory" button="true"
			buttonIcon="ui-icon-arrowthick-1-n">
			<s:text name="button.importHistory" />
		</sj:a>
		<sj:a onClickTopics="closeApp" button="true"
			buttonIcon="ui-icon-close">
			<s:text name="button.close" />
		</sj:a>
	</div>
</div>
<div id="itemWrapper">
	<s:url var="itemTypeSelect" action="select"
		namespace="/base/dictionary">
		<s:param name="dictId" value="19" />
	</s:url>
	<s:url var="purchaseMethodSelect" action="select"
		namespace="/base/dictionary">
		<s:param name="dictId" value="21" />
	</s:url>
	<s:url var="purchaseModeSelect" action="select"
		namespace="/base/dictionary">
		<s:param name="dictId" value="20" />
	</s:url>
	<s:url var="listImport" action="listImport" namespace="/item" />
	<sjg:grid id="itemImportTable" dataType="json" href="%{listImport}"
		pager="true" rownumbers="true" multiselect="true" multiboxonly="true"
		navigator="true" navigatorSearch="true"
		navigatorSearchOptions="{caption:'查询',multipleSearch:true,closeAfterSearch: true, drag: true, closeOnEscape: true}"
		navigatorAdd="false" navigatorEdit="false" navigatorView="false"
		navigatorDelete="false"
		navigatorExtraButtons="{
    					seperator: { 
    						title : 'seperator'  
    					}, 
    					editBtn : { 
    		    			caption:'编辑信息',
	    					title : '编辑信息', 
	    					icon: 'ui-icon-pencil',
	    					topic: 'editItem'
    					},
    					editFundBtn : { 
    		    			caption:'编辑资金',
	    					title : '编辑紫荆', 
	    					icon: 'ui-icon-pencil',
	    					topic: 'editItemFund'
    					},
    					editDetailBtn : { 
    		    			caption:'编辑明细',
	    					title : '编辑明细', 
	    					icon: 'ui-icon-pencil',
	    					topic: 'editItemDetail'
    					},
    					viewBtn : { 
    		    			caption:'查看',
	    					title : '查看', 
	    					icon: 'ui-icon-document',
	    					topic: 'viewItem'
    					},
    					seperator: { 
    						title : 'seperator'  
    					},
    					deleteBtn : { 
    		    			caption:'删除',
	    					title : '删除', 
	    					icon: 'ui-icon-minus',
	    					topic: 'deleteItem'
    					}
    				}"
		onCompleteTopics="resizeGrid" gridModel="itemList"
		rowList="10,15,20,50" rowNum="20" viewrecords="true" height="380"
		width="1140" shrinkToFit="false">
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
			sortable="false" search="true" width="150" />
		<sjg:gridColumn name="purchaseMethod" index="purchaseMethod"
			jsonmap="purchaseMethod.itemName" title="采购方式" searchtype="select"
			searchoptions="{sopt:['eq','ne'],dataUrl : '%{purchaseMethodSelect}'}"
			sortable="false" search="true" width="120" />
		<sjg:gridColumn name="purchaseMode" index="purchaseMode"
			jsonmap="purchaseMode.itemName" title="组织方式" sortable="false"
			search="true" searchtype="select"
			searchoptions="{sopt:['eq','ne'],dataUrl : '%{purchaseModeSelect}'}"
			width="120" />
		<sjg:gridColumn name="orgCat" index="orgCat" title="单位分类"
			jsonmap="org.orgType.itemName" sortable="true" search="false"
			width="80" />
		<sjg:gridColumn name="superintendent" index="superintendent"
			title="项目负责人" sortable="false" search="true"
			searchoptions="{sopt:['eq','ne','bw','cn']}" width="80" />
		<sjg:gridColumn name="username" index="username" title="录入人"
			sortable="false" search="false" width="80" />
		<sjg:gridColumn name="created" index="created" title="创建日期"
			sortable="true" sorttype="date" search="false" formatter="date"
			formatoptions="{newformat : 'Y-m-d', srcformat : 'Y-m-d H:i:s'}"
			width="80" />
	</sjg:grid>
</div>
<sj:dialog id="uploadDialog" autoOpen="false" modal="false"
	resizable="false" title="项目导入对话框" width="480" height="250"
	indicator="indicator"
	buttons="{
         '关闭':function() {$(this).dialog('close');}
  }" />

<sj:dialog id="uploadHistoryDialog" autoOpen="false" modal="false"
	resizable="false" title="项目导入对话框" width="480" height="250"
	indicator="indicator"
	buttons="{
         '关闭':function() {$(this).dialog('close');}
  }" />

<sj:dialog id="itemEditDialog" title="采购项目编辑" modal="true"
	autoOpen="false" width="960" height="450" indicator="indicator"
	buttons="{
		 '保存':function() {$.publish('updateItem');},
         '关闭':function() {$(this).dialog('close');}
  }" />
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

<sj:dialog id="selectBudgetDialog" title="资金来源" modal="true"
	autoOpen="false" width="785" height="560"
	buttons="{
         '关闭':function() {$(this).dialog('close');}
  }" />

<sj:dialog id="itemDetailDialog" title="采购项目明细" modal="true"
	autoOpen="false" width="885" height="340"
	buttons="{
         '关闭':function() {$(this).dialog('close');}
  }" />



<sj:dialog id="detailDialog" title="采购项目明细" modal="true"
	autoOpen="false" width="885" height="360"
	buttons="{
	 	     '保存':function() {$.publish('updateDetailItem');},
            '关闭':function() {$(this).dialog('close');}
  }" />