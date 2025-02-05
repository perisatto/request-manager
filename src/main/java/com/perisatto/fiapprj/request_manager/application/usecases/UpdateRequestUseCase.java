package com.perisatto.fiapprj.request_manager.application.usecases;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.perisatto.fiapprj.request_manager.application.interfaces.FileProcessingManagement;
import com.perisatto.fiapprj.request_manager.application.interfaces.RequestRepository;
import com.perisatto.fiapprj.request_manager.domain.entities.Request;
import com.perisatto.fiapprj.request_manager.domain.entities.RequestStatus;
import com.perisatto.fiapprj.request_manager.handler.exceptions.NotFoundException;
import com.perisatto.fiapprj.request_manager.handler.exceptions.ValidationException;

public class UpdateRequestUseCase {
	
	static final Logger logger = LogManager.getLogger(UpdateRequestUseCase.class);
	
	private final RequestRepository requestRepository;	
	private final FileProcessingManagement fileProcessingManagement;
	
	public UpdateRequestUseCase(RequestRepository requestRepository,FileProcessingManagement fileProcessingManagement) {
		this.requestRepository = requestRepository;
		this.fileProcessingManagement = fileProcessingManagement;
	}
	
	public Request updateRequest(String owner, String id, String remarks, RequestStatus status) throws Exception {
		
		Optional<Request> request = requestRepository.getRequestByOwnerAndId(owner, id);
		
		if(request.isEmpty()) {
			throw new NotFoundException("rqst-1001", "Request not found");
		}
		
		Request updatedRequest = request.get();
		
		if(updatedRequest.getStatus() == status) {
			throw new ValidationException("rqst-1002", "Request already " + status.toString());
		} else {
			if (updatedRequest.getStatus().getId() > status.getId()) {
				throw new ValidationException("rqst-1003", "Request can't go back to " + status.toString() + " status");
			}
		}
		
		if(remarks != null) {
			updatedRequest.setRemarks(remarks);
		}
		
		if(status == RequestStatus.PENDING_PROCESS) {
			fileProcessingManagement.createProcessingRequest(updatedRequest);
		}
				
		updatedRequest.setStatus(status);
		
		requestRepository.updateRequest(updatedRequest);
		
		return updatedRequest;
	}
}
