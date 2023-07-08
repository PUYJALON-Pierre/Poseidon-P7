package com.nnk.springboot.domain;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jparams.verifier.tostring.ToStringVerifier;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserTest {
  
  @Test
  public void userHashCodeTest() {

    User user1 = new User();
    
    user1.setUsername("benoit");
    user1.setFullname("benoit");
    user1.setPassword("Benoit1!");
    user1.setRole("ADMIN");
    user1.setId(1);
    
    User user2 = new User();
    
    user2.setUsername("benoit");
    user2.setFullname("benoit");
    user2.setPassword("Benoit1!");
    user2.setRole("ADMIN");
    user1.setId(2);

    assertNotSame(user1, user2);
    assertEquals(user1.hashCode(), user2.hashCode());
  }

  @Test
  public void userToStringTest() {

    ToStringVerifier.forClass(User.class).verify();
  }

  
  
}
