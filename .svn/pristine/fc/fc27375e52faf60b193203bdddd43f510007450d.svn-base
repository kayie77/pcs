<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link href="${ctx}/assets/css/formCss.css" rel="stylesheet">
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<script>
function isExists(selectOb,value)
{
	for(var i = 0;i < selectOb.options.length;i++)
	{
		if(selectOb.options[i].value == value){
			return true;
		}
	}
	return false;
}
function add()
{
	var allperson = $("#allperson")[0];
	var person = $("#person")[0];
	for(var i = 0;i < allperson.options.length;i++)
	{
		//左边选中的人
		if(allperson.options[i].selected)
		{
			//判断右边是否已有，没有的话就加
			if(!isExists(person, allperson.options[i].value))
			{
				var op = new Option(allperson.options[i].text,allperson.options[i].value);
// 				person.options[person.options.length] = op;
				
				var label = $(allperson.options[i]).parent().attr("label");
				$(person).find("optgroup[label='"+label+"']").append(op);
				
// 				alert($(allperson.options[i]).parent().attr("label"));
				//获取左边所在的optgroup
				//查找右边optgroup的位置
				//插入
			}
		}
	}
}
function del()
{
	var person = $("#person")[0];
	for(var i = person.options.length - 1;i >= 0;i--)
	{
		if(person.options[i].selected)
		{
			person.options[i] = null;
		}
	}
}
function toSave()
{
	var personIds = "";
	var person = $("#person")[0];
	var taskMainId = $("#taskMainId").val();
	for(var i = person.options.length - 1;i >= 0;i--)
	{
		personIds += person.options[i].value + ",";
	}
	if(personIds == '')
	{
		personIds = personIds.substring(0,personIds.length-1);
	}
	
	$.ajax({
		url:"${ctx}/collect/taskMain/saveTaskMainPerson",
		data:{'personIds':personIds,'taskMainId':taskMainId},
		type:"get",
		dataType:"json",
		success:function(data){
			alert(data.message);
			if(data.status) {
				$("#closeButton1").trigger("click");
			}
		},
		error:function(){
			alert('保存失败');
		}
	});
}
</script>
<div class="modal-content">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title" id="myModalLabel">人员分配</h4>
  </div>
  <div class="modal-body">

    <!-- 数据元 表单项 -->

      <div class="form-group" style="height:420px;">
        <div class="col-sm-5">
        	<div style="font-size:16px">
        		所有人员
        	</div>
        	<input type="hidden" id="taskMainId" name="taskMainId" value="${taskMainId}" />
        	<select class="form-control" id="allperson" name="allperson" multiple="multiple" style="width:380px;height:400px;">
        		<c:forEach var="groupInfoItem" items="${groupInfoList}">
        			<optgroup label='${groupInfoItem.name}'>
	        			<c:forEach var="personGroupsItem" items="${groupInfoItem.personGroups}">
	        				<option value="${personGroupsItem.dataCollectPerson.id}">&nbsp;&nbsp;&nbsp;&nbsp;${personGroupsItem.dataCollectPerson.name}</option>
	        			</c:forEach>
        			</optgroup>
        		</c:forEach>
        		
        		<c:if test="${fn:length(notGroupPersonList) != 0}">
	        		<optgroup label='未分组'>
		        		<c:forEach var="notGroupPersonItem" items="${notGroupPersonList}">
		        			<option value="${notGroupPersonItem.id}">&nbsp;&nbsp;&nbsp;&nbsp;${notGroupPersonItem.name}</option>
		        		</c:forEach>
	        		</optgroup>
        		</c:if>
        	</select>
        </div>
        <div class="col-sm-1">
        	<table style="width:55px;height:100%;">
        		<tr>
        			<td height="170px">&nbsp;</td>
        		</tr>
        		<tr>
        			<td align="right"><input type="button" value=">" onclick="add()" /></td>
        		</tr>
        		<tr>
        			<td height="20px">&nbsp;</td>
        		</tr>
        		<tr>
        			<td align="right"><input type="button" value="<" onclick="del()" /></td>
        		</tr>
        	</table>
        	
        </div>
        <div class="col-sm-5">
        	<div style="font-size:16px">
        		分配人员
        	</div>
        	<select class="form-control" id="person" name="person" multiple="multiple" style="width:380px;height:400px;">
<%--         		<c:forEach var="taskGiveItem" items="${taskGiveList}"> --%>
<%--         			<option value="${taskGiveItem.dataCollectPerson.id}">&nbsp;&nbsp;&nbsp;&nbsp;${taskGiveItem.dataCollectPerson.name}</option> --%>
<%--         		</c:forEach> --%>
        	
        		<c:forEach var="groupInfoAndPersonItem" items="${groupInfoAndPersonList}">
        			<optgroup label='${groupInfoAndPersonItem.name}'>
	        			<c:forEach var="personItem" items="${groupInfoAndPersonItem.personList}">
	        				<option value="${personItem[0]}">&nbsp;&nbsp;&nbsp;&nbsp;${personItem[5]}</option>
	        			</c:forEach>
        			</optgroup>
        		</c:forEach>
        		
        	</select>
        </div>
      </div>

  </div>
  <div class="modal-footer">
  <div class="form-group">
    <button type="button" class="btn btn-success btn-white btn-round" onclick="toSave()"> <i class="ace-icon fa fa-floppy-o bigger-125"></i>保存</button>
    <button type="button" class="btn btn-danger btn-white btn-round" data-dismiss="modal" id="closeButton1"><i class="ace-icon fa fa-times bigger-125"></i>关闭</button>
  </div>
  </div>
</div>
<script>
</script>