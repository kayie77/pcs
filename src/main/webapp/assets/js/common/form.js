function ajaxFormJsonSubmit(form, successCallback) {
  var cfg = {
    success: successCallback, // post-submit callback 
    url: $(form).attr("action"),
    type: $(form).attr("method") ? $(form).attr("method") : 'post', // 'get' or 'post', override for form's 'method' attribute 
    dataType: 'json', // 'xml', 'script', or 'json' (expected server response type) 
    contentType: 'application/json',
    data: JSON.stringify($(form).serializeObject()),
    //clearForm: true        // clear all form fields after successful submit 
    //resetForm: true        // reset the form after successful submit 

    // $.ajax options can be used here too, for example: 
    //timeout:   3000 
  };
  $.ajax(cfg);
}

function serializeParams (selector) {
  var result = '';
  Array.prototype.forEach.call($(selector), function (item) {
    if (result && result!='') {
      result += '&';
    };
    result += $(item).attr('name') + '=' + encodeURIComponent($(item).val());
  });
  return result;
}

function formableAjaxSubmit (selector, opt) {
  var url;
  if (action.indexOf('?')) {
    url = opt.url + "&";
  } else {
    url = opt.url + "?";
  };
  url += serializeParams(selector);
  opt.url = url;
  $.ajax(opt);
}