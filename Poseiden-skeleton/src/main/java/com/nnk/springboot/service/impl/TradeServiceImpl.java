package com.nnk.springboot.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.ITradeService;

@Service
public class TradeServiceImpl implements ITradeService {

  final static Logger logger = LogManager.getLogger(TradeServiceImpl.class);

  @Autowired
  TradeRepository tradeRepository;

  @Override
  public List<Trade> getAllTrades() {
    logger.debug("Start finding all trades");
    logger.info("Getting all trades");

    if (tradeRepository.findAll().isEmpty()) {
      logger.error("No trades founded");
    }
    return tradeRepository.findAll();
  }

  @Override
  public Trade getTradeById(int id) {
    // vérifier
    logger.debug("Finding trade with id : {}", id);
    if (tradeRepository.findById(id).isPresent()) {
      logger.info("Founded trade with id : {}", id);
      return tradeRepository.findById(id).get();
    } else {
      logger.error("No trade founded with id : {}", id);
      return null;
    }
  }

  @Override
  public Trade saveTrade(Trade trade) throws Exception {
    logger.debug("Creating trade with id : {}", trade.getTradeId());
    // Checking if trade already exist
    if (tradeRepository.findById(trade.getTradeId()).isPresent()) {
      logger.error("This trade cannot be created because already exist");
      throw new Exception("This trade already exist");
    } else {
      logger.info("Trade with id : {} created", trade.getTradeId());
      tradeRepository.save(trade);
    }
    return trade;
  }

  @Override
  public Trade updateTrade(Trade trade) throws Exception {
    logger.debug("Updating curvePoint with id : {}", trade.getTradeId());
    // Checking if trade already exist
    if (tradeRepository.findById(trade.getTradeId()).isPresent()) {
      logger.info("Trade with id : {} updated", trade.getTradeId());
      return tradeRepository.save(trade);
    }

    else {
      logger.error("This trade cannot be updated because not founded");
      throw new Exception("Trade to update not founded");
    }
  }

  @Override
  public void deleteTrade(Trade trade) throws Exception {

    logger.debug("Deleting trade with id : {}", trade.getTradeId());

    if (getTradeById(trade.getTradeId()) != null) {
      logger.info("Trade with id : {} deleted", trade.getTradeId());
      tradeRepository.delete(trade);
    } else {
      logger.error("This trade cannot be deleted because not founded");
      throw new Exception("Trade to delete not founded");
    }
  }

}
