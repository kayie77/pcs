<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<script>
	function gaback()
	{
		window.location.href = "${ctx}/index#page/collect/taskMain/historyManager?isCheck=${param.isCheck}";
	}
	function gaCheckBack() {

		var size = $("#takMainTable").find("tbody > tr > td > input[type=checkbox]:checked ").size();
		if(size == 0) {
			alert('请选择一行记录！');
			return false;
		}
		
		if(!confirm("确认驳回吗？")) {
			return false;
		}
		
		var ids = "";
		$("#takMainTable").find("tbody > tr > td > input[type=checkbox]:checked ").each(function(i,ob) {
			ids += ob.value + ",";
		});
		
		$.ajax({
			url:"${ctx}/collect/dataReoprtedMain/goCheckBack",
			data:{'ids':ids},
			type:"get",
			dataType:"json",
			success:function(data){
				alert(data.message);
				if(data.status) {
					reloadTakMainTable();
				}
			}
		});
	}
	function gacheck() {
		
		var size = $("#takMainTable").find("tbody > tr > td > input[type=checkbox]:checked ").size();
		if(size == 0) {
			alert('请选择一行记录！');
			return false;
		}
		
		if(!confirm("确认审核吗？")) {
			return false;
		}
		
		var ids = "";
		$("#takMainTable").find("tbody > tr > td > input[type=checkbox]:checked ").each(function(i,ob) {
			ids += ob.value + ",";
		});
		
		$.ajax({
			url:"${ctx}/collect/dataReoprtedMain/check",
			data:{'ids':ids},
			type:"get",
			dataType:"json",
			success:function(data){
				alert(data.message);
				if(data.status) {
					reloadTakMainTable();
				}
			}
		});

	}
	function checkall(ob) {
		$("#takMainTable").find("tbody > tr > td > input[type=checkbox]").each(function(i,checkboxItem) {
			checkboxItem.checked = ob.checked;
		});
	}
</script>

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

<div class="container-fluid">
	<div class="row">
      <div class="widget-box">
        <div class="widget-header widget-header-flat">
          <h4 class="widget-title">
          <c:if test="${param.isCheck == '1'}">
          	任务审核明细
          	</c:if>
          	<c:if test="${param.isCheck != '1'}">
          	历史明细查询
          	</c:if>
          </h4>

          
        </div>

        <div class="widget-body">
          <div class="widget-main">
			      <div class="" style="margin-bottom:10px;">
  		        <form class="form-inline" id="searchForm" action="${ctx}/collect/queryHistoryDetail" >
  		          
  		          <div class="form-group">
  		            <label for="code">期数：</label>
  		            <input type="hidden" name="taskMainId" value="${taskMainId}"/>
  		            <select id="reriod" name="reriod">
  		            	<option value="">-=未选择=-</option>
  		            	<c:forEach var="reriodItem" items="${reriodList}">
  		            		<option value="${reriodItem}">${reriodItem}</option>
  		            	</c:forEach>
  		            </select>
  		            
  		            <label for="code">状态：</label>
  		            <select id="state" name="state">
  		            	<option value="">-=未选择=-</option>
  		            	<option value="0">未上报</option>
  		            	<option value="1">已上报</option>
  		            	<option value="2">已审核</option>
  		            	<option value="3">已驳回</option>
  		            </select>
  		          </div>
  		          <div class="btn-group">
  		             <button  type="submit" class="btn btn-info btn-white btn-round">查询</button>
  		             <c:if test="${param.isCheck == '1'}">
  		             	<button  type="button" class="btn btn-info btn-white btn-round" onclick="gacheck()">审核</button>
  		             	<button  type="button" class="btn btn-info btn-white btn-round" onclick="gaCheckBack()">驳回</button>
		          	 </c:if>
  		             <button  type="button" class="btn btn-info btn-white btn-round" onclick="gaback()">返回</button>
  		          </div>
  		        </form>
		        </div>
          <!-- 
    		           <th class="text-center" style="width: 80px;">编辑</th>
    		           <th class="text-center" style="width: 80px;">查看</th>
    		           <th class="text-center" style="width: 80px;">人员分配</th>
    		           <th class="text-left" style="width: 70px;"><input type="checkbox" id="row_all_selector">序号</th>
    		           <th class="text-center" style="width: 100px;">创建时间</th>
    		           <th class="text-center" style="width: 100px;">开始日期</th>
    		           <th class="text-center" style="width: 100px;">结束日期</th>
    		           <th class="text-center" style="width: 100px;">执行类型</th>
           -->
    		    <!-- table -->
    		    <div class="fix-table-container">
    		      <table class="table table-striped table-bordered table-hover js-table fix-table " id="takMainTable">
    		        <thead>
    		          <tr>
    		           <th class="text-center" style="width: 20px;"><input type="checkbox" onclick="checkall(this)"/></th>
    		           <th class="text-center" style="width: 300px;">采集人</th>
    		           <th class="text-center" style="width: 100px;">任务期数</th>
    		           <th class="text-center" style="width: 80px;" >状态</th>
    		           <th class="text-center" style="width: 80px;">查看</th>
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

<div class="loadDiv" id="dcpContainer"></div>
  
   <%--  <script src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
    <script src="${ctx}/assets/bootstrap/bootstrap.min.js"></script>
    <script src="${ctx}/assets/jquery/plugins/validation/jquery.validate.min.js"></script>
    <script src="${ctx}/assets/jquery/plugins/validation/localization/messages_zh.min.js"></script>
    <script src="${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js"></script> 
    <script src="${ctx}/assets/bootstrap/plugins/date-time/bootstrap-datepicker.min.js"></script>
    <script src="${ctx}/assets/bootstrap/plugins/date-time/locales/bootstrap-datepicker.zh-CN.js"></script>
    <script src="${ctx}/assets/jquery/plugins/lixi_common/utils.js"></script>
    <script src="${ctx}/assets/jquery/plugins/lixi_common/ajax_render.js"></script>
    <script src="${ctx}/assets/jquery/plugins/lixi_common/js-table.js"></script>
    <script src="${ctx}/assets/jquery/plugins/lixi_common/jquery.serialize-object.min.js"></script>
    <script src="${ctx}/assets/jquery/plugins/toast/jquery.toaster.js"></script>
    <script src="${ctx}/assets/jquery/plugins/jquery-form/jquery.form.js"></script>
    <script src="${ctx}/assets/jquery/plugins/jstree/jstree.min.js"></script> --%>

    
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
          	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
          		jQuery(function($) {
          			$('#takMainTable').jstable({pager:true,template:genRowTemplate()});
          	        reloadTakMainTable();

          	        //search form init
          	        $("#searchForm").ajaxForm({
          	          beforeSubmit:function(arr, $form, options) { 
          	            var searchUrl = $form.attr('action')+ '?' +$form.serialize();
          	            $('#takMainTable').jstable('set_params',{url:searchUrl});
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

          	        //action bar new
          	        $('button[name="newTakMain"]').click(function (){

          	            showModal('${ctx}/collect/taskMain/view/new',dataCollectPointFormInit);
          	        });

          	        //action bar del
          	        $('button[name="delTakMain"]').click(function (){
          	          var ids = getSelectedRowsId();
          	          if (!ids || ids.length <= 0) {
          	            bootbox.alert("请选择操作的目标!");
          	          }else{
          	            bootbox.confirm('确定要删除吗?',function(result){
          	              if(result) { 
          	                params = ids.map(function (index,id){
          	                  return 'id='+id;
          	                }).toArray().join('&');

          	                var url = '${ctx}/collect/taskMain?del&' + params;
          	                $.post(url, function (result){
          	                  if(result.status){
          	                    $.toaster({ title : '成功', priority : 'success', message : result.message });
          	                    reloadTakMainTable();
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

      function reloadTaskDetailPage(tmId){
        var url = '${ctx}/collect/taskDetail/manager?tmId='+ tmId;
          $(".loadDiv").load(url);
      }

      function reloadTakMainTable(){
        ajaxRenderTable('${ctx}/collect/queryHistoryDetail?taskMainId=${taskMainId}&reriod=',genRowTemplate());
        //清空takDetailTable表
       $('#taskDetailTable').data('tmId', null);
       $('#dcpContainer').html('');
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
      
      function dataCollectPointFormInit () {
        var form = $('#modalDialog').find('form').eq(0);;
        //validate init
        form.validate({ 
          rules : {
            name : {
              required : true,
              maxlength: 100,
            },
            code : {
              required : true
            },
            createdOn : {
              required : true
            },
            beginDate : {
              required : true
            },
            endDate : {
              required : true
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
                reloadTakMainTable();
              }else {
                $.toaster({ title : '失败', priority : 'warning', message : data.message });
              }
            });
          }
        });

        var createdDate = form.find('#createdOn');
        createdDate.datepicker({language:  'zh-CN', autoclose: true});
        if (createdDate.val() == '') {
          createdDate.datepicker('setDate', new Date());
        };

        var beginDate = form.find('#beginDate');
        beginDate.datepicker({language:  'zh-CN', autoclose: true});
        if (beginDate.val() == '') {
          beginDate.datepicker('setDate', new Date());
        };

        var endDate = form.find('#endDate');
        endDate.datepicker({language:  'zh-CN', autoclose: true});
        if (endDate.val() == '') {
          endDate.datepicker('setDate', new Date());
        };


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
        $('#takMainTable').on('click', 'a[name="edit"]', function () {
          var _this = $(this);
          var date_element = _this.parents('.data-row');
          var url = '${ctx}/collect/taskMain/view/edit?id=' +  date_element.attr('id');
          showModal(url, dataCollectPointFormInit);
        });

        $('#takMainTable').on('click', 'a[name="view"]', function () {
          var _this = $(this);
          var date_element = _this.parents('.data-row');
          var url = '${ctx}/collect/taskMain/view/detail?id=' + date_element.attr('id');
          showModal(url);
        });
        
         //js table action showDcp
        $('#takMainTable').on('click', 'td.row-select,a[name="detail"]', function () {
        	var _this = $(this);
            var date_element = _this.parents('.data-row');
            var url = '${ctx}/main#page/collect/dataReoprtedDetail/manager?dmId=' + date_element.attr('id')+"&date="+new Date().getTime();
            window.open(url);
        });
         
        $('#takMainTable').on('click', 'a[name="persons"]', function () {
            var _this = $(this);
            var date_element = _this.parents('.data-row');
            var url = '${ctx}/collect/taskMain/view/person?id=' + date_element.attr('id');
            showModal(url);
        });
          

        // row all selectos init
        $('#row_all_selector').click(function (){
          if($(this).prop('checked')){
            getRowSelectors().prop('checked',true);
          }else{
            getRowSelectors().prop('checked',false);
          }
        })

        /*
        <td title="编辑"style="text-align: center;cursor: pointer;" >\
         <a class="green" name="edit"><i class="ace-icon fa fa-pencil-square-o bigger-150"></i></a>\
         </td>\
                            
        <td style="text-align: center;cursor: pointer;" >\
          <div class="btn-group" role="group" aria-label="查看">\
          <a class="blue" name="view"><i class="ace-icon fa fa-eye bigger-150"></i></a>\
          </div>\

          <td style="text-align: center;cursor: pointer;" >\
          <div class="btn-group" role="group" aria-label="人员分配">\
          <a class="green" name="persons"><i class="ace-icon fa fa-cog bigger-150"></i></a>\
          </div>\
        </td>\
        
        <th><input type="checkbox" class="js-row_selector">{_index}</th>\
        <td title="{createdOn}">{createdOn}</td>\
        <td title="{beginDate}">{beginDate}</td>\
        <td title="{endDate}">{endDate}</td>\
        <td title="{executeType}" style="text-align: center;"> {executeType}</td>\
        */
        function genRowTemplate(node) {
          var tamplate = '<tr id="{id}" class="data-row">' +
          					'<td title="" align="center"><input type="checkbox" value="{id}"/></td>\
                            <td title="{name}">{name}</td>\
                            <td title="{reriod}">{reriod}</td>\
                            <td title="{reportedStatus}" style="text-align: center;"><span class="badge badge-primary">{reportedStatus}</span></td>\
                            <td style="text-align: center;cursor: pointer;" >\
                            <div class="btn-group" role="group" aria-label="明细">\
                            <a class="green" name="detail"><i class="ace-icon fa fa-eye bigger-150"></i></a>\
                            </div>\
                          </td>\
                          </tr>';
          return tamplate;
        }

        function ajaxRenderTable(url, template){
          $('#takMainTable').jstable('set_params',{ url: url,template:template});
        }

        function getRowSelectors(node){
          return $('tbody .js-row_selector');
        }

        function getSelectedRowsId(node){
          return $('tbody .js-row_selector:checked').parents('tr').map(function(){
            return $(this).prop('id');
          });
        }

    </script>
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jstree/themes/default/style.min.css" />
    <link rel="stylesheet" href="${ctx}/assets/bootstrap/plugins/date-time/css/datepicker.min.css" />
    <link href="${ctx}/assets/css/mgr-common.css" rel="stylesheet">