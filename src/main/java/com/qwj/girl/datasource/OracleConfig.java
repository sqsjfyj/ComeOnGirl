package com.qwj.girl.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryOracle", transactionManagerRef = "transactionManagerOracle", basePackages = {"com.qwj.girl.dao.oracle"})
//配置连接工厂 entityManagerFactory//配置 事物管理器  transactionManager//设置持久层所在位置
public class OracleConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("oracleDataSource")
    private DataSource oracleDataSource;

    @Value("${spring.jpa.properties.hibernate.oracle-dialect}")
    private String oracleDialect;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean("entityManagerFactoryOracle")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryOracle(EntityManagerFactoryBuilder entityManagerFactoryBuilder){
        return entityManagerFactoryBuilder.dataSource(oracleDataSource).properties(getVendorProperties(oracleDataSource)).packages("com.qwj.girl.entity.oracle").build();
    }

    private Map<String, Object> getVendorProperties(DataSource dataSource){
        Map<String, String> map = new HashMap<>();
        map.put("hibernate.dialect", oracleDialect);
        jpaProperties.setProperties(map);
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }

    @Bean("transactionManagerOracle")
    public PlatformTransactionManager transactionManagerOracle(EntityManagerFactoryBuilder entityManagerFactoryBuilder){
        return new JpaTransactionManager(entityManagerFactoryOracle(entityManagerFactoryBuilder).getObject());
    }

}
