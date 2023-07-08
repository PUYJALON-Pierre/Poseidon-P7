package com.nnk.springboot.domain;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jparams.verifier.tostring.ToStringVerifier;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RatingTest {

  
  @Test
  public void ratingHashCodeTest() {

    Rating rating1 = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
    Rating rating2 = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);
   
    assertNotSame( rating1,  rating2);
    assertEquals( rating1.hashCode(),  rating2.hashCode());
  }

  @Test
  public void ratingToStringTest() {

    ToStringVerifier.forClass(Rating.class).verify();
  }

  
  
}
