<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<s:url var="listActivity" action="list" namespace="/item/activity">
	<s:param name="itemId" value="%{itemId}" />
</s:url>
<sjg:grid id="activityTable" dataType="json" href="%{listActivity}"
	pager="true" rownumbers="true" navigator="true" navigatorSearch="false"
	navigatorSearchOptions="{caption:'查询',multipleSearch:true}"
	navigatorAdd="false" navigatorEdit="false" navigatorView="false"
	navigatorDelete="false" gridModel="activityList" rowList="10,15,20"
	rowNum="10" viewrecords="true" height="220" width="800"
	shrinkToFit="false">
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
		jsonmap="activityNum" title="顺序号" hidden="true" sortable="true"
		search="false" editable="false" width="20" />
	<sjg:gridColumn name="activity.signature" index="signature"
		jsonmap="signature" title="经办人" sortable="false" search="true"
		editable="true" width="80" />
	<sjg:gridColumn name="activity.completedDate" index="completedDate"
		jsonmap="completedDate" title="经办时间" sortable="true" search="true"
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
		title="审批状态" sortable="false" search="true" editable="true"
		edittype="select" editoptions="{value:'0:待处理;1:已完成'}"
		formatter="select" width="100" />
	<sjg:gridColumn name="activity.editable" index="editable"
		jsonmap="editable" hidden="true" title="是否可编辑" width="100"
		sortable="false" search="false" editable="true" edittype="checkbox"
		editoptions="{value:'true:false'}" formatter="checkbox" />
</sjg:grid>