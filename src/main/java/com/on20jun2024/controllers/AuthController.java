package com.on20jun2024.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.on20jun2024.dto.AuthRequestDTO;
import com.on20jun2024.dto.JwtResponseDTO;
import com.on20jun2024.services.JwtService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponseDTO> AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO, HttpServletResponse response){
		
		String username = authRequestDTO.getUsername();
		String password = authRequestDTO.getPassword();
		
		/* FOR REST API's */
	    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	    if(authentication.isAuthenticated()){	    	
	    	String token = jwtService.GenerateToken(username);
	    	
	    	/* you can also SAVE YOUR TOKEN INTO COOKIE FOR FORM BASED OPERATIONS*/
			/*
			Cookie cookie = new Cookie("jwtToken", token);
			cookie.setHttpOnly(true);
			cookie.setSecure(true); //  if you are not using HTTPS make it false | it is for production only
			cookie.setPath("/");
			cookie.setMaxAge(3600); // 1 Hour
			response.addCookie(cookie);
	    	*/
	    	
	    	return ResponseEntity.ok(JwtResponseDTO.builder()
	               .accessToken(token).build());
	    } else { 
	        throw new UsernameNotFoundException("invalid user request..!!");
	    }
	    
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/ping")
	public ResponseEntity<String> test() {
		try {
	        return ResponseEntity.ok("Pinging....");
	    } catch (Exception e){
	        throw new RuntimeException(" Something went wrong!! ");
	    }
	} 
	
}
