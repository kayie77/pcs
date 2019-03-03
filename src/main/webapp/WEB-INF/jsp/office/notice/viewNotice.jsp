<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<div class="modal-dialog" style="width:800px;">
	<div class="modal-content">
      	<div class="table-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<i class="ace-icon fa fa-user"></i><b>通知信息</b>
      	</div>
      	
      		<div class="modal-body">
	      		<div class="row">
	      		
	      		
	      			<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">消息来源:</label>
							<div class="col-sm-8 controls">
				    			${divName}
							</div>
						</div>
					</div>
					
	      			<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">标题:</label>
							<div class="col-sm-8 controls">
				    			${notice.title}
							</div>
						</div>
					</div>
					
					<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="content">内容:</label>
							<div class="col-sm-8 controls">
				    			${notice.ntcontent}
							</div>
						</div>
					</div>
					
						
				</div>
			</div>
			
			<div class="modal-footer">
        			<button type="button" class="btn btn-default btn-info btn-sm btn-round" data-dismiss="modal" aria-hidden="true"> 
        				<i class="ace-icon fa fa-times"></i>        			
        				关闭
       				</button>
       		</div>

    </div>
</div>
<script>
function initSomeStyle() {
	$(".modal-content")[0].style.border = "1px solid #307ecc";
	$(".table-header").find(".close")[0].style.fontSize = "40px";
}
setTimeout("initSomeStyle()",300);
</script>