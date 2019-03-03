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
    <form id="dataCollectPointForm" class="form-horizontal" action="${ctx}/collect/dataCollectPoint" method="post">
      <div class="form-group">
        <c:if test="${dataCollectPoint.id != null}">
          <input type="hidden" name="id" value="${dataCollectPoint.id}"/>
        </c:if>
        <!-- 分类id -->
        <input type="hidden" name="dataCollectCategory[id]" value="${ctgId}">
        <label for="name" class="col-sm-2 control-label"><span style="color:red;"> * </span>名称</label>
        <div class="col-sm-3">
          <input type="text" class="form-control" id="name" name="name" value="${dataCollectPoint.name}">
        </div>
        <label for="shortname" class="col-sm-2 control-label">简称</label>
        <div class="col-sm-3">
          <input type="text" class="form-control" id="shortname" name="shortname" value="${dataCollectPoint.shortname}">
        </div>
      </div>
      <div class="form-group">
        <label for="linkman" class="col-sm-2 control-label">联系人</label>
        <div class="col-sm-3">
          <input type="text" class="form-control" id="linkman" name="linkman" value="${dataCollectPoint.linkman}">
        </div>
        <label for="region" class="col-sm-2 control-label">所属地区</label>
        <div class="col-sm-3">
          <select class="form-control" id="division" name="division[id]" required>
            <c:forEach items="${divisionList}" var ="division">
          		<option value="${division.id}" data-nameEN="${division.divName}">${division.divName}</option>
            </c:forEach>
          </select>
        </div>
      </div>
      <div class="form-group">
        <label for="telephone" class="col-sm-2 control-label">联系电话</label>
        <div class="col-sm-3">
          <input type="text" class="form-control" id="telephone" name="telephone" value="${dataCollectPoint.telephone}">
        </div>
                <label for="state" class="col-sm-2 control-label">状态</label>
        <div class="col-sm-3">
          <select name="state" id="state" class="form-control">
            <option value="1" <c:if test="${dataCollectPoint.state == 1}" >selected</c:if> >启用</option>
            <option value="0" <c:if test="${dataCollectPoint.state == 0}" >selected</c:if>>停用</option>
          </select>
        </div>
      </div>   
      <div class="form-group">
        <label for="remark" class="col-sm-2 control-label">备注</label>
        <div class="col-sm-8">
          <textarea name="remark" id="remark" class="form-control" rows="6" maxLength="500">${dataCollectPoint.remark}</textarea>
        </div>
      </div>
    </form>
  </div>
  <div class="modal-footer">
    <button type="button" class="btn btn-success btn-white btn-round" onclick="$('#dataCollectPointForm').submit()"><i class="ace-icon fa fa-floppy-o bigger-125"></i>保存</button>
    <button type="button" class="btn btn-danger btn-white btn-round" data-dismiss="modal"><i class="ace-icon fa fa-times bigger-125"></i>关闭</button>
  </div>
</div>
<script>
</script>