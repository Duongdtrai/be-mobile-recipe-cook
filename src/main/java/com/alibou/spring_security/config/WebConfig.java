package com.alibou.spring_security.config;

import com.alibou.spring_security.filter.PrimaryHealthIndicator;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }

    @Bean(name = "localeResolver")
    public LocaleResolver getLocalResolver() {
        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setCookieDomain("myAppLocaleCookie");
        resolver.setDefaultLocale(Locale.ENGLISH);
        // 60 minutes
        resolver.setCookieMaxAge(60 * 60);
        return resolver;
    }

    @Bean(name = "messageSource")
    public MessageSource getMessageResource() {
        System.out.println("Get Message Resource");
        ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();

        // Đọc vào file i18n/messages_xxx.properties
        // Ví dụ: i18n/messages_en.properties
        messageResource.setBasename("classpath:lang/messages");
        messageResource.setDefaultEncoding("UTF-8");
        return messageResource;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        localeInterceptor.setParamName("language");
        registry.addInterceptor(localeInterceptor).addPathPatterns("/*");
    }

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter
                = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("REQUEST DATA : ");
        return filter;
    }

    @Bean("customPrimaryHealthIndicator")
    @Primary
    public HealthIndicator primaryHealthIndicator() {
        return new PrimaryHealthIndicator();
    }
}
