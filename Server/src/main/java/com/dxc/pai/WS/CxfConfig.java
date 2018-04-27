package com.dxc.pai.WS;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * bean define here will override the default bean.
 * like dispatcherServletWS() method here. If I change the method name to dispatcherServlet(), 
 * then because of the method name will be the bean name by default, 
 * a new bean name "dispatcherServlet" will be created, and the default dispatcherServlet generate by 
 * Spring Boot will be override, so the Controller method will be useless.
 * @author wenc
 *
 */
@Configuration
public class CxfConfig {

    @Bean
    public ServletRegistrationBean dispatcherServletWS() {
    	return new ServletRegistrationBean(new CXFServlet(),"/demo/*");
    }
    
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
    	return new SpringBus();
    }
    
    @Bean
    public DemoWebService demoService() {
        return new DemoWebServiceImpl();
    }
    
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), demoService());
        endpoint.publish("/jwspai");
        return endpoint;
    }
    
}
