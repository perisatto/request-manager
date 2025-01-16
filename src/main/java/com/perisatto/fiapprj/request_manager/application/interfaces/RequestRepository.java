package com.perisatto.fiapprj.request_manager.application.interfaces;

import java.util.Optional;

import com.perisatto.fiapprj.request_manager.domain.entities.Request;

public interface RequestRepository {

	Request createRequest(Request request) throws Exception;

	Optional<Request> updateRequest(Request request);
}
