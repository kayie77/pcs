<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<style>
.def-cur {
	cursor: default;
}

#allmap {
	height: 300px;
	overflow: hidden;
	margin-top: 20px;
	font-family: "微软雅黑";
}

.anchorBL {
	display: none
}
</style>
<!-- add and edit modal -->
<div class="modal fade" id="modalDialog" tabindex="-1" role="dialog"
	aria-labelledby="新增" data-backdrop="static">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<!-- content -->
		</div>
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
		<!-- left -->
		<div class="col-xs-2" style="padding: 0 2px;">
			<div class="panel panel-primary" style="border-radius: 0px;">
				<div class="panel-heading">
					<h5 class="panel-title" style="font-size: 18px;">采集点分类</h5>
				</div>
				<div class="panel-body left-tree-nav">
					<div id="bdctg_tree" class=""></div>
				</div>
			</div>
		</div>
		<!-- right -->
		<div class="col-xs-10" style="padding: 0px 2px 0px 5px;">
			<div class="panel panel-primary" style="border-radius: 0px;">
				<div class="widget-header widget-header-flat">
					<h4 class="widget-title blue">采集点信息明细</h4>
					<div class="widget-toolbar" style="line-height: 32px;">
			      <label>
			      	<small><button name="newDataCollectPoint" class="btn btn-white btn-success btn-sm btn-round" style="line-height: 20px;"><i class="ace-icon fa fa-plus bigger-100 green"></i>新建</button></small>
			      </label>
			      <label>
			        <small><button name="delDataCollectPoint" class="btn btn-white btn-danger btn-sm btn-round" style="line-height: 20px;"><i class="ace-icon fa fa-trash-o bigger-120 red"></i>删除</button></small>
			      </label>
			      <label>
			        <small><button name="showDcpMap" class="btn btn-white btn-purple btn-sm btn-round" style="line-height: 20px;"><i class="ace-icon fa fa-globe bigger-120 pruple"></i>采集点分布地图</button></small>
			      </label>  
			    </div>
				</div>
				<div class="panel-body">
					<div class="toolbar container-fluid">
						<div class="search-bar row" style="">
							<form class="form-inline" id="searchForm"
								action="${ctx}/collect/dataCollectPoint" method="get">
								<div class="form-group">
									<label for="name">名称</label> <input type="text"
										class="form-control" name="search_LIKE_name">
								</div>
								<div class="form-group ">
									<div class="btn-group">
										<button  type="submit" class="btn btn-info btn-white btn-round">查询</button>
									</div>
								</div>
							</form>
						</div>
					</div>

					<!-- table -->
					<div class="fix-table-container">
						<table
							class="table table-striped table-bordered table-hover js-table fix-table "
							id="dataCollectPointTable">
							<thead>
								<tr>
									<th class="text-center" style="width: 60px;"><input
										type="checkbox" id="row_all_selector">序号</th>
									<th class="text-center" style="width: 150px;">名称</th>
									<th class="text-center" style="width: 100px;">所属地区</th>
 									<th class="text-center" style="width: 250px;">地址</th>
									<th class="text-center" style="width: 70px;">编辑</th>
				                    <th class="text-center" style="width: 70px;">查看</th>
				                    <th class="text-center" style="width: 70px;">采集人</th>
				                    <th class="text-center" style="width: 70px;">地理位置</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>

				</div>
			</div>

			<div class="hr hr-18 dotted hr-double"></div>
			<div class="panel panel-primary" style="border-radius: 0px;">
				<div class="widget-header widget-header-flat">
					<h4 class="widget-title">数据采集人员信息</h4>
					<div class="widget-toolbar" style="line-height: 32px;">
            <label>
              <small>
                <button name="newDataCollectPerson" class="btn btn-white btn-success btn-sm btn-round" style="line-height: 20px;"><i class="ace-icon fa fa-plus bigger-100 green"></i>添加</button>
              </small>
            </label>
            <label>
              <small>
                <button name="delDataCollectPerson" class="btn btn-white btn-danger btn-sm btn-round" style="line-height: 20px;"><i class="ace-icon fa fa-trash-o bigger-120 red"></i>删除</button>
              </small>
            </label>
          </div>
				</div>
				<div class="panel-body">
					<div class="toobar container-fluid">
						<div class="search-bar row">
							<div class="toolbar container-fluid"></div>
							<!-- add and edit modal -->
							<div class="modal fade" id="addOrEditDialog" tabindex="-1"
								role="dialog" aria-labelledby="采集人员信息" data-backdrop="static">
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

<!--   <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=vvZ5PYfUyh2gDgG6H4TppTsG"></script> -->
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
	$('.page-content-area').ace_ajax('loadScripts',scripts,
					function() {
						
						jQuery(function($) {
														
							$('#dataCollectPointTable').jstable({
								pager : true,
								template : genRowTemplate()
							});

							//search form init
							$("#searchForm").ajaxForm(
									{
										beforeSubmit : function(arr, $form,
												options) {
											var ctg = getCurCtg();
											if (!ctg || ctg == "") {
												return false
											}
											;

											arr.push({
												name : "ctgId",
												type : 'text',
												value : getCurCtg()
											});
											var searchUrl = $form
													.attr('action')
													+ '?'
													+ $form.serialize()
													+ '&ctgId=' + getCurCtg();
											$('#dataCollectPointTable')
													.jstable('set_params', {
														url : searchUrl
													});
											return false;
										},
										dataType : 'json',
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
							
							//init bdc jstree
							$('#bdctg_tree').jstree({
										'core' : {
										'check_callback' : true,
										'multiple' : false,
										'data' : {
												'url' : function(node) {
														return '${ctx}/collect/dataCollectPointTreeNode';
												 }
										  }
										}
									}).on('select_node.jstree',function(e, data) {
											var selectedNode = data.instance.get_node(data.selected[0]);
												$('#dataCollectPointTable').data('ctgId',selectedNode.id);
												reloadDataCollectPointTable(selectedNode.id);
									});

							//action bar new
							$('button[name="newDataCollectPoint"]').click(
								function() {
									if (!getCurCtg()) {
											bootbox.alert("请选择分类!");
									} else {
										    showModal('${ctx}/collect/dataCollectPoint/view/new?ctgId='+ $(
												'#dataCollectPointTable').data('ctgId'),dataCollectPointFormInit);
											}
									});

							//action bar del
							$('button[name="delDataCollectPoint"]')
									.click(
											function() {
												var ids = getSelectedRowsId();
												if (!ids || ids.length <= 0) {
													bootbox.alert("请选择操作的目标!");
												} else {
													bootbox.confirm('确定要删除吗?',
																	function(result) {
																		if (result) {
																			params = ids.map(function(index,id) {
																				return 'id='+ id;}).toArray().join('&');

																			var url = '${ctx}/collect/dataCollectPoint?del&'+ params;
																			$.post(url,function(result) {
																				if (result.status) {
																						$.toaster({
																							title : '成功',
																							priority : 'success',
																							message : result.message
																						});
																						reloadDataCollectPointTable(getCurCtg());
																						} else {
																							 $.toaster({
																									title : '失败',
																									priority : 'warning',
																									message : result.message
																						    });
																						}
																			});
																		}
																	});
												}
											});

							//action bar showMap
							$('button[name="showDcpMap"]').click(
								function() {
									if (!getCurCtg()) {
										bootbox.alert("请选择分类!");
									}else{
										var dpId = $('#dataCollectPointTable').data('ctgId');
										var url = '${ctx}/collect/dataCollectPoint/allPoint?dpId='+ dpId;
										 window.open(url); 
										/* showModal(url); */
									}
							});
						});
					});

	$(document).ready(function() {

	});

	function getCurCtg() {
		return $('#dataCollectPointTable').data('ctgId');
	}

	function getCtgJstree() {
		return $('#bdctg_tree').jstree(true);
	}

	function reloadDcpPage(dcpId) {
		var url = '${ctx}/collect/dataCollectPerson/manager?dcpId=' + dcpId;
		$(".loadDiv").load(url);
	}

	function reloadDataCollectPointTable(ctgId) {
		ajaxRenderTable('${ctx}/collect/dataCollectPoint?ctgId=' + ctgId,
				genRowTemplate());
		//清空dataCollectPerson表
		$('#dataCollectPersonTable').data('dcpId', null);
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

	function dataCollectPointFormInit() {
		var form = $('#modalDialog').find('form').eq(0);
		;
		//validate init
		form.validate({
			rules : {
				name : {
					required : true,
					maxlength : 100,
				},
				remark:{
					maxlength : 500
				}
			},
			errorClass : 'text-warning',
			errorPlacement : function(error, element) {
				error.css('position', 'absolute').css('font-size', 'xx-small')
						.insertAfter(element);
			},
			submitHandler : function(form) {
				ajaxFormSubmit(form, function(data, status) {
					if (data.data) {
						$.toaster({
							title : '成功',
							priority : 'success',
							message : data.message
						});
						$(form).parents('.modal').modal('hide');
						reloadDataCollectPointTable(getCurCtg());
					} else {
						$.toaster({
							title : '失败',
							priority : 'warning',
							message : data.message
						});
					}
				});
			}
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

	//js table
	//js table action view
	$('#dataCollectPointTable').on(
			'click',
			'a[name="edit"]',
			function() {
				var _this = $(this);
				var date_element = _this.parents('.data-row');
				var url = '${ctx}/collect/dataCollectPoint/view/edit?id='
					+ date_element.attr('id');
				showModal(url, dataCollectPointFormInit);
			});

	$('#dataCollectPointTable').on(
			'click',
			'a[name="view"]',
			function() {
				var _this = $(this);
				var date_element = _this.parents('.data-row');
				var url = '${ctx}/collect/dataCollectPoint/view/detail?id='
					+ date_element.attr('id');
				showModal(url);
			});
	
	$('#dataCollectPointTable').on('click','a[name="map"]',
		function() {
			var _this = $(this);
			var date_element = _this.parents('.data-row');
			var url = '${ctx}/collect/dataCollectPoint/singlePoint?id='
				+ date_element.attr('id');;
			window.open(url);
			/* showModal(url); */
	});
	
	 $('#dataCollectPointTable').on('click', 'td.row-select,a[name="detail"]', function () {
         var dcp_set = $(this).parents('tr');
         dcp_set.css("color","#F08080").siblings().css("color","#000"); 
         dcp_set.css("background","#FFE4C4").siblings().css("background","#fff");
         reloadDcpPage(dcp_set.attr('id'));
       });

	//js table action showDcp
	$('#dataCollectPointTable').on('click', 'a.row-select', function() {
		var dcp_set = $(this).parents('tr');
		dcp_set.css("color","#F08080").siblings().css("color","#000"); 
        dcp_set.css("background","#FFE4C4").siblings().css("background","#fff");
		reloadDcpPage(dcp_set.attr('id'));
	});

	// row all selectos init
	$('#row_all_selector').click(function() {
		if ($(this).prop('checked')) {
			getRowSelectors().prop('checked', true);
		} else {
			getRowSelectors().prop('checked', false);
		}
	})
	
/* 	                             */

	function genRowTemplate(node) {
		var tamplate = '<tr id="{id}" class="data-row">'
				+ '<th><input type="checkbox" class="js-row_selector">{_index}</th>\
                            <td title="{name}"><a class="row-select" href="javascript:void(0);">{name}</a></td>\
                            <td title="{divisionName}">{divisionName}</td>\
                            <td title="{adress}">{adress}</td>\
                            <td title="编辑"style="text-align: center;cursor: pointer;" >\
                            <a class="green" name="edit"><i class="ace-icon fa fa-pencil-square-o bigger-150"></i></a>\
                            </td>\
                            <td style="text-align: center;cursor: pointer;" >\
                            <div class="btn-group" role="group" aria-label="查看">\
                            <a class="blue" name="view"><i class="ace-icon fa fa-eye bigger-150"></i></a>\
                            </div></td>\
                            <td style="text-align: center;cursor: pointer;" >\
                            <div class="btn-group" role="group" aria-label="采集人">\
                            <a class="red2" name="detail"><i class="ace-icon fa fa-user bigger-140"></i></a>\
                            </div>\
                          </td>\
                          <td style="text-align: center;cursor: pointer;" >\
                          <div class="btn-group" role="group" aria-label="地理位置">\
                          <a class="purple" name="map"><i class="ace-icon fa fa-globe bigger-140"></i></a>\
                          </div>\
                        </td>\
                          </tr>';
		return tamplate;
	}

	function ajaxRenderTable(url, template) {
		$('#dataCollectPointTable').jstable('set_params', {
			url : url,
			template : template
		});
	}

	function getRowSelectors(node) {
		return $('tbody .js-row_selector');
	}

	function getSelectedRowsId(node) {
		return $('tbody .js-row_selector:checked').parents('tr').map(
				function() {
					return $(this).prop('id');
				});
	}

</script>
<link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jstree/themes/default/style.min.css" />
<link rel="stylesheet" href="${ctx}/assets/bootstrap/plugins/date-time/css/datepicker.min.css" />
<%@ include file="/WEB-INF/jsp/common/commonCss.jsp"%>
<link href="${ctx}/assets/css/mgr-common.css" rel="stylesheet">
