package com.example.jacktest.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    
    @Bean(name = "messageSource")
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource messageResource =  new ReloadableResourceBundleMessageSource();
        messageResource.setBasename("classpath:multilingual/messages");
        messageResource.setDefaultEncoding("UTF-8");

        return messageResource;
    }
}