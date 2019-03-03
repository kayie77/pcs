package com.yunforge.common.excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

public class ExcelTest {
	public static void main(String[] args) throws IOException {
		noTemplate();
	}

	public static void noTemplate() throws IOException {
		String title = "产销存情况";
		String[][] columnNames = {
				{ "#", "#", "产量", "#", "#", "销量", "#", "库存", "#", "#" },
				{ "单位编码", "单位名称", "本期", "同期", "上期", "本期", "同期", "上期", "本期",
						"同期" } };
		int[] columnWidths = { 100, 150, 100, 100, 100, 100, 100, 100, 100, 100 };

		List datas = getData();

		ExcelNoTemplate excel = new ExcelNoTemplate(title, columnNames,
				columnWidths, datas, null, null);
		excel.setPaged(false);
		excel.setFirstDataColumnDisplayed(false);
		excel.prepare();
		excel.save("c:\\temp.xls");
	}

	public static void simple() throws IOException {
		List list = getData();
		String templateFile = "c:\\temp\\temp.xls";
		String outPath = "c:\\out.xls";

		SimpleExcel excel = new SimpleExcel(templateFile, list);

		excel.addParameter("year", "2008");
		excel.addParameter("month", "08");

		excel.setScale(2);
		excel.setPageRows(5);
		excel.prepare();
		excel.save(outPath);
	}

	public static List<ListOrderedMap> getData() {
		List list = new ArrayList();

		ListOrderedMap rowData = new ListOrderedMap();
		rowData.put("code", "111");
		rowData.put("name", Double.valueOf(234.554D));
		rowData.put("pro", Double.valueOf(345.89600000000002D));
		rowData.put("sal", Double.valueOf(456.48700000000002D));
		rowData.put("sto", Double.valueOf(694.26300000000003D));
		rowData.put("aa", Double.valueOf(234.554D));
		rowData.put("bb", Double.valueOf(234.554D));
		rowData.put("dd", Double.valueOf(234.554D));
		rowData.put("ff", Double.valueOf(234.554D));
		rowData.put("gg", Double.valueOf(234.554D));
		list.add(rowData);

		rowData = new ListOrderedMap();
		rowData.put("code", "111");
		rowData.put("name", Double.valueOf(234.554D));
		rowData.put("pro", Double.valueOf(665.89599999999996D));
		rowData.put("sal", Double.valueOf(432.54300000000001D));
		rowData.put("sto", Double.valueOf(766.24599999999998D));
		rowData.put("aa", Double.valueOf(234.554D));
		rowData.put("bb", Double.valueOf(234.554D));
		rowData.put("dd", Double.valueOf(234.554D));
		rowData.put("ff", Double.valueOf(234.554D));
		rowData.put("gg", Double.valueOf(234.554D));
		list.add(rowData);

		rowData = new ListOrderedMap();
		rowData.put("code", "111");
		rowData.put("name", Double.valueOf(234.554D));
		rowData.put("pro", Double.valueOf(977.86500000000001D));
		rowData.put("sal", Double.valueOf(583.34400000000005D));
		rowData.put("sto", Double.valueOf(853.85400000000004D));
		rowData.put("aa", Double.valueOf(234.554D));
		rowData.put("bb", Double.valueOf(234.554D));
		rowData.put("dd", Double.valueOf(234.554D));
		rowData.put("ff", Double.valueOf(234.554D));
		rowData.put("gg", Double.valueOf(234.554D));
		list.add(rowData);

		for (int i = 0; i < 25; i++) {
			rowData = new ListOrderedMap();
			rowData.put("code", "454" + i);
			rowData.put("name", Double.valueOf(234.554D + i));
			rowData.put("pro", Double.valueOf(977.86500000000001D));
			rowData.put("sal", Double.valueOf(583.34400000000005D));
			rowData.put("sto", Double.valueOf(853.85400000000004D));
			rowData.put("aa", Double.valueOf(234.554D));
			rowData.put("bb", Double.valueOf(234.554D));
			rowData.put("dd", Double.valueOf(234.554D));
			rowData.put("ff", Double.valueOf(234.554D));
			rowData.put("gg", Double.valueOf(234.554D));
			list.add(rowData);
		}

		return list;
	}
}