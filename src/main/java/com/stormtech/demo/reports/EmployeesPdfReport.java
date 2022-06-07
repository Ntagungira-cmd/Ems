package com.stormtech.demo.reports;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.management.loading.PrivateClassLoader;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.stormtech.demo.models.Employee;

public class EmployeesPdfReport {
	private List<Employee> employees;

	public EmployeesPdfReport(List<Employee> employees) {
		this.employees = employees;
	}

	private void writeTableHeaders(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.DARK_GRAY);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Employee names", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Employee email", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Employment time", font));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table) {
		for (Employee employee : employees) {
			table.addCell(String.valueOf(employee.getFirstname()) + " " + employee.getLastname());
			table.addCell(String.valueOf(employee.getEmail()));
			table.addCell(String.valueOf(employee.getEmploymentTime()));
		}
	}

	public void export(HttpServletResponse res) throws DocumentException, IOException {
		Document doc = new Document(PageSize.A3);
		PdfWriter.getInstance(doc, res.getOutputStream());

		doc.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(14);
		font.setColor(Color.BLACK);
		
		Paragraph p = new Paragraph("List of Employees", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        
        doc.add(p);
        
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {4.5f, 3.5f, 3.5f});
        table.setSpacingBefore(10);
        
        writeTableHeaders(table);
        writeTableData(table);
        
        doc.add(table);
        doc.close();
	}
}









