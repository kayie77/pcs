<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/jquery/ui/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jqGrid/css/ui.jqgrid.css" />
<link rel="stylesheet" href="${ctx}/assets/bootstrap/plugins/date-time/css/datepicker.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jstree/themes/default/style.css" />
<div class="row">
	<div class="col-xs-12">
		<div class="btn-group">
        	<button data-toggle="dropdown" class="btn btn-info btn-blue btn-sm btn-round dropdown-toggle">
				<i class="ace-icon fa fa-plus-square"></i>
				新建
		    	<i class="ace-icon fa fa-caret-down icon-on-right"></i>
			</button>
			<ul class="dropdown-menu">
				<li>
					<a href="#" id="btnCreateFather">父资源</a>
				</li>
				<li>
					<a href="#" id="btnCreateLeaf">子资源</a>
				</li>
			</ul>
    	</div>
		<div class="btn-group">
			<button id="btnEditRes"  type="button" class="btn btn-info btn-blue btn-sm btn-round" data-toggle="modal">
		    	<i class="fa fa-pencil-square"></i>   
			 	编辑
			</button>
		</div>
    	<div class="btn-group">
    	   <button id="btnDeleteRes" class="btn btn-info btn-blue btn-sm btn-round" id="btnDelete">
				<i class="ace-icon glyphicon glyphicon-trash"></i>
				删除
		   </button>
		</div>
		<div class="space-6"></div>
	 </div>
	 <div class="col-xs-4"  style="padding:0 2px;">
		<div class="widget-box">
			<div class="widget-header widget-header-small">
				<h5 class="widget-title bolder">资源树</h5>
			</div>
			<div class="widget-body" style="height:520px;overflow:auto;">
				<div class="widget-main no-padding">
					<div id="resTree"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-xs-8" style="padding:0 2px;">
		<div class="widget-box">
			<div class="widget-header widget-header-small">
				<h5 class="widget-title bolder">资源角色</h5>
			</div>
			<div class="widget-body" style="height:520px;overflow:hidden;">
				<div class="widget-main no-padding">
					<table id="grid-table"></table>
					<div id="grid-pager"></div>
	            </div>
	        </div>				
		</div>
	</div>
</div>
<div id="resRoleModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
<div id="resModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>

<script type="text/javascript">
var scripts = [null,"${ctx}/assets/jquery/plugins/jstree/jstree.min.js","${ctx}/assets/jquery/plugins/jquery-form/jquery-form.min.js","${ctx}/assets/jquery/plugins/validation/jquery.validate.min.js","${ctx}/assets/jquery/plugins/validation/localization/messages_zh.min.js","${ctx}/assets/bootstrap/plugins/date-time/bootstrap-datepicker.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/jquery.jqGrid.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/i18n/grid.locale-cn.js","${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js","${ctx}/assets/js/agrims.js", null]
$('.page-content-area').ace_ajax('loadScripts', scripts, function() { 
jQuery(function($) {
	 var ctx='${ctx}';	
	 var grid_selector = "#grid-table";
	 var pager_selector = "#grid-pager";
	 var pageSize=${pageSize};
	 
	 $("#resTree").jstree({
		 'plugins' : ['state','dnd','contextmenu','wholerow'],
		 'core': {
	            'check_callback' : true,
	            'themes': {
	                'name': 'default',
	                'responsive': false
	            },
	            'data' : {
	                'url' : "${ctx}/resource/tree",
	                'data' : function (node) {
	                     return { 'id' : node.id };
	                }
	              }
	      },
	      "contextmenu":{         
    			"items": function($node) {
    			    var tree=$("#resTree").jstree(true);
       			    return {
       			    	"Edit": {
                			"separator_before": false,
                			"separator_after": false,
                			"label": "编辑",
                			"icon":"ace-icon fa fa-pencil",
                			"action": function (obj) { 
                				$("#btnEditRes").click();
                			}
            			},                         
            			"Remove": {
                			"separator_before": false,
                			"separator_after": false,
               	 			"label": "删除",
               	 			"icon":"ace-icon glyphicon glyphicon-trash",
                			"action": function (obj) { 
                    			tree.delete_node($node);
                			}
            			}
        			};
    			}
		   }
       }).bind("select_node.jstree", function(node,selected,event) {
    	   var id=selected.node.id; 
   	  		if( id != null ) {
   	  			$("#grid-table").jqGrid('setGridParam',{url:"${ctx}/role/listResRoles/"+id});
		  		$("#grid-table").trigger('reloadGrid',[{page:1}]);
		   	}
    	  	
	   }).bind('delete_node.jstree', function (e, data) {
			var parent= data.instance.get_parent(data.node);
			$.ajax({
	  			type : 'DELETE',
	  	    	dataType : 'JSON',
	  			url : '${ctx}/resource/delete/'+data.node.id,
	  			success : function(d) {   
                    data.instance.select_node(parent);
	  				bootbox.alert(d.message);
	        	}
    		});
	    }).delegate("a", "click", function(event, d) {
		  	event.preventDefault();
	   });
	   
	   $(window).on('resize.jqGrid', function () {
			//$("#grid-table").jqGrid( 'setGridWidth', $(".col-md-10").width() );
	   });
	   
	  var parent_column = $("#grid-table").closest('[class*="col-"]');
	  $(document).on('settings.ace.jqGrid' , function(ev, event_name, collapsed) {
			if( event_name === 'sidebar_collapsed' || event_name === 'main_container_fixed' ) {
				setTimeout(function() {
					$("#grid-table").jqGrid( 'setGridWidth', parent_column.width() );
				}, 0);
			}
	  }); 
	  
	  $("#grid-table").jqGrid({
			url:'${ctx}/role/listResRoles/0',
			datatype: 'JSON',
			colNames:['ID','角色名','角色中文名'],
		 	colModel:[
		   		{name:'id',index:'id', hidden:true,width:20, editable: false},
		   		{name:'roleName',index:'roleName',width:120, editable: false},
		   		{name:'roleNameCN',index:'roleNameCN',width:120, editable: false}
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
		 	height:423,
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
	  $("#grid-table").jqGrid('navGrid',pager_selector,
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
			view: true,
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
		
		 $("#grid-table").jqGrid('navButtonAdd',pager_selector,{
			    caption:"增加",
			    buttonicon:"ace-icon fa fa-plus-square red",
		        onClickButton:function(){
		        	 var resId = $('#resTree').jstree(true).get_selected(false);
			         if(resId){
			        	 $.get("${ctx}/role/select", function(data){ 
			        		 var modal = $('#resRoleModal');
								modal.html(data);
								modal.modal({show:true,backdrop:false}).on("hidden", function(){
						    		$(this).remove();
								});
					    });  
			         } else {
			        	 bootbox.alert('请在左边选择一个授权给角色资源!');	
			         }     							
		       } 
	     });
		 
		 $("#grid-table").jqGrid('navButtonAdd',pager_selector,{
			    caption:"删除",
			    buttonicon:"ace-icon fa fa-minus-square blue",
		        onClickButton:function(){
		        	 var ids = $("#grid-table").jqGrid('getGridParam','selarrrow');	
			         var resId = $('#resTree').jstree(true).get_selected(false);	
			         if(ids!=null && ids.length>0){
			        	 
			        	 bootbox.confirm("确定删除吗？",function(result) {
		        			if(result) {	

					        	var params={'resId':resId,'roles':ids};
						 		$.ajax({
						 			  	type : 'post',
						 			  	dataType : 'json',
						 			  	url : '${ctx}/resource/removeRoles',
						 			  	data :jQuery.param(params,true),
						 			  	success : function(data) {
						 			  		bootbox.alert(data.message);
						 			  		if(data.success==true){
						 			  			$("#grid-table").trigger('reloadGrid');	
						 			  		}
						 			    },
						 		 });
		        			}
		        		});	
				 		
				 		
			         } else {
			        	 bootbox.alert('请选择要删除的角色!');
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
			$("#grid-table").jqGrid('GridUnload');
			$('.ui-jqdialog').remove();
	  }); 
	  
	    $("#btnCreateFather").click(function(e){
	    	$.get("${ctx}/resource/new?"+Math.random(1000) , '', function(data){ 
	    		var modal = $('#resModal');
	    		modal.html(data);
	    		modal.modal({show:true,backdrop:false}).on("hidden", function(){
	    	    	$(this).remove();
	    		});
	            var form = modal.find('form:eq(0)');
	            form.validate({	
	    			rules : {
	    				resName : {
	    					required : true,
	    					minlength : 3,
	    					maxlength : 50
	    				},
	    				resCode : {
	    					minlength : 3,
	    					maxlength : 20
	    				},
	    				resType : {
	    					required : true
	    				},
	    				permission : {
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
	    			               $('#resTree').jstree(true).refresh();
	    			                modal.modal('hide');
	    			            } 
	    			        }  
	    				});
	    			},
	    		});
	        });
		    e.preventDefault();
	    });
	   
	    $("#btnCreateLeaf").click(function(e){
		  var ref = $('#resTree').jstree(false);
		  var pid = ref.get_selected();
	      if(!pid) { 
	         bootbox.alert('请选择父机构!');
	         return false;
	      }else{
	    	  $.get("${ctx}/resource/new?"+Math.random(1000) , "pid="+pid, function(data){ 
		    		var modal = $('#resModal');
		    		modal.html(data);
		    		modal.modal({show:true,backdrop:false}).on("hidden", function(){
		    	    	$(this).remove();
		    		});
		            var form = modal.find('form:eq(0)');
		            form.validate({	
		    			rules : {
		    				resName : {
		    					required : true,
		    					minlength : 3,
		    					maxlength : 50
		    				},
		    				resCode : {
		    					minlength : 3,
		    					maxlength : 20
		    				},
		    				resType : {
		    					required : true
		    				},
		    				permission : {
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
		    			            	$('#resTree').jstree(true).refresh();
		    			                modal.modal('hide');
		    			            } 
		    			        }  
		    				});
		    			},
		    		});
		        });
	      }
		  e.preventDefault();
	   });
	    
	    $("#btnEditRes").click(function(e){
		    var ref = $('#resTree').jstree(false);
			var id = ref.get_selected();
		    if(id) { 
	    		$.get("${ctx}/resource/edit/"+id+"?"+Math.random(1000) , '', function(data){ 
	    			var modal = $('#resModal');
	    			modal.html(data);
	    			modal.modal({show:true,backdrop:false}).on("hidden", function(){
	    	    		$(this).remove();
	    			});
	            	var form = modal.find('form:eq(0)');
	            	form.validate({	
		    			rules : {
		    				resName : {
		    					required : true,
		    					minlength : 3,
		    					maxlength : 50
		    				},
		    				resCode : {
		    					minlength : 3,
		    					maxlength : 20
		    				},
		    				resType : {
		    					required : true
		    				},
		    				permission : {
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
	    			            		$('#resTree').jstree(true).refresh();
	    			                	modal.modal('hide');
	    			            	} 
	    			        	}  
	    					});
	    				}
	    			});
	        	});
		    }else{
	    	    bootbox.alert('请选择要编辑资源!');
		        return false;
		    }
		    e.preventDefault();
	    });
	   
	   $("#btnDeleteRes").click(function(e){
		  var ref = $('#resTree').jstree(false);
		  var id = ref.get_selected();
	      if(id) {
	    	  
	    	  bootbox.confirm("确定删除吗？",function(result) {
    			if(result) {	

    		    	  $.ajax({
    			  			type : 'DELETE',
    			  	    	dataType : 'JSON',
    			  			url : '${ctx}/resource/delete/'+id,
    			  			success : function(data) {   
    			  				bootbox.alert(data.message);
    			  				$('#resTree').jstree(true).refresh();
    			        	}
    		    		});
    			}
    		});
	    	  
	      }else{
	    	  bootbox.alert('请选择要删除资源!');
		      return false;
	      }
		  e.preventDefault();
	  });
  });
  
  roleCallback = function(roles) {
		if (roles.length > 0) {
			 var resId = $('#resTree').jstree(true).get_selected(false);
			 var params={'resId':resId,'roles':roles};
			 $.ajax({
		  			type : 'POST',
		  			url : '${ctx}/resource/addRoles',
		  			data :jQuery.param(params,true),
		  			success : function(data) {
		  				bootbox.alert(data.message); 
		  				if (data.success == true) {
		  					$("#grid-table").trigger('reloadGrid');	
		  				}
		  			},
		  			dataType : 'json'
		  	});
		}
   }
});

</script>