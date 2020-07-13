package com.monogoaggregate.example.repository;

import com.monogoaggregate.example.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerMongoRepository extends MongoRepository<Customer,String> {
}
