package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "rating")
public class Rating {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  
}
