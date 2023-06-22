package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "curvepoint")
public class CurvePoint {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  
  
}
