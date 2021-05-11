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
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactoryMysql", transactionManagerRef = "transactionManagerMysql", basePackages = {"com.qwj.girl.dao.mysql"})
//配置连接工厂 entityManagerFactory//配置 事物管理器  transactionManager//设置持久层所在位置
public class MysqlConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("mysqlDataSource")
    private DataSource mysqlDataSource;

    @Value("${spring.jpa.properties.hibernate.mysql-dialect}")
    private String mysqlDialect;

    @Autowired
    private HibernateProperties hibernateProperties;

    @Bean("entityManagerFactoryMysql")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryMysql(EntityManagerFactoryBuilder entityManagerFactoryBuilder){
        return entityManagerFactoryBuilder.dataSource(mysqlDataSource).properties(getVendorProperties(mysqlDataSource)).packages("com.qwj.girl.entity.mysql").build();
    }

    private Map<String, Object> getVendorProperties(DataSource dataSource){
        Map<String, String> map = new HashMap<>();
        map.put("hibernate.dialect", mysqlDialect);
        jpaProperties.setProperties(map);
        return hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings());
    }

    @Bean("transactionManagerMysql")
    @Primary
    public PlatformTransactionManager transactionManagerMysql(EntityManagerFactoryBuilder entityManagerFactoryBuilder){
        return new JpaTransactionManager(entityManagerFactoryMysql(entityManagerFactoryBuilder).getObject());
    }

}
