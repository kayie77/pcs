<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
		<script type="text/javascript">
			//处理console报错到问题
			if (!window.console || !console.firebug){
			    var names = ["log", "debug", "info", "warn", "error", "assert", "dir", "dirxml", "group", "groupEnd", "time", "timeEnd", "count", "trace", "profile", "profileEnd"];
			    window.console = {};
			    for (var i = 0; i < names.length; ++i) window.console[names[i]] = function() {}
			}
		</script>
		<script type="text/javascript">
			$(function () {
				$("#subBtn").bind("click", function () {
					
				    $('#messageForm').submit();
			    });
			});
			
		</script>	
<div class="modal-dialog" style="width:800px;">
	<div class="modal-content">
      	<div class="table-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<i class="ace-icon fa fa-file-text"></i>
        	<b>文字类型信息</b>
      	</div>
      	<form class="form-horizontal" id="messageForm" modelAttribute="wordType" action="${actionUrl}" method="POST">
      		<div class="modal-body">
	      		<div class="row">
	      		
	      			<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">文字类型:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
				    			<input style="width:500px" id="title" value="${wordType.title}" name="title" placeholder="文字类型..." required="required" />
							</div>
						</div>
					</div>
					
					<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">基 础 分:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
				    			<input style="width:500px" id="score" value="${wordType.score}" type="number" min="0" value="100" name="score" placeholder="基 础 分..." required="required" />
							</div>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" id="id" name="id" value="${wordType.id}">
			<input type="hidden" id="wtmonth" name="wtmonth" value="${wtmonth}">
			<input type="hidden" id="wtyear" name="wtyear" value="${wtyear}">
			
			
			<div class="modal-footer">
        			<button type="button" class="btn btn-default btn-info btn-sm btn-round" data-dismiss="modal" aria-hidden="true"> 
        				<i class="ace-icon fa fa-times"></i>        			
        				关闭
       				</button>
      	 			<button type="button" id="subBtn" class="btn btn-default btn-info btn-sm btn-round">
          				<i class="ace-icon fa fa-check"></i>
        	 			 保存
          			</button>
       		</div>
       	</form>
    </div>
</div>

<script>
function initSomeStyle() {
	$(".modal-content")[0].style.border = "1px solid #307ecc";
	$(".table-header").find(".close")[0].style.fontSize = "40px";
}
setTimeout("initSomeStyle()",300);
</script>

