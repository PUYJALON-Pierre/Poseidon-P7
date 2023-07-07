package com.nnk.springboot.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.IBidListService;


@WebMvcTest(controllers = BidListController.class)
@AutoConfigureMockMvc(addFilters = false)
public class BidListControllerTest {
  
  
  @Autowired
  MockMvc mockMvc;
  
  @MockBean
  IBidListService iBidListService;
  
  @MockBean
  BidListRepository bidListRepository;
  
  private BidList  bid;
  private List<BidList> bidList;
  
  public void setup() {
    bid = new BidList("account", "type", 10d);
    bidList = Collections.singletonList(bid);
    
    when(iBidListService.getAllBidLists()).thenReturn(bidList);
  }
  
  
  @Test
  public void getViewBidlistPageModelTest() throws Exception {
    mockMvc.perform(get("/bidList/list")).andExpect(status().isOk());
  }
  
  
  
  
  

  
  
  
}
