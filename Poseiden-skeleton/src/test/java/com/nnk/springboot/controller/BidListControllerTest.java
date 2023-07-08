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
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.IBidListService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BidListController.class)
@TestInstance(Lifecycle.PER_CLASS)
public class BidListControllerTest {
  
  
  @Autowired
  MockMvc mockMvc;
  
  @MockBean
  IBidListService iBidListService;
  
  @MockBean
  BidListRepository bidListRepository;
  
  private BidList  bid;
  private List<BidList> bidList = new ArrayList<>();
  
  @BeforeEach
  public void setup() {
    bid = new BidList("account", "type", 10d);

    bidList.add(bid);
    
    when(iBidListService.getAllBidLists()).thenReturn(bidList);
  }
  
  
  @Test
  @WithMockUser(roles = "ADMIN")
  public void getViewBidlistPageModelTest() throws Exception {
    mockMvc.perform(get("/bidList/list")).andExpect(status().isOk());
  }
  
  @Test
  @WithMockUser(roles = "ADMIN")
  public void getViewAddBidlistPageModelTest() throws Exception {
    mockMvc.perform(get("/bidList/add")).andExpect(status().isOk());
  }
  
  
  @Test
  @WithMockUser(roles = "ADMIN")
  public void postBidlistTest() throws Exception {
    mockMvc.perform(post("/bidList/validate")
        .with(csrf().asHeader())   //not working without
        .param("account", "account")
        .param("type", "type")
        .param("bidQuantity", "10d"))
        .andExpect(redirectedUrl("/bidList/list")).andExpect(status().is3xxRedirection());

  }
  

  
  @Test
  @WithMockUser(roles = "ADMIN")
  public void getViewUpdateBidlistPageModelTest() throws Exception {
  
    when(bidListRepository.findById(6)).thenReturn(Optional.of(bid));
    when(iBidListService.getBidListByBidListId(6)).thenReturn(bid);
    
    mockMvc.perform(get("/bidList/update/{id}", "6")).andExpect(status().isOk());}

  
  @Test
  @WithMockUser(roles = "ADMIN")
  public void updateBidlistTest() throws Exception {
 
    when(bidListRepository.findById(6)).thenReturn(Optional.of(bid));
    when(iBidListService.updateBidList(bid)).thenReturn(bid);
    
    mockMvc.perform(post("/bidList/update/{id}", "6")
        .with(csrf().asHeader())   //not working without
        .param("account", "account")
        .param("type", "type")
        .param("bidQuantity", "10d"))
        .andExpect(redirectedUrl("/bidList/list")).andExpect(status().is3xxRedirection());

  }
  
  
  @Test
  @WithMockUser(roles = "ADMIN")
  public void deleteBidlistTest() throws Exception {
    
    when(bidListRepository.findById(6)).thenReturn(Optional.of(bid));
    when(iBidListService.getBidListByBidListId(6)).thenReturn(bid);
    
    mockMvc.perform(get("/bidList/delete/{id}", "6").param("account", "account")
        .with(csrf().asHeader())   //not working without
        .param("type", "type")
        .param("bidQuantity", "10d"))
        .andExpect(redirectedUrl("/bidList/list")).andExpect(status().is3xxRedirection());

  }
  
 
  
  
  
}
