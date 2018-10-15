package com.monica.restfulSpringMongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.monica.restfulSpringMongo.model.Post;
@Repository
public interface PostRepository extends MongoRepository<Post,String>{
	
}
