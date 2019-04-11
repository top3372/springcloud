package com.haili.ins.common.cloud.feign.config;

import com.haili.ins.common.cloud.feign.interceptor.FeignInterceptor;
import com.haili.ins.common.cloud.feign.interceptor.SecurityInterceptor;
import com.haili.ins.common.cloud.feign.version.InsSpringMvcContract;
import com.haili.ins.common.convert.EnumToStringConverter;
import com.haili.ins.common.convert.StringToEnumConverter;
import feign.Contract;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;

import java.util.ArrayList;

@Configuration
public class FeignConfiguration {

    @Bean
    public FeignInterceptor feignInterceptor() {
        return new FeignInterceptor();
    }

    @Bean
    public SecurityInterceptor securityInterceptor() {
        return new SecurityInterceptor();
    }

    @Bean
    public Contract feignContract(ConversionService conversionService) {
        ConverterRegistry converterRegistry =  ((ConverterRegistry) conversionService);
        converterRegistry.addConverter(new EnumToStringConverter());
        converterRegistry.addConverter(new StringToEnumConverter());
        return new InsSpringMvcContract(new ArrayList<>(), conversionService);
    }
}
