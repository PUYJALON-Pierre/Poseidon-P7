package com.nnk.springboot.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;



/**
 *  Model class for BidList in Trading App UI (Poseidon inc)
 *
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "bidlist")
public class BidList {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="BidListId")
  private Integer bidListId;
  
  @NotNull
  @NotBlank(message = "Account is mandatory")
  @Size(max = 30, message = "Max of {max} characters")
  @Column(name="account")
  private String account;
  
  @NotNull
  @NotBlank(message = "Type is mandatory")
  @Size(max = 30, message = "Max of {max} characters")
  @Column(name="type")
  private String type;
  
  @NotNull(message = "Bid quantity is mandatory")
  @PositiveOrZero
  @Digits(fraction = 2, integer = 20)
  @Column(name="bidQuantity")
  private Double bidQuantity;
  
  @Column(name="askQuantity")
  private Double askQuantity;
  
  @Column(name="bid")
  private Double bid;
  
  @Column(name="ask")
  private Double ask;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="benchmark")
  private String benchmark;
  
  @Column(name="bidListDate")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime bidListDate;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="commentary")
  private String commentary;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="security")
  private String security;
  
  @Size(max = 10, message = "Max of {max} characters")
  @Column(name="status")
  private String status;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="trader")
  private String trader;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="book")
  private String book;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="creationName")
  private String creationName;
  
  @Column(name="creationDate")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime creationDate;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="revisionName")
  private String revisionName;
  
  @Column(name="revisionDate")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime revisionDate;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="dealName")
  private String dealName;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="dealType")
  private String dealType;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="sourceListId")
  private String sourceListId;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="side")
  private String side;

  
  public BidList(String account, String type, Double bidQuantity) {
    this.account = account;
    this.type = type;
    this.bidQuantity = bidQuantity;
}


  
  
  
  
}
