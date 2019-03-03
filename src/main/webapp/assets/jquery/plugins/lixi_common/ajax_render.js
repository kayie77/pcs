/* usage: $('target').ajax_render(setting, template)
 * setting: is same with the config of the $.ajax() 
 * template: html template to be render, using {property_name} as the placeholder
 
*/
(function ( $ ) {
    function render(data, template, position, target) {
      var elementStr;
      if (data instanceof Array) {
        // data.forEach(function (e) {
        //   var element = $(format(template, e));
        //   element.appendTo(target);
        // });
        elementStr = data.reduce(function (p,c,index) {
          c['_index'] = index + 1;
          return p + format(template, c);
        },'');
      }else{
        elementStr = format(template, data);
      }
      position = position ? position : 'after';
      if (position === 'in_after') {
        $(elementStr).appendTo(target);   
      } else if (position === 'after') {
        $(elementStr).insertAfter(target);   
      };
    }

    //{ajax:, template, position, finish}
    $.fn.ajax_render = function (cfg) {
        var default_options = { 
          dataFilter : function  (data) {
            return data;
          },
          ajax : {
            success:      success,  // post-submit callback 
            dataType:     'json',   // 'xml', 'script', or 'json' (expected server response type)
          }
        };
        var target = this;
        cfg = $.extend(true, default_options, cfg);
        var setting = cfg.ajax;
        var template = cfg.template;
        var position = cfg.position;
        var callback = cfg.finish;
        var dataFilter = cfg.dataFilter;
        $.ajax(setting);

        function success(data) {
          render(dataFilter(data), template, position, target);
          if (callback) {
            callback(target, data);
          };
        }
        return this;
    }

    $.fn.json_render = function (data, template, position) {
      render(data, template, position, this);
      return this;
    }

 
}( jQuery ));
