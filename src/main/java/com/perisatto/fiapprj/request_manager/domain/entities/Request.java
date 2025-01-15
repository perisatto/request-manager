package com.perisatto.fiapprj.request_manager.domain.entities;

import java.io.ByteArrayInputStream;
import java.net.URLConnection;
import java.util.Base64;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.perisatto.fiapprj.request_manager.handler.exceptions.ValidationException;

public class Request {

	private String id;
	private String owner;
	private Integer interval;
	private RequestStatus status;
	private String videoFile;
	private String remarks;

	static final Logger logger = LogManager.getLogger(Request.class);

	public Request(String owner, Integer interval, String videoFile) throws ValidationException {

		if((owner == null) || (owner.isEmpty()) || (owner.isBlank())) {
			logger.debug("Error validating request data: null or empty owner");
			throw new ValidationException("rqst-1001", "Error validating request data: null or empty owner");
		}

		if((interval == null) || (interval < 10)) {
			logger.debug("Error validating request data: invalid interval value");
			throw new ValidationException("rqst-1001", "Error validating request data: invalid interval value");
		}

		if((videoFile == null) || (videoFile.isEmpty()) || (videoFile.isBlank())){
			logger.debug("Error validating product data: empty, null or blank videoFile");
			throw new ValidationException("rqst-1001", "Error validating product data: empty, null or blank videoFile");			
		} else {			
			if(!isValidVideoFile(videoFile)) {
				logger.debug("Error validating request data: invalid Base64 file type (must be a video)");
				throw new ValidationException("rqst-1001", "Error validating request data: invalid Base64 file type (must be a video)");		        	
			}
		}

		this.owner = owner;
		this.interval = interval;
		this.videoFile = videoFile;
	}

	private Boolean isValidVideoFile(String videoFile) throws ValidationException {
		try	{
			byte[] decodedBytes = Base64.getDecoder().decode(videoFile);

			ByteArrayInputStream inputStream = new ByteArrayInputStream(decodedBytes);
			String mimeType = URLConnection.guessContentTypeFromStream(inputStream);
			if((mimeType == null) || (!mimeType.startsWith("video"))) {
				return false;		        	
			}
		} catch (Exception e) {
			logger.debug("Error validating request data: invalid Base64 image file");
			throw new ValidationException("rqst-1001", "Error validating request data: invalid Base64 image file");				
		}

		return true;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public String getVideoFile() {
		return videoFile;
	}

	public void setVideoFile(String videoFile) throws ValidationException {
		if((videoFile == null) || (videoFile.isEmpty()) || (videoFile.isBlank())){
			logger.debug("Error validating product data: empty, null or blank videoFile");
			throw new ValidationException("rqst-1001", "Error validating product data: empty, null or blank videoFile");			
		} else {			
			if(!isValidVideoFile(videoFile)) {
				logger.debug("Error validating request data: invalid Base64 file type (must be a video)");
				throw new ValidationException("rqst-1001", "Error validating request data: invalid Base64 file type (must be a video)");		        	
			}
		}
		
		this.videoFile = videoFile;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
