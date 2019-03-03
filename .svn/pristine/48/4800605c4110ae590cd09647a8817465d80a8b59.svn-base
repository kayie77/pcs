<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<ul id="formerrors" class="errorMessage ui-widget-content ui-corner-all"
	style="display: none;"></ul>
<div id="result" class="result ui-widget-content ui-corner-all"
	style="display: none;"></div>
<div class="formContainer">
	<s:form id="articleForm" action="update" namespace="/info/article">
		<table>
			<tbody>
				<tr>
					<td style="width: 12%"><s:label key="label.article.title" />：<span
						class="required">*</span></td>
					<td style="width: 36%"><s:textfield id="title"
							name="article.title" cssClass="w80" /><span id="titleError"></span></td>
					<td style="width: 12%"><s:label key="label.article.category" />：</td>
					<td style="width: 36%"><s:url var="selectCategory"
							action="select" namespace="/info/article" escapeAmp="false">
							<s:param name="keyId">categoryid</s:param>
							<s:param name="valueId">catName</s:param>
						</s:url> <s:textfield id="catName" name="article.category.catName"
							cssClass="w50" readonly="true" /> <s:hidden id="categoryid"
							name="article.category.id" /> <sj:a href="%{selectCategory}"
							openDialog="catDialog" cssClass="fg-button" title="选择">
							<span class="ui-icon ui-icon-search"></span>&nbsp;</sj:a><span
						id="categoryidError"></span></td>
				</tr>
				<tr>
					<td><s:label key="label.article.author" />：</td>
					<td><s:textfield id="author" name="article.author"
							cssClass="w50" /></td>
					<td><s:label key="label.article.published" />：</td>
					<td><s:if test="%{article.published!=null}">
							<s:textfield id="published" name="article.published"
								value="%{getText('global.date',{article.created})}"
								cssClass="w50" readonly="true" />
						</s:if> <s:else>
							<s:textfield id="published" name="article.published"
								cssClass="w50" readonly="true" />
						</s:else></td>
				</tr>
				<tr>
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
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td><s:label key="label.article.text" />：</td>
					<td colspan="3"><sjr:ckeditor id="text" name="article.text"
							rows="10" cols="100" uploads="true" /></td>
				</tr>
			</tbody>
		</table>
		<s:hidden id="id" name="article.id" />
		<s:hidden id="oper" name="oper" />
		<s:hidden id="status" name="article.status" />
		<s:hidden id="created" name="article.created" />
		<s:hidden id="username" name="article.username" />
		<sj:submit id="submitArticleForm" formIds="articleForm"
			cssStyle="display:none" onCompleteTopics="updateArticleComplete"
			validate="true" validateFunction="customeValidation"
			onBeforeTopics="removeErrors" onSuccessTopics="removeErrors"
			value="Submit Form" targets="result" />
	</s:form>
</div>
