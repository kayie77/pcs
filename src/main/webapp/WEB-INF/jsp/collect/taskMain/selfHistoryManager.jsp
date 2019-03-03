<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>我的任务</title>

<style type="text/css">
#task_content {
	margin-top: 10px;
}

.task_item {
	margin: 10px auto;
	border-bottom: 1px solid #ddd;
	height: 60px;
	/* background: #f5f5f5; */
}

.task_item1 {
	float: left;
	width: 380px;
	margin-left: 10px;
	height: 80px;
	background: #f5f5f5;
}

#fdw-pricing-table {
	margin: 0 auto;
	text-align: center;
	width: 100%; /* total computed width */
	zoom: 1;
}

#fdw-pricing-table:before, #fdw-pricing-table:after {
	content: "";
	display: table
}

#fdw-pricing-table:after {
	clear: both
}

/* --------------- */
#fdw-pricing-table .plan {
	font: 13px 'Lucida Sans', 'trebuchet MS', Arial, Helvetica;
	background: #fff;
	border: 1px solid #ddd;
	color: #333;
	padding-bottom: 10px;
	width: 400px;
	height: 380px;
	overflow-y: auto;
	float: left;
	_display: inline; /* IE6 double margin fix */
	position: relative;
	margin: 10px 6px;
	-moz-box-shadow: 0 2px 2px -1px rgba(0, 0, 0, .3);
	-webkit-box-shadow: 0 2px 2px -1px rgba(0, 0, 0, .3);
	box-shadow: 0 2px 2px -1px rgba(0, 0, 0, .3);
	float: left;
}

#fdw-pricing-table .plan:HOVER {
	border-color: #ccc;
}

#fdw-pricing-table .plan:after {
	z-index: -1;
	position: absolute;
	content: "";
	bottom: 10px;
	right: 4px;
	width: 80%;
	top: 80%;
	-webkit-box-shadow: 0 12px 5px rgba(0, 0, 0, .3);
	-moz-box-shadow: 0 12px 5px rgba(0, 0, 0, .3);
	box-shadow: 0 12px 5px rgba(0, 0, 0, .3);
	-webkit-transform: rotate(3deg);
	-moz-transform: rotate(3deg);
	-o-transform: rotate(3deg);
	-ms-transform: rotate(3deg);
	transform: rotate(3deg);
}

#fdw-pricing-table .popular-plan {
	top: -20px;
	padding: 40px 20px;
}

/* --------------- */
#fdw-pricing-table .header {
	height: 80px;
	position: relative;
	font-size: 14px;
	font-weight: bold;
	text-transform: uppercase;
	padding: 10px 5px;
	margin: auto 0;
	border-bottom: 8px solid;
	background-color: #eee;
	background-image: -moz-linear-gradient(#fff, #eee);
	background-image: -webkit-gradient(linear, left top, left bottom, from(#fff),
		to(#eee));
	background-image: -webkit-linear-gradient(#fff, #eee);
	background-image: -o-linear-gradient(#fff, #eee);
	background-image: -ms-linear-gradient(#fff, #eee);
	background-image: linear-gradient(#fff, #eee);
}

#fdw-pricing-table .header:after {
	position: absolute;
	bottom: -8px;
	left: 0;
	height: 3px;
	width: 100%;
	content: '';
}

#fdw-pricing-table .popular-plan .header {
	margin-top: -40px;
	padding-top: 60px;
}

#fdw-pricing-table .plan1 .header {
	border-bottom-color: #B3E03F;
}

#fdw-pricing-table .plan2 .header {
	border-bottom-color: #7BD553;
}

#fdw-pricing-table .plan3 .header {
	border-bottom-color: #3AD5A0;
}

#fdw-pricing-table .plan4 .header {
	border-bottom-color: #45D0DA;
}

/* --------------- */
#fdw-pricing-table .price {
	font-size: 45px;
}

#fdw-pricing-table .item {
	width: 100%;
	height: 40px;
	position: relative;
}

#fdw-pricing-table  .icon {
	height: 30px;
	width: 30px;
	border-radius: 30px;
	line-height: 30px;
	left: 10px;
	top: 4px;
	position: absolute;
	background: #2e6da4;
	color: #fff;
	font-size: 16px;
	text-align: center;
}

#fdw-pricing-table .item .left {
	height: 100%;
	width: 20%;
	float: left;
}

#fdw-pricing-table .item .right {
	height: 100%;
	width: 80%;
	text-align: left;
	float: left;
}

#fdw-pricing-table .monthly {
	font-size: 13px;
	margin-bottom: 20px;
	text-transform: uppercase;
	color: #999;
}

/* --------------- */
#fdw-pricing-table ul {
	margin: 20px 0;
	padding: 0;
	list-style: none;
}

#fdw-pricing-table li {
	height: 70px;
	padding: 10px 0;
	border-bottom: 1px dotted #ccc;
}

#fdw-pricing-table li:HOVER {
	background: #f9f9f9;
}

/* --------------- */
#fdw-pricing-table .signup {
	position: relative;
	padding: 10px 20px;
	color: #fff;
	font: bold 14px Arial, Helvetica;
	text-transform: uppercase;
	text-decoration: none;
	display: inline-block;
	background-color: #72ce3f;
	-moz-border-radius: 3px;
	-webkit-border-radius: 3px;
	border-radius: 3px;
	text-shadow: 0 -1px 0 rgba(0, 0, 0, .15);
	opacity: .9;
}

#fdw-pricing-table .signup:hover {
	opacity: 1;
}

#fdw-pricing-table .signup:active {
	-moz-box-shadow: 0 2px 2px rgba(0, 0, 0, .3) inset;
	-webkit-box-shadow: 0 2px 2px rgba(0, 0, 0, .3) inset;
	box-shadow: 0 2px 2px rgba(0, 0, 0, .3) inset;
}

#fdw-pricing-table .plan1 .signup {
	background: #B3E03F;
}

#fdw-pricing-table .plan2 .signup {
	background: #7BD553;
}

#fdw-pricing-table .plan3 .signup {
	background: #3AD5A0;
}

#fdw-pricing-table .plan4 .signup {
	background: #45D0DA;
}
</style>
</head>
<body>
	<div class="row" style="background: #fff;">
		<div class="left_content" style="width: 100%; float: left;">
			<!-- <div class="" id="search_content"
				style="height: 60px; border-bottom: 0px solid green;">

			</div> -->
			<div id="task_content">
				<header>
					<div id="fdw-pricing-table">
						
						
						<div class="container-fluid">
							<div class="row" style="padding:25px;text-align:left;">
						      <div class="widget-box">
						
						        <div class="widget-body">
						          <div class="widget-main">
									      <div class="" style="margin-bottom:10px;">
						  		        <form class="form-inline" id="searchForm" action="${ctx}/collect/querySelfHistory" method="get">
						  		          <div class="form-group">
						  		            <label for="name">名称</label>
						  		            <input type="text" class="form-control" name="search_LIKE_name">
						  		          </div>
						  		          <div class="btn-group">
						  		             <button  type="submit" class="btn btn-info btn-white btn-round">查询</button>
						  		          </div>
						  		        </form>
								        </div>
						          <!-- 
						    		           <th class="text-center" style="width: 80px;">编辑</th>
						    		           <th class="text-center" style="width: 80px;">查看</th>
						    		           <th class="text-center" style="width: 80px;">人员分配</th>
						           -->
						    		    <!-- table -->
						    		    <div class="fix-table-container">
						    		      <table class="table table-striped table-bordered table-hover js-table fix-table " id="takMainTable">
						    		        <thead>
						    		          <tr>
						    		           <th class="text-center" style="width: 300px;">任务名称</th>
						    		           <th class="text-center" style="width: 100px;">任务编号</th>
						    		           <th class="text-center" style="width: 100px;">创建时间</th>
						    		           <th class="text-center" style="width: 100px;">开始日期</th>
						    		           <th class="text-center" style="width: 100px;">结束日期</th>
						    		           <th class="text-center" style="width: 100px;">执行类型</th>
						    		           <th class="text-center" style="width: 80px;" >状态</th>
						    		           <th class="text-center" style="width: 80px;">任务明细</th>
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
				</header>
			</div>
		</div>
		
		</div>
		<script src="${ctx}/assets/jquery/ui/jquery-ui.custom.min.js"></script>
		<script src="${ctx}/assets/jquery/plugins/jquery-form/jquery-form.min.js"></script>
		<script src="${ctx}/assets/jquery/plugins/validation/jquery.validate.min.js"></script>
		<script src="${ctx}/assets/jquery/plugins/validation/localization/messages_zh.min.js"></script>
		<script src="${ctx}/assets/bootstrap/plugins/date-time/bootstrap-datepicker.min.js"></script>
		<script src="${ctx}/assets/bootstrap/plugins/date-time/locales/bootstrap-datepicker.zh-CN.js"></script>
		<script src="${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js"></script>
		<script src="${ctx}/assets/jquery/plugins/jqGrid/js/jquery.jqGrid.min.js"></script>
		<script src="${ctx}/assets/jquery/plugins/jqGrid/js/i18n/grid.locale-cn.js"></script>
		<script src="${ctx}/assets/jquery/plugins/lixi_common/ajax_render.js"></script>
		<script src="${ctx}/assets/jquery/plugins/lixi_common/jquery.serialize-object.min.js"></script>
		<script src="${ctx}/assets/jquery/plugins/toast/jquery.toaster.js"></script>
		<script src="${ctx}/assets/jquery/plugins/lixi_common/utils.js"></script>
		<script src="${ctx}/assets/jquery/plugins/jstree/jstree.min.js"></script>
		<script src="${ctx}/assets/jquery/plugins/lixi_common/js-table.js"></script>
		<script src="${ctx}/assets/js/agrims.js"></script>
		<script>
		
		function viewTaskMain(id) {
			reloadTaskDetailPage(id);
		}

		function genRowTemplate(node) {
	          var tamplate = '<tr id="{id}" class="data-row">' +
	                           '<td title="{name}">{name}</td>\
	                            <td title="{code}">{code}</td>\
	                            <td title="{createdOn}">{createdOn}</td>\
	                            <td title="{beginDate}">{beginDate}</td>\
	                            <td title="{endDate}">{endDate}</td>\
	                            <td title="{executeType}" style="text-align: center;"> {executeType}</td>\
	                            <td title="{state}" style="text-align: center;"><span class="btn btn-xs btn-primary">{state}</span></td>\
	                            <td style="text-align: center;cursor: pointer;" >\
	                            	<a class="green" name="detail"><i class="glyphicon glyphicon-cog btn-lg" style="padding: 0px 0px;"></i></a>\
	                          	</td>\
	                          </tr>';
	          return tamplate;
	        }

	        function ajaxRenderTable(url, template){
	          $('#takMainTable').jstable('set_params',{ url: url,template:template});
	        }
        
	        function reloadTakMainTable(){
	            ajaxRenderTable('${ctx}/collect/querySelfHistory',genRowTemplate());
	            //清空takDetailTable表
	           $('#taskDetailTable').data('tmId', null);
	           $('#dcpContainer').html('');
	          }
	        
			$(function() {
				$("[data-toggle='tooltip']").tooltip({
					html : true
				});
				$('#takMainTable').jstable({pager:true,template:genRowTemplate()});
      	        reloadTakMainTable();
      	        
      	      $("#searchForm").ajaxForm({
      	          beforeSubmit:function(arr, $form, options) { 
      	            var searchUrl = $form.attr('action')+ '?' +$form.serialize();
      	            $('#takMainTable').jstable('set_params',{url:searchUrl});
      	            return false;        
      	          },
      	          dataType : 'json',
      	        });
			});
			
			$('.taskEditBtn').on('click',  function() {
				var tm_set = $(this);
				reloadTaskDetailPage(tm_set.attr('id'));
			});
			
			$('#takMainTable').on('click', 'td.row-select,a[name="detail"]', function () {
	          var tm_set = $(this).parents('tr');
// 	          tm_set.css("color","#F08080").siblings().css("color","#000"); 
// 	          tm_set.css("background","#FFE4C4").siblings().css("background","#fff");
	          reloadTaskDetailPage(tm_set.attr('id'));
	        });
			
			function reloadTaskDetailPage(dmId) {
				var url = '${ctx}/main#page/collect/taskMain/selfHistoryDetailManager?taskMainId=' + dmId;
				window.open(url);
// 				loadPage(url);
			}
		</script>

</body>
</html>