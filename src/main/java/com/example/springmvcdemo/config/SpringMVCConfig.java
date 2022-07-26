package com.example.springmvcdemo.config;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.servlet.DispatcherType;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.example.springmvcdemo.dev.controller"})
public class SpringMVCConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
		registry.addResourceHandler("/file-upload/**").addResourceLocations("/file-upload/");

	}


	@Bean
	public FilterRegistrationBean siteMeshFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new SiteMeshFilter());
		registration.setEnabled(true);
		registration.addUrlPatterns("/*");
		registration.setOrder(1);
		registration.setDispatcherTypes(DispatcherType.ERROR, DispatcherType.FORWARD, DispatcherType.REQUEST);
		return registration;
	}

	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}




}
