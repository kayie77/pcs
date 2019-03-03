<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:url var="treeDataUrl" action="treeData" namespace="/info/category" />
<div class="dialog-outer" style="width: 100%;">
	<div class="dialog-row">
		<div class="dialog-col">
			<sjt:tree id="dictItemTree" href="%{treeDataUrl}"
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
		      var id=node.attr("id");  
			  var name=node.attr("title");  
			  $('#<s:property value="%{keyId}"/>').val(id);
			  $('#<s:property value="%{valueId}"/>').val(name);
			  $('#catDialog').dialog('close');
	   });
});
//-->
</script>
