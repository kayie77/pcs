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
    <h4 class="modal-title" id="myModalLabel">采集人员信息查看</h4>
  </div>
  <div class="modal-body">
    <div class="navBgDiv">
      <div class="navFontDiv">
        <span style="color:#B40605;float:left;">所属采集点：${dcpName}</span>
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
            <td class="attr-label">姓名：</td>
            <td class="display">${dataCollectPerson.name}</td>
            <td class="attr-label">编号：</td>
            <td class="display">${dataCollectPerson.code}</td>
            <td class="attr-label">性别：</td>
            <td class="display">${dataCollectPerson.sex}</td>
            <td class="attr-label">电话：</td>
            <td class="display">${dataCollectPerson.telephone}</td>
          </tr>
          <!-- tr>(td[style="text-align:right;"]+td)*4 -->
          <tr>
            <td class="attr-label">地址：</td>
            <td colspan=7>${dataCollectPerson.adress}</td>
          </tr> 
          <tr>
            <td class="attr-label">备注：</td>
            <td colspan=7>${dataCollectPerson.remark}</td>
          </tr> 
        
        </table>
      </div>
    </div>
  </div>   
</div>
