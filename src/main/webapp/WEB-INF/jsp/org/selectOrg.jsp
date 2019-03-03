<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/jquery/ui/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jstree/themes/default/style.css" />
<style>
<!--
 .modal-dialog{width:450px;}
-->
</style>
<div class="modal-dialog">
	<div class="modal-content">
      	<div class="modal-header no-padding">
			 <div class="table-header">
				  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						<span class="white">&times;</span>
				  </button>
				  <b>组织机构</b>
			 </div>
		</div>
      	<div class="modal-body" style="height:450px!important;overflow:auto;">
			<div id="orgTree"></div>
		</div>
		<div class="modal-footer no-margin-top">
        	<button id="btnDoAddOrgs" type="button" class="btn btn-primary btn-sm" data-dismiss="modal" aria-hidden="true"> 
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
	$("#orgTree").jstree({
		 'checkbox' : {
		        "three_state":false,
		        //"cascade":'up',
		        "whole_node":false,
                "keep_selected_style" : false,
                "undetermined" : false
    	  },
		 'plugins' : ['checkbox','wholerow'],
		 'core': {
	            'check_callback' : true,
	            'themes': {
	            	'name': 'default',
	                'responsive': true
	            },
	            'data' : {
	                'url' : "${ctx}/org/tree",
	                'data' : function (node) {
	                     return { 'id' : node.id };
	                }
	              }
	      }
       }).delegate("a", "click", function(event, d) {
		  	event.preventDefault();
	   });
	   
	   $("#btnDoAddOrgs").click(function(e){
			var selectNodes = $('#orgTree').jstree(true).get_checked(true);;
			if(selectNodes!=null && selectNodes.length>0){
				var orgs = [];
				for(i=0;i<selectNodes.length;i++){
					orgs.push(selectNodes[i].id);
				} 
				orgCallback(orgs);
			} else {
	    		bootbox.alert('请选择一个或多个要赋予角色的组织机构!');
			}
			e.preventDefault();
		});
  });
  </script>