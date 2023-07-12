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
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 *  Model class for Trade in Trading App UI (Poseidon inc)
 *
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "trade")
public class Trade {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="TradeId")
  private Integer tradeId;
 
  @NotNull
  @NotBlank(message = "Account is mandatory")
  @Size(max = 30, message = "Max of {max} characters")
  @Column(name="account")
  String account;
  
  @NotNull
  @NotBlank(message = "Type is mandatory")
  @Size(max = 30, message = "Max of {max} characters")
  @Column(name="type")
  String type;
  
  @NotNull(message = "Buy quantity is mandatory")
  @Digits(fraction = 2, integer = 20)
  @Positive
  @Column(name="buyQuantity")
  Double buyQuantity;
  
  @Column(name="sellQuantity")
  Double sellQuantity;
  
  @Column(name="buyPrice")
  Double buyPrice;
  
  @Column(name="sellPrice")
  Double sellPrice;
  
  @Column(name="tradeDate")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  LocalDateTime tradeDate;
  
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
  
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name="creationDate")
  LocalDateTime creationDate;
  
  @Size(max = 125, message = "Max of {max} characters")
  @Column(name="revisionName")
  String revisionName;
  
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name="revisionDate")
  LocalDateTime revisionDate;
  
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

  
  
  public Trade(String account, String type, double buyQuantity) {
    this.account = account;
    this.type = type;
    this.buyQuantity= buyQuantity;

}
  
 
}
