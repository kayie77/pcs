<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<div id="personModal" class="modal fade" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
</div>
	
<!-- add and edit modal -->
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
    <div class="fix-table-container">
			<table class="table table-striped table-bordered table-hover js-table fix-table " id="dataCollectPersonTable">
				<thead>
					<tr>
					<th class="text-center" style="width: 65px;"><input
						type="checkbox" id="dcp_row_all_selector">序号</th>
					<th class="text-center" style="width: 100px;">姓名</th>
					<th class="text-center" style="width: 100px;">编码</th>
					<th class="text-center" style="width: 100px;">别名</th>
					<th class="text-center" style="width: 100px;">性别</th>
					<th class="text-center" style="width: 100px;">电话</th>
					<th class="text-center" style="width: 100px;">地址</th>
					<th class="text-center" style="width: 100px;">备注</th>
					<th class="text-center" style="width: 70px;">编辑</th>
                    <th class="text-center" style="width: 70px;">查看</th>
					</tr>
				</thead>
			  <tbody>
			  </tbody>
			</table>
		</div>
  </div>
</div>


		<script>
			$(document)
					.ready(
							function() {

								$('#dataCollectPersonTable').jstable({
									pager : true,
									template : getDcpRowTemplate()
								});
								reloadDataCollectPersonTable('${dcpId}');
								$('#dataCollectPersonTable').data('dcpId',
										'${dcpId}');

								//search form init
								$("#dcpSearchForm")
										.ajaxForm(
												{
													beforeSubmit : function(
															arr, $form, options) {
														var ctg = getDcpId();
														if (!ctg || ctg == "") {
															return false
														}
														;

														arr.push({
															name : "dcpId",
															type : 'text',
															value : getDcpId()
														});
														var searchUrl = $form
																.attr('action')
																+ '?'
																+ $form
																		.serialize()
																+ '&dcpId='
																+ getDcpId();
														$(
																'#dataCollectPersonTable')
																.jstable(
																		'set_params',
																		{
																			url : searchUrl
																		});
														return false;
													},
													dataType : 'json',
												});
								//init iframe
								var iframe = document
										.getElementById("modalPage");
								if (iframe.attachEvent) {
									iframe.attachEvent("onload", function() {
										$('#pageDialog').modal("show");
									});
								} else {
									iframe.onload = function() {
										$('#pageDialog').modal("show");
									};
								}

								//action bar new
								$('button[name="newDataCollectPerson"]')
										.click(
												function() {
													showPersonModal('${ctx}/collect/person/select?dcpId='
															+ $(
																	'#dataCollectPersonTable')
																	.data(
																			'dcpId'));
													//addPerson($('#dataCollectPersonTable').data('dcpId'));

													/* if (!getDcpId()) {
													  bootbox.alert("请先选择正确分类!");
													}else{
													  showModal('${ctx}/collect/dataCollectPerson/view/new?dcpId=' + $('#dataCollectPersonTable').data('dcpId'), dataCollectPersonFormInit);
													} */
												});

								//action bar del
								$('button[name="delDataCollectPerson"]')
										.click(
												function() {
													var ids = getDcpSelectedRowsId();
													if (!ids || ids.length <= 0) {
														bootbox
																.alert("请选择操作的目标!");
													} else {
														bootbox
																.confirm(
																		'确定要删除吗?',
																		function(
																				result) {
																			if (result) {
																				params = ids
																						.map(
																								function(
																										index,
																										id) {
																									return 'id='
																											+ id;
																								})
																						.toArray()
																						.join(
																								'&');

																				var url = '${ctx}/collect/dataCollectPerson?del&'
																						+ params;
																				$
																						.post(
																								url,
																								function(
																										result) {
																									if (result.status) {
																										$
																												.toaster({
																													title : '成功',
																													priority : 'success',
																													message : result.message
																												});
																										reloadDataCollectPersonTable(getDcpId());
																									} else {
																										$
																												.toaster({
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

							});

			function addPerson(dcpId) {
				var params = {
					"dcpId" : dcpId
				};
				//showModal('${ctx}/collect/person/select?dcpId=' + $('#dataCollectPersonTable').data('dcpId'), dataCollectPersonFormInit);
				showModal('${ctx}/collect/person/select?dcpId='
						+ $('#dataCollectPersonTable').data('dcpId'),
						dataCollectPointFormInit);

				/* 
				$.get("${ctx}/collect/person/select",params, function(data){ 
						var modal = $('#orgPersonModal');
						modal.html(data);
						modal.modal({show:true,backdrop:false}).on("hidden", function(){
				    		$(this).remove();
				    		
						});
						 
				});  */

			}

			function getDcpId() {
				return $('#dataCollectPersonTable').data('dcpId');
			}

			function reloadDataCollectPersonTable(dcpId) {
				ajaxRenderDcpTable('${ctx}/collect/dataCollectPerson?dcpId='
						+ dcpId, getDcpRowTemplate());
			}

			function showPersonModal(url, callback) {
				$.get(url, function(data) {
					var modal = $('#personModal');
					modal.html(data);
					if (callback) {
						callback();
					}
					;
					modal.modal('show');
				});
			}
			
			function showModal(url, callback) {
				$.get(url, function(data) {
					var modal = $('#modalDialog');
					var modal_content = modal.find('.modal-content');
					modal_content.html(data);
					if (callback) {
						callback();
					};
					modal.modal('show');
				});
			}
			
			function dataCollectPersonFormInit() {
				var form = $('#modalDialog').find('form').eq(0);
				;
				//validate init
				form.validate({
					rules : {
						name : {
							required : true,
							maxlength : 100,
						},
						code : {
							required : true,
							maxlength : 100,
						},
						telephone : {
							number : true
						},
						alias:{
							required : true
						}
					},
					errorClass : 'text-warning',
					errorPlacement : function(error, element) {
						error.css('position', 'absolute').css('font-size',
								'xx-small').insertAfter(element);
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
								reloadDataCollectPersonTable(getDcpId());
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
					type : $(form).attr("method") ? $(form).attr("method")
							: 'post', // 'get' or 'post', override for form's 'method' attribute 
					dataType : 'json', // 'xml', 'script', or 'json' (expected server response type) 
					contentType : 'application/json',
					data : JSON.stringify($(form).serializeObject()),
				};
				$.ajax(cfg);
			}

			//js table
			//js table action view
			$('#dataCollectPersonTable')
					.on(
							'click',
							'a[name="edit"]',
							function() {
								var _this = $(this);
								var data_person = _this.parents('.data-row');
								var url = '${ctx}/collect/dataCollectPerson/view/edit?id='
										+ data_person.attr('id');
								showModal(url, dataCollectPersonFormInit);
							});

			$('#dataCollectPersonTable')
					.on(
							'click',
							'a[name="view"]',
							function() {
								var _this = $(this);
								var data_person = _this.parents('.data-row');
								var url = '${ctx}/collect/dataCollectPerson/view/detail?id='
										+ data_person.attr('id');
								showModal(url);
							});

			// row all selectos init
			$('#dcp_row_all_selector').click(function() {
				if ($(this).prop('checked')) {
					getDcpRowSelectors().prop('checked', true);
				} else {
					getDcpRowSelectors().prop('checked', false);
				}
			})

			function getDcpRowTemplate(node) {
				var tamplate = '<tr id="{id}" class="data-row">'
						+ '<th><input type="checkbox" class="js_dcp_row_selector">{_index}</th>\
                            <td title="{name}">{name}</td>\
                            <td title="{code}">{code}</td>\
                            <td>{sex}</td>\
                            <td>{alias}</td>\
                            <td>{telephone}</td>\
                            <td title="{adress}">{adress}</td>\
                            <td title="{remark}">{remark}</td>\
                            <td title="编辑"style="text-align: center;cursor: pointer;" >\
                            <a class="green" name="edit"><i class="ace-icon fa fa-pencil-square-o bigger-150"></i></a>\
                            </td>\
                            <td style="text-align: center;cursor: pointer;" >\
                            <div class="btn-group" role="group" aria-label="查看">\
                            <a class="blue" name="view"><i class="ace-icon fa fa-eye bigger-150"></i></a>\
                            </div></td>\
                          </tr>';
				return tamplate;
			}

			function ajaxRenderDcpTable(url, template) {
				$('#dataCollectPersonTable').jstable('set_params', {
					url : url,
					template : template
				});
			}

			function getDcpRowSelectors(node) {
				return $('tbody .js_dcp_row_selector');
			}

			function getDcpSelectedRowsId(node) {
				return $('tbody .js_dcp_row_selector:checked').parents('tr')
						.map(function() {
							return $(this).prop('id');
						});
			}
		</script>