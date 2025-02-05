package com.perisatto.fiapprj.request_manager.infra.controllers.dtos;

import java.util.LinkedHashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.perisatto.fiapprj.request_manager.domain.entities.Request;

public class GetRequestListResponseDTO {
	@JsonProperty(value = "_page")
	private Integer page;
	@JsonProperty(value = "_size")
	private Integer size;
	@JsonProperty(value = "_pageElements")
	private Integer pageElements;
	
	@JsonProperty(value = "_content")
	private Set<GetRequestResponseDTO> content = new LinkedHashSet<>();
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getPageElements() {
		return pageElements;
	}

	public void setPageElements(Integer pageElements) {
		this.pageElements = pageElements;
	}

	public Set<GetRequestResponseDTO> getContent() {
		return content;
	}

	public void setContent(Set<GetRequestResponseDTO> content) {
		this.content = content;
	}
	
	public void setContent(Set<Request> content, Integer page, Integer size) {
		this.setPage(page);
		this.setSize(size);
		this.setPageElements(content.size());
		ModelMapper requestMapper = new ModelMapper();
		
		for(Request request : content) {
			GetRequestResponseDTO newRequestResponse = requestMapper.map(request, GetRequestResponseDTO.class);
			this.content.add(newRequestResponse);
		}
	}
}
