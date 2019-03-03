<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%
response.setHeader("Pragma","No-cache");    
response.setHeader("Cache-Control","no-cache");    
response.setDateHeader("Expires", -10);   
%>  
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">   
<link rel="stylesheet" href="${ctx}/assets/jquery/ui/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jqGrid/css/ui.jqgrid.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jstree/themes/default/style.css" />

<script src="${ctx}/assets/js/common.js"></script>
<script type="text/javascript">
	var scripts = [null,"${ctx}/assets/jquery/plugins/jstree/jstree.min.js","${ctx}/assets/jquery/plugins/jquery-form/jquery-form.min.js","${ctx}/assets/jquery/plugins/validation/jquery.validate.min.js","${ctx}/assets/jquery/plugins/validation/localization/messages_zh.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/jquery.jqGrid.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/i18n/grid.locale-cn.js","${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js", null]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
		  	jQuery(function($) {
			 	var ctx='${ctx}';	
			 	$("#divTree").jstree({
				 	'plugins' : ['state','dnd','contextmenu','wholerow'],
				 	'core': {
			            'check_callback' : true,
			            'themes': {
			                'name': 'default',
                			'responsive': false
			            },
			            'data' : {
			                'url' : "${ctx}/office/task/getReportTree?id=${param.id}&date=" + new Date().getTime(),
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
		   		}).delegate("a", "click", function(event, data) {
		   			var ids = $(this).parent().attr("id");
		   			var postData = {"dicReportIds":ids,"task_id":'${taskId}'};
					$("#grid-table").jqGrid('setGridParam',{  
						 datatype:'json',  
						 postData:postData
					}).trigger("reloadGrid");
			
				  	event.preventDefault();
			   	});
		
			});
	});
		

	function auditButton() {
		var selectedIds = $("#grid-table").jqGrid('getGridParam','selarrrow');
		if(selectedIds != null && selectedIds.length != 1) {
			bootbox.alert("请选择一条记录!");
		} else {
				
			var rowData = $("#grid-table").jqGrid('getRowData',selectedIds[0]);
			if(rowData.status.indexOf("已上报") == -1) {
				bootbox.alert("未上报，不能审核！");
				return false;
			}
			auditRow(selectedIds[0],'5');
		}
	}
	
	function returnButton() {
		var selectedIds = $("#grid-table").jqGrid('getGridParam','selarrrow');
		if(selectedIds != null && selectedIds.length != 1) {
			bootbox.alert("请选择一条记录!");
		} else {					
			var rowData = $("#grid-table").jqGrid('getRowData',selectedIds[0]);
			if(rowData.status.indexOf("已上报") == -1 && rowData.status.indexOf("已审核通过") == -1) {
				bootbox.alert("未上报，不能重报！");
				return false;
			}
					
			downRow(selectedIds[0]);
		}
	}
			
	function auditRow(taskItemId,status) {				
		var url = "${ctx}/office/task/taskItemAudit?taskItemId="+taskItemId+"&status=" + status+"&r="+new Date().getTime();
		$.getJSON(url,function(data) {
			bootbox.alert(data.message);
			$("#grid-table").trigger('reloadGrid');
		});
	}
	
	function goBackButton() {
		location.href = "${ctx}/index#page/office/task/taskAudit";
	}
	
	function outputButton() {
		
		var selectedIds = $("#grid-table-dicreport").find("tr[aria-selected=true]").size();
		if(selectedIds != 1) {
			bootbox.alert("请选择一条记录!");
			return false;
		}
				
		var id = $("#grid-table-dicreport").find("tr[aria-selected=true]").find("td[aria-describedby=grid-table-dicreport_id]").html();
		window.open("${ctx}/report/recordOutput?dicReportId=" + id + "&taskDivId=${param.id}&date=" + new Date().getTime());
	}
			
	function searchButton() {			
		var array = $('#divTree').jstree('inputtable').find("a[class='jstree-anchor jstree-clicked']");
		if(array.size() < 1) {
			bootbox.alert("请选择记录!");
		} else {					
			var ids = "";
			for(var i = 0;i < array.size();i++) {
				var item = $(array.get(i));
				var templateId = item.attr("id");
				ids += templateId + ",";
			}
					
			if(ids != '') {
				ids = ids.substring(0,ids.length-1);
			}
	
			var postData = {"dicReportIds":ids,"task_id":'${taskId}'};
			$("#grid-table").jqGrid('setGridParam',{  
				 datatype:'json',  
				 postData:postData
			}).trigger("reloadGrid");				    
		}
	}
	
	function viewButton() {				
		//$('#divTree').jstree('inputtable').find("a[class='jstree-anchor jstree-clicked']").size() != 1
		var selectedIds = $("#grid-table-dicreport").find("tr[aria-selected=true]").size();
		if(selectedIds != 1) {
			bootbox.alert("请选择一条记录!");
			return false;
		}
				
		var divCode = "${divcode}";
		var id = $("#grid-table-dicreport").find("tr[aria-selected=true]").find("td[aria-describedby=grid-table-dicreport_id]").html();
		
		$.ajax({
			 type : 'GET',
			 dataType : 'JSON',
			 url : '${ctx}/office/task/viewQueryReport2?id='+id+"&divcode="+divCode+"&taskDivId=${param.id}&taskId=${taskId}&date=",
			 success : function(data) {
			  	//var url = "${ctx_bi}"+data.reportUrl;
			  	var url = "${ctx}"+data.reportUrl;
			  	window.open(url);
			 }
		});
				
	}
	
	function sumButton() {				
		//var selectedIds = $("#grid-table-dicreport").jqGrid('getGridParam','selarrrow');
		var selectedIds = $("#grid-table-dicreport").find("tr[aria-selected=true]").size();
		if(selectedIds != 1) {
			bootbox.alert("请选择一条记录!");
			return false;
		}
				
		bootbox.confirm("确定汇总吗？",function(result) {
			if(result) {	
				
				var inputtable = $("#grid-table-dicreport").find("tr[aria-selected=true]").find("td[aria-describedby=grid-table-dicreport_inputTable]").html();
				var reportcode = $("#grid-table-dicreport").find("tr[aria-selected=true]").find("td[aria-describedby=grid-table-dicreport_reportCode]").html();
				//var inputtable = $("#grid-table-dicreport").find("tr[aria-selected=true]")$('#divTree').jstree('inputtable').find("a[class='jstree-anchor jstree-clicked']").attr("inputtable");
				//var reportcode = $('#divTree').jstree('reportcode').find("a[class='jstree-anchor jstree-clicked']").attr("reportcode");
						
				$("#input_table_param").val(inputtable);
				$("#report_code_param").val(reportcode);
						
				$("#auditform1").ajaxSubmit({
					type: "post",
					url: "${ctx}/office/task/sumReport",
					dataType: "json",
					success: function(result){

						$("#grid-table-dicreport").trigger("reloadGrid");
						bootbox.alert(result.message);
					}
				});
			}
		});
		
	}
	
	function selectTreeNode(ob) {
				
	}
	
	function hasSumFormatter (cellvalue, options, rowObject) {
		if(cellvalue == 'true') {
			return "<span class=\"label label-success arrowed-in arrowed-in-right\">已汇总</span>";
		} else {
			return "<span class=\"label label-warning arrowed arrowed-right\">未汇总</span>";
		}
		
	}
	
	function statusFormatter(cellValue,options,rowObject){
		//未上报、已上报、需重报
		if(cellValue==2){
			return "<span class=\"label label-warning arrowed arrowed-right\">未上报</span>";
		}else if(cellValue == 3){
			return "<span class=\"label label-success arrowed-in arrowed-in-right\">已上报</span>";
		}else if(cellValue == 9){
			return "<span class=\"label label-danger arrowed\">需重报</span>";
		}else{
			return "";
		}
	}
	
	function viewResultsFormatter(cellValue,options,rowObject){
		return "<a href=\"${ctx}/report/reportSubTB?reportCode="+rowObject.reportCode+"&reportNum="+rowObject.reportNum+"&divCode="+rowObject.divCode+"&taskItemId="+rowObject.id+"\" target=\"_blank\">浏览</a>";
	}
</script>
		
<form id="auditform1" name="auditform1" action="${ctx}/office/task/sumReport" method="post">
	<input type="hidden" id="task_id_param" name="task_id_param" value="${param.id}"/>
	<input type="hidden" id="input_table_param" name="input_table_param"/>
	<input type="hidden" id="report_code_param" name="report_code_param"/>
</form>
			
<input type="hidden" id="divIdStr" name="divIdStr" >
<input type="hidden" id="wtYear" name="wtYear" value="${wtYear}">
<input type="hidden" id="wtMonth" name="wtMonth" value="1">
			
<div class="row">
	<div class="col-xs-12">
		 <div class="btn-group">
			<button onclick="sumButton()" type="button" class="btn btn-default btn-info btn-sm btn-round" data-toggle="modal" style="display:none">
				<i class="fa fa-plus-square"></i>
				汇总
			</button>
			<button onclick="returnButton()" type="button" class="btn btn-default btn-info btn-sm btn-round" data-toggle="modal">
				<i class="fa fa-pencil"></i>
				需重报
			</button>
			<button onclick="viewButton()" type="button" class="btn btn-default btn-info btn-sm btn-round" data-toggle="modal" style="margin-left:5px;">
				<i class="fa fa-eye"></i>
				查看报表数据
			</button>
			<button onclick="searchButton()" type="button" class="btn btn-default btn-info btn-sm btn-round" data-toggle="modal" style="display:none">
				<i class="fa fa-eye"></i>
				查询填报情况
			</button>	
			<button onclick="outputButton()" type="button" class="btn btn-default btn-info btn-sm btn-round" data-toggle="modal" style="margin-left:5px;"><!--  style="display:none" -->
				<i class="fa fa-eye"></i>
				过录输出
			</button>	
			<button onclick="goBackButton()" type="button" class="btn btn-info btn-blue btn-sm btn-round" data-toggle="modal" style="margin-left:5px;">
				<i class="fa fa-arrow-left"></i>
				返回
			</button>
		</div>
		<div class="btn-group">		
		 </div>
		<div class="btn-group">	
		</div>
	     <div class="space-6"></div>
	  </div>
	  <div class="col-xs-4"  style="padding:0 2px;">
			<div class="widget-box">
				<div class="widget-header widget-header-small">
					<h5 class="widget-title bolder">报表列表</h5>
					<h5 id="needupload" class="widget-title bolder" style="padding-left:10px;color:red"></h5>
				</div>
				<div class="widget-body" style="height:520px;overflow:hidden;">
					<div class="widget-main no-padding">
						<table id="grid-table-dicreport"></table>
						<div id="grid-pager-dicreport" style="display:none"></div>
						<div id="divTree" style="display:none"></div>
					</div>
				</div>
			</div>
	 </div>
	 <div class="col-xs-8" style="padding:0 2px;">
		    <div class="widget-box">
				<div class="widget-header widget-header-small">
					<h5 class="widget-title bolder">单位填报情况</h5>
					<h5 id="uploadCountH5" class="widget-title bolder" style="padding-left:10px"></h5>
				</div>
				<div class="widget-body" style="height:520px;overflow:hidden;">
					<div class="widget-main no-padding">
					 	<table id="grid-table"></table>
						<div id="grid-pager"></div>
	            	</div>
	        	</div>				
			</div>
	</div>
</div><!-- /.row -->

<div id="taskModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
	  <div class="modal-content">
    	 <div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<h3 class="modal-title">上报审核</h3>
    	 </div>
    	 <div class="modal-body"></div>
    	 <div class="modal-footer">
         	<button type="button" class="btn btn-default btn-info btn-sm btn-round" data-dismiss="modal" aria-hidden="true">
        		<i class="icon-times"></i>         			
        		关闭
       		</button>
      		<button type="submit" class="btn btn-default btn-info btn-sm btn-round">
          		<i class="icon-save"></i> 
        	  	保存
        	</button>
        </div>
  	 </div>
  </div>
</div>

<script type="text/javascript">

function setWidth1() {
	var ww = parseInt($(".col-xs-4").width());
	$("#grid-table-dicreport").jqGrid( 'setGridWidth', ww);
	$("#grid-table").jqGrid( 'setGridWidth', $(".col-xs-8").width());
}
//setInterval("setWidth1()",500);
setTimeout("setWidth1()",500);

var scripts = [null,"${ctx}/assets/jquery/ui/jquery-ui.custom.min.js","${ctx}/assets/jquery/plugins/jquery-form/jquery-form.min.js","${ctx}/assets/jquery/plugins/validation/jquery.validate.min.js","${ctx}/assets/jquery/plugins/validation/localization/messages_zh.min.js","${ctx}/assets/bootstrap/plugins/date-time/bootstrap-datepicker.min.js","${ctx}/assets/bootstrap/plugins/date-time/locales/bootstrap-datepicker.zh-CN.js","${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/jquery.jqGrid.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/i18n/grid.locale-cn.js","${ctx}/assets/js/agrims.js", null]
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
  jQuery(function($) {
	var ctx='${ctx}';
	var g=$("a[href$='${ctx}/office/message']");
	g.closest("li").parent().parent().addClass('active open');
	g.closest("li").addClass('active');
	var f = g.next().get(0);
	
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	//resize to fit page size
	$(window).on('resize.jqGrid', function () {
		var ww = parseInt($(".col-xs-4").width());
		$("#grid-table-dicreport").jqGrid( 'setGridWidth', ww);
		$(grid_selector).jqGrid( 'setGridWidth', $(".col-xs-8").width() );
    });
	
	//resize on sidebar collapse/expand
	var parent_column = $(grid_selector).closest('[class*="col-"]');
	$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
		/*
		if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
			//setTimeout is for webkit only to give time for DOM changes and then redraw!!!
			setTimeout(function() {
				var ww = parseInt($(".col-xs-4").width());
				$("#grid-table-dicreport").jqGrid( 'setGridWidth', ww);
				$(grid_selector).jqGrid( 'setGridWidth', $(".col-xs-8").width() );
				//$(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
			}, 0);
		}*/
    });
	
	
	
	$("#grid-table-dicreport").jqGrid({
		 url:'${ctx}/office/task/getReportTree?id=${param.id}&date=' + new Date().getTime(),
		 datatype: 'JSON',
		 colNames:['id','inputtable', '报表','reportcode','status','hasSum'],
		 colModel:[
		   		{name:'id',index:'id', hidden:true,width:50, editable: true, sortable:false},
		   		{name:'inputTable',index:'inputTable',width:50,editable: true, sortable:true,search:false,hidden:true},
		   		{name:'reportName',index:'reportName',width:40,editable: true, sortable:true,search:false},
		   		{name:'status',index:'status',width:12,editable: true, sortable:true,search:false,formatter:statusFormatter},
		   		{name:'hasSum',index:'hasSum',width:12,editable: true, sortable:true,search:false,formatter:hasSumFormatter},
		   		{name:'reportCode',index:'reportCode',width:50,editable: true, sortable:true,search:false,hidden:true}
		 ],
		 jsonReader : {
				root: "rows",
	            page: "page",
	            total: "total",
	            records: "records",
	            repeatitems: false,
	            cell: "cell",
	            id: "id"
	     },
	     editurl:'${actionUrl}',
	     loadonce:false,
		 rownumbers: false,
		 rowNum:9999,
		 rowList:[10,15,20],
		 pager : "#grid-pager-dicreport",
		 height:550,
		 autowidth: true,
		 altRows: true,
		 viewrecords: true,
		 multiselect: false,		 
		 loadComplete : function() {
			 var table = this;
				setTimeout(function(){
					styleCheckbox(table);
					
					updateActionIcons(table);
					updatePagerIcons(table);
					enableTooltips(table);
					
					$(table).find("tr").each(function(i,ob) {
						ob.style.cursor = "pointer";
					});
				}, 0);
				
				$('.ui-jqgrid-hdiv').hide();
		},
		onSelectRow: function(id){ 
			
			var reportcode = $("#grid-table-dicreport").find("tr[aria-selected=true]").find("td[aria-describedby=grid-table-dicreport_reportCode]").html();
			var url1 = "${ctx}/office/task/getUploadCount?taskDivId=${param.id}&dicReportId="+id+"&date="+new Date().getTime()+"&reportcode="+reportcode;
			$.getJSON(url1,function(data) {
				if(data.success) {
					if(data.isArea) {
						var uploadCount = data.uploadCount;
						var taskItemCount = data.taskItemCount;
						var cityUploadCount = data.cityUploadCount;
						var cityTaskItemCount = data.cityTaskItemCount;
						
						var str = "已上报市级：[&nbsp;" +cityUploadCount+ "&nbsp;/&nbsp;" +cityTaskItemCount+ "&nbsp;]" + "&nbsp;&nbsp;" +
								"县级：[&nbsp;" +uploadCount+ "&nbsp;/&nbsp;" +taskItemCount+ "&nbsp;]";
						$("#uploadCountH5").html(str);
						
						
					} else {
						var uploadCount = data.uploadCount;
						var taskItemCount = data.taskItemCount;
						$("#uploadCountH5").html("已上报：[&nbsp;" +uploadCount+ "&nbsp;/&nbsp;" +taskItemCount+ "&nbsp;]");
						
						if( data.taskReturnReason != null && data.taskReturnReason != ''){  
							$('#needupload').html('需重报信息:'+data.taskReturnReason);
						} else {
							$('#needupload').html('');
						}
					}
				} else {
					$("#uploadCountH5").html("");
				}
			});
			
			//var ids = $(this).parent().attr("id");
   			var postData = {"dicReportIds":id,"task_id":'${taskId}'};
			$("#grid-table").jqGrid('setGridParam',{  
				 datatype:'json',  
				 postData:postData
			}).trigger("reloadGrid");
		}
   });
	
	
	$(grid_selector).jqGrid({
		 url:'${ctx}/office/task/taskReportAuditList?id=${id}',
		 datatype: 'local',
		 colNames:['ID','报表', '单位','群组','上报时间','状态','浏览','地区类型'],
		 colModel:[
		   		{name:'id',index:'id', hidden:true,width:100, editable: true, sortable:false},
		   		{name:'reportName',index:'reportName',width:250,editable: true, sortable:true,search:false},
		   		{name:'divName',index:'divName',width:80,editable: true, sortable:true,search:true,
		   			searchoptions:{  
                    	sopt:["cn"]
                	}
		   		},
		   		{name:'groupName',index:'groupName',width:80,editable: true, sortable:true,search:false},
		   		{name:'subTime',index:'subTime',width:120,editable: true, sortable:true,search:false},	
		   		{name:'viewResults',index:'viewResults',width:40,editable: true, sortable:true,search:false,formatter:viewResultsFormatter},
		   		{name:'status',index:'status', width:80, fixed:true, sortable:false, resize:false, sortable:false,search:false,
		   			formatter:icoFormatter,
		   			editable: false
		   		},
		   		{name:'areaType',index:'areaType',width:1,editable: true,sortable:false,
		   			search:true,
		   			stype: "select",
		   			searchoptions:{  
                        sopt:["eq"],
                        stype:"select",
                        dataUrl:"${ctx}/office/task/areaTypeSearch"
                    }
		   		}
		 ],
		 jsonReader : {
				root: "rows",
	            page: "page",
	            total: "total",
	            records: "records",
	            repeatitems: false,
	            cell: "cell",
	            id: "id"
	     },
	     editurl:'${actionUrl}',
	     loadonce:false,
		 rownumbers: true,
		 rowNum:20,
		 rowList:[10,15,20],
		 pager : pager_selector,
		 height:466,
		 autowidth: true,
		 altRows: true,
		 viewrecords: true,
		 multiselect: true,		 
		 loadComplete : function() {
			 var table = this;
				setTimeout(function(){
					styleCheckbox(table);
					
					updateActionIcons(table);
					updatePagerIcons(table);
					enableTooltips(table);
				}, 0);
		}
    });
		
	$(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
	
	//enable search/filter toolbar
	//jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
	//jQuery(grid_selector).filterToolbar({});
	
	//switch element when editing inline
	function aceSwitch( cellvalue, options, cell ) {
		setTimeout(function(){
			$(cell) .find('input[type=checkbox]')
				.addClass('ace ace-switch ace-switch-5')
				.after('<span class="lbl"></span>');
		}, 0);
	}
	//enable datepicker
	function pickDate( cellvalue, options, cell ) {
		setTimeout(function(){
			$(cell) .find('input[type=text]')
					.datepicker({format:'yyyy-mm-dd' , autoclose:true}); 
		}, 0);
	}
	
	
	//navButtons
	$(grid_selector).jqGrid('navGrid',pager_selector,
	  {
		edit: false,
		editicon : 'ace-icon fa fa-pencil blue',
		edittext:"编辑",
		add: false,
		addicon : 'ace-icon fa fa-plus-circle purple',
		del: false,
		delicon : 'ace-icon fa fa-trash-o red',
		search: true,
		searchicon : 'ace-icon fa fa-search orange',
		searchtext:"查找",
		refresh: false,
		refreshicon : 'ace-icon fa fa-refresh green',
		refreshtext:"刷新",
		view: false,
		viewicon : 'ace-icon fa fa-search-plus grey',
		viewtext:"查看"
		},
		{
			//edit record form
			//closeAfterEdit: true,
			recreateForm: true,
			beforeShowForm : function(e) {
				var form = $(e[0]);
				form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
				style_edit_form(form);
			}
		},
		{
			//new record form
			closeAfterAdd: true,
			recreateForm: true,
			viewPagerButtons: false,
			beforeShowForm : function(e) {
				var form = $(e[0]);
				form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
				style_edit_form(form);
			}
		},
		{
			//delete record form
			recreateForm: true,
			beforeShowForm : function(e) {
				var form = $(e[0]);
				if(form.data('styled')) return false;
				
				form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
				style_delete_form(form);
				
				form.data('styled', true);
			},
			onClick : function(e) {
				alert(1);
			}
		},
		{
			//search form
			recreateForm: true,
			afterShowSearch: function(e){
				var form = $(e[0]);
				form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
				style_search_form(form);
			},
			afterRedraw: function(){
				style_search_filters($(this));
			}
			,
			multipleSearch: false,
			/**
			multipleGroup:true,
			showQuery: true
			*/
		},
		{
			//view record form
			recreateForm: true,
			beforeShowForm: function(e){
				var form = $(e[0]);
				form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
			}
		}
	);
	
	function style_edit_form(form) {
		//enable datepicker on "sdate" field and switches for "stock" field
		form.find('input[name=sdate]').datepicker({format:'yyyy-mm-dd' , autoclose:true})
			.end().find('input[name=stock]')
				.addClass('ace ace-switch ace-switch-5').after('<span class="lbl"></span>');
				   //don't wrap inside a label element, the checkbox value won't be submitted (POST'ed)
				  //.addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');

		//update buttons classes
		var buttons = form.next().find('.EditButton .fm-button');
		buttons.addClass('btn btn-sm').find('[class*="-icon"]').hide();//ui-icon, s-icon
		buttons.eq(0).addClass('btn-primary').prepend('<i class="ace-icon fa fa-check"></i>');
		buttons.eq(1).prepend('<i class="ace-icon fa fa-times"></i>')
		
		buttons = form.next().find('.navButton a');
		buttons.find('.ui-icon').hide();
		buttons.eq(0).append('<i class="ace-icon fa fa-chevron-left"></i>');
		buttons.eq(1).append('<i class="ace-icon fa fa-chevron-right"></i>');		
	}
	
	function style_delete_form(form) {
		var buttons = form.next().find('.EditButton .fm-button');
		buttons.addClass('btn btn-sm btn-white btn-round').find('[class*="-icon"]').hide();//ui-icon, s-icon
		buttons.eq(0).addClass('btn-danger').prepend('<i class="ace-icon fa fa-trash-o"></i>');
		buttons.eq(1).addClass('btn-default').prepend('<i class="ace-icon fa fa-times"></i>')
	}
	
	function style_search_filters(form) {
		form.find('.delete-rule').val('X');
		form.find('.add-rule').addClass('btn btn-xs btn-primary');
		form.find('.add-group').addClass('btn btn-xs btn-success');
		form.find('.delete-group').addClass('btn btn-xs btn-danger');
	}
	
	function style_search_form(form) {
		var dialog = form.closest('.ui-jqdialog');
		var buttons = dialog.find('.EditTable')
		buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'ace-icon fa fa-retweet');
		buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'ace-icon fa fa-comment-o');
		buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'ace-icon fa fa-search');
	}
	
	function beforeDeleteCallback(e) {
		var form = $(e[0]);
		if(form.data('styled')) return false;
		
		form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
		style_delete_form(form);
		
		form.data('styled', true);
	}
	
	function beforeEditCallback(e) {
		var form = $(e[0]);
		form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
		style_edit_form(form);
	}
	
	
	
	//it causes some flicker when reloading or navigating grid
	//it may be possible to have some custom formatter to do this as the grid is being created to prevent this
	//or go back to default browser checkbox styles for the grid
	function styleCheckbox(table) {
	/**
		$(table).find('input:checkbox').addClass('ace')
		.wrap('<label />')
		.after('<span class="lbl align-top" />')
	
	
		$('.ui-jqgrid-labels th[id*="_cb"]:first-child')
		.find('input.cbox[type=checkbox]').addClass('ace')
		.wrap('<label />').after('<span class="lbl align-top" />');
	*/
	}
	
	
	//unlike navButtons icons, action icons in rows seem to be hard-coded
	//you can change them like this in here if you want
	function updateActionIcons(table) {
		
		var replacement = 
		{
			'ui-ace-icon fa fa-pencil' : 'ace-icon fa fa-pencil blue',
			'ui-ace-icon fa fa-trash-o' : 'ace-icon fa fa-trash-o red',
			'ui-icon-disk' : 'ace-icon fa fa-check green',
			'ui-icon-cancel' : 'ace-icon fa fa-times red'
		};
		$(table).find('.ui-pg-div span.ui-icon').each(function(){
			var icon = $(this);
			var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
			if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
		})
		
	}
	
	//replace icons with FontAwesome icons like above
	function updatePagerIcons(table) {
		var replacement = 
		{
			'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
			'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
			'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
			'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
		};
		$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
			var icon = $(this);
			var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
			
			if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
		})
	}
	
	function enableTooltips(table) {
		$('.navtable .ui-pg-button').tooltip({container:'body'});
		$(table).find('.ui-pg-div').tooltip({container:'body'});
	}
	
	//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
	
	$(document).on('ajaxloadstart', function(e) {
		$(grid_selector).jqGrid('GridUnload');
		//$("#grid-table-dicreport").jqGrid('GridUnload');
		$('.ui-jqdialog').remove();
	});
	
  });
});




function downRow(id){

	$.get("${ctx}/office/task/toDownTaskReport?id="+id+"&"+Math.random(1000) , '', function(data){ 
		var modal = $('#taskModal');
		modal.html(data);
		modal.modal({show:true,backdrop:false}).on("hidden", function(){
	    	$(this).remove();
		});
        var form = modal.find('form:eq(0)');
        form.validate({	
			rules : {
				title : {
					required : true,
					minlength : 1,
					maxlength : 100
				},
				content : {
					required : true,
					minlength : 1,
					maxlength : 2000
				},
				div_code : {
					required : true,
					minlength : 6,
					maxlength : 6
				}
			},
			//errorElement: "span",
			errorPlacement: function(error, element) {
				console.log(error);
			    error.insertAfter(element.parent());
			},
			highlight: function(element) {
			    //$(element).closest('.form-group').removeClass('success').addClass('error');
			},
			submitHandler: function (form) {
				$(form).ajaxSubmit({
					//target: "#result",
					type:"POST",
					dataType:"json",
					success:function(data,status) {
			            console.log(data)
			            if(data.success===true) {
			            	$("#grid-table").trigger('reloadGrid');
			            	modal.modal('hide');
			            	bootbox.dialog({
	 							message: data.message,
	 							buttons: 			
	 							{
	 								"success" :
	 								 {
	 									"label" : "<i class='ace-icon fa fa-check'></i>确定",
	 									"className" : "btn-sm btn-success",
										"callback": function() {	
										}
	 								}
	 							}
	 						});
			            } else {
			            	modal.modal('hide');
			            	 bootbox.dialog({
									message: data.message,
									buttons: 			
									{
										"danger" :
										 {
											"label" : "<i class='ace-icon fa fa-exclamation-triangle'></i>确定",
											"className" : "btn-sm btn-success",
											"callback": function() {	
											}
										}
									}
							});
			            }
			        }  
				});
			},
		});
    });    
}
</script>