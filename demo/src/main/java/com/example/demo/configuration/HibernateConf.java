package com.example.demo.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;
@EnableTransactionManagement //add at 2024/01/29
@Configuration
public class HibernateConf {

	//手動配置entityManagerFactory 如果方法名稱不是entityManagerFactory 要用此Bean name去修改,因為spring會自動去抓entityManagerFactory
	 @Bean(name="entityManagerFactory") 
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.example.demo"});
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
    	HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setJdbcUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");

        return dataSource;
    }

//    @Bean
    @Bean(name="transactionManager")
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
          = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
          "hibernate.hbm2ddl.auto", "create-drop");
        hibernateProperties.setProperty(
          "hibernate.dialect", "org.hibernate.dialect.H2Dialect");

        return hibernateProperties;
    }
	
}
