package com.nnk.springboot.service;

import java.util.ArrayList;
import java.util.Collection;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

/**
 * Service class to loads specific data for an User from a database
 *
 * @author PUYJALON Pierre
 * @since 25/06/2023
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

  final static Logger logger = LogManager.getLogger(MyUserDetailsService.class);

  @Autowired
  UserRepository userRepository;

  /**
   * Get authority from a specific user
   * 
   * @param user - User
   * @return Collection of GrantedAuthority
   */
  private Collection<GrantedAuthority> getAuthority(User user) {
    Collection<GrantedAuthority> authorities = new ArrayList<>(2);
    if (user.getRole().equals("ADMIN")) {
      authorities.add(new SimpleGrantedAuthority("ADMIN"));
    } else if (user.getRole().equals("USER")) {
      authorities.add(new SimpleGrantedAuthority("USER"));
    }
    return authorities;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    logger.debug("Searching user with username : {}", username);

    // Search user by mail
    User user = userRepository.findByUsername(username).get();

    if (user == null) {
      logger.error("username : {} not founded", username);
      throw new UsernameNotFoundException("Cannot find user from username : " + username);
    }

    logger.info("username : {} founded", username);

    return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(), true, true, true, true, getAuthority(user));
  }

}
