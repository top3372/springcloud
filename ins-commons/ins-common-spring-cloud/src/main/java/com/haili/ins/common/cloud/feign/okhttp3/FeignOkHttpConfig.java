package com.haili.ins.common.cloud.feign.okhttp3;

import feign.Feign;
import okhttp3.ConnectionPool;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
    /**
     * okhttp拦截器 用于打日志
     */
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
                //设置连接的读取超时时间，默认10s
            .readTimeout(60, TimeUnit.SECONDS)
            //设置连接的连接超时的时间，默认10s
            .connectTimeout(60, TimeUnit.SECONDS)
                //设置写入超时时间，默认10s
            .writeTimeout(120, TimeUnit.SECONDS) 
            .connectionPool(new ConnectionPool())
                //添加拦截器
            .addInterceptor(httpLoggingInterceptor)
                //是否自动重连
//           .retryOnConnectionFailure(true)
            .build();
    }
}