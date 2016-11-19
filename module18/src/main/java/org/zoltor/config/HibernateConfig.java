package org.zoltor.config;

import org.h2.Driver;
import org.hibernate.dialect.H2Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.zoltor.db.h2.DbServer;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Pavel Ordenko on 14/11/2016, 23:24.
 */
@Configuration
@ComponentScan({"org.zoltor.db.entities"})
@EnableTransactionManagement
public class HibernateConfig {

/*    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", H2Dialect.class.getName());
        hibernateProperties.setProperty("hibernate.show_sql", Boolean.TRUE.toString());
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.generate_statistics", Boolean.TRUE.toString());
        sessionFactory.setHibernateProperties(hibernateProperties);
        //sessionFactory.setDataSource(getDataSource());
        return sessionFactory;
    }*/

  /*  @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getLocalContainerEntityManagerFactoryBean() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("employee-unit");
        return factoryBean;
    }*/

/*
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setUrl(DbServer.DB_URL);
        dataSource.setUsername(DbServer.DB_USERNAME);
        dataSource.setPassword(DbServer.DB_PASSWORD);
        return dataSource;
    }

    @Bean(name = "transactionManager")
    public JtaTransactionManager getJtaTransactionManager() {
        JtaTransactionManager transactionManager = new JtaTransactionManager();
        transactionManager.set
        return transactionManager;
    }
*/
}
