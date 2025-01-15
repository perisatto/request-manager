package com.perisatto.fiapprj.request_manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.perisatto.fiapprj.request_manager.application.interfaces.FileProcessingManagement;
import com.perisatto.fiapprj.request_manager.application.interfaces.FileRepositoryManagement;
import com.perisatto.fiapprj.request_manager.application.interfaces.RequestManagement;
import com.perisatto.fiapprj.request_manager.application.usecases.CreateRequestUseCase;

@Configuration
public class RequestManagerConfig {
	
	@Bean
	CreateRequestUseCase createRequestUseCase(RequestManagement requestManagement, FileRepositoryManagement fileRepositoryManagement, FileProcessingManagement fileProcessingManagement) {
		return new CreateRequestUseCase(requestManagement, fileRepositoryManagement, fileProcessingManagement);
	}	
}
