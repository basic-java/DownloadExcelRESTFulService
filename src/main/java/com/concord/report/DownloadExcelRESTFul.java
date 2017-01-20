package com.concord.report;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.concord.report.util.PropertyLoader;
import com.sun.jersey.spi.container.servlet.ServletContainer;

public class DownloadExcelRESTFul {
	
	private static final Logger logger = LogManager.getLogger(DownloadExcelRESTFul.class);

	public static void main(String[] args) throws Exception {
		
		logger.info("Application is starting");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
		
		context.getBean(PropertyLoader.class).storeAllProperties(context);
						
		startJettyServer();
	    
	    ((AbstractApplicationContext) context).close();

	}

	private static void startJettyServer() throws Exception {
		
		ServletHolder sh = new ServletHolder(ServletContainer.class);    
	    sh.setInitParameter("com.sun.jersey.config.property.resourceConfigClass", "com.sun.jersey.api.core.PackagesResourceConfig");
		sh.setInitParameter("com.sun.jersey.config.property.packages", "com.concord.report.restulservice");
	    sh.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");
	    
	    Server server = new Server(10002);
	    ServletContextHandler servletContextHandler = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
	    servletContextHandler.addServlet(sh, "/*");
	    server.start();
	}	
	
}
