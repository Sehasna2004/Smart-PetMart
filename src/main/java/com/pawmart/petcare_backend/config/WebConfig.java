package com.pawmart.petcare_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // This maps the web URL "/images/**" to the folder "C:/pawmart_uploads/"
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:C:/pawmart_uploads/");
    }
}