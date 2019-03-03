<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/jquery/ui/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jqGrid/css/ui.jqgrid.css" />
<link rel="stylesheet" href="${ctx}/assets/bootstrap/plugins/date-time/css/datepicker.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jstree/themes/proton/style.css" />
<div class="page-header">
	<h1>
		机构管理
	</h1>
</div><!-- /.page-header -->
<div class="row">
	<div class="col-md-12">
		<div class="btn-group">
        	<button data-toggle="dropdown" class="btn btn-primary btn-blue dropdown-toggle">
				<i class="ace-icon fa fa-plus-square"></i>
				新建
		    	<i class="ace-icon fa fa-caret-down icon-on-right"></i>
			</button>
			<ul class="dropdown-menu">
				<li>
					<a href="#" id="btnCreateFather">父机构</a>
				</li>
				<li>
					<a href="#" id="btnCreateLeaf">子机构</a>
				</li>
			</ul>
    	</div>
		<div class="btn-group">
    	   <button class="btn btn-primary btn-blue" id="btnDelete">
				<i class="ace-icon glyphicon glyphicon-trash"></i>
				删除
		   </button>
		</div>
	
		
		<div class="space-4"></div>
		<div class="row">
		    <div class="col-md-2">
				<div class="widget-box widget-color-blue2">
					<div class="widget-header">
						<h4 class="widget-title lighter smaller">机构菜单</h4>
					</div>
					<div class="widget-body" style="height:520px;overflow:auto;">
						<div class="widget-main padding-16">
							<div id="resTree"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-10">
			    <div class="widget-box widget-color-blue2">
					<div class="widget-header">
						<h4 class="widget-title lighter smaller">机构信息</h4>
					</div>
					<div class="widget-body" style="height:520px;overflow:hidden;">
					   <div class="widget-main padding-0">
							<div id="resContent"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div><!-- /.col -->
</div><!-- /.row -->


<script type="text/javascript">
var scripts = [null,"${ctx}/assets/jquery/plugins/jstree/jstree.min.js","${ctx}/assets/jquery/plugins/jquery-form/jquery-form.min.js","${ctx}/assets/jquery/plugins/validation/jquery.validate.min.js","${ctx}/assets/jquery/plugins/validation/localization/messages_zh.min.js","${ctx}/assets/bootstrap/plugins/date-time/bootstrap-datepicker.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/jquery.jqGrid.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/i18n/grid.locale-cn.js","${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js", null]
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
  jQuery(function($) {
		 var ctx='${ctx}';	
		 $("#resTree").jstree({
			 'plugins' : ['state','dnd','contextmenu','wholerow'],
			 'core': {
		            'check_callback' : true,
		            'themes': {
		                'name': 'proton',
		                'responsive': true
		            },
		            'data' : {
		                'url' : "${ctx}/org/tree",
		                'data' : function (node) {
		                     return { 'id' : node.id };
		                }
		              }
		      },
		      "contextmenu":{         
	    			"items": function($node) {
	    			    var tree=$("#resTree").jstree(true);
	       			    return {
	            			"Create": {
	                			"separator_before": false,
	                			"separator_after": false,
	                			"label": "新建",
	                			"icon":"ace-icon fa fa-plus-square",
	                			"action": function (obj) { 
	                    			$node = tree.create_node($node);
	                    			tree.edit($node,"新机构节点");
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
	    		  	editRow(id);
			   	}
	    	  	
		   }).bind('create_node.jstree', function (e, data) {
		        var url="${ctx}/org/new?"+Math.random(1000);
			    $.get(url, {"pid":data.node.parent,"resType":"menu"}, function(d){ 
	                 data.instance.set_id(data.node, d.id);
	                 data.instance.set_text(data.node, d.text);
	                 if(data.node.parent!='#'){
	                    var parent= data.instance.get_parent(data.node);
	                    data.instance.deselect_node(parent);
	                 }
	                 data.instance.select_node(data.node);
	                 data.instance.refresh_node(data.node);
	            },"json");		    
		   }).bind('delete_node.jstree', function (e, data) {
				var parent= data.instance.get_parent(data.node);
				$.ajax({
		  			type : 'DELETE',
		  	    	dataType : 'JSON',
		  			url : '${ctx}/org/delete/'+data.node.id,
		  			success : function(d) {   
	                    data.instance.select_node(parent);
		  				bootbox.dialog({
							message: d.message,
							buttons: 			
							{
								"success" :
							 	{
									"label" : "<i class='ace-icon fa fa-check'></i>确定",
									"className" : "btn-sm btn-success"
								}
							}
						});
		        	}
	    		});
		   }).delegate("a", "click", function(event, d) {
			  	event.preventDefault();
		   });
		   
		   $("#btnCreateFather").click(function(e){
		      var ref = $('#resTree').jstree(true);
		      var node = ref.create_node("#");
			  e.preventDefault();
		   });
		   
		    $("#btnCreateLeaf").click(function(e){
			  var ref = $('#resTree').jstree(true);
			  sel = ref.get_selected();
		      if(!sel.length) { 
		         bootbox.dialog({
		 			message: '请选择父机构!',
		 			buttons:{
		 				"success" :
		 				{
		 					"label" : "<i class='ace-icon fa fa-check'></i>确定",
		 					"className" : "btn-sm btn-success",
							"callback": function() {}
		 				}
		 			}
		 		});
		        return false;
		      }else{
		        ref.create_node(sel);
		      }
			  e.preventDefault();
		   });
		   
		    $("#btnDelete").click(function(e){
			  var ref = $('#resTree').jstree(true);
			  sel = ref.get_selected();
		      if(!sel.length) { 
		         bootbox.dialog({
		 			message: '请选择要删机构!',
		 			buttons:{
		 				"success" :
		 				{
		 					"label" : "<i class='ace-icon fa fa-check'></i>确定",
		 					"className" : "btn-sm btn-success",
							"callback": function() {}
		 				}
		 			}
		 		});
		        return false;
		      }else{
		        ref.delete_node(sel);
		      }
			  e.preventDefault();
		});
	});
});

function editRow(id){
	$.get("${ctx}/org/"+id+"/?"+Math.random(1000) , '', function(data){ 
		var content = $('#resContent');
		content.html(data);
        var form = content.find('form:eq(0)');
        form.validate({	
			rules : {
				orgCode : {
					required : true,
					minlength : 5,
					maxlength : 20
				},
				orgName : {
					required : true,
					minlength : 10,
					maxlength : 100
				}
			},
			//errorElement: "span",
			errorPlacement: function(error, element) {
				console.log(error);
			    error.insertAfter(element);
			},
			highlight: function(element) {
			    //$(element).closest('.form-group').removeClass('success').addClass('error');
			},
			submitHandler: function (form) {
				$(form).ajaxSubmit({
					//target: "#result",
					type:"POST",
					dataType:"json",
					success:function(data,status) {
			            console.log(data)
			            if(data.success===true) {
			            	bootbox.dialog({
	 							message: data.message,
	 							buttons: 			
	 							{
	 								"success" :
	 								 {
	 									"label" : "<i class='ace-icon fa fa-check'></i>确定",
	 									"className" : "btn-sm btn-success",
										"callback": function() {	
										}
	 								}
	 							}
	 						});
			            } else {
			            	 bootbox.dialog({
									message: data.message,
									buttons: 			
									{
										"danger" :
										 {
											"label" : "<i class='ace-icon fa fa-exclamation-triangle'></i>确定",
											"className" : "btn-sm btn-success",
											"callback": function() {	
											}
										}
									}
							});
			            }
			        }  
				});
			},
		});
    });    
}
</script>