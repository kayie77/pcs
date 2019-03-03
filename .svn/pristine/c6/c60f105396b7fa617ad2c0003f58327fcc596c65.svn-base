<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/jquery/ui/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/bootstrap/plugins/date-time/css/datepicker.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jqGrid/css/ui.jqgrid.css" />
<div class="row">
	<div class="col-xs-12">
		<div class="btn-group">
			<button id="editParamBtn" type="button" class="btn btn-default btn-info btn-sm btn-round" data-toggle="modal" style="display:none">
				<i class="fa fa-pencil"></i>   
				编辑
			</button>
		</div>
		<div class="space" style="display:none"></div>
		<table id="grid-table"></table>
		<div id="grid-pager"></div>
	</div>
</div>
<div id="paramModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>

<script type="text/javascript">

function editRow(rowId) {

	if(rowId){
		$.get("${ctx}/parameter/edit/"+rowId+"/?"+Math.random(1000) , '', function(data){
    		var modal = $('#paramModal');
    		modal.html(data);
    		modal.modal({show:true,backdrop:false}).on("hidden", function(){
    	    	$(this).remove();
    		});
            var form = modal.find('form:eq(0)');
            form.validate({	
    			rules : {
    				paraVal : {
    					required : true
    				}
    			},
    			errorPlacement: function(error, element) {
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
    			                $("#grid-table").trigger('reloadGrid');	
    			                modal.modal('hide');
    			            } 
    			        }  
    				});
    			},
    		});
        });  		
	}else{
		bootbox.alert("请选择一条记录!");
	}
}

function editFormatter(cellvalue, options, rowObject) {
	return "<div style=\"float:left;cursor:pointer;\" class=\"ui-pg-div ui-inline-edit\" id=\"jEditButton_1\" onclick=\"editRow('"+rowObject.id+"');\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\" data-original-title=\"编辑所选记录\"><span class=\"ui-icon ui-icon-pencil\"></span></div>";
}

var scripts = [null,"${ctx}/assets/jquery/ui/jquery-ui.custom.min.js","${ctx}/assets/jquery/plugins/jquery-form/jquery-form.min.js","${ctx}/assets/jquery/plugins/validation/jquery.validate.min.js","${ctx}/assets/jquery/plugins/validation/localization/messages_zh.min.js","${ctx}/assets/bootstrap/plugins/date-time/bootstrap-datepicker.min.js","${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/jquery.jqGrid.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/i18n/grid.locale-cn.js","${ctx}/assets/js/agrims.js", null]
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
  jQuery(function($) {
	  var ctx='${ctx}';
		
		var g=$("a[href$='${ctx}/parameter']");
		g.closest("li").parent().parent().addClass('active open');
		g.closest("li").addClass('active');
		var f = g.next().get(0);
		
		var grid_selector = "#grid-table";
		var pager_selector = "#grid-pager";
		var pageSize=${pageSize};
		
		//resize to fit page size
		$(window).on('resize.jqGrid', function () {
			$("#grid-table").jqGrid( 'setGridWidth', $(".page-content").width() );
			$("#grid-table").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
	    });
		
		//resize on sidebar collapse/expand
		var parent_column = $("#grid-table").closest('[class*="col-"]');
		$(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
			if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
				//setTimeout is for webkit only to give time for DOM changes and then redraw!!!
				setTimeout(function() {
					$("#grid-table").jqGrid( 'setGridWidth', parent_column.width() );
					$("#grid-table").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
				}, 0);
			}
	    });
	   $("#grid-table").jqGrid({
		  url:'${ctx}/parameter/list',
		  datatype: 'JSON',
		  colNames:['ID','参数代码','参数名','参数值','参数说明','操作'],
		  colModel:[
		   	{name:'id',index:'id', hidden:true,width:20, editable: true},
			{name:'paraCode',index:'paraCode',width:100, editable: false,
				searchoptions:{  
                	sopt:["cn"]
            	}
		   	},
		   	{name:'paraName',index:'paraName',width:150, editable: false,
		   		searchoptions:{  
                	sopt:["cn"]
            	}
		   	},
		   	{name:'paraVal',index:'paraVal',width:300, editable: true,
		   		searchoptions:{  
                	sopt:["cn"]
            	}
		   	},
		   	{name:'paraDesc',index:'paraDesc',width:500, editable: true,
		   		searchoptions:{  
                	sopt:["cn"]
            	}
		   	},
	   		{name:'beginTime',index:'beginTime',width:60,editable: true,search:false,formatter:editFormatter}
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
		}
    });
		
	$(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
	
	$("#grid-table").jqGrid('navGrid',pager_selector,
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
		refresh: true,
		refreshicon : 'ace-icon fa fa-refresh green',
		refreshtext:"刷新",
		view: false,
		viewicon : 'ace-icon fa fa-search-plus grey',
		viewtext:"查看"
		},
		{},
		{},
		{},
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
	
	$(document).on('ajaxloadstart', function(e) {
		$("#grid-table").jqGrid('GridUnload');
		$('.ui-jqdialog').remove();
	});
	
	$("#editParamBtn").click(function(e){
		var rowId = $("#grid-table").jqGrid('getGridParam','selrow');
		editRow(rowId);
	 });

  });
});
$("#grid-table").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
</script>