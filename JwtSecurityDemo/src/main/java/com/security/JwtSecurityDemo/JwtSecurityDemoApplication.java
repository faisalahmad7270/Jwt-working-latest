package com.security.JwtSecurityDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.security.JwtSecurityDemo.entity.Role;
import com.security.JwtSecurityDemo.entity.User;
import com.security.JwtSecurityDemo.repository.UserRepository;

@SpringBootApplication
public class JwtSecurityDemoApplication implements CommandLineRunner{
	
	@Autowired
	private UserRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(JwtSecurityDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User adminAcc=repo.findByRole(Role.ADMIN);
		if(adminAcc==null) {
			User user=new User();
			user.setEmail("faisal@gmail.com");
			user.setFirstName("faisal");
			user.setSecondName("ahmad");
			user.setPassword(new BCryptPasswordEncoder().encode("7270"));
			user.setRole(Role.ADMIN);
			repo.save(user);
		}
		
	}

}
