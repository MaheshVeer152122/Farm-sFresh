package com.fresh.service;

import java.io.FileOutputStream; 
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.fresh.pojos.CartItem;

@Service
public class PdfExportService {
	
	public void export(List<CartItem> items) throws DocumentException, MalformedURLException, IOException, URISyntaxException {
		
		Image logo = Image.getInstance("fm.jpg");
		logo.scaleAbsolute(50, 50);
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("receipt.pdf"));
		document.open();
		document.add(logo);
		Font font = FontFactory.getFont(FontFactory.COURIER, 20, BaseColor.BLACK);
		Paragraph p = new Paragraph();
		p.setTabSettings(new TabSettings(56f));
        p.add(Chunk.TABBING);
        p.add(new Chunk("Farmers Marketplace", font));
		document.add(p);
		Chunk linebreak = new Chunk(new DottedLineSeparator());
		document.add(linebreak);

		PdfPTable table = new PdfPTable(4);
		addTableHeader(table);
		addRows(table, items);

		document.add(table);
		
		double grandtotal = 0.0;

		for (CartItem item : items) {
			grandtotal += item.getAmount();
		}
		
		for (int i = 0; i < 10; i++) {
			document.add(Chunk.NEWLINE);
		}
		
		Chunk c = new Chunk("Total Amount: "+grandtotal, font);
		document.add(c);
		
		document.close();
	}
	
	private void addTableHeader(PdfPTable table) {
		Stream.of("Product Name", "Quantity", "Price", "Amount").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private void addRows(PdfPTable table, List<CartItem> items)
			throws URISyntaxException, BadElementException, MalformedURLException, IOException {

		for (CartItem cartItem : items) {
			table.addCell(cartItem.getItem());
			table.addCell(String.valueOf(cartItem.getQty()));
			table.addCell(String.valueOf(cartItem.getPrice()));
			table.addCell(String.valueOf(cartItem.getAmount()));
		}
	}
}
