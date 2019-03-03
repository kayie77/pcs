<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/jquery/ui/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jqGrid/css/ui.jqgrid.css" />
<style>
<!--
 .modal-dialog{width:500px;}
-->
</style>
<div class="row">
	<div class="col-xs-12">
		<div class="btn-group">
			<button id="createUserBtn" type="button" class="btn btn-default btn-info btn-sm btn-round" data-toggle="modal">
				<i class="fa fa-plus-square"></i>   
				新建
			</button>
			<button data-toggle="dropdown" class="btn btn-info btn-blue btn-sm btn-round dropdown-toggle">
				<i class="fa fa-pencil"></i> 
				编辑
		    	<i class="ace-icon fa fa-caret-down icon-on-right"></i>
			</button>
			<ul class="dropdown-menu">
				<li>
					<a href="#" id="editUserBtn">基本信息</a>
				</li>
				<li>
					<a href="#" id="editPersonBtn">详细信息</a>
				</li>
			</ul>
			<button id="deleteUserBtn" type="button" class="btn btn-info btn-blue btn-sm btn-round" data-toggle="modal">
				<i class="fa fa-trash-o"></i>   
				删除
			</button>
		</div>
		<div class="space-6 "></div>
		<table id="grid-table"></table>
		<div id="grid-pager"></div>
	</div>
</div>
<div id="userModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>

<script type="text/javascript">
var scripts = [null,"${ctx}/assets/jquery/ui/jquery-ui.custom.min.js","${ctx}/assets/jquery/plugins/jquery-form/jquery-form.min.js","${ctx}/assets/jquery/plugins/validation/jquery.validate.min.js","${ctx}/assets/jquery/plugins/validation/localization/messages_zh.min.js","${ctx}/assets/bootstrap/plugins/date-time/bootstrap-datepicker.min.js","${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/jquery.jqGrid.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/i18n/grid.locale-cn.js","${ctx}/assets/js/agrims.js", null]
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
  jQuery(function($) {
	var ctx='${ctx}';
	
	var g=$("a[href$='${ctx}/user']");
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
		 url:'${ctx}/user/list',
		 datatype: 'json',
		 colNames:['ID','人员ID','登录名', '姓名','手机号码','电子邮件','数字证书号码','启用'],
		 colModel:[
		   		{name:'id',index:'id', hidden:true,width:100, editable: true},
		   		{name:'persId',index:'persId', jsonmap:'person.id',hidden:true,width:100, editable: true},
		   		{name:'username',index:'username',width:150,editable: true,
		   			searchoptions:{  
                        sopt:["cn"]
                    }
		   		},
		   		{name:'person.persName',index:'person.persName',jsonmap:'person.persName',width:180,editable: true,
		   			searchoptions:{  
                        sopt:["cn"]
                    }
		   		},
		   		{name:'person.mobile',index:'person.mobile',jsonmap:'person.mobile', width:120,editable: true,
		   			searchoptions:{  
                        sopt:["cn"]
                    }
		   		},		
		   		{name:'person.email',index:'person.email',jsonmap:'person.email',width:150,editable: true,
		   			searchoptions:{  
                        sopt:["cn"]
                    }
		   		},
		   		{name:'caSn',index:'caSn',width:150,editable: true,
		   			searchoptions:{  
                        sopt:["cn"]
                    }
		   		},
		   		{name:'enabled',index:'enabled',edittype:'checkbox',editable: true,editoptions: {value:"true:false"},formatter:'checkbox',width:80,search:false}
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
		
	$(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
	
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
		}
	);
	
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
	     			  		url : '${ctx}/user/lock/'+rowId,
	     			  		success : function(data) {
	     			  			bootbox.alert(data.message);
	     			  			if(data.success==true){
	     			  				$(grid_selector).trigger('reloadGrid');	
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
	     			  		url : '${ctx}/user/unLock/'+rowId,
	     			  		success : function(data) {
	     			  			bootbox.alert(data.message);
	     			  			if(data.success==true){
	     			  				$(grid_selector).trigger('reloadGrid');	
	     			  			}
	     			        },
	     		    });
		         } else {
		        	 bootbox.alert('请选择一条记录!');
		         }     							
	       } 
    });
	
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
	
	
	$(document).on('ajaxloadstart', function(e) {
		$(grid_selector).jqGrid('GridUnload');
		$('.ui-jqdialog').remove();
	});
	
	$("#createUserBtn").click(function(e){
	    $.get("${ctx}/user/new?"+Math.random(1000) , '', function(data){ 
	    		var modal = $('#userModal');
	    		modal.html(data);
	    		modal.modal({show:true,backdrop:false}).on("hidden", function(){
	    	    	$(this).remove();
	    		});
	            var form = modal.find('form:eq(0)');
	            form.validate({	
	    			rules : {
	    				persName : {
	    					required : true,
	    					minlength : 3,
	    					maxlength : 20
	    				},
	    				email : {
	    					required : true,
	    					email: true
	    				},
	    				mobile : {
	    					required : true,
	    					minlength : 11,
	    					maxlength : 11
	    				},
	    				telNum : {
	    					required : true,
	    					minlength : 7,
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
	
	$("#editUserBtn").click(function(e){
		var rowId = $(grid_selector).jqGrid('getGridParam','selrow');
		if(rowId){
			$.get("${ctx}/user/edit/"+rowId+"/?"+Math.random(1000) , '', function(data){
	    		var modal = $('#userModal');
	    		modal.html(data);
	    		modal.modal({show:true,backdrop:false}).on("hidden", function(){
	    	    	$(this).remove();
	    		});
	            var form = modal.find('form:eq(0)');
	            var username=modal.find('input[name="username"]');
	            //alert(username);
	            form.validate({	
	    			rules : {
	    				persName : {
	    					required : true,
	    					minlength : 3,
	    					maxlength : 20
	    				},
	    				email : {
	    					required : true,
	    					email: true
	    				},
	    				mobile : {
	    					required : true,
	    					minlength : 11,
	    					maxlength : 11
	    				},
	    				telNum : {
	    					required : true,
	    					minlength : 7,
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
		}else{
			bootbox.alert("请选择一条记录!");
		}
	 });
	
	$("#editPersonBtn").click(function(e){
		var rowId = $(grid_selector).jqGrid('getGridParam','selrow');
		if(rowId){
			var rowData = $(grid_selector).jqGrid('getRowData',rowId); 
			$.get("${ctx}/person/edit/"+rowData.persId+"/?"+Math.random(1000) , '', function(data){
	    		var modal = $('#userModal');
	    		modal.html(data);
	    		modal.modal({show:true,backdrop:false}).on("hidden", function(){
	    	    	$(this).remove();
	    		});
	            var form = modal.find('form:eq(0)');
	            var username=modal.find('input[name="username"]');
	            //alert(username);
	            form.validate({	
	    			rules : {
	    				persName : {
	    					required : true,
	    					minlength : 2,
	    					maxlength : 20
	    				},
	    				email : {
	    					required : true,
	    					email: true
	    				},
	    				mobile : {
	    					required : true,
	    					minlength : 11,
	    					maxlength : 11
	    				},
	    				telNum : {
	    					required : true,
	    					minlength : 7,
	    					maxlength : 20
	    				},
	    				zip : {
	    					required : true,
	    					minlength : 6,
	    					maxlength : 6
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
		}else{
			bootbox.alert("请选择一条记录!");
		}
	 });
	
	$("#deleteUserBtn").click(function(e){
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
				  		url : '${ctx}/user/delete',
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
	
  });
});
$("#grid-table").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
</script>