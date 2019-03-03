<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

		<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jstree/themes/default/style.css" />
		<script type="text/javascript">
			//处理console报错到问题
			if (!window.console || !console.firebug){
			    var names = ["log", "debug", "info", "warn", "error", "assert", "dir", "dirxml", "group", "groupEnd", "time", "timeEnd", "count", "trace", "profile", "profileEnd"];
			    window.console = {};
			    for (var i = 0; i < names.length; ++i) window.console[names[i]] = function() {}
			}
		</script>
		
		<script type="text/javascript">
		
		
			var scripts = [null,"${ctx}/assets/jquery/plugins/jstree/jstree.min.js","${ctx}/assets/jquery/plugins/jquery-form/jquery-form.min.js","${ctx}/assets/jquery/plugins/validation/jquery.validate.min.js","${ctx}/assets/jquery/plugins/validation/localization/messages_zh.min.js","${ctx}/assets/bootstrap/plugins/date-time/bootstrap-datepicker.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/jquery.jqGrid.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/i18n/grid.locale-cn.js","${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js", null]
			$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
			  	jQuery(function($) {
			  		//$("#divIdStr").val("");
				 	var ctx='${ctx}';	
				 	$("#divTree").jstree({
					 	'plugins' : ['state','dnd','contextmenu','wholerow','checkbox'],
					 	'checkbox' : { three_state : false },
					 	'core': {
				            'check_callback' : true,
				            'themes': {
				                'name': 'default',
	                			'responsive': false
				            },
				            'data' : {
				                'url' : "${ctx}/division/treeData",
				                'data' : function (node) {
				                    return { 'id' : node.id };
				                }
				              }
				      	}
			       	}).bind("load_node.jstree", function(e, data) {
			       		var checkDiv = $("#divIdStr").val();
				    	var array = checkDiv.split(",");
			       		var nodeIds = data.node.children;
						
			      		for(var i=0;i<nodeIds.length;i++){
			   		        for(var j=0;j<array.length;j++){ 
			   		        	if(array[j] == nodeIds[i]){
			   		        		//$(this).andSelf().removeClass("jstree-unchecked jstree-undetermined").addClass("jstree-clicked");
			   		        	}
			   		        } 
			   	    	}
			      		$("#divTree").jstree("clear_state");
			      		//clear_state ()
			   		}).delegate("a", "click", function(event, d) {
					  	event.preventDefault();
				   	});
			
				});
			});
			function test() {
				//$("#divTree").jstree("close_all");
				//$("#divTree").jstree("open_all");
				//$("#divTree").find("a").removeClass("jstree-clicked");
				//jstree-anchor
				$("#divTree").find("a").each(function(i,ob) {
					//ob.className = "jstree-anchor";
				});
				$("#divTree").find("div").each(function(i,ob) {
					//ob.className = "jstree-wholerow";
				});
				$("#divTree").find("li").each(function(i,ob) {
					//$(ob).attr("aria-selected","");
				});
				
				
			}
			$(function () {
				//setTimeout("test()",1000);
				$("#subBtn").bind("click", function () {

					//取得所有选中的节点，返回节点对象的集合   
					var divIds=$("#divTree").jstree("get_checked");
					
					var parentMessageCreateDivId = $("#parentMessageCreateDivId").val();
					
					if(parentMessageCreateDivId != 'undefined' && parentMessageCreateDivId != null) {
						divIds = parentMessageCreateDivId;
					}
					//alert(divIds);
				    $('#divIdStr').val(divIds); 
				    $('#messageForm').submit();
			    });
			});
			function deletebutton(ob) {
				$(ob).parent().remove();
			}
			function addfile() {
				$("#filediv").append("<div><input type='file' style='width:250px;position:relative;float:left;' name='uploadfile'/><input type='button' value='删除' onclick='deletebutton(this)' style='position:relative;float:left;'/></div>");
			}
		</script>	
<div class="modal-dialog" style="width:800px;">
	<div class="modal-content">
      	<div class="table-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<i class="ace-icon fa fa-envelope-o"></i>
        	<b>短信信息</b>
      	</div>
      	<form class="form-horizontal" id="messageForm" modelAttribute="message" action="${actionUrl}" method="POST" enctype="multipart/form-data">
      		<div class="modal-body">
	      		<div class="row">
	      			<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">主题:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
				    			<input style="width:500px" id="title" value="${message.title}" name="title" placeholder="主题..." required="required" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">特性:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
				    			<input type="checkbox" id="important" name="important" value="1"/>重要消息 
				    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    			<input type="checkbox" id="needreplay" name="needreplay" value="1"/>要求回复
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="content">内容:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
								<textarea id="contentStr" name="contentStr" style="width:500px;height:250px;" placeholder="内容..." required="required">${message.content}</textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label" for="content">附件:<span class="required"></span></label>
							<div class="col-sm-8 controls">
								<button type="button" onclick="addfile()" class="btn btn-default btn-info btn-sm btn-round"> 
			        				<i class="ace-icon fa fa-plus"></i>        			
			        				新增附件
			       				</button>
							</div>
						</div>
       				
       					<div class="form-group">
							<label class="col-sm-2 control-label" for="content"><span class="required"></span></label>
							<div class="col-sm-8 controls" id="filediv">
								
								<!-- 
								<div><input type='file' style='width:180px' name='uploadfile'/><input type='button' value='删除' onclick='deletebutton(this)'/></div>
								<div><input type='file' style='width:180px' name='uploadfile'/></div>
								<div><input type='file' style='width:180px' name='uploadfile'/></div>
								<div><input type='file' style='width:180px' name='uploadfile'/></div>
								 -->
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label" >收件人:<span class="required">*</span></label>
							<div class="col-sm-4 controls" style="width:80%;">
								<div id="divTree" style="overflow:scroll;height:200px;width:100%;"></div>
								<c:if test="${parentMessage != null}">
									<script>
										$("#divTree")[0].style.display = "none";
									</script>
									${parentMessage.createDiv.divName}
									<input type="hidden" id="parentMessageCreateDivId" value="${parentMessage.createDiv.id}"/>
								</c:if>
							</div>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" id="divIdStr" name="divIdStr" value="${divIdStr }">
			<input type="hidden" id="id" name="id" value="${message.id}">
			<input type="hidden" id="parentId" name="parentId" value="${parentId}">
			<input type="hidden" id="messageSendId" name="messageSendId" value="${messageSendId}">
			
			<c:if test="${parentMessage != null}">
				<script>
					$("#divIdStr").val('${parentMessage.createDiv.id}');
				</script>
			</c:if>
			
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

