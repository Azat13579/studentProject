package ru.innopolis.stc21.med.configs;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {
    private static final String[] RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/" };

    private static final String UPLOAD_DIR = "/uploads";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        if (!registry.hasMappingForPattern("/**"))
        {
            registry.addResourceHandler("/**").addResourceLocations(RESOURCE_LOCATIONS);
        }
        registry.addResourceHandler("/uploads/**").addResourceLocations(UPLOAD_DIR);
    }


       // registry.addResourceHandler("/photo/**").
           //     addResourceLocations("file:///tmp/");


}
