package com.perisatto.fiapprj.request_manager.domain.entities;

public enum RequestStatus {
	
	PENDING(1L),
	PROCESSING(2L),
	ERROR(3L),
	COMPLETED(4L);
	
	private Long id;
	
	RequestStatus(Long id){
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
