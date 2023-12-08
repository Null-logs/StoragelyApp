package com.nervlabs.storagely.presentation.controller.privateControllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;
import com.nervlabs.storagely.business.commons.classes.DataTableResponse;
import com.nervlabs.storagely.business.commons.classes.Response;
import com.nervlabs.storagely.business.facades.IItemFacade;
import com.nervlabs.storagely.business.services.clasess.ReportService;
import com.nervlabs.storagely.domain.dtos.UnregisteredItemDto;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value = "/storer")
public class StorerController {
	
	@Autowired
	IItemFacade facade;
	
	@Autowired
	ReportService reportService;
	
	@PostMapping(value = "/register")
	Response registerItems(@RequestBody Set<UnregisteredItemDto> items) {
		return facade.register(items);
	}
	
	@GetMapping(value = "/get", produces = "application/json")
	DataTableResponse getItems(@RequestParam Map<String,String> dataTableParams) {
		return facade.get(dataTableParams);
	}
	
	@GetMapping(value = "/getReport")
	void generteReport (HttpServletResponse rs) throws DocumentException, IOException {
		rs.setContentType("application/pdf");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Productos_" + fechaActual + ".pdf";
		
		rs.setHeader(cabecera, valor);
		
		reportService.exportPDF(rs);
	}
	
	@GetMapping(value = "/getReportSale")
	void generteReportSale (HttpServletResponse rs) throws DocumentException, IOException {
		rs.setContentType("application/pdf");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename=Ventas" + fechaActual + ".pdf";
		
		rs.setHeader(cabecera, valor);
		
		reportService.exportPDFSale(rs);
	}	
}
