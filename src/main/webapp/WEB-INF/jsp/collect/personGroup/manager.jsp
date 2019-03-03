<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

    <!-- add and edit modal -->
    <div class="modal fade" id="modalDialog" tabindex="-1" role="dialog" aria-labelledby="新增" data-backdrop="static">
      <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
          <!-- content -->
        </div>
      </div>
    </div>
    
    <div class="modal fade" id="pageDialog" tabindex="-1" role="dialog" aria-labelledby="关联" data-backdrop="static">
      <div class="modal-lg"  style="min-width: 100%;min-height: 1138px;">
        <div class="modal-content" style="min-width: 100%;min-height: 1138px;border: 0px;">
        
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" id="close-dialog"></span></button>
           <div className="modal-body" style="min-width: 100%;min-height: 1138px;">
            <iframe src="" id="modalPage" class="modalFrame" frameborder="0" style="width:100%; min-height:1138px;"></iframe>
           </div>
        </div>
      </div>
    </div>
    
<div class="hr hr-18 dotted hr-double"></div>
<div class="container-fluid">
  <div class="row">
    <div class="">
      <div class="widget-box">
        <div class="widget-header widget-header-flat">
          <h4 class="widget-title">分组详情</h4>

          <div class="widget-toolbar" style="line-height: 32px;">
            <label>
              <small>
                <button name="newPersonGrop" id="newPersonGrop" class="btn btn-white btn-success btn-sm btn-round" style="line-height: 20px;"><i class="ace-icon fa fa-plus bigger-100 green"></i>新建</button>
              </small>
            </label>
            <label>
              <small>
                <button name="delPersonGrop" id="delPersonGrop" class="btn btn-white btn-danger btn-sm btn-round" style="line-height: 20px;"><i class="ace-icon fa fa-trash-o bigger-120 red"></i>删除</button>
              </small>
            </label>
            
          </div>
        </div>

        <div class="widget-body">
          <div class="widget-main">
            <div class="fix-table-container">
              <table class="table table-striped table-bordered table-hover js-table fix-table " id="personGropTable">
                <thead>
                 <tr>
                   <th class="text-left" style="width: 60px;"><input type="checkbox" id="dcp_row_all_selector">序号</th>
                   <th class="text-center" style="width: 300px;">名字</th>
                   <th class="text-center" style="width: 150px;">编码</th>
                   <th class="text-center" style="width: 150px;">别名</th>
                   <th class="text-center" style="width: 150px;">备注</th>
                   <th class="text-center" style="width: 70px;">编辑</th>
                 </tr>
                </thead>
                <tbody>
                </tbody>
              </table>
            </div>
          </div>
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
         			"${ctx}/assets/js/agrims.js",
         			"${ctx}/assets/bootstrap-multiselect/js/bootstrap-multiselect.js" ,null ]
         	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
         		jQuery(function($) {
         			$('#personGropTable').jstable({pager:true,template:getDcpRowTemplate()});
         	        reloadPersonGropTable('${ctgId}');
         	        $('#personGropTable').data('ctgId','${ctgId}');

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

         	        //action bar new
         	        $('button[name="newPersonGrop"]').click(function (){
         	          if (!getCtgId()) {
         	            bootbox.alert("请先选择正确分类!");
         	          }else{
         	            showModal('${ctx}/collect/personGroup/view/new?ctgId=' + $('#personGropTable').data('ctgId'), taskDetailFormInit);
         	          }
         	        });

         	        //action bar del
         	        $('button[name="delPersonGrop"]').click(function (){
         	          var ids = getDcpSelectedRowsId();
         	          if (!ids || ids.length <= 0) {
         	            bootbox.alert("请选择操作的目标!");
         	          }else{
         	            bootbox.confirm('确定要删除吗?',function(result){
         	              if(result) { 
         	                params = ids.map(function (index,id){
         	                  return 'id='+id;
         	                }).toArray().join('&');

         	                var url = '${ctx}/collect/personGroup?del&' + params;
         	                $.post(url, function (result){
         	                  if(result.status){
         	                    $.toaster({ title : '成功', priority : 'success', message : result.message });
         	                    reloadPersonGropTable(getCtgId());
         	                  }else{
         	                    $.toaster({ title : '失败', priority : 'warning', message : result.message });
         	                  }
         	                }); 
         	              }
         	            });
         	          }
         	        });
         	        
         		});
         	});
      $(document).ready(function () {

        
      });

      function getCtgId(){
        return $('#personGropTable').data('ctgId');
      }

      function reloadPersonGropTable(ctgId){
        ajaxRenderDcpTable('${ctx}/collect/personGroup?ctgId=' + ctgId,
        		getDcpRowTemplate());
      }

      function showModal(url, callback){
        $.get(url, function (data){
          var modal = $('#modalDialog');
          var modal_content =  modal.find('.modal-content');
          modal_content.html(data);
          if (callback) {
            callback();
          };
          modal.modal('show');
        });
      }
  
      function taskDetailFormInit () {
        var form = $('#modalDialog').find('form').eq(0);;
        //validate init
        form.validate({ 
          rules : {
            name : {
              required : true,
              maxlength: 100,
            },
            code : {
              required : true,
              maxlength: 100,
            }
          },
          errorClass: 'text-warning',
          errorPlacement: function(error, element) {
            error.css('position','absolute').css('font-size','xx-small').insertAfter(element);
          },
          submitHandler: function (form) {
            ajaxFormSubmit(form, function(data, status) {
              if(data.data) {
                $.toaster({ title : '成功', priority : 'success', message : data.message });
                $(form).parents('.modal').modal('hide');
                reloadPersonGropTable(getCtgId());
              }else {
                $.toaster({ title : '失败', priority : 'warning', message : data.message });
              }
            });
          }
        });

      }

      //common
      function ajaxFormSubmit(form, successCallback) {
        var cfg = {
          success: successCallback, // post-submit callback 
          url: $(form).attr("action"),
          type: $(form).attr("method") ? $(form).attr("method") : 'post', // 'get' or 'post', override for form's 'method' attribute 
          dataType: 'json', // 'xml', 'script', or 'json' (expected server response type) 
          contentType: 'application/json',
          data: JSON.stringify($(form).serializeObject()),
        };
        $.ajax(cfg);
      }

      //js table
        //js table action view
        $('#personGropTable').on('click', 'a[name="edit"]', function () {
          var _this = $(this);
          var data_person = _this.parents('.data-row');
          var url = '${ctx}/collect/personGroup/view/edit?id=' +  data_person.attr('id');
          showModal(url, taskDetailFormInit);
        });

        // row all selectos init
        $('#dcp_row_all_selector').click(function (){
          if($(this).prop('checked')){
            getDcpRowSelectors().prop('checked',true);
          }else{
            getDcpRowSelectors().prop('checked',false);
          }
        })

        function getDcpRowTemplate(node) {
          var tamplate = '<tr id="{id}" class="data-row">' +
                           '<th><input type="checkbox" class="js_dcp_row_selector">{_index}</th>\
                            <td title="{personName}">{personName}</td>\
                            <td title="{personCode}">{personCode}</td>\
                            <td title="{personAlias}">{personAlias}</td>\
                            <td title="{personRemark}">{personRemark}</td>\
                            <td title="编辑"style="text-align: center;cursor: pointer;" >\
                            <a class="green" name="edit"><i class="ace-icon fa fa-pencil-square-o bigger-150"></i></a>\
                            </td>\
                          </td>\
                          </tr>';
          return tamplate;
        }

        function ajaxRenderDcpTable(url, template){
          $('#personGropTable').jstable('set_params',{
                                                        url: url,
                                                        template:template
                                                      });
        }

        function getDcpRowSelectors(node){
          return $('tbody .js_dcp_row_selector');
        }

        function getDcpSelectedRowsId(node){
          return $('tbody .js_dcp_row_selector:checked').parents('tr').map(function(){
            return $(this).prop('id');
          });
        }
    </script>
