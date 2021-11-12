package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Descriptions;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface DescriptionsRepository  extends MongoRepository<Descriptions, Integer> {
}
