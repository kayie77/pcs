<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
		<script type="text/javascript" charset="utf-8" src='${ctx}/assets/kindeditor-4.1.7/kindeditor.js'></script>
		<script type="text/javascript" charset="utf-8" src='${ctx}/assets/kindeditor-4.1.7/lang/zh_CN.js'></script>
		<script type="text/javascript">
			//处理console报错到问题
			if (!window.console || !console.firebug){
			    var names = ["log", "debug", "info", "warn", "error", "assert", "dir", "dirxml", "group", "groupEnd", "time", "timeEnd", "count", "trace", "profile", "profileEnd"];
			    window.console = {};
			    for (var i = 0; i < names.length; ++i) window.console[names[i]] = function() {}
			}

			var keditor;
			
			keditor = KindEditor.create('#contentTemp', {
				uploadJson : '${ctx}/office/file/update',
				fileManagerJson : '${ctx}/office/file/update',
                allowFileManager : false,
        		allowUpload : true,
                items: ["source", "|", "undo", "redo", "|", "justifyleft", "justifycenter", "justifyright", "justifyfull", "insertorderedlist", "insertunorderedlist", "indent", "outdent", "subscript", "superscript", "clearhtml", "quickformat", "selectall", "|", "fullscreen", "/", "formatblock", "fontname", "fontsize", "|", "forecolor", "hilitecolor", "bold", "italic", "underline", "strikethrough", "lineheight", "removeformat", "|", "image", "table", "hr", "pagebreak", "anchor", "link", "unlink"]
        	});
			
			$(function () {
				$("#subBtn").bind("click", function () {

					var wordtypeid = $("#wordtypeid")[0];
					if(wordtypeid.options.length == 0) {
						alert('请选择文字类型！');
						return false;
					}
					
					var title = $("#title")[0];
					if(title.value == '') {
						alert('请填写标题！');
						title.focus();
						return false;
					}
					
					$('#content').val(keditor.html());
					if($('#content').val().length > 2000) {
						//alert('内容不能超过2000个字！');
						//return false;
					}
					
					$("#uploadflag").val("0");
				    $('#messageForm').submit();
					$("#subBtn")[0].disabled = "disabled";
			    });
				
				
			});
			function downloadfile(id) {
				var url = '${ctx}/office/wordtypeinfo/downloadWordTypeInfo?id=' + id;
				window.open(url);
			}
			function deletebutton(ob) {
				$(ob).parent().remove();
			}
			function addfile() {
				$("#filediv").append("<div><input type='file' style='width:250px;position:relative;float:left;' name='uploadfile'/><input type='button' value='删除' onclick='deletebutton(this)' style='position:relative;float:left;'/></div>");
			}
			function deleteWordTypeInfoFile(a,id) {
				
				var url = "${ctx}/office/wordtypeinfo/deleteWordTypeInfoFile?id=" + id;
				$.getJSON(url,function(data) {
					if(data.success) {
						$(a).parent().parent().remove();
						alert(data.message);
					}
				});
			}
			function changeMonth(selectOb,defaultValue) {
				
				var wordtypeid = $("#wordtypeid")[0];
				if(defaultValue != '') {
					selectOb.selectedIndex = parseInt(defaultValue) - 1;
				}
				var month = selectOb.options[selectOb.selectedIndex].value;
				var url = "${ctx}/office/wordtype/queryWordtypeByMonth?month=" + month;
				
				clearSelect(wordtypeid);
				
				$.getJSON(url,function(data) {
					
					var list = data.wordTypeList;
					for(var i = 0;i < list.length;i++) {
						
						var op = new Option();
						op.value = list[i].id;
						op.text = list[i].title;
						
						if(list[i].id == '${wordType.id}') {
							op.selected = true;
						}
						
						wordtypeid.options[wordtypeid.options.length] = op;
					}
				});
				
			}
			function clearSelect(ob) {
				for(var i = ob.options.length - 1;i >= 0;i--) {
					ob.options[i] = null;
				}
			}
			function touplad(id) {

				var url = "${ctx}/office/wordtypeinfo/uploadWordTypeInfo?id=" + id;
				$.getJSON(url,function(data) {
					if(data.success) {
						
						$("#subBtn")[0].style.display = "none";
						$("#uploadBtn")[0].style.display = "none";
						
						$("#grid-table").trigger('reloadGrid');
						bootbox.alert('操作成功！');
					} else {
						bootbox.alert('操作失败！');
					}
				});
			}
		</script>	
<div class="modal-dialog" style="width:800px;">
	<div class="modal-content">
      	<div class="table-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<i class="ace-icon fa fa-file-text"></i>
        	<b>文字填报信息</b>
      	</div>
      	<form class="form-horizontal" id="messageForm" modelAttribute="message" action="${ctx}/office/wordtypeinfo/save" method="POST" enctype="multipart/form-data">
      		<input type="hidden" id="uploadflag" name="uploadflag" value="${wordTypeInfo.uploadflag}" />
      		<div class="modal-body">
	      		<div class="row">
	      			<div class="col-sm-12">
	      			
	      				<div class="form-group">
							<label class="col-sm-2 control-label" for="title">文字编号:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
								<c:choose>
									<c:when test="${wordTypeInfo == null}">
										<input style="width:500px" maxlength="10" id="num" readonly value="${wordNum}" name="num" placeholder="文字编号..." required="required" />
									</c:when>
									<c:otherwise>
										<input style="width:500px" maxlength="10" id="num" readonly value="${wordTypeInfo.num}" name="num" placeholder="文字编号..." required="required" />
									</c:otherwise>
								</c:choose>
				    			
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">文字标题:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
				    			<input style="width:500px" maxlength="100" id="title" value="${wordTypeInfo.title}" name="title" placeholder="文字标题..." required="required" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label" for="title">文字类型:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
				    			<select id="month" name="month" onchange="changeMonth(this,'')">
				    				<c:forEach var="month" items="${monthList}">
				    					<option value="${month.key}" <c:if test="${wordType.wtmonth == month.key}"> selected </c:if>>${month.value}</option>
				    				</c:forEach>
				    			</select>
				    			
				    			<select id="wordtypeid" name="wordtypeid" style="width:150px">
				    			</select>
				    			
				    			<c:choose>
				    				<c:when test="${wordType.wtmonth == null}">
						    			<script>
						    				changeMonth($("#month")[0],'${currentMonth}');
						    			</script>
				    				</c:when>
				    				<c:otherwise>
						    			<script>
						    				changeMonth($("#month")[0],'${wordType.wtmonth}');
						    			</script>
				    				</c:otherwise>
				    			</c:choose>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label" for="content">内容:<span class="required">*</span></label>
							<div class="col-sm-4 controls">
								<textarea id="content" name="content" style="display:none"></textarea>
								<textarea id="contentTemp" name="contentTemp" style="width:500px;height:250px;" placeholder="内容..." required="required">${wordTypeInfo.content}</textarea>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-sm-2 control-label" for="content">附件:<span class="required"></span></label>
							<div class="col-sm-8 controls">
								<button type="button" onclick="addfile()" class="btn btn-default btn-info btn-sm btn-round"> 
			        				<i class="ace-icon fa fa-plus-square"></i>        			
			        				新增附件
			       				</button>
							</div>
						</div>
       				
       					<div class="form-group">
							<label class="col-sm-2 control-label" for="content"><span class="required"></span></label>
							<div class="col-sm-8 controls" id="filediv">
								
								<!-- 
								<div><input type='file' style='width:180px' name='uploadfile'/><input type='button' value='删除' onclick='deletebutton(this)'/></div>
								<div><input type='file' style='width:180px' name='uploadfile'/></div>
								<div><input type='file' style='width:180px' name='uploadfile'/></div>
								<div><input type='file' style='width:180px' name='uploadfile'/></div>
								 -->
							</div>
						</div>
						
						
						
						<c:if test="${fn:length(wordTypeInfoFileList) > 0}">
							<div class="col-sm-12">
								<div class="form-group">
									<label class="col-sm-2 control-label" for="content">已上传附件:</label>
									<div class="col-sm-8 controls">
										<table style="width:100%">
											<tr>
												<td>文件名</td>
												<td>文件大小</td>
												<td>操作</td>
											</tr>
						    				<c:forEach var="messageFileItem" items="${wordTypeInfoFileList}">
											<tr>
												<td><a href="###" onclick="downloadfile('${messageFileItem.id}')">${messageFileItem.filename}</a></td>
												<td>${messageFileItem.filelength/1024}&nbsp;KB</td>
												<td><a href="###" onclick="deleteWordTypeInfoFile(this,'${messageFileItem.id}')">删除</a></td>
											</tr>
						    				</c:forEach>
										</table>
									</div>
								</div>
							</div>
						</c:if>
					</div>
				</div>
			</div>
			<input type="hidden" id="id" name="id" value="${wordTypeInfo.id}">
			<div class="modal-footer">
        			<button type="button" class="btn btn-default btn-info btn-sm btn-round" data-dismiss="modal" aria-hidden="true"> 
        				<i class="ace-icon fa fa-times"></i>        			
        				关闭
       				</button>
      	 			<button type="button" id="subBtn" class="btn btn-default btn-info btn-sm btn-round">
          				<i class="ace-icon fa fa-check"></i>
        	 			 保存
          			</button>
          			
          			<c:if test="${isAear}">
          				<script>
          					$("#subBtn")[0].style.display = "";
          				</script>
          			</c:if>
          			
          			<%/*
          			<button type="button" id="uploadBtn" onclick="touplad('${wordTypeInfo.id}')" class="btn btn-default btn-info btn-sm btn-round" style="display:none">
          				<i class="ace-icon fa fa-check"></i>
        	 			 上报
          			</button>
          			<c:if test="${wordTypeInfo.uploadflag != '1'}">
          				<script>
          					$("#subBtn")[0].style.display = "";
          				</script>
          			</c:if>
          			<c:if test="${wordTypeInfo.uploadflag != null && wordTypeInfo.uploadflag != '1'}">
          				<script>
          					$("#uploadBtn")[0].style.display = "";
          				</script>
          			</c:if>
          			*/%>
       		</div>
       	</form>
    </div>
</div>

<script>
function initSomeStyle() {
	$(".modal-content")[0].style.border = "1px solid #307ecc";
	$(".table-header").find(".close")[0].style.fontSize = "40px";
}
setTimeout("initSomeStyle()",300);
</script>


