package com.haili.ins.common.database.mybatis.config;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.haili.ins.dao.mapper")
@Configuration
public class MapperConfig {

}
