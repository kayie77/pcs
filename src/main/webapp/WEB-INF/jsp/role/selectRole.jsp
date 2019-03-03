<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="modal-dialog">
	<div class="modal-content">
      	<div class="modal-header no-padding">
			 <div class="table-header">
				  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						<span class="white">&times;</span>
				  </button>
				  <b>选择角色</b>
			 </div>
		</div>
      	<div class="modal-body no-padding">
			<table id="roleGrid-table"></table>
			<div id="roleGrid-pager"></div>
		</div>
		<div class="modal-footer no-margin-top">
        	<button id="btnDoAddRoles" type="button" class="btn btn-primary btn-sm" data-dismiss="modal" aria-hidden="true"> 
        		<i class="ace-icon fa fa-check-square"></i>        			
        		确定
       		</button>
        	<button type="button" class="btn btn-primary btn-sm" data-dismiss="modal" aria-hidden="true"> 
        		<i class="ace-icon fa fa-times"></i>        			
        		取消
       		</button>
      	</div>
	</div>
</div>
<script type="text/javascript">
  jQuery(function($) {
	var ctx='${ctx}';
	
	var grid_selector = "#roleGrid-table";
	var pager_selector = "#roleGrid-pager";
	
	$(window).on('resize.jqGrid', function () {
		$(grid_selector).jqGrid( 'setGridWidth', $(".modal-dialog").width() );
    });
	
	$(grid_selector).jqGrid({
		 url:'${ctx}/role/list',
		 datatype: 'JSON',
		 colNames:['ID','角色名称','角色中文名称'],
		 colModel:[
			   		{name:'id',index:'id', hidden:true,width:100, editable: false},
			   		{name:'roleName',index:'roleName',width:120, editable: false},
		   		    {name:'roleNameCN',index:'roleNameCN',width:120, editable: true}
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
		 rowNum:15,
		 pginput:false,
		 rowList:[10,15,20],
		 pager : pager_selector,
		 height:400,
		 autowidth: true,
		 altRows: true,
		 viewrecords: true,
		 multiselect: true,		 
		 loadComplete : function() {
			    var table = this;
				setTimeout(function(){
					updateActionIcons(table);
					updatePagerIcons(table);
					enableTooltips(table);
				}, 0);
		}
    });
		
	$(window).triggerHandler('resize.jqGrid');
	
	function aceSwitch( cellvalue, options, cell ) {
		setTimeout(function(){
			$(cell) .find('input[type=checkbox]')
				.addClass('ace ace-switch ace-switch-5')
				.after('<span class="lbl"></span>');
		}, 0);
	}
	
	//navButtons
	$(grid_selector).jqGrid('navGrid',pager_selector,
	  {
		edit: false,
		editicon : 'ace-icon fa fa-pencil blue',
		edittext:"编辑",
		add: false,
		addtext:"新增",
		addicon : 'ace-icon fa fa-plus-circle purple',
		del: false,
		deltext:"删除",
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
			multipleRole:true,
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
		form.find('.add-role').addClass('btn btn-xs btn-success');
		form.find('.delete-role').addClass('btn btn-xs btn-danger');
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
	
	$("#btnDoAddRoles").click(function(e){
		var roles = $(grid_selector).jqGrid('getGridParam','selarrrow');		 
		if(roles!=null && roles.length>0){
			 roleCallback(roles);
		} else {
    		bootbox.dialog({
				message: '请选择角色!',
				buttons: {
					"danger" :{
						"label" : "<i class='ace-icon fa fa-exclamation-triangle'></i>确定",
						"className" : "btn-sm btn-success",
						"callback": function() {
							
						}
					}
				}
   	 		});
		}
		e.preventDefault();
	});
	
	$(document).on('ajaxloadstart', function(e) {
		$(grid_selector).jqGrid('GridUnload');
		$('.ui-jqdialog').remove();
	});
});
</script>