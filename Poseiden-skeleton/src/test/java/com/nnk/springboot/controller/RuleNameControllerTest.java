package com.nnk.springboot.controller;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.controllers.CurvePointController;
import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.IBidListService;
import com.nnk.springboot.service.ICurvePointService;
import com.nnk.springboot.service.IRatingService;
import com.nnk.springboot.service.IRuleNameService;

@ExtendWith(SpringExtension.class) @WebMvcTest(controllers = RuleNameController.class) @TestInstance(Lifecycle.PER_CLASS)
public class RuleNameControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  IRuleNameService iRuleNameService;

  @MockBean
  RuleNameRepository ruleNameRepository;

  private RuleName ruleName;
  private List<RuleName> ruleNames = new ArrayList<>();

  @BeforeEach
  public void setup() {

    ruleName = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

    ruleNames.add(ruleName);

    when(iRuleNameService.getAllRuleNames()).thenReturn(ruleNames);
  }

  @Test @WithMockUser(roles = "ADMIN")
  public void getViewRuleNamePageModelTest() throws Exception {
    mockMvc.perform(get("/ruleName/list")).andExpect(status().isOk());
  }

  @Test @WithMockUser(roles = "ADMIN")
  public void getViewAddRuleNamePageModelTest() throws Exception {
    mockMvc.perform(get("/ruleName/add")).andExpect(status().isOk());
  }

  @Test @WithMockUser(roles = "ADMIN")
  public void postRuleNameTest() throws Exception {
    mockMvc.perform(post("/ruleName/validate")
        .with(csrf().asHeader())
        .param("name", "Rule Name")
        .param("description", "Description")
        .param("json", "Json")
        .param("template", "Template")
        .param("sqlStr", "SQL")
        .param("sqlPart", "SQL Part"))
        .andExpect(redirectedUrl("/ruleName/list")).andExpect(status().is3xxRedirection());

  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void getViewUpdateRuleNamePageModelTest() throws Exception {
  
    when(ruleNameRepository.findById(6)).thenReturn(Optional.of(ruleName));
    when(iRuleNameService.getRuleNameById(6)).thenReturn(ruleName);
    
    mockMvc.perform(get("/ruleName/update/{id}", "6")).andExpect(status().isOk());}

  @Test
  @WithMockUser(roles = "ADMIN")
  public void updateRuleNameTest() throws Exception {
 
    when(ruleNameRepository.findById(6)).thenReturn(Optional.of(ruleName));
    when(iRuleNameService.updateRuleName(ruleName)).thenReturn(ruleName);
    
    mockMvc.perform(post("/ruleName/update/{id}", "6")
        .with(csrf().asHeader())
        .param("name", "Rule Name")
        .param("description", "Description")
        .param("json", "Json")
        .param("template", "Template")
        .param("sqlStr", "SQL")
        .param("sqlPart", "SQL Part"))
        .andExpect(redirectedUrl("/ruleName/list")).andExpect(status().is3xxRedirection());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void deleteRuleNameTest() throws Exception {
    
    when(ruleNameRepository.findById(6)).thenReturn(Optional.of(ruleName));
    when(iRuleNameService.getRuleNameById(6)).thenReturn(ruleName);
    
    mockMvc.perform(get("/ruleName/delete/{id}", "6")
        .with(csrf().asHeader())
        .param("name", "Rule Name")
        .param("description", "Description")
        .param("json", "Json")
        .param("template", "Template")
        .param("sqlStr", "SQL")
        .param("sqlPart", "SQL Part"))
        .andExpect(redirectedUrl("/ruleName/list")).andExpect(status().is3xxRedirection());

  }

}
