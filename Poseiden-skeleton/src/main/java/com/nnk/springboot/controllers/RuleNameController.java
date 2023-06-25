package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.IRuleNameService;

/**
 * Controller class for RuleName view in Trading App UI (Poseidon inc)
 *
 * @author PUYJALON Pierre
 * @since 25/06/2023
 */
@Controller @RequestMapping("/ruleName")
public class RuleNameController {

  final static Logger logger = LogManager.getLogger(RuleNameController.class);

  @Autowired
  IRuleNameService iRuleNameService;

  /**
   * Get ruleName/list page model
   * 
   * @param model - Model
   * @return list (html template)
   */
  @GetMapping("/list")
  public String home(Model model) {
    logger.debug("Getting request ruleName/list");
    model.addAttribute("ruleNames", iRuleNameService.getAllRuleNames());
    return "ruleName/list";
  }

  /**
   * Get ruleName/add page model
   * 
   * @param ruleName - RuleName
   * @return add (html template)
   */
  @GetMapping("/add")
  public String addRuleForm(RuleName ruleName) {
    logger.debug("Getting request ruleName/add");
    return "ruleName/add";
  }

  /**
   * Creating a new RuleName
   * 
   * @param ruleName - RuleName
   * @param result - BindingResult
   * @param model - Model
   * @return redirect:/ruleName/list or ruleName/add
   * @throws Exception
   */
  @PostMapping("/validate")
  public String validate(@Valid RuleName ruleName, BindingResult result, Model model)
      throws Exception {

    logger.debug("Posting request ruleName/validate for ruleName with id:{}", ruleName.getId());
    if (!result.hasErrors()) {
      iRuleNameService.saveRuleName(ruleName);
      model.addAttribute("ruleNames", iRuleNameService.getAllRuleNames());
      return "redirect:/ruleName/list";
    }
    logger.error("Error during saving ruleName : {}", result.getFieldError());

    return "ruleName/add";
  }

  /**
   * Get ruleName/update page model
   * 
   * @param id - int
   * @param model - Model
   * @return ruleName/update
   */
  @GetMapping("/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

    logger.debug("Getting request ruleName/update/{id} for ruleName with id:{}", id);
    RuleName newRuleName = iRuleNameService.getRuleNameById(id);
    model.addAttribute("ruleName", newRuleName);
    return "ruleName/update";
  }

  /**
   * Post request for updating a RuleName by his id
   * 
   * @param id - int
   * @param ruleName - RuleName
   * @param result - BindingResult
   * @param model - Model
   * @return redirect:/ruleName/list or ruleName/update
   * @throws Exception
   */
  @PostMapping("/update/{id}")
  public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
      BindingResult result, Model model) throws Exception {

    logger.debug("Posting request ruleName/update/{id} for ruleName with id:{}", id);
    if (result.hasErrors()) {
      logger.error("Error during updating ruleName : {}", result.getFieldError());
      return "ruleName/update";
    }
    ruleName.setId(id);
    iRuleNameService.updateRuleName(ruleName);
    model.addAttribute("ruleNames", iRuleNameService.getAllRuleNames());
    return "redirect:/ruleName/list";
  }

  /**
   * Delete request for deleting a RuleName by his id
   * 
   * @param id - int
   * @param model - Model
   * @return redirect:/ruleName/list
   * @throws Exception
   */
  @GetMapping("/delete/{id}")
  public String deleteRuleName(@PathVariable("id") Integer id, Model model) throws Exception {

    logger.debug("Getting request ruleName/delete/{id} for ruleName with id:{}", id);
    RuleName newRuleName = iRuleNameService.getRuleNameById(id);
    iRuleNameService.deleteRuleName(newRuleName);
    model.addAttribute("ruleNames", iRuleNameService.getAllRuleNames());
    return "redirect:/ruleName/list";
  }
}
