package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class MyUserDetailsServiceTest {

  @Autowired
  MyUserDetailsService myUserDetailsService;

  @MockBean
  UserRepository userRepository;

  private User connectedUser = new User();

  @BeforeEach
  public void setup() {
    connectedUser.setFullname("admin");
    connectedUser.setUsername("admin");
    connectedUser.setRole("ADMIN");
    connectedUser.setPassword("$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa");
    connectedUser.setId(4);

  }

  @Test
  public void loadUserByUsernameTest() {
    String username = "admin";

    when(userRepository.findByUsername(username)).thenReturn(Optional.of(connectedUser));

    UserDetails userToRetrieve = myUserDetailsService.loadUserByUsername(username);

    assertEquals("admin", userToRetrieve.getUsername());

    // same with USER role
    connectedUser.setRole("USER");
    userToRetrieve = myUserDetailsService.loadUserByUsername(username);
    assertEquals("admin", userToRetrieve.getUsername());
  }

  @Test
  public void loadUserByUsernameFailTest() {
    String username = "test";

    try {
      when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
      // when
      myUserDetailsService.loadUserByUsername(username);

    } catch (UsernameNotFoundException e) {
      assertEquals(e.getMessage(), "Cannot find user from username : " + username);
      verify(userRepository, times(1)).findByUsername(username);
    }
  }

}
