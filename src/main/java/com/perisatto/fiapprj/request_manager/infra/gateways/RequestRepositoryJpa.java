package com.perisatto.fiapprj.request_manager.infra.gateways;

import java.util.Optional;

import com.perisatto.fiapprj.request_manager.application.interfaces.RequestRepository;
import com.perisatto.fiapprj.request_manager.domain.entities.Request;

public class RequestRepositoryJpa implements RequestRepository {

	@Override
	public Request createRequest(Request request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Request> updateRequest(Request request) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
