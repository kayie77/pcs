package com.yunforge.core.web.view;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class PdfView extends AbstractPdfView {
	private String encoding = "UTF-8";

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String title = request.getParameter("filename");
		String content = request.getParameter("wordContent");
		Assert.isTrue((title != null) && (!title.equals("")), "pdf文件名不能为空!");
		Assert.isTrue((content != null) && (!content.equals("")),
				"pdf文件名内容不能为空!");

		response.setCharacterEncoding(getEncoding());
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(title.getBytes(getEncoding()), "ISO8859-1")
				+ ".pdf");

		Paragraph header = new Paragraph();
		BaseFont headerChinese = BaseFont.createFont("STSongStd-Light",
				"UniGB-UCS2-H", false);
		Font fontHeader = new Font(headerChinese, 24.0F, 0, Color.BLACK);

		header.add(new Paragraph(title, fontHeader));
		document.add(header);

		Paragraph pf = new Paragraph();
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light",
				"UniGB-UCS2-H", false);
		Font fontContent = new Font(bfChinese, 12.0F, 0, Color.BLACK);
		pf.add(new Paragraph(content, fontContent));
		document.add(pf);
	}

	public String getEncoding() {
		return this.encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
}