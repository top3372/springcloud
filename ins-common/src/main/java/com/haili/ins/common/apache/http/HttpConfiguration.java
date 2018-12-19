package com.haili.ins.common.apache.http;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置 HTTPClient Bean
 */
@Slf4j
@Configuration
@ConditionalOnProperty(value = "feign.httpclient.enabled", havingValue = "true")
public class HttpConfiguration {

    @Autowired
    PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;

    @Bean
    public HttpClient httpClient(HttpClientBuilder httpClientBuilder) {
        log.info("===== Apache httpclient 初始化连接池开始===" );
        HttpClient client = httpClientBuilder.build();

        // 启动定时器，定时回收过期的连接
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                //log.info("=====closeIdleConnections===");
                poolingHttpClientConnectionManager.closeExpiredConnections();
                poolingHttpClientConnectionManager.closeIdleConnections(5, TimeUnit.SECONDS);
            }
        },10 * 1000,5 * 1000, TimeUnit.MILLISECONDS);

        log.info("===== Apache httpclient 初始化连接池完毕===");
        return client;
    }

    @Bean(destroyMethod = "close")
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        log.info("===== Apache httpclient 初始化连接池管理器===" );
        // 连接池配置
        // 长连接保持30秒
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(30, TimeUnit.MILLISECONDS);
        // 总连接数
        connectionManager.setMaxTotal(1000);
        // 同路由的并发数
        connectionManager.setDefaultMaxPerRoute(200);
        log.info("===== Apache httpclient 初始化连接池管理器完毕===" );
        return connectionManager;
    }

    @Bean
    public HttpClientBuilder httpClientBuilder(HttpClientConnectionManager connectionManager) {
        log.info("===== Apache httpclient 初始化HttpClientBuilder===" );
        // 生成请求配置  // 超时时间 // 连接时间 //请求时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5 * 1000)
                .setSocketTimeout(5 * 1000).setConnectionRequestTimeout(5 * 1000).build();
        // httpclient 配置
        HttpClientBuilder builder = HttpClientBuilder.create();
        // 保持长连接配置，需要在头添加Keep-Alive
        builder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
        //添加连接管理器 这里就是链接池管理器
        builder.setConnectionManager(connectionManager);
        //添加请求配置
        builder.setDefaultRequestConfig(requestConfig);
        builder.setRetryHandler(new DefaultHttpRequestRetryHandler(0,false));
        log.info("===== Apache httpclient 初始化HttpClientBuilder完毕===" );
        return builder;
    }

}
