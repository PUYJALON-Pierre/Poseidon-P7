package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.BidList;



public interface IBidListService {

  
  
  
  
  public List<BidList> getAllBidLists();
  
  public BidList getBidListByBidListId(int id) throws Exception ;
  
  public BidList saveBidList(BidList bidList) throws Exception ;
  
  public BidList updateBidList(BidList bidList) throws Exception ;
  
  public void deleteBidList(BidList bidList) throws Exception ;
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}
