<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

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
        	<i class="ace-icon fa fa-star"></i>
        	<b>评分</b>
      	</div>
      	<form class="form-horizontal" id="messageForm" action="${ctx}/office/wordtypeinfo/updateWordTypeInfoScore" method="POST" >
      		<div class="modal-body">
	      		<div class="row">
	      			<div class="col-sm-12">
	      				<input type="hidden" id="wordTypeInfoId" name="wordTypeInfoId" value="${wordTypeInfoId}"/>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">评分依据:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
				    			<select id="useStandardId" name="useStandardId">
				    				<c:forEach var="useStandardItem" items="${useStandardList}">
				    					<option value="${useStandardItem.id}">${useStandardItem.title}</option>
				    				</c:forEach>
				    			</select>
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
      	 			<button type="button" id="subBtn" class="btn btn-default btn-info btn-sm btn-round">
          				<i class="ace-icon fa fa-check"></i>
        	 			 保存
          			</button>
       		</div>
       	</form>
    </div>
</div>
