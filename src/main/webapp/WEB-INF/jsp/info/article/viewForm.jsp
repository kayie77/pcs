<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="formContainer">
	<s:form id="articleForm">
		<table>
			<tbody>
				<tr>
					<td style="width: 15%"><s:label key="label.article.title" />：</td>
					<td><s:property value="%{article.title}" /></td>
					<td style="width: 15%"><s:label key="label.article.category" />：</td>
					<td style="width: 35%"><s:property
							value="%{article.category.catName}" /></td>
				</tr>
				<tr>
					<td><s:label key="label.article.author" />：</td>
					<td><s:property value="%{article.author}" /></td>
					<td><s:label key="label.article.created" />：</td>
					<td><s:property
							value="%{getText('global.date',{article.created})}" /></td>
				</tr>
				<tr>
					<td><s:label key="label.article.published" />：</td>
					<td><s:if test="%{article.published!=null}">
							<s:property value="%{getText('global.date',{article.published})}" />
						</s:if></td>
					<td><s:label key="label.article.status" />：</td>
					<td><s:if test="%{article.status==0}">
					        草稿
					     </s:if> <s:elseif test="%{article.status==1}">
					        待审
					     </s:elseif> <s:elseif test="%{article.status==2}">
					         审核通过
					     </s:elseif> <s:elseif test="%{article.status==3}">
					        审核未通过
					     </s:elseif> <s:else>
					        草稿
					     </s:else></td>
				</tr>
				<tr>
					<td><s:label key="label.article.text" />：</td>
					<td colspan="3"><s:property value="article.text"
							escape="false" /></td>
				</tr>
			</tbody>
		</table>
	</s:form>
</div>