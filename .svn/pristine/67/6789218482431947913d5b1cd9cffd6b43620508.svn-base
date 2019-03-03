<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<html>
	<body>
		<form id="showreportform" name="showreportform" action="${ctx}/office/task/showreport" method="post" target="_blank">
			<input type="hidden" id="url" name="url"/>
		</form>
		
		<script>
			$("#url").val("${ctx_bi}/${url}?divCode=${divCode}");
			$("#showreportform").submit();
		</script>
	</body>
</html>