package com.nnk.springboot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@ExtendWith(SpringExtension.class) @SpringBootTest @TestInstance(Lifecycle.PER_CLASS)
public class RuleNameServiceTest {

  @Autowired
  IRuleNameService iRuleNameService;

  @MockBean
  RuleNameRepository ruleNameRepository;

  RuleName ruleName = new RuleName();

  List<RuleName> listRuleNames = new ArrayList<>();

  @BeforeEach
  public void setup() {

    ruleName.setId(1);
    ruleName.setName("Rule Name");
    ruleName.setDescription("Description");
    ruleName.setJson("Json");
    ruleName.setTemplate("Template");
    ruleName.setSqlStr("SQL");
    ruleName.setSqlPart("SQL Part");
  }

  
  @Test
  public void getRuleNamesTest() {

    listRuleNames.add(ruleName);

    // when
    when(ruleNameRepository.findAll()).thenReturn(listRuleNames);

    assertEquals(iRuleNameService.getAllRuleNames().size(), 1);

    assertEquals((int)iRuleNameService.getAllRuleNames().get(0).getId(), 1);
    assertEquals(iRuleNameService.getAllRuleNames().get(0).getName(), "Rule Name");
    assertEquals(iRuleNameService.getAllRuleNames().get(0).getDescription(), "Description");
    assertEquals(iRuleNameService.getAllRuleNames().get(0).getJson(), "Json");
    assertEquals(iRuleNameService.getAllRuleNames().get(0).getTemplate(), "Template");
    assertEquals(iRuleNameService.getAllRuleNames().get(0).getSqlStr(), "SQL");
    assertEquals(iRuleNameService.getAllRuleNames().get(0).getSqlPart(), "SQL Part");
  }

  @Test
  public void getRuleNameByIdTest() throws Exception {

    // when
    when(ruleNameRepository.findById(ruleName.getId())).thenReturn(Optional.of(ruleName));
    
    
    assertEquals((iRuleNameService.getRuleNameById(ruleName.getId()).getName()),
        "Rule Name");
    assertEquals((iRuleNameService.getRuleNameById(ruleName.getId()).getDescription()),
        "Description");

  }

  @Test
  public void saveRuleNameTest() throws Exception {

    RuleName ruleName2 = new RuleName();

    ruleName2.setId(2);
    ruleName2.setName("Rule Name");
    ruleName2.setDescription("Description");
    ruleName2.setJson("Json");
    ruleName2.setTemplate("Template");
    ruleName2.setSqlStr("SQL");
    ruleName2.setSqlPart("SQL Part");

    when(ruleNameRepository.save(ruleName2)).thenReturn(ruleName2);

    // when

    assertEquals(iRuleNameService.saveRuleName(ruleName2), ruleName2);
    verify(ruleNameRepository, times(1)).save(ruleName2);
  }

  @Test
  public void updateRuleNameTest() throws Exception {

    ruleName.setId(5);
    ruleName.setName("Rule Name");
    ruleName.setDescription("Description");
    ruleName.setJson("Json");
    ruleName.setTemplate("Template");
    ruleName.setSqlStr("SQL");
    ruleName.setSqlPart("SQL Part");

    when(ruleNameRepository.findById(ruleName.getId())).thenReturn(Optional.of(ruleName));
    when(ruleNameRepository.save(ruleName)).thenReturn(ruleName);
    // when

    assertEquals(iRuleNameService.updateRuleName(ruleName), ruleName);
    verify(ruleNameRepository, times(1)).save(ruleName);

  }

  @Test
  public void updateRuleNameFailTest() throws Exception {
    try {

      ruleName.setId(5);
      ruleName.setName("Rule Name");
      ruleName.setDescription("Description");
      ruleName.setJson("Json");
      ruleName.setTemplate("Template");
      ruleName.setSqlStr("SQL");
      ruleName.setSqlPart("SQL Part");

      when(ruleNameRepository.findById(ruleName.getId())).thenReturn(Optional.empty());

      // when
      iRuleNameService.updateRuleName(ruleName);

    } catch (Exception e) {
      assertEquals(e.getMessage(), "RuleName to update not founded");
    }
  }

  @Test
    public void deleteRuleNameTest() throws Exception {
    
    when(ruleNameRepository.findById(ruleName.getId())).thenReturn(Optional.of(ruleName));
      
      // when
      iRuleNameService.deleteRuleName(ruleName);
      
      verify(ruleNameRepository, times(1)).delete(ruleName);

    }

  @Test
  public void deleteRuleNameFailTest() throws Exception {

    try {
      when(ruleNameRepository.findById(ruleName.getId())).thenReturn(Optional.empty());

      // when
      iRuleNameService.deleteRuleName(ruleName);

    } catch (Exception e) {
      assertEquals(e.getMessage(), "RuleName to delete not founded");
      verify(ruleNameRepository, times(0)).delete(ruleName);
    }

  }

}
