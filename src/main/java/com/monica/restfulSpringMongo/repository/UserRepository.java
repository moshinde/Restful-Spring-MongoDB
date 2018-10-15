package com.monica.restfulSpringMongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.monica.restfulSpringMongo.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
