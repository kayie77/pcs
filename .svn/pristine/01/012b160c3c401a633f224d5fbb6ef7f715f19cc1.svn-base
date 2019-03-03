<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<link rel="stylesheet" href="${ctx}/assets/jquery/ui/css/jquery-ui.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jqGrid/css/ui.jqgrid.css" />
<link rel="stylesheet" href="${ctx}/assets/bootstrap/plugins/date-time/css/datepicker.min.css" />
<link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jstree/themes/proton/style.css" />
<script src="${ctx}/assets/js/common.js"></script>

<div class="row">
	<div class="col-xs-12">
		<div class="btn-group">
			<!-- 
			<button id="createTaskBtn" type="button" class="btn btn-default btn-info btn-sm btn-round" data-toggle="modal">
				<i class="fa fa-plus-square"></i>
				新建
			</button>
			<button id="createDivisionBtn" type="button" class="btn btn-default btn-info btn-sm btn-round" data-toggle="modal">
				<i class="fa fa-plus-square"></i>
				选择区域
			</button>
		 	-->
		 	<button onclick="deleteButton()" type="button" class="btn btn-default btn-info btn-sm btn-round" data-toggle="modal">
				<i class="fa fa-trash-o"></i>
				删除
			</button>
		</div>
		<div style="margin-top:5px;margin-bottom:5px;">
			（图释： <img src='${ctx}/assets/images/message/unread.gif'/>未阅读&nbsp;&nbsp;
					<img src='${ctx}/assets/images/message/readed.gif'/>已阅读&nbsp;&nbsp;
					<img src='${ctx}/assets/images/message/isreplay.gif'/>已回复&nbsp;&nbsp;
					<span style="color:red">！</span>重要&nbsp;&nbsp;
					<span style="color:red">？</span>要求回复）
		</div>
		<table id="grid-table"></table>
		<div id="grid-pager"></div>
	</div><!-- /.col -->
</div><!-- /.row -->

<div id="taskModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
	  <div class="modal-content">
    	 <div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<h3 class="modal-title">短信信息</h3>
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

<script type="text/javascript">

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

function operateFormatter(cellvalue, options, rowObject) {
	return "<div style=\"float:left;margin-left:5px;cursor:pointer;\" class=\"ui-pg-div ui-inline-del\" id=\"jDeleteButton_2\" onclick=\"deleteRow('"+rowObject.id+"');\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\" data-original-title=\"删除所选记录\"><span class=\"ui-icon ui-icon-trash\"></span></div>";
}

function statusFormatter (cellvalue, options, rowObject) {
	if(cellvalue == '1') {
		return "已发布";
	} else if(cellvalue == '2') {
		return "未发布";
	} else {
		return "删除";
	}
}

function createdateFormatter (cellvalue, options, rowObject) {
	return new Date(cellvalue).pattern("yyyy-MM-dd");
}


function statusFormatter(cellvalue, options, rowObject) {
	
	var str = "";
	if(rowObject.readflag == '1') {
		if(rowObject.replayflag == '1') {
			str += "<img src='${ctx}/assets/images/message/isreplay.gif'/>&nbsp;&nbsp;";
		} else {
			str += "<img src='${ctx}/assets/images/message/readed.gif'/>&nbsp;&nbsp;";
		}
	} else {
		str += "<img src='${ctx}/assets/images/message/unread.gif'/>&nbsp;&nbsp;";
	}
	if(rowObject.important == '1') {
		str += "<span style='color:red'>！</span>&nbsp;&nbsp;";
	}
	if(rowObject.needreplay == '1') {
		str += "<span style='color:red'>？</span>&nbsp;&nbsp;";
	}
	return str;
}


function titleFormatter(cellvalue, options, rowObject) {
	
	if(cellvalue.length > 40) {
		return "<a href='###' onclick='showRow(\"" +rowObject.id+ "\")'>" +cellvalue.substring(0,40) + "...</a>";
	} else {
		return "<a href='###' onclick='showRow(\"" +rowObject.id+ "\")'>" +cellvalue + "</a>";
	}
}


//编辑选定记录
function showRow(id){
	$.get("${ctx}/office/message/viewMessage?id="+id+"&"+Math.random(1000)+"&canReply=${canReply}", '', function(data){ 
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

var scripts = [null,"${ctx}/assets/jquery/ui/jquery-ui.custom.min.js","${ctx}/assets/jquery/plugins/jquery-form/jquery-form.min.js","${ctx}/assets/jquery/plugins/validation/jquery.validate.min.js","${ctx}/assets/jquery/plugins/validation/localization/messages_zh.min.js","${ctx}/assets/bootstrap/plugins/date-time/bootstrap-datepicker.min.js","${ctx}/assets/bootstrap/plugins/date-time/locales/bootstrap-datepicker.zh-CN.js","${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/jquery.jqGrid.min.js","${ctx}/assets/jquery/plugins/jqGrid/js/i18n/grid.locale-cn.js","${ctx}/assets/js/agrims.js", null]
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
	
	
	var g=$("a[href$='${ctx}/office/message/recvlist']");
	g.closest("li").parent().parent().addClass('active open');
	g.closest("li").addClass('active');
	var f = g.next().get(0);
	
	var grid_selector = "#grid-table";
	var pager_selector = "#grid-pager";
	
	/*,'操作',
		   		{name:'myac',index:'', width:80, fixed:true, sortable:false, resize:false,search:false,formatter:operateFormatter,editable: false}
	*/
	
	$(grid_selector).jqGrid({
		 url:'${ctx}/office/message/queryRecvList',
		 datatype: 'JSON',
		 colNames:['ID','状态', '发送人','主题','接收时间'],
		 colModel:[
		   		{name:'id',index:'id', width:100, hidden:true,editable: true},
		   		{name:'readflag',index:'readflag',width:30,editable: true,formatter:statusFormatter,sortable:false,stype: "select",
		   			searchoptions:{  
                        sopt:["eq"],
                        stype:"select",
                        dataUrl:"${ctx}/office/message/messageStatusSearch"
                    }
		   		},
		   		{name:'div_name',index:'div_name',width:50,editable: true,
		   			searchoptions:{  
                        sopt:["cn"]
                    }
                },
		   		{name:'title',index:'title',width:150,editable: true,formatter:titleFormatter,
		   			searchoptions:{  
                        sopt:["cn"]
                    }
		   		},
		   		{name:'createdate',index:'createdate',width:50,editable: true,search:false}
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
		search: true,
		searchicon : 'ace-icon fa fa-search orange',
		searchtext:"查找",
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
	$.get("${ctx}/office/notice/edit?"+Math.random(1000) , '', function(data){ 
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

//编辑选定记录
function editRow(id){
	$.get("${ctx}/office/notice/edit?id="+id+"&"+Math.random(1000) , '', function(data){ 
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
	  		url : '${ctx}/office/message/delete?id='+id,
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

</script>