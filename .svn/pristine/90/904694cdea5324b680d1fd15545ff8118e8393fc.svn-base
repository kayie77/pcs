<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=Edge;chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="content-language" content="zh-CN" />
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="平台,业务" />
<meta http-equiv="description" content="通用业务平台" />
<meta name="author" content="Yunforge" />
<title><spring:message code="app.title" /></title>
<link rel="icon" href="${ctx}/favicon.ico" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/resources/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" media="screen" href="${ctx}/resources/css/login.css" />
<script type="text/javascript" src="${ctx}/resources/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/bootstrap/js/bootstrap.js"></script>

<!--[if lt IE 9]>
  <script src="${ctx}/resources/js/html5shiv.js"></script>
  <script src="${ctx}/resources/js/respond.min.js"></script>
<![endif]-->

<script type="text/javascript">

$(document).ready(function() { 
	 $("#setupData").click(function() {
		 $.ajax({
			    type : "GET",
			    dataType : 'JSON',
		  		url : '${ctx}/setup/org',
		  		success : function(data) {
		  			alert(data.message);
		        }
	    });			
	 });
});
</script>
</head>
<body>
	<div class="navbar navbar-default login-top-nav" role="banner">
		<div class="navbar-inner">
			<div class="navbar-header">
				<a href="#" class="navbar-brand"><spring:message code="app.title" /></a>
			</div>
			<nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">帮助</a></li>
					<li><a href="#">关于</a></li>
				</ul>
			</nav>
		</div>
	</div>
    <div class="login-wrap">		
		<div id="myCarousel" class="carousel slide">      
      		<div class="carousel-inner">
        		<div class="item active masthead">
          			<div class="masthead-bg"></div>
          				<div class="container">
                           <div class="row">	
		 						<div class="col-xs-12 col-sm-6 col-md-8">
          							<img src="${ctx}/resources/images/esb.png" class="login-image" alt="ESB"/>
        						</div> 
								<div class="col-xs-6 col-md-4 text-left login-col">
		  		  					<div class="panel panel-primary">
		  		    					<div class="panel-heading">
		  		           					<h3 class="panel-title">系统安装</h3>
		  		   						</div>
		  								<div class="panel-body">
		     		  							<button type="button" id="setupData" class="btn btn-primary"><spring:message code="button.setup" /></button>
		   			  					</div>
		   		    				</div>
		 						</div>
	   						</div>
          				 </div>
        		     </div>
      		   </div>
     	</div>
		<footer class="footer">
		   <spring:message code="app.footer" />
		</footer>
  	</div>
</body>
</html>