package com.nnk.springboot.domain;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jparams.verifier.tostring.ToStringVerifier;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RuleNameTest {

  
  @Test
  public void ruleNameHashCodeTest() {

    RuleName rule1 = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
    RuleName rule2 = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
   
    assertNotSame( rule1,  rule2);
    assertEquals( rule1.hashCode(),  rule2.hashCode());
  }

  @Test
  public void ruleNameToStringTest() {

    ToStringVerifier.forClass(RuleName.class).verify();
  }

  
  
}
