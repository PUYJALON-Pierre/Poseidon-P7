package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.Rating;

public interface IRatingService {

  

  public List<Rating> getAllRatings() ;
  
  public Rating getRatingById(int id) ;
  
  public Rating saveRating(Rating rating) throws Exception ;
  
  public Rating updateRating(Rating rating) throws Exception ;
  
  public void deleteRating(Rating rating) throws Exception ;
  

  
}
