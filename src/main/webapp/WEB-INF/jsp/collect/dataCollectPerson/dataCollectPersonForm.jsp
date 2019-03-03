<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link href="${ctx}/assets/css/formCss.css" rel="stylesheet">
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<div class="modal-content">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title" id="myModalLabel">采集点信息新增/编辑</h4>
  </div>
  <div class="modal-body">

    <!-- 数据元 表单项 -->
    <form id="dataCollectPersonForm" class="form-horizontal" action="${ctx}/collect/dataCollectPerson" method="post">
      <div class="form-group">
      
        <c:if test="${dataCollectPerson.id != null}">
          <input type="hidden" name="id" value="${dataCollectPerson.id}"/>
        </c:if>
        <!-- 分类id -->
        <input type="hidden" name="dataCollectPoint[id]" value="${dcpId}">
        <label for="name" class="col-sm-2 control-label"><span style="color:red;"> * </span>姓名</label>
        <div class="col-sm-3">
          <input type="text" class="form-control" id="name" name="name" value="${dataCollectPerson.name}">
        </div>
        <label for="code" class="col-sm-2 control-label"><span style="color:red;"> * </span>编号</label>
        <div class="col-sm-3">
          <input type="text" class="form-control" id="code" name="code" value="${dataCollectPerson.code}">
        </div>
      </div>
      <div class="form-group">
        <label for="alias" class="col-sm-2 control-label"><span style="color:red;"> * </span>别名</label>
        <div class="col-sm-3">
          <input type="text" class="form-control" id="alias" name="alias" value="${dataCollectPoint.alias}">
        </div>
        <label for="sex" class="col-sm-2 control-label">性别</label>
        <div class="col-sm-3">
          <input type="text" class="form-control" id="sex" name="sex" value="${dataCollectPerson.sex}">
        </div>
       </div>
      <div class="form-group">
        <label for="telephone" class="col-sm-2 control-label">电话</label>
        <div class="col-sm-3">
          <input type="text" class="form-control" id="telephone" name="telephone" value="${dataCollectPoint.telephone}">
        </div>
      </div>
      <div class="form-group">
        <label for="adress" class="col-sm-2 control-label">地址</label>
        <div class="col-sm-8">
          <textarea name="adress" id="adress" class="form-control" rows="6" maxLength="500">${dataCollectPerson.adress}</textarea>
        </div>
      </div> 
      <div class="form-group">
        <label for="remark" class="col-sm-2 control-label">备注</label>
        <div class="col-sm-8">
          <textarea name="remark" id="remark" class="form-control" rows="6" maxLength="500">${dataCollectPerson.remark}</textarea>
        </div>
      </div>
    </form>
  </div>
  <div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    <button type="button" class="btn btn-primary" onclick="$('#dataCollectPersonForm').submit()">保存</button>
  </div>
</div>
