<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script type="text/javascript">
<!--
$(document).ready(function() {
	$.subscribe('selectRow', function(event,data) {
		 var rowId = jQuery("#"+data.id).jqGrid('getGridParam','selrow');
		 if(rowId){
			 var rowData = jQuery("#"+data.id).jqGrid('getRowData',rowId); 
			 if(rowData.status=='1'||rowData.status=='2'){
				 $('#requestEditBtn').addClass('ui-state-disabled');
				 $('#requestDeleteBtn').addClass('ui-state-disabled');
				 $('#requestSubmitBtn').addClass('ui-state-disabled');
			 }else{
				 $('#requestEditBtn').removeClass('ui-state-disabled');
				 $('#requestDeleteBtn').removeClass('ui-state-disabled');
				 $('#requestSubmitBtn').removeClass('ui-state-disabled');
			 }
		 } 
     });
	
	 $.subscribe('submitArticle', function(event,data) {
	    var selectedIds = jQuery("#"+data.id).jqGrid('getGridParam','selarrrow');
		 var ids = [];
		 if (selectedIds==null || selectedIds.length==0){
			 jAlert("请选择要送审的信息!");
			 return false;
		 }
		 for(var i=0;i<selectedIds.length;i++){
			 ids.push(selectedIds[i]);
		} 
		var params={"ids":ids};
	    $.ajax({
			  		type : 'POST',
			  		url : '<s:url value="/info/article/submit.action"/>',
			  		data :jQuery.param(params,true),
			  		success : function(response) {
			  		  jAlert(response.message,"消息框");
			  		 jQuery("#"+data.id).trigger('reloadGrid');	
			},
			dataType : 'JSON'
		});
     });
	
	 $.subscribe('editArticle', function(event, data) {
		 var rowId = jQuery("#"+data.id).jqGrid('getGridParam','selrow');
		 if(rowId){
			 $('#articleEditDialog').empty().load("<s:url value="/info/article/inputForm.action"/>",{id:rowId}).dialog('open');
			 return false;
		 }else{
			 jAlert("请选择要编辑的纪录!");
		 }		 
    });
	
	$.subscribe('viewArticle', function(event, data) {
		 var rowId = jQuery("#"+data.id).jqGrid('getGridParam','selrow');
		 if(rowId){
			 $('#articleViewDialog').empty().load("<s:url value="/info/article/viewForm.action"/>",{id:rowId}).dialog('open');
			 return false;
		 }else{
			 jAlert("请选择要编辑的纪录!");
		 }		 
   });
	
	 $.subscribe('addArticle', function(event, data) {
		 $('#articleEditDialog').empty().load("<s:url value="/info/article/inputForm.action"/>").dialog('open'); 
		 return false;
     })
	
	$.subscribe('deleteArticle', function(event,data) {
	     var rowId = jQuery("#"+data.id).jqGrid('getGridParam','selrow');
	     if(rowId){
			 jQuery("#"+data.id).jqGrid('delGridRow',rowId,{reloadAfterSubmit:true});
			 return false;
		 }else{
			 jAlert("请选择要删除的记录!");	
		 }
    });
	
	 $.subscribe('updateArticle', function(event, data) {
		 $("#submitArticleForm").click();   
     }); 
	 
	  $.subscribe('updateArticleComplete', function(event,data) {
		  var data=jQuery.parseJSON(event.originalEvent.request.responseText);
		    jAlert(data.message);
		    if(data.success==true){
		    	$('#articleEditDialog').dialog('close');
		    	jQuery("#articleRequestTable").trigger('reloadGrid');
		    }  
	  });
});
//-->
</script>
<div class="app-panel">
	<div class="title">当前位置：信息管理 &gt;&gt; 信息报送</div>
	<div class="toolbar">
		<sj:a onClickTopics="addArticle" button="true"
			buttonIcon="ui-icon-plus">
			<s:text name="button.add" />
		</sj:a>
		<sj:a onClickTopics="closeApp" button="true"
			buttonIcon="ui-icon-close">
			<s:text name="button.close" />
		</sj:a>
	</div>
</div>
<s:url var="listRequest" action="listRequest" namespace="/info/article" />
<s:url var="editUrl" action="delete" namespace="/info/article" />
<sjg:grid id="articleRequestTable" dataType="json" href="%{listRequest}"
	pager="true" multiselect="true" multiboxonly="true" navigator="true"
	navigatorSearch="true"
	navigatorSearchOptions="{sopt:['eq','ne','lt','gt','bw','cn']}"
	navigatorAdd="false" navigatorEdit="false" navigatorView="false"
	navigatorDelete="false"
	navigatorExtraButtons="{
    					seperator: { 
    						title : 'seperator'  
    					}, 
    					submitBtn : {
    					    id:'requestSubmitBtn',
    		  			    caption:'送审',
	    					title : '送审', 
	    					icon: 'ui-icon-pencil',
	    					topic:  'submitArticle'
    					},
    					editBtn : {
    					    id:'requestEditBtn',
    		  			    caption:'编辑',
	    					title : '编辑', 
	    					icon: 'ui-icon-pencil',
	    					topic:  'editArticle'
    					},
    					viewBtn : {
    					     id:'requestViewBtn',
    		  			    caption:'查看',
	    					title : '查看', 
	    					icon: 'ui-icon-document',
	    					topic: 'viewArticle'
    					},
    					seperator: { 
    						title : 'seperator'  
    					},
    					deleteBtn : { 
    					     id:'requestDeleteBtn',
    		    			caption:'删除',
	    					title : '删除', 
	    					icon: 'ui-icon-minus',
	    					topic: 'deleteArticle'
    					}
    		  }"
	onCompleteTopics="resizeGrid" onSelectRowTopics="selectRow"
	gridModel="artList" rowList="10,15,20" rowNum="20" editurl="%{editUrl}"
	viewrecords="true" height="400" width="1140" shrinkToFit="false">
	<sjg:gridColumn name="id" index="id" hidden="true" title="ID"
		sortable="false" width="20" />
	<sjg:gridColumn name="title" index="title" title="标题" width="400"
		sortable="true" search="true" />
	<sjg:gridColumn name="catName" index="catName"
		jsonmap="category.catName" title="分类" width="100" sortable="true"
		search="true" />
	<sjg:gridColumn name="author" index="author" title="作者" width="100"
		sortable="true" search="true" />
	<sjg:gridColumn name="published" index="published" title="发布日期"
		sortable="true" search="false" formatter="date"
		formatoptions="{newformat : 'Y-m-d', srcformat : 'Y-m-d H:i:s'}"
		width="100" />
	<sjg:gridColumn name="created" index="created" title="创建日期"
		sortable="true" search="false" formatter="date"
		formatoptions="{newformat : 'Y-m-d', srcformat : 'Y-m-d H:i:s'}"
		width="100" />
	<sjg:gridColumn name="status" index="status" title="状态" width="80"
		sortable="true" editoptions="{value:'0:草稿;1:待审 ;2:通过;;3:未通过'}"
		search="true" formatter="select" />
</sjg:grid>
<sj:dialog id="catDialog" autoOpen="false" modal="true"
	resizable="false" title="选择分类" width="360" height="510"
	indicator="indicator"
	buttons="{
               '关闭':function() {$('#catDialog').dialog('close');}
 }" />
<sj:dialog id="articleEditDialog" title="信息编辑" modal="true"
	autoOpen="false" width="960" height="560" indicator="indicator"
	buttons="{
		 '保存':function() {$.publish('updateArticle');},
         '关闭':function() {$(this).dialog('close');}
  }" />
<sj:dialog id="articleViewDialog" title="信息浏览" modal="true"
	autoOpen="false" width="960" height="450" indicator="indicator"
	buttons="{
         '关闭':function() {$(this).dialog('close');}
  }" />