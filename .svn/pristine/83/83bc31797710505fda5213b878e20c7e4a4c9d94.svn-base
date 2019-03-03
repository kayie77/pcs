<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=11; IE=10; IE=9; IE=8; IE=Edge;chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<meta http-equiv="content-language" content="zh-CN" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta http-equiv="keywords" content="平台,业务" />
<meta http-equiv="description" content="通用业务平台" />
<meta name="author" content="Yunforge" />
<title><spring:message code="app.title" /></title>
<link rel="icon" href="${ctx}/favicon.ico" />
<link rel="stylesheet" href="${ctx}/assets/css/pace.min.css" />
<script data-pace-options='{ "ajax": true, "document": true, "eventLag": false, "elements": false }' src="${ctx}/assets/js/pace.min.js"></script>

<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/assets/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/assets/css/font-awesome.min.css" />

<!-- 文本字体 -->
<link rel="stylesheet" href="${ctx}/assets/css/ace-fonts.min.css" />

<!-- 系统样式 -->
<link rel="stylesheet" href="${ctx}/assets/css/ace.min.css"  class="ace-main-stylesheet" id="main-ace-style" />


<!--[if lte IE 9]>
<link rel="stylesheet" href="${ctx}/assets/css/ace-part2.min.css" class="ace-main-stylesheet"/>
<![endif]-->

<!--[if lte IE 9]>
<link rel="stylesheet" href="${ctx}/assets/css/ace-ie.min.css" />
<![endif]-->
<script>
	function selectMenuItem(ob) {
		$("#menuLi").find("li").removeClass("active");
		$("#menuLi").find("i").removeClass("fa-caret-right");
		
		$(ob).addClass("active");
		$(ob).find("i").addClass("fa-caret-right");
	}
</script>
<style type="text/css">
 a.navbar-brand{font-family:"Microsoft YaHei"!important;font-weight:bold!important;float:left;letter-spacing:4px;}
 .ace-settings-container{position:absolute;right:20px;top:auto;z-index:12}
 .breadcrumbs .ace-settings-container {top:3px;}
 .btn.ace-settings-btn {
	float: left;
	display: block;
	width: 30px !important;
	text-align: center;
	border-radius: 6px 6px 6px 6px !important;
	opacity: 0.55;
	vertical-align: top;
	margin: 0;
 }

 .btn.ace-settings-btn:hover,.btn.ace-settings-btn.open {
	opacity: 1;
 }
</style>

<!--系统设置句柄 -->
<script src="${ctx}/assets/js/ace-extra.js"></script>

<!-- IE8 使用的HTML5shiv和Respond.js以支持HTML5元素和媒体查询 -->

<!--[if lte IE 8]>
<script src="${ctx}/assets/js/html5shiv.min.js"></script>
<script src="${ctx}/assets/js/respond.min.js"></script>
<![endif]-->
</head>
<body class="no-skin">
    <div id="navbar" class="navbar navbar-fixed-top">
         <script type="text/javascript">
			try{ace.settings.check('navbar' , 'fixed')}catch(e){}
		</script>
        <div class="navbar-container" id="navbar-container">
            <!-- #section:basics/sidebar.mobile.toggle -->
		   <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
				<span class="sr-only">左侧导航开关</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
		    </button>
           <div class="navbar-header pull-left">
				<a href="#" class="navbar-brand">
					<i class="glyphicon glyphicon-grain"></i>
					<spring:message code="app.title" />	
				</a><!-- /.brand -->
		   </div><!-- /.navbar-header -->
           <div class="navbar-buttons navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
				
				
					<li class="blue">
						<a data-toggle="dropdown" class="dropdown-toggle" href="${ctx}/main" onclick="javascript:location.href='${ctx}/main'">
							<i class="ace-icon fa fa-arrow-left"></i>
							<span class="">返回</span>
						</a>
					</li>
				
				
					<li class="blue">
						<a data-toggle="dropdown" class="dropdown-toggle" href="#">
							<i class="ace-icon fa fa-tasks"></i>
							<span class="badge badge-grey">${fn:length(taskDivList)}</span>
						</a>
						<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header">
							    <i class="ace-icon fa fa-check"></i>
							    ${fn:length(taskDivList)}个待办任务
							</li>
							<li class="dropdown-content">
								<ul class="dropdown-menu dropdown-navbar">
						  		<c:set var="urls1" value=""/>
						  		<c:choose>
									<c:when test="${divCode == '450000'}">
										<c:set var="urls1" value="${ctx}/statistics#page/office/task/taskAudit"/>
									</c:when>
									<c:otherwise>
										<c:set var="urls1" value="${ctx}/statistics#page/office/task/wait"/>
									</c:otherwise>
								</c:choose>
							
								<c:forEach var="taskDivItem" items="${taskDivList}" varStatus="i">
									<c:if test="${i.index < 5}">
										<c:set var="urls" value=""/>
								  		<c:choose>
											<c:when test="${divCode == '450000'}">
												<c:set var="urls" value="${ctx}/statistics#page/office/task/taskReportAudit?id=${taskDivItem.taskDivId}"/>
											</c:when>
											<c:otherwise>
												<c:set var="urls" value="${ctx}/statistics#page/office/task/viewInput/${taskDivItem.id}"/>
											</c:otherwise>
										</c:choose>
										<li>
											<a href="${urls}">
												<div class="clearfix">
													<span class="pull-left">${taskDivItem.taskName}</span>
										    	</div>
									    	</a>
										</li>
									</c:if>
								</c:forEach>
								</ul>
							</li>
							<li class="dropdown-footer">
								<a href="${urls1}">
								           查看更多任务..
								  <i class="ace-icon fa fa-arrow-right"></i>
								</a>
							</li>
							
						</ul>
					</li>
					<li class="blue">
						<a data-toggle="dropdown" class="dropdown-toggle" href="#">
							<i class="ace-icon fa fa-bell icon-animated-bell"></i>
							<span class="badge badge-important">${fn:length(noticeList)}</span>
						</a>
						<ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header">
								<i class="ace-icon fa fa-exclamation-triangle"></i>
								${fn:length(noticeList)}个未读通知
							</li>
							<li class="dropdown-content">
								<ul class="dropdown-menu dropdown-navbar navbar-pink">
								<c:forEach var="noticeItem" items="${noticeList}" varStatus="i">
									<c:if test="${i.index < 5}">
										<li>
											<a href="#" onclick="showRowByIndexPage('${noticeItem.id}','notice')">
												<i class="btn btn-xs btn-primary fa fa-user"></i>
												<c:choose>
													<c:when test="${fn:length(noticeItem.title) > 15}">
														${fn:substring(noticeItem.title, 0, 15)}..
													</c:when>
													<c:otherwise>
														${noticeItem.title}
													</c:otherwise>
												</c:choose>
											</a>
										</li>
									</c:if>
								</c:forEach>
							   </ul>
							</li>
							<li class="dropdown-footer">
								<a href="${ctx}/statistics#page/office/notice/noticeViewList">
									查看所有通知
									<i class="ace-icon fa fa-arrow-right"></i>
								</a>
							</li>
						</ul>
					</li>
					<li class="blue">
						<a data-toggle="dropdown" class="dropdown-toggle" href="#">
							<i class="ace-icon fa fa-envelope icon-animated-vertical"></i>
							<span class="badge badge-success">${fn:length(messageList)}</span>
						</a>
						<ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
							<li class="dropdown-header">
								<i class="ace-icon fa fa-envelope-o"></i>
								${fn:length(messageList)}个未读信息
							</li>
							<li class="dropdown-content">
							   <ul class="dropdown-menu dropdown-navbar">
							   		<c:forEach var="messageItem" items="${messageList}" varStatus="i">
										<c:if test="${i.index < 5}">
									   		<li onclick="showRowByIndexPage('${messageItem.id}','message')">
												<a href="#">
														<span class="msg-body">
															<span class="msg-title">
																<span class="blue">${messageItem.message.createDiv.divName}:</span>
																${messageItem.message.title}
															</span>
														</span>
													</a>
											</li>
										</c:if>
									</c:forEach>
								</ul>
							</li>					
							<li class="dropdown-footer">
								<a href="${ctx}/statistics#page/office/message/recvlist">
									查看所有信息
									<i class="ace-icon fa fa-arrow-right"></i>
								</a>
							</li>
						</ul>
					</li>
					<!-- #section:basics/navbar.user_menu -->
					<li class="light-blue">
						<a data-toggle="dropdown" href="#" class="dropdown-toggle">
							<c:choose>
								<c:when test="${userSession.avatar == null}">
									<img class="nav-user-photo" src="${ctx}/assets/avatars/avatar80x80.jpg" alt="${userSession.fullName}" />
								</c:when>
								<c:otherwise>
									<img class="nav-user-photo" src="${ctx}/office/file/download?filename=${userSession.avatar}&type=avatar" alt="${userSession.fullName}" />
								</c:otherwise>
							</c:choose>
							<span class="user-info">
								${userSession.fullName}
							</span>
							<i class="ace-icon fa fa-caret-down"></i>
						</a>
						<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
						    <li>
								<a data-url="page/profile/profile" href="${ctx}/statistics#page/profile/profile">
									<i class="ace-icon fa fa-user"></i>
									帐户
								</a>
							</li>
							<li class="divider"></li>
							<li>
								<a href="${ctx}/logout">
									<i class="ace-icon fa fa-power-off"></i>
									退出
								</a>
							</li>
						</ul>
					</li>
				</ul><!-- /.ace-nav -->
			</div><!-- /.navbar-header -->
		</div><!-- /.container -->
	</div>
       
	<div class="main-container" id="main-container">
	    <script type="text/javascript">
	     	try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>	
		<!-- #section:basics/sidebar -->
	    <div id="sidebar" class="sidebar responsive">
		     <script type="text/javascript">
		        try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
			 </script>
		     <div class="sidebar-shortcuts" id="sidebar-shortcuts">
				  <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
						<a data-url="page/desktop" href="${ctx}/statistics#page/desktop" class="btn btn-success" data-rel="tooltip" title="我的工作台" style="display:none">
							<i class="ace-icon fa fa-home"></i>
						</a>
						<a data-url="page/office/notice/noticeViewList" href="${ctx}/statistics#page/office/notice/noticeViewList" class="btn btn-warning" data-rel="tooltip" title="通知信息">
							<i class="ace-icon fa fa-bell"></i>
						</a>
						<!-- #section:basics/sidebar.layout.shortcuts -->
						<a data-url="page/office/message/recvlist" href="${ctx}/statistics#page/office/message/recvlist" class="btn btn-info2" data-rel="tooltip" title="我的短信">
							<i class="ace-icon fa fa-envelope"></i>
						</a>
						<a data-url="page/profile/profile" href="${ctx}/statistics#page/profile/profile"  class="btn btn-purple" data-rel="tooltip" title="我的帐户">
							<i class="ace-icon fa fa-user"></i>
						</a>
						<!-- /section:basics/sidebar.layout.shortcuts -->
				 </div>
				 <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span>
						<span class="btn btn-warning"></span>
						<span class="btn btn-info2"></span>
						<span class="btn btn-purple"></span>
				 </div>
			</div><!-- #sidebar-shortcuts -->
		      
		    <ul class="nav nav-list">
		        <li style="display:none">
					<a data-url="page/desktop" href="${ctx}/statistics#page/desktop">
						<i class="menu-icon fa fa-tachometer"></i>
						<span class="menu-text">我的工作台 </span>
					</a>
					<b class="arrow"></b>
				</li>
				
				
				
				
				
				


				<li class="open" id="menuLi">
				   <ul class="submenu nav-show">
				   		<c:forEach var="menuItem" items="${allMenuList}" varStatus="menuIndex">
				   			<li class="<c:if test="${menuIndex.index == 0}">active</c:if>" onclick="selectMenuItem(this)">
							<a href="${ctx}/statistics#page${menuItem.resString}" data-url="${ctx}/statistics#page${menuItem.resString}">
							<i class="menu-icon fa <c:if test="${menuIndex.index == 0}">fa-caret-right</c:if>"></i>
							${menuItem.resName}</a>
							<b class="arrow"></b>
							</li>
				   		</c:forEach>
				   		<!-- 
				   		<li class="active">
						<a href="${ctx}/statistics#page/office/task/analysis" data-url="${ctx}/statistics#page/office/task/analysis">
						<i class="menu-icon fa fa-caret-right"></i>
						统计数据</a>
						<b class="arrow"></b>
						</li>
						
						<li class="active">
						<a href="${ctx}/statistics#page/report/analysisOldReport" data-url="${ctx}/statistics#page/report/analysisOldReport">
						<i class="menu-icon fa null"></i>
						数据分析</a>
						<b class="arrow"></b>
						</li>
						
						<li class="open">
						<a href="#" class="dropdown-toggle">
						<i class="menu-icon fa null"></i>
						农情报表<b class="arrow fa fa-angle-down"></b>
						</a>
						<b class="arrow"></b>
						<ul class="submenu nav-show" style="display: block;">
							<li class="">
							<a href="${ctx}/statistics#page/office/task/eightreport?ethink_type=Y" data-url="${ctx}/statistics#page/office/task/eightreport?ethink_type=Y">
							<i class="menu-icon fa null"></i>
							9大类区分处室报表</a>
							<b class="arrow"></b>
							</li>
							
							<li class="">
							<a href="${ctx}/statistics#page/office/task/eightreport?ethink_type=N" data-url="${ctx}/statistics#page/office/task/eightreport?ethink_type=N">
							<i class="menu-icon fa null"></i>
							9大类不区分处室报表</a>
							<b class="arrow"></b>
							</li>
							
							<li class="">
							<a href="${ctx}/statistics#page/office/task/eightreport?ethink_type=J" data-url="${ctx}/statistics#page/office/task/eightreport?ethink_type=J">
							<i class="menu-icon fa null"></i>
							进度报表</a>
							<b class="arrow"></b>
							</li>
						</ul>
						</li>
						
						<li class="">
						<a href="${ctx}/statistics#page/office/newestRecords/list" data-url="${ctx}/statistics#page/office/newestRecords/list">
						<i class="menu-icon fa null"></i>
						指标查询</a>
						<b class="arrow"></b>
						</li>
						
						<li class="">
						<a href="${ctx}/statistics#page/office/task/query" data-url="${ctx}/statistics#page/office/task/query">
						<i class="menu-icon fa null"></i>
						历史填报查询</a>
						<b class="arrow"></b>
						</li>
				   		 -->
						</ul>
						
				</li>




				
				
				     
				
				<!-- 
		        <c:forEach var="item" items="${accordion}" varStatus="i">
		        <li<c:if test="${i.index == 0}"> class="open" </c:if>>
				   <a href="#" class="dropdown-toggle">
					   <i class="menu-icon fa ${item.iconCls}"></i>
					   <span class="menu-text" id="menu_${item.id}">${item.title}</span>
					   <b class="arrow fa fa-angle-down"></b>
				   </a>
				   ${item.content}
				</li>		                
		        </c:forEach>
				 -->
		    </ul><!-- /.nav-list -->			    
				    
		    <!-- #section:basics/sidebar.layout.minimize -->
		    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
				<i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
			</div>

			<!-- /section:basics/sidebar.layout.minimize -->
			<script type="text/javascript">
				try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
			</script>       
	    </div>
		<div class="main-content">
			<div class="main-content-inner">
				<!-- #section:basics/content.breadcrumbs -->
				<div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
				 	<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
				 	</script>

				 	<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="#">首页</a>
						</li>
				 	</ul><!-- .breadcrumb -->
	 
				 	<!-- #section:settings.box -->
					<div class="ace-settings-container" id="ace-settings-container">
						<div class="btn btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
							<i class="ace-icon fa fa-cog"></i>
						</div>
				 		<div class="ace-settings-box clearfix" id="ace-settings-box">
							<div class="pull-left width-50">
								<!-- #section:settings.skins -->
								<div class="ace-settings-item" style="display:none">
									<div class="pull-left">
										<select id="skin-colorpicker" class="hide">
											<option data-skin="no-skin" value="#438EB9">#438EB9</option>
											<option data-skin="skin-1" value="#222A2D">#222A2D</option>
											<option data-skin="skin-2" value="#C6487E">#C6487E</option>
											<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
										</select>
									</div>
									<span>&nbsp; 选择皮肤</span>
								</div>
								<!-- /section:settings.skins -->
                            	<!-- #section:basics/sidebar.options -->
								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover" />
									<label class="lbl" for="ace-settings-hover"> 子导航菜单弹出</label>
								</div>
							</div><!-- /.pull-left -->
							<div class="pull-left width-50">
								<div class="ace-settings-item">
									<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact" />
									<label class="lbl" for="ace-settings-compact">压缩左边导航</label>
								</div>
								<!-- /section:basics/sidebar.options -->
							</div><!-- /.pull-left -->
						</div><!-- /.ace-settings-box -->
					</div><!-- /.ace-settings-container -->
				</div>
				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					<!-- /section:settings.box -->
					<div class="page-content-area" data-ajax-content="true">
						<!-- ajax content goes here -->
					</div><!-- /.page-content-area -->
				</div><!-- /.page-content -->
			</div>				
	   </div><!-- /.main-content -->
	   	
	   <div class="footer">
			<div class="footer-inner">
				<!-- #section:basics/footer -->
				<div class="footer-content">
					<span class="bigger-110">
						<span><spring:message code="app.footer" /></span>
					</span>
				</div>
				<!-- /section:basics/footer -->
			</div>
		</div>
		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div><!-- /.main-container -->
	
	
	
<div id="indexPageModal" class="modal fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
	  <div class="modal-content">
    	 <div class="modal-header">
        	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        	<h3 class="modal-title">通知信息</h3>
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

<!-- basic scripts -->

<!--[if !IE]> -->
<script type="text/javascript">
	window.jQuery || document.write("<script src='${ctx}/assets/jquery/jquery.min.js'>"+"<"+"/script>");
</script>
<!-- <![endif]-->
	
<!--[if IE]>
<script type="text/javascript">
 	window.jQuery || document.write("<script src='${ctx}/assets/jquery/jquery1x.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
		
<script type="text/javascript">
	if('ontouchstart' in document.documentElement) document.write("<script src='${ctx}/assets/jquery/mobile/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="${ctx}/assets/bootstrap/bootstrap.min.js"></script>
	
<!-- ace scripts -->
<script src="${ctx}/assets/js/ace/elements.scroller.js"></script>
<script src="${ctx}/assets/js/ace/elements.colorpicker.js"></script>
<script src="${ctx}/assets/js/ace/elements.fileinput.js"></script>
<script src="${ctx}/assets/js/ace/elements.typeahead.js"></script>
<script src="${ctx}/assets/js/ace/elements.wysiwyg.js"></script>
<script src="${ctx}/assets/js/ace/elements.spinner.js"></script>
<script src="${ctx}/assets/js/ace/elements.treeview.js"></script>
<script src="${ctx}/assets/js/ace/elements.wizard.js"></script>
<script src="${ctx}/assets/js/ace/elements.aside.js"></script>
<script src="${ctx}/assets/js/ace/ace.js"></script>
<script src="${ctx}/assets/js/ace/ace.ajax-content.js"></script>
<script src="${ctx}/assets/js/ace/ace.touch-drag.js"></script>
<script src="${ctx}/assets/js/ace/ace.sidebar.js"></script>
<script src="${ctx}/assets/js/ace/ace.sidebar-scroll-1.js"></script>
<script src="${ctx}/assets/js/ace/ace.submenu-hover.js"></script>
<script src="${ctx}/assets/js/ace/ace.widget-box.js"></script>
<script src="${ctx}/assets/js/ace/ace.settings.js"></script>
<script src="${ctx}/assets/js/ace/ace.settings-rtl.js"></script>
<script src="${ctx}/assets/js/ace/ace.settings-skin.js"></script>
<script src="${ctx}/assets/js/ace/ace.widget-on-reload.js"></script>
<script src="${ctx}/assets/js/ace/ace.searchbox-autocomplete.js"></script>	
	
<script type="text/javascript">	


	
	
	


//document ready function
jQuery(function($) {
	basics();
	enableSidebar();
	
	enableAjax();
	handleScrollbars();
	
	dropdownAutoPos();
	
	navbarHelpers();
	sidebarTooltips();
	
	scrollTopBtn();
	
	someBrowserFix();
	
	bsCollapseToggle();
	smallDeviceDropdowns();
	
	////////////////////////////

	function basics() {
		// for android and ios we don't use "top:auto" when breadcrumbs is fixed
		if(ace.vars['non_auto_fixed']) {
			$('body').addClass('mob-safari');
		}

		ace.vars['transition'] = !!$.support.transition.end
	}
	
	function enableSidebar() {
		//initiate sidebar function
		var $sidebar = $('.sidebar');
		if($.fn.ace_sidebar) $sidebar.ace_sidebar();
		if($.fn.ace_sidebar_scroll) $sidebar.ace_sidebar_scroll({
			//for other options please see documentation
			'include_toggle': false || ace.vars['safari'] || ace.vars['ios_safari'] //true = include toggle button in the scrollbars
		});
		if($.fn.ace_sidebar_hover)	$sidebar.ace_sidebar_hover({
			'sub_hover_delay': 750,
			'sub_scroll_style': 'no-track scroll-thin scroll-margin scroll-visible'
		});
	}

	
	//Load content via ajax
	function enableAjax() {		
		if(!$.fn.ace_ajax) return;

		if(window.Pace) {
			window.paceOptions = {
				ajax: true,
				document: true,
				eventLag: false // disabled
				//elements: {selectors: ['.page-content-area']}
			}
		}
		
		var ajax_options = {
			 'close_active': true,
			 <c:if test="${firstMenu.resString != null}">
			 'default_url': 'page${firstMenu.resString}',//default hash
			 </c:if>
			 'content_url': function(hash) {
				//***NOTE***
				//this is for Ace demo only, you should change it to return a valid URL
				//please refer to documentation for more info

				if( !hash.match(/^page\//) ) return false;
				var path = document.location.pathname;

				//for example in Ace HTML demo version we convert /ajax/index.html#page/gallery to > /ajax/content/gallery.html and load it
				//if(path.match(/(\/ajax\/)(index\.html)?/)){
				//	return path.replace(/(\/ajax\/)(index\.html)?/, '/ajax/content/'+hash.replace(/^page\//, '')) ;
				//}
				if(path.match(/statistics/)) {
					return path.replace(/statistics/, hash.replace(/^page\//, ''));
				}
				//for example in Ace PHP demo version we convert "ajax.php#page/dashboard" to "ajax.php?page=dashboard" and load it
				return path + "?" + hash.replace(/\//, "=");
			  }			  
		}
		   
		//for IE9 and below we exclude PACE loader (using conditional IE comments)
		//for other browsers we use the following extra ajax loader options
		if(window.Pace) {
			ajax_options['loading_overlay'] = 'body';//the opaque overlay is applied to 'body'
		}

		//initiate ajax loading on this element( which is .page-content-area[data-ajax-content=true] in Ace's demo)
		$('[data-ajax-content=true]').ace_ajax(ajax_options);

		//if general error happens and ajax is working, let's stop loading icon & PACE
		$(window).on('error.ace_ajax', function() {
			$('[data-ajax-content=true]').each(function() {				
				var $this = $(this);
				if( $this.ace_ajax('working') ) {
					if(window.Pace && Pace.running) Pace.stop();
					$this.ace_ajax('stopLoading', true);
				}
			})
		})
	}

	/////////////////////////////

	function handleScrollbars() {
		//add scrollbars for navbar dropdowns
		var has_scroll = !!$.fn.ace_scroll;
		if(has_scroll) $('.dropdown-content').ace_scroll({reset: false, mouseWheelLock: true})

		//reset scrolls bars on window resize
		if(has_scroll && !ace.vars['old_ie']) {//IE has an issue with widget fullscreen on ajax?!!!
			$(window).on('resize.reset_scroll', function() {
				$('.ace-scroll:not(.scroll-disabled)').not(':hidden').ace_scroll('reset');
			});
			if(has_scroll) $(document).on('settings.ace.reset_scroll', function(e, name) {
				if(name == 'sidebar_collapsed') $('.ace-scroll:not(.scroll-disabled)').not(':hidden').ace_scroll('reset');
			});
		}
	}


	function dropdownAutoPos() {
		//change a dropdown to "dropup" depending on its position
		$(document).on('click.dropdown.pos', '.dropdown-toggle[data-position="auto"]', function() {
			var offset = $(this).offset();
			var parent = $(this.parentNode);

			if ( parseInt(offset.top + $(this).height()) + 50 
					>
				(ace.helper.scrollTop() + ace.helper.winHeight() - parent.find('.dropdown-menu').eq(0).height()) 
				) parent.addClass('dropup');
			else parent.removeClass('dropup');
		});
	}

	
	function navbarHelpers() {
		//prevent dropdowns from hiding when a from is clicked
		/**$(document).on('click', '.dropdown-navbar form', function(e){
			e.stopPropagation();
		});*/


		//disable navbar icon animation upon click
		$('.ace-nav [class*="icon-animated-"]').closest('a').one('click', function(){
			var icon = $(this).find('[class*="icon-animated-"]').eq(0);
			var $match = icon.attr('class').match(/icon\-animated\-([\d\w]+)/);
			icon.removeClass($match[0]);
		});


		//prevent dropdowns from hiding when a tab is selected
		$(document).on('click', '.dropdown-navbar .nav-tabs', function(e){
			e.stopPropagation();
			var $this , href
			var that = e.target
			if( ($this = $(e.target).closest('[data-toggle=tab]')) && $this.length > 0) {
				$this.tab('show');
				e.preventDefault();
				$(window).triggerHandler('resize.navbar.dropdown')
			}
		});
	}

	
	function sidebarTooltips() {
		//tooltip in sidebar items
		$('.sidebar .nav-list .badge[title],.sidebar .nav-list .badge[title]').each(function() {
			var tooltip_class = $(this).attr('class').match(/tooltip\-(?:\w+)/);
			tooltip_class = tooltip_class ? tooltip_class[0] : 'tooltip-error';
			$(this).tooltip({
				'placement': function (context, source) {
					var offset = $(source).offset();

					if( parseInt(offset.left) < parseInt(document.body.scrollWidth / 2) ) return 'right';
					return 'left';
				},
				container: 'body',
				template: '<div class="tooltip '+tooltip_class+'"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'
			});
		});
		
		//or something like this if items are dynamically inserted
		/**
		$('.sidebar').tooltip({
			'placement': function (context, source) {
				var offset = $(source).offset();

				if( parseInt(offset.left) < parseInt(document.body.scrollWidth / 2) ) return 'right';
				return 'left';
			},
			selector: '.nav-list .badge[title],.nav-list .label[title]',
			container: 'body',
			template: '<div class="tooltip tooltip-error"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'
		});
		*/
	}
	
	

	function scrollTopBtn() {
		//the scroll to top button
		var scroll_btn = $('.btn-scroll-up');
		if(scroll_btn.length > 0) {
			var is_visible = false;
			$(window).on('scroll.scroll_btn', function() {
				var scroll = ace.helper.scrollTop();
				var h = ace.helper.winHeight();
				var body_sH = document.body.scrollHeight;
				if(scroll > parseInt(h / 4) || (scroll > 0 && body_sH >= h && h + scroll >= body_sH - 1)) {//|| for smaller pages, when reached end of page
					if(!is_visible) {
						scroll_btn.addClass('display');
						is_visible = true;
					}
				} else {
					if(is_visible) {
						scroll_btn.removeClass('display');
						is_visible = false;
					}
				}
			}).triggerHandler('scroll.scroll_btn');

			scroll_btn.on(ace.click_event, function(){
				var duration = Math.min(500, Math.max(100, parseInt(ace.helper.scrollTop() / 3)));
				$('html,body').animate({scrollTop: 0}, duration);
				return false;
			});
		}
	}


	
	function someBrowserFix() {
		//chrome and webkit have a problem here when resizing from 479px to more
		//we should force them redraw the navbar!
		if( ace.vars['webkit'] ) {
			var ace_nav = $('.ace-nav').get(0);
			if( ace_nav ) $(window).on('resize.webkit_fix' , function(){
				ace.helper.redraw(ace_nav);
			});
		}
		
		
		//fix an issue with ios safari, when an element is fixed and an input receives focus
		if(ace.vars['ios_safari']) {
		  $(document).on('ace.settings.ios_fix', function(e, event_name, event_val) {
			if(event_name != 'navbar_fixed') return;

			$(document).off('focus.ios_fix blur.ios_fix', 'input,textarea,.wysiwyg-editor');
			if(event_val == true) {
			  $(document).on('focus.ios_fix', 'input,textarea,.wysiwyg-editor', function() {
				$(window).on('scroll.ios_fix', function() {
					var navbar = $('#navbar').get(0);
					if(navbar) ace.helper.redraw(navbar);
				});
			  }).on('blur.ios_fix', 'input,textarea,.wysiwyg-editor', function() {
				$(window).off('scroll.ios_fix');
			  })
			}
		  }).triggerHandler('ace.settings.ios_fix', ['navbar_fixed', $('#navbar').css('position') == 'fixed']);
		}
	}

	
	
	function bsCollapseToggle() {
		//bootstrap collapse component icon toggle
		$(document).on('hide.bs.collapse show.bs.collapse', function (ev) {
			var panel_id = ev.target.getAttribute('id')
			var panel = $('a[href*="#'+ panel_id+'"]');
			if(panel.length == 0) panel = $('a[data-target*="#'+ panel_id+'"]');
			if(panel.length == 0) return;

			panel.find(ace.vars['.icon']).each(function(){
				var $icon = $(this)

				var $match
				var $icon_down = null
				var $icon_up = null
				if( ($icon_down = $icon.attr('data-icon-show')) ) {
					$icon_up = $icon.attr('data-icon-hide')
				}
				else if( $match = $icon.attr('class').match(/fa\-(.*)\-(up|down)/) ) {
					$icon_down = 'fa-'+$match[1]+'-down'
					$icon_up = 'fa-'+$match[1]+'-up'
				}

				if($icon_down) {
					if(ev.type == 'show') $icon.removeClass($icon_down).addClass($icon_up)
						else $icon.removeClass($icon_up).addClass($icon_down)
						
					return false;//ignore other icons that match, one is enough
				}

			});
		})
	}
	

	
	//in small devices display navbar dropdowns like modal boxes
	function smallDeviceDropdowns() {
	  if(ace.vars['old_ie']) return;
	  
	  $('.ace-nav > li')
	  .on('shown.bs.dropdown.navbar', function(e) {
		adjustNavbarDropdown.call(this);
	  })
	  .on('hidden.bs.dropdown.navbar', function(e) {
		$(window).off('resize.navbar.dropdown');
		resetNavbarDropdown.call(this);
	  })
	 
	  function adjustNavbarDropdown() {
		var $sub = $(this).find('> .dropdown-menu');

		if( $sub.css('position') == 'fixed' ) {
			var win_width = parseInt($(window).width());
			var offset_w = win_width > 320 ? 60 : (win_width > 240 ? 40 : 30);
			var avail_width = parseInt(win_width) - offset_w;
			var avail_height = parseInt($(window).height()) - 30;
			
			var width = parseInt(Math.min(avail_width , 320));
			//we set 'width' here for text wrappings and spacings to take effect before calculating scrollHeight
			$sub.css('width', width);

			var tabbed = false;
			var extra_parts = 0;
			var dropdown_content = $sub.find('.tab-pane.active .dropdown-content.ace-scroll');
			if(dropdown_content.length == 0) dropdown_content = $sub.find('.dropdown-content.ace-scroll');
			else tabbed = true;

			var parent_menu = dropdown_content.closest('.dropdown-menu');
			var scrollHeight = $sub[0].scrollHeight;
			if(dropdown_content.length == 1) {
				//sometimes there's no scroll-content, for example in detached scrollbars
				var content =  dropdown_content.find('.scroll-content')[0];
				if(content) {
					scrollHeight = content.scrollHeight;
				}
			
				extra_parts += parent_menu.find('.dropdown-header').outerHeight();
				extra_parts += parent_menu.find('.dropdown-footer').outerHeight();
				
				var tab_content = parent_menu.closest('.tab-content');
				if( tab_content.length != 0 ) {
					extra_parts += tab_content.siblings('.nav-tabs').eq(0).height();
				}
			}
			

			
			var height = parseInt(Math.min(avail_height , 480, scrollHeight + extra_parts));
			var left = parseInt(Math.abs((avail_width + offset_w - width)/2));
			var top = parseInt(Math.abs((avail_height + 30 - height)/2));

			
			var zindex = parseInt($sub.css('z-index')) || 0;

			$sub.css({'height': height, 'left': left, 'right': 'auto', 'top': top - (!tabbed ? 1 : 3)});
			if(dropdown_content.length == 1) {
				if(!ace.vars['touch']) {
					dropdown_content.ace_scroll('update', {size: height - extra_parts}).ace_scroll('enable').ace_scroll('reset');
				}
				else {
					dropdown_content
					.ace_scroll('disable').css('max-height', height - extra_parts).addClass('overflow-scroll');
				}
			}
			$sub.css('height', height + (!tabbed ? 2 : 7));//for bottom border adjustment and tab content paddings
			
			
			if($sub.hasClass('user-menu')) {
				$sub.css('height', '');//because of user-info hiding/showing at different widths, which changes above 'scrollHeight', so we remove it!
				
				//user menu is re-positioned in small widths
				//but we need to re-position again in small heights as well (modal mode)
				var user_info = $(this).find('.user-info');
				if(user_info.length == 1 && user_info.css('position') == 'fixed') {
					user_info.css({'left': left, 'right': 'auto', 'top': top, 'width': width - 2, 'max-width': width - 2, 'z-index': zindex + 1});
				}
				else user_info.css({'left': '', 'right': '', 'top': '', 'width': '', 'max-width': '', 'z-index': ''});
			}
			
			//dropdown's z-index is limited by parent .navbar's z-index (which doesn't make sense because dropdowns are fixed!)
			//so for example when in 'content-slider' page, fixed modal toggle buttons go above are dropdowns
			//so we increase navbar's z-index to fix this!
			$(this).closest('.navbar.navbar-fixed-top').css('z-index', zindex);
		}
		else {
			if($sub.length != 0) resetNavbarDropdown.call(this, $sub);
		}
		
		var self = this;
		$(window)
		.off('resize.navbar.dropdown')
		.one('resize.navbar.dropdown', function() {
			$(self).triggerHandler('shown.bs.dropdown.navbar');
		})
	  }

	  //reset scrollbars and user menu
	  function resetNavbarDropdown($sub) {
		$sub = $sub || $(this).find('> .dropdown-menu');
	  
	    if($sub.length > 0) {
			$sub
			.css({'width': '', 'height': '', 'left': '', 'right': '', 'top': ''})
			.find('.dropdown-content').each(function() {
				if(ace.vars['touch']) {
					$(this).css('max-height', '').removeClass('overflow-scroll');
				}

				var size = parseInt($(this).attr('data-size') || 0) || $.fn.ace_scroll.defaults.size;
				$(this).ace_scroll('update', {size: size}).ace_scroll('enable').ace_scroll('reset');
			})
			
			if( $sub.hasClass('user-menu') ) {
				var user_info = 
				$(this).find('.user-info')
				.css({'left': '', 'right': '', 'top': '', 'width': '', 'max-width': '', 'z-index': ''});
			}
		}
		
		$(this).closest('.navbar').css('z-index', '');
	  }
	}

});//jQuery document ready


	
	
	
	

	//编辑选定记录
	function showRowByIndexPage(id,type){			
		var url11 = "";
		if(type == 'notice') {
			url11 = "${ctx}/office/notice/viewNotice?id="+id+"&"+Math.random(1000);
		} else {
			url11 = "${ctx}/office/message/viewMessage?id="+id+"&"+Math.random(1000)+"&canDelete=false&canReply=false";
		}
		$.get(url11, '', function(data){ 
			var modal = $('#indexPageModal');
			modal.html(data);
			modal.modal({show:true,backdrop:false}).on("hidden", function(){
			    $(this).remove();
			});
		    var form = modal.find('form:eq(0)');
		    form.validate({	
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
					            //console.log(data)
					        if(data.success===true) {
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
</script>
</body>
</html>