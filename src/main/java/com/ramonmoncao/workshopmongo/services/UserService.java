package com.ramonmoncao.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramonmoncao.workshopmongo.domain.User;
import com.ramonmoncao.workshopmongo.dto.UserDTO;
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
	public User insert(User obj) {
		return repository.insert(obj);
	}
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(),objDto.getName(),objDto.getEmail());
	}
	public void delete(String id) {
		findbyId(id);
		repository.deleteById(id);
	}
	public User update(User obj) {
		User newObj = findbyId(obj.getId());
		updateData(newObj,obj);
		return repository.save(newObj);
	
		
	}
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
}
