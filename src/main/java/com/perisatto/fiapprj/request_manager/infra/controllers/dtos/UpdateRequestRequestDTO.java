package com.perisatto.fiapprj.request_manager.infra.controllers.dtos;

public class UpdateRequestRequestDTO {
	private String status;
	private String remarks;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
