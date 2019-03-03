<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/jquery/ui/css/jquery-ui.custom.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/gritter/css/jquery.gritter.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/select2/css/select2.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/chosen/css/chosen.css" />
<link rel="stylesheet" href="${ctx}/assets/bootstrap/plugins/editable/css/bootstrap-editable.css" />
<div class="page-header">
	<h1>个人设置</h1>
</div>
<div class="row">
  <div id="user-profile" class="user-profile">
	<div class="col-xs-12 col-sm-3 center">
	  <c:forEach var="item" items="${preferences}">	
		<div class="profile-user-info">
			<div class="profile-info-row">
				<div class="profile-info-name"> ${item.prefDesc}：  </div>
				<div class="profile-info-value">
					<span id="id_${item.id}">${item.prefVal}</span>
				</div>
			</div>			 
        </div>
      </c:forEach>
     </div>
</div>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
  <script src="${ctx}/assets/js/excanvas.min.js"></script>
<![endif]-->

<script type="text/javascript">
	var scripts = [null,"${ctx}/assets/jquery/ui/jquery-ui.custom.min.js","${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js","${ctx}/assets/jquery/ui/jquery.ui.touch-punch.min.js","${ctx}/assets/jquery/plugins/chosen/chosen.jquery.min.js","${ctx}/assets/jquery/plugins/gritter/jquery.gritter.min.js","${ctx}/assets/jquery/plugins/hotkeys/jquery.hotkeys.min.js","${ctx}/assets/jquery/plugins/select2/select2.min.js","${ctx}/assets/jquery/plugins/fuelux/fuelux.spinner.min.js","${ctx}/assets/bootstrap/plugins/editable/bootstrap-editable.min.js","${ctx}/assets/js/ace-editable.min.js", null]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	     //inline scripts related to this page
	    jQuery(function($) {
			//editables on first profile page
			$.fn.editable.defaults.mode = 'inline';
			$.fn.editableform.loading = "<div class='editableform-loading'><i class='ace-icon fa fa-spinner fa-spin fa-2x light-blue'></i></div>";
	    	$.fn.editableform.buttons = '<button type="submit" class="btn btn-info editable-submit"><i class="ace-icon fa fa-check"></i></button>'+
	                                '<button type="button" class="btn editable-cancel"><i class="ace-icon fa fa-times"></i></button>';    
		
			//editables 
			//text editable
	     <c:forEach var="item" items="${preferences}">		
			$('#id_${item.id}').editable({
				type: 'text',
				name: '${item.prefName}',
				pk:'${item.id}',
				url:'${ctx}/profile/updatePreference'
	    	});
		 </c:forEach>	
		});	
	});
</script>
