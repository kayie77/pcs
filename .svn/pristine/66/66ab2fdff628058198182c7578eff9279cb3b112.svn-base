<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script type="text/javascript">
<!--
$(document).ready(function() {
	  $('#left-tree').height($('#mytabs').height());  
});
//-->
</script>
<div class="app-panel">
	<div class="title">当前位置：信息管理 &gt;&gt; 信息分类</div>
	<div class="toolbar">
		<sj:a onClickTopics="closeApp" button="true"
			buttonIcon="ui-icon-close">
			<s:text name="button.close" />
		</sj:a>
	</div>
</div>
<s:url var="treeDataUrl" action="treeData" namespace="/info/category" />
<s:url var="view" action="view" namespace="/info/category" />
<div class="left-tree" id="left-tree">
	<sjt:tree id="artCatTree" href="%{treeDataUrl}" jstreetheme="default"
		nodeHref="%{view}" nodeTargets="catDiv" deferredLoading="false"
		loadingText="" />
</div>
<div class="right-content">
	<sj:div href="%{view}" id="catDiv" indicator="indicator" />
</div>