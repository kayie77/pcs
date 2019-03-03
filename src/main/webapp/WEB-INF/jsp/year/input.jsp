<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script type="text/javascript">
 $(document).ready(function() {
	   $.subscribe('updateYearComplete', function(event,data) {
		  var data=jQuery.parseJSON(event.originalEvent.request.responseText);
		  $('#result').html(data.message);
		  $('#id').val(data.id);
	      $('#oper').val('edit');
	   });
 });
</script>
<div class="app-panel">
	<div class="title">当前位置：基础数据 &gt;&gt; 业务年度</div>
	<div class="toolbar">
		<sj:a formIds="yearForm" targets="result" effectMode="show"
			effect="fade" effectDuration="500"
			onCompleteTopics="updateYearComplete"
			onEffectCompleteTopics="hideTarget" validate="true"
			validateFunction="customeValidation" onBeforeTopics="removeErrors"
			onSuccessTopics="removeErrors" button="true"
			buttonIcon="ui-icon-disk">
			<s:text name="button.save" />
		</sj:a>
		<s:url var="year" action="year" namespace="/base/year" />
		<sj:a button="true" href="%{year}" indicator="indicator"
			targets="%{tabId}" buttonIcon="ui-icon-arrowreturnthick-1-w">
			<s:text name="button.return" />
		</sj:a>
		<sj:a onClickTopics="closeApp" button="true"
			buttonIcon="ui-icon-close">
			<s:text name="button.close" />
		</sj:a>
	</div>
</div>
<ul id="formerrors" class="errorMessage ui-widget-content ui-corner-all"
	style="display: none;"></ul>
<div id="result" class="result ui-widget-content ui-corner-all"
	style="display: none;"></div>
<div class="formContainer">
	<s:form id="yearForm" action="update" namespace="/base/year">
		<table>
			<tbody>
				<tr>
					<td style="width: 15%"><s:label key="label.year.yearNum" />：</td>
					<td style="width: 35%"><s:textfield id="yearNum"
							name="year.yearNum" /><span class="required">*</span><span
						id="yearNumError"></span></td>
					<td style="width: 15%"><s:label key="label.year.yearName" />：</td>
					<td style="width: 35%"><s:textfield id="yearName"
							name="year.yearName" /><span class="required">*</span><span
						id="yearNameError"></span></td>
				</tr>
				<tr>
					<td><s:label key="label.year.startDate" />：</td>
					<td><sj:datepicker id="startDate" name="year.startDate"
							displayFormat="yy-mm-dd" /></td>
					<td><s:label key="label.year.endDate" />：</td>
					<td><sj:datepicker id="endDate" name="year.endDate"
							displayFormat="yy-mm-dd" /></td>
				</tr>
				<tr>
					<td><s:label key="label.year.enabled" />：</td>
					<td colspan="3"><s:checkbox id="enabled" name="year.enabled" /></td>
				</tr>
			</tbody>
		</table>
		<s:hidden id="id" name="year.id" />
		<s:hidden id="oper" name="oper" />
	</s:form>
</div>