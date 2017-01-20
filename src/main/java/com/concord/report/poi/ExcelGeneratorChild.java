package com.concord.report.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.concord.report.model.XLSDataModel;

public class ExcelGeneratorChild extends ExcelGenerator<XLSDataModel>{
	
	private static final Logger logger = LogManager.getLogger(ExcelGeneratorChild.class.getName());

	public File createXLS(
			String reportName,
			List<XLSDataModel> modelData){
		
		File file = null;
		
		try {
			
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet xSSFSheet = workbook.createSheet("Sheet 1");
			XSSFSheet xSSFSheet2 = workbook.createSheet("Sheet 2");
						
			String[]  reportFields = getReportFields();

			setUpWorkSheets(workbook,xSSFSheet, reportFields);
			setUpWorkSheets(workbook,xSSFSheet2, reportFields);
			
			List<XLSDataModel> sheetData = new ArrayList<XLSDataModel>();
			for(XLSDataModel expiryEvent : modelData){
				sheetData.add(expiryEvent);				
			}
			
			processWorkSheet(xSSFSheet, sheetData);	
			processWorkSheet(xSSFSheet2, sheetData);	
			
			FileOutputStream out = new FileOutputStream(reportName+".xlsx");
			workbook.write(out);
			out.close();
			
			file = new  File(reportName+".xlsx");
			
		} 
		catch (Exception e) {
			
			logger.error("Error generating excel report:\n", e);
		}
	
		return file;
	}
	
	@Override
	protected List<String> getColumnElements(XLSDataModel expiryEvent) {
		List<String> elements = new ArrayList<String>();
		
		elements.add(expiryEvent.getFirstName());
		elements.add(expiryEvent.getLastName());
		elements.add(expiryEvent.getLocation());
		
		return elements;
	}
	
	public static String[] getReportFields(){
		
		String[] fields = new String[3];
		fields[0] = "First Name";
		fields[1] = "Last Name";
		fields[2] = "Location";		
		
		return fields;
	}
}
