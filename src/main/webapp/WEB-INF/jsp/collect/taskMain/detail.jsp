<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<style type="text/css">
  .topDiv{
    margin:40px 0px 0px;
    border-bottom: 2px solid #B40605;
  }
  .navBgDiv{
    background-color:#EEE;
    height:41px;
    width:100%;
  }
  .navFontDiv{
    margin: 0px auto;
    font-size: 16px;
    line-height: 40px;
  }
  .mainDiv{
    margin: -15px auto;
    font-size: 13px;
  }
  .tableTopFont{
    font-weight:bold;
    color:#0D69D3;
  }
  .table>tbody>tr>td{
    border-top: 1px solid #FFF;
  }
  .table .attr-label{
    font-weight: 700;
    width: 10%;
  }
  .table .display{
    width: 15%;
  }
</style> 
<div class="modal-content">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title" id="myModalLabel">数据采集任务信息查看</h4>
  </div>
  <div class="modal-body">
    <div class="mainDiv">
      
      <!-- 基本信息 -->
      <div  class="panel panel-default" style="margin-top:20px">
        <table class="table table-hover fix-table fix-table-wrap" >
          <tr>
            <td class="tableTopFont">基本信息</td>
            <td colspan=7></td>
          </tr>
          <tr>
            <td class="attr-label">名称：</td>
            <td class="display" colspan="3">${taskMain.name}</td>
<!--             <td class="attr-label">编号：</td> -->
<!--             <td class="display">${taskMain.code}</td> -->
            <td class="attr-label">创建时间：</td>
            <td class="display">${taskMain.createdOn}</td>
<%--             <td class="attr-label">任务对象：</td>
            <td class="display">${taskMain.dataCollectCategory.name}</td> --%>
          </tr>
          <tr>
            <td class="attr-label">开始日期：</td>
            <td class="display"><fmt:formatDate pattern="yyyy-MM-dd" value="${taskMain.beginDate}"/></td>
            <td class="attr-label">结束日期：</td>
            <td class="display"><fmt:formatDate pattern="yyyy-MM-dd" value="${taskMain.endDate}"/></td>
            <td class="attr-label">执行类型：</td>
            <td class="display">
              <c:if test="${taskMain.executeType == 1}">每日执行</c:if>
              <c:if test="${taskMain.executeType == 2}">每周执行</c:if>
              <c:if test="${taskMain.executeType == 3}">每月执行</c:if>
            </td>
            <td class="attr-label">状态：</td>
            <td class="display">
              <c:if test="${taskMain.state == 0}">未启用</c:if>
              <c:if test="${taskMain.state == 1}">在用</c:if>
              <c:if test="${taskMain.state == -1}">到期</c:if>
              <c:if test="${taskMain.state == -2}">停用</c:if>
            </td>
            <td class="attr-label"></td>
            <td class="display"></td>
          </tr>
<%--           <tr>
            <td class="attr-label">领取状态：</td>
            <td class="display">${taskMain.receiveStatus}</td>
            <td class="attr-label">上报状态：</td>
            <td class="display">${taskMain.reportedStatus}</td>
          </tr> --%>
          <!-- tr>(td[style="text-align:right;"]+td)*4 -->
          <tr>
            <td class="attr-label">描述：</td>
            <td colspan=7>${taskMain.description}</td>
          </tr> 
          <tr>
            <td class="attr-label">备注：</td>
            <td colspan=7>${taskMain.remark}</td>
          </tr> 
        
        </table>
      </div>
    </div>
  </div>   
</div>
