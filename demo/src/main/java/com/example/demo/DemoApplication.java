package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@SpringBootApplication
@EnableTransactionManagement //啟動事務管理,等同於xml <tx:annotation-driven />
@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class }) //取消自動配置:自动配置的功能可能不符合我们的需求，我们自定義配置於HibernateCong.java
public class DemoApplication implements TransactionManagementConfigurer{

	@Autowired
	PlatformTransactionManager platformTransactionManager;
	@Bean
	public Object testBean(PlatformTransactionManager platformTransactionManager){
		System.out.println("                PlatformTransactionManager實現類別   "+platformTransactionManager.getClass().getName());
		return new Object();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Override
	public TransactionManager annotationDrivenTransactionManager() {
		// 返回值代表當使用多個事務管理器的時候,默認使用的管理器(由interface TransactionManagementConfigurer提供,因此需要implements TransactionManagementConfigurer)
		//＠Transactional 不指定value就是預設的事務管理器
		return platformTransactionManager;
	}
}
