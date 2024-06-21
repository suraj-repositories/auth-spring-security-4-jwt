package com.on20jun2024.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.on20jun2024.entity.UserInfo;
import com.on20jun2024.repositories.UserRepository;
import com.on20jun2024.security.CustomUserDetails;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws RuntimeException {

        logger.debug("Entering in loadUserByUsername Method...");
        UserInfo user = userRepository.findByUsername(username);
        if(user == null){
            logger.error("Username not found: " + username);
            throw new RuntimeException("could not found user..!!");
        }
        logger.info("User Authenticated Successfully..!!!");
        return new CustomUserDetails(user);
    }
}