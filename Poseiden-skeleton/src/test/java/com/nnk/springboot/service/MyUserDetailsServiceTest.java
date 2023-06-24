package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.UserRepository;

@SpringBootTest @TestInstance(Lifecycle.PER_CLASS)
public class MyUserDetailsServiceTest {

  @Autowired
 MyUserDetailsService myUserDetailsService;

  @MockBean
  UserRepository userRepository;



  private User connectedUser;
  
  @BeforeEach
  public void setup() {

   // connectedUser = new User("admin","$2a$10$pBV8ILO/s/nao4wVnGLrh.sa/rnr5pDpbeC4E.KNzQWoy8obFZdaa","admin name","ADMIN");
  }

  @AfterEach
  public void clear() {
    userRepository.deleteAll();
  }

  @Test
  public void loadUserByUsernameTest() {
    String username = "yveslelong@gmail.com";
    UserDetails userToRetrieve = myUserDetailsService.loadUserByUsername(username);

    assertEquals("yveslelong@gmail.com", userToRetrieve.getUsername());

  

  }
  
  @Test
  public void loadUserByUsernameFailTest() {
    String username = "yveslelong@gmail.com";
    try {

      // when
      
      UserDetails userToRetrieve = myUserDetailsService.loadUserByUsername(username);

    } catch (Exception e) {
      assertEquals(e.getMessage(), "Cannot find user from username : " + username);

    }

  

  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
