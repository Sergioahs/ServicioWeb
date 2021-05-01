package com.example.venta;


import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;

@EnableWs
@Configuration
public class VentaConfiguracion extends WsConfigurerAdapter{

    @Bean
    public XsdSchema ventaSchema(){
        return new SimpleXsdSchema(new ClassPathResource("venta.xsd"));
    }    
    @Bean
    public ServletRegistrationBean messageDispatcherServlet (ApplicationContext aplicationContext){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(aplicationContext);
        servlet.setTransformWsdlLocations(true);

        return new ServletRegistrationBean(servlet, "/ws/*");
    }    
    @Bean (name = "venta")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema ventaSchema){
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("ventaPort");
        wsdl.setLocationUri("/ws/venta");
        wsdl.setTargetNamespace("http://www.example.org/venta");
        wsdl.setSchema(ventaSchema);
        return wsdl;
    }
    @Bean
    public FilterRegistrationBean FilterRegistrationBean(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
