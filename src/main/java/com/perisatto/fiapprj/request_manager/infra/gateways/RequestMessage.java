package com.perisatto.fiapprj.request_manager.infra.gateways;

import java.io.Serializable;

import com.perisatto.fiapprj.request_manager.domain.entities.RequestStatus;

public class RequestMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idRequest;
	private String id;
	private String owner;
	private Integer interval;
	private RequestStatus status;
	private String videoFileName;
	private String remarks;
	private String videoUploadUrl;
	private String videoDownloadUrl;
	public Long getIdRequest() {
		return idRequest;
	}
	public void setIdRequest(Long idRequest) {
		this.idRequest = idRequest;
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
	public String getVideoFileName() {
		return videoFileName;
	}
	public void setVideoFileName(String videoFileName) {
		this.videoFileName = videoFileName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getVideoUploadUrl() {
		return videoUploadUrl;
	}
	public void setVideoUploadUrl(String videoUploadUrl) {
		this.videoUploadUrl = videoUploadUrl;
	}
	public String getVideoDownloadUrl() {
		return videoDownloadUrl;
	}
	public void setVideoDownloadUrl(String videoDownloadUrl) {
		this.videoDownloadUrl = videoDownloadUrl;
	}
}
