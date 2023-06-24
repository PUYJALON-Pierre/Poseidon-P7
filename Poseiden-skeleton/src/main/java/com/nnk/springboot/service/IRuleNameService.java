package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.RuleName;

public interface IRuleNameService {

  
  
  public List<RuleName> getAllRuleNames() ;
  
  public RuleName getRuleNameById(int id) ;
  
  public RuleName saveRuleName(RuleName ruleName) throws Exception ;
  
  public RuleName updateRuleName(RuleName ruleName) throws Exception ;
  
  public void deleteRuleName(RuleName ruleName) throws Exception ;
  
  
  
}
