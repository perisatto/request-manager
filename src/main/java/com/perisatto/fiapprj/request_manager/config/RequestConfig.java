package com.perisatto.fiapprj.request_manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.perisatto.fiapprj.request_manager.application.interfaces.FileRepositoryManagement;
import com.perisatto.fiapprj.request_manager.application.interfaces.RequestRepository;
import com.perisatto.fiapprj.request_manager.application.usecases.CreateRequestUseCase;
import com.perisatto.fiapprj.request_manager.infra.gateways.FileProcessorQueueManagement;
import com.perisatto.fiapprj.request_manager.infra.gateways.RequestRepositoryJpa;
import com.perisatto.fiapprj.request_manager.infra.gateways.S3RepositoryManagement;

@Configuration
public class RequestConfig {
	
	@Bean
	CreateRequestUseCase createRequestUseCase(RequestRepository requestManagement, FileRepositoryManagement fileRepositoryManagement) {
		return new CreateRequestUseCase(requestManagement, fileRepositoryManagement);
	}
		
	@Bean
	RequestRepositoryJpa requestRepositoryJpa() {
		return new RequestRepositoryJpa();
	}
	
	@Bean
	S3RepositoryManagement s3RepositoryManagement(){
		return new S3RepositoryManagement();
	}
	
	@Bean
	FileProcessorQueueManagement fileProcessorQueueManagement() {
		return new FileProcessorQueueManagement();
	}
}
