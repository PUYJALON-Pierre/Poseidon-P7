package com.nnk.springboot.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *  Model class for BidList in Trading App UI (Poseidon inc)
 *
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "bidlist")
public class BidList {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="BidListId")
  private Integer bidListId;
  
  @NotBlank(message = "Account is mandatory")
  @Size(max = 30, message = "Max of {max} characters")
  @Column(name="account")
  private String account;
  
  @NotBlank(message = "Type is mandatory")
  @Size(max = 30, message = "Max of {max} characters")
  @Column(name="type")
  private String type;
  

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
  private Timestamp bidListDate;
  
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
  private Timestamp creationDate;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="revisionName")
  private String revisionName;
  
  @Column(name="revisionDate")
  private  Timestamp revisionDate;
  
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
