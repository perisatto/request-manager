package com.perisatto.fiapprj.request_manager.infra.gateways;

import java.util.Optional;

import com.perisatto.fiapprj.request_manager.application.interfaces.RequestRepository;
import com.perisatto.fiapprj.request_manager.domain.entities.Request;
import com.perisatto.fiapprj.request_manager.infra.gateways.mappers.RequestMapper;
import com.perisatto.fiapprj.request_manager.infra.persistences.entities.RequestEntity;
import com.perisatto.fiapprj.request_manager.infra.persistences.repositories.RequestPersistenceRepository;

public class RequestRepositoryJpa implements RequestRepository {

	private final RequestPersistenceRepository requestPersistenceRepository;
	private final RequestMapper requestMapper;
	
	public RequestRepositoryJpa(RequestPersistenceRepository requestPersistenceRepository, RequestMapper requestMapper) {
		this.requestPersistenceRepository = requestPersistenceRepository;
		this.requestMapper = requestMapper;
	}
	
	@Override
	public Request createRequest(Request request) throws Exception {
		RequestEntity requestJpaEntity = requestMapper.mapToJpaEntity(request);
		requestJpaEntity.setIdRequestStatus(1L);
		requestJpaEntity = requestPersistenceRepository.save(requestJpaEntity);
		Request newRequest;
		newRequest = requestMapper.mapToDomainEntity(requestJpaEntity);
		return newRequest;
	}

	@Override
	public Optional<Request> updateRequest(Request request) throws Exception {
		RequestEntity requestEntity = requestMapper.mapToJpaEntity(request);
		requestEntity = requestPersistenceRepository.save(requestEntity);
		Request updatedRequest = requestMapper.mapToDomainEntity(requestEntity);
		return Optional.of(updatedRequest);
	}

	@Override
	public Optional<Request> getRequestById(String id) throws Exception {
		Request request;
		
		Optional<RequestEntity> requestEntity = requestPersistenceRepository.findById(id);
		if(requestEntity.isPresent()) {
			request = requestMapper.mapToDomainEntity(requestEntity.get());
		} else {
			return Optional.empty();
		}
		
		return Optional.of(request);
	}

}
