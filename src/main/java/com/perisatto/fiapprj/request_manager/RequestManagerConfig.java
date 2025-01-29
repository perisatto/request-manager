package com.perisatto.fiapprj.request_manager;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.perisatto.fiapprj.request_manager.domain.entities.Request;

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


	@Bean
	public Queue pendingRequests() {
		return new Queue("pending_requests");
	}
	
	@Bean
	public Jackson2JsonMessageConverter jsonMessageConverter() {
	    Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();
	    jsonConverter.setClassMapper(classMapper());
	    return jsonConverter;
	}

	@Bean
	public DefaultClassMapper classMapper() {
	    DefaultClassMapper classMapper = new DefaultClassMapper();
	    Map<String, Class<?>> idClassMapping = new HashMap<>();
	    idClassMapping.put("Request", Request.class);
	    classMapper.setIdClassMapping(idClassMapping);
	    return classMapper;
	}
}
