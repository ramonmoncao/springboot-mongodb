package com.ramonmoncao.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramonmoncao.workshopmongo.domain.User;
import com.ramonmoncao.workshopmongo.repository.UserRepository;
import com.ramonmoncao.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	public User findbyId(String id){
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Object not found."));
	}
}
