package com.nnk.springboot.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.controllers.HomeController;
import com.nnk.springboot.controllers.LoginController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LoginController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class LoginControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  private UserRepository userRepository;

  List<User> userList = new ArrayList<>();

  @Test
  @WithMockUser (roles = "ADMIN")
  public void getViewHomeUserModelTest() throws Exception {

    when(userRepository.findAll()).thenReturn(userList);
    mockMvc.perform(get("/app/secure/article-details"))
           .andExpect(view().name("user/list"))
           .andExpect(model().attribute("users", userList));
  }

  @Test @WithMockUser(roles = "ADMIN")
  public void getViewErrorTest() throws Exception {
    mockMvc.perform(get("/app/error")).andExpect(view().name("../static/error/403"))
        .andExpect(model().attributeExists("errorMsg"));
  }

}
