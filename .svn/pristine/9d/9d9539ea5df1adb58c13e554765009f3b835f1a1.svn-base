<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script type="text/javascript">
<!--
$(document).ready(function() {
	$.subscribe('viewArticle', function(event, data) {
		 var rowId = jQuery("#"+data.id).jqGrid('getGridParam','selrow');
		 if(rowId){
			 $('#articleViewDialog').empty().load("<s:url value="/info/article/viewForm.action"/>",{id:rowId}).dialog('open');
			 return false;
		 }else{
			 jAlert("请选择要编辑的纪录!");
		 }		 
  });
});
//-->
</script>
<div class="app-panel">
	<div class="title">当前位置：信息管理 &gt;&gt; 信息查询</div>
	<div class="toolbar">
		<sj:a onClickTopics="closeApp" button="true"
			buttonIcon="ui-icon-close">
			<s:text name="button.close" />
		</sj:a>
	</div>
</div>
<s:url var="listQuery" action="listQuery" namespace="/info/article" />
<sjg:grid dataType="json" href="%{listQuery}" pager="true"
	multiselect="true" multiboxonly="true" navigator="true"
	navigatorSearch="true"
	navigatorSearchOptions="{sopt:['eq','ne','lt','gt','bw','cn']}"
	navigatorAdd="false" navigatorEdit="false" navigatorView="false"
	navigatorDelete="false"
	navigatorExtraButtons="{
    					seperator: { 
    						title : 'seperator'  
    					}, 
    					viewBtn : {
    					    id:'viewBtn', 
    		  			    caption:'查看',
	    					title : '查看', 
	    					icon: 'ui-icon-document',
	    					topic: 'viewArticle'
    					}
    		  }"
	onCompleteTopics="resizeGrid" gridModel="artList" rowList="10,15,20"
	rowNum="20" viewrecords="true" height="400" width="1140"
	shrinkToFit="false">
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
<sj:dialog id="articleViewDialog" title="信息浏览" modal="true"
	autoOpen="false" width="960" height="450" indicator="indicator"
	buttons="{
         '关闭':function() {$(this).dialog('close');}
  }" />