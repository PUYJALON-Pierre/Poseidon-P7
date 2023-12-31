package com.nnk.springboot.domain;

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
 *  Model class for RuleName in Trading App UI (Poseidon inc)
 *
 */
@NoArgsConstructor
@Data
@Entity @Table(name = "rulename")
public class RuleName {

  @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name = "Id")
  private Integer id;

  @NotBlank(message = "Name is mandatory")
  @Column(name = "name") @Size(max = 125, message = "Max of {max} characters")
  String name;

  @NotBlank(message = "Description is mandatory")
  @Column(name = "description") @Size(max = 125, message = "Max of {max} characters")
  String description;

  @NotBlank(message = "Json is mandatory")
  @Column(name = "json") @Size(max = 125, message = "Max of {max} characters")
  String json;

  @NotBlank(message = "Template is mandatory")
  @Column(name = "template") @Size(max = 512, message = "Max of {max} characters")
  String template;

  @NotBlank(message = "SqlStr is mandatory")
  @Column(name = "sqlStr") @Size(max = 125, message = "Max of {max} characters")
  String sqlStr;

  @NotBlank(message = "SqlPart is mandatory")
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
