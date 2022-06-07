package com.stormtech.demo.reports;

import java.awt.Color;
import java.util.List;

import javax.management.loading.PrivateClassLoader;

import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.stormtech.demo.models.Employee;

public class EmployeesPdfReport {
	private List<Employee> employees;

	public EmployeesPdfReport(List<Employee> employees) {
		this.employees = employees;
	}

	private void writeTableHeaders(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Employee names", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Employee email", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Employment Data", font));
		table.addCell(cell);
	}

	private void writeTable(PdfPTable table) {
		for(Employee employee: employees) {
			table.addCell(String.valueOf(employee.getFirstname())+" "+ employee.getLastname());
			table.addCell(String.valueOf(employee.getEmail()));
			table.addCell(String.valueOf(employee.getEmploymentTime()));
		}
	}
	
}
