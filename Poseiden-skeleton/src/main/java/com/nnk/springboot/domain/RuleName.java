package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "rulename")
public class RuleName {

  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="Id")
  private Integer id;
  
  
  @Column(name="name")
  @Size(max = 125, message = "Max of {max} characters")
  String name;
  
  @Column(name="description")
  @Size(max = 125, message = "Max of {max} characters")
  String description;
  
  @Column(name="json")
  @Size(max = 125, message = "Max of {max} characters")
  String json;
  
  @Column(name="template")
  @Size(max = 512, message = "Max of {max} characters")
  String template;
  
  @Column(name="sqlStr")
  @Size(max = 125, message = "Max of {max} characters")
  String sqlStr;
  
  @Column(name="sqlPart")
  @Size(max = 125, message = "Max of {max} characters")
  String sqlPart;

  
}
