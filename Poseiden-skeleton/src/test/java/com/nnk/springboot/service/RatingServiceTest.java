package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class RatingServiceTest {

  @Autowired
  IRatingService iRatingService;

  @MockBean
  RatingRepository ratingRepository;

  Rating rating = new Rating();

  List<Rating> listRatings = new ArrayList<>();

  @BeforeEach
  public void setup() {

    rating.setId(1);
    rating.setMoodysRating("Moodys Rating");
    rating.setSandPRating("Sand PRating");
    rating.setFitchRating("Fitch Rating");
    rating.setOrderNumber(10);

  }

  @Test
  public void getAllRatingsTest() {

    listRatings.add(rating);

    // when
    when(ratingRepository.findAll()).thenReturn(listRatings);

    assertEquals(iRatingService.getAllRatings().size(), 1);

    assertEquals((int) iRatingService.getAllRatings().get(0).getId(), 1);
    assertEquals(iRatingService.getAllRatings().get(0).getMoodysRating(), "Moodys Rating");
    assertEquals(iRatingService.getAllRatings().get(0).getSandPRating(), "Sand PRating");
    assertEquals(iRatingService.getAllRatings().get(0).getFitchRating(), "Fitch Rating");
    assertEquals((int) iRatingService.getAllRatings().get(0).getOrderNumber(), 10);

  }

  @Test
  public void getRatingByIdTest() throws Exception {

    // when
    when(ratingRepository.findById(rating.getId())).thenReturn(Optional.of(rating));
    
    
    assertEquals((int)iRatingService.getRatingById(rating.getId()).getId(),
        1);
    assertEquals(iRatingService.getRatingById(rating.getId()).getMoodysRating(),
        "Moodys Rating");
    assertEquals(iRatingService.getRatingById(rating.getId()).getSandPRating(),
        "Sand PRating");
    assertEquals(iRatingService.getRatingById(rating.getId()).getFitchRating(),
        "Fitch Rating");
    assertEquals((int)iRatingService.getRatingById(rating.getId()).getOrderNumber(),
    10);

  }

  @Test
  public void saveRatingTest() throws Exception {

    Rating rating2 = new Rating();

    rating2.setId(2);
    rating2.setMoodysRating("Moodys Rating");
    rating2.setSandPRating("Sand PRating");
    rating2.setFitchRating("Fitch Rating");
    rating2.setOrderNumber(15);

    when(ratingRepository.save(rating2)).thenReturn(rating2);

    // when

    assertEquals(iRatingService.saveRating(rating2), rating2);
    verify(ratingRepository, times(1)).save(rating2);
  }

  @Test
  public void updateRatingTest() throws Exception {

    rating.setId(5);
    rating.setMoodysRating("Moodys Ratingg");
    rating.setSandPRating("Sand PRatingg");
    rating.setFitchRating("Fitch Ratingg");
    rating.setOrderNumber(1);

    when(ratingRepository.findById(rating.getId())).thenReturn(Optional.of(rating));
    when(ratingRepository.save(rating)).thenReturn(rating);
    // when

    assertEquals(iRatingService.saveRating(rating), rating);
    verify(ratingRepository, times(1)).save(rating);

  }

  @Test
  public void updateRatingFailTest() throws Exception {
    try {

      rating.setId(5);
      rating.setMoodysRating("Moodys Ratingg");
      rating.setSandPRating("Sand PRatingg");
      rating.setFitchRating("Fitch Ratingg");
      rating.setOrderNumber(1);

      when(ratingRepository.findById(rating.getId())).thenReturn(Optional.empty());

      // when
      iRatingService.updateRating(rating);

    } catch (Exception e) {
      assertEquals(e.getMessage(), "Rating to update not founded");
    }
  }

  @Test
    public void deleteRatingTest() throws Exception {
    
    when(ratingRepository.findById(rating.getId())).thenReturn(Optional.of(rating));
      
      // when
      iRatingService.deleteRating(rating);
      
      verify(ratingRepository, times(1)).delete(rating);

    }

  @Test
  public void deleteRatingFailTest() throws Exception {

    try {
      when(ratingRepository.findById(rating.getId())).thenReturn(Optional.empty());

      // when
      iRatingService.deleteRating(rating);

    } catch (Exception e) {
      assertEquals(e.getMessage(), "Rating to delete not founded");
    }

  }

}
