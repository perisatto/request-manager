package com.perisatto.fiapprj.request_manager.infra.persistences.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perisatto.fiapprj.request_manager.infra.persistences.entities.RequestEntity;

public interface RequestPersistenceRepository extends JpaRepository<RequestEntity, Long> {
	
	Optional<RequestEntity> findById(String id); 
}
