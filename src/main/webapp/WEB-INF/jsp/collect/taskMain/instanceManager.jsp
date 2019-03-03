<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link href="${ctx}/assets/css/formCss.css" rel="stylesheet">
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<div class="modal-content">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title" id="myModalLabel">任务实例化</h4>
  </div>
  <div class="modal-body">
	<div id="loading_div"><img src="${ctx}/assets/images/loading.gif"/></div>
	<div id="result_div"></div>
  </div>
  <div class="modal-footer">
  <div class="form-group">
    <button type="button" class="btn btn-danger btn-white btn-round" data-dismiss="modal"><i class="ace-icon fa fa-times bigger-125"></i>关闭</button>
  </div>
  </div>
</div>
<script>
	$(document).ready(function () {
    	$.ajax({
			url : "${ctx}/collect/taskMain/instance",
			data : {
				"taskMainId" : '${taskMainId}',
				"beginDate" : '${beginDate}',
				"endDate" : '${endDate}'
			},
			type : "POST",
			dataType : 'json',
			beforeSend:loading,//执行ajax前执行loading函数.直到success
			success : function(result) {
				if(result.instanceResult.instanceStatus=="OK"){
					$('#result_div').html("实例化成功!"); 
				}
			},
			complete : function(data) {
				$('#loading_div').remove();
			}
		})
	});
	
	function loading(){ 
		$('#loading_div').html('正在实例化<img src="${ctx}/assets/images/loading.gif"/>'); 
		} 
	function Response(data){ 
		$('#loading_div').html(data); 
		} 
</script>