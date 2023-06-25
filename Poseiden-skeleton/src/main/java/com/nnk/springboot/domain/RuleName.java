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
 *  Model class for RuleName in Trading App UI (Poseidon inc)
 *
 */
@Data
@NoArgsConstructor
@Entity @Table(name = "rulename")
public class RuleName {

  @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "Id")
  private Integer id;

  @Column(name = "name") @Size(max = 125, message = "Max of {max} characters")
  String name;

  @Column(name = "description") @Size(max = 125, message = "Max of {max} characters")
  String description;


  @Column(name = "json") @Size(max = 125, message = "Max of {max} characters")
  String json;

  @Column(name = "template") @Size(max = 512, message = "Max of {max} characters")
  String template;

  @Column(name = "sqlStr") @Size(max = 125, message = "Max of {max} characters")
  String sqlStr;

  @Column(name = "sqlPart") @Size(max = 125, message = "Max of {max} characters")
  String sqlPart;

  
  
  public RuleName(String name, String description, String json, String template, String sqlStr,
      String sqlPart) {
    super();
    this.name = name;
    this.description = description;
    this.json = json;
    this.template = template;
    this.sqlStr = sqlStr;
    this.sqlPart = sqlPart;
  }
  
}
