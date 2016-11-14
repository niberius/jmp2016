package org.zoltor.config;

import org.h2.Driver;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.H2Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.zoltor.db.h2.DbServer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Pavel Ordenko on 14/11/2016, 23:24.
 */
@Configuration
@ComponentScan({"org.zoltor.db.entities"})
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
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
    }

    /*@Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setUrl(DbServer.DB_URL);
        dataSource.setUsername(DbServer.);
        return dataSource;
    }
*/


}
