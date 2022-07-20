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
		registry.addResourceHandler("/asset/**").addResourceLocations("/asset/");
		registry.addResourceHandler("/asset/user/**").addResourceLocations("/asset/user/");
		registry.addResourceHandler("/asset/user/css/**").addResourceLocations("/asset/user/css/");
		registry.addResourceHandler("/asset/user/fonts/**").addResourceLocations("/asset/user/fonts/");
		registry.addResourceHandler("/asset/user/js/**").addResourceLocations("/asset/user/js/");
		registry.addResourceHandler("/asset/user/images/**").addResourceLocations("/asset/user/images/");
		registry.addResourceHandler("/asset/admin/**").addResourceLocations("/asset/admin/");
		registry.addResourceHandler("/asset/admin/dist/**").addResourceLocations("/asset/admin/dist/");
		registry.addResourceHandler("/asset/admin/plugins/**").addResourceLocations("/asset/admin/plugins/");
		registry.addResourceHandler("/asset/admin/pages/**").addResourceLocations("/asset/admin/pages/");
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
