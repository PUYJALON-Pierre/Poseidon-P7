package com.nnk.springboot.controller;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
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

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.controllers.CurvePointController;
import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.IBidListService;
import com.nnk.springboot.service.ICurvePointService;
import com.nnk.springboot.service.IRatingService;
import com.nnk.springboot.service.IRuleNameService;
import com.nnk.springboot.service.ITradeService;
import com.nnk.springboot.service.IUserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class UserControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  IUserService iUserService;

  @MockBean
  UserRepository userRepository;

  private User user = new User();
  private List<User> users = new ArrayList<>();

  @BeforeEach
  public void setup() {

    user.setId(1);
    user.setUsername("benoit");
    user.setFullname("benoit");
    user.setPassword("Benoit1!");
    user.setRole("ADMIN");

    users.add(user);

    when(iUserService.getAllUsers()).thenReturn(users);
  }

  @Test @WithMockUser(roles = "ADMIN")
  public void getViewUserPageModelTest() throws Exception {
    mockMvc.perform(get("/user/list")).andExpect(status().isOk());
  }

  @Test @WithMockUser(roles = "ADMIN")
  public void getViewAddUserPageModelTest() throws Exception {
    mockMvc.perform(get("/user/add")).andExpect(status().isOk());
  }

  @Test @WithMockUser(roles = "ADMIN")
  public void postUserTest() throws Exception {
    mockMvc
        .perform(post("/user/validate").with(csrf().asHeader())
            .param("username", user.getUsername()).param("password", user.getPassword())
            .param("fullname", user.getFullname()).param("role", user.getRole()))
        .andExpect(redirectedUrl("/user/list")).andExpect(status().is3xxRedirection());

  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void getViewUpdateUserPageModelTest() throws Exception {
  
    when(userRepository.findById(6)).thenReturn(Optional.of(user));
    when(iUserService.getUserById(6)).thenReturn(user);
    
    mockMvc.perform(get("/user/update/{id}", "6")).andExpect(status().isOk());}

  @Test
  @WithMockUser(roles = "ADMIN")
  public void updateUserTest() throws Exception {
 
    when(userRepository.findById(6)).thenReturn(Optional.of(user));
    when(iUserService.updateUser(user)).thenReturn(user);
    
    mockMvc.perform(post("/user/update/{id}", "6")
        .with(csrf().asHeader())
        .param("username", user.getUsername())
        .param("password", user.getPassword())
        .param("fullname", user.getFullname())
        .param("role", user.getRole()))
        .andExpect(redirectedUrl("/user/list")).andExpect(status().is3xxRedirection());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void deleteUserTest() throws Exception {
  
    when(userRepository.findById(6)).thenReturn(Optional.of(user));
    when(iUserService.getUserById(6)).thenReturn(user);
    
    mockMvc.perform(get("/user/delete/{id}", "6")
        .with(csrf().asHeader())
        .param("username", user.getUsername())
        .param("password", user.getPassword())
        .param("fullname", user.getFullname())
        .param("role", user.getRole()))
        .andExpect(redirectedUrl("/user/list")).andExpect(status().is3xxRedirection());

  }

}
