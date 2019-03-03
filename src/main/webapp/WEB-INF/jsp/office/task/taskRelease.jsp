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
<link rel="stylesheet" href="${ctx}/assets/bootstrap/plugins/date-time/css/datepicker.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jstree/themes/default/style.css" />
<style>
<!--
 .modal-dialog{width:450px;}
-->
</style>
<input type="hidden" id="taskId" value="" />
<div class="row">
	<div class="col-xs-12">
		<div class="btn-group">
			<button id="checkDivBtn" type="button" class="btn btn-info btn-blue btn-sm btn-round" data-toggle="modal">
				<i class="glyphicon glyphicon-tint"></i>
				选择区域下发
			</button>
		</div>
		<div class="space-6"></div>
	</div>
    <div class="col-xs-4" style="padding:0 2px;">
		<div class="widget-box">
			<div class="widget-header widget-header-small">
				<h5 class="widget-title bolder">任务列表</h5>
			</div>
			<div class="widget-body" style="height:620px;overflow:auto;">
				<div class="widget-main padding-0">
					<div id="taskTree"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-xs-8" style="padding:0 2px;">
	  	<div class="widget-box">
		   	<div class="widget-header widget-header-small">
				<h5 class="widget-title bolder">任务区域列表</h5>
		    </div>
		    <div class="widget-body" style="height:620px;overflow:hidden;">
			   <div class="widget-main padding-0">
			      	<table id="grid-table"></table>
					<div id="grid-pager"></div>
	           </div>
	      	</div>				
		</div>
	</div>
</div><!-- /.row -->

<div id="divModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>

<script type="text/javascript">
function operateFormatter(cellvalue, options, rowObject) {
	
	return "<div style=\"float:left;margin-left:5px;cursor:pointer;\" class=\"ui-pg-div ui-inline-del\" id=\"jDeleteButton_2\" onclick=\"deleteRow('"+rowObject.id+"');\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\" data-original-title=\"删除所选记录\"><span class=\"ui-icon ui-icon-trash\"></span></div>";
}
function deleteRow(id) {
	bootbox.confirm("确定删除吗？",function(result) {
		if(result) {	
			var url = "${ctx}/office/task/deleteTaskDivAndItemById?id="+id;
			$.getJSON(url,function(data) {
				$("#grid-table").trigger('reloadGrid');
			});
		}
	});
}
var scripts = [null,"${ctx}/assets/jquery/plugins/jstree/jstree.min.js","${ctx}/assets/jquery/plugins/jquery-form/jquery-form.min.js","${ctx}/assets/jquery/plugins/validation/jquery.validate.min.js","${ctx}/assets/jquery/plugins/validation/localization/messages_zh.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/jquery.jqGrid.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/i18n/grid.locale-cn.js","${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js","${ctx}/assets/js/agrims.js", null]
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	jQuery(function($) {
		var ctx='${ctx}';
		var g=$("a[href$='${ctx}/index#page/office/task/release']");
		g.closest("li").parent().parent().addClass('active open');
		g.closest("li").addClass('active');
		var f = g.next().get(0);
		
		var grid_selector = "#grid-table";
		var pager_selector = "#grid-pager";
		var pageSize=${pageSize};
		 $("#taskTree").jstree({
			 'plugins': ["wholerow"],
			 'core': {
		            'themes': {
		                'name': 'default',
		                'responsive': false
		            },
		            'data' : {
		                'url' : "${ctx}/office/task/taskReleaseTree",
		                'data' : function (node) {
		                     return { 'id' : node.id };
		                }
		              }
		      }
	       }).bind("select_node.jstree", function(node,selected,event) {
    	  		var id=selected.node.id; 
    	  		if( id != null ) {
    	  			$("#taskId").val(id);
    		  		$(grid_selector).jqGrid('setGridParam',{url:"${ctx}/office/task/taskDiv/list/?taskId="+id});
    		  		$(grid_selector).trigger('reloadGrid');
		   		}
		   }).delegate("a", "click", function(event, data) {
			  	event.preventDefault();
		   });
		
		
		//resize to fit page size
		$(window).on('resize.jqGrid', function () {
			$(grid_selector).jqGrid( 'setGridWidth', $(".col-xs-8").width() );
	    });
		
		//resize on sidebar collapse/expand
		var parent_column = $(grid_selector).closest('[class*="col-"]');
		$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
			if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
				//setTimeout is for webkit only to give time for DOM changes and then redraw!!!
				setTimeout(function() {
					$(grid_selector).jqGrid( 'setGridWidth', $(".col-xs-8").width() );
				}, 0);
			}
	    });
	  	
		$(grid_selector).jqGrid({
			 url:'${ctx}/office/task/taskDiv/list',
			 datatype: 'JSON',
			 colNames:['ID','区域编号', '区域名称','操作'],
			 colModel:[
			   		{name:'id',index:'id', hidden:true,width:100, editable: true},
			   		{name:'divCode',index:'divCode',width:100,editable: true},
			   		{name:'divName',index:'divName',width:100,editable: true},
			   		{name:'x',index:'d',width:30,editable: true,formatter:operateFormatter,sortable:false}
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
	     	loadonce:false,
		 	rownumbers: true,
		 	rowNum:pageSize,
		 	rowList:[10,15,20],
		 	pager : pager_selector,
		 	height:520,
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
		
		$("#checkDivBtn").click(function(e){
			createRow();
			e.preventDefault();
		});
		   
		   
	});
});

//新建记录
function createRow(){
	var taskId = $('#taskId').val();
	if(taskId == ""){
		alert("请选择一个任务!");
	}else{
		$.get("${ctx}/office/task/checkDiv/"+taskId+"?"+Math.random(1000) , '', function(data){ 
			var modal = $('#divModal');
			modal.html(data);
			modal.modal({show:true,backdrop:false}).on("hidden", function(){
		    	$(this).remove();
			});
	        var form = modal.find('form:eq(0)');
	        form.validate({	
				rules : {},
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
	   					type:"POST",
	   					dataType:"json",
	   					success:function(data,status) {
	   						bootbox.alert(data.message);
	   			            if(data.success===true) {
	   			                $('#grid-table').trigger('reloadGrid');	
	   			                modal.modal('hide');
	   			            } 
	   			        }  
					});
				},
			});
	    });
	}
}
</script>