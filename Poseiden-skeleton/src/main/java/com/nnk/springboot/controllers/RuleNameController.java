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

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.IBidListService;
import com.nnk.springboot.service.IRuleNameService;


@Controller
@RequestMapping("/ruleName")
public class RuleNameController {
  
  final static Logger logger = LogManager.getLogger(RuleNameController.class);
  
  @Autowired
  IRuleNameService iRuleNameService;


    @GetMapping("/list")
    public String home(Model model)
    {    logger.debug("Getting request ruleName/list");
          model.addAttribute("ruleNames", iRuleNameService.getAllRuleNames());
        return "ruleName/list";
    }

    @GetMapping("/add")
    public String addRuleForm(RuleName bid) {
      logger.debug("Getting request ruleName/add");
        return "ruleName/add";
    }

    @PostMapping("/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) throws Exception {

      logger.debug("Posting request ruleName/validate for ruleName with id:{}", ruleName.getId());
      if (!result.hasErrors()) {
        iRuleNameService.saveRuleName(ruleName);
        model.addAttribute("ruleNames", iRuleNameService.getAllRuleNames());
        return "redirect:/ruleName/list";
      }
      logger.error("Error during saving ruleName : {}", result.getFieldError());
  
        return "ruleName/add";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

      logger.debug("Getting request ruleName/update/{id} for ruleName with id:{}", id);
      RuleName newRuleName = iRuleNameService.getRuleNameById(id);
      model.addAttribute("ruleName", newRuleName);
        return "ruleName/update";
    }

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

    @GetMapping("/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) throws Exception {

      logger.debug("Getting request ruleName/delete/{id} for ruleName with id:{}", id);
      RuleName newRuleName = iRuleNameService.getRuleNameById(id);
      iRuleNameService.deleteRuleName(newRuleName);
      model.addAttribute("ruleNames", iRuleNameService.getAllRuleNames());
        return "redirect:/ruleName/list";
    }
}
