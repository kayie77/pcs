function format(template_str, object) {
  var result = template_str; 
  for (var key in object) {
      var value = object[key] || "";
      if (undefined != value) {
          result = result.replace(new RegExp("\\{" + key + "\\}", 'g'), value);
      }
  }
  return result;
}