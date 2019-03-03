function actionFormatter (cellvalue, options, rowObject)
{
	return "<div style=\"margin-left:8px;\"><div style=\"float:left;cursor:pointer;\" class=\"ui-pg-div ui-inline-edit\" id=\"jEditButton_1\" onclick=\"editRow('"+rowObject.id+"');\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\" data-original-title=\"编辑所选记录\"><span class=\"ui-icon ui-icon-pencil\"></span></div><div style=\"float:left;margin-left:5px;cursor:pointer;\" class=\"ui-pg-div ui-inline-del\" id=\"jDeleteButton_2\" onclick=\"deleteRow('"+rowObject.id+"');\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\" data-original-title=\"删除所选记录\"><span class=\"ui-icon ui-icon-trash\"></span></div></div>";
}

function icoFormatter (cellvalue, options, rowObject)
{
	if(cellvalue == 0){
		return "<span class=\"label label-warning arrowed arrowed-right\">草稿</span>";
	}else if(cellvalue == 1){
		return "<span class=\"label label-success arrowed-in arrowed-in-right\">已发布</span>";
	}else if(cellvalue == 2){
		return "<span class=\"label label-warning arrowed arrowed-right\">未上报</span>";
	}else if(cellvalue == 3){
		return "<span class=\"label label-success arrowed-in arrowed-in-right\">已上报</span>";
	}else if(cellvalue == 4){
		return "<span class=\"label label-warning arrowed arrowed-right\">未审核</span>";
	}else if(cellvalue == 5){
		return "<span class=\"label label-success arrowed-in arrowed-in-right\">已审核通过</span>";
	}else if(cellvalue == 6){
		return "<span class=\"label label-danger arrowed\">已审核未通过</span>";
	}else if(cellvalue == 7){
		return "<span class=\"label label-warning arrowed arrowed-right\">未汇总</span>";
	}else if(cellvalue == 8){
		return "<span class=\"label label-success arrowed-in arrowed-in-right\">已汇总</span>";
	}else if(cellvalue == 9){
		return "<span class=\"label label-danger arrowed\">需重报</span>";
	}else if(cellvalue == 10){
		return "<span class=\"label label-success arrowed-in arrowed-in-right\">已延迟上报</span>";
	}else if(cellvalue == 11){
		return "<span class=\"label label-success arrowed-in arrowed-in-right\">已按时上报</span>";
	}else if(cellvalue == 12){
		return "<span class=\"label label-danger arrowed arrowed-right\">已保存</span>";
	}else if(cellvalue == 13){
		return "<span class=\"label label-warning arrowed arrowed-in-right\">提前上报</span>";
	}
	return "";
}

function showFormatter (cellvalue, options, rowObject)
{
	return "<div style=\"margin-left:8px;\"><div style=\"float:left;cursor:pointer;\" class=\"ui-pg-div ui-inline-edit\" id=\"jEditButton_1\" onclick=\"showRow('"+rowObject.id+"');\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\" data-original-title=\"查看所选记录\"><span class=\"ui-icon ui-icon-eye\"></span></div>";
}

function inputFormatter (cellvalue, options, rowObject)
{
	if(rowObject.status == 2 || rowObject.status == 9){
		return "<div style=\"margin-left:8px;\"><div style=\"float:left;cursor:pointer;\" class=\"ui-pg-div ui-inline-edit\" id=\"jEditButton_1\" onclick=\"showRow('"+rowObject.id+"');\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\" data-original-title=\"查看所选报表\"><span class=\"ui-icon ui-icon-eye\"></span></div><div style=\"margin-left:8px;\"><div style=\"float:left;cursor:pointer;\" class=\"ui-pg-div ui-inline-edit\" id=\"jEditButton_1\" onclick=\"upRow('"+rowObject.id+"');\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\" data-original-title=\"上报报表\"><span class=\"ui-icon ui-icon-share\"></span></div>";
	}else{
		return "<div style=\"margin-left:8px;\"><div style=\"float:left;cursor:pointer;\" class=\"ui-pg-div ui-inline-edit\" id=\"jEditButton_1\" onclick=\"showRow('"+rowObject.id+"');\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\" data-original-title=\"查看所选报表\"><span class=\"ui-icon ui-icon-eye\"></span></div>";
	}
}

function downFormatter (cellvalue, options, rowObject)
{
	if(rowObject.status == 0 || rowObject.status == 1 || rowObject.status == 2 || rowObject.status == 9){
		return "<div style=\"margin-left:8px;\"><div style=\"float:left;cursor:pointer;\" class=\"ui-pg-div ui-inline-edit\" id=\"jEditButton_1\" onclick=\"showRow('"+rowObject.id+"');\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\" data-original-title=\"查看所选报表\"><span class=\"ui-icon ui-icon-eye\"></span></div>";
	}else{
		return "<div style=\"margin-left:8px;\"><div style=\"float:left;cursor:pointer;\" class=\"ui-pg-div ui-inline-edit\" id=\"jEditButton_1\" onclick=\"showRow('"+rowObject.id+"');\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\" data-original-title=\"查看所选报表\"><span class=\"ui-icon ui-icon-eye\"></span></div><div style=\"margin-left:8px;\"><div style=\"float:left;cursor:pointer;\" class=\"ui-pg-div ui-inline-edit\" id=\"jEditButton_1\" onclick=\"downRow('"+rowObject.id+"');\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\" data-original-title=\"需重报报表\"><span class=\"ui-icon ui-icon-reply\"></span></div>";
	} 
}

function editFormatter (cellvalue, options, rowObject)
{
	return "<div style=\"margin-left:8px;\"><div style=\"float:left;cursor:pointer;\" class=\"ui-pg-div ui-inline-edit\" id=\"jEditButton_1\" onclick=\"editRow('"+rowObject.id+"');\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\" data-original-title=\"编辑所选记录\"><span class=\"ui-icon ui-icon-pencil\"></span></div>";
}

function auditFormatter (cellvalue, options, rowObject)
{
	return "<div style=\"margin-left:8px;\"><div style=\"float:left;cursor:pointer;\" class=\"ui-pg-div ui-inline-edit\" id=\"jEditButton_1\" onclick=\"auditRow('"+rowObject.id+"');\" onmouseover=\"jQuery(this).addClass('ui-state-hover');\" onmouseout=\"jQuery(this).removeClass('ui-state-hover');\" data-original-title=\"审核所选记录\"><span class=\"ui-icon ui-icon-cogs\"></span></div>";
}
