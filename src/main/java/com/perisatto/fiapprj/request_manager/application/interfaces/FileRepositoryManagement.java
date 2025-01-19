package com.perisatto.fiapprj.request_manager.application.interfaces;

public interface FileRepositoryManagement {

	String generateUploadFileURL(String id, String videoFile);
	
	String generateDownloadFileURL(String id, String videoFile);
}
