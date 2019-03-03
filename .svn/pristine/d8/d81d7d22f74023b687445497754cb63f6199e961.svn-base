<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<script type="text/javascript">
$(document).ready(function() {
	 jQuery.struts2_jquery.require("js/plugins/jquery.form.min.js");
	 
	var bar = $('.bar');
	var percent = $('.percent');
	var status = $('#status');
	
	 $('#uploadItemForm').ajaxForm({
		    beforeSend: function() {
		        status.empty();
		        var percentVal = '0%';
		        bar.width(percentVal)
		        percent.html(percentVal);
		    },
		    uploadProgress: function(event, position, total, percentComplete) {
		        var percentVal = percentComplete + '%';
		        bar.width(percentVal)
		        percent.html(percentVal);
				//console.log(percentVal, position, total);
		    },
			complete: function(xhr) {
				$('#submitBtn').removeClass('ui-state-disabled');
			}
		}); 
	$.subscribe('clickSubmit', function(event,data) {
		  $('#submitBtn').addClass('ui-state-disabled');
	});
});
</script>
<ul id="formerrors" class="errorMessage ui-widget-content ui-corner-all"
	style="display: none;"></ul>
<div class="formContainer">
	<s:form id="uploadItemForm" action="doUpload" namespace="/item/detail"
		enctype="multipart/form-data">
		<table>
			<tbody>
				<tr>
					<td><s:file id="upload" name="upload" /></td>
				</tr>
			</tbody>
		</table>
		<div style="text-align: center">
			<sj:submit id="submitBtn" onClickTopics="clickSubmit" button="true"
				value="上传" />
		</div>
	</s:form>
</div>
<div class="progress">
	<div class="bar"></div>
	<div class="percent">0%</div>
</div>
<div id="status"></div>