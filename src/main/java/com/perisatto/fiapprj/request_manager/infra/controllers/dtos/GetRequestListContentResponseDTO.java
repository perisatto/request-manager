package com.perisatto.fiapprj.request_manager.infra.controllers.dtos;

import com.perisatto.fiapprj.request_manager.domain.entities.RequestStatus;

public class GetRequestListContentResponseDTO {
	private String id;
	private Integer interval;
	private String videoFileName;
	private RequestStatus status;
	private String remarks;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getInterval() {
		return interval;
	}
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	public String getVideoFileName() {
		return videoFileName;
	}
	public void setVideoFileName(String videoFileName) {
		this.videoFileName = videoFileName;
	}
	public RequestStatus getStatus() {
		return status;
	}
	public void setStatus(RequestStatus status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		if(remarks == null) {
			this.remarks = "";
		} else {
			this.remarks = remarks;
		}
	}
}
