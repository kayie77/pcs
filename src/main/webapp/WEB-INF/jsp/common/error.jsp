<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<html>
<head>
<title><s:text name="errorPage.title" /></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="all"
	href="${ctx}/resources/css/default.css" />
</head>
<body bgcolor="#ffffff" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0">
	<table width="100%" border="0" align="center">
		<tr>
			<td width="100%" height="400" align="center" valign="top"><p>&nbsp;</p>
				<table width="600" border="0" class="TabBig">
					<tr>
						<td align="center"><img style="border: 0"
							src="<s:url value="/common/images/500.gif"/>" /></td>
					</tr>
					<tr>
						<td>错误信息:<s:property value="%{exception.message}" /></td>
					</tr>
					<tr>
						<td>详细信息:<s:property value="%{exceptionStack}" /></td>
					</tr>
					<tr>
						<td align="center"><a href="<s:url value="/"/>">返回</a></td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>