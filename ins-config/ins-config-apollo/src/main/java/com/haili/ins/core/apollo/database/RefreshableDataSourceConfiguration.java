package com.haili.ins.core.apollo.database;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty(value = "spring.haili.apollo.database.enabled", havingValue ="true")
public class RefreshableDataSourceConfiguration {

  @Bean
  public DynamicDataSource dataSource(DataSourceManager dataSourceManager) {
    DataSource actualDataSource = dataSourceManager.createDataSource();
    return new DynamicDataSource(actualDataSource);
  }
}