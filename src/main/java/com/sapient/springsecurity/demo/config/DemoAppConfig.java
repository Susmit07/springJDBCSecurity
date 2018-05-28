package com.sapient.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc // same as <mvc:annotation-driven/>
@ComponentScan(basePackages="com.sapient.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties") // Read from the properties file (files are automatically copied to class-path during maven build)
public class DemoAppConfig {

	// Set up variable to hold the database properties. We will be using spring helper class Environment.
	@Autowired
	private Environment environment; // Will hold the data read form properties file.
	private Logger logger = Logger.getLogger(getClass().getName());

	// define a bean for ViewResolver
	@Bean
	public ViewResolver viewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	// define a bean for our security data source.
	@Bean
	public DataSource securityDataSource(){
		
		// Creating connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		// Set the JDBC driver class
		try {
			securityDataSource.setDriverClass(environment.getProperty("jdbc.driver"));
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		logger.info("JDBC Url: "+environment.getProperty("jdbc.url")+" , "
				+ "JDBC User: "+environment.getProperty("jdbc.user"));
		
		// Set the connection database properties.
		securityDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
		securityDataSource.setUser(environment.getProperty("jdbc.user"));
		securityDataSource.setPassword(environment.getProperty("jdbc.password"));
		
		// Set connection pool properties.
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		
		return securityDataSource;
	}
	
	//A helper method to convert to integer after reading from environment property
	public int getIntProperty(String envPropName) {
		
		String propVal = environment.getProperty(envPropName);
		int intPropVal = Integer.parseInt(propVal);
		return intPropVal;
	}
}









