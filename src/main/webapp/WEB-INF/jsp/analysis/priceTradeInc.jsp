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
    <title>价格成交量涨跌排行</title>

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
    <style>
    
    </style>
  </head>
  <body>
    <!-- topic begin -->
    <div class="container">
      <div class="topic">
        <!-- constraint begin -->
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
              <ul id="products" name="products" class="t1-condition list-inline dui-controller products-list multiple" data-parent="ctg"  data-custom="true"></ul>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-12">
              <span class="clable">地区：</span>
              <select id="region" name="region" class="region-list" >
              </select>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-12">
              <span class="clable">交易地选择：</span>
              <ul id="markets" name="markets" class="t1-condition list-inline dui-controller markets-list multiple" data-parent="region" data-custom="true">
              </ul>
            </div>  
          </div>
          <div  class="row">
            <div class="form-froup">
              <div class="col-sm-3 adjust-center"> 
                <span class="clable">周期选择：</span>
                <input type="radio" name="atime"  value="day" class="date-condition t1-condition" checked="checked" id="date-month"> 日
                <input type="radio" name="atime"  value="week" class="date-condition t1-condition"  id="date-week"> 周
                <input type="radio" name="atime"  value="month" class="date-condition t1-condition" id="date-month"> 月
                <input type="radio" name="atime"  value="year" class="date-condition t1-condition" id="date-year"> 年
              </div>
              <div class="col-sm-2">
                <div class="input-group date datePicker">
                  <input type="text" class="form-control t1-condition" name="cdate" />
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
        </div>
        <!-- constraint end -->
      
        <!-- subjects begin -->
        <div class="subject">
          <span class="title">价格,涨幅,成交量</span>
          <div class="content">
            <div class="constraint">
            </div>
            <div id="table1" class="table-container" data-table="priceTradeInc_main" data-condition="t1-condition">
            </div>
          </div>
        </div>

        <div class="subject">
          <span class="title">同一农产品不同市场价格明细</span>
          <div class="content">
            <div class="constraint">
              <div class="row">
                <div class="col-sm-3">
                  <span class="clable">农产品: </span>
                  <select name="product" id="chartProduct" class="subSelect"></select>
                </div>
                <div class="col-sm-3"> 
                  <span class="clable">类型:</span>
                  <input type="radio" name="mttype"  value="0" class="type-condition chart1-condition" checked="checked" > 价格
                  <input type="radio" name="mttype"  value="1" class="type-condition chart1-condition" > 涨跌
                  <input type="radio" name="mttype"  value="2" class="type-condition chart1-condition" > 成交量
                </div>
              </div>
            </div>
            <div id="table1Chart1" class="chart"></div>
          </div>
        </div>

        <div class="subject" >
          <span class="title">同一市场不同农产品价格明细</span>
          <div class="content">
            <div class="constraint">
              <!-- 交易地选择下拉框-->
              <div class="row">
                <div class="col-sm-3">
                  <span class="clable">交易地选择：</span>
                  <select name="market" id="chartMarket" class="subSelect">
                  </select>
                </div>
                <div class="col-sm-3 "> 
                  <span class="clable">类型:</span>
                  <input type="radio" name="cttype"  value="0" class="type-condition chart2-condition" checked="checked" > 价格
                  <input type="radio" name="cttype"  value="1" class="type-condition chart2-condition" > 涨跌
                  <input type="radio" name="cttype"  value="2" class="type-condition chart2-condition" > 成交量
                </div>
              </div>
            </div>
            <div id="table1Chart2" class="chart"></div>
          </div>
        </div>
         <!-- subjects end -->
      </div>
    </div>
    <!-- topic end -->

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
    <script src="${ctx}/assets/js/echart/mycharts.js"></script>\
    <script src="${ctx}/assets/js/component/common.js"></script>

    <script>
      // 思路：在pricecompositequery.jsp页面中
      //       加载每日、每周、每月、每年的价格统计模板、
      //       农产品价格明细模板、交易地农产品价格明细模板，
      //       农产品类别、农产品、区域、交易地作为公共的条件，
      //       选择不同的时间显示不同的模板
      var CROPTYPES_MAP = {}
      , REGIONS_MAP = {}
      , TABLE_DATA;
      $(document).ready(function(){
        $('#products').on('content.finish change', function() {
          renderSubList(this, $('#chartProduct'));
        });

        $('#markets').on('content.finish change', function() {
          renderSubList(this, $('#chartMarket'));
        });

        $('#chartProduct').change(_drawChart1);
        $('#chartMarket').change(_drawChart2);
        $('.type-condition.chart1-condition').click(_drawChart1);
        $('.type-condition.chart2-condition').click(_drawChart2);
        //chart init
        $('.subject')
        .on(
          'loaded.tbl.dui',
          '.dui-component',
          function (e, component) {
            TABLE_DATA = dui_tbl.getRawData(component);
            if (component.attr('id') == 'table1') {
              drawTable1Chart(component);
            } ;
          }
        );

        //初始化时间控件
        datePickInit({
          locale: 'zh-CN',
          useCurrent: false,
          format: 'YYYY-MM-DD',
          viewMode: 'days',
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
            //所有分类加载完毕
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
        }).then(function() {
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
      
      
      var pNames, pDatas, mNames, mDatas;
      function drawTable1Chart(component) {
        pNames = []
        , pDatas = [[],[],[]]
        , mNames = []
        , mDatas = [[],[],[]];
        _.forEach(TABLE_DATA, function(row) {
          var product = row[0]
          , market = row[6]
          , price = row[1]
          , inc = row[3]
          , trade = row[4]
          , selectProduct = $('#chartProduct option:selected').text()
          , selectMarket = $('#chartMarket option:selected').text();
          if (product == selectProduct ) {
            mNames.push(market);
            mDatas[0].push(price);
            mDatas[1].push(inc);
            mDatas[2].push(trade);  
          }
          if (market == selectMarket ) {
            pNames.push(product);
            pDatas[0].push(price);
            pDatas[1].push(inc);
            pDatas[2].push(trade);
          }
        });
        _drawChart1();
        _drawChart2();
      }

      function _drawChart1() {
        drawMyChart(
          $('#table1Chart1'),
          dui_tbl._getTable($('#table1').find('.dui-component')),
          [
            {
              name: '市场',
              data: mNames
            },
            {
              name: $('.type-condition.chart1-condition:checked').text(),
              data: mDatas[$('.type-condition.chart1-condition:checked').val()]
            }
          ],
          'bar1'
        );
      }

      function _drawChart2() {
        drawMyChart(
          $('#table1Chart2'),
          dui_tbl._getTable($('#table1').find('.dui-component')),
          [
            {
              name: '农产品',
              data: pNames
            },
            {
              name: $('.type-condition.chart2-condition:checked').text(),
              data: pDatas[$('.type-condition.chart2-condition:checked').val()]
            }
          ],
          'bar1'
        );
      }

    </script>
    
    <div id="footer">
		
	</div>
  </body>
</html>