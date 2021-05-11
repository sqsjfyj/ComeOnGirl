package com.qwj.girl.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class MyDataSource {

    @Bean("mysqlDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.mysql.datasource.druid")
    public DataSource getMysqlDataSource() {
        return new DruidDataSource();
    }

    @Bean("oracleDataSource")
    @ConfigurationProperties(prefix = "spring.oracle.datasource.druid")
    public DataSource getOracleDataSource() {
        return new DruidDataSource();
    }

}
