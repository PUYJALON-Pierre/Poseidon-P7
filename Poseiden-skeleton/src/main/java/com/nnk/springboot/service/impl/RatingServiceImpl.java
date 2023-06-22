package com.nnk.springboot.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.IRatingService;

@Service
public class RatingServiceImpl implements IRatingService {
 
  final static Logger logger = LogManager.getLogger(RatingServiceImpl.class);
  
  @Autowired
  RatingRepository ratingRepository;

  @Override
  public List<Rating> getAllRatings() {
    logger.debug("Start finding all ratings");
    logger.info("Getting all ratings");

    if (ratingRepository.findAll().isEmpty()) {
      logger.error("No ratings founded");
    }
    return ratingRepository.findAll();
  }

  @Override
  public Rating getRatingById(int id) {
  
    logger.debug("Finding rating with id : {}", id);
    if (ratingRepository.findById(id).isPresent()) {
      logger.info("Founded rating with id : {}", id);
      return ratingRepository.findById(id).get();
    } else {
      logger.error("No rating founded with id : {}", id);
      return null;
    }
  }

  @Override
  public Rating saveRating(Rating rating) throws Exception {
    
    logger.debug("Creating rating with id : {}", rating.getId());
    // Checking if rating already exist
    if (ratingRepository.findById(rating.getId()).isPresent()) {
      logger.error("This rating cannot be created because already exist");
      throw new Exception("This rating already exist");
    } else {
      logger.info("Rating with id : {} created", rating.getId());
      ratingRepository.save(rating);
    }
    return rating;
  }

  @Override
  public Rating updateRating(Rating rating) throws Exception {
   
    logger.debug("Updating rating with id : {}", rating.getId());
    // Checking if rating already exist
    if (ratingRepository.findById(rating.getId()).isPresent()) {
      logger.info("Rating with id : {} updated", rating.getId());
      return ratingRepository.save(rating);
    }
    else {
      logger.error("This rating cannot be updated because not founded");
      throw new Exception("Rating to update not founded");
    }
  }

  @Override
  public void deleteRating(Rating rating) throws Exception {
   
    logger.debug("Deleting rating with id : {}", rating.getId());

    if (getRatingById(rating.getId()) != null) {
      logger.info("Rating with id : {} deleted", rating.getId());
      ratingRepository.delete(rating);
    } else {
      logger.error("This rating cannot be deleted because not founded");
      throw new Exception("Rating to delete not founded");
    }
  }
  
  
  
}
