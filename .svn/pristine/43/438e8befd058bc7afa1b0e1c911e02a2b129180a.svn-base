<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<script type="text/javascript">
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

function tt() {
	$("#divTree").find("a").each(function() {
		 var tempid=$(this).attr("id");  
		 $(this).andSelf().removeClass("jstree-clicked");
	});
	$("#divTree").find("i").each(function() {
		 var tempid=$(this).attr("id");  
		 $(this).andSelf().removeClass("jstree-undetermined");
	});
}
function test() {
	$("#divTree").jstree("open_all");
	
	
	//setTimeout("tt()",3000);
}

$(function () {
	setTimeout("test()",500);
	$("#subBtn").bind("click", function () {
		
		$("#closeBtnId")[0].style.display = "none";
		$("#subBtn")[0].style.display = "none";
		$("#closeSpan")[0].style.display = "none";
		$("#subingBtn")[0].style.display = "";
		
		//取得所有选中的节点，返回节点对象的集合   
		var divIds=$("#divTree").jstree("get_checked");
	    $('#divIdStr').val(divIds); 
	    $('#taskDivForm').submit();
    });
});

</script>	
<div class="modal-dialog">
	<div class="modal-content">
      	<div class="modal-header no-padding">
        	<div class="table-header">
				  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						<span class="white" id="closeSpan">&times;</span>
				  </button>
				  <i class="ace-icon glyphicon glyphicon-map-marker"></i><b> 选择区域</b>
			 </div>
      	</div>
		<form:form cssClass="form-horizontal" id="taskDivForm" modelAttribute="taskDiv" action="${actionUrl}" method="POST">
    		<div class="modal-body" style="height:400px;overflow-y:scroll;">
      			<div class="row">
      				<div class="col-sm-12">
						<div class="form-group">
							<div id="divTree"></div>
						</div>
					</div>
				</div>
			</div>
			<input type="hidden" id="taskId" name="taskId" value="${task.id}">
			<input type="hidden" id="divIdStr" name="divIdStr" value="${divIdStr }">
			<div class="modal-footer">
        		<button id="closeBtnId" type="button" class="btn btn-info btn-blue btn-sm btn-round" data-dismiss="modal" aria-hidden="true"> 
        			<i class="ace-icon fa fa-times"></i>        			
        			关闭
       			</button>
      			<button id="subBtn" type="button" class="btn btn-info btn-blue btn-sm btn-round">
          			<i class="ace-icon fa fa-check"></i>
        	  		发布
        		</button>
        		
        		<button id="subingBtn" type="button" class="btn btn-info btn-blue btn-sm btn-round" style="display:none">
          			<i class="ace-icon fa fa-check"></i>
        	  		发布中..
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
