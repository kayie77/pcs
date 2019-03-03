<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jstree/themes/default/style.css" />
		<script type="text/javascript">
			//处理console报错到问题
			if (!window.console || !console.firebug){
			    var names = ["log", "debug", "info", "warn", "error", "assert", "dir", "dirxml", "group", "groupEnd", "time", "timeEnd", "count", "trace", "profile", "profileEnd"];
			    window.console = {};
			    for (var i = 0; i < names.length; ++i) window.console[names[i]] = function() {}
			}
			
			$(function () {
				$("#subBtn").bind("click", function () {
				
					if($("#dicReportType").val() == '') {
						bootbox.alert('请选择上报方式！');
						return false;
					}
					
					var groupsArray = $("#groupsArray")[0];
					for(var i = 0;i < groupsArray.options.length;i++) {
						groupsArray.options[i].selected = true;
					}
					
				    $('#messageForm').submit();
			    });
			});
			
			function isExistsValue(select,value) {
				for(var i = 0;i < select.options.length;i++) {
					if(select.options[i].value == value) {
						return true;
					}
				}
				return false;
			}
			function createOption(text,value) {
				var op = new Option();
				op.value = value;
				op.text = text;
				return op;
			}
			function addGroup() {
				
				var oldGroup = $("#oldGroup")[0];
				var groupsArray = $("#groupsArray")[0];
				
				for(var i = 0;i < oldGroup.options.length;i++) {
					if(oldGroup.options[i].selected == true && !isExistsValue(groupsArray,oldGroup.options[i].value)) {
						
						groupsArray.options[groupsArray.options.length] = createOption(oldGroup.options[i].text,oldGroup.options[i].value);
						break;
					}
				}
			}
			function addGroups() {
				var oldGroup = $("#oldGroup")[0];
				var groupsArray = $("#groupsArray")[0];
				
				for(var i = 0;i < oldGroup.options.length;i++) {
					if(oldGroup.options[i].selected == true && !isExistsValue(groupsArray,oldGroup.options[i].value)) {
						
						groupsArray.options[groupsArray.options.length] = createOption(oldGroup.options[i].text,oldGroup.options[i].value);
					}
				}
			}
			function deleteGroup() {
				var groupsArray = $("#groupsArray")[0];
				
				for(var i = groupsArray.options.length-1;i >= 0;i--) {
					if(groupsArray.options[i].selected == true) {
						groupsArray.options[i] = null;
						break;
					}
				}
			}
			function deleteGroups() {
				var groupsArray = $("#groupsArray")[0];
				
				for(var i = groupsArray.options.length-1;i >= 0;i--) {
					if(groupsArray.options[i].selected == true) {
						groupsArray.options[i] = null;
					}
				}
			}
			function deleteFile(id) {
				bootbox.confirm("确定删除吗？",function(result) {
					if(result) {	
						
						var url = "${ctx}/office/task/deleteTableHeadFile?id="+id+"&date="+new Date().getTime();
						$.getJSON(url,function(data) {
							if(data.success) {
								$("#tableHeadFileDiv").remove();
							}
						});
					}
				});
				
			}
		</script>	
<div class="modal-dialog" style="width:800px;">
	<div class="modal-content">
      	<div class="table-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<i class="ace-icon fa fa-users"></i>
        	<b>报表群组分配信息</b>
      	</div>
      	<form class="form-horizontal" id="messageForm" modelAttribute="message" action="${actionUrl}" method="POST" enctype="multipart/form-data">
      		<div class="modal-body">
	      		<div class="row">
	      			<div class="col-sm-12">
	      			
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">报表编码:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
				    			<input style="width:500px" id="reportShowCode" maxlength="25" value="${dicReport.reportShowCode}" name="reportShowCode" placeholder="报表编码..." required="required" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">报表名称:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
				    			<input style="width:500px" id="reportName" maxlength="50" value="${dicReport.reportName}" name="reportName" placeholder="报表名称..." required="required" />
							</div>
						</div>
						
						<div class="form-group" style="display:none">
							<label class="col-sm-2 control-label" for="title">路径:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
				    			<input style="width:500px" id="reportShowUrl" maxlength="50" value="${dicReport.reportShowUrl}" name="reportShowUrl" placeholder="路径..."  />
							</div>
						</div>
						
						<div class="form-group" style="display:none">
							<label class="col-sm-2 control-label" for="title">模板:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
				    			<input style="width:500px" id="reportInputCode" maxlength="50" value="${dicReport.reportInputCode}" name="reportInputCode" placeholder="模板..."  />
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">报表类型:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
								<select id="ethinkType" name="ethinkType">
								    <option value='Y'<c:if test="${dicReport.ethinkType == 'Y'}"> selected </c:if>>9大类区分处室报表</option>  
								    <option value='N'<c:if test="${dicReport.ethinkType == 'N'}"> selected </c:if>>9大类不区分处室报表</option>
								    <option value='J'<c:if test="${dicReport.ethinkType == 'J'}"> selected </c:if>>进度报表</option>
								</select>
							</div>
						</div>
						
						<!-- 
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">报表类型:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
				    			<input style="width:500px" id="ethinkType" maxlength="50" value="${dicReport.ethinkType}" name="ethinkType" placeholder="报表类型..." required="required" />
							</div>
						</div>
						 -->
						
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">标题:</label>
							<div class="col-sm-4 controls">
				    			<input style="width:500px" id="tableHeader" maxlength="100" value="${dicReport.tableHeader}" name="tableHeader" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label" for="unitTitle">单位:</label>
							<div class="col-sm-4 controls">
				    			<input style="width:500px" id="unitTitle" maxlength="50" value="${dicReport.unitTitle}" name="unitTitle" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label" for="tableFoot1">报表尾部信息1:</label>
							<div class="col-sm-4 controls">
				    			<input style="width:500px" id="tableFoot1" maxlength="500" value="${dicReport.tableFoot1}" name="tableFoot1" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label" for="tableFoot2">报表尾部信息2:</label>
							<div class="col-sm-4 controls">
				    			<input style="width:500px" id="tableFoot2" maxlength="500" value="${dicReport.tableFoot2}" name="tableFoot2" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label" for="tableFoot3">报表尾部信息3:</label>
							<div class="col-sm-4 controls">
				    			<input style="width:500px" id="tableFoot3" maxlength="500" value="${dicReport.tableFoot3}" name="tableFoot3" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">上报方式:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
								
				    			<input type="radio" id="dicReportType" name="dicReportType" value="1" <c:if test="${dicReport.dicReportType == '1'}"> checked </c:if>/>直报
				    			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				    			<input type="radio" id="dicReportType" name="dicReportType" value="2" <c:if test="${dicReport.dicReportType == '2'}"> checked </c:if>/>逐级上报
				    			
				    			<c:if test="${dicReport.dicReportType == null}">
				    				<script>
				    				$("#dicReportType")[0].checked = true;
				    				</script>
				    			</c:if>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">群组:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
				    			<table>
				    				<tr>
				    					<td>
				    						<select id="oldGroup" name="oldGroup" multiple="multiple" style="width:200px;height:220px;">
				    							<c:forEach var="groupItem" items="${groupList}">
				    								<option value="${groupItem.id}">${groupItem.groupName}</option>
				    							</c:forEach>
					    					</select>
				    					</td>
				    					<td style="padding-left:5px;padding-right:5px;">
				    						<button onclick="addGroup()" type="button" class="btn btn-sm btn-info">
				    						     <i class="ace-icon fa fa-angle-right icon-only"></i>				  
				    						</button><br/><br/>
				    						<button onclick="addGroups()" type="button" class="btn btn-sm btn-info">
				    						     <i class="ace-icon fa fa-angle-double-right icon-only"></i>	
				    						</button><br/><br/>
				    						<button onclick="deleteGroup()" type="button" class="btn btn-sm btn-info">
				    						     <i class="ace-icon fa fa-angle-left icon-only"></i>	
				    						</button><br/><br/>
				    						<button onclick="deleteGroups()" type="button" class="btn btn-sm btn-info">
				    						    <i class="ace-icon fa fa-angle-double-left icon-only"></i>	
				    						</button><br/><br/>
				    					</td>
				    					<td>
				    						<select id="groupsArray" name="groupsArray" multiple="multiple" style="width:200px;height:220px;">
				    							<c:forEach var="groupItem" items="${dicReportGroupList}">
				    								<option value="${groupItem.group.id}">${groupItem.group.groupName}</option>
				    							</c:forEach>
					    					</select>
				    					</td>
				    				</tr>
				    			</table>
							</div>
						</div>
						
						
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">表头文件:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
								<input type="file" name="tableheadFile" id="tableheadFile"/>
							</div>
						</div>
						
						<c:if test="${dicReport.reportHeaderFileName != null}">
							<div id="tableHeadFileDiv" class="form-group">
								<label class="col-sm-2 control-label" for="title">表头文件:</label>
								<div class="col-sm-4 controls">
									<a href="###">${dicReport.reportHeaderFileName}</a>
									<input type="button" value="删除" onclick="deleteFile('${dicReport.id}')"/>
								</div>
							</div>
						</c:if>
						
						
						
						
					</div>
				</div>
			</div>
			<input type="hidden" id="id" name="id" value="${dicReport.id}">
			<div class="modal-footer">
        			<button type="button" class="btn btn-default btn-info btn-sm btn-round" data-dismiss="modal" aria-hidden="true"> 
        				<i class="ace-icon fa fa-times"></i>        			
        				关闭
       				</button>
      	 			<button type="button" id="subBtn" class="btn btn-default btn-info btn-sm btn-round">
          				<i class="ace-icon fa fa-check"></i>
        	 			 保存
          			</button>
       		</div>
       	</form>
    </div>
</div>
