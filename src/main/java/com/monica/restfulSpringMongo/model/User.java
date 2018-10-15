package com.monica.restfulSpringMongo.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="User")
public class User {
	@Id
	private String id;
	
	@Size(min=2, message="Human name cant be of size 2")
	private String name;
	
	@Past(message="One cant born in future")
	private Date dob;

	List<Post> listPost;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", dob=" + dob + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public List<Post> getListPost() {
		return listPost;
	}
	public void setListPost(List<Post> listPost) {
		this.listPost = listPost;
	}
	public User(String id, String name,Date dob, List<Post> listPost) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.listPost = listPost;
	}
	public User() {
	}
	
}
