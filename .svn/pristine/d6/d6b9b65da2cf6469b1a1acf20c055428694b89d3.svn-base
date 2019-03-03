function showDialog(selector, url, callback, uid){
  var modal = $(selector);
  var last_uid = modal.data('uid') || 'none';
  if (last_uid == uid) {
    modal.modal('show'); //打开缓存
    return;
  }
  
  modal.data('uid', uid);
  $.get(url, function (data){
    var modal = $('#modalDialog');
    var modal_content =  modal.find('.modal-content');
    modal_content.html(data);
    if (callback) {
      callback();
    };
    modal.modal('show');
  });
}