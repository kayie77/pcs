<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="modal-dialog">
	<div class="modal-content">
      	<div class="modal-header no-padding">
			 <div class="table-header">
				  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						<span class="white">&times;</span>
				  </button>
				  <b>选择群组</b>
			 </div>
		</div>
      	<div class="modal-body no-padding">
			<table id="groupGrid-table"></table>
			<div id="groupGrid-pager"></div>
		</div>
		<div class="modal-footer no-margin-top">
        	<button id="btnDoAddGroups" type="button" class="btn btn-info btn-blue btn-sm btn-round" data-dismiss="modal" aria-hidden="true"> 
        		<i class="ace-icon fa fa-check-square"></i>        			
        		确定
       		</button>
        	<button type="button" class="btn btn-info btn-blue btn-sm btn-round" data-dismiss="modal" aria-hidden="true"> 
        		<i class="ace-icon fa fa-times"></i>        			
        		取消
       		</button>
      	</div>
	</div>
</div>
<script type="text/javascript">
  jQuery(function($) {
	var ctx='${ctx}';
	
	var grid_selector = "#groupGrid-table";
	var pager_selector = "#groupGrid-pager";
	
	$(window).on('resize.jqGrid', function () {
		$(grid_selector).jqGrid( 'setGridWidth', $(".modal-dialog").width() );
    });
	
	$(grid_selector).jqGrid({
		 url:'${ctx}/group/list',
		 datatype: 'JSON',
		 colNames:['ID','群组名称'],
		 colModel:[
			   		{name:'id',index:'id', hidden:true,width:100, editable: false},
			   		{name:'groupName',index:'groupName',width:150,editable: false}
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
	
	$("#btnDoAddGroups").click(function(e){
		var groups = $(grid_selector).jqGrid('getGridParam','selarrrow');		 
		if(groups!=null && groups.length>0){
			 groupCallback(groups);
		} else {
    		bootbox.alert('请选择一个或多个要赋予角色的群组!');
		}
		e.preventDefault();
	});
	
	$(document).on('ajaxloadstart', function(e) {
		$(grid_selector).jqGrid('GridUnload');
		$('.ui-jqdialog').remove();
	});
});
</script>