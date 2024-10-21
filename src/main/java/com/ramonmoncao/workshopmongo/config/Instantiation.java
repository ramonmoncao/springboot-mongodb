package com.ramonmoncao.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ramonmoncao.workshopmongo.domain.Post;
import com.ramonmoncao.workshopmongo.domain.User;
import com.ramonmoncao.workshopmongo.dto.AuthorDTO;
import com.ramonmoncao.workshopmongo.dto.CommentDTO;
import com.ramonmoncao.workshopmongo.repository.PostRepository;
import com.ramonmoncao.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		User maria = new User(null, "Maria Brown", "maria@gmail.com"); 
		User alex = new User(null, "Alex Green", "alex@gmail.com"); 
		User bob = new User(null, "Bob Grey", "bob@gmail.com"); 
	
		userRepository.deleteAll();
		userRepository.saveAll(Arrays.asList(maria,alex,bob));
		
		Post post1 = new Post(null,sdf.parse("21/03/2024"),"Partiu viajem","Vou viajar para São Paulo. Abraço",new AuthorDTO(maria));
		
		postRepository.deleteAll();
		postRepository.save(post1);
		
		maria.getPosts().add(post1);
		userRepository.save(maria);
		
		CommentDTO comment1 = new CommentDTO("Boa viagem mano!",sdf.parse("2018/03/21"),new AuthorDTO(bob));
		
		post1.getComments().add(comment1);
		postRepository.save(post1);
		
	}

}
