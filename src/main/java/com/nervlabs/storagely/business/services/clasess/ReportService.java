package com.nervlabs.storagely.business.services.clasess;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nervlabs.storagely.domain.entites.ItemEntity;
import com.nervlabs.storagely.persistence.IItemRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {

	private final IItemRepository repository;

	public List<ItemEntity> getEmptyProductsList() {
		var products = repository.findAllOrderByQty();
		return products;
	}

	private void writeHeader(PdfPTable table) {
		PdfPCell celda = new PdfPCell();

		celda.setPhrase(new Phrase("ID"));
		table.addCell(celda);

		celda.setPhrase(new Phrase("Descripción"));
		table.addCell(celda);

		celda.setPhrase(new Phrase("Cantidad"));
		table.addCell(celda);

		celda.setPhrase(new Phrase("Precio"));
		table.addCell(celda);

	}

	private void writeData(PdfPTable table) {

		var items = getEmptyProductsList();

		for (ItemEntity item : items) {
			table.addCell(String.valueOf(item.getId()));
			table.addCell(item.getDescription());
			table.addCell("1");
			table.addCell( "$ " + item.getFinalPrice().toString());
		}
	}

	public void exportPDF(HttpServletResponse rs) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, rs.getOutputStream());

		documento.open();

		Paragraph titulo = new Paragraph("Lista de productos");
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);

		PdfPTable tabla = new PdfPTable(4);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[] { 6f, 6f, 2f, 2f });
		tabla.setWidthPercentage(110);

		writeHeader(tabla);
		writeData(tabla);

		documento.add(tabla);
		documento.close();
	}

	public void exportPDFSale(HttpServletResponse rs) throws DocumentException, IOException {

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());

		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, rs.getOutputStream());

		documento.open();

		Paragraph titulo = new Paragraph("Ventas del día");
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);

		PdfPTable tablaHeader = new PdfPTable(2);
		tablaHeader.setWidthPercentage(100);
		tablaHeader.setSpacingBefore(15);
		tablaHeader.setWidths(new float[] { 6f, 2f });
		tablaHeader.setWidthPercentage(110);

		PdfPCell celda = new PdfPCell();

		celda.setPhrase(new Phrase("Vendedor"));
		tablaHeader.addCell(celda);

		celda.setPhrase(new Phrase("Fecha"));
		tablaHeader.addCell(celda);

		tablaHeader.addCell("Juan Fernandez");
		tablaHeader.addCell(fechaActual);

		documento.add(tablaHeader);

		PdfPTable tabla = new PdfPTable(4);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[] { 6f, 6f, 2f, 2f });
		tabla.setWidthPercentage(110);

		writeHeader(tabla);
		writeData(tabla);

		documento.add(tabla);

		PdfPTable tablaFooter = new PdfPTable(2);
		tablaFooter.setWidthPercentage(100);
		tablaFooter.setSpacingBefore(15);
		tablaFooter.setWidths(new float[] { 6f, 2f });
		tablaFooter.setWidthPercentage(110);

		tablaFooter.addCell("Total");

		tablaFooter.addCell("$ " + repository.findAll().stream().map(objeto -> objeto.getFinalPrice())
				.reduce(BigDecimal.ZERO, (total, valor) -> total.add(valor)).toString());

		documento.add(tablaFooter);

		documento.close();
	}

}
