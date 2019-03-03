var OP = {
      EQUAL:'1',
      LIKE:'2',
      GREATER:'3',
      LESS:'4',
    };
var TYPE = {
  SINGLE:'1',
  COMPISITION:'2',
};
var COMPOSITION = {
  AND:'1',
  OR:'2',
};

function genCondition(id){
  var component = $('#'+id);
  var object =  {};
  if (component.data('type') == 1) {
    object.type = 1;
    object.name = component.find('[name="name"]').val();
    object.op = component.find('[name="op"]').val();
    object.value = component.find('[name="value"]').val();
  }else if (component.data('type') == 2) {
    object.type = 2;
    object.left = genCondition(component.find('[name="left"]').val());
    object.right = genCondition(component.find('[name="right"]').val());
    object.compostion = component.find('[name="composition"]').val();;
  };
  return object;
}

function con2strImpl (conditon) {
  var opStr = null;
  var opValue = conditon.value;
  var prefix = $('#condition-prefix').val();
  prefix = prefix ? prefix + '.' : '';
  switch (conditon.op) {
  case OP.EQUAL:
    opStr = " %3D ";
    break;
  case OP.LIKE:
    opStr = " like "; 
    opValue = "%25" + conditon.value + "%25";
    break;
  case OP.GREATER:
    opStr = " > ";
    break;
  case OP.LESS:
    opStr = " < ";
    break;
  default:
    break;
  }

  return prefix + conditon.name + opStr + "'" + opValue + "'"; 
}

function con2str (conditon) {
  var string = null;
  if (conditon.type == TYPE.SINGLE) {
    string = con2strImpl(conditon); 
  }else if (conditon.type == TYPE.COMPISITION) {
    var contStr = null;
    switch (conditon.compostion) {
    case COMPOSITION.AND:
      contStr = " and ";
      break;
    case COMPOSITION.OR:
      contStr = " or ";
      break;
    default:
      break;
    }
    string = "("+ con2strImpl(conditon.left) + contStr + con2strImpl(conditon.right)  +")";
  }
  return string;
}