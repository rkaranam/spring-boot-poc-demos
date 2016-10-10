package com.springtweaks.boot.poc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class MultipleDbConfiguration {
	
	@Bean("mysqlDataSource")
	@ConfigurationProperties(value = "spring.ds_mysql")
	@Primary
	public DataSource mysqlDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean("mysqlJdbcTemplate")
	public JdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqlDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean("h2DataSource")
	@ConfigurationProperties(value = "spring.ds_h2")
	public DataSource h2DataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean("h2JdbcTemplate")
	public JdbcTemplate h2JdbcTemplate(@Qualifier("h2DataSource")DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
