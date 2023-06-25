package com.nnk.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *  Model class for rating in Trading App UI (Poseidon inc)
 *
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "rating")
public class Rating {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="Id")
  private Integer id;
  
  @Column(name="moodysRating")
  @Size(max = 125, message = "Max of {max} characters")
  String moodysRating;
 
  @Column(name="sandPRating")
  @Size(max = 125, message = "Max of {max} characters")
  String sandPRating;

  @Column(name="fitchRating")
  @Size(max = 125, message = "Max of {max} characters")
  String fitchRating;
 
  @Column(name="orderNumber")
  Integer orderNumber;
  
  public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
    this.moodysRating = moodysRating;
    this.sandPRating = sandPRating;
    this.fitchRating = fitchRating;
    this.orderNumber = orderNumber;
}
  
}
