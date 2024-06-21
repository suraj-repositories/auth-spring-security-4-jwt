package com.on20jun2024;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.on20jun2024.entity.UserInfo;
import com.on20jun2024.entity.UserRole;
import com.on20jun2024.repositories.UserRepository;
import com.on20jun2024.repositories.UserRoleRepository;

@SpringBootApplication
public class AuthSpringSecurity4JwtApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	public static void main(String[] args) {
		SpringApplication.run(AuthSpringSecurity4JwtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		UserRole role = new UserRole();
		role.setName("ADMIN");
		roleRepository.save(role);
		
		UserRole userRole = roleRepository.findByName("ADMIN");
		
		UserInfo user = UserInfo.builder()
						.username("username")
						.password(encoder.encode("123"))
						.roles(new HashSet<>(Arrays.asList(userRole))).build();
		userRepository.save(user);
		
	}

}
