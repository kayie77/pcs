/* usage: $('target').ajax_render(setting, template)
 * setting: is same with the config of the $.ajax() 
 * template: html template to be render, using {property_name} as the placeholder
 * template: format
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
    var tableElement = getDocElemet(this);
    var default_options = {
    pager: false,
    pageSize: 10,
    sort: null,
    asc: false,
    data: null,
    template: null,
    url: null
    };//template, data, url

    operations = {
      clear_table: clearTable,
      json_render_table: renderTable,
      ajax_render_table: ajaxRenderTable,
      set_params: setParams,
      reload: reloadTable,
      getRowData: getRowData,
    }

    var meat_options = processTableMeta(this);
    //init
    if (arguments.length == 1 && typeof arguments[0] == 'object')//没有传递参数 
    { 
      bindTableEvent(this);
      tableElement.options = $.extend({}, default_options, meat_options, arguments[0]);
      if (tableElement.options.data) {
        renderTable(this, tableElement.options);
      }else if (tableElement.options.url) {
        reloadTable(this);
      }
      return this;
    }

    //method call
    if (arguments.length == 2 || typeof arguments[0] == 'string')//方法调用
    { 
      func = operations[arguments[0]];
      if (func) {
        func(this, arguments[1]);
      }
      return this;
    }
 
    return this;
  }

  function renderPager(table, data) {
    var options = getDocElemet(table).options;
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

  /* 
   * jstable method
   */
  function setParams(table, params) {
    $.extend(getDocElemet(table).options, params);
    reloadTable(table);
  }

  /* 
   * jstable method
   */
 function clearTable(table){
    table.find('tbody tr').remove();
    table.next('#pager').remove(); 
  }



  /* 
   * jstable method
   */
  function loadTable (table) {
    var tableElement = getDocElemet(table);
    var options = tableElement.options;
    var cfg = {};
    cfg.template = options.template;
    if (options.pager == true) {
      cfg.url = genPageUrl(options.url, 1, options.pageSize, {filed:options.sort, order:options.asc})
    }else{
      cfg.url = options.url;
    };
    ajaxRenderTable(table, cfg);
  }

  /* 
   * jstable method
   */
  function reloadTable(table){
    clearTable(table);
    loadTable(table);
  }

  /* 
   * jstable method
   */
  function ajaxRenderTable(table, options){
    var tableElement = getDocElemet(table);
    function success (data) {
      tableElement.options.data = data; //保存最近一次的数据
      options.data = data;
      renderTable(table, options);
    };
    $.ajax({url:options.url, type:'get', dataType:'json', success:success});
  }


  /* 
   * jstable method
   */
  function renderTable(table, options){
    var tableElement = getDocElemet(table);
    if (tableElement.options.pager == true) {
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

  /* 
   * jstable method
   */
  function getRowData(table, rowId){
    var tableElement = getDocElemet(table);
    var datas = tableElement.options.data;
    for (var i = 0; i < datas.length; i++) {
      if (datas[i].id = rowId ) {
        return datas[i];
      };
    };
  }

  /* 
   * private method
   */
  function bindTableEvent(table){
    var tableElement = getDocElemet(table);
    table.on('click','thead th.sortable',function (){
      tableElement.options.sort = $(this).data('sort');
      tableElement.options.asc = !tableElement.options.asc;
      reloadTable(table);
    })
    table.on('click','tbody tr',function (){
      table.trigger('select.row.jstable', [$(this)]);
    })
  }

  /* 
   * private method
   */
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

  /* 
   * private method
   */
  function getDocElemet (jqueryObject) {
    return jqueryObject[0];
  }

  function processTableMeta (table) {
    var meta_options = {};
    var template = '<tr id={id}>{tds}</tr>';
    var ths = table.find('thead>tr>th');
    var thTemplate = Array.prototype.reduce.call(
      ths, 
      function (p, th) {
        var $th  = $(th);
        var field = $th.data('field');
        var title = $th.data('title') || '';
        var tdContext = {
          field : field,
          title : format(title, {field: field}),
          cssclass : $th.data('cssclass') || '',
        }
        p.tds += format('<td name="{{field}}" title="{title}" class="{cssclass}">{{field}}</td>', tdContext);
        return p;
      }, 
      {tds : ''}
    );
    meta_options.template = format(template, thTemplate);
    return meta_options;
  }
}(jQuery));
