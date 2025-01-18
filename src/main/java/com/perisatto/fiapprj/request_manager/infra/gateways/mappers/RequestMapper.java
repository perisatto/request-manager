package com.perisatto.fiapprj.request_manager.infra.gateways.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import com.perisatto.fiapprj.request_manager.domain.entities.Request;
import com.perisatto.fiapprj.request_manager.infra.persistences.entities.RequestEntity;

public class RequestMapper {
	
	private ModelMapper modelMapper;

	public RequestEntity mapToJpaEntity(Request request) {
		this.modelMapper = new ModelMapper();
		TypeMap<Request, RequestEntity> propertyMapper = this.modelMapper.createTypeMap(Request.class, RequestEntity.class);
		propertyMapper.addMapping(Request::getId, RequestEntity::setId);
		propertyMapper.addMapping(Request::getOwner, RequestEntity::setOwner);
		propertyMapper.addMapping(Request::getInterval, RequestEntity::setTimeInterval);
		propertyMapper.addMapping(Request::getVideoFileName, RequestEntity::setVideoFileName);
		propertyMapper.addMapping(Request::getRemarks, RequestEntity::setRemarks);
		propertyMapper.addMapping(Request::getVideoUploadUrl, RequestEntity::setVideoUploadUrl);
		propertyMapper.addMapping(Request::getVideoDownloadUrl, RequestEntity::setVideoDownloadUrl);
		
		RequestEntity requestJpaEntity = modelMapper.map(request, RequestEntity.class);
		
		requestJpaEntity.setIdRequestStatus(request.getStatus().getId());
		
		return requestJpaEntity;
	}

	public Request mapToDomainEntity(RequestEntity requestJpaEntity) {
		// TODO Auto-generated method stub
		return null;
	}
}
