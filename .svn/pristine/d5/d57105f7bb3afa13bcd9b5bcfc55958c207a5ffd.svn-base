<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/jquery/ui/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jqGrid/css/ui.jqgrid.css" />
<link rel="stylesheet" href="${ctx}/assets/bootstrap/plugins/date-time/css/datepicker.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jstree/themes/proton/style.css" />
<style>
#backTaskBtn,#expAllReports,#impAllReportsTBDatas{
	margin:0 6px 0 0;
}
</style>
<input type="hidden" id="divCode" value="${divCode }" />
<div class="row">
	<div class="col-xs-12">
		<div class="btn-group">
			<button id="showInputReportBtn" type="button" class="btn btn-info btn-blue btn-sm btn-round" data-toggle="modal" style="display:none">
				<i class="fa fa-eye"></i>   
				查看
			</button>
			<button id="backTaskBtn" type="button" class="btn btn-info btn-blue btn-sm btn-round" data-toggle="modal">
				<i class="fa fa-arrow-left"></i>
				返回
			</button>
			<button id="expAllReports" type="button" class="btn btn-info btn-blue btn-sm btn-round" data-toggle="modal">
				<i class="fa fa-arrow-down"></i>
				导出
			</button>
			<button id="impAllReportsTBDatas" type="button" class="btn btn-info btn-blue btn-sm btn-round" data-toggle="modal">
				<i class="fa fa-arrow-up"></i>
				导入
			</button>
		</div>
		<div class="space-6"></div>
		<table id="grid-table"></table>
		<div id="grid-pager"></div>
	</div><!-- /.col -->
</div><!-- /.row -->

<form action="${ctx}/report/expTBTemplate" method="POST" id="expTBAllReports" accept="application/json">
	<input type="hidden" id="taskIdExpTBTemplate" name="taskId" value="${taskId}"/>
	<input type="hidden" id="reportNumExpTBTemplate" name="reportNum" value="${reportNum}"/>
	<input type="hidden" id="beforeReportNumExpTBTemplate" name="beforeReportNum" value="${beforeReportNum}"/>
	<input type="hidden" id="divCodeExpTBTemplate" name="divCode" value="${divCode}"/>
	<input type="hidden" id="lastReportNumExpTBTemplate" name="lastReportNum" value="${lastReportNum}"/>
</form>







<div id="uploadModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
	  <div class="modal-content">
    	 <div class="modal-body">
    	 	<form action="${ctx}/report/impTBDatas" method="POST" id="impFileAction" enctype="multipart/form-data">
		    	<table style="width:100%;">
		    		<tr>
		    			<td valign="top">文件名：</td>
		    			<td>
			    			<input type="hidden" id="taskItemId4Upload" name="taskId" />
							<input type="hidden" id="divCode4Upload" name="divCode" />
							<input type="file" id="impFile" name="file" style="width:500px">
							<label id="jindu"></label>
		    			</td>
		    		</tr>
		    	</table>
			</form>
    	 </div>
    	 <div class="modal-footer">
         	<button id="inputFileButtonId" type="button" onclick="submitImportFile()" class="btn btn-default btn-info btn-round">
        		<i class="fa fa-upload"></i>
        		上传
       		</button>
       		<button type="button" class="btn btn-default btn-info btn-round" data-dismiss="modal" aria-hidden="true">
        		<i class="ace-icon fa fa-times"></i>
        		关闭
       		</button>
        </div>
  	 </div>
  </div>
</div>




<script type="text/javascript">

function inputFormatter1 (cellvalue, options, rowObject)
{
	if(rowObject.status == 9 || rowObject.status == 12){
		//rowObject.status == 2 || rowObject.status == 9 || rowObject.status == 12
		return "<div style=\"text-align:center;\" class=\"ui-pg-div ui-inline-edit\" id=\"jEditButton_1\" onclick=\"upRow('"+rowObject.id+"');\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\" data-original-title=\"上报报表\"><span class=\"ui-icon ui-icon-share\"><img src='${ctx}/assets/images/task/uploadtask.png'/></span></div>";
	}else{
		return "";
	}
}

var scripts = [null,"${ctx}/assets/jquery/plugins/jstree/jstree.min.js","${ctx}/assets/jquery/plugins/jquery-form/jquery-form.min.js","${ctx}/assets/jquery/plugins/validation/jquery.validate.min.js","${ctx}/assets/jquery/plugins/validation/localization/messages_zh.min.js","${ctx}/assets/bootstrap/plugins/date-time/bootstrap-datepicker.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/jquery.jqGrid.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/i18n/grid.locale-cn.js","${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js","${ctx}/assets/js/agrims.js", null]
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
  	jQuery(function($) {
		var ctx='${ctx}';
	
		//resize to fit page size
		$(window).on('resize.jqGrid', function () {
			$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width() );
	    });
		
		//resize on sidebar collapse/expand
		var parent_column = $(grid_selector).closest('[class*="col-"]');
		$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
			if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
				//setTimeout is for webkit only to give time for DOM changes and then redraw!!!
				setTimeout(function() {
					$(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
				}, 0);
			}
	    });
		
		
		var g=$("a[href$='${ctx}/index#page/office/task/waitlist']");
		g.closest("li").parent().parent().addClass('active open');
		g.closest("li").addClass('active');
		var f = g.next().get(0);
		
		var grid_selector = "#grid-table";
		var pager_selector = "#grid-pager";
		var pageSize=${pageSize};
		
		$(grid_selector).jqGrid({
			 url:'${ctx}/office/task/listTaskItem?taskId=${task.id}&divCode=${divCode }',
			 datatype: 'JSON',
			 colNames:['ID','报表名称','需重报信息','实际上报时间','状态','上报'],
			 colModel:[
			   		{name:'id',index:'id', hidden:true,width:100, editable: true},
			   		{name:'reportName',index:'reportName',width:300,editable: true},
			   		{name:'taskReturnReason',index:'taskReturnReason',width:100,editable: false, sortable:false,search:false},
			   		{name:'subTime',index:'subTime',width:80,editable: false, sortable:false,search:false},
			   		{name:'status',index:'status', width:80, fixed:true, sortable:false, resize:false,
			   			formatter:icoFormatter,
			   			editable: false
			   		},
			   		{name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,
			   			formatter:inputFormatter1,
			   			editable: false
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
			 rowNum:pageSize,
			 rowList:[10,15,20],
			 pager : pager_selector,
			 height:500,
			 autowidth: true,
			 altRows: true,
			 viewrecords: true,
			 multiselect: false,		 
			 loadComplete : function() {
				 var table = this;
					setTimeout(function(){
						updateActionIcons(table);
						updatePagerIcons(table);
						enableTooltips(table);
						
						$(table).find("tr").each(function(i,ob) {
							ob.style.cursor = "pointer";
						});
					}, 0);
			},
			onCellSelect: function(rowid, iCol, cellcontent, e){ 
				if(iCol != 6) {
					showRow(rowid);
				}
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
			search: false,
			searchicon : 'ace-icon fa fa-search orange',
			searchtext:"查找",
			refresh: true,
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
			$('.ui-jqdialog').remove();
		});
		
		$("#showInputReportBtn").click(function(e){
			var rowId = $(grid_selector).jqGrid('getGridParam','selrow');
			if(rowId){
				showRow(rowId);
			}else{
				bootbox.alert("请选择一条记录!");
			}
			e.preventDefault();
		});
		
		$("#backTaskBtn").click(function(){
			window.location.href="${ctx}/index#page/office/task/wait";
		});
		
		$('#expAllReports').click(function(){
			$('#expTBAllReports').submit();
		});
		
		$('#impAllReportsTBDatas').click(function(){
			//$('#impFile').click();
			
			var modal = $('#uploadModal');
			modal.modal({show:true,backdrop:false}).on("hidden", function(){
				$(this).remove();
			});
		});
		
		/*
		$('#impFile').change(function(){//模拟选择文件时,触发提交事件
			
		});
		*/ 
	});
});


function submitImportFile() {
	
	var rightFileType=new Array('xls','xlsx');//Excel拓展名
	if($('#impFile').val()==''){
		alert('未选择文件!');
	}else{
		var isExcel=false;
		var fileType=$('#impFile').val().substring($('#impFile').val().lastIndexOf('.')+1);
		for(var i=0;i<rightFileType.length;i++){
			if(rightFileType[i]==fileType){
				isExcel=true;
				break;
			}
		}
		if(isExcel==true){//选择的文件为Excel类型
			$('#ImpData').attr('disabled',true);
			$('#inputFileButtonId').attr('disabled',true);
			$("#taskItemId4Upload").val($('#taskIdExpTBTemplate').val());
			$("#divCode4Upload").val($('#divCodeExpTBTemplate').val());
			
			/*
			var impFileActionFormData;
			if(typeof FormData =="undefined"){//IE8 下不支持 FormData
				impFileActionFormData=[];
				impFileActionFormData.push('file[]',$('#impFile').val());
				impFileActionFormData.push('taskId',$('#taskIdExpTBTemplate').val());
				impFileActionFormData.push('divCode',$('#divCodeExpTBTemplate').val())
				alert('目前IE8、9下不支持导入功能,请使用更高版本的IE或者其他浏览器!');//not ok
			}else{
				impFileActionFormData=new FormData($('#impFileAction')[0]);
				impFileActionFormData.append('taskId',$('#taskIdExpTBTemplate').val());
				impFileActionFormData.append('divCode',$('#divCodeExpTBTemplate').val());
			}
			*/
			

			$("#impFileAction").ajaxSubmit({
				dataType:'json',
				success:function(json){
					$.ajax({
						url:'${ctx}/report/getSingleReportProgressInfos',
						type:'GET',
						success:function(data)
						{
							$.ajax({
								url:'${ctx}/report/getProgressInfos',
								type:'GET',
								success:function(data)
								{
									if(data.impDatasProgress.valiSuccess==true){
										alert(data.impDatasProgress.valiResult+'\n导入成功!');
									}else{
										alert(data.impDatasProgress.valiResult+'\n导入失败!');
									}
									$('#impFile').val('');
									$('#ImpData').attr('disabled',false);
									$('#inputFileButtonId').attr('disabled',false);
									
									if(data.impDatasProgress.valiSuccess==true){
										location.reload();
									}
								},error:function()
								{
									alert('导入失败!');
									$('#impFile').val('');
									$('#ImpData').attr('disabled',false);
									$('#inputFileButtonId').attr('disabled',false);
								}
							});
						},error:function()
						{
							alert('导入失败!');
							$('#impFile').val('');
							$('#ImpData').attr('disabled',false);
							$('#inputFileButtonId').attr('disabled',false);
						}
					});
				},
				error:function(msg){
					alert(msg);
				}
			});
		}else{
			alert('不支持非Excel格式的文件导入,请导入由系统导出的填报模板!');
		}
	}
}


function showRow(id){
	var divCode = $("#divCode").val();
	$.ajax({
  		type : 'GET',
  	    dataType : 'JSON',
  		url : '${ctx}/office/task/viewInputReport/'+id+"/"+divCode,
  		success : function(data) {
  			//var url = "${ctx_bi}"+data.reportUrl;
  			var url = "${ctx}"+data.reportUrl;
  			window.open(url);
        }
	});
}

function doUpload(id) {
	
	$.ajax({
  		type : 'GET',
  	    dataType : 'JSON',
  		url : '${ctx}/office/task/upTaskReport?id='+id+"&date="+new Date().getTime(),
  		success : function(data) {
  			if(data.success==true){
  				bootbox.dialog({
					message: data.message,
					buttons: 			
					{
						"success" :
						 {
							"label" : "<i class='ace-icon fa fa-check'></i>确定",
							"className" : "btn-sm btn-success",
							"callback": function() {
								$("#grid-table").trigger('reloadGrid');
							}
						}
					}
				});
  			}
        }
	});
}

function upRow(id){
	bootbox.confirm("确定上报吗？",function(result) {
		if(result) {	
			$.ajax({
		  		type : 'GET',
		  	    dataType : 'JSON',
		  		url : '${ctx}/office/task/existsUnUploadTask?id='+id+"&date="+new Date().getTime(),
		  		success : function(data) {
		  			
		  			if(data.success==false){
		  				bootbox.alert(data.message);
		  				$("#grid-table").trigger('reloadGrid');
		  			} else {
		  				doUpload(id);
		  			}
		  			
		        }
			});
		}
	});
}

</script>