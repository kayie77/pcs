<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/jquery/ui/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jqGrid/css/ui.jqgrid.css" />
<input type="hidden" id="divCode" value="${divCode }" />





		<div class="row">
           <div class="col-xs-12 col-sm-6 widget-container-col">
				<div class="widget-box widget-color-blue">
					<div class="widget-header widget-header-small">
						<h5 class="widget-title bolder">
							<i class="ace-icon fa fa-tasks"></i>
							
						</h5>
						<div class="widget-toolbar no-border">
							
					   	</div>
					 </div>
					 <div class="widget-body" style="min-height: 200px;">
					      <div class="widget-main no-padding">
					        <table class="table table-striped table-bordered table-hover">
                               <tbody>
                                  	<c:forEach var="listItem1" items="${list1}">
                                    	<tr>
                                      		<td onclick="showRow('${listItem1.id}')" style="font-size:14px;cursor:pointer;">${listItem1.analysisReportName}</td>
                                    	</tr>
					   				</c:forEach>
                                   </tbody>
                            </table>            
					      </div>
					</div>	
				 </div>
			</div>
			<div class="col-xs-12 col-sm-6 widget-container-col">
				<div class="widget-box widget-color-blue">
					<div class="widget-header widget-header-small">
						<h5 class="widget-title  bolder">
							<i class="ace-icon fa fa-tasks"></i>
							
						</h5>
						<div class="widget-toolbar no-border">
							
					   	</div>
					 </div>
					 <div class="widget-body" style="min-height: 200px;">
					     <div class="widget-main no-padding">
					       <table class="table table-striped table-bordered table-hover">
                               <tbody>
                                   <c:forEach var="listItem2" items="${list2}">
                                    	<tr>
                                      		<td onclick="showRow('${listItem2.id}')" style="font-size:14px;cursor:pointer;">${listItem2.analysisReportName}</td>
                                    	</tr>
					   				</c:forEach>
                               </tbody>
                           </table>            
					     </div>
					</div>	
				 </div>
			</div>
	   </div>





<div class="row" style="display:none">
	<div class="col-xs-12">
		<div class="btn-group" style="display:none">
			<button id="showAnalysisBtn" type="button" class="btn btn-info btn-blue btn-sm btn-round" data-toggle="modal">
				<i class="fa fa-eye"></i>   
				查看
			</button>
		</div>
		<div class="space-6"></div>
		<table id="grid-table"></table>
		<div id="grid-pager"></div>
	</div><!-- /.col -->
</div><!-- /.row -->


<form id="showreportform" name="showreportform" action="${ctx}/office/task/showreport" method="post" target="_blank">
	<input type="hidden" id="url" name="url"/>
</form>

<script>
function changeCursor() {
	$("#grid-table").find("tr").each(function(i,ob) {
		ob.style.cursor = "pointer";
	});
}
setInterval("changeCursor()",500);
</script>
<script type="text/javascript">
var scripts = [null,"${ctx}/assets/jquery/plugins/jquery-form/jquery-form.min.js","${ctx}/assets/jquery/plugins/validation/jquery.validate.min.js","${ctx}/assets/jquery/plugins/validation/localization/messages_zh.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/jquery.jqGrid.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/i18n/grid.locale-cn.js","${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js","${ctx}/assets/js/agrims.js", null]
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
  	jQuery(function($) {
		var ctx='${ctx}';
	
		//resize to fit page size
		$(window).on('resize.jqGrid', function () {
			$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width() );
			$("#grid-table").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
	    });
		
		//resize on sidebar collapse/expand
		var parent_column = $(grid_selector).closest('[class*="col-"]');
		$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
			if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
				//setTimeout is for webkit only to give time for DOM changes and then redraw!!!
				setTimeout(function() {
					$(grid_selector).jqGrid( 'setGridWidth', parent_column.width() );
					$("#grid-table").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
				}, 0);
			}
	    });
		
		
		var g=$("a[href$='${ctx}/index#page/office/task/analysis']");
		g.closest("li").parent().parent().addClass('active open');
		g.closest("li").addClass('active');
		var f = g.next().get(0);
		
		var grid_selector = "#grid-table";
		var pager_selector = "#grid-pager";
		var pageSize=${pageSize};
	  	
		$(grid_selector).jqGrid({
			 url:'${ctx}/office/task/analysislist',
			 datatype: 'JSON',
			 colNames:['ID','报表名称'],
			 colModel:[
			   		{name:'id',index:'id', hidden:true,width:100, editable: true},
			   		{name:'analysisReportName',index:'analysisReportName',width:150,editable: true}
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
			 height:'100%',
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
			},
			onSelectRow: function(id){ 
				showRow(id);
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
		
		$("#showAnalysisBtn").click(function(e){
			var rowId = $(grid_selector).jqGrid('getGridParam','selrow');
			if(rowId){
				showRow(rowId);
			}else{
				bootbox.alert("请选择一条记录!");
			}
			e.preventDefault();
		});
		
	});
});

function showRow(id){
	var divCode = $("#divCode").val();
	$.ajax({
  		type : 'GET',
  	    dataType : 'JSON',
  		url : '${ctx}/office/task/viewAnalysisReport/'+id+"/"+divCode,
  		success : function(data) {
  			$("#url").val("${ctx_bi}"+data.reportUrl);
  			$("#showreportform").submit();
  			
  			//var url = "${ctx_bi}"+data.reportUrl;
			//window.open(url,'fullscreen','channelmode,fullscreen,scrollbars');
        }
	});
}



$("#grid-table").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
</script>