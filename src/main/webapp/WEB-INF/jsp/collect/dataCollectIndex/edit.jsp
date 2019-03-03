<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link href="${ctx}/assets/css/formCss.css" rel="stylesheet">
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<script>
	function goSubmit() {
		if($("#indexCode").val() == '' || $("#indexName").val() == '') {
			alert('不能为空！');
			return false;
		}
		$('#dataCollectPersonForm').ajaxSubmit({
			type:"POST",
			dataType:"json",
			success:function(data) {
	            alert(data.message);
	            reloadTakMainTable();
	            $("#closeButtonId").trigger("click");
	        }  
		});
	}
</script>
<div class="modal-content">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title" id="myModalLabel">采集指标信息新增/编辑</h4>
  </div>
  <div class="modal-body">

    <!-- 数据元 表单项 -->
    <form id="dataCollectPersonForm" class="form-horizontal" action="${ctx}/dataCollectIndex/save" method="post">
      <div class="form-group">
        <input type="hidden" name="id" value="${id}"/>
        <label for="name" class="col-sm-2 control-label"><span style="color:red;"> * </span>编码</label>
        <div class="col-sm-3">
          <input type="text" class="form-control" id="indexCode" name="indexCode" value="${indexCode}">
        </div>
        <label for="code" class="col-sm-2 control-label"><span style="color:red;"> * </span>名称</label>
        <div class="col-sm-3">
          <input type="text" class="form-control" id="indexName" name="indexName" value="${indexName}">
        </div>
      </div>
    </form>
  </div>
  <div class="modal-footer">
    <button type="button" class="btn btn-success btn-white btn-round" onclick="$('#dataCollectPersonForm').submit()"><i class="ace-icon fa fa-floppy-o bigger-125"></i>保存</button>
    <button type="button" class="btn btn-danger btn-white btn-round" data-dismiss="modal" id="closeButtonId" ><i class="ace-icon fa fa-times bigger-125"></i>关闭</button>
  </div>
</div>

