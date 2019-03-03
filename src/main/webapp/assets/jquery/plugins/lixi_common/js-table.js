/* usage: $('target').ajax_render(setting, template)
 * setting: is same with the config of the $.ajax() 
 * template: html template to be render, using {property_name} as the placeholder
 
*/
(function($) {
  function render(data, template, position, target) {
    var elementStr;
    if (data instanceof Array) {
      // data.forEach(function (e) {
      //   var element = $(format(template, e));
      //   element.appendTo(target);
      // });
      elementStr = data.reduce(function(p, c, index) {
        c['_index'] = index + 1;
        return p + format(template, c);
      }, '');
    } else {
      elementStr = format(template, data);
    }
    position = position ? position : 'after';
    if (position === 'in_after') {
      $(elementStr).appendTo(target);
    } else if (position === 'after') {
      $(elementStr).insertAfter(target);
    };
  }

  //{ajax:, template}
  $.fn.jstable = function() {
    var default_options = {pager:false, pageSize:10, sort: null, asc: false};//template, data, url

    operations = {
      clear_table: clearTable,
      json_render_table: renderTable,
      ajax_render_table: ajaxRenderTable,
      set_params: setParams,
      reload: reloadTable.bind(null, this),
      getRowData: getRowData,
    }
    if (arguments.length == 2 || typeof arguments[0] == 'string')//方法调用
    { 
      func = operations[arguments[0]];
      if (func) {
        func(this, arguments[1]);
      }
      return this;
    }

    //init
    if (arguments.length == 1 && typeof arguments[0] == 'object')//没有传递参数 
    { 
      bindTableEvent(this);
      this[0].options = $.extend({}, default_options, arguments[0]);
      if (this[0].options.data) {
        renderTable(this, this[0].options);
      }else if (this[0].options.url) {
        reloadTable(this);
      }
      return this;
    }

    // var default_options = {
    //   success: success, // post-submit callback 
    //   dataType: 'json', // 'xml', 'script', or 'json' (expected server response type) 
    // };
    // options = $.extend({}, default_options, options);
 
    return this;
  }

  function renderPager(table, data) {
    var options = table[0].options;
    var pager = '<div id="pager">\
                  <p class="page-info pull-left">\
                    第<span id="page-cur">{number}</span>页/共<span id="page-total">{totalPages}</span>页\
                  </p>\
                  <div class="page-action text-center">\
                    <a href="#" id="pre">上一页</a>&nbsp;&nbsp;\
                    <a href="#" id="next">下一页</a>\
                  </div>\
                </div>'
    table.json_render(data, pager);

    pager = table.next('#pager');
    pager.width(table.width());
    $(window).resize(function(){
      pager.width(table.width());
    });
    var size = options.pageSize;
    pager.find("#pre").click(
      function  () {
        var page = Number(pager.find('#page-cur').text()) - 1;
        if (page < 1) {
          return
        };
        clearTable(table);
        var cfg = {};
        cfg.url = genPageUrl(options.url, page, size, {filed:options.sort, order:options.asc})
        cfg.template = options.template;
        ajaxRenderTable(table, cfg);
      }
    );
    pager.find('#next').click(
      function () {
        var page = Number(pager.find('#page-cur').text()) + 1;
        var total = Number(pager.find('#page-total').text());
        if (page > total) {
          return;
        };
        clearTable(table);
        var cfg = {};
        cfg.url = genPageUrl(options.url, page, size, {filed:options.sort, order:options.asc})
        cfg.template = options.template;
        ajaxRenderTable(table, cfg);
      }
    );
  }

  function setParams(table, params) {
    $.extend(table[0].options, params);
    reloadTable(table);
  }

 function clearTable(table){
    table.find('tbody tr').remove();
    table.next('#pager').remove(); 
  }

  function reloadTable(table){
    clearTable(table);
    loadTable(table);
  }

  function loadTable (table) {
    var options = table[0].options;
    var cfg = {};
    cfg.template = options.template;
    if (options.pager == true) {
      cfg.url = genPageUrl(options.url, 1, options.pageSize, {filed:options.sort, order:options.asc})
    }else{
      cfg.url = options.url;
    };
    ajaxRenderTable(table, cfg);
  }
  /**
   * params {ajax_cfg:value}
   *
   * 
   */
  function ajaxRenderTable(table, options){
    function success (data) {
      table[0].options.data = data; //保存最近一次的数据
      options.data = data;
      renderTable(table, options);
    };
    $.ajax({url:options.url, type:'get', dataType:'json', success:success});
  }

  function renderTable(table, options){
    if (table[0].options.pager == true) {
      table.find('tbody').json_render(options.data.content, options.template, 'in_after');
      pageInfo = {number: options.data.number+1,
        totalPages: options.data.totalPages,
      }
      renderPager(table, pageInfo);
    }else{
      table.find('tbody').json_render(options.data, options.template, 'in_after');
    }
    table.trigger('finish.table.jstable', [table]);
  }

  function bindTableEvent(table){
    table.on('click','thead th.sortable',function (){
      table[0].options.sort = $(this).data('sort');
      table[0].options.asc = !table[0].options.asc;
      reloadTable(table);
    })
    table.on('click','tbody tr',function (){
      table.trigger('select.row.jstable', [$(this)]);
    })
  }

  function getRowData(table, rowId){
    var tableElement = table[0];
    var datas = tableElement.options.data;
    for (var i = 0; i < datas.length; i++) {
      if (datas[i].id = rowId ) {
        return datas[i];
      };
    };
  }

  function genPageUrl (url, page, size, sort) {
    var pageParam = 'page='+ page + '&size=' + size;
    if (sort.filed != "undefined" && sort.filed!=null) {
      pageParam += '&sort=' + sort.filed + ',' + (sort.order ? 'asc' : 'desc');
    };
    if ( url.indexOf('?') > 0 ){
      return url + '&' + pageParam;
    }else{
      return url + '?' + pageParam;
    }
  }

}(jQuery));
