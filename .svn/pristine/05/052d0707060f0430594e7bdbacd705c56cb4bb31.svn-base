<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog"
	aria-labelledby="新增" data-backdrop="static">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content"></div>
	</div>
</div>
<div class="modal fade" id="pageDialog" tabindex="-1" role="dialog"
	aria-labelledby="关联" data-backdrop="static">
	<div class="modal-lg" style="min-width: 100%; min-height: 1138px;">
		<div class="modal-content"
			style="min-width: 100%; min-height: 1138px; border: 0px;">

			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true" id="close-dialog"></span>
			</button>
			<div className="modal-body"
				style="min-width: 100%; min-height: 1138px;">
				<iframe src="" id="modalPage" class="modalFrame" frameborder="0"
					style="width: 100%; min-height: 1138px;"></iframe>
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row">
		<!-- right -->
		<div class="col-xs-12" style="padding: 0px 2px 0px 5px;">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h5 class="panel-title">填报主表</h5>
				</div>
				<div class="panel-body">
					<div class="toolbar container-fluid">
						<ul class="nav nav-tabs" id="taskTypeList">
							<li data-type="day" class="active" ><a href="#">今日任务</a></li>
							<li data-type="week"><a href="#">本周任务</a></li>
						</ul>
					</div>

					<!-- table -->
					<div class="fix-table-container">
						<table
							class="table table-bordered table-hover js-table fix-table "
							id="takMainTable">
							<thead>
								<tr>
									<th class="text-left" style="width: 50px;">序号</th>
									<th class="text-center" style="width: 400px;">任务名称</th>
									<th class="text-center" style="width: 100px;">执行类型</th>
									<th class="text-center" style="width: 100px;">状态</th>
									<th class="text-center" >描述</th>
									<th class="text-center" style="width: 100px;">操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<div class="panel panel-primary">
				<div class="panel-heading">
					<h5 class="panel-title">填报细表</h5>
				</div>
				<div class="panel-body">
					<div class="toobar container-fluid">
						<div class="search-bar row">
							<div class="toolbar container-fluid"></div>
							<!-- add and edit modal -->
							<div class="modal fade" id="addOrEditDialog" tabindex="-1"
								role="dialog" aria-labelledby="信息" data-backdrop="static">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<!-- content -->
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="loadDiv" id="dcpContainer"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	var scripts = [
			null,
			"${ctx}/assets/jquery/ui/jquery-ui.custom.min.js",
			"${ctx}/assets/jquery/plugins/jquery-form/jquery-form.min.js",
			"${ctx}/assets/jquery/plugins/validation/jquery.validate.min.js",
			"${ctx}/assets/jquery/plugins/validation/localization/messages_zh.min.js",
			"${ctx}/assets/bootstrap/plugins/date-time/bootstrap-datepicker.min.js",
			"${ctx}/assets/bootstrap/plugins/date-time/locales/bootstrap-datepicker.zh-CN.js",
			"${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js",
			"${ctx}/assets/jquery/plugins/jqGrid/js/jquery.jqGrid.min.js",
			"${ctx}/assets/jquery/plugins/jqGrid/js/i18n/grid.locale-cn.js",
			"${ctx}/assets/jquery/plugins/lixi_common/ajax_render.js",
			"${ctx}/assets/jquery/plugins/lixi_common/jquery.serialize-object.min.js",
			"${ctx}/assets/jquery/plugins/toast/jquery.toaster.js",
			"${ctx}/assets/jquery/plugins/lixi_common/utils.js",
			"${ctx}/assets/jquery/plugins/jstree/jstree.min.js",
			"${ctx}/assets/jquery/plugins/lixi_common/js-table.js",
			"${ctx}/assets/js/agrims.js", null ]
	$('.page-content-area').ace_ajax(
			'loadScripts',
			scripts,
			function() {
				jQuery(function($) {
					$('#takMainTable').jstable({
						pager : true,
						template : genRowTemplate()
					});
					reloadTakMainTable();

					//search form init
					$("#searchForm").ajaxForm(
							{
								beforeSubmit : function(arr, $form, options) {
									var searchUrl = $form.attr('action') + '?'
											+ $form.serialize();
									$('#takMainTable').jstable('set_params', {
										url : searchUrl
									});
									return false;
								},
								dataType : 'json',
							});
					
					
					// 任务类型切换
					$('#taskTypeList li').click(function() {
							var type = $(this).data('type');
							 switch (type) {
							case 'day':
								ajaxRenderTable('${ctx}/collect/myTaskMain?executeType=1&state=1', genRowTemplate());
								//清空takDetailTable表
								$('#taskDetailTable').data('tmId', null);
								$('#dcpContainer').html('');
								break;
							case 'week':
								ajaxRenderTable('${ctx}/collect/myTaskMain?executeType=2&state=1', genRowTemplate());
								//清空takDetailTable表
								$('#taskDetailTable').data('tmId', null);
								$('#dcpContainer').html('');
								break;
							default:
								break;
							} 
							$(this).siblings().removeClass("active");
							$(this).addClass("active")
						});
					
					
					
					//init iframe
					var iframe = document.getElementById("modalPage");
					if (iframe.attachEvent) {
						iframe.attachEvent("onload", function() {
							$('#pageDialog').modal("show");
						});
					} else {
						iframe.onload = function() {
							$('#pageDialog').modal("show");
						};
					}
				});
			});
	$(document).ready(function() {

	});

	function reloadTaskDetailPage(dmId) {
		var url = '${ctx}/collect/dataReoprtedDetail/manager?dmId=' + dmId;
		$(".loadDiv").load(url);
	}

	// 加载表数据
	function reloadTakMainTable() {
		// 默认查询天的任务：executeType=1 
		ajaxRenderTable('${ctx}/collect/myTaskMain?executeType=1&state=1', genRowTemplate());
		//清空takDetailTable表
		$('#taskDetailTable').data('tmId', null);
		$('#dcpContainer').html('');
	}

	function showModal(url, callback) {
		$.get(url, function(data) {
			var modal = $('#modalDialog');
			var modal_content = modal.find('.modal-content');
			modal_content.html(data);
			if (callback) {
				callback();
			}
			;
			modal.modal('show');
		});
	}

	//common
	function ajaxFormSubmit(form, successCallback) {
		var cfg = {
			success : successCallback, // post-submit callback 
			url : $(form).attr("action"),
			type : $(form).attr("method") ? $(form).attr("method") : 'post', // 'get' or 'post', override for form's 'method' attribute 
			dataType : 'json', // 'xml', 'script', or 'json' (expected server response type) 
			contentType : 'application/json',
			data : JSON.stringify($(form).serializeObject()),
		};
		$.ajax(cfg);
	}

	$('#takMainTable').on('click', '.data-row', function() {
		var tm_set = $(this);
		tm_set.css("color", "#CC6600").siblings().css("color", "#000");
		reloadTaskDetailPage(tm_set.attr('id'));
	});

/* 	$('#takMainTable').on('click','button[name="receive"]',function() {
		var _this = $(this);
		var date_element = _this.parents('.data-row');
		var url = '${ctx}/collect/dataReoprtedDetail/receive?dmId='
				+ date_element.attr('id');
			$.get(url, function(result) {
				if (result.status) {
					$.toaster({
						title : '成功',
						priority : 'success',
						message : result.message
					});
					reloadTaskDetailPage(date_element.attr('id'));
				} else {
					$.toaster({
						title : '失败',
						priority : 'warning',
						message : result.message
					});
				}
			});
		}); */

	function genRowTemplate(node) {
		var tamplate = '<tr id="{id}" class="data-row">'
				+ '<th>{_index}</th>\
                            <td title="{taskName}">{taskName}</td>\
                            <td title="{executeType}">{executeType}</td>\
                            <td title="{reportedStatus}">{reportedStatus}</td>\
                            <td title="{description}">{description}</td>\
                            <td style="text-align: center;cursor: pointer;" >\
                            <div class="btn-group" role="group" aria-label="任务上报">\
                            <a class="green" name="detail"></a>\
                            <button  class="btn  btn-info btn-blue btn-sm "><i class="ace-icon fa fa-cog bigger-100"></i>上报</button>\
                            </div>\
                          </td>\
                          </tr>';
		return tamplate;
	}

	function ajaxRenderTable(url, template) {
		$('#takMainTable').jstable('set_params', {
			url : url,
			template : template
		});
	}
	
</script>
<link href="${ctx}/assets/bootstrap/css/bootstrap.min.css"rel="stylesheet">
<link rel="stylesheet"href="${ctx}/assets/jquery/plugins/jstree/themes/default/style.min.css" />
<link rel="stylesheet"href="${ctx}/assets/bootstrap/plugins/date-time/css/datepicker.min.css" />
<link href="${ctx}/assets/css/mgr-common.css" rel="stylesheet">