package com.perisatto.fiapprj.request_manager.infra.controllers;

import java.net.URI;
import java.util.Properties;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.perisatto.fiapprj.request_manager.application.usecases.CreateRequestUseCase;
import com.perisatto.fiapprj.request_manager.domain.entities.Request;
import com.perisatto.fiapprj.request_manager.infra.controllers.dtos.CreateRequestRequestDTO;
import com.perisatto.fiapprj.request_manager.infra.controllers.dtos.CreateRequestResponseDTO;

@RestController
public class RequestManagerRestController {
	private final CreateRequestUseCase createRequestUseCase;
	private final Properties requestProperties;

	public RequestManagerRestController(CreateRequestUseCase createRequestUseCase, Properties requestProperties) {
		this.createRequestUseCase = createRequestUseCase;
		this.requestProperties = requestProperties;
	}

	@PostMapping(value = "/users/{userId}/requests", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreateRequestResponseDTO> createCustomer(@PathVariable(value = "userId") String userId, @RequestBody CreateRequestRequestDTO createRequest) throws Exception {
		requestProperties.setProperty("resourcePath", "/users/" + userId + "/resources");

		Request request = createRequestUseCase.createRequest(userId, createRequest.getInterval(), createRequest.getVideoFileName());		
		ModelMapper requestMapper = new ModelMapper();
		CreateRequestResponseDTO response = requestMapper.map(request, CreateRequestResponseDTO.class);
		URI location = new URI("/users/" + userId + "/requests/" + response.getId());
		return ResponseEntity.status(HttpStatus.CREATED).location(location).body(response);
	}
}