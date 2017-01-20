package com.concord.report.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.concord.report.poi.ExcelGeneratorChild;
import com.concord.report.restulservice.RESTFulResource;

public class PropertyLoader {
	
	@Autowired
	ExcelGeneratorChild excelGeneratorChild;
	
	public void storeAllProperties(ApplicationContext context) throws IOException{
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("excelGeneratorChild", excelGeneratorChild);
		RESTFulResource.setMap(map);		
				
	}

}
