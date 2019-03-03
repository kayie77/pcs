package com.yunforge.common.excel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class CrossExcel extends SimpleExcel {
	public static final String CROSS_COLUMN_PARA = "#column";
	protected List columnDatas;
	protected int dynColIndex = 1;

	protected int titleRowIndex = 2;

	protected int pageCols = 5;

	public CrossExcel(String templateFile, List datas, List columnDatas)
			throws FileNotFoundException, IOException {
		super(templateFile, datas);
		this.columnDatas = columnDatas;
	}

	public CrossExcel(HSSFWorkbook templateWb, List datas, List columnDatas) {
		super(templateWb, datas);
		this.columnDatas = columnDatas;
	}

	@Override
	public void prepare() {
		super.prepare();
		writeColumnTitle();
	}

	@Override
	protected void writeSheetData(HSSFSheet sheet, int sheetIndex) {
		HSSFRow tempRow = sheet.getRow(0);
		int startCol = (tempRow.getLastCellNum() - this.dynColIndex)
				* sheetIndex + this.dynColIndex;
		int rowIndex = 0;
		for (int i = 0; i < this.datas.size(); i++) {
			HSSFRow row = sheet.getRow(this.dataRowIndex + rowIndex);
			if (row == null)
				row = sheet.createRow(this.dataRowIndex + rowIndex);
			ListOrderedMap rowData = (ListOrderedMap) this.datas.get(i);
			for (int j = 0; j < this.dynColIndex; j++) {
				HSSFCell cell = row.getCell((short) j);
				if (cell == null)
					cell = row.createCell((short) j);
				setCellValue(cell, rowData.getValue(j));
			}
			int colIndex = 0;
			for (int j = startCol; (j < rowData.size())
					&& (colIndex < row.getLastCellNum() - 1); j++) {
				HSSFCell cell = row
						.getCell((short) (this.dynColIndex + colIndex));
				if (cell == null)
					cell = row
							.createCell((short) (this.dynColIndex + colIndex));
				setCellValue(cell, rowData.getValue(j));
				colIndex++;
			}
			rowIndex++;
		}
	}

	@Override
	protected void calcPageSize() {
		this.pageSize = (this.columnDatas.size() / this.pageCols);
		if (this.columnDatas.size() % this.pageCols > 0)
			this.pageSize += 1;
	}

	protected void writeColumnTitle() {
		for (int i = 0; i < this.targetWb.getNumberOfSheets(); i++)
			writeSheetColumnTitle(this.targetWb.getSheetAt(i), i);
	}

	protected void writeSheetColumnTitle(HSSFSheet sheet, int sheetIndex) {
		int startRow = sheetIndex * this.pageCols;
		HSSFRow titleRow = sheet.getRow(this.titleRowIndex);
		for (int i = startRow; (i < this.columnDatas.size())
				&& (i < startRow + this.pageCols); i++) {
			ListOrderedMap rowData = (ListOrderedMap) this.columnDatas.get(i);
			for (int j = this.dynColIndex; j < titleRow.getLastCellNum(); j++) {
				HSSFCell cell = titleRow.getCell((short) j);
				if (cell == null)
					continue;
				String cellValue = cell.getRichStringCellValue() == null ? ""
						: cell.getRichStringCellValue().getString();
				if (cellValue.indexOf("#column") < 0)
					continue;
				cell.setCellValue(new HSSFRichTextString((String) rowData
						.getValue(1)));
				break;
			}

		}

		int colIndex = -1;
		for (int i = this.dynColIndex; i < titleRow.getLastCellNum(); i++) {
			HSSFCell cell = titleRow.getCell((short) i);
			if (cell == null)
				continue;
			String cellValue = cell.getRichStringCellValue() == null ? ""
					: cell.getRichStringCellValue().getString();
			if (cellValue.indexOf("#column") < 0)
				continue;
			if (colIndex == -1)
				colIndex = i;
			cell.setCellValue(new HSSFRichTextString(""));
		}

		if (colIndex != -1) {
			for (int i = this.titleRowIndex + 1; i < this.dataRowIndex; i++) {
				HSSFRow row = sheet.getRow(i);
				for (int j = colIndex; j < row.getLastCellNum(); j++) {
					HSSFCell cell = row.getCell((short) j);
					if (cell == null)
						continue;
					cell.setCellValue(new HSSFRichTextString(""));
				}
			}
		}
	}

	public List getColumnDatas() {
		return this.columnDatas;
	}

	public void setColumnDatas(List columnDatas) {
		this.columnDatas = columnDatas;
	}

	public int getDynColIndex() {
		return this.dynColIndex;
	}

	public void setDynColIndex(int dynColIndex) {
		this.dynColIndex = dynColIndex;
	}
}