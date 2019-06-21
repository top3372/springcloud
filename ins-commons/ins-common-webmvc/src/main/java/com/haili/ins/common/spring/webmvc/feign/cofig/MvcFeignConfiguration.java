package com.haili.ins.common.spring.webmvc.feign.cofig;

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
public class MvcFeignConfiguration {

    @Bean
    public Contract mbvFeignContract(@Qualifier("mvcConversionService") ConversionService conversionService) {
        ConverterRegistry converterRegistry = ((ConverterRegistry) conversionService);
        converterRegistry.addConverter(new EnumToStringConverter());
        converterRegistry.addConverter(new StringToEnumConverter());
        return new InsSpringMvcContract(new ArrayList<>(), conversionService);
    }
}
