package com.nnk.springboot.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.IBidListService;

@Service
public class BidListServiceImpl implements IBidListService {

  final static Logger logger = LogManager.getLogger(BidListServiceImpl.class);

  @Autowired
  BidListRepository bidListRepository;

  @Override
  public List<BidList> getAllBidLists() {
    logger.debug("Start finding all bidLists");
    logger.info("Getting all bidLists");

    if (bidListRepository.findAll().isEmpty()) {
      logger.error("No bidLists founded");
    }
    return bidListRepository.findAll();
  }

  @Override
  public BidList getBidListByBidListId(int id) {

    logger.debug("Finding BidList with id : {}", id);
    // vérifier
    if (bidListRepository.findById(id).isPresent()) {
      logger.info("Founded BidList with id : {}", id);
      return bidListRepository.findById(id).get();
    } else {
      logger.error("No BidList founded with id : {}", id);
      return null;
    }

  }

  // @transactionnal???
  @Override
  public BidList saveBidList(BidList bidList) throws Exception {
    logger.debug("Creating BidList with id : {}", bidList.getBidListId());
    // Checking if BidList already exist
    if (bidListRepository.findById(bidList.getBidListId()).isPresent()) {
      logger.error("This bidList cannot be created because already exist");
      throw new Exception("This bidList already exist");
    } else {
      logger.info("BidList with id : {} created", bidList.getBidListId());
      bidListRepository.save(bidList);
    }
    return bidList;

  }

  @Override
  public BidList updateBidList(BidList bidList) throws Exception {
    logger.debug("Updating BidList with id : {}", bidList.getBidListId());
    // Checking if BidList already exist
    if (bidListRepository.findById(bidList.getBidListId()).isPresent()) {
      logger.info("BidList with id : {} updated", bidList.getBidListId());
      return bidListRepository.save(bidList);
    }

    else {
      logger.error("This bidList cannot be updated because not founded");
      throw new Exception("BidList to update not founded");
    }

  }

  @Override
  public void deleteBidList(BidList bidList) throws Exception {
    logger.debug("Deleting BidList with id : {}", bidList.getBidListId());

    if (getBidListByBidListId(bidList.getBidListId()) != null) {
      logger.info("BidList with id : {} deleted", bidList.getBidListId());
      bidListRepository.delete(bidList);
    } else {
      logger.error("This bidList cannot be deleted because not founded");
      throw new Exception("BidList to delete not founded");
    }

  }

}
