package com.perisatto.fiapprj.request_manager.infra.gateways;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.perisatto.fiapprj.request_manager.application.interfaces.FileProcessingManagement;
import com.perisatto.fiapprj.request_manager.domain.entities.Request;

public class FileProcessorQueueManagement implements FileProcessingManagement {	
	
    @Autowired
    private RabbitTemplate template;
    
    @Autowired
    private Queue pendingRequests;
	
	@Override
	public void createProcessingRequest(Request request) {		
		ModelMapper modelMapper = new ModelMapper();
		RequestMessage requestMessage = modelMapper.map(request, RequestMessage.class);
				
		template.convertAndSend(pendingRequests.getName(), requestMessage);
	}

}
