package com.yunforge.core.web.view;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.yunforge.common.excel.ExcelNoTemplate;

public class ExcelView extends AbstractExcelView {
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String title = URLDecoder
				.decode(request.getParameter("title"), "UTF-8");
		String columnNames = URLDecoder.decode(
				request.getParameter("columnNames"), "UTF-8");
		String columnWidth = request.getParameter("columnWidth");

		List<?> dataList = (List<?>) model.get("dataList");
		if ((dataList == null) || (dataList.size() == 0)) {
			throw new IllegalArgumentException("导出Excel失败, 原因是没有查询到数据.");
		}
		ExcelNoTemplate excel = new ExcelNoTemplate(title,
				getColNames(columnNames), getWidths(columnWidth), dataList,
				null, workbook);
		excel.setPaged(request.getParameter("isDivPage") != null);
		excel.setFirstDataColumnDisplayed(false);
		excel.prepare();

		workbook = excel.getTargetWb();

		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "Attachment; filename="
				+ new String(title.getBytes("UTF-8"), "ISO8859-1"));
	}

	public static int[] getWidths(String colWidth) {
		String[] colWidths = colWidth.split(",");
		int[] ws = new int[colWidths.length];
		for (int i = 0; i < colWidths.length; i++)
			ws[i] = Integer.parseInt(colWidths[i]);
		return ws;
	}

	public static String[][] getColNames(String colName) {
		String[] temp = colName.split(";");
		String[][] colNames = new String[temp.length][temp[0].split(",").length];
		for (int i = 0; i < colNames.length; i++) {
			String[] tempArray = temp[i].split(",");
			for (int j = 0; j < colNames[i].length; j++) {
				colNames[i][j] = tempArray[j];
			}
		}
		return colNames;
	}
}