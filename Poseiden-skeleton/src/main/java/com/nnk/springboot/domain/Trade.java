package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "trade")
public class Trade {
    // DONE: Map columns in data table TRADE with corresponding java fields
  
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="TradeId")
  private Integer tradeId;
 
  @NotBlank(message = "Account is mandatory")
  @Size(max = 30, message = "Max of {max} characters")
  @Column(name="TradeId")
  String account;
  
  
  @NotBlank(message = "Type is mandatory")
  @Size(max = 30, message = "Max of {max} characters")
  @Column(name="type")
  String type;
  
  @Column(name="buyQuantity")
  Double buyQuantity;
  
  @Column(name="sellQuantity")
  Double sellQuantity;
  
  @Column(name="buyPrice")
  Double buyPrice;
  
  @Column(name="sellPrice")
  Double sellPrice;
  
  @Column(name="tradeDate")
  Timestamp tradeDate;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="security")
  String security;
  
  @Size(max = 10, message = "Max of {max} characters")
  @Column(name="status")
  String status;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="trader")
  String trader;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="benchmark")
  String benchmark;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="book")
  String book;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="creationName")
  String creationName;
  

  @Column(name="creationDate")
  Timestamp creationDate;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="revisionName")
  String revisionName;
  
  @Column(name="revisionDate")
  Timestamp revisionDate;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="dealName")
  String dealName;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="dealType")
  String dealType;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="sourceListId")
  String sourceListId;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="side")
  String side;

  
  
  
  
  
  
}
