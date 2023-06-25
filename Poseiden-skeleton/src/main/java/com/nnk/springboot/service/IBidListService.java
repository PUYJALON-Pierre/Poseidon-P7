package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.BidList;

/**
 * Interface for services operations concerning BidList in Trading App UI (Poseidon inc)
 *
 * @author PUYJALON Pierre
 * @since 25/06/2023
 */
public interface IBidListService {

  /**
   * Retrieve a list of all BidList
   * 
   * @return List of BidList
   */
  public List<BidList> getAllBidLists();

  /**
   * Retrieve a BidList by his id
   * 
   * @param id - int
   * @return BidList
   * @throws Exception
   */
  public BidList getBidListByBidListId(int id) throws Exception;

  /**
   * Save a BidList
   * 
   * @param bidList - BidList
   * @return BidList
   * @throws Exception
   */
  public BidList saveBidList(BidList bidList) throws Exception;

  /**
   * Update an existing BidList
   * 
   * @param bidList - BidList
   * @return BidList
   * @throws Exception
   */
  public BidList updateBidList(BidList bidList) throws Exception;

  /**
   * Delete a BidList
   * 
   * @param bidList - BidList
   * @throws Exception
   */
  public void deleteBidList(BidList bidList) throws Exception;

}
