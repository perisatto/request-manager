package com.perisatto.fiapprj.request_manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.perisatto.fiapprj.request_manager.application.interfaces.CustomerRepository;
import com.perisatto.fiapprj.request_manager.application.interfaces.UserManagement;
import com.perisatto.fiapprj.request_manager.application.usecases.CustomerUseCase;
import com.perisatto.fiapprj.request_manager.infra.gateways.CustomerRepositoyJpa;
import com.perisatto.fiapprj.request_manager.infra.gateways.mappers.CustomerMapper;
import com.perisatto.fiapprj.request_manager.infra.persistences.repositories.CustomerPersistenceRepository;

@Configuration
public class CustomerConfig {

	@Bean
	CustomerUseCase customerUseCase(CustomerRepository customerRepository, UserManagement userManagement) {
		return new CustomerUseCase(customerRepository, userManagement);
	}	
	
	@Bean
	CustomerRepositoyJpa customerJpaRepository(CustomerPersistenceRepository customerPersistenceRepository, CustomerMapper customerMapper) {
		return new CustomerRepositoyJpa(customerPersistenceRepository, customerMapper);
	}
	
	@Bean
	CustomerMapper customerMapper() {
		return new CustomerMapper();
	}
}
