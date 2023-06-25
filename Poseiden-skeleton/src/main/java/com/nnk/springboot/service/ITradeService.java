package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.Trade;

/**
 * Interface for services operations concerning Trade in Trading App UI (Poseidon inc)
 *
 * @author PUYJALON Pierre
 * @since 25/06/2023
 */
public interface ITradeService {

  /**
   * Retrieve a list of all Trade
   * 
   * @return List of Trade
   */
  public List<Trade> getAllTrades();

  /**
   * Retrieve a Trade by his id
   * 
   * @param id - int
   * @return Trade
   * @throws Exception
   */
  public Trade getTradeById(int id);

  /**
   * Save a Trade
   * 
   * @param trade - Trade
   * @return Trade
   * @throws Exception
   */
  public Trade saveTrade(Trade trade) throws Exception;

  /**
   * Update an existing Trade
   * 
   * @param trade - Trade
   * @return Trade
   * @throws Exception
   */
  public Trade updateTrade(Trade trade) throws Exception;

  /**
   * Delete a Trade
   * 
   * @param trade - Trade
   * @throws Exception
   */
  public void deleteTrade(Trade trade) throws Exception;

}
