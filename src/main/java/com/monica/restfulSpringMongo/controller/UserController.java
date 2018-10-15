package com.monica.restfulSpringMongo.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.monica.restfulSpringMongo.exception.PostNotFoundException;
import com.monica.restfulSpringMongo.exception.UserNotFoundException;
import com.monica.restfulSpringMongo.model.Post;
import com.monica.restfulSpringMongo.model.User;
import com.monica.restfulSpringMongo.service.UserService;

@RestController
public class UserController {

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.retrieveAllUsers();
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user){
		User savedUser=userService.createUser(user);
		URI location =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/users/{id}")
	public Resource<User> getById(@PathVariable String id) {
		Optional<User> user=  userService.getById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found for ID="+id);
		}
		Resource<User> resource=new Resource<User>(user.get());
		ControllerLinkBuilder linkTo=linkTo(methodOn(this.getClass()).getAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	@DeleteMapping("/users/{id}")
	public String removeUser(@PathVariable String id) {
		 userService.deleteUserById(id);
		 return "User has been removed";
		
	}
	
	@GetMapping("/say-hello")
	public String sayHelloInternationalized(@RequestHeader(name="Accept-Language", required=false)Locale locale) {
		return messageSource.getMessage("good.morning.message", null,locale);
	}
	
	@GetMapping("/users/{id}/posts")
	public List<Post> retrievePostsByUserId(@PathVariable String id){
		return userService.getById(id).get().getListPost();
	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> createPost(@Valid @RequestBody Post post){
		Post savedPost=userService.savePost(post);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/users/{userId}/posts/{postId}")
	public Resource<Post> getByPostId(@PathVariable String userId,@PathVariable String postId){
		Optional<Post> post=userService.getPost(postId);
		if(!post.isPresent()) {
			throw new PostNotFoundException("Post not found for ID="+postId);
		}
		Resource<Post> resource=new Resource<Post>(post.get());
		ControllerLinkBuilder linkTo=linkTo(methodOn(this.getClass()).retrievePostsByUserId(userId));
		resource.add(linkTo.withRel("/all-posts"));
		return resource;
	}
}
