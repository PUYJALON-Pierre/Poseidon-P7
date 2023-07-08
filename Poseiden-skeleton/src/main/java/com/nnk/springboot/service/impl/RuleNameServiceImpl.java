package com.nnk.springboot.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.IRuleNameService;

@Service
public class RuleNameServiceImpl implements IRuleNameService {

  final static Logger logger = LogManager.getLogger(RuleNameServiceImpl.class);

  RuleNameRepository ruleNameRepository;

  public RuleNameServiceImpl(RuleNameRepository ruleNameRepository) {
    super();
    this.ruleNameRepository = ruleNameRepository;
  }

  @Override
  public List<RuleName> getAllRuleNames() {
    logger.debug("Start finding all ruleNames");
    logger.info("Getting all ruleNames");

    if (ruleNameRepository.findAll().isEmpty()) {
      logger.error("No ruleNames founded");
    }
    return ruleNameRepository.findAll();
  }

  @Override
  public RuleName getRuleNameById(int id) {
    logger.debug("Finding ruleName with id : {}", id);
    if (ruleNameRepository.findById(id).isPresent()) {
      logger.info("Founded ruleName with id : {}", id);
      return ruleNameRepository.findById(id).get();
    } else {
      logger.error("No ruleName founded with id : {}", id);
      return null;
    }
  }

  @Override @Transactional
  public RuleName saveRuleName(RuleName ruleName) throws Exception {

    logger.debug("Creating ruleName");
    return ruleNameRepository.save(ruleName);
  }

  @Override @Transactional
  public RuleName updateRuleName(RuleName ruleName) throws Exception {

    logger.debug("Updating ruleName with id : {}", ruleName.getId());
    // Checking if ruleName already exist
    if (ruleNameRepository.findById(ruleName.getId()).isPresent()) {
      logger.info("ruleName with id : {} updated", ruleName.getId());
      return ruleNameRepository.save(ruleName);
    }

    else {
      logger.error("This ruleName cannot be updated because not founded");
      throw new Exception("RuleName to update not founded");
    }
  }

  @Override @Transactional
  public void deleteRuleName(RuleName ruleName) throws Exception {
    logger.debug("Deleting ruleName with id : {}", ruleName.getId());

    if (getRuleNameById(ruleName.getId()) != null) {
      logger.info("RuleName with id : {} deleted", ruleName.getId());
      ruleNameRepository.delete(ruleName);
    } else {
      logger.error("This ruleName cannot be deleted because not founded");
      throw new Exception("RuleName to delete not founded");
    }
  }

}
