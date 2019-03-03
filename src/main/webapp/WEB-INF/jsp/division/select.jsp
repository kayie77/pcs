<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:url var="treeDataUrl" action="treeData" namespace="/base/division" />
<div class="dialog-outer" style="width: 100%;">
	<div class="dialog-row">
		<div class="dialog-col">
			<sjt:tree id="divisionTree" href="%{treeDataUrl}"
				jstreetheme="default" onClickTopics="treeClicked"
				cssStyle="background:#fff;border:1px solid #AAA;height:385px;overflow:auto;"
				deferredLoading="false" loadingText="" />
		</div>
	</div>
</div>
<script type="text/javascript">
<!--
$(document).ready(function() {
	   $.subscribe('treeClicked', function(event, data) {
		     var node = event.originalEvent.data.rslt.obj;
		      var code=node.attr("code");  
			  $('#<s:property value="%{valueId}"/>').val(code);
			  $('#divisionDialog').dialog('close');
	   });
});
//-->
</script>
