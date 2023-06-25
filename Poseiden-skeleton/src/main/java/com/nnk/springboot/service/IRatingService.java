package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.Rating;

/**
 * Interface for services operations concerning Rating in Trading App UI (Poseidon inc)
 *
 * @author PUYJALON Pierre
 * @since 25/06/2023
 */
public interface IRatingService {

  /**
   * Retrieve a list of all Rating
   * 
   * @return List of Rating
   */
  public List<Rating> getAllRatings();

  /**
   * Retrieve a Rating by his id
   * 
   * @param id - int
   * @return Rating
   * @throws Exception
   */
  public Rating getRatingById(int id);

  /**
   * Save a Rating
   * 
   * @param rating - Rating
   * @return Rating
   * @throws Exception
   */
  public Rating saveRating(Rating rating) throws Exception;

  /**
   * Update an existing Rating
   * 
   * @param rating - Rating
   * @return Rating
   * @throws Exception
   */
  public Rating updateRating(Rating rating) throws Exception;

  /**
   * Delete a Rating
   * 
   * @param rating - Rating
   * @throws Exception
   */
  public void deleteRating(Rating rating) throws Exception;

}
