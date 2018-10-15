package com.monica.restfulSpringMongo.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monica.restfulSpringMongo.model.Post;
import com.monica.restfulSpringMongo.model.User;
import com.monica.restfulSpringMongo.repository.PostRepository;
import com.monica.restfulSpringMongo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	
	public List<User> retrieveAllUsers(){
		return userRepository.findAll();
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> getById(String id) {
		return userRepository.findById(id);
	}
	
	public void deleteUserById(String id) {
		 userRepository.deleteById(id);
	}

	public Post savePost(Post post) {
		return postRepository.save(post);
	}
	
	public Optional<Post> getPost(String postId) {
		return postRepository.findById(postId);
	}
	
	
}
