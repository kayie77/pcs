package com.yunforge.base.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.yunforge.common.bean.ExpExcelResult;

public class ExpExcelResultView extends AbstractExcelView
{
	public ExpExcelResultView(){}

	@Override
	protected void buildExcelDocument(Map<String, Object> datas,
			HSSFWorkbook hssfWorkBook, HttpServletRequest req, HttpServletResponse resp)
			throws Exception
	{
		ExpExcelResult expExcelResult=(ExpExcelResult) datas.get("datas");
		
		int rows=0;//行数
		int cells=0;
		HSSFCellStyle cellStyle=hssfWorkBook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		HSSFFont font=hssfWorkBook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 11);
		cellStyle.setFont(font);
		cellStyle.setWrapText(true);
		
		Date expDate=new Date();
		String tjDate=new SimpleDateFormat("yyyy.MM.dd").format(expDate);
		
		//new String(expExcelResult.getReportName().getBytes(),"iso-8859-1")
		String fileName=expExcelResult.getReportName()+"_"+expExcelResult.getReportNum()+".xls";//如：农业生产进度统计表(春种)_20150330.xls
		
		String tableHeads=expExcelResult.getReportTableHeads();
		String reportDatas=expExcelResult.getDatas();
		
		HSSFSheet hssfSheet0=hssfWorkBook.createSheet(expExcelResult.getReportName());
		
		HSSFRow hssfRow0=hssfSheet0.createRow(rows++);//表名
		HSSFCell hssfCellReportName=hssfRow0.createCell(0);
		hssfCellReportName.setCellValue(expExcelResult.getReportName());
		HSSFCellStyle reportNameCellStyle=hssfWorkBook.createCellStyle();
		HSSFFont reportNameFont=hssfWorkBook.createFont();
		reportNameFont.setFontName("宋体");
		reportNameFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		reportNameFont.setFontHeightInPoints((short)18);
		reportNameCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		reportNameCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		reportNameCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		reportNameCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		reportNameCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		reportNameCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		reportNameCellStyle.setFont(reportNameFont);
		hssfCellReportName.setCellStyle(reportNameCellStyle);//
		
		HSSFRow hssfRow1=hssfSheet0.createRow(rows++);//期数
		HSSFCell hssfCellReportNum=hssfRow1.createCell(0);
		hssfCellReportNum.setCellValue(expExcelResult.getReportNum());
		HSSFCellStyle hssfCellStyleReportNum=hssfWorkBook.createCellStyle();
		hssfCellStyleReportNum.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		hssfCellStyleReportNum.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		hssfCellStyleReportNum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		hssfCellStyleReportNum.setBorderTop(HSSFCellStyle.BORDER_THIN);
		hssfCellStyleReportNum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		hssfCellStyleReportNum.setBorderRight(HSSFCellStyle.BORDER_THIN);
		HSSFFont reportNumFont=hssfWorkBook.createFont();
		reportNumFont.setFontName("Times New Roman");
		reportNumFont.setFontHeightInPoints((short)11);
		hssfCellStyleReportNum.setFont(reportNumFont);
		hssfCellReportNum.setCellStyle(hssfCellStyleReportNum);
		
		HSSFRow hssfRow2=hssfSheet0.createRow(rows++);//统计时间
		HSSFCell hssfCellReportTJDate=hssfRow2.createCell(0);
		hssfCellReportTJDate.setCellValue("统计时间:"+tjDate);
		HSSFCellStyle hssfCellStyle=hssfWorkBook.createCellStyle();
		HSSFFont reportTJDateFont=hssfWorkBook.createFont();
		reportTJDateFont.setFontName("Times New Roman");
		reportTJDateFont.setFontHeightInPoints((short)11);
		hssfCellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		hssfCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		hssfCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		hssfCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		hssfCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		hssfCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		hssfCellStyle.setFont(reportTJDateFont);
		hssfCellReportTJDate.setCellStyle(hssfCellStyle);
		//
		
		//表头 tableHeads
		Map<String,Object> modelTableHead=ExpExcelResultView.StringHtmlToExcel(hssfSheet0,cellStyle, tableHeads, rows ,cells,"tableHeads");
		hssfSheet0=(HSSFSheet)modelTableHead.get("hssfSheet");
		rows=(int)modelTableHead.get("rows");
		cells=(int)modelTableHead.get("cells");
		
		for(int i=1;i<=cells-1;i++)//合并单元格的边框
		{
			HSSFCell hssfCellRow0=hssfRow0.createCell(i);
			hssfCellRow0.setCellStyle(reportNameCellStyle);
			
			HSSFCell hssfCellRow1=hssfRow1.createCell(i);
			hssfCellRow1.setCellStyle(reportNameCellStyle);
			
			HSSFCell hssfCellRow2=hssfRow2.createCell(i);
			hssfCellRow2.setCellStyle(reportNameCellStyle);
		}
		
		//数据 reportDatas
		Map<String,Object> modelReportDatas=ExpExcelResultView.StringHtmlToExcel(hssfSheet0,cellStyle, reportDatas, rows ,cells,"reportDatas");
		hssfSheet0=(HSSFSheet)modelReportDatas.get("hssfSheet");
		rows=(int)modelReportDatas.get("rows");
		cells=(int)modelReportDatas.get("cells");
		
		CellRangeAddress cellRangeAddressReportName=new CellRangeAddress(0,0,0,cells-1);//起始行号，终止行号， 起始列号，终止列号cells
		hssfSheet0.addMergedRegion(cellRangeAddressReportName);
		
		CellRangeAddress cellRangeAddressReportNum=new CellRangeAddress(1,1,0,cells-1);//起始行号，终止行号， 起始列号，终止列号
		hssfSheet0.addMergedRegion(cellRangeAddressReportNum);
		
		CellRangeAddress cellRangeAddressTJDate=new CellRangeAddress(2,2,0,cells-1);//起始行号，终止行号， 起始列号，终止列号
		hssfSheet0.addMergedRegion(cellRangeAddressTJDate);
		
		resp.setContentType("application/vnd.ms-excel;charset=utf-8");
		resp.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes(),"iso-8859-1"));
		OutputStream outputStream=resp.getOutputStream();
		hssfWorkBook.write(outputStream);
		
		outputStream.flush();
		outputStream.close();
	}

	@SuppressWarnings("rawtypes")
	public static Map<String,Object> StringHtmlToExcel(HSSFSheet hssfSheet,HSSFCellStyle cellStyle,String html,int rows,int cells,String type) throws IOException
	{
		HSSFFont f=hssfSheet.getWorkbook().createFont();
		HSSFCellStyle cellStyle_Roman=hssfSheet.getWorkbook().createCellStyle();
		f.setFontName("Times New Roman");
		f.setFontHeightInPoints((short) 11);
		cellStyle_Roman.setFont(f);
		cellStyle_Roman.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle_Roman.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle_Roman.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle_Roman.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle_Roman.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle_Roman.setBorderRight(HSSFCellStyle.BORDER_THIN);
		
		Map<String,Object> model=new HashMap<String,Object>();
		List<CellRangeAddress> cellRangeAddresss=new ArrayList<CellRangeAddress>();
		html="<html><head></head><body><table>"+html+"</table></body></html>";
		Document doc=Jsoup.parse(html);
		
		Element tr;
		HSSFRow hssfRow;
		for(Iterator iteratorTr=doc.select("tr").iterator();iteratorTr.hasNext();)
		{
			hssfRow=hssfSheet.createRow(rows);
			tr=(Element) iteratorTr.next();
			Element td;
			int rowCells=0;
			for(Iterator iteratorTd=tr.getElementsByTag("td").iterator();iteratorTd.hasNext();)
			{
				td=(Element)iteratorTd.next();
				
				//写入tr中的td时,考虑已经写入的合并单元格。如果已写入的单元格在该行内，则该行在创建HSSFCell时，考虑rowCells
				for(CellRangeAddress c:cellRangeAddresss)
				{
					if(c.getFirstRow()<=rows && rows<=c.getLastRow())
					{
						if(c.getFirstColumn()<=rowCells && rowCells <=c.getLastColumn())
						{
							rowCells=c.getLastColumn()+1;
						}
					}
				}
				HSSFCell cell=hssfRow.createCell(rowCells);
				cell.setCellValue(td.text());
				
				//如果有数字,则Times New Roman
				if(ExpExcelResultView.hasDigit(td.text()))
				{
					cell.setCellStyle(cellStyle_Roman);
				}else{
					cell.setCellStyle(cellStyle);
				}
				
				if(type=="reportDatas")//根据数据的长度来设置列的宽度
				{
					if(td.text().getBytes().length>(int)10-2)// -2是指Excel中列宽为10的单元格,只能最多显示9个字符.这里-2是为了更美观些
					{
						hssfSheet.setColumnWidth(rowCells, (int)(td.text().getBytes().length+2)*269);
					}else
					{
						if(hssfSheet.getColumnWidth(rowCells)<=(10)*269)//最小的列宽度,在Excel中10的列宽
						{
							hssfSheet.setColumnWidth(rowCells,(10)*269);
						}
					}
				}
				
				int rowspan=Integer.parseInt((td.attr("rowspan")=="" ||td.attr("rowspan")==null)?"1":td.attr("rowspan"));
				int colspan=Integer.parseInt((td.attr("colspan")=="" ||td.attr("colspan")==null)?"1":td.attr("colspan"));
				
				if(!(rowspan==1 && colspan==1))
				{
					CellRangeAddress cellRangeAddress=new CellRangeAddress(rows,rows+rowspan-1,rowCells,rowCells+colspan-1);//起始行号，终止行号， 起始列号，终止列号
					cellRangeAddresss.add(cellRangeAddress);
					Collections.sort(cellRangeAddresss,new Comparator<CellRangeAddress>(){
						@Override
						public int compare(CellRangeAddress c1,CellRangeAddress c2)
						{
							if(c1.getFirstColumn() > c2.getFirstColumn())
							{
								return 1;
							}else if(c1.getFirstColumn()==c2.getFirstColumn())
							{
								return c1.getFirstRow()==c2.getFirstRow()?0:(c1.getFirstRow() > c2.getFirstRow()?1:-1);
							}else 
							{
								return -1;
							}
						}
					});
					hssfSheet.addMergedRegion(cellRangeAddress);
				}
				rowCells=rowCells+colspan;
			}
			if(cells<=rowCells)
			{
				cells=rowCells;
			}
			rows++;
		}
		
		for(CellRangeAddress cellRangeAddress1: cellRangeAddresss)
		{
			for(int row_Range=cellRangeAddress1.getFirstRow();row_Range<=cellRangeAddress1.getLastRow();row_Range++)
			{
				HSSFRow hssfRow1=hssfSheet.getRow(row_Range);
				for(int col_Range=cellRangeAddress1.getFirstColumn();col_Range<=cellRangeAddress1.getLastColumn();col_Range++)
				{
					//创建合并单元格包含的其他单元格(左上角第一格不创建)
					if(!(row_Range==cellRangeAddress1.getFirstRow() && col_Range==cellRangeAddress1.getFirstColumn()))
					{
						HSSFCell hssfCell=hssfRow1.createCell(col_Range);
						hssfCell.setCellStyle(cellStyle_Roman);
					}
				}
			}
		}
		
		
		model.put("hssfSheet", hssfSheet);
		model.put("rows", rows);
		model.put("cells", cells);
		
		return model;
	}
	
	/*
	 * 判断一个字符串中是否含数字
	 */
	public static boolean hasDigit(String s)
	{
		if(s == null) {
			return false;
		}
		Pattern p=Pattern.compile(".*\\d+.*");
		Matcher m=p.matcher(s);
		if(m.matches())
		{
			return true;
		}
		return false;
	}
}
