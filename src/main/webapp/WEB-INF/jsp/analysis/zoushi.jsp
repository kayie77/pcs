<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<c:url var="component" value="/test"/>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>价格销量走势</title>

    <!-- Bootstrap -->
    <link href="${ctx}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${ctx}/assets/css/font-awesome.min.css" rel="stylesheet">
    <link href="${ctx}/assets/css/analysis/common.css" rel="stylesheet">
    <link href="${ctx}/assets/bootstrap-datetimepicker/bootstrap-datetimepicker.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <div class="container">
      <div class="topic">
        <div class="constraint">
          
        </div>
      
        <div class="subject">
          <span class="title">同一农产品不同市场价格明细</span>
          <div class="content">
            <div class="constraint">
              <div class="row">
                <div class="col-sm-12">
                  <span class="clable">农产品类别: </span>
                  <ul id="ctg" name="ctg" class="ctg-list list-inline dui-controller" data-custom="true"></ul>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-12">
                  <span class="clable">农产品: </span>
                  <ul name="product" class="t1-condition list-inline dui-controller products-list" data-parent="ctg"  data-custom="true"></ul>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-12">
                  <span class="clable">地区：</span>
                  <select id="region1" name="region" class="region-list" >
                  </select>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-12">
                  <span class="clable">交易地选择：</span>
                  <ul name="markets" class="t1-condition list-inline dui-controller markets-list multiple" data-parent="region1" data-custom="true">
                  </ul>
                </div>  
              </div>
              <!-- 时间条件选择单选按钮-->
              <div  class="row">
                <div class="col-sm-3 adjust-center"> 
                  <span class="clable">类型:</span>
                  <input type="radio" name="ctype"  value="price" class="type-condition t1-condition" checked="checked" > 价格
                  <input type="radio" name="ctype"  value="sale" class="type-condition t1-condition" > 销量
                </div>
                <div class="col-sm-3 adjust-center"> 
                  <span class="clable">展示方式：</span>
                  <!-- <input type="radio" name="b.time"  value="day" class="t1-condition d1-condition date-condition" checked="checked"> 日
                  <input type="radio" name="b.time"  value="week" class="t1-condition d2-condition date-condition" > 周 -->
                  <!-- <input type="radio" name="b.time"  class="t1-condition" > 旬 -->
                  <!-- <input type="radio" name="b.time"  class="t1-condition" > 半月 -->
                  <input type="radio" name="time"  value="month" class="date-condition t1-condition" checked="checked" id="date-month"> 月
                  <!-- <input type="radio" name="b.time"  class="t1-condition" > 半年 -->
                  <input type="radio" name="time"  value="year" class="date-condition t1-condition" id="date-year"> 年
                </div>
                <div class="col-sm-2">
                  <div class="input-group datePicker " >
                    <input type="text" class="form-control t1-condition" name="cdate"/>
                    <span class="input-group-addon">
                      <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                  </div>
                </div>
                <div class="col-sm-1">
                  <button class="btn btn-default search-btn" data-target="table1">查询</button>
                </div>
              </div>
            </div>
            <div id="table1" class="table-container hide" data-table="zoushi_crop" data-condition="t1-condition">
            </div>
            <div id="table1Chart" class="chart">
              
            </div>
          </div>
        </div>
      
        <div class="subject">
          <span class="title">同一市场不同农产品价格明细</span>
          <div class="content">
            <div class="constraint">
              <div class="row">
                <div class="col-sm-12">
                  <span class="clable">农产品类别: </span>
                  <ul id="ctg1" name="ctg" class="ctg-list list-inline dui-controller" data-custom="true"></ul>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-12">
                  <span class="clable">农产品: </span>
                  <ul name="products" class="t2-condition list-inline dui-controller products-list multiple" data-parent="ctg"  data-custom="true"></ul>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-3">
                  <span class="clable">地区：</span>
                  <select id="region2" name="region" class="region-list" >
                  </select>
                </div>
                <div class="col-sm-4">
                  <span class="clable">交易地选择：</span>
                  <select name="market" class="t2-condition markets-list" data-parent="region2" >
                  </select>
                </div>
              </div>
          
              <!-- 时间条件选择单选按钮-->
              <div  class="row">
                <div class="col-sm-3 adjust-center"> 
                  <span class="clable">类型:</span>
                  <input type="radio" name="mtype"  value="price" class="type-condition t2-condition" checked="checked" > 价格
                  <input type="radio" name="mtype"  value="sale" class="type-condition t2-condition" > 销量
                </div>

                <div class="col-sm-3 adjust-center">
                  <span class="clable">展示方式：</span>
                  <!-- <input type="radio" name="b.time"  value="day" class="t1-condition d1-condition date-condition" checked="checked"> 日
                  <input type="radio" name="b.time"  value="week" class="t1-condition d2-condition date-condition" > 周 -->
                  <!-- <input type="radio" name="b.time"  class="t1-condition" > 旬 -->
                  <!-- <input type="radio" name="b.time"  class="t1-condition" > 半月 -->
                  <input type="radio" name="mtime"  value="month" class="date-condition t2-condition" checked="checked" id="date-month"> 月
                  <!-- <input type="radio" name="b.time"  class="t1-condition" > 半年 -->
                  <input type="radio" name="mtime"  value="year" class="date-condition t2-condition" id="date-year"> 年
                </div>
          
                <div class="col-sm-2">
                  <div class="input-group datePicker">
                    <input type="text" class="form-control t2-condition" name='cdate'/>
                    <span class="input-group-addon">
                      <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                  </div>
                </div>

                <div class="col-sm-1">
                  <button class="btn btn-default search-btn" data-target="table2">查询</button>
                </div>
              </div>
            </div>
            <div id="table2" class="table-container hide" data-table="zoushi_market" data-condition="t2-condition">
            </div>
            <div id="table2Chart" class="chart">
              
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${ctx}/assets/jquery/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${ctx}/assets/bootstrap/bootstrap.min.js"></script>
    <script src="${ctx}/assets/js/component/dui-helper.js"></script>
    <script type="text/javascript" src="${ctx}/assets/moment/min/moment.min.js"></script>
    <script type="text/javascript" src="${ctx}/assets/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"></script>
    <script src="${ctx}/assets/datepicker/bootstrap-datepicker.js"></script>
    <script src="${ctx}/assets/datepicker/bootstrap-datepicker.zh-CN.js"></script>
    <script src="${ctx}/assets/js/bluebird.min.js"></script>
    <script src="${ctx}/assets/js/lodash.min.js"></script>
    <script src="${ctx}/assets/js/echart/dist/echarts-all.js"></script>
    <script src="${ctx}/assets/js/echart/mycharts.js"></script>
    <script src="${ctx}/assets/js/component/common.js"></script>

    <script>
      // 思路：在pricecompositequery.jsp页面中
      //       加载每日、每周、每月、每年的价格统计模板、
      //       农产品价格明细模板、交易地农产品价格明细模板，
      //       农产品类别、农产品、区域、交易地作为公共的条件，
      //       选择不同的时间显示不同的模板
      var CROPTYPES_MAP = {}
      , REGIONS_MAP = {};
      $(document).ready(function(){

        //chart init
        $('.subject')
        .on(
          'loaded.tbl.dui',
          '.dui-component',
          function (e, component) {
            if (component.attr('id') == 'table1') {
              drawTable1Chart(component);
            } else if (component.attr('id') == 'table2') {
              drawTable2Chart(component);
            };
          }
        );

        //初始化时间控件
        datePickInit({
          locale: 'zh-CN',
          useCurrent: false,
          format: 'YYYY-MM',
          viewMode: 'months',
          defaultDate: new Date()
        });

        /**
         * 页面初始化时从后台获取json格式的农产品类别显示在前台页面
         */ 
        var ctgsRequest = $.getJSON('${ctx}/analysis/ctgs')
        , regionsRequest = $.getJSON('${ctx}/analysis/regions?NEQ__region_code=450000');
        Promise
        .all([ctgsRequest, regionsRequest])
        .spread(function(ctgs, regions) {
          // init ctgslist
          renderOption(ctgs, $('.ctg-list'));
          // init proudctslist
          var productsReady = Promise.reduce(ctgs, function(myMap, ctg, index){
            return $.getJSON('${ctx}/analysis/ctgs/' + ctg.code + '/products')
            .then(function (products){
              if (index == 0 ) {
                renderOption(products, $('.products-list'));
              };
              myMap[ctg.code] = products;
              return myMap;
            });
          }, CROPTYPES_MAP).then(function () {
            //所有产品加载完毕
            console.log(CROPTYPES_MAP);
          });

          // init regionslist
          renderOption(regions, $('.region-list'));
          // init regionslist
          var marketsReady = Promise.reduce(regions, function(myMap, region, index){
            return $.getJSON('${ctx}/analysis/regions/' + region.code + '/markets')
            .then(function (markets){
              if (index == 0 ) {
                renderOption(markets, $('.markets-list'));
              };
              myMap[region.code] = markets;
              return myMap;
            });
          }, REGIONS_MAP).then(function () {
            console.log(REGIONS_MAP);
          });
          return Promise.all([productsReady, marketsReady]);
        }).then(function() {var productsReady = 
          //table init
          $('.table-container').each(function () {
            loadTable($(this));
          });

          //sub list listening init
          _.forEach($('.products-list'), function(element) {
            var parent = $(element).data('parent');
            $('#'+parent).on('change', function(e, value) {
              var key = value || $(this).val();
              renderOption(CROPTYPES_MAP[key], element);
            });
          });
          _.forEach($('.markets-list'), function(element) {
            var parent = $(element).data('parent');
            $('#'+parent).on('change', function(e, value) {
              var key = value || $(this).val();
              renderOption(REGIONS_MAP[key], element);
            });
          });
        });

      });//ready end

      function drawTable1Chart(component) {
        var items = $(component).find('.ec-legend').map(function() {
          return {
            name: $(this).text(),
            data: dui_tbl.getDatas(component, $(this).text()+'-'+$('.type-condition.t1-condition:checked').val())
          }
        }).get();
        drawMyChart(
          $('#table1Chart'),
          dui_tbl._getTable(component),
          [
            {
              name:'time',
              label: '时间',
              data: component.find('.tbl-side-header [name="time"]').map(function(){return $(this).text();}).get()
            },
          ].concat(items),
          'line1'
        );
      }

      function drawTable2Chart(component) {
        var items = $(component).find('.ec-legend').map(function() {
          return {
            name: $(this).text(),
            data: dui_tbl.getDatas(component, $(this).text()+'-'+$('.type-condition.t2-condition:checked').val())
          }
        }).get();
        drawMyChart(
          $('#table2Chart'),
          dui_tbl._getTable(component),
          [
            {
              name:'time',
              label: '时间',
              data: component.find('.tbl-side-header [name="time"]').map(function(){return $(this).text();}).get()
            },
          ].concat(items),
          'line1'
        );
      }
    </script>
  </body>
</html>