package com.nnk.springboot.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.controllers.HomeController;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HomeController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class HomeControllerTest {

  @Autowired
  MockMvc mockMvc;
  
  
  @Test
  @WithMockUser
  public void getViewHomePageModelTest() throws Exception {
    mockMvc.perform(get("/")).andExpect(status().isOk());
       
  }  
  
  @Test
  @WithMockUser
  public void getViewAdminHomePageModelTest() throws Exception {
    mockMvc.perform(get("/admin/home")).andExpect(redirectedUrl("/bidList/list"))
    .andExpect(status().is3xxRedirection());
  }  
  
}
