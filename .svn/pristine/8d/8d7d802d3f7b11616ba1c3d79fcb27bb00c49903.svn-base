<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link href="${ctx}/assets/css/formCss.css" rel="stylesheet">
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<div class="modal-content">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title" id="myModalLabel">农产品分类</h4>
  </div>
  <div class="modal-body">
    <!-- 数据元分类 表单项 -->
    <form id="basicDataCtgEditForm" class="form-horizontal" action="${ctx}/collect/agrProCtg" method="post">
      <div class="form-group has-feedback">
        <c:if test="${apc.id != null}">
          <input type="hidden" name="id" value="${apc.id}"/>
        </c:if>
        <label for="parent" class="col-sm-2 control-label">上级分类</label>
        <div class="col-sm-8">
          <input type="hidden" id="pid" name="parent[id]" value="${pid}">
          <div type="text" class="form-control def-cur" id="parentName" autocomplete="off">${apcName}</div>
          <span class="glyphicon glyphicon-chevron-down form-control-feedback" aria-hidden="true" style=""></span>
          <div id="bdctg_tree" class="hide float-menu col-sm-8" style="width: 93%;"></div>
        </div>
      </div>
      <div class="form-group">
        <label for="name" class="col-sm-2 control-label"><span style="color:red;"> * </span>分类名称</label>
        <div class="col-sm-8">
          <input type="text" class="form-control" id="name" name="name" value="${apc.name}">
        </div>
      </div>
      <div class="form-group">  
        <label for="code" class="col-sm-2 control-label"><span style="color:red;"> * </span>分类编码</label>
        <div class="col-sm-8">
          <input type="text" class="form-control" id="code" name="code" value="${apc.code}">
        </div>
      </div>
      <div class="form-group">
        <label for="state" class="col-sm-2 control-label">状态</label>
        <div class="col-sm-3">
          <select name="state" id="state" class="form-control">
            <option value="1" <c:if test="${apc.state == 1}" >selected</c:if> >启用</option>
            <option value="0" <c:if test="${apc.state == 0}" >selected</c:if>>停用</option>
          </select>
        </div>
        <label for="sort" class="col-sm-2 control-label">排序</label>
        <div class="col-sm-3">
          <input type="text" class="form-control" id="sort" name="sort" value="${apc.sort}" maxlength="10">
        </div>
      </div>
      <div class="form-group">
        <label for="explain" class="col-sm-2 control-label">分类说明</label>
        <div class="col-sm-8">
          <textarea name="explain" id="explain" class="form-control" rows="6" maxlength="500">${apc.explain}</textarea>
        </div>
      </div>
    </form>
  </div>
  <div class="modal-footer">
    <button type="button" class="btn btn-success btn-white btn-round" onclick="$('#basicDataCtgEditForm').submit()"><i class="ace-icon fa fa-floppy-o bigger-125"></i>保存</button>
  	<button type="button" class="btn btn-danger btn-white btn-round" data-dismiss="modal"><i class="ace-icon fa fa-times bigger-125"></i>关闭</button>
  </div>
</div>