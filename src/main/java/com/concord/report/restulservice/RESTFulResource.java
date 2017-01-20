package com.concord.report.restulservice;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;

import com.concord.report.model.XLSDataModel;
import com.concord.report.poi.ExcelGeneratorChild;

@Configurable
@Path("/RESTFul")
public class RESTFulResource {
	
	private static Map<String, Object> contextMap = new HashMap<String, Object>();
	
	private static final Logger logger = LogManager.getLogger(RESTFulResource.class);
	
	@GET
	@Path("/downloadxls")
	@Produces("application/vnd.ms-excel")
	public Response downloadXLS() {
		
		logger.info("downloading xls sheet...");
		
		List<XLSDataModel> dataModel = new ArrayList<XLSDataModel>();
		dataModel.add(new XLSDataModel("syamala", "Myneni", "IL"));
		dataModel.add(new XLSDataModel("Prashant", "Talele", "IL"));
		dataModel.add(new XLSDataModel("Anisha", "Talele", "IL"));
		dataModel.add(new XLSDataModel("Kush", "Talele", "IL"));
		
		File file = ((ExcelGeneratorChild) contextMap.get("excelGeneratorChild")).createXLS("Family", dataModel);
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition", "attachment; filename=Family.xlsx");
	    response.header("Content-Type","application/vnd.ms-excel");
	    return response.build();
 
	}
	

	public static Map<String, Object> getMap() {
		return contextMap;
	}

	public static void setMap(Map<String, Object> map) {
		RESTFulResource.contextMap = map;
	}
	
 
}
