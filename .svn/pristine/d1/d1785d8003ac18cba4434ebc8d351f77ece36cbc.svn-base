function options() {
	var _default = {
		table : null,
		chartPanel : null,
		indexArray : null,
		chartType : 'line1',
		title : {
			text : '',
			subtext : '',
			x : 'center'
		},
		legend : {
			x : 'left',
			data : [],
			selected : {

			}
		},
		tooltip : {
			show : true,
			trigger : 'axis',
			axisPointer : {
				show : true,
				type : 'cross',
				lineStyle : {
					type : 'dashed',
					width : 1
				}
			}
		},
		calculable : true,
		toolbox : {
			show : false,
			feature : {
				mark : {
					show : true
				},
				dataView : {
					show : true,
					readOnly : false
				},
				restore : {
					show : true
				},
				saveAsImage : {
					show : true
				}
			}

		},
		/*
		 * axis:{ axisLine:{ show:false }, splitLine :{ show:false } },
		 */
		xAxis : [],
		yAxis : [ {
			type : 'value',
			axisLine : {},
			name : '',
			splitLine : {
				show : true,
				lineStyle : {
					color : [ '#f5f5f5' ],
					width : 1,
					type : 'dotted'
				}
			}
		}, {
			type : 'value',
			axisLine : {},
			splitLine : {
				show : true,
				lineStyle : {
					color : [ '#f5f5f5' ],
					width : 1,
					type : 'dotted'
				}
			}
		} ],
		series : []
	}

	this.initOption = function() {
		return _default;
	}
}
var jsonData = [ {// 数组的第一个元素为x轴，其他的为y轴数据
	name : 'aa', // 必须，根据该值获取数据，当data不为空时，
	label : 'aa', // 可选
	type : 'bar', // 可选，默认折线line
	unit : '', // 可选，默认没有单位
	yAxisIndex : 0, // 可选，默认是0，左坐标，1表示右坐标
	data : []
// 可选，当数组长度为0时，调用dui_tbl.getDatas($table, name) 获取数据
} ];

// 创建ECharts [折线图1]
function drawMyChart($chartPanel, $table, indexArray, _type,_theme) {
	var theme=_theme ? _theme:'macarons';
	var chartType=_type ? _type : 'line1'
	var myChart = echarts.init($chartPanel[0],theme);
	
	// 图表显示提示信息
	myChart.showLoading({
		text : "图表数据正在努力加载..."
	});
	myChart.hideLoading();
	// 初始化option
	var option = (new options()).initOption();
	// 设置option
	option.chartPanel = $chartPanel[0];
	option.table = $table[0];
	option.indexArray = indexArray;
	option.chartType = chartType;
	var dataArr = getDataArray($table, indexArray);
	// option = getDefaultOption(option, dataArr);
	switch (chartType) {
	case 'line1':
		option = getDefaultOption(option, dataArr, 'line');
		break;
	case 'bar1':
		option = getDefaultOption(option, dataArr, 'bar');
		break;
	case 'pie1':
		option = getKeyValOption(option, dataArr, 'pie');
		break;
	case 'mix1':// 折柱混搭
		option = getDefaultOption(option, dataArr, 'line');
		break;
	case 'map1':// 地图
		option = getDefaultMapOption(option, dataArr, 'map');
		break;
	case 'scatter1':// 气泡图（x,y,z）
		option = getDefaultScatterOption(option, dataArr, 'scatter');
		break;
	default:
		option = getDefaultOption(option, dataArr, 'line');
		break;
	}
	myChart.setOption(option);
	// ECharts图表的click事件监听
	myChart.on("click", function() {
		// alert("你点击我了!");
	});
	return myChart;
}

function getDataArray($table, indexArray) {
	var dataArray = [];
	var item;
	// x轴数据
	if (indexArray[0] && indexArray[0].data && indexArray[0].data.length > 0) {// x轴已有数据
		
		dataArray.push({
			name : indexArray[0].label ? indexArray[0].label : $(
					'#t-h-' + indexArray[0].name).text(),
			data : indexArray[0].data,
			yAxis : []
		});
	} else if (indexArray[0] && indexArray[0].name) {
		dataArray.push({
			name : indexArray[0].label ? indexArray[0].label : $('#t-h-' + indexArray[0].name).text(),
			data : dui_tbl.getDatas($table, indexArray[0].name),
			yAxis : []
		});
	} else {// x轴没有数据，程序通过表名和indexArray[0].name获取数据
		item = getTableHeadDataByIndex($table);
		dataArray.push({
			name : item.name,
			data : item.data,
			yAxis : []
		});
	}
	// 双坐标配置
	if (indexArray[0] && indexArray[0].yAxis && indexArray[0].yAxis.length > 0) {
		for (var j = 0; j < indexArray[0].yAxis.length; j++) {
			dataArray[0].yAxis[j] = indexArray[0].yAxis[j];
		}
	}
	for (var i = 1; i < indexArray.length; i++) {
		if (indexArray[i].data && indexArray[i].data.length > 0) {// y轴有数据
			dataArray.push({
				name : indexArray[i].label ? indexArray[i].label : $(
						'#t-h-' + indexArray[i].name).text(),
				type : indexArray[i].type ? indexArray[i].type : '',
				unit : indexArray[i].unit ? indexArray[i].unit : '',
				location : indexArray[i].location ? indexArray[i].location
						: '广西',
				data : indexArray[i].data,
				yAxisIndex : indexArray[i].yAxisIndex
			});
		} else {// 获取y轴数据
			dataArray.push({
				name : indexArray[i].label ? indexArray[i].label : $(
						'#t-h-' + indexArray[i].name).text(),
				type : indexArray[i].type ? indexArray[i].type : '',
				unit : indexArray[i].unit ? indexArray[i].unit : '',
				location : indexArray[i].location ? indexArray[i].location
						: '广西',
				data : dui_tbl.getDatas($table, indexArray[i].name),
				yAxisIndex : indexArray[i].yAxisIndex
			});
		}
	}
	return dataArray;
}

// 默认option，支持普通的折线和柱状图
function getDefaultOption(option, dataArr, chartType) {
	// y轴样式
	if (dataArr[0].yAxis && dataArr[0].yAxis.length > 0) {
		for (var j = 0; j < dataArr[0].yAxis.length; j++) {
			option.yAxis[j].name = dataArr[0].yAxis[j].name;
		}
	}

	var xdata = dataArr[0].data;// x轴数据
	var serie = {};
	var xitem = {
		type : 'category',
		data : xdata,
		splitLine : {
			show : false
		}
	}
	option.xAxis.push(xitem);
	// 遍历y轴数据
	for (var i = 1; i < dataArr.length; i++) {
		serie = {
			name : dataArr[i].name,
			type : dataArr[i].type.length < 1 ? chartType : dataArr[i].type,
			data : dataArr[i].data,
			itemStyle : {
				normal : {
					label : {
						show : false,
						position : 'top',
						textStyle : {
							fontSize : '14',
							fontFamily : '宋体',
							fontWeight : 'bold'
						}
					}
				}
			}
		}
		serie.yAxisIndex = dataArr[i].yAxisIndex;
		option.legend.data.push(dataArr[i].name);
		option.series.push(serie);
	}

	return option;
}

function getMixOption(option, dataArr, chartType) {
	var xdata = dataArr[0].data;// x轴数据
	var serie = {};
	var xitem = {
		type : 'category',
		data : xdata,
		splitLine : {
			show : false
		}
	}
	option.xAxis.push(xitem);
	// 遍历y轴数据
	for (var i = 1; i < dataArr.length; i++) {
		serie = {
			name : dataArr[i].name,
			type : chartType,
			data : dataArr[i].data,
			itemStyle : {
				normal : {
					label : {
						show : false,
						position : 'top',
						textStyle : {
							fontSize : '14',
							fontFamily : '宋体',
							fontWeight : 'bold'
						}
					}
				}
			}
		}
		serie.yAxisIndex = dataArr[i].yAxisIndex;
		option.legend.data.push(dataArr[i].name);
		option.series.push(serie);
	}
	return option;
}

// key-value 数据【饼图】
function getKeyValOption(option, dataArr, chartType) {
	// option = getKeyValOption(option,dataArr,'pie');

	/*
	 * 数据格式 data:[ {value:335, name:'直接访问'}, {value:310, name:'邮件营销'},
	 * {value:234, name:'联盟广告'}, {value:135, name:'视频广告'}, {value:1548,
	 * name:'搜索引擎'} ]
	 * 
	 * legend: { orient : 'vertical', x : 'left',
	 * data:['直接访问','邮件营销','联盟广告','视频广告','搜索引擎'] },
	 */

	var xdata = dataArr[0].data;// x轴数据
	var serie = {};
	var xitem = {
		orient : 'vertical',
		data : xdata,
		x : 'left'
	}
	option.xAxis.push(xitem);
	// 遍历y轴数据
	for (var i = 1; i < 2; i++) {
		var yArray = new Array();
		for (var j = 0; j < dataArr[i].data.length; j++) {
			yArray.push({
				value : dataArr[i].data[j],
				name : xdata[j]
			});
		}
		serie = {
			name : dataArr[i].name,
			type : chartType,
			data : yArray,
			radius : '70%',
			center : [ '50%', '50%' ]
		}
		option.xAxis = null;
		option.yAxis = null;
		option.tooltip.trigger = 'item';
		option.tooltip.formatter = "{a} <br/>{b} : {c} ({d}%)";
		option.series[0] = (serie);
	}
	return option;
}

// 地图
function getDefaultMapOption(option, dataArr, chartType) {
	var max = dataArr[1].data[0], min = dataArr[1].data[0];
	var yArray = new Array();
	for (var j = 0; j < dataArr[1].data.length; j++) {
		max = dataArr[1].data[j] > max ? dataArr[1].data[j] : max;
		min = dataArr[1].data[j] < min ? dataArr[1].data[j] : min;
		yArray.push({
			value : dataArr[1].data[j],
			name : dataArr[0].data[j]
		});
	}
	serie = {
		name : dataArr[1].name,
		type : chartType,
		data : yArray,
		mapType : dataArr[1].location,
		itemStyle : {
			normal : {
				label : {
					show : true
				},
				areaStyle : {
					color : '#f5f5f5'
				}
			},
			emphasis : {
				label : {
					show : true
				},
				areaStyle : {
					color : '#FFE4C4'
				}
			}
		},
		roam : true

	}
	option.dataRange = {
		min : parseInt(min) - 1,
		max : parseInt(max) + 1,
		color : [ 'green', '#FBFFFD' ],
		// color:['orange','yellow'],
		text : [ '高', '低' ], // 文本，默认为数值文本
		calculable : true
	}
	option.legend.showLegendSymbol = true;
	option.legend.data[0] = dataArr[1].name;
	option.xAxis = null;
	option.yAxis = null;
	option.tooltip.trigger = 'item';
	option.tooltip.formatter = "{a} <br/>{b} : {c}  " + dataArr[1].unit;
	option.series[0] = (serie);
	return option;
}

// 气泡图
function getDefaultScatterOption(option, dataArr, chartType) {
	var d = [];
	var dd = [];
	var serie = {};
	for (var t = 0; t < dataArr.length; t = t + 4) {
		if (t + 4 > dataArr.length)
			break;
		for (var i = 0; i < dataArr[t].data.length; i++) {
			d.push([ dataArr[t].data[i], dataArr[t + 1].data[i],
					dataArr[t + 2].data[i], dataArr[t + 3].data[i] ]);
		}
		for (var i = 0; i < dataArr[t].data.length; i++) {
			dd.push([ dataArr[t].data[i], dataArr[t + 1].data[i] ]);
		}
		serie = {
			name : dataArr[t].name,
			type : 'scatter',
			symbolSize : function(value) {
				return Math.round(value[2] / 5);
			},
			data : d,
			itemStyle : {
				normal : {
					formatter : '{a}',
					borderWidth : 1,
					label : {
						show : true,
						position : 'inside',
						formatter : function(a, b, c) {
							return a.data[3]
						}
					},
					areaStyle : {
						color : '#f5f5f5'
					}
				},
				emphasis : {
					label : {
						show : true,
						position : 'center'
					},
					areaStyle : {
						color : '#FFE4C4'
					}
				}
			}
		}
		option.legend.data.push(dataArr[t].name);
		option.series.push(serie);
		d = [];
	}

	/*
	 * for (var i = 0; i < dataArr[0].data.length; i++) {
	 * d.push([dataArr[0].data[i], dataArr[1].data[i], dataArr[2].data[i]]); }
	 */

	var xitem = {
		type : 'value',
		splitNumber : 4,
		scale : true,
		splitLine : {
			show : false
		}
	}
	option.xAxis.push(xitem);
	var yitem = {
		type : 'category',// category
		splitNumber : 4,
		scale : true,
		splitLine : {
			show : true
		}
	}
	option.yAxis.push(yitem);

	/*
	 * var d = []; for (var i = 0; i < dataArr[0].data.length; i++) {
	 * d.push([dataArr[0].data[i], dataArr[1].data[i], dataArr[3].data[i]]); }
	 * serie = { name : 'scatter2', type : 'scatter', symbolSize :
	 * function(value) { return Math.round(value[2] / 5); }, data : d }
	 * option.legend.data.push('scatter2'); option.series.push(serie);
	 */
	return option;
}

$('.chartSwitch').click(function() {
	var type = $(this).data('chart');
	myChart1 = chartTools(this, myChart1);
	$(this).siblings().removeClass("active");
	$(this).addClass("active")
});
function chartTools(ele, myChart) {
	var type = $(ele).data('type');
	var option = myChart.getOption();
	myChart = drawMyChart($(option.chartPanel), $(option.table),
			option.indexArray, type);
	return myChart;
}

function getDataInfo($table) {
	// 构造数据返回对象
	var data_info = {
		x : new Array(),
		y : new Array(),
		colCount : 0
	};
	var table = $table;
	var tbody = table.children("tbody").first();
	var thead = table.children("thead");
	var colCount = tbody.children("tr").first().children("td").length;
	data_info.colCount = colCount;
	var head_tr = thead.children("tr");
	var thCount = head_tr.length;
	var index = 0;
	var x_index;
	var y_index = new Array();
	var item;
	var _type;
	if (thCount < 2) {// 简单表头
		head_th = head_tr.children("th");
		for (var n = 0; n < head_th.length; n++) {
			if (n == 0) {
				item = {
					index : n,
					name : $(head_th[n]).text()
				}
				data_info.x.push(item);
			} else {
				item = {
					index : n,
					name : $(head_th[n]).text()
				}
				data_info.y.push(item);
			}
		}
	} else {// 复合表头
		head_tr.children("th").each(function() {
			if ($(this).hasClass("x")) {
				item = {
					index : $(this).cellPos().left,
					name : $(this).text()
				}
				data_info.x.push(item);
			} else if ($(this).hasClass("y")) {
				if ($(this).hasClass("y1")) {// 折线
					_type = "line";
				} else if ($(this).hasClass("y2")) {// 柱状
					_type = "bar";
				} else {
					_type = "bar";
				}
				item = {
					index : $(this).cellPos().left,
					name : $(this).text(),
					type : _type
				}
				data_info.y.push(item);
			}
		});
	}// 处理复合表头完毕

	return data_info;

}
/** ****************start*************************** */
// 使用方法
// $(this).cellPos().left,
// $(this).cellPos().top
function scanTable($table) {
	var m = [];
	$table.children("tr").each(
			function(y, row) {
				$(row).children("td, th")
						.each(
								function(x, cell) {
									var $cell = $(cell), cspan = $cell
											.attr("colspan") | 0, rspan = $cell
											.attr("rowspan") | 0, tx, ty;
									cspan = cspan ? cspan : 1;
									rspan = rspan ? rspan : 1;
									for (; m[y] && m[y][x]; ++x)
										; // skip already occupied cells in
									// current row
									for (tx = x; tx < x + cspan; ++tx) { // mark
										// matrix
										// elements
										// occupied
										// by
										// current
										// cell
										// with
										// true
										for (ty = y; ty < y + rspan; ++ty) {
											if (!m[ty]) { // fill missing rows
												m[ty] = [];
											}
											m[ty][tx] = true;
										}
									}
									var pos = {
										top : y,
										left : x
									};
									$cell.data("cellPos", pos);
								});
			});
};
$.fn.cellPos = function(rescan) {
	var $cell = this.first(), pos = $cell.data("cellPos");
	if (!pos || rescan) {
		var $table = $cell.closest("table, thead, tbody, tfoot");
		scanTable($table);
	}
	pos = $cell.data("cellPos");
	return pos;
}

function getTableHeadDataByIndex($table) {
	var table = $table[0];
	var $tbody = $table.children("tbody").first();
	var $thead = $table.children("thead").first();
	var trs = $tbody.children("tr");
	var head_trs = $thead.children("tr");
	var head_col = head_trs.children("th");
	var data = new Array();
	if ($table.hasClass('h')) {// 行
		for (var i = 1; i < head_col.length; i++) {
			data.push($($thead[0].rows[0].cells[i]).text());
		}
	} else {
		for (var i = 0; i < trs.length; i++) {
			data.push($($tbody[0].rows[i].cells[0]).text());
		}
	}
	var itemData = {
		name : $($thead[0].rows[0].cells[0]).text(),
		data : data
	}
	return itemData;
}
