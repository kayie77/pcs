<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jstree/themes/default/style.css" />
<script type="text/javascript" charset="utf-8" src='${ctx}/assets/kindeditor-4.1.7/kindeditor.js'></script>
<script type="text/javascript" charset="utf-8" src='${ctx}/assets/kindeditor-4.1.7/lang/zh_CN.js'></script>	
<script type="text/javascript">
			var keditor;
			
			keditor = KindEditor.create('#contentTemp', {
                allowFileManager : false,
                items: ["source", "|", "undo", "redo", "|", "justifyleft", "justifycenter", "justifyright", "justifyfull", "insertorderedlist", "insertunorderedlist", "indent", "outdent", "subscript", "superscript", "clearhtml", "quickformat", "selectall", "|", "fullscreen", "/", "formatblock", "fontname", "fontsize", "|", "forecolor", "hilitecolor", "bold", "italic", "underline", "strikethrough", "lineheight", "removeformat", "|", "image", "table", "hr", "pagebreak", "anchor", "link", "unlink"]
        	});
        	
			var scripts = [null,"${ctx}/assets/jquery/plugins/jstree/jstree.min.js","${ctx}/assets/jquery/plugins/jquery-form/jquery-form.min.js","${ctx}/assets/jquery/plugins/validation/jquery.validate.min.js","${ctx}/assets/jquery/plugins/validation/localization/messages_zh.min.js","${ctx}/assets/bootstrap/plugins/date-time/bootstrap-datepicker.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/jquery.jqGrid.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/i18n/grid.locale-cn.js","${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js", null]
			$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
			  	jQuery(function($) {
			  		//$("#divIdStr").val("");
				 	var ctx='${ctx}';	
				 	$("#divTree").jstree({
					 	'plugins' : ['state','dnd','contextmenu','wholerow','checkbox'],
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
			   		        		$(this).andSelf().removeClass("jstree-unchecked jstree-undetermined").addClass("jstree-clicked");
			   		        	}
			   		        } 
			   	    	}
			   		}).delegate("a", "click", function(event, d) {
					  	event.preventDefault();
				   	});
			
				});
			});
			function test() {
				$("#divTree").jstree("open_all");
			}
			
			$(function () {
				
				setTimeout("test()",500);
				
				$("#subBtn").bind("click", function () {
					$('#ntcontent').val(keditor.html());
					if($('#ntcontent').val().length > 2000) {
						//alert('内容不能超过2000个字！');
						//return false;
					}
					
					//取得所有选中的节点，返回节点对象的集合   
					var divIds=$("#divTree").jstree("get_checked");
				    $('#divIdStr').val(divIds); 
				    $('#noticeForm').submit();
			    });
			});

		</script>	
<div class="modal-dialog" style="width:800px;">
	<div class="modal-content">
      	<div class="table-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<i class="ace-icon fa fa-user"></i>
        	<b>通知信息</b>
      	</div>
      	<form:form cssClass="form-horizontal" id="noticeForm" modelAttribute="notice" action="${actionUrl}" method="POST">
      		<div class="modal-body">
	      		<div class="row">
	      			<div class="col-sm-12">
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">标题:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
				    			<form:input cssStyle="width:500px" id="title" value="${notice.title}" path="title" placeholder="标题..." required="required" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="content">内容:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
								<%/*
				    			<form:textarea cssStyle="width:500px;height:100px;" id="content" value="${notice.content}" path="content" placeholder="内容..." required="required" />
				    			*/%>
								<textarea id="ntcontent" name="ntcontent" style="display:none"></textarea>
								<textarea id="contentTemp" name="contentTemp" style="width:100px;height:250px;" placeholder="内容..." required="required">${notice.ntcontent}</textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label" for="status">状态:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
								<select name="status" id="status" style="width:500px">
									<c:forEach var="statusItem" items="${statusList}">
										<option value="${statusItem.value}" <c:if test="${statusItem.value == notice.status}"> selected </c:if>>${statusItem.key}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group" style="height:200px;overflow-y:scroll;">
							<label class="col-sm-2 control-label" for="status">地区:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
								<div id="divTree"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<input type="hidden" id="divIdStr" name="divIdStr" value="${divIdStr }">
			<input type="hidden" id="id" name="id" value="${notice.id}">
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
       	</form:form>
    </div>
</div>

<script>
function initSomeStyle() {
	$(".modal-content")[0].style.border = "1px solid #307ecc";
	$(".table-header").find(".close")[0].style.fontSize = "40px";
}
setTimeout("initSomeStyle()",300);
</script>


