package ru.innopolis.stc21.med.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String upPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/photo/**").
                addResourceLocations("file://" + upPath + "/");
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("history");
        registry.addViewController("/history").setViewName("history");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/addRequest").setViewName("addRequest");
        registry.addViewController("/registration").setViewName("registration");
        registry.addViewController("/geoip").setViewName("geoip");
        registry.addViewController("/hi").setViewName("hi");
    }

  /*  @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {


            registry.addResourceHandler("/**").addResourceLocations(
                    CLASSPATH_RESOURCE_LOCATIONS);

        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/img/**").addResourceLocations("file:///tmp/");

*//*
        registry.addResourceHandler("/resources/**").
                addResourceLocations("/resources/");


        registry.addResourceHandler("/img/**").
                addResourceLocations("file:///tmp/");*//*

        //super.addResourceHandlers(registry);
    }*/
}