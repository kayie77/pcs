<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/jquery/ui/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jqGrid/css/ui.jqgrid.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jstree/themes/default/style.css" />

<script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/bootstrap/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/jquery/plugins/jstree/jstree.min.js"></script>
<script type="text/javascript" src="${ctx}/assets/jquery/plugins/jquery-form/jquery-form.min.js"></script>
<script type="text/javascript">

</script>	
<div class="modal-dialog" style="width:600px;">
	<div class="modal-content">
      	<div class="table-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<i class="ace-icon fa fa-user"></i>
        	<b>选择地区</b>
      	</div>
      		<div class="modal-body">
	      		<div class="row">
	      			<div id="divTree1" style="height:400px;overflow-y:scroll;">
	      			</div>
				</div>
			</div>
			
			<div class="modal-footer">
        			<button type="button" class="btn btn-default btn-info btn-sm btn-round" data-dismiss="modal" aria-hidden="true"> 
        				<i class="ace-icon fa fa-times"></i>        			
        				关闭
       				</button>
      	 			<button type="button" id="subBtn" onclick="saveTree()" class="btn btn-default btn-info btn-sm btn-round" data-dismiss="modal" aria-hidden="true">
          				<i class="ace-icon fa fa-check"></i>
        	 			 保存
          			</button>
       		</div>
    </div>
</div>

<script>

//选择节点
function saveTree() {
	
	//获取选中的单位id
	var divStr=$('#divTree1').jstree('get_checked');
	divStr.sort();
	
	//根据id获取名称
	var divNames = "";
	var divIds = (divStr+"").split(",");
	for(var i = 0;i < divIds.length;i++) {
		divNames += $("#divTree1").find("li[id=" +divIds[i]+ "]").find("a").attr("title") + "，";
	}
	
	//截取名称最后一个逗号
	if(divNames != "") {
		divNames = divNames.substring(0,divNames.length - 1);
	}
	$("#divStr").val((divStr+""));
	$("#divisionNameDiv").html("查询单位："+divNames);
	
	//查询，并隐藏弹出窗口
	toQuery();
	$('#treeModal').modal('hide');
}

$(function() {
	
	$("#divTree1").jstree({
		'plugins' : ['state','dnd','contextmenu','wholerow','checkbox'],
		'core': {
	       'check_callback' : true,
	       'themes': {
	           'name': 'default',
	           'responsive': false
	       },
	       'data' : {
	           'url' : "${ctx}/division/treeData4ReportView",
	           'data' : function (node) {
	               return { 'id' : node.id };//某个市的id
	           }
	         }
	 	}
	});
		
});

//设置弹出窗口边框
function initSomeStyle() {
	$(".modal-content")[0].style.border = "1px solid #307ecc";
	$(".table-header").find(".close")[0].style.fontSize = "40px";
}
setTimeout("initSomeStyle()",300);
</script>


