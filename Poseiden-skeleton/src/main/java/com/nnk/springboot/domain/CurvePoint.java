package com.nnk.springboot.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class for CurvePoint in Trading App UI (Poseidon inc)
 *
 */
@NoArgsConstructor @Data @Entity @Table(name = "curvepoint")
public class CurvePoint {

  @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "Id")
  private Integer id;

  @NotNull(message = "Curve Id is mandatory")
  @Positive
  @Digits(fraction =0, integer = 3)
  @DecimalMax(value = "127", message = "Out of range")
  @Column(name = "CurveId")
  Integer curveId;

  @Column(name = "asOfDate") @DateTimeFormat(pattern = "yyyy-MM-dd")
  LocalDateTime asOfDate;

  @NotNull(message = "Term is mandatory")
  @Digits(fraction = 2, integer = 20)
  @Positive
  @Column(name = "term")
  Double term;

  @NotNull(message = "Value is mandatory")
  @Digits(fraction = 2, integer = 20)
  @Positive
  @Column(name = "value")
  Double value;

  @Column(name = "creationDate") @DateTimeFormat(pattern = "yyyy-MM-dd")
  LocalDateTime creationDate;

  public CurvePoint(int curveId, double term, double value) {
    this.curveId = curveId;
    this.term = term;
    this.value = value;
  }
}
