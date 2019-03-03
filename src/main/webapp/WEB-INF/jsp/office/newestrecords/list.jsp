<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%
response.setHeader("Pragma","No-cache");    
response.setHeader("Cache-Control","no-cache");    
response.setDateHeader("Expires", -10);   
%>  
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">   
<link rel="stylesheet" href="${ctx}/assets/jquery/ui/css/jquery-ui.min.css" />
<script src="${ctx}/assets/My97DatePicker/WdatePicker.js"></script>

<script>
	function changeAgrresidx(selectOb) {
		
		var index_name = $("#index_name")[0];
		var agrresidxCode = selectOb.options[selectOb.selectedIndex].text;
		var url = "${ctx}/office/newestRecords/queryIndexNameList?agrresidxCode=" + agrresidxCode+"&date="+new Date().getTime();
		
		clearSelect(index_name);
		
		$.getJSON(url,function(data) {
			
			var list = data.indexNameList;
			
			var op = new Option();
			op.value = "";
			op.text = "-=未选择=-";
			index_name.options[index_name.options.length] = op;
			
			for(var i = 0;i < list.length;i++) {
				
				var op = new Option();
				op.value = list[i].indexCode;
				op.text = list[i].indexName;
				
				index_name.options[index_name.options.length] = op;
			}
		});
	}
	function changeClass1(selectOb) {
		
		var class2 = $("#class2")[0];
		var code = selectOb.options[selectOb.selectedIndex].value;
		var url = "${ctx}/office/newestRecords/queryClass2?code=" + code+"&date="+new Date().getTime();
		
		clearSelect(class2);
		
		$.getJSON(url,function(data) {
			
			var list = data.list2;
			
			var op = new Option();
			op.value = "";
			op.text = "-=未选择=-";
			class2.options[class2.options.length] = op;
			
			for(var i = 0;i < list.length;i++) {
				
				var op = new Option();
				op.value = list[i].agrres2ndClasscode;
				op.text = list[i].agrres2ndClass;
				
				class2.options[class2.options.length] = op;
			}
			changeClass2($("#class2")[0]);
		});
	}
	
	function changeClass2(selectOb) {
			
		var class3 = $("#class3")[0];
		var code = selectOb.options[selectOb.selectedIndex].value;
		var url = "${ctx}/office/newestRecords/queryClass3?code=" + code+"&date="+new Date().getTime();
		
		clearSelect(class3);
		
		$.getJSON(url,function(data) {
			
			var list = data.list3;
			
			var op = new Option();
			op.value = "";
			op.text = "-=未选择=-";
			class3.options[class3.options.length] = op;
			
			for(var i = 0;i < list.length;i++) {
				
				var op = new Option();
				op.value = list[i].agrres3rdClasscode;
				op.text = list[i].agrres3rdClass;
				
				class3.options[class3.options.length] = op;
			}
			
			changeClass3($("#class3")[0]);
		});
	}
		
	function changeClass3(selectOb) {
		
		var class4 = $("#class4")[0];
		var code = selectOb.options[selectOb.selectedIndex].value;
		var url = "${ctx}/office/newestRecords/queryClass4?code=" + code+"&date="+new Date().getTime();
		
		clearSelect(class4);
		
		$.getJSON(url,function(data) {
			
			var list = data.list4;
			
			var op = new Option();
			op.value = "";
			op.text = "-=未选择=-";
			class4.options[class4.options.length] = op;
			
			for(var i = 0;i < list.length;i++) {
				
				var op = new Option();
				op.value = list[i].agrresidxCode;
				op.text = list[i].agrresidx;
				
				class4.options[class4.options.length] = op;
			}
			
			changeClass4($("#class4")[0]);
		});
	}
	
	function changeClass4(selectOb) {
		
		var class5 = $("#class5")[0];
		var code = selectOb.options[selectOb.selectedIndex].value;
		var url = "${ctx}/office/newestRecords/queryClass5?code=" + code+"&date="+new Date().getTime();
		
		clearSelect(class5);
		
		$.getJSON(url,function(data) {
			
			var list = data.list5;
			
			var op = new Option();
			op.value = "";
			op.text = "-=未选择=-";
			class5.options[class5.options.length] = op;
			
			for(var i = 0;i < list.length;i++) {
				
				var op = new Option();
				op.value = list[i].agrresidxCode;
				op.text = list[i].agrresidx;
				
				class5.options[class5.options.length] = op;
			}
			
		});
	}
	
	function changeCreateDivType(selectOb) {
		
		var divcode = $("#divcode")[0];
		var createDivType = selectOb.options[selectOb.selectedIndex].value;
		var url = "${ctx}/office/wordtypeinfo/queryCreateDivId?createDivType=" + createDivType;
		
		clearSelect(divcode);
		
		$.getJSON(url,function(data) {
			
			var list = data.resultList;
			
			var op = new Option();
			op.value = "";
			op.text = "全部";
			divcode.options[divcode.options.length] = op;
				
			for(var i = 0;i < list.length;i++) {
				
				var op = new Option();
				op.value = list[i].divCode;
				op.text = list[i].divName;
				
				divcode.options[divcode.options.length] = op;
			}
		});
	}
	
	function clearSelect(ob) {
		for(var i = ob.options.length - 1;i >= 0;i--) {
			ob.options[i] = null;
		}
	}
	
	function replaceEnd0(str) {
	   while(str.lastIndexOf('0') == str.length-1) {
		   str = str.substring(0,str.length-1);
	   }
	   return str;
	}
	
	function goSearch1() {
		
		var divcode = $("#divcode").val();
		//var agrresidx = $("#agrresidx").val();
		//var index_name = $("#index_name").val();
		var agrresidx = '';
		var index_name = '';
		var reportdateBegin = $("#reportdateBegin").val();
		var reportdateEnd = $("#reportdateEnd").val();
		
		if($("#class1").val() != '') {
			agrresidx = replaceEnd0($("#class1").val());
		}
		if($("#class2").val() != '') {
			agrresidx = replaceEnd0($("#class2").val());
		}
		if($("#class3").val() != '') {
			agrresidx = replaceEnd0($("#class3").val());
		}
		if($("#class4").val() != '') {
			agrresidx = replaceEnd0($("#class4").val());
		}
		if($("#class5").val() != '') {
			agrresidx = replaceEnd0($("#class5").val());
		}
		
		var url = "${ctx}/office/zul/p1420991153029_AutoCreateZul_16?divcode="+divcode+"&agrresidxCode="+agrresidx+"&indexCode="+index_name+"&date="+new Date().getTime();
		$.getJSON(url,function(data) {
			var url = "${ctx_bi}" + data.filename;
			$("#newestRecordsiframe")[0].src = url;
		});
	}
</script>

<table id="table1" style="width:100%;height:100%;border-collapse:collapse;border:none;" cellpadding="0" cellspacing="0">
	<tr>
		<td style="height:30px">
			<button style="display:none" type="button" onclick="goViews()" class="btn btn-info btn-blue btn-sm btn-round">
				<i class="fa fa-eye"></i>
				查看
			</button>
			
			<div style="height:5px">&nbsp;</div>
				
			<div style="margin-top:5px;margin-bottom:5px;">
			
				一级指标:
				<select id="class1" name="class1" style="width:100px" onchange="changeClass1(this)">
					<option value="">-=未选择=-</option>
					<c:forEach var="item1" items="${list1}">
						<option value="${item1.agrres1stClasscode}">${item1.agrres1stClass}</option>
					</c:forEach>
				</select>
				
				二级指标:
				<select id="class2" name="class2" style="width:100px" onchange="changeClass2(this)">
					<option value="">-=未选择=-</option>
				</select>
				
				三级指标:
				<select id="class3" name="class3" style="width:100px" onchange="changeClass3(this)">
					<option value="">-=未选择=-</option>
				</select>
				
				四级指标:
				<select id="class4" name="class4" style="width:100px" onchange="changeClass4(this)">
					<option value="">-=未选择=-</option>
				</select>
				
				指标名称:
				<select id="class5" name="class5" style="width:100px">
					<option value="">-=未选择=-</option>
				</select>
				
				<div style="display:none">
				作物品种:
				<select id="agrresidx" name="agrresidx" style="width:150px" onchange="changeAgrresidx(this)">
					<option value="">-=未选择=-</option>
					<c:forEach var="agrresidxItem" items="${agrresidxList}">
						<option value="${agrresidxItem.agrresidxCode}">${agrresidxItem.agrresidx}</option>
					</c:forEach>
				</select>
				
				指标名称:
				<select id="index_name" name="index_name" style="width:150px">
					<option value="">-=未选择=-</option>
					<c:forEach var="indexNameItem" items="${indexNameList}">
						<option value="${indexNameItem.indexCode}">${indexNameItem.indexName}</option>
					</c:forEach>
				</select>
				</div>
				
				<script>/*changeAgrresidx($("#agrresidx")[0]);*/</script>
	
				<div style="display:none">
				&nbsp;&nbsp;&nbsp;&nbsp;
				时间:
				<input id="reportdateBegin" name="reportdateBegin" class="Wdate" style="height:26px;width:100px" onfocus="WdatePicker({isShowClear:false,readOnly:true})" readonly="readonly"/>
				至
				<input id="reportdateEnd" name="reportdateEnd" class="Wdate" style="height:26px;width:100px" onfocus="WdatePicker({isShowClear:false,readOnly:true})" readonly="readonly"/>
				</div>
				
				&nbsp;&nbsp;&nbsp;&nbsp;
				地区：
				<select id="createDivType" name="createDivType" onchange="changeCreateDivType(this)">
					<option value="1">全部</option>
					<option value="2">地市级</option>
					<option value="3">区县级</option>
				</select>
				<select id="divcode" name="divcode"></select>
				<script>
					changeCreateDivType($("#createDivType")[0]);
				</script>
				<button type="button" style="display:none" onclick="goSearch()" class="btn btn-default btn-info btn-sm btn-round">明细查询</button>
				<button type="button" onclick="goSearch1()" class="btn btn-default btn-info btn-sm btn-round">查询</button>
			</div>
		</td>
	</tr>
	<tr>
		<td style="height:100%">
			<iframe id="newestRecordsiframe" width="100%" height="100%" frameborder="0" scrolling="no"/>
		</td>
	</tr>
</table>
<script>
	function t() {
		$("#newestRecordsiframe").height($(window).height()-200);
	}
	//alert($(".main-container").height());
	setInterval("t()",500);
	
	$(".ajax-loading-overlay")[0].style.display = "none";
</script>