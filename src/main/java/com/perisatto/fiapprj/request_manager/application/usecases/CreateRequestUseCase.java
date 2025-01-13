package com.perisatto.fiapprj.request_manager.application.usecases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.perisatto.fiapprj.request_manager.application.interfaces.FileProcessingManagement;
import com.perisatto.fiapprj.request_manager.application.interfaces.FileRepositoryManagement;
import com.perisatto.fiapprj.request_manager.application.interfaces.RequestManagement;
import com.perisatto.fiapprj.request_manager.domain.entities.Request;
import com.perisatto.fiapprj.request_manager.domain.entities.RequestStatus;

public class CreateRequestUseCase {

	static final Logger logger = LogManager.getLogger(CreateRequestUseCase.class);
	
	private final RequestManagement requestManagement;
	private final FileRepositoryManagement fileRepositoryManagement;
	private final FileProcessingManagement fileProcessingManagement;
	
	public CreateRequestUseCase(RequestManagement requestManagement, FileRepositoryManagement fileRepositoryManagement, FileProcessingManagement fileProcessingManagement) {
		this.requestManagement = requestManagement;
		this.fileRepositoryManagement = fileRepositoryManagement;
		this.fileProcessingManagement = fileProcessingManagement;
	}
	
	public Request createRequest(String userId, Integer interval, String videoFile) throws Exception {
		
		if("me".equals(userId)) {
			userId = getUserIdFromToken();
		}
		
		Request request = new Request(userId, interval, videoFile);
		request.setStatus(RequestStatus.PENDING);
		requestManagement.createRequest(request);
		
		try {
			fileRepositoryManagement.uploadFile(request.getId(), request.getVideoFile());
			fileProcessingManagement.createProcessingRequest(request.getId(), request.getInterval(), request.getVideoFile());
		} catch (Exception e) {
			request.setStatus(RequestStatus.ERROR);
			requestManagement.updateRequest(request);
		}
				
		return request;		
	}

	private String getUserIdFromToken() {
		// TODO create function to get userId attribute from authentication token
		return null;
	}
}
