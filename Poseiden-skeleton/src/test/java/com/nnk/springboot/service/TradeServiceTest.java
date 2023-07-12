package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class TradeServiceTest {

  @Autowired
  ITradeService iTradeService;

  @MockBean
  TradeRepository tradeRepository;

  Trade trade = new Trade();

  List<Trade> listTrades = new ArrayList<>();

  @BeforeEach
  public void setup() {

    trade.setTradeId(1);
    trade.setAccount("Trade Account");
    trade.setType("Type");
    trade.setBuyQuantity(10.0);
  }

  @Test
  public void getTradesTest() {

    listTrades.add(trade);

    // when
    when(tradeRepository.findAll()).thenReturn(listTrades);

    assertEquals(iTradeService.getAllTrades().size(), 1);

    assertEquals((int) iTradeService.getAllTrades().get(0).getTradeId(), 1);
    assertEquals(iTradeService.getAllTrades().get(0).getAccount(), "Trade Account");
    assertEquals(iTradeService.getAllTrades().get(0).getType(), "Type");
    assertEquals((double) iTradeService.getAllTrades().get(0).getBuyQuantity(), 10.0);
  }

  @Test
  public void getTradeByIdTest() throws Exception {

    // when
    when(tradeRepository.findById(trade.getTradeId())).thenReturn(Optional.of(trade));
    
    
    assertEquals(((int)iTradeService.getTradeById(trade.getTradeId()).getTradeId()),
        1);
    assertEquals((iTradeService.getTradeById(trade.getTradeId()).getAccount()),
        "Trade Account");
    assertEquals(((double)iTradeService.getTradeById(trade.getTradeId()).getBuyQuantity()),
       10.0);

  }

  @Test
  public void saveTradeTest() throws Exception {

    Trade trade2 = new Trade();

    trade2.setTradeId(2);
    trade2.setAccount("Trade Account");
    trade2.setType("Type");
    trade2.setBuyQuantity(10.0);

    when(tradeRepository.save(trade2)).thenReturn(trade2);

    // when

    assertEquals(iTradeService.saveTrade(trade2), trade2);
    verify(tradeRepository, times(1)).save(trade2);
  }

  @Test
  public void updateTradeTest() throws Exception {

    trade.setTradeId(5);
    trade.setAccount("Trade Account");
    trade.setType("Type");
    trade.setBuyQuantity(10.0);

    when(tradeRepository.findById(trade.getTradeId())).thenReturn(Optional.of(trade));
    when(tradeRepository.save(trade)).thenReturn(trade);
    // when

    assertEquals(iTradeService.updateTrade(trade), trade);
    verify(tradeRepository, times(1)).save(trade);

  }

  @Test
  public void updateTradeFailTest() throws Exception {
    try {
      trade.setTradeId(5);
      trade.setAccount("Trade Account");
      trade.setType("Type");
      trade.setBuyQuantity(10.0);

      when(tradeRepository.findById(trade.getTradeId())).thenReturn(Optional.empty());

      // when
      iTradeService.updateTrade(trade);

    } catch (Exception e) {
      assertEquals(e.getMessage(), "Trade to update not founded");
    }
  }

  @Test
    public void deleteTradeTest() throws Exception {
    
    when(tradeRepository.findById(trade.getTradeId())).thenReturn(Optional.of(trade));
      
      // when
    iTradeService.deleteTrade(trade);
      
      verify(tradeRepository, times(1)).delete(trade);

    }

  @Test
  public void deleteTradeFailTest() throws Exception {

    try {
      when(tradeRepository.findById(trade.getTradeId())).thenReturn(Optional.empty());

      // when
      iTradeService.deleteTrade(trade);

    } catch (Exception e) {
      assertEquals(e.getMessage(), "Trade to delete not founded");
      verify(tradeRepository, times(0)).delete(trade);
    }

  }

}
