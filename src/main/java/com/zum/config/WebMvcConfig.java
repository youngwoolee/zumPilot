package com.zum.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by joeylee on 2017-03-16.
 */

@Configuration
@EnableWebMvc
@EnableJpaRepositories(basePackages = "com.zum.repository")
public class WebMvcConfig extends WebMvcConfigurerAdapter {



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.setOrder(1);
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/static/").setCachePeriod(86400);
        registry.addResourceHandler("/upload/**").addResourceLocations("/upload/");
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
    }



    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

//    @Bean
//    public SimpleMappingExceptionResolver exceptionResolver() {
//        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
//        exceptionResolver.setDefaultErrorView("exceptionHandler");
//        Properties exceptionMappings = new Properties();
////        exceptionMappings.setProperty(".DataAccessException", "dataAccessFailure");
////        exceptionMappings.setProperty(".NoSuchRequestHandlingMethodException", "resourceNotFound");
////        exceptionMappings.setProperty(".TypeMismatchException", "resourceNotFound");
////        exceptionMappings.setProperty(".MissingServletRequestParameterException", "resourceNotFound");
//        exceptionMappings.setProperty(".NotFoundExceptionRest", "exceptionHandler");
//        exceptionMappings.setProperty(".UserDuplicationException", "exceptionHandler");
//        exceptionResolver.setExceptionMappings(exceptionMappings);
//        return exceptionResolver;
//    }

    @Bean
    public MessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/message");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(5);
        return messageSource;
    }

}
