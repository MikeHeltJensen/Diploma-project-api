package com.example.backendapi.repository;

import com.example.backendapi.model.SystemData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SystemDataRepository extends MongoRepository<SystemData, String> {
    // No custom queries, only basic CRUD operations
}
