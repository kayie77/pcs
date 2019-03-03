package com.yunforge.common.excel;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.springframework.util.StringUtils;

public class ExcelNoTemplate extends SimpleExcel {
	protected String title;
	protected String[][] colNames;
	protected int[] colWidths;
	protected HSSFSheet sheet;
	protected String units;

	public ExcelNoTemplate(String title, String[][] colNames, int[] colWidths,
			List datas, String[] dataIndex, HSSFWorkbook workbook) {
		this.title = title;
		this.colNames = colNames;
		this.colWidths = colWidths;
		this.datas = datas;
		this.dataIndex = dataIndex;
		this.targetWb = (workbook != null ? workbook : new HSSFWorkbook());
		this.sheet = this.targetWb.createSheet();

		this.styleCenter = this.targetWb.createCellStyle();
		this.styleRight = this.targetWb.createCellStyle();
		this.styleLeft = this.targetWb.createCellStyle();

		this.styleCenter.setAlignment(new Short("2"));
		this.styleCenter.setBorderBottom(new Short("1"));
		this.styleCenter.setBorderLeft(new Short("1"));
		this.styleCenter.setBorderRight(new Short("1"));
		this.styleCenter.setBorderTop(new Short("1"));

		this.styleRight.setAlignment(new Short("3"));
		this.styleRight.setBorderBottom(new Short("1"));
		this.styleRight.setBorderLeft(new Short("1"));
		this.styleRight.setBorderRight(new Short("1"));
		this.styleRight.setBorderTop(new Short("1"));

		this.styleLeft.setAlignment(new Short("1"));
		this.styleLeft.setBorderBottom(new Short("1"));
		this.styleLeft.setBorderLeft(new Short("1"));
		this.styleLeft.setBorderRight(new Short("1"));
		this.styleLeft.setBorderTop(new Short("1"));
	}

	@Override
	public void prepare() {
		initSheet();
		createTitle();
		createHeader();
		configSheet();
		configHeader();
		super.prepare();
	}

	protected void createTitle() {
		HSSFRow row = this.sheet.createRow(this.sheet.getLastRowNum());
		HSSFCell cell = row.createCell(new Short("0"));
		cell.setCellValue(new HSSFRichTextString(getTitle()));
		cell.setCellStyle(this.styleCenter);
		createCell(row);

		row = this.sheet.createRow(this.sheet.getLastRowNum() + 1);
		cell = row.createCell(new Short("0"));
		if ((this.units != null) && (!this.units.equals("")))
			cell.setCellValue(new HSSFRichTextString("单位：" + this.units));
		cell.setCellStyle(this.styleRight);
		createCell(row);
	}

	protected void createHeader() {
		for (int i = 0; i < this.colNames.length; i++) {
			HSSFRow row = this.sheet.createRow(this.sheet.getLastRowNum() + 1);
			for (int j = 0; j < this.colNames[i].length; j++) {
				HSSFCell tempCell = row.createCell((short) j);
				if (!"#".equals(this.colNames[i][j])) {
					tempCell.setCellValue(new HSSFRichTextString(
							this.colNames[i][j]));
				}
				tempCell.setCellStyle(this.styleCenter);
			}
		}
		this.dataRowIndex = (this.sheet.getLastRowNum() + 1);
	}

	protected void initSheet() {
		for (int i = 0; i < this.colWidths.length; i++)
			this.sheet.setColumnWidth((short) i,
					(short) (this.colWidths[i] * 35));
		this.sheet.setDefaultRowHeight(new Short("300"));
	}

	protected void configSheet() {
		this.sheet.addMergedRegion(new Region(new Short("0"), new Short(" 0"),
				new Short("0"), (short) (this.colNames[0].length - 1)));
		this.sheet.addMergedRegion(new Region(new Short("1"), new Short("0"),
				new Short("1"), (short) (this.colNames[0].length - 1)));
	}

	protected void configHeader() {
		HSSFRow row = this.sheet.getRow(2);
		for (int i = 1; i <= row.getLastCellNum(); i++) {
			HSSFCell cell = row.getCell((short) i);
			String tempStr = cell.getRichStringCellValue() == null ? "" : cell
					.getRichStringCellValue().getString();

			if (StringUtils.hasText(tempStr))
				continue;
			this.sheet.addMergedRegion(new Region(2, (short) (i - 1), 2,
					(short) i));
		}
	}

	protected void createCell(HSSFRow row) {
		for (int i = 1; i < this.colNames[0].length; i++) {
			HSSFCell cell = row.createCell((short) i);
			cell.setCellStyle(this.styleCenter);
		}
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[][] getColNames() {
		return this.colNames;
	}

	public void setColNames(String[][] colNames) {
		this.colNames = colNames;
	}

	public int[] getColWidths() {
		return this.colWidths;
	}

	public void setColWidths(int[] colWidths) {
		this.colWidths = colWidths;
	}

	public String getUnits() {
		return this.units;
	}

	public void setUnits(String units) {
		this.units = units;
	}
}