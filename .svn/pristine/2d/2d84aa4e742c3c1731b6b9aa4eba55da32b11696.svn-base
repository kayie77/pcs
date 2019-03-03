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
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jqGrid/css/ui.jqgrid.css" />
<script src="${ctx}/assets/js/common.js"></script>
<script type="text/javascript">
	//处理console报错到问题
	if (!window.console || !console.firebug){
	    var names = ["log", "debug", "info", "warn", "error", "assert", "dir", "dirxml", "group", "groupEnd", "time", "timeEnd", "count", "trace", "profile", "profileEnd"];
	    window.console = {};
	    for (var i = 0; i < names.length; ++i) window.console[names[i]] = function() {}
	}
</script>
<script>
	function changeCreateDivType(selectOb) {
		
		var createDivId = $("#createDivId")[0];
		var createDivType = selectOb.options[selectOb.selectedIndex].value;
		var url = "${ctx}/office/wordtypeinfo/queryCreateDivId?createDivType=" + createDivType;
		
		clearSelect(createDivId);
		
		$.getJSON(url,function(data) {
			
			var list = data.resultList;
			
			var op = new Option();
			op.value = "";
			op.text = "全部";
			createDivId.options[createDivId.options.length] = op;
				
			for(var i = 0;i < list.length;i++) {
				
				var op = new Option();
				op.value = list[i].id;
				op.text = list[i].divName;
				
				createDivId.options[createDivId.options.length] = op;
			}
		});
	}
	
	function clearSelect(ob) {
		for(var i = ob.options.length - 1;i >= 0;i--) {
			ob.options[i] = null;
		}
	}
	
	function goSearch() {
		
		var beginYear = $("#beginYear").val();
		var beginMonth = $("#beginMonth").val();
		var endYear = $("#endYear").val();
		var endMonth = $("#endMonth").val();
		var wordTypeId = $("#wordTypeId").val();
		var createDivId = $("#createDivId").val();
		var status = $("#status").val();
		
		var postData = {"beginYear":beginYear,
						"beginMonth":beginMonth,
						"endYear":endYear,
						"endMonth":endMonth,
						"wordTypeId":wordTypeId,
						"createDivId":createDivId,
						"status":status
						};

	    $("#grid-table").jqGrid('setGridParam',{  
	        datatype:'json',  
	        postData:postData
	    }).trigger("reloadGrid");
	    
	}
</script>

<div class="row">
	<div class="col-xs-12">
	    <div class="btn-group">
			<button id="createTaskBtn" type="button" class="btn btn-default btn-info btn-sm btn-round" data-toggle="modal">
				<i class="fa fa-plus-square"></i>
				新建
			</button>
			<button onclick="editButton()" type="button" class="btn btn-default btn-info btn-sm btn-round" data-toggle="modal" style="display:none">
				<i class="fa fa-pencil"></i>
				编辑
			</button>
			<!-- 
			 -->
			<button onclick="deleteButton()" type="button" class="btn btn-default btn-info btn-sm btn-round" data-toggle="modal">
				<i class="fa fa-trash-o"></i>
				删除
			</button>
			<c:if test="${!isArea}">
			</c:if>
		</div>
		<div class="space-6"></div>
		<form class="form-horizontal" role="form">
			时间：
			<select id="beginYear" name="beginYear">
				<c:forEach var="year" items="${yearList}">
					<option value="${year}" <c:if test="${currentYear == year}"> selected </c:if>>${year}</option>
				</c:forEach>
			</select>年
			<select id="beginMonth" name="beginYear">
				<c:forEach var="month" items="${monthList}">
					<option value="${month}" <c:if test="${currentMonth == month}"> selected </c:if>>${month}</option>
				</c:forEach>
			</select>月
			&nbsp;
			至
			<select id="endYear" name="endYear">
				<c:forEach var="year" items="${yearList}">
					<option value="${year}" <c:if test="${currentYear == year}"> selected </c:if>>${year}</option>
				</c:forEach>
			</select>年
			<select id="endMonth" name="endYear">
				<c:forEach var="month" items="${monthList}">
					<option value="${month}" <c:if test="${currentMonth == month}"> selected </c:if>>${month}</option>
				</c:forEach>
			</select>月
			&nbsp;
			文字类型：
			<select id="wordTypeId" name="wordTypeId" style="width:200px">
				<option value="">全部</option>
				<c:forEach var="wordType" items="${wordTypeList}">
					<c:if test="${wordType.showMonth != null}">
						<option value="">${wordType.showMonth}</option>
					</c:if>
					<c:if test="${wordType.showMonth == null}">
						<option value="${wordType.id}">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${wordType.title}</option>
					</c:if>
				</c:forEach>
			</select>
			来源：
			<select id="createDivType" name="createDivType" onchange="changeCreateDivType(this)">
				<option value="1">全部</option>
				<option value="2">地市级</option>
				<option value="3">区县级</option>
			</select>
			<select id="createDivId" name="createDivId"></select>
			<script>
				changeCreateDivType($("#createDivType")[0]);
			</script>
			状态：
			<select id="status" name="status">
				<option value="">全部</option>
				<option value="2">已采用</option>
				<option value="1">已浏览</option>
				<option value="0">未浏览</option>
			</select>
			&nbsp;&nbsp;
			<button type="button" onclick="goSearch()" class="btn btn-default btn-info btn-minier btn-round">
			     <i class="ace-icon glyphicon glyphicon-search bigger-150 icon-only"></i>
			</button>
		</form>
		<div class="space-6"></div>
		<table id="grid-table"></table>
		<div id="grid-pager"></div>
	</div><!-- /.col -->
</div><!-- /.row -->

<div id="taskModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
	  <div class="modal-content">
    	 <div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<h3 class="modal-title">文字列表</h3>
    	 </div>
    	 <div class="modal-body"></div>
    	 <div class="modal-footer">
         	<button type="button" class="btn btn-default btn-info btn-sm btn-round" data-dismiss="modal" aria-hidden="true">
        		<i class="icon-times"></i>         			
        		关闭
       		</button>
      		<button type="submit" class="btn btn-default btn-info btn-sm btn-round">
          		<i class="icon-save"></i> 
        	  	保存
        	</button>
        </div>
  	 </div>
  </div>
</div>

<c:if test="${isManager != 'true'}">
	<script>
		function operateFormatter(cellvalue, options, rowObject) {
			return "<div style=\"float:left;cursor:pointer;\" class=\"ui-pg-div ui-inline-edit\" id=\"jEditButton_1\" onclick=\"editRow('"+rowObject.id+"');\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\" data-original-title=\"编辑所选记录\"><span class=\"ui-icon ui-icon-pencil\"></span></div><div style=\"float:left;margin-left:5px;cursor:pointer;\" class=\"ui-pg-div ui-inline-del\" id=\"jDeleteButton_2\" onclick=\"deleteRow('"+rowObject.id+"');\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\" data-original-title=\"删除所选记录\"><span class=\"ui-icon ui-icon-trash\"></span></div>";
		}
	</script>
</c:if>
<c:if test="${isManager == 'true'}">
	<script>
		function operateFormatter(cellvalue, options, rowObject) {
			return "<div style=\"margin-left:2px;\"><div style=\"float:left;cursor:pointer;\" class=\"ui-pg-div ui-inline-edit\" id=\"jEditButton_1\" onclick=\"showRow('"+rowObject.id+"');\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\" data-original-title=\"浏览\"><span class=\"ui-icon ace-icon fa fa-search-plus grey\" style='width:50px;'>浏览</span></div></div>";
		}
	</script>
</c:if>


<script type="text/javascript">

function goViews() {
	var selectedIds = $("#grid-table").jqGrid('getGridParam','selarrrow');
	if(selectedIds != null && selectedIds.length != 1) {
		bootbox.alert("请选择一条记录!");
	} else {
		showRow(selectedIds[0]);
	}
}

function editButton() {
	var selectedIds = $("#grid-table").jqGrid('getGridParam','selarrrow');
	if(selectedIds != null && selectedIds.length != 1) {
		bootbox.alert("请选择一条记录!");
	} else {
		var url11 = "${ctx}/office/wordtypeinfo/isSelfWordtypeInfo?id="+selectedIds[0]+"&date="+new Date().getTime();
		$.getJSON(url11,function(data) {
			if(data.isSelfWordtypeInfo == '1') {
				editRow(selectedIds[0]);
			} else {
				showRow(selectedIds[0]);
			}
		});
	}
}

function deleteButton() {
	var selectedIds = $("#grid-table").jqGrid('getGridParam','selarrrow');
	if(selectedIds != null && selectedIds.length != 0) {
		bootbox.confirm("确定删除吗？",function(result) {
			if(result) {	

				var id = '';
				for(var i = 0;i < selectedIds.length;i++) {
					id += selectedIds[i] + ",";
				}
				if(id != '') {
					id = id.substring(0,id.length-1);
				}
				deleteRow(id);
			}
		});
	} else {
		bootbox.alert("请选择一条记录!");
	}
}

function uploadflagFormatter(cellvalue, options, rowObject) {
	if(cellvalue == '1') {
		return "<span class='label label-success arrowed arrowed-right'>已上报</span>";
	} else {
		return "<span class='label label-warning arrowed arrowed-right'>未上报</span>";
	}
}

function readflagFormatter(cellvalue, options, rowObject) {
	if(cellvalue == '1') {
		return "<span class='label label-warning arrowed arrowed-right'>已浏览</span>";
	} if(cellvalue == '2') {
		return "<span class='label label-success arrowed arrowed-right'>已采用</span>";
	} else {
		return "<span class='label label-danger arrowed arrowed-right'>未浏览</span>";
	}
}

function wtiTitleFormatter(cellvalue, options, rowObject) {
	return "<a href='###' onclick=\"showRow('"+rowObject.id+"')\">"+cellvalue+"</a>";
}

function editFormatter(cellvalue, options, rowObject) {
	return "<div style=\"float:left;cursor:pointer;\" class=\"ui-pg-div ui-inline-edit\" id=\"jEditButton_1\" onclick=\"editRow('"+rowObject.id+"');\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\" data-original-title=\"编辑所选记录\"><span class=\"ui-icon ui-icon-pencil\"></span></div>";
}

var scripts = [null,"${ctx}/assets/jquery/ui/jquery-ui.custom.min.js","${ctx}/assets/jquery/plugins/jquery-form/jquery-form.min.js","${ctx}/assets/jquery/plugins/validation/jquery.validate.min.js","${ctx}/assets/jquery/plugins/validation/localization/messages_zh.min.js","${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/jquery.jqGrid.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/i18n/grid.locale-cn.js","${ctx}/assets/js/agrims.js", null]
$('.page-content-area').ace_ajax('loadScripts', scripts, function() {
  jQuery(function($) {
	var ctx='${ctx}';
	
	//resize to fit page size
	$(window).on('resize.jqGrid', function () {
		$(grid_selector).jqGrid( 'setGridWidth', $(".page-content").width() );
		$("#grid-table").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
    });
	
	//resize on sidebar collapse/expand
	var parecolumn = $(grid_selector).closest('[class*="col-"]');
	$(document).on('settings.ace.jqGrid' , function(ev, evename, collapsed) {
		if( evename === 'sidebar_collapsed' || evename === 'main_container_fixed' ) {
			//setTimeout is for webkit only to give time for DOM changes and then redraw!!!
			setTimeout(function() {
				$(grid_selector).jqGrid( 'setGridWidth', parecolumn.width() );
				$("#grid-table").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
			}, 0);
		}
    });
	
	
	var g=$("a[href$='${ctx}/office/message']");
	g.closest("li").parent().parent().addClass('active open');
	g.closest("li").addClass('active');
	var f = g.next().get(0);
	
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	
	
	/*,
	searchoptions:{  
                 sopt:["cn"]
             },'操作',
		   		{name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,search:false,formatter:operateFormatter,editable: false}
*/
	
	function rowspan(col) {
		var t = document.getElementById("grid-table");
		for(var i = 0;i < t.rows.length - 1;i++) {
			var rowSpan = 1;
			for(var j = i+1;j < t.rows.length;j++) {
				if(t.rows[i].cells[col].id == t.rows[j].cells[col].id) {
					t.rows[i].cells[col].rowSpan = ++rowSpan;
					t.rows[j].cells[col].style.display = "none";
				} else {
					i = j-1;
					break;
				}
			}
		}
	}
	
	$(grid_selector).jqGrid({
		 url:'${ctx}/office/wordtypeifno/queryWtiList',
		 datatype: 'JSON',
		 colNames:['ID','编号','文字标题','文字类型','来源','文字状态','填报日期','得分','操作'],
		 colModel:[
		   		{name:'id',index:'id', width:100, hidden:true,editable: true},
		   		{name:'num',index:'num', width:50, editable: true,search:false},
		   		{name:'wtiTitle',index:'wtiTitle', width:100, editable: true,search:false,formatter:wtiTitleFormatter},
		   		{name:'wtTitle',index:'wtTitle', width:100, editable: true,search:false,
		   			cellattr : function(rowId,tv,rawObject,cm,rdata) {
		   		    	return " id='wtId"+rawObject.wtId + "' ";
		   			}
		   		},
		   		{name:'createDiv',index:'createDiv', width:50, editable: true,search:false,
		   			cellattr : function(rowId,tv,rawObject,cm,rdata) {
		   		    	return " id='divId"+rawObject.divId + "' ";
		   			}
		   		},
		   		{name:'readflag',index:'readflag', width:40, editable: true,formatter:readflagFormatter,search:false},
		   		{name:'createDate',index:'createDate', width:60, editable: true,search:false},
		   		{name:'score',index:'score', width:30, editable: true,search:false},
		   		{name:'beginTime',index:'beginTime',width:30,editable: true,search:false,formatter:editFormatter}
		 ],
		 jsonReader : {
				root: "rows",
	            page: "page",
	            total: "total",
	            records: "records",
	            repeatitems: false,
	            cell: "cell",
	            id: "id"
	     },
	     editurl:'${actionUrl}',
	     loadonce:false,
		 rownumbers: true,
		 rowNum:20,
		 rowList:[10,15,20],
		 pager : pager_selector,
		 sortname:'createDate',
		 sortorder:'desc',
		 height:'100%',
		 autowidth: true,
		 altRows: true,
		 viewrecords: true,
		 multiselect: true,		 
		 loadComplete : function() {
			 var table = this;
				setTimeout(function(){
					styleCheckbox(table);
					
					updateActionIcons(table);
					updatePagerIcons(table);
					enableTooltips(table);
					
					//rowspan(5);
					//rowspan(6);
				}, 0);
		}
    });
		
$(window).triggerHandler('resize.jqGrid');//trigger window resize to make the grid get the correct size
	
	//enable search/filter toolbar
	//jQuery(grid_selector).jqGrid('filterToolbar',{defaultSearch:true,stringResult:true})
	//jQuery(grid_selector).filterToolbar({});
	
	//switch element when editing inline
	function aceSwitch( cellvalue, options, cell ) {
		setTimeout(function(){
			$(cell) .find('input[type=checkbox]')
				.addClass('ace ace-switch ace-switch-5')
				.after('<span class="lbl"></span>');
		}, 0);
	}
	//enable datepicker
	function pickDate( cellvalue, options, cell ) {
		setTimeout(function(){
			$(cell) .find('input[type=text]')
					.datepicker({format:'yyyy-mm-dd' , autoclose:true}); 
		}, 0);
	}
	
	//navButtons
	$(grid_selector).jqGrid('navGrid',pager_selector,
	  {
		edit: false,
		editicon : 'ace-icon fa fa-pencil blue',
		edittext:"编辑",
		add: false,
		addicon : 'ace-icon fa fa-plus-circle purple',
		del: false,
		delicon : 'ace-icon fa fa-trash-o red',
		/*
		search: true,
		searchicon : 'ace-icon fa fa-search orange',
		searchtext:"查找",
		*/
		refresh: true,
		refreshicon : 'ace-icon fa fa-refresh green',
		refreshtext:"刷新",
		view: false,
		viewicon : 'ace-icon fa fa-search-plus grey',
		viewtext:"查看"
		},
		{
			//edit record form
			//closeAfterEdit: true,
			recreateForm: true,
			beforeShowForm : function(e) {
				var form = $(e[0]);
				form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
				style_edit_form(form);
			}
		},
		{
			//new record form
			closeAfterAdd: true,
			recreateForm: true,
			viewPagerButtons: false,
			beforeShowForm : function(e) {
				var form = $(e[0]);
				form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
				style_edit_form(form);
			}
		},
		{
			//delete record form
			recreateForm: true,
			beforeShowForm : function(e) {
				var form = $(e[0]);
				if(form.data('styled')) return false;
				
				form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
				style_delete_form(form);
				
				form.data('styled', true);
			},
			onClick : function(e) {
				alert(1);
			}
		},
		{
			//search form
			recreateForm: true,
			afterShowSearch: function(e){
				var form = $(e[0]);
				form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
				style_search_form(form);
			},
			afterRedraw: function(){
				style_search_filters($(this));
			}
			,
			multipleSearch: false,
			/**
			multipleGroup:true,
			showQuery: true
			*/
		},
		{
			//view record form
			recreateForm: true,
			beforeShowForm: function(e){
				var form = $(e[0]);
				form.closest('.ui-jqdialog').find('.ui-jqdialog-title').wrap('<div class="widget-header" />')
			}
		}
	);
	
	function style_edit_form(form) {
		//enable datepicker on "sdate" field and switches for "stock" field
		form.find('input[name=sdate]').datepicker({format:'yyyy-mm-dd' , autoclose:true})
			.end().find('input[name=stock]')
				.addClass('ace ace-switch ace-switch-5').after('<span class="lbl"></span>');
				   //don't wrap inside a label element, the checkbox value won't be submitted (POST'ed)
				  //.addClass('ace ace-switch ace-switch-5').wrap('<label class="inline" />').after('<span class="lbl"></span>');

		//update buttons classes
		var buttons = form.next().find('.EditButton .fm-button');
		buttons.addClass('btn btn-sm').find('[class*="-icon"]').hide();//ui-icon, s-icon
		buttons.eq(0).addClass('btn-primary').prepend('<i class="ace-icon fa fa-check"></i>');
		buttons.eq(1).prepend('<i class="ace-icon fa fa-times"></i>')
		
		buttons = form.next().find('.navButton a');
		buttons.find('.ui-icon').hide();
		buttons.eq(0).append('<i class="ace-icon fa fa-chevron-left"></i>');
		buttons.eq(1).append('<i class="ace-icon fa fa-chevron-right"></i>');		
	}
	
	function style_delete_form(form) {
		var buttons = form.next().find('.EditButton .fm-button');
		buttons.addClass('btn btn-sm btn-white btn-round').find('[class*="-icon"]').hide();//ui-icon, s-icon
		buttons.eq(0).addClass('btn-danger').prepend('<i class="ace-icon fa fa-trash-o"></i>');
		buttons.eq(1).addClass('btn-default').prepend('<i class="ace-icon fa fa-times"></i>')
	}
	
	function style_search_filters(form) {
		form.find('.delete-rule').val('X');
		form.find('.add-rule').addClass('btn btn-xs btn-primary');
		form.find('.add-group').addClass('btn btn-xs btn-success');
		form.find('.delete-group').addClass('btn btn-xs btn-danger');
	}
	
	function style_search_form(form) {
		var dialog = form.closest('.ui-jqdialog');
		var buttons = dialog.find('.EditTable')
		buttons.find('.EditButton a[id*="_reset"]').addClass('btn btn-sm btn-info').find('.ui-icon').attr('class', 'ace-icon fa fa-retweet');
		buttons.find('.EditButton a[id*="_query"]').addClass('btn btn-sm btn-inverse').find('.ui-icon').attr('class', 'ace-icon fa fa-comment-o');
		buttons.find('.EditButton a[id*="_search"]').addClass('btn btn-sm btn-purple').find('.ui-icon').attr('class', 'ace-icon fa fa-search');
	}
	
	function beforeDeleteCallback(e) {
		var form = $(e[0]);
		if(form.data('styled')) return false;
		
		form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
		style_delete_form(form);
		
		form.data('styled', true);
	}
	
	function beforeEditCallback(e) {
		var form = $(e[0]);
		form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar').wrapInner('<div class="widget-header" />')
		style_edit_form(form);
	}
	
	
	
	//it causes some flicker when reloading or navigating grid
	//it may be possible to have some custom formatter to do this as the grid is being created to prevent this
	//or go back to default browser checkbox styles for the grid
	function styleCheckbox(table) {
	/**
		$(table).find('input:checkbox').addClass('ace')
		.wrap('<label />')
		.after('<span class="lbl align-top" />')
	
	
		$('.ui-jqgrid-labels th[id*="_cb"]:first-child')
		.find('input.cbox[type=checkbox]').addClass('ace')
		.wrap('<label />').after('<span class="lbl align-top" />');
	*/
	}
	
	
	//unlike navButtons icons, action icons in rows seem to be hard-coded
	//you can change them like this in here if you want
	function updateActionIcons(table) {
		
		var replacement = 
		{
			'ui-ace-icon fa fa-pencil' : 'ace-icon fa fa-pencil blue',
			'ui-ace-icon fa fa-trash-o' : 'ace-icon fa fa-trash-o red',
			'ui-icon-disk' : 'ace-icon fa fa-check green',
			'ui-icon-cancel' : 'ace-icon fa fa-times red'
		};
		$(table).find('.ui-pg-div span.ui-icon').each(function(){
			var icon = $(this);
			var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
			if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
		})
		
	}
	
	//replace icons with FontAwesome icons like above
	function updatePagerIcons(table) {
		var replacement = 
		{
			'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
			'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
			'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
			'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
		};
		$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
			var icon = $(this);
			var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
			
			if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
		})
	}
	
	function enableTooltips(table) {
		$('.navtable .ui-pg-button').tooltip({container:'body'});
		$(table).find('.ui-pg-div').tooltip({container:'body'});
	}
	
	//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');
	
	$(document).on('ajaxloadstart', function(e) {
		$(grid_selector).jqGrid('GridUnload');
		$('.ui-jqdialog').remove();
	});
	
	$("#createTaskBtn").click(function(e){
		createRow();
		e.preventDefault();
	});
	
	$("#createDivisionBtn").click(function(e){
		
		e.preventDefault();
	});
	
	
  });
});


//新建记录
function createRow(){

	var wtmonth = $("#wtMonth").val();
	$.get("${ctx}/office/wordtypeinfo/edit?wtyear=${wtYear}&wtmonth="+wtmonth+"&"+Math.random(1000) , '', function(data){ 
		var modal = $('#taskModal');
		modal.html(data);
		modal.modal({show:true,backdrop:false}).on("hidden", function(){
	    	$(this).remove();
		});
        var form = modal.find('form:eq(0)');
        form.validate({	
			rules : {
				title : {
					required : true,
					minlength : 1,
					maxlength : 200
				},
				num : {
					required : true,
					minlength : 1,
					maxlength : 10
				},
				content : {
					required : true,
					minlength : 1,
					maxlength : 2000
				},
				score : {
					required : true,
					minlength : 1,
					maxlength : 5
				}
			},
			//errorElement: "span",
			errorPlacement: function(error, element) {
				console.log(error);
			    error.insertAfter(element.parent());
			},
			highlight: function(element) {
			    //$(element).closest('.form-group').removeClass('success').addClass('error');
			},
			submitHandler: function (form) {
				$(form).ajaxSubmit({
					//target: "#result",
					type:"POST",
					dataType:"json",
					success:function(data,status) {
			            console.log(data)
			            if(data.success===true) {
			            	$("#grid-table").trigger('reloadGrid');
			            	modal.modal('hide');
			            	bootbox.dialog({
	 							message: '操作成功！',
	 							buttons: 			
	 							{
	 								"success" :
	 								 {
	 									"label" : "<i class='ace-icon fa fa-check'></i>确定",
	 									"className" : "btn-sm btn-success",
										"callback": function() {	
										}
	 								}
	 							}
	 						});
			            } else {
			            	modal.modal('hide');
			            	 bootbox.dialog({
									message: '操作失败！',
									buttons: 			
									{
										"danger" :
										 {
											"label" : "<i class='ace-icon fa fa-exclamation-triangle'></i>确定",
											"className" : "btn-sm btn-success",
											"callback": function() {	
											}
										}
									}
							});
			            }
			        }  
				});
			},
		});
    });    
}

function tocheck(id,f) {
	$("#"+id).find("input[type=checkbox]")[0].checked = f;
}

//编辑选定记录
function editRow(id){
	
	setTimeout("tocheck('" +id+ "'," +$("#"+id).find("input[type=checkbox]")[0].checked+ ")",500);
	
	$.get("${ctx}/office/wordtypeinfo/edit?id="+id+"&"+Math.random(1000) , '', function(data){ 
		var modal = $('#taskModal');
		modal.html(data);
		modal.modal({show:true,backdrop:false}).on("hidden", function(){
	    	$(this).remove();
		});
      var form = modal.find('form:eq(0)');
      form.validate({	
			rules : {
				title : {
					required : true,
					minlength : 1,
					maxlength : 100
				},
				content : {
					required : true,
					minlength : 1,
					maxlength : 2000
				},
				div_code : {
					required : true,
					minlength : 6,
					maxlength : 6
				}
			},
			//errorElement: "span",
			errorPlacement: function(error, element) {
				console.log(error);
			    error.insertAfter(element.parent());
			},
			highlight: function(element) {
			    //$(element).closest('.form-group').removeClass('success').addClass('error');
			},
			submitHandler: function (form) {
				$(form).ajaxSubmit({
					//target: "#result",
					type:"POST",
					dataType:"json",
					success:function(data,status) {
			            console.log(data)
			            if(data.success===true) {
			            	$("#grid-table").trigger('reloadGrid');
			            	modal.modal('hide');
			            	bootbox.dialog({
	 							message: '操作成功！',
	 							buttons: 			
	 							{
	 								"success" :
	 								 {
	 									"label" : "<i class='ace-icon fa fa-check'></i>确定",
	 									"className" : "btn-sm btn-success",
										"callback": function() {	
										}
	 								}
	 							}
	 						});
			            } else {
			            	modal.modal('hide');
			            	 bootbox.dialog({
									message: '操作失败！',
									buttons: 			
									{
										"danger" :
										 {
											"label" : "<i class='ace-icon fa fa-exclamation-triangle'></i>确定",
											"className" : "btn-sm btn-success",
											"callback": function() {	
											}
										}
									}
							});
			            }
			        }  
				});
			},
		});
  });    
}




//编辑选定记录
function showRow(id){
	$.get("${ctx}/office/wordtypeinfo/viewWordTypeInfo?id="+id+"&"+Math.random(1000) , '', function(data){ 
		var modal = $('#taskModal');
		modal.html(data);
		modal.modal({show:true,backdrop:false}).on("hidden", function(){
	    	$(this).remove();
		});
        var form = modal.find('form:eq(0)');
        form.validate({	
			rules : {
				title : {
					required : true,
					minlength : 1,
					maxlength : 100
				},
				content : {
					required : true,
					minlength : 1,
					maxlength : 2000
				},
				div_code : {
					required : true,
					minlength : 6,
					maxlength : 6
				}
			},
			//errorElement: "span",
			errorPlacement: function(error, element) {
				console.log(error);
			    error.insertAfter(element.parent());
			},
			highlight: function(element) {
			    //$(element).closest('.form-group').removeClass('success').addClass('error');
			},
			submitHandler: function (form) {
				$(form).ajaxSubmit({
					//target: "#result",
					type:"POST",
					dataType:"json",
					success:function(data,status) {
			            console.log(data)
			            if(data.success===true) {
			            	$("#grid-table").trigger('reloadGrid');
			            	modal.modal('hide');
			            	bootbox.dialog({
	 							message: data.message,
	 							buttons: 			
	 							{
	 								"success" :
	 								 {
	 									"label" : "<i class='ace-icon fa fa-check'></i>确定",
	 									"className" : "btn-sm btn-success",
										"callback": function() {	
										}
	 								}
	 							}
	 						});
			            } else {
			            	modal.modal('hide');
			            	 bootbox.dialog({
									message: data.message,
									buttons: 			
									{
										"danger" :
										 {
											"label" : "<i class='ace-icon fa fa-exclamation-triangle'></i>确定",
											"className" : "btn-sm btn-success",
											"callback": function() {	
											}
										}
									}
							});
			            }
			        }  
				});
			},
		});
    });    
}


function deleteRow(id){
     $.ajax({
	  		type : 'POST',
	  	    dataType : 'JSON',
	  		url : '${ctx}/office/wordtypeinfo/delete?id='+id,
	  		success : function(data) {
	  			$("#grid-table").trigger('reloadGrid');
	  			bootbox.dialog({
					message: data.message,
					buttons: 			
					{
						"success" :
						 {
							"label" : "<i class='ace-icon fa fa-check'></i>确定",
							"className" : "btn-sm btn-success"
						}
					}
				});
	        },
  });
}

$("#grid-table").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
</script>