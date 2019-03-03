//search-btn init
$('.search-btn').click(function () {
  loadTable($('#' + $(this).data('target')));
});

//custom controller init
$('.dui-controller:not(.multiple)').on(
		'click',
		'.dui-option',
		function() {
			var $controller = $(this).parents('.dui-controller'), cur = $(this)
					.data('value');
			if (previous == cur) {
				return;
			}

			$controller.find('.dui-option').removeClass('active');
			$(this).addClass('active');
			var previous = $controller.data('value');
			$controller.data('value', cur);
			$controller.trigger('change', [ cur ]);
		});

$('.dui-controller.multiple').on('click', '.dui-option', function() {
	var $controller = $(this).parents('.dui-controller')
	$(this).toggleClass('active');
	var cur = $controller.find('.dui-option.active').map(function() {
		return $(this).data('value');
	}).get().join(',');
	$controller.data('value', cur);
	$controller.trigger('change', [ cur ]);
});

//根据选择时间的不同类型触发时间控件格式
$('.date-condition').click(function(){
  var datePicker = $(this).parents('.row').find('.datePicker');
  if ($(this).val() == 'day') {
    dayPicker(datePicker);
  } else if ($(this).val() == 'week') {
	dayPicker(datePicker);
  } else if ($(this).val() == 'month') {
    monthPicker(datePicker);
  } else if ($(this).val() == 'year'){
    yearPicker(datePicker);
  };
}); 

function renderOption(datas, targets) {
	if(targets instanceof jQuery){
		targets = targets.toArray();
	}
	var _targets = [].concat(targets);
	_.forEach(_targets, function(v) {
		$(v).empty();
	});
	_.reduce(datas, function(lists, item, index) {
		return _.map(lists, function(list) {
			var option;
			if ($(list).data('custom')) {
				option = $('<li  class="dui-option" data-value="' + item.code
						+ '">' + item.name + '</li>');
				if (index == 0) {
					option.addClass('active');
					$(list).data('value', item.code);
				}
			} else {
				option = $('<option value="' + item.code + '">' + item.name
						+ '</option>');
			}
			$(list).append(option);
			return list;
		});
	}, _targets);
	$(targets).trigger('content.finish');
}

function renderSubList(parent, targets) {
    var items = $(parent).find('.dui-option.active');
    renderOption(_.map(items,function(item) {
      return {name:$(item).text(),code:$(item).data('value')};
    }),
    targets);
}

function datePickInit(option) {
	$('.datePicker').datetimepicker(option);
}

function dayPicker(datePicker) {
	// body...
	var now = new Date();
	datePicker.datetimepicker().each(function() {
		$(this).data('DateTimePicker').format('YYYY-MM-DD').viewMode('days');
	});
}

function monthPicker(datePicker) {
	datePicker.datetimepicker().each(function() {
		$(this).data('DateTimePicker').format('YYYY-MM').viewMode('months');
	});

}

function yearPicker(datePicker) {
	datePicker.datetimepicker().each(function() {
		$(this).data('DateTimePicker').format('YYYY').viewMode('years');
	});
}

function loadTable(container) {
	var condition = '.' + $(container).data('condition');
	duiHelper.load({
		container : $(container),
		cid : $(container).data('table')
				+ ($(condition + '.date-condition:checked').val() ? '_'
						+ $(condition + '.date-condition:checked').val() : ''),
		condition : duiHelper.getCondition($('.'
				+ $(container).data('condition')))
	});
}