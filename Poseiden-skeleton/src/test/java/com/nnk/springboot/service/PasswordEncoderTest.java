package com.nnk.springboot.service;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class PasswordEncoderTest {

  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Test
  public void passwordEncoderTest() {

    String password = "Password1!";
    String encodedPassword = passwordEncoder.encode(password);

    assertNotEquals(password, encodedPassword);
    assertNotNull(encodedPassword);
    assertTrue(passwordEncoder.matches(password, encodedPassword));

  }

}
