package com.nnk.springboot.controller;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.controllers.CurvePointController;
import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.IBidListService;
import com.nnk.springboot.service.ICurvePointService;
import com.nnk.springboot.service.IRatingService;

@ExtendWith(SpringExtension.class) @WebMvcTest(controllers = RatingController.class) @TestInstance(Lifecycle.PER_CLASS)
public class RatingControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  IRatingService iRatingService;

  @MockBean
  RatingRepository ratingRepository;

  private Rating rating;
  private List<Rating> ratings = new ArrayList<>();

  @BeforeEach
  public void setup() {

    rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

    ratings.add(rating);

    when(iRatingService.getAllRatings()).thenReturn(ratings);
  }

  @Test @WithMockUser(roles = "ADMIN")
  public void getViewRatingPageModelTest() throws Exception {
    mockMvc.perform(get("/rating/list")).andExpect(status().isOk());
  }

  @Test @WithMockUser(roles = "ADMIN")
  public void getViewAddRatingPageModelTest() throws Exception {
    mockMvc.perform(get("/rating/add")).andExpect(status().isOk());
  }

  @Test @WithMockUser(roles = "ADMIN")
  public void postRatingTest() throws Exception {
    mockMvc.perform(post("/rating/validate").with(csrf().asHeader())
        .param("moodysRating", rating.getMoodysRating())
        .param("sandPRating", rating.getSandPRating()).param("fitchRating", rating.getFitchRating())
        .param("orderNumber", rating.getOrderNumber().toString()))
        .andExpect(redirectedUrl("/rating/list")).andExpect(status().is3xxRedirection());

  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void getViewUpdateRatingPageModelTest() throws Exception {
  
    when(ratingRepository.findById(6)).thenReturn(Optional.of(rating));
    when(iRatingService.getRatingById(6)).thenReturn(rating);
    
    mockMvc.perform(get("/rating/update/{id}", "6")).andExpect(status().isOk());}

  @Test
  @WithMockUser(roles = "ADMIN")
  public void updateRatingTest() throws Exception {
 
    when(ratingRepository.findById(6)).thenReturn(Optional.of(rating));
    when(iRatingService.updateRating(rating)).thenReturn(rating);
    
    mockMvc.perform(post("/rating/update/{id}", "6")
        .with(csrf().asHeader())
        .param("moodysRating", rating.getMoodysRating())
        .param("sandPRating", rating.getSandPRating())
        .param("fitchRating", rating.getFitchRating())
        .param("orderNumber", rating.getOrderNumber().toString()))
        .andExpect(redirectedUrl("/rating/list")).andExpect(status().is3xxRedirection());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void deleteRatingTest() throws Exception {
    
    when(ratingRepository.findById(6)).thenReturn(Optional.of(rating));
    when(iRatingService.getRatingById(6)).thenReturn(rating);
    
    mockMvc.perform(get("/rating/delete/{id}", "6")
        .with(csrf().asHeader())
        .param("moodysRating", rating.getMoodysRating())
        .param("sandPRating", rating.getSandPRating())
        .param("fitchRating", rating.getFitchRating())
        .param("orderNumber", rating.getOrderNumber().toString()))
        .andExpect(redirectedUrl("/rating/list")).andExpect(status().is3xxRedirection());

  }

}
