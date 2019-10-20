package com.lms.LMSLibrarian;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableAutoConfiguration
public class LmsLibrarianApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsLibrarianApplication.class, args);
	}
	
//	@Bean
//	public ServletWebServerFactory servletContainer() {
//	    //Enabling SSL Traffic on tomcat
//	    TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory() {
//	        @Override
//	        protected void postProcessContext(Context context) {
//	            SecurityConstraint securityConstraint = new SecurityConstraint();
//	            securityConstraint.setUserConstraint("CONFIDENTIAL");
//	            SecurityCollection securityCollection = new SecurityCollection();
//	            securityCollection.addPattern("/*");
//	            securityConstraint.addCollection(securityCollection);
//	            context.addConstraint(securityConstraint);
//	        }
//	    };
//	    
//	    //this line will add http to https automatic redirect
//	    tomcatServletWebServerFactory.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());
//	    
//	    return tomcatServletWebServerFactory;
//	}
//	/*
//	* following method is in charge of creating a Connector which 
//	* automates the redirection from http to https
//	* any request to 8080 will get redirected to 443
//	* */
//	private Connector httpToHttpsRedirectConnector(){
//	    Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//	    connector.setScheme("http");
//	    connector.setPort(8080);
//	    connector.setSecure(false);
//	    connector.setRedirectPort(8443);
//	    return connector;
//	}
	
//    @Bean
//    public ServletWebServerFactory servletContainer() {
//    	TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//        @Override
//        protected void postProcessContext(Context context) {
//          SecurityConstraint securityConstraint = new SecurityConstraint();
//          securityConstraint.setUserConstraint("CONFIDENTIAL");
//          SecurityCollection collection = new SecurityCollection();
//          collection.addPattern("/*");
//          securityConstraint.addCollection(collection);
//          context.addConstraint(securityConstraint);
//        }
//      };
//      tomcat.addAdditionalTomcatConnectors(getHttpConnector());
//      return tomcat;
//    }
//    
//    private Connector getHttpConnector() {
//      Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//      connector.setScheme("http");
//      connector.setPort(8080);
//      connector.setSecure(false);
//      connector.setRedirectPort(8443);
//      return connector;
//    }

}
