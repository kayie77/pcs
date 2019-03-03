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
      #allmap {width: 100%;height: 70%;overflow: hidden;margin:0;font-family:"微软雅黑";}
    </style>
   </head>
<body> 

    <div id="allmap"></div>

    <div class="form-inline paramContain" >
      <div class="form-group">
          <label for="longitude">经度:</label> <input id="longitude" type="text" class="form-control"/>
          <label for="latitude">纬度:</label> <input id="latitude" type="text" class="form-control"/>
          <input type="button" value="查询" onclick="serchByPoint()" class="btn btn-default hide"/>
      </div>
    </div>
    <div class="form-inline paramContain" >
      <div class="form-group">
          <label for="addressId">地址:</label> <input id="addressId" type="text" class="form-control" style="width:300px"/>
          <input id="yes" type="button" value="保存" onclick="getMapParams()" class="btn btn-info" />
          <div id="searchResultPanel"></div>
      </div>    
    </div>

  <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=vvZ5PYfUyh2gDgG6H4TppTsG"></script>
  <script type="text/javascript" src="${ctx}/assets/jquery/jquery-1.11.2.min.js"></script>
  <script type="text/javascript" src="${ctx}/assets/bootstrap/bootstrap.min.js"></script>
  <script type="text/javascript" src="${ctx}/assets/bootstrap/plugins/bootbox/bootbox.min.js"></script>
  <script type="text/javascript" src="${ctx}/assets/jquery/plugins/lixi_common/jquery.serialize-object.min.js"></script>
  <script type="text/javascript" src="${ctx}/assets/jquery/plugins/toast/jquery.toaster.js"></script>
  <script type="text/javascript" src="${ctx}/assets/jquery/plugins/lixi_common/utils.js"></script>
  <script type="text/javascript">

  //初始化地图
  var map = new BMap.Map("allmap");            
/*  map.centerAndZoom("广西壮族自治区",15);  */
  map.centerAndZoom(new BMap.Point(108.334113, 22.821206), 14);
  map.setCurrentCity("广西壮族自治区");  // 设置地图显示的城市 此项是必须设置的
  map.enableScrollWheelZoom(true);

  //逆地址解析
  var geoc = new BMap.Geocoder();     

  //窗口参数定义
  var opts = {
    width : 300,     // 信息窗口宽度
    height: 100,     // 信息窗口高度
    enableMessage:true,//设置允许信息窗发送短息
  }
  
  initPoint();//自动初始point
  
  function initPoint(){
	  var lng ='${dataCollectPoint.longitude}';
	  var lat ='${dataCollectPoint.latitude}';
	  if(lng == '' || lat == ''){
	  }else{
		  creatPoint(lng,lat);
	  }
  }
  
  //使用经纬度设置地图中心点和创建消息窗口
  function creatPoint(lng,lat){
	  var pt = new BMap.Point(lng,lat); 
	  getPoint(lng,lat);
	  createMessage(pt,lng,lat);//创建信息窗口
  }
  
  // 经纬度设置地图中心点
  function getPoint(lng,lat){
      map.clearOverlays(); 
      var new_point = new BMap.Point(lng,lat);
      var marker = new BMap.Marker(new_point);  // 创建标注
      map.addOverlay(marker);              // 将标注添加到地图中
      map.panTo(new_point);      
  }

  //创建消息窗口
  function createMessage(pt,lng,lat){
    geoc.getLocation(pt, function(rs){
      var addComp = rs.addressComponents;
      var address = addComp.province + addComp.city + addComp.district + addComp.street+addComp.streetNumber;
       document.getElementById("longitude").value = lng;//把值设到地址输入框
       document.getElementById("latitude").value = lat;//把值设到地址输入框
       document.getElementById("addressId").value = address;//把值设到地址输入框
      var infoWindow = new BMap.InfoWindow("采集点：${dataCollectPoint.name}<br>地址："+address+"<br>采集人：${result}<br>经度："+lng+"，纬度："+lat, opts);  // 创建信息窗口对象 
      map.openInfoWindow(infoWindow,pt); //开启信息窗口
    });   
  }

  //鼠标点击事件
  map.addEventListener("click",function(e){
    var lng = e.point.lng;//获取经度
    var lat = e.point.lat;//获取纬度
    creatPoint(lng,lat);
  });

  //按经纬度查询事件
  function serchByPoint(){
    var lng = document.getElementById("longitude").value;
    var lat = document.getElementById("latitude").value;
    var pt = new BMap.Point(lng,lat);

    getPoint(lng,lat);//创建经纬度标注点
    createMessage(pt,lng,lat);
  }
  
  //根据地址地位地图  包含搜索自动提示
  function G(id) {
   return document.getElementById(id);
 }

 var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
   {"input" : "addressId"
   ,"location" : map
 });

 ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
 var str = "";
   var _value = e.fromitem.value;
   var value = "";
   if (e.fromitem.index > -1) {
     value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
   }    
   str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
   
   value = "";
   if (e.toitem.index > -1) {
     _value = e.toitem.value;
     value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
   }    
   str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
   G("searchResultPanel").innerHTML = str;
 });

 var myValue;
 ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
 var _value = e.item.value;
   myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
   G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
   
   setPlace();
 });

 function setPlace(){
   map.clearOverlays();    //清除地图上所有覆盖物
   function myFun(){
     var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
     map.centerAndZoom(pp, 18);
     map.addOverlay(new BMap.Marker(pp));    //添加标注

     var pt = new BMap.Point(pp.lng,pp.lat);
     createMessage(pt,pp.lng,pp.lat);
   }
   var local = new BMap.LocalSearch(map, { //智能搜索
     onSearchComplete: myFun
   });
   local.search(myValue);
 }

 function getMapParams(){
   var id = '${dataCollectPoint.id}';
   var lng = document.getElementById("longitude").value;
   var lat = document.getElementById("latitude").value;
   var address = document.getElementById("addressId").value;
         
		bootbox.confirm('确定要提交吗?', function(result) {
			if (result) {
				var url = '${ctx}/collect/dataCollectPoint/map?id=' + id + '&lng='
						+ lng + '&lat=' + lat+ '&address=' + address;
				$.post(url, function(result) {
					if (result.status) {
						$.toaster({
							title : '成功',
							priority : 'success',
							message : result.message
						});
						window.close();
						self.opener.location.reload(); 
					} else {
						$.toaster({
							title : '失败',
							priority : 'warning',
							message : result.message
						});
					}
				});
			}
		});
 }
 
</script>

</body>
</html>
