package com.vinicius.tracking_service.domain.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vinicius.tracking_service.domain.model.Location;

public interface LocationRepository extends MongoRepository<Location, UUID> {

}
