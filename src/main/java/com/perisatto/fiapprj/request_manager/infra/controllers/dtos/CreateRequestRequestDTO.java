package com.perisatto.fiapprj.request_manager.infra.controllers.dtos;

public class CreateRequestRequestDTO {
	private Integer interval;
	private String videoFile;
	
	public Integer getInterval() {
		return interval;
	}
	public void setInterval(Integer interval) {
		this.interval = interval;
	}
	public String getVideoFile() {
		return videoFile;
	}
	public void setVideoFile(String videoFile) {
		this.videoFile = videoFile;
	}
	
	
}
