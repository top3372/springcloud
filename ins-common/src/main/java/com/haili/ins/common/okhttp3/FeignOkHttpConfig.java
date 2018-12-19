package com.haili.ins.common.okhttp3;

import feign.Feign;
import okhttp3.ConnectionPool;
import okhttp3.logging.HttpLoggingInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
@ConditionalOnProperty(value = "feign.okhttp.enabled", havingValue ="true")
public class FeignOkHttpConfig {

    @Value("${feign.okhttp.log.level:NONE}")
    private String level ;

    @Bean
    public HttpLoggingInterceptor httpLoggingInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.valueOf(level));
        return httpLoggingInterceptor;
    }

    @Autowired
    HttpLoggingInterceptor httpLoggingInterceptor;

    @Bean
    public okhttp3.OkHttpClient okHttpClient(){

        return new okhttp3.OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS) 
            .writeTimeout(120, TimeUnit.SECONDS) 
            .connectionPool(new ConnectionPool())
            .addInterceptor(httpLoggingInterceptor)
            .build();
    }
}