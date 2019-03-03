<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:url var="treeDataUrl" action="itemTreeData"
	namespace="/base/dictionary">
	<s:param name="dictId" value="%{id}" />
</s:url>
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
	   var keyType="<s:property value="%{keyType}"/>";
	   $.subscribe('treeClicked', function(event, data) {
		     var node = event.originalEvent.data.rslt.obj;
		      var id=node.attr("id");  
		      var code=node.attr("code");  
			  var name=node.attr("title");  
			  if(keyType=="id"){
			      $('#<s:property value="%{keyId}"/>').val(id);
	          }else if(keyType=="code"){
	        	  $('#<s:property value="%{keyId}"/>').val(code);
	          }
			   $('#<s:property value="%{valueId}"/>').val(name);
			   $('#dictItemDialog').dialog('close');
	   });
});
//-->
</script>

