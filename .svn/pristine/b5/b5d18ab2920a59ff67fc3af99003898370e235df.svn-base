<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<style type="text/css">
.uploadify-button {
	background-color: transparent;
	border: none;
	padding: 0;
	border-radius: 0px;
}

.uploadify:hover .uploadify-button {
	background-color: transparent;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$('#uploadify').uploadify({
		'buttonImage' : '<s:url value="/images/btn-browser.png"/>',
		'fileObjName' : 'uploadify',
		'fileTypeDesc' : '电子表格文件',
        'fileTypeExts' : '*.xls',
        'height':39,
        'auto': false,
        'multi'    : false,
        'progressData' : 'speed',
		'method'   : 'post',
        'swf' : '<s:url value="/images/flash/uploadify.swf"/>',
        'uploader' : '<s:url value="/item/doUploadHistory.action"/>',
        'onUploadSuccess' : function(file, data, response) {
        	var result=$.parseJSON(data);
            if(result.success==true){
            	  jAlert(result.message);
            	  jQuery("#itemImportTable").trigger('reloadGrid');	
            }else{
            	 jAlert(result.message);
            }
         },
         'onUploadError' : function(file, errorCode, errorMsg, errorString) {
             jAlert('上传失败!');
         }
    });
	
	 $.subscribe('uploadTopic', function(event,data) {
		 jQuery('#uploadify').uploadify('upload');
     });
	 
	 $.subscribe('clearTopic', function(event,data) {
		 jQuery('#uploadify').uploadify('cancel');
     });
});
</script>
<table>
	<tbody>
		<tr>
			<td width="65%"><s:file id="uploadify" name="uploadify" /></td>
			<td width="35%" rowspan="2"
				style="vertical-align: top; padding-left: 6px;">
				<p style="line-height: 20px; color: #999;">
					<font style="font-weight: 600">说明</font>：系统只支持Excel
					97-20003格式的电子表格文件，如"采购项目.xls"，项目文档模板请到“我的桌面”相应栏目下载。
				</p>
			</td>
		</tr>
		<tr>
			<td style="text-align: center;"><sj:a id="uploadBtn"
					onClickTopics="uploadTopic" button="true">  上传  </sj:a> <sj:a
					id="clearBtn" onClickTopics="clearTopic" button="true">  取消  </sj:a>
			</td>
		</tr>
	</tbody>
</table>