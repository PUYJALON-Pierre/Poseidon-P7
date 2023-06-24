package com.nnk.springboot.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "curvepoint")
public class CurvePoint {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="Id")
  private Integer id;
  
  @Column(name="CurveId")
  Integer curveId;
  
  @Column(name="asOfDate")
  Timestamp asOfDate;
  
  @Column(name="term")
  Double term;
  
  @Column(name="value")
  Double value;
  
  @Column(name="creationDate")
  Timestamp creationDate;



  public CurvePoint(int curveId, double term, double value) {
    this.curveId = curveId;
    this.term = term;
    this.value = value;
  }
}
