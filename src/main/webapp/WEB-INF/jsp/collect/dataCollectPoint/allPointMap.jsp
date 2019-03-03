<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
  
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/assets/jquery/plugins/jstree/themes/default/style.min.css" />
    <link href="${ctx}/assets/css/map.css" rel="stylesheet"> 
    <style type="text/css">
      #allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
    </style>
    </head>
<body> 

  <button id="mapId" class="btn btn-default hidden"onclick="mapShow()">显示地图</button>
  <div id="allmap"></div>
  <table class="table hidden"id="table">
	  <thead>	
		   <c:forEach var="dcpItem" items="${dcpList}">
		      <tr><td>${dcpItem.longitude}</td><td>${dcpItem.latitude}</td><td>${dcpItem.adress}</td></tr>
		   </c:forEach>
		
	  </thead>
  </table>

  <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=vvZ5PYfUyh2gDgG6H4TppTsG"></script>
  <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
  <script type="text/javascript" src="${ctx}/assets/bootstrap/bootstrap.min.js"></script>
  <script type="text/javascript" src="${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js"></script>
  <script type="text/javascript" src="${ctx}/assets/jquery/plugins/lixi_common/jquery.serialize-object.min.js"></script>
  <script type="text/javascript" src="${ctx}/assets/jquery/plugins/toast/jquery.toaster.js"></script>
  <script type="text/javascript" src="${ctx}/assets/jquery/plugins/lixi_common/utils.js"></script> 
  <script type="text/javascript">
	/****************map show***************/
	setTimeout("$('#mapId').click();", 200);
	//百度地图API功能
	var map = new BMap.Map("allmap");
	map.enableScrollWheelZoom(true); 
	
	//逆地址解析
	var geoc = new BMap.Geocoder();
	//窗口参数定义
	var opts = {
		width : 300, // 信息窗口宽度
		height : 60, // 信息窗口高度
		color : '#666',
		enableMessage : true,//设置允许信息窗发送短息
	}

	//创建地图
	function mapShow() {
		map.clearOverlays();
		var data = new Array();
		var lngArr = new Array();
		var latArr = new Array();
		var llArr = new Array();
		var nameArr = new Array();
		var pointArray = new Array();
		var orow = document.getElementById("table").rows;

		for (var i = 0; i < orow.length; i++) {
			if(orow.item(i).cells[0].innerHTML == '' || orow.item(i).cells[1].innerHTML == ''){
				//data = null;
			}
			else{
				lngArr[i] = JSON.parse(orow.item(i).cells[0].innerHTML);
				latArr[i] = JSON.parse(orow.item(i).cells[1].innerHTML);
				nameArr[i] = orow.item(i).cells[2].textContent;
				llArr = [ lngArr[i], latArr[i], nameArr[i] ];
				data.push(llArr);
			}
		}

		for (var i = 0; i < data.length; i++) {
			var marker = new BMap.Marker(new BMap.Point(data[i][0], data[i][1])); // 创建点
			var name = data[i][2];
			map.addOverlay(marker); //增加点
			pointArray[i] = new BMap.Point(data[i][0], data[i][1]);
			if (data.length == 1) {
				createMessage(pointArray[i], data[i][0], data[i][1], data[i][2]);//创建信息窗口 
			}
			//绑定点击事件
			addClickHandler(name, marker);
			}
			//让所有点在视野范围内
			map.setViewport(pointArray);
		}


	//创建点击事件
	function addClickHandler(name, marker) {
		marker.addEventListener("click", function(e) {
			openInfo(name, e)
		});
	}

	//打开信息框
	function openInfo(name, e) {
		var lng = e.point.lng;//获取经度
		var lat = e.point.lat;//获取纬度
		var pt = new BMap.Point(lng, lat);
		createMessage(pt, lng, lat, name);//创建信息窗口 
	}

	//创建消息窗口
	function createMessage(pt, lng, lat, name) {
		geoc.getLocation(pt, function(rs) {
			var addComp = rs.addressComponents;
			var address = addComp.province + addComp.city + addComp.district
					+ addComp.street + addComp.streetNumber;
			var infoWindow = new BMap.InfoWindow("<span><b>经度：</b>" + lng
					+ " <b>纬度：</b>" + lat + "<br><b>地址：</b>" + name, opts); // 创建信息窗口对象 
			map.openInfoWindow(infoWindow, pt); //开启信息窗口
		});
	}
	
</script>

</body>
</html> 
