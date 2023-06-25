package com.nnk.springboot.service;

import java.util.List;

import com.nnk.springboot.domain.RuleName;

/**
 * Interface for services operations concerning RuleName in Trading App UI (Poseidon inc)
 *
 * @author PUYJALON Pierre
 * @since 25/06/2023
 */
public interface IRuleNameService {

  /**
   * Retrieve a list of all RuleName
   * 
   * @return List of RuleName
   */
  public List<RuleName> getAllRuleNames();

  /**
   * Retrieve a RuleName by his id
   * 
   * @param id - int
   * @return RuleName
   * @throws Exception
   */
  public RuleName getRuleNameById(int id);

  /**
   * Save a RuleName
   * 
   * @param ruleName - RuleName
   * @return RuleName
   * @throws Exception
   */
  public RuleName saveRuleName(RuleName ruleName) throws Exception;

  /**
   * Update an existing RuleName
   * 
   * @param ruleName - RuleName
   * @return RuleName
   * @throws Exception
   */
  public RuleName updateRuleName(RuleName ruleName) throws Exception;

  /**
   * Delete a RuleName
   * 
   * @param ruleName - RuleName
   * @throws Exception
   */
  public void deleteRuleName(RuleName ruleName) throws Exception;

}
