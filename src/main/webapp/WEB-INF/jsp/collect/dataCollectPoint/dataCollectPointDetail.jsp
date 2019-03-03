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
    margin: 0px auto;
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
    width: 10%;
  }
  .table .display{
    width: 15%;
  }
</style> 
<div class="modal-content">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <h4 class="modal-title" id="myModalLabel">采集点详细信息查看</h4>
  </div>
  <div class="modal-body">
    <div class="navBgDiv">
      <div class="navFontDiv">
        <span style="color:#B40605;float:left;">采集点分类：${ctgName}</span>
      </div>
    </div>

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
            <td class="display" colspan="3">${dataCollectPoint.name}</td>
           <%--  <td class="attr-label">编码：</td>
            <td class="display">${dataCollectPoint.code}</td> --%>
            <td class="attr-label">简称：</td>
            <td class="display">${dataCollectPoint.shortname}</td>
            <td class="attr-label">所属地区：</td>
            <td class="display">${dataCollectPoint.division.divName}</td>
          </tr>
          <tr>
            <td class="attr-label">联系人：</td>
            <td class="display">${dataCollectPoint.linkman}</td>
            <td class="attr-label">联系电话：</td>
            <td class="display">${dataCollectPoint.telephone}</td>
            <td class="attr-label">经度：</td>
            <td class="display">${dataCollectPoint.longitude}</td>
            <td class="attr-label">纬度：</td>
            <td class="display">${dataCollectPoint.latitude}</td>
          </tr>
          <!-- tr>(td[style="text-align:right;"]+td)*4 -->
          <tr>
            <td class="attr-label">地址：</td>
            <td colspan=7>${dataCollectPoint.adress}</td>
          </tr> 
          <tr>
            <td class="attr-label">备注：</td>
            <td colspan=7>${dataCollectPoint.remark}</td>
          </tr> 
        
        </table>
      </div>
    </div>
  </div>   
</div>
