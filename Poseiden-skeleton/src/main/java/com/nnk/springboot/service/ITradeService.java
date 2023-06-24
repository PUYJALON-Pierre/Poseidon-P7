package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.Trade;

public interface ITradeService {

  
  
  
  
  public List<Trade> getAllTrades() ;
  
  public Trade getTradeById(int id) ;
  
  public Trade saveTrade(Trade trade) throws Exception ;
  
  public Trade updateTrade(Trade trade) throws Exception ;
  
  public void deleteTrade(Trade trade) throws Exception ;
  
  
  
  
  
  
  
  
}
