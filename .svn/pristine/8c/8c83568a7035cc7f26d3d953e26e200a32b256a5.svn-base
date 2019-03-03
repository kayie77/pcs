package com.yunforge.common.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.yunforge.common.util.NumberUtils;

public class SimpleExcel {
	public static final String PARAMETER_PREFIX = "#";
	protected boolean isPaged = false;

	protected int pageRows = 300;

	protected int dataRowIndex = 6;

	protected int scale = 2;
	protected HSSFWorkbook targetWb;
	protected int pageSize = 1;

	protected int sheetIndex = 1;
	protected List datas;
	protected String[] dataIndex;
	protected boolean firstDataColumnDisplayed = true;

	protected Map<String, String> parameters = new HashMap();
	protected HSSFCellStyle styleCenter;
	protected HSSFCellStyle styleRight;
	protected HSSFCellStyle styleLeft;

	public SimpleExcel() {
	}

	public SimpleExcel(String templateFile, List datas)
			throws FileNotFoundException, IOException {
		POIFSFileSystem fsfile = new POIFSFileSystem(new FileInputStream(
				templateFile));
		this.targetWb = new HSSFWorkbook(fsfile);
		this.datas = datas;
		this.dataIndex = this.dataIndex;
		this.styleCenter = this.targetWb.createCellStyle();
		this.styleRight = this.targetWb.createCellStyle();
		this.styleLeft = this.targetWb.createCellStyle();
	}

	public SimpleExcel(HSSFWorkbook templateWb, List datas) {
		this.targetWb = templateWb;
		this.datas = datas;
		this.dataIndex = this.dataIndex;
	}

	public void prepare() {
		calcPageSize();
		createSheet();
		writeParameter();
		writeData();
	}

	public void out(OutputStream out) throws IOException {
		this.targetWb.write(out);
	}

	public void save(String outPath) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(outPath);
		this.targetWb.write(fileOut);
		fileOut.close();
	}

	public void addParameter(String paraName, String paraValue) {
		this.parameters.put(paraName, paraValue);
	}

	public void removeParameter(String paraName) {
		this.parameters.remove(paraName);
	}

	protected void createSheet() {
		for (int i = 0; i < this.pageSize; i++) {
			this.targetWb.cloneSheet(0);
			this.targetWb
					.setSheetName(i + 1, Integer.toString(this.sheetIndex));
			this.sheetIndex += 1;
		}
		this.targetWb.removeSheetAt(0);
	}

	protected void calcPageSize() {
		if (isPaged()) {
			this.pageSize = (10 / this.pageRows);
			if (10 % this.pageRows > 0)
				this.pageSize += 1;
		}
	}

	protected void writeParameter() {
		for (int i = 0; i < this.targetWb.getNumberOfSheets(); i++)
			writeSheetParameter(this.targetWb.getSheetAt(i));
	}

	protected void writeSheetParameter(HSSFSheet sheet) {
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			HSSFRow row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				HSSFCell cell = row.getCell((short) j);
				if ((cell == null) || (cell.getCellType() != 1))
					continue;
				String value = cell.getRichStringCellValue() == null ? ""
						: cell.getRichStringCellValue().getString();
				Iterator iter = this.parameters.keySet().iterator();
				while (iter.hasNext()) {
					String paraName = (String) iter.next();
					if (value.indexOf("#" + paraName) >= 0)
						value = value.replaceAll("#" + paraName,
								this.parameters.get(paraName));
				}
				cell.setCellValue(new HSSFRichTextString(value));
			}
		}
	}

	protected void writeData() {
		for (int i = 0; i < this.targetWb.getNumberOfSheets(); i++) {
			if (this.dataIndex == null) {
				if (isPaged())
					writeSheetData(this.targetWb.getSheetAt(i), i);
				else {
					writeSheetData(this.targetWb.getSheetAt(i));
				}
			} else if (isPaged())
				writeSheetDataIndex(this.targetWb.getSheetAt(i), i,
						this.dataIndex);
			else
				writeSheetDataIndex(this.targetWb.getSheetAt(i), this.dataIndex);
		}
	}

	protected void writeSheetData(HSSFSheet sheet) {
		for (int i = 0; i < this.datas.size(); i++) {
			HSSFRow row = sheet.getRow((short) this.datas.size()
					+ this.dataRowIndex);
			if (row == null) {
				row = sheet.createRow((short) this.dataRowIndex + i);
			}
			ListOrderedMap rowData = (ListOrderedMap) this.datas.get(i);

			for (int j = 0; j < rowData.size(); j++) {
				HSSFCell cell = row.getCell((short) j);
				if (cell == null)
					cell = row.createCell((short) j);
				setCellValue(cell, rowData.getValue(j));
			}
		}
	}

	protected void writeSheetDataIndex(HSSFSheet sheet, String[] dataindex) {
		for (int i = 0; i < this.datas.size(); i++) {
			HSSFRow row = sheet.getRow(this.dataRowIndex + i);
			if (row == null)
				row = sheet.createRow(this.dataRowIndex + i);
			ListOrderedMap rowData = (ListOrderedMap) this.datas.get(i);

			for (int j = 0; j < dataindex.length; j++) {
				HSSFCell cell = row.getCell((short) j);
				if (cell == null)
					cell = row.createCell((short) j);
				setCellValue(cell,
						rowData.getValue(Integer.parseInt(dataindex[j])));
			}
		}
	}

	protected void writeSheetData(HSSFSheet sheet, int sheetIndex) {
		int startRow = sheetIndex * this.pageRows;
		int rowIndex = 0;
		for (int i = startRow; (i < this.datas.size())
				&& (i < startRow + this.pageRows); i++) {
			HSSFRow row = sheet.getRow(this.dataRowIndex + rowIndex);
			if (row == null)
				row = sheet.createRow(this.dataRowIndex + rowIndex);
			ListOrderedMap rowData = (ListOrderedMap) this.datas.get(i);

			for (int j = 0; j < rowData.size(); j++) {
				HSSFCell cell = row.getCell((short) j);
				if (cell == null)
					cell = row.createCell((short) j);

				setCellValue(cell, rowData.getValue(j));
			}
			rowIndex++;
		}
	}

	protected void writeSheetDataIndex(HSSFSheet sheet, int sheetIndex,
			String[] dataindex) {
		int startRow = sheetIndex * this.pageRows;
		int rowIndex = 0;
		for (int i = startRow; (i < this.datas.size())
				&& (i < startRow + this.pageRows); i++) {
			HSSFRow row = sheet.getRow(this.dataRowIndex + rowIndex);
			if (row == null)
				row = sheet.createRow(this.dataRowIndex + rowIndex);
			ListOrderedMap rowData = (ListOrderedMap) this.datas.get(i);

			for (int j = 0; j < dataindex.length; j++) {
				HSSFCell cell = row.getCell((short) j);
				if (cell == null)
					cell = row.createCell((short) j);

				setCellValue(cell,
						rowData.getValue(Integer.parseInt(dataindex[j])));
			}
			rowIndex++;
		}
	}

	protected void setCellValue(HSSFCell cell, Object value) {
		if ((value instanceof Number)) {
			cell.setCellType(0);

			this.styleRight.setDataFormat(HSSFDataFormat
					.getBuiltinFormat("#,##0.00"));
			cell.setCellStyle(this.styleRight);
			double tempValue = NumberUtils.round(value, getScale());
			cell.setCellValue(tempValue);
		}
		if ((value instanceof String)) {
			cell.setCellType(1);
			cell.setCellValue(value.toString());
			cell.setCellStyle(this.styleLeft);
		}
	}

	public int getDataRowIndex() {
		return this.dataRowIndex;
	}

	public void setDataRowIndex(int dataRowIndex) {
		this.dataRowIndex = dataRowIndex;
	}

	public int getPageRows() {
		return this.pageRows;
	}

	public void setPageRows(int pageRows) {
		this.pageRows = pageRows;
	}

	public boolean isPaged() {
		return this.isPaged;
	}

	public void setPaged(boolean isPaged) {
		this.isPaged = isPaged;
	}

	public int getScale() {
		return this.scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public boolean isFirstDataColumnDisplayed() {
		return this.firstDataColumnDisplayed;
	}

	public void setFirstDataColumnDisplayed(boolean firstDataColumnDisplayed) {
		this.firstDataColumnDisplayed = firstDataColumnDisplayed;
	}

	public HSSFWorkbook getTargetWb() {
		return this.targetWb;
	}

	public void setTargetWb(HSSFWorkbook targetWb) {
		this.targetWb = targetWb;
	}
}