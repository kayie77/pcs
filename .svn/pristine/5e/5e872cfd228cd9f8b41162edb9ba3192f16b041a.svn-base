<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/jquery/ui/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jqGrid/css/ui.jqgrid.css" />
<link rel="stylesheet" href="${ctx}/assets/bootstrap/plugins/date-time/css/datepicker.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jstree/themes/proton/style.css" />

<div class="row">
	<div class="col-xs-12" style="overflow:scroll;">
		<iframe id="customReportIframe" src="${ctx_bi}${customReport_path}" width="140%" height="650px" frameborder="no" allowtransparency="yes"> 
		</iframe>
	</div><!-- /.col -->
</div><!-- /.row -->

<script type="text/javascript">
var scripts = [null,"${ctx}/assets/jquery/ui/jquery-ui.min.js","${ctx}/assets/jquery/ui/jquery.ui.touch-punch.min.js","${ctx}/assets/js/agrims.js", null]
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
	jQuery(function($) {
		var g=$("a[href$='${ctx}/index#page/customeReport']");
		g.closest("li").parent().parent().addClass('active open');
		g.closest("li").addClass('active');
	});
});
</script>