<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/jquery/ui/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jqGrid/css/ui.jqgrid.css" />
<div class="row">
	<div class="col-xs-12">
		<div class="btn-group">
			<button id="createGroupBtn" type="button" class="btn btn-info btn-blue btn-sm btn-round" data-toggle="modal">
				<i class="fa fa-plus-square"></i>    
				新增
			</button>
			<button id="editGroupBtn"  type="button" class="btn btn-info btn-blue btn-sm btn-round" data-toggle="modal" style="display:none">
		    	<i class="fa fa-pencil-square"></i>   
			 	编辑
			</button>
			<button id="deleteGroupBtn" type="button" class="btn btn-default btn-info btn-sm btn-round">
				<i class="fa fa-trash-o"></i>   
				删除
			</button>
			<button data-toggle="dropdown" type="button" class="btn btn-info btn-blue btn-sm btn-round dropdown-toggle">
				<i class="fa fa-users"></i>   
				 成员
				<i class="ace-icon fa fa-caret-down icon-on-right"></i>
			</button>
			<ul class="dropdown-menu">
				<li>
					<a href="#" id="btnShowGroupUsers">群组用户</a>
				</li>
			</ul>
		</div>
		<div class="nav ace-nav btn-group">
			
		</div>
		<div class="space-6"></div>
		<table id="grid-table"></table>
		<div id="grid-pager"></div>
	</div>
</div>
<div id="groupModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>

<script type="text/javascript">
function tocheck(id,f) {
	$("#"+id).find("input[type=checkbox]")[0].checked = f;
}

function editRow(rowId) {
	setTimeout("tocheck('" +rowId+ "'," +$("#"+rowId).find("input[type=checkbox]")[0].checked+ ")",500);


	if(rowId){
		$.get("${ctx}/group/edit/"+rowId+"/?"+Math.random(1000) , '', function(data){
    		var modal = $('#groupModal');
    		modal.html(data);
    		modal.modal({show:true,backdrop:false}).on("hidden", function(){
    	    	$(this).remove();
    		});
            var form = modal.find('form:eq(0)');
            form.validate({	
    			rules : {
    				groupName : {
    					required : true,
    					minlength : 2,
    					maxlength : 20
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
    			                $(grid_selector).trigger('reloadGrid');	
    			                modal.modal('hide');
    			            } 
    			        }  
    				});
    			},
    		});
        });  		
	    //e.preventDefault();
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
	
	var g=$("a[href$='${ctx}/group']");
	g.closest("li").parent().parent().addClass('active open');
	g.closest("li").addClass('active');
	var f = g.next().get(0);
	
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	var pageSize=${pageSize};
	
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
	
	$(grid_selector).jqGrid({
		 url:'${ctx}/group/list',
		 datatype: 'JSON',
		 colNames:['ID','群组名','群组说明','启用','操作'],
		 colModel:[
		   		{name:'id',index:'id', hidden:true,width:20, editable: true},
		   		{name:'groupName',index:'groupName',width:150, editable: true,
		   			searchoptions:{  
                    	sopt:["cn"]
                	}
		   		},
		   		{name:'groupDesc',index:'groupDesc',width:300, sortable:true, editable: true,
		   			searchoptions:{  
                    	sopt:["cn"]
                	}
		   		},
		   		{name:'enabled',index:'enabled',width:60, editable: true,edittype:'checkbox',editoptions: {value:"true:false"},formatter:'checkbox',width:80,search:false},
		   		{name:'beginTime',index:'beginTime',width:40,editable: true,search:false,formatter:editFormatter}
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
	
	//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
	
	$(document).on('ajaxloadstart', function(e) {
		$(grid_selector).jqGrid('GridUnload');
		$('.ui-jqdialog').remove();
	});
	
	$(grid_selector).jqGrid('navButtonAdd',pager_selector,{
	    caption:"锁定",
	    buttonicon:"ace-icon fa fa-lock red",
        onClickButton:function(){
	         var rowId = $(grid_selector).jqGrid('getGridParam','selrow');
	         if(rowId){
	        	 //alert(rowId);
     		     $.ajax({
     		    	    type : 'GET',
     			  	    dataType : 'JSON',
     			  		url : '${ctx}/group/lock/'+rowId,
     			  		success : function(data) {
     			  			bootbox.alert(data.message);
     			  			if(data.success===true) {
    			                $(grid_selector).trigger('reloadGrid');	
    			                modal.modal('hide');
    			            } 
     			        },
     		    });
	         } else {
	        	 bootbox.alert('请选择一条记录!');
	         }     							
       } 
	});
 
 	$(grid_selector).jqGrid('navButtonAdd',pager_selector,{
	    caption:"解锁",
	    buttonicon:"ace-icon fa fa-unlock blue",
        onClickButton:function(){
	         var rowId = $(grid_selector).jqGrid('getGridParam','selrow');
	         if(rowId){
	        	 //alert(rowId);
     		     $.ajax({
     			  		type : 'GET',
     			  	    dataType : 'JSON',
     			  		url : '${ctx}/group/unLock/'+rowId,
     			  		success : function(data) {
     			  			bootbox.alert(data.message);
     			  			if(data.success===true) {
    			                $(grid_selector).trigger('reloadGrid');	
    			                modal.modal('hide');
    			            } 
     			        },
     		    });
	         } else {
	        	 bootbox.alert('请选择一条记录!');
	         }     							
       } 
	});
	
	$("#createGroupBtn").click(function(e){
	    $.get("${ctx}/group/new?"+Math.random(1000) , '', function(data){ 
	    		var modal = $('#groupModal');
	    		modal.html(data);
	    		modal.modal({show:true,backdrop:false}).on("hidden", function(){
	    	    	$(this).remove();
	    		});
	            var form = modal.find('form:eq(0)');
	            form.validate({	
	    			rules : {
	    				groupName : {
	    					required : true,
	    					minlength : 3,
	    					maxlength : 20
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
	    			                $(grid_selector).trigger('reloadGrid');	
	    			                modal.modal('hide');
	    			            } 
	    			        }  
	    				});
	    			},
	    		});
	        });  		
		   e.preventDefault();
	});
	
	$("#editGroupBtn").click(function(e){
		var rowId = $(grid_selector).jqGrid('getGridParam','selrow');
		editRow(rowId);
	 });
	
	$("#deleteGroupBtn").click(function(e){
		var selectedIds = $(grid_selector).jqGrid('getGridParam','selarrrow');
		var ids = [];
		if (selectedIds==null || selectedIds.length==0){
			bootbox.alert("请选择要删除记录!");
			return false;
		}else{
			
			bootbox.confirm("确定删除吗？",function(result) {
				if(result) {	
					for(var i=0;i<selectedIds.length;i++){
						 ids.push(selectedIds[i]);
					} 
					var params={"ids":ids};
					$.ajax({
				  		type : 'POST',
				  	    dataType : 'json',
				  		url : '${ctx}/group/delete',
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
			
		}		
		e.preventDefault();
	});
	
	$("#btnShowGroupUsers").click(function(e){
		var rowId = $(grid_selector).jqGrid('getGridParam','selrow');
	    if(rowId){
	    	window.location.href="${ctx}/index#page/user/groupUser/"+rowId; 
		} else {
			bootbox.alert('请选择一条记录!');
		}
		e.preventDefault();
	});

  });
});

$("#grid-table").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
</script>