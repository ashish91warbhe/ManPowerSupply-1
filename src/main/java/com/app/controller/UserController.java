package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.User;
import com.app.repository.UserRepository;

@RestController
@RequestMapping("api/user")
public class UserController {
@Autowired
private UserRepository userRepository;


//1.create users
@PostMapping("/create")
public User createUser(@RequestBody User user) {
	return this.userRepository.save(user);
	
}


//2.get All users
@GetMapping("/getAllUsers")
public List<User> getAllUsers(){
	return this.userRepository.findAll();
	
}


//3.get user by id
@GetMapping("/{id}")
public User getUserById(@PathVariable (name="id") long userId){
	return this.userRepository.findById(userId).orElseThrow(null);
	
}
//4.update user

@PutMapping("/{id}")
public User getUserById(@RequestBody User user,@PathVariable (name="id") long userId){
	User exist=this.userRepository.findById(userId).orElseThrow(null);
	exist.setFirstName(user.getFirstName());
	exist.setLastName(user.getLastName());
	exist.setEmail(user.getEmail());
	
	return this.userRepository.save(exist);
	
}
//5.delete user by id

@DeleteMapping("/{id}")
public ResponseEntity DeleteUserById(@PathVariable (name="id") long userId){
	User exist=this.userRepository.findById(userId).orElseThrow(null);
	this.userRepository.delete(exist);
	return ResponseEntity.ok().build();
			
}
}
