package com.perisatto.fiapprj.request_manager;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.perisatto.fiapprj.request_manager.application.interfaces.FileProcessingManagement;
import com.perisatto.fiapprj.request_manager.application.interfaces.FileRepositoryManagement;
import com.perisatto.fiapprj.request_manager.application.interfaces.RequestRepository;
import com.perisatto.fiapprj.request_manager.application.usecases.CreateRequestUseCase;

@Configuration
public class RequestManagerConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public DataSource dataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();

	    dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
	    dataSource.setUsername(env.getProperty("spring.datasource.username"));
	    dataSource.setPassword(env.getProperty("spring.datasource.password"));
	    dataSource.setUrl(env.getProperty("spring.datasource.url")); 
	    
	    return dataSource;
	}

}
