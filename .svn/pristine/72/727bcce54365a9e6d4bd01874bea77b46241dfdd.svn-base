<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/jquery/ui/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jqGrid/css/ui.jqgrid.css" />
<style>
<!--
 .modal-dialog{width:600px;}
-->
</style>
<div class="row">
	<div class="col-xs-12">
		<div class="btn-group">
			<button id="btnAddUser" type="button" class="btn btn-info btn-blue btn-sm btn-round" data-toggle="modal">
				<i class="fa fa-plus-square"></i>    
				新增
			</button>
			<button id="btnRemoveUser" type="button" class="btn btn-info btn-blue btn-sm btn-round">
				<i class="fa fa-minus-square"></i>    
				删除
			</button>
			<button  id="btnGroup" type="button" class="btn btn-default btn-info btn-sm btn-round">
				<i class="fa fa-arrow-left"></i>  
				返回
			</button>
		</div>
		<div class="space-6"></div>
		<table id="grid-table"></table>
		<div id="grid-pager"></div>
	</div>
</div>
<div id="groupUserModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
<script type="text/javascript">
var scripts = [null,"${ctx}/assets/jquery/ui/jquery-ui.custom.min.js","${ctx}/assets/jquery/plugins/jstree/jstree.min.js","${ctx}/assets/jquery/plugins/jquery-form/jquery-form.min.js","${ctx}/assets/jquery/plugins/validation/jquery.validate.min.js","${ctx}/assets/jquery/plugins/validation/localization/messages_zh.min.js","${ctx}/assets/bootstrap/plugins/date-time/bootstrap-datepicker.min.js","${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/jquery.jqGrid.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/i18n/grid.locale-cn.js", null];
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
  jQuery(function($) {
	var ctx='${ctx}';
	
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	
	//resize to fit page size
	$(window).on('resize.jqGrid', function () {
		$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width() );
		$("#grid-table").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
    });
	$(grid_selector).jqGrid({
		 url:'${ctx}/user/listGroupUsers/${groupId}',
		 datatype: 'json',
		 colNames:['ID','登录名','用户姓名','用户说明'],
		 colModel:[
		   		{name:'id',index:'id', hidden:true,width:100, editable: false},
		   		{name:'username',index:'username', width:120,editable: false},
		   		{name:'persName',index:'persName',jsonmap:'person.persName',width:150,editable: false},	   		
		   		{name:'userDesc',index:'userDesc',width:150,editable: false}
		   		
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
	     caption: "群组成员-用户",
		 loadonce:false,
		 rownumbers: true,
		 rowNum:15,
		 rowList:[10,15,20],
		 pager : pager_selector,
		 height:'100%',
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
	
	$("#btnGroup").click(function(){
		window.location.href="${ctx}/index#page/group";
	});
	
	$("#btnAddUser").click(function(){
		$.get("${ctx}/user/select", function(data){ 
			var modal = $('#groupUserModal');
			modal.html(data);
			modal.modal({show:true,backdrop:false}).on("hidden", function(){
	    		$(this).remove();
			});
    	});   
	});
	
	
	$("#btnRemoveUser").click(function(){
		 var ids = $(grid_selector).jqGrid('getGridParam','selarrrow');		 
		 if(ids!=null && ids.length>0){
			 
			 bootbox.confirm("确定删除吗？",function(result) {
				if(result) {
					 var params={'id':'${groupId}','users':ids};
		 		     $.ajax({
		 			  		type : 'post',
		 			  	    dataType : 'json',
		 			  		url : '${ctx}/group/removeUsers',
		 			  		data :jQuery.param(params,true),
		 			  		success : function(data) {
		 			  			bootbox.alert(data.message);
		 			  			if(data.success==true){
		 			  				$(grid_selector).trigger('reloadGrid');	
		 			  			}
		 			        },
		 		    });
				}
			 });
         } else {
        	 bootbox.alert('请选择要删除的群组用户!');
         }     			
	});
	
	$(document).on('ajaxloadstart', function(e) {
		$(grid_selector).jqGrid('GridUnload');
		$('.ui-jqdialog').remove();
	});
	
  });
  
  userCallback = function(users) {
		 if (users.length > 0) {
			 var params={'id':'${groupId}','users':users};	
			 $.ajax({
		  				type : 'POST',
		  				url : '${ctx}/group/addUsers',
		  				data :jQuery.param(params,true),
		  				success : function(data) {
		  					bootbox.alert(data.message);
		  					if(data.success==true){
	 			  				$('#grid-table').trigger('reloadGrid');	
	 			  			}
		  				},
		  				dataType : 'json'
		  		});
		  }
  }
}); 
$("#grid-table").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
</script>