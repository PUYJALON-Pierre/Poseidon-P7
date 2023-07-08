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
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.IBidListService;
import com.nnk.springboot.service.ICurvePointService;
import com.nnk.springboot.service.IRatingService;
import com.nnk.springboot.service.IRuleNameService;
import com.nnk.springboot.service.ITradeService;

@ExtendWith(SpringExtension.class) @WebMvcTest(controllers = TradeController.class) @TestInstance(Lifecycle.PER_CLASS)
public class TradeControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  ITradeService iTradeService;

  @MockBean
  TradeRepository tradeRepository;

  private Trade trade;
  private List<Trade> trades = new ArrayList<>();

  @BeforeEach
  public void setup() {

    trade = new Trade("Trade Account", "Type", 10.0);

    trades.add(trade);

    when(iTradeService.getAllTrades()).thenReturn(trades);
  }

  @Test @WithMockUser(roles = "ADMIN")
  public void getViewTradePageModelTest() throws Exception {
    mockMvc.perform(get("/trade/list")).andExpect(status().isOk());
  }

  @Test @WithMockUser(roles = "ADMIN")
  public void getViewAddTradePageModelTest() throws Exception {
    mockMvc.perform(get("/trade/add")).andExpect(status().isOk());
  }

  @Test @WithMockUser(roles = "ADMIN")
  public void postTradeTest() throws Exception {
    mockMvc
        .perform(post("/trade/validate")
            .with(csrf().asHeader())
            .param("account", trade.getAccount())
            .param("type", trade.getType())
            .param("buyQuantity", trade.getBuyQuantity().toString()))
        .andExpect(redirectedUrl("/trade/list")).andExpect(status().is3xxRedirection());

  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void getViewUpdateTradePageModelTest() throws Exception {
  
    when(tradeRepository.findById(6)).thenReturn(Optional.of(trade));
    when(iTradeService.getTradeById(6)).thenReturn(trade);
    
    mockMvc.perform(get("/trade/update/{id}", "6")).andExpect(status().isOk());}

  @Test
  @WithMockUser(roles = "ADMIN")
  public void updateTradeTest() throws Exception {
 
    when(tradeRepository.findById(6)).thenReturn(Optional.of(trade));
    when(iTradeService.updateTrade(trade)).thenReturn(trade);
    
    mockMvc.perform(post("/trade/update/{id}", "6")
        .with(csrf().asHeader())
        .param("account", trade.getAccount())
        .param("type", trade.getType())
        .param("buyQuantity", trade.getBuyQuantity().toString()))
        .andExpect(redirectedUrl("/trade/list")).andExpect(status().is3xxRedirection());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void deleteTradeTest() throws Exception {
    
    when(tradeRepository.findById(6)).thenReturn(Optional.of(trade));
    when(iTradeService.getTradeById(6)).thenReturn(trade);
    
    mockMvc.perform(get("/trade/delete/{id}", "6")
        .with(csrf().asHeader())
        .param("account", trade.getAccount())
        .param("type", trade.getType())
        .param("buyQuantity", trade.getBuyQuantity().toString()))
        .andExpect(redirectedUrl("/trade/list")).andExpect(status().is3xxRedirection());

  }

}
